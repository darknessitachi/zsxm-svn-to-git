<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>	
		<style>
			.xwintd{ height:28px; line-height:28px;font-size:12px; color:#006699;}
			.xwintxt{ border-bottom:1pt solid #ccc;border-right:1pt solid #ccc ;border-top:2px solid #999;border-left:2px solid #999}
			.xwinfieldset{ border:0px; border-top:1pt solid #b0d4f8; width:95%; height:25px; line-height:25px; font-weight:700; color:#285593}
			.xwinfieldset .open{ background:url(images/skin0/other/filedsetopen.png) no-repeat; padding-left:20px}
			.title {background:#B8CDE3;color:#003770;line-height:26px;height:26px;padding-left:15px;border:1px solid #fff;font-weight:bold;text-align:left;}
		</style>
	</head>
	<body>
		<s:component template="xtitle" theme="app" value="栏目设置"></s:component>

		<s:form name="uForm"  method="post">
		
			<s:hidden name="parMap.lmid"></s:hidden>
			<div style="width:100%;text-align:center;" id="xwincontent">
				<fieldset class="xwinfieldset"><legend class="open">基本信息设置</legend></fieldset>
				<table cellpadding="0" cellspacing="0" style="width:100%" >
					<tr>
						<td class="xwintd" style="text-align:right;padding-right:2px;">
							栏目名称：
						</td>
						<td class="xwintd" style=text-align:left;">
							<input type="text" id="lmmc" class="xwintxt" onchange="checkLmmc();">
						</td>
						
					</tr>
					
				</table>
				<fieldset class="xwinfieldset"><legend class="open">用户权限设置</legend></fieldset>
						
							<div style="height:260px;width:449px;overflow:hidden;border:1px solid #B8D4E9;" >
								<div class="title">
									权限设置
								</div>
								<div id="tree" style="text-align:left;padding:1px;overflow:auto;height:245px;">
									<table style="width:100%">
										<tbody id="lm_tbody"></tbody>
									</table>
								</div>
							</div>
					
				
			</div>
		</s:form>


		<div class="footer">
			
			<input type="button" name="saveBtn" id="saveGoBtn" class="btn" value="保存继续"
					onclick="saveLminfo(1);" />
			<input type="button" name="saveBtn" id="saveCanBtn"  class="btn" value="保存退出"
					onclick="saveLminfo(2);" />
		
			<input type="button" name="closeBtn" class="btn" value="关 闭"
				onclick="parent.closeXwin(self.name);" />
		</div>
	</body>
	<script type="text/javascript">
	var tree;
	loadUser();
	if($("parMap.lmid").value!=""){
		$("saveGoBtn").style.display = "none";
	}
  function loadUser(){
		var ajax = new AppAjax("xxgl!loadLmdetail.do",loadUser_callback);
		ajax.add("parMap.lmid",$("parMap.lmid").value);
		ajax.submit();	
}



function loadUser_callback(data){
	if(!data)return;
	if(data.lmmc)$("lmmc").value = data.lmmc;
	var pdata = data.userList;
	var str = new StringBuffer();
	var temp_str = new StringBuffer();
	var p = 1;
	var len = pdata.length;
	for(var i=0;i<len;i++){
		var _checked = "";
		if(pdata[i].ischecked!="0"){
			_checked = "checked";
		}
		temp_str.append("<td style='border-right:0px;border-left:0px;'>").append("<input name='userCheckBox' type='checkbox' "+_checked+" value='"+pdata[i].userid+"'/>"+pdata[i].cnname).append("</td>");

		if(p%4==0 ){
			str.append("<tr>").append(temp_str).append("</tr>");
			temp_str = new StringBuffer();
		}

		p ++;
		
	}
	str.append("<tr>").append(temp_str).append("</tr>");
	temp_str = new StringBuffer();
	$("lm_tbody").update(str.toString());
}



function checkLmmc(){
		var ajax = new AppAjax("xxgl!checkLmmc.do",function(data){
			if(data.lmmcCode!="0"){
					alert("存在重复的栏目名称，请重新输入！");
					$("lmmc").focus();
					$("lmmc").select();
				}
		});
		ajax.add("parMap.lmmc",$("lmmc").value);
		ajax.submit();
		
}

 function saveLminfo(type){ 	
 	if($("lmmc").value.trim()=="" || $("lmmc").value.trim()==""){
 		alert("栏目名称不能为空！");	
 		return;
 	}
 	
 	var ajax = new AppAjax("xxgl!saveLmgl.do",function(data){save_callback(data,type);});
 		ajax.add("parMap.userList",getQxList());
 		ajax.add("parMap.lmid",$("parMap.lmid").value);
 		ajax.add("parMap.lmmc",$("lmmc").value);
 		ajax.submit();
 }
 
 function save_callback(data,type){
 	alert(data.text);
 	if(data.code == "-1"){
 		
 	}else{
 		getOpener().showZgList();
 		if(type=="2"){			
 			parent.closeXwin(self.name);
 		}
 		if(type == "1"){
 			document.uForm.reset();
 		}
 		
 	}
 }
 
 function getQxList(){
 	var arr = new Array();
 	var objs = document.getElementsByName("userCheckBox");
 	for(var i=0;i<objs.length;i++){
 		if(objs[i].checked){
 			arr.push(objs[i].value);
 		}
 	}
 	return arr.toString();
 }

	</script>
</html>


<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
				<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>

	<s:form name="czrcForm" action='exp!classinfo.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
	<s:hidden name="exp.zjlb" ></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->

	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">

				       	<table align="center" class="fxtable" cellpadding="0" cellspacing="0" id="tab9"  style="">
				       			<tr>
									<td class="bt">入选年份</td>
									<td class="tdright"><s:select name="exp.rxnf" id="exp.rxnf" list="dmmcs" cssStyle="width:130" listKey="dm" listValue="mc" headerKey="" headerValue="--请选择--"/></td>
									
								</tr>
								<tr>	
									<td class="bt">专家类别</td>
									<td class="tdright" colspan=3>

										<INPUT id="exp.bpzjqk_button" class="selectBut2"  title="选择专家类别" value="选择专家类别" type=button onclick="O_D('exp.bpzjqk_button','yjlbd','bottom');">
										<div id="yjlbmc" style="width:100%;display:none"></div>	
									</td>
			
								</tr>
								<tr>	
									<td class="bt">备注</td>
									<td class="tdright" colspan=3><s:textfield cssStyle="width:300px" name="exp.sm"/></td>
			
								</tr>
						</table>					
					
	</div>
	<div class="footer" style="background:#f5f5f5">
		<s:if test="opttype==3">
			<input class="button3" type="button" value="保   存" onclick="javascript:doSave(3);"/>
		</s:if>
		<s:else>
			<!-- <input class="button3" type="button" value="保   存" onclick="javascript:doSave(1);"/> -->
			<input class="button3" type="button" value="保   存" onclick="javascript:doSave(2);"/>
		</s:else>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>

<div id='yjlbd' class="fsg_nr" style="display:none;width:300;height:190;">	
	<div loading='0' id='yjlbdd' style="width:300;height:150;background-color:#E7EAF7;overflow:auto;">
		<s:iterator value="xtdict25">
			<input type="checkbox" name="bpzjqk" id='yjlb<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><span id='ch<s:property value="dictbh"/>'><s:property value="dictname"/></span><br>
		</s:iterator>
	</div>
	
	<div class="footer" style="background:#f5f5f5" style="width:300;height:20;text-align:right">
			<input class="button3" type="button" value="确  定" onclick="selTrue();"/>
		&nbsp&nbsp&nbsp&nbsp
	</div>
	<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
</div>	
	
	</s:form>
	</body>
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		if('<s:property value="exp.bpzjqk"/>' != ''){
			$('yjlb<s:property value="exp.bpzjqk"/>').checked = true;
		}
		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		var reload = 0;
		
		
		function doSave(type){
			
			
			var bc = COM.checkbox('bpzjqk');
			if(bc.length <= 0){
				alert('请选择相应的专家类别！');
				return false;
			}
			
			var c1 =0,c2=0,c3=0;
			for(var i=0;i<bc.length;i++){
				if(bc[i].v == '011' || bc[i].v == '012'){
					++c1;
				}
				if(bc[i].v == '013' || bc[i].v == '014'){
					++c2;
				}
				if(bc[i].v == '015' || bc[i].v == '016'){
					++c3;
				}
			}
			if(c1 >1 || c2>1 || c3>1){
				alert('同一层次的专家不能同时复选!');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("exp!doBpzjI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("exp!doBpzjI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("exp!doBpzjU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				if(type == 1){//保存继续
					reload = 1;
					document.all.czrcForm.reset();
				}else if(type==2){//保存退出
					reload = 1;
					window.parent.refreshmm();
					closeWin();
				}else if(type==3){
					reload = 1;
					window.parent.refreshmm();
					closeWin();
				}
			}else{
				alert(data.message.text);
			}
		}	
		
		
		function selTrue(){
			$('yjlbd').style.display = 'none';
		}	
	</script>
</html>


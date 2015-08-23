<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script src="Framework/Main.js" type="text/javascript"></script>
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:form name="czrcForm" action='exp!preZscq.do' method="post" >
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->
	<div class="title"  onclick=""><h2> 知识产权情况&nbsp&nbsp&nbsp&nbsp&nbsp
	
	</h2>
	<div class="img_right" id="s1" ></div>
	</div>
	<div class="butbar" id="butbar">
		<div class="left">	
			<input type="button" class="button3"  value="新  增" onclick="addxx()"/>
			<input type="button" class="button3"  value="修  改" onclick="repxx()"/>
			<input type="button" class="button3"  value="删  除" onclick="delxx()"/>
		</div>
	</div>	
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table id="tac" style="width:1000px">
			<thead>
				<tr>
					<td width="20px"><input type="checkbox" id="allcheckbox" onclick="RC.checkboxAll('dmkey',this.checked)"></td>
						<td>知识产权编号</td>
						<td>知识产权名称</td>
						<td>知识产权类型</td>
						<td>授权时间</td>
						<td>申请人</td>
						<td>权利人</td>
						<td>来常前知识产权情况</td>
						<td>注册地点</td>
						<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist">
				<tr>
					<td><input type="checkbox" id="dmkey" name="dmkey" value='<s:property value="xh"/>'></td>
						<td><s:property value="zsbh"/></td>
						<td><s:property value="zsmc"/></td>
						<td><s:property value="zsno_mc"/></td>
						<td width="100px"><s:property value="hdrq"/></td>
						<td><s:property value="sqr"/></td>
						<td><s:property value="qlr"/></td>
						<td><s:property value="iscz==2?'来常后':'来常前'"/></td>
						<td><s:property value="zcdd==2?'国外':'国内'"/></td>
						<td><s:property value="sm"/></td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	<div class="footer" style="background:#efefef;">
	<input class="btn_submit1" type="button" value="下 一 步" onclick="javascript:parent.activeChange('rcda14','exp!preOtherinfo.do');"/>
	<s:if test="opttype==3">
		<input class="btn_submit1" type="button" value="退     出" onclick="javascript:closeWindows();"/>
	</s:if>
	<s:else>
		<s:if test="xtuser.loginbz == 1">
				<input class="btn_submit1" type="button" value="填写完成" onclick="javascript:parent.setRcid('',1,'exp!preExp.do',true);"/>
			</s:if>	
		</s:else>
	</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 110)+"px"; 
		function closeWindows(){
			window.parent.parent.refreshP();
			closeWin();
		}
		var diag = null;
		
		function addxx(){
			diag = new Dialog("xxjlinfo1");
			diag.Title = "新增";
			diag.Width = 540;
			diag.Height = 200;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "新增知识产权";
			diag.Message = "请填写个人知识产权情况";
			diag.URL = "exp!preZscqI.do?rcid="+$('rcid').value+"&opttype=1&pname="+$('pname').value+"&winid=xxjlinfo1";
			diag.show();
		}
		
		function repxx(){
			var rcsel = COM.checkbox('dmkey');
			if(rcsel.length == 0){
				alert("请选择需要修改的信息!");
				return false;
			}
			if(rcsel.length > 1){
				alert("修改只能选择一条记录!");
				return false;
			}
			diag = new Dialog("xxjlinfo1");
			diag.Title = "修改";
			diag.Width = 540;
			diag.Height = 200;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "修改知识产权";
			diag.Message = "请修改个人知识产权情况";
			diag.URL = "exp!preZscqU.do?rcid="+$('rcid').value+"&xh="+rcsel[0].v+"&opttype=3&pname="+$('pname').value+"&winid=xxjlinfo1";
			diag.show();
		}
				
		
		function delxx(){
			var rcsel = COM.checkbox('dmkey');
			if(rcsel.length == 0){
				alert("请选择需要删除的信息!");
				return false;
			}
			if(window.confirm("您确定要删除 "+rcsel.length+" 条专家信息?")){
				var ajax = new AppAjax("exp!doZscqD.do",sava_back).submitForm("czrcForm");
			}
		}
		
		function sava_back(data){
			if(data.message.code >0){
				alert("删除成功!");
				refresh();
			}else{
				alert(data.message.text);
			}
		}
		
		function refresh(){
			document.all.czrcForm.submit();
		}
	</script>
</html>


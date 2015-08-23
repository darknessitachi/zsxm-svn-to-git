<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>	
		<script src="Framework/Main.js" type="text/javascript"></script>
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:form name="czrcForm" action='exp!preBpzj.do' method="post" >
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->
	<div class="title"  onclick=""><h2> 专家类别&nbsp&nbsp&nbsp&nbsp&nbsp

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
		<table id="tac">
			<thead>
				<tr>
					<td width="20px"><input type="checkbox" id="allcheckbox" onclick="RC.checkboxAll('dmkey',this.checked)"></td>
						<td width="100px">入选年份</td>
						<td>专家类别</td>
						<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist">
				<tr>
					<td><input type="checkbox" id="dmkey" name="dmkey" value='<s:property value="xh"/>'></td>
						<td><s:property value="rxnf"/></td>
						<td><s:property value="bpzjqk_mc"/></td>
						<td><s:property value="sm"/></td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	
	<div class="footer" style="background:#efefef;">
		<input class="btn_submit1" type="button" value="下 一 步" onclick="javascript:parent.activeChange('rcda10','exp!preZylz.do');"/>
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
		$('tableContainer').style.height = (getSize().h - 120)+"px"; 
		function closeWindows(){
			window.parent.parent.refreshP();
			closeWin();
		}	
		var diag = null;
		
		function addxx(){
			diag = new Dialog("xxjlinfo1");
			diag.Title = "新增";
			diag.Width = 500;
			diag.Height = 220;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "新增专家类别";
			diag.Message = "请填写相应的专家类别";
			diag.URL = "exp!preBpzjI.do?rcid="+$('rcid').value+"&opttype=1&pname="+$('pname').value+"&winid=xxjlinfo1";
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
			diag.Width = 500;
			diag.Height = 220;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "修改专家类别";
			diag.Message = "请修改相应的专家类别";
			diag.URL = "exp!preBpzjU.do?rcid="+$('rcid').value+"&xh="+rcsel[0].v+"&opttype=3&pname="+$('pname').value+"&winid=xxjlinfo1";
			diag.show();
		}
		
		function delxx(){
			data_ = null;
			var rcsel = COM.checkbox('dmkey');
			if(rcsel.length == 0){
				alert("请选择需要删除的信息!");
				return false;
			}
			if(window.confirm("您确定要删除 "+rcsel.length+" 条专家信息?")){
			  	var ajax = new AppAjax("exp!doBpzjD.do",sava_back).submitForm("czrcForm");
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


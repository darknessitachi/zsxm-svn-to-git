<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
	
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='数据退回原因'></s:component>
	<s:form name="czrcForm" id="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="ryid"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >
		
		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		
			<tr>
				<td class="tdleft">退回原因:</td>
				<td colspan=3>
					<s:textarea name="thyy" id="thyy" cols="35" rows="4"></s:textarea>
				</td>
			</tr>
		</table>	


	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确   定" onclick="javascript:doSave(3);"/>
		<input class="button3" type="button" value="退   出" onclick="closeT();"/>
	</div>


	</s:form>
	</body>

	<script type="text/javascript">
		// $('xwincontent').style.height = (getSize().h - 105)+"px"; 
		var reload = 0;
		function closeT(){
			getOpener().refresh();
			closeWindow(self.name);
		}
		
		function doSave(type){
			if($('thyy').value.trim()==''){
				alert('发回原因不能为空！请填写');
				return false;
			}
			var ajax = new AppAjax("rysh!doShth.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				closeT();
			}else{
				alert(data.message.text);
			}
		}
		
	</script>
</html>


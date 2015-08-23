<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
	 <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>		
	<style>

	</style>
	</head>

	<body>
	
	<s:form name="czrcForm" action='exp!classinfo.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcidtb"></s:hidden>
	<s:hidden name="tbtype"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->
	
	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">
    			
				<table align="center" class="fxtable"  cellpadding="0" cellspacing="0"  id="tab4" style="">
	       			<tr>
						<td class="bt" style="width:100px">选择专家分类</td>
						<td >
							<s:select name="expfl" id="expfl"  list="expfllist" cssStyle="width:130" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--" />&nbsp
						</td>
					</tr>
					
				</table>
				
	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确   定" onclick="javascript:doSave(3);"/>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>
	
	</s:form>
	</body>

	<script type="text/javascript">
		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		
		function doSave(type){
			if($('expfl').value==''){
				alert('请选择专家分类');
				return false;
			}
			var ajax = new AppAjax("exptb!doExpTbBatch.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
		}
		function save_Back(data,type){
			if(data.message.code >0){
				alert('同步成功！');
				window.parent.refreshP();
				closeWin();
			}else{
				alert(data.message.text);
			}
		}		
	</script>
</html>


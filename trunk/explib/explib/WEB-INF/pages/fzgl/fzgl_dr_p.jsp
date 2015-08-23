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
									<td class="bt">选择导入模板<font color=red>*</font></td>
									<td class="tdright">
										<select style="width:200px">
											<option> --请选择相应的模板--</option>
										</select>
									</td>
									
								</tr>
								
						</table>					
					
	</div>
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确   定" onclick="javascript:;"/>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>


	</s:form>
	</body>

	<script type="text/javascript">

		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		var reload = 0;
		
	</script>
</html>


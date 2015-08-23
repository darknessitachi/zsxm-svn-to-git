<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
	<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='个人日程安排'></s:component>
	<s:form name="czrcForm" action='schedule!preScheduleI.do' method="post" >
	<s:hidden name="mt.sid"></s:hidden>
	<div  id="tableContainer">
    	<table class="fxtable" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					日程标题
				</td>
				<td>
					<s:property value="mt.sbt"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					开始时间
				</td>
				<td>
					<s:property value="mt.strdate"/>&nbsp&nbsp&nbsp
					<s:property value="mt.strhour"/>时 &nbsp&nbsp
					<s:property value="mt.strminute"/>分
				</td>
			</tr>
			<!-- 
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					结束时间
				</td>
				<td>
					<s:property value="mt.enddate"/>&nbsp&nbsp&nbsp
					<s:property value="mt.endhour"/>时 &nbsp&nbsp
					<s:property value="mt.endminute"/>分
				</td>
			</tr>
			 -->
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					日程内容
				</td>
				<td>
					<s:textarea name="mt.snr" cols="46" rows="10"></s:textarea>
				</td>
			</tr>
		</table>
	</div>


	</s:form>
	</body>

	<script type="text/javascript">
	
	</script>
</html>


<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>	
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:form name="czrcForm" action='fzht!preFzsq.do' method="post" >
	<s:hidden name="htid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->
	<div class="title"  onclick=""><h2> 房租收取&nbsp&nbsp&nbsp&nbsp&nbsp
	
	</h2>
	<div class="img_right" id="s1" ></div>
	</div>
	<div class="butbar" id="butbar">
		<div class="left">	
			
		</div>
	</div>	
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table id="tac">
			<thead>
				<tr>
					<td width="20px"><input type="checkbox" id="allcheckbox" onclick="RC.checkboxAll('dmkey',this.checked)"></td>
					<td width="50px">序号</td>
					
					<td>收缴日期</td>
					<td>收缴年份</td>
					<td>应收房租</td>
					<td>已收房租</td>
					<td>未收房租</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist">
				<tr>
					<td><input type="checkbox" id="dmkey" name="dmkey" value='<s:property value="xh"/>'></td>
						<td><s:property value="xh"/></td>
						<td>
							<s:property value="sjdate"/> 至 <s:property value="sjenddate"/>
						</td>
						<td>
							<s:property value="sjnf"/>
						</td>
						<td>
							<s:property value="ysfzz"/>
						</td>
						<td>
							<s:property value="ysfz"/>
						</td>
						<td>
							<s:property value="wsfz"/>
						</td>
						<td>
							<s:property value="bz"/>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	<div class="footer">
		
		<input class="btn_submit1" type="button" value="退    出" onclick="javascript:doOut(4);"/>
		
	</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 120)+"px"; 
		
		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			parent.closeWindows();
		}
		
		function doOut(type){
			closeWindows();
		}
	</script>
</html>


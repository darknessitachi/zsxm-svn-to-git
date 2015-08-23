<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<style>
		</style>
	</head>
	<body  style="overflow:auto">
	<s:component template="xtitle" theme="app" value='选择单位'></s:component>
	<s:form name="czrcForm" id="form1" action="fzht!getDwWaitSelect.do" method="post">
		
		<s:hidden name="bydm"></s:hidden>
		<s:hidden name="pname"></s:hidden>
		<div class="butbar" id="butbar" style="width:100%;">
			<div class="left">		 	
				字段：
				<s:select name="query.field" list="fields" cssStyle="width:130" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--" />
				=
				<s:textfield name="query.value"></s:textfield>
				<input type="button" class="button"  value="查   询" onclick="query()"/>
				<input type="button" class="button" value="确定选择" onclick="doSave();"/>
				<input type="button" name="resetBtn" class="button" value="关  闭" onclick="closeWin(self.name);"/>
			</div>			
		</div>
		<div id="rcflmx"  class="tableContainer" style="width:100%;height:330px">
			<table align="center" style="width:1000px" >
			<thead>
			<tr><td style="width:10px;"></td>
			<td>组织机构代码</td>
			<td>单位名称</td>
			<td>单位状态</td>
			<td>单位类型</td>
			
			</thead>
			<s:iterator value="qlist">
			<tr>
				<td><input type="radio" name="dwid" onclick="setV('<s:property value="dwid"/>','<s:property value="dwmc"/>')"></td>
				<td><s:property value="dwdm"/></td>
				<td><s:property value="dwmc"/></td>
				<td><s:property value="dwzt_mc"/></td>
				<td><s:property value="dwlx_mc"/></td>
			</tr>
			</s:iterator>
			</table>
		</div>
		
	</s:form> 
</body>
<script type="text/javascript">
	//$('rcflmx').style.height= getSize().h-70;
	
	function query(){
		document.all.form1.submit();
	}
	
	var id='',mc = '';
	function setV(i,m){
		id=i;
		mc=m;
	}
	
	function doSave(){
		var xx = $('pname').value
		var yy= eval('parent.document.'+xx);
		yy.setDw(id,mc);
		closeWin(self.name);
	}
	
</script>
</html>

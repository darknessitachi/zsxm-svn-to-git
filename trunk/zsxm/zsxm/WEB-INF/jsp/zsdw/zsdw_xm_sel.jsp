<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<style>
		</style>
	</head>
	<body  style="overflow:auto">
	<s:component template="xtitle" theme="app" value='选择项目'></s:component>
	<s:form name="czrcForm" id="form1" action="zsdw!getZsxmWaitSelect.do" method="post">
		
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
		<div id="rcflmx"  class="tableContainer" style="width:100%;">
			<table align="center" style="width:1000px" >
			<thead>
			<tr><td style="width:10px;"></td>
			<td>项目名称</td>
			<td>日期</td>
			<td>项目类别</td>
			<td>项目星级</td>
			<td>项目进度概述</td>
			<td>预计投入(万)</td>
			<td>对方联系人</td>
			</thead>
			<s:iterator value="qlist">
			<tr>
				<td><input type="radio" name="xmid" onclick="setV('<s:property value="xmid"/>','<s:property value="xmmc"/>')"></td>
				<td><s:property value="xmmc"/></td>
				<td><s:property value="rq"/></td>
				<td><s:property value="xmlb_mc"/></td>
				<td><s:property value="xmxj_mc"/></td>
				<td><s:property value="xmjdgs"/></td>
				<td><s:property value="yjtr"/></td>
				<td><s:property value="dflxr"/></td>
			</tr>
			</s:iterator>
			</table>
		</div>
		
	</s:form> 
</body>
<script type="text/javascript">
	$('rcflmx').style.height= getSize().h-70;
	
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
		yy.setZzxm(id,mc);
		closeWin(self.name);
	}
	
	function save_Back(data){
		if(data.message.code >0){
			getOpener().refreshflmx();
			closeWin(self.name);
		}else{
			alert(data.message.text);
		}
	}	
</script>
</html>

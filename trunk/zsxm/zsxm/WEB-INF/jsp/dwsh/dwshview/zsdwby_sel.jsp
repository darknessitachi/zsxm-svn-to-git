<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<style>
		</style>
	</head>
	<body  style="overflow:auto">
	<s:form name="czrcForm" id="form1" action="zsdwby!getZsDwWaitSelect.do" method="post">
		<s:component template="xtitle" theme="app" value='选择企业'></s:component>
		<s:hidden name="bydm"></s:hidden>
		<div class="butbar" id="butbar">
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
		<div id="rcflmx"  class="tableContainer" style="width:100%">
			<table align="center" style="width:1000px" >
			<thead>
			<tr><td style="width:10px;"><input type="checkbox" id="alluser" onclick="selectBoxAll()"/></td>
			<td>组织机构代码</td>
			<td>单位名称</td>
			<td>单位状态</td>
			<td>单位类型</td>
			<td>招资项目</td>
			<td>成立日期</td>
			<td>内/外资</td>
			<td>注册资本</td>
			</thead>
			<s:iterator value="qlist">
			<tr>
				<td><input type="checkbox" name="dwid" value='<s:property value="dwid"/>'></td>
				<td><s:property value="dwdm"/></td>
				<td><s:property value="dwmc"/></td>
				<td><s:property value="dwzt_mc"/></td>
				<td><s:property value="dwlx_mc"/></td>
				<td><s:property value="xm_mc"/></td>
				<td><s:property value="clrq"/></td>
				<td><s:property value="nwz_mc"/></td>
				<td align="right"><s:property value="zczb"/></td>
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
	
	function doSave(){
		var ajax = new AppAjax("zsdwby!doSelectZsDwmx.do",save_Back).submitForm("czrcForm");
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

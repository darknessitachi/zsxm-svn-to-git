<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:260px;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:30px;}
		    .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		    .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
		</style>
		<script src="Framework/Main.js" type="text/javascript"></script>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
		<link rel="STYLESHEET" type="text/css" href="js/dhtmlxTab/dhtmlxtabbar.css">
		<script type="text/javascript" src="js/dhtmlxTab/dhtmlxtabbar.js"></script>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/gt_grid.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/default/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/china/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/vista/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/mac/skinstyle.css"/>" />
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
	</head>
	<body  style="overflow:auto">
	<s:form name="czrcForm" id="form1" action="expflwh!getRcxxWaitSelect.do" method="post">
		<s:component template="xtitle" theme="app" value='选择专家'></s:component>
		<s:hidden name="fldm"></s:hidden>
		<s:hidden name="winid" id="winid"></s:hidden>
		<div class="butbar" id="butbar">
			<div class="left">		 	
				字段：
				<s:select name="query.field" list="fields" cssStyle="width:130" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--" />
				=
				<s:textfield name="query.value"></s:textfield>
				<input type="button" class="button"  value="查   询" onclick="query()"/>
				<input type="button" class="button" value="确定选择" onclick="doSave();"/>
				<input type="button" name="resetBtn" class="button" value="关  闭" onclick="closeWin();"/>
			</div>			
		</div>
		<div id="rcflmx"  class="tableContainer" style="width:100%">
			<table align="center" style="width:1000px" >
			<thead>
			<tr><td style="width:10px;"><input type="checkbox" id="alluser" onclick="selectBoxAll()"/></td>
			<td>姓名</td>
			<td>性别</td>
			<td>国籍</td>
			<td>证件类别</td>
			<td>证件号码</td>
			<td>专家类别</td>
			<td>学历</td>
			<td>学位</td>
			</thead>
			<s:iterator value="qlist">
			<tr>
				<td><input type="checkbox" name="rcid" value='<s:property value="rcid"/>'></td>
				<td><s:property value="rcname"/></td>
				<td><s:property value="sex_mc"/></td>
				<td><s:property value="nation_mc"/></td>
				<td><s:property value="zjlb_mc"/></td>
				<td><s:property value="zjno"/></td>
				<td><s:property value="rclb_mc"/></td>
				<td><s:property value="xl_mc"/></td>
				<td><s:property value="xw_mc"/></td>
			</tr>
			</s:iterator>
			</table>
		</div>
		
	</s:form> 
</body>
<script type="text/javascript">
	$('rcflmx').style.height= getSize().h-40;
	
	function query(){
		document.all.form1.submit();
	}
	
	function doSave(){
		var ajax = new AppAjax("expflwh!selectRcflmx.do",save_Back).submitForm("czrcForm");
	}
	
	function save_Back(data){
		if(data.message.code >0){
			window.parent.refreshmm();
			closeWin();
		}else{
			alert(data.message.text);
		}
	}	
</script>
</html>

<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/gt_grid.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/default/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/china/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/vista/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/mac/skinstyle.css"/>" />
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
	</head>
	<body  style="overflow:auto">
	<s:form name="emasForm" id="form1" method="post">	
				<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
					<tr>
					    <td class="lefttop"></td>
					    <td width="%"  class="centertop"></td>
					    <td class="righttop"></td>
				    </tr>
					<tr>
						<td height="26" class="leftcenter">&nbsp;</td>
					    <td class="center" >
					    		操作说明：<input type="text" name="query.czsm" id="czsm">
						      	操作时间：
						      	<input type="text" class="Wdate" name="query.czsj1" id="czsj1" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>&nbsp;|&nbsp;
						      	<input type="text" class="Wdate" name="query.czsj2" id="czsj2" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
						    	<input type="button"  class="but" id="usersx" value="查  询" onclick="">
						    	<input type="button"  class="but" id="userqk" value="清  空" onclick="remove()">
					     </td>
					   	<td class="rightcenter"></td>
					</tr>	
				   <tr>
					    <td class="leftbot"></td>
					    <td class="centerbot"></td>
					    <td class="rightbot"></td>
				   </tr>
			</table>
				
			<div id="mygrid_container" style="width:100%;" ></div>
	</s:form> 
	

</body>
<script type="text/javascript">



function remove(){
$("czsm").value ="";
$("czsj1").value ="";
$("czsj2").value ="";
}


	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ 
		   		    {name : 'loginname'},
		   		    {name : 'cnname'},
		   		    {name : 'menuid_mc'}, 
		   		    {name : 'optionid'},
		   		    {name : 'czsm'}, 
		   		    {name : 'czinfo'}, 
		   		    {name : 'intime'}, 
		   		   ]
	};

	var colsOption = [{
		id : 'loginname',
		header : "登入名",
		width : getSize().w * 0.1,
		align : "left"
	}, {
		id : 'cnname',
		header : "用户名",
		width : getSize().w * 0.1,
		align : "left"
	}, {
		id : 'menuid_mc',
		header : "操作模块",
		width : getSize().w * 0.1,
		align : "left"
	}, {
		id : 'optionid',
		header : "操作类型",
		width : getSize().w * 0.1,
		align : "left"
	},{
		id : 'czsm',
		header : "操作说明",
		width : getSize().w * 0.15,
		align : "left"
	}, {
		id : 'czinfo',
		header : "操作内容",
		align : "left",
		width : getSize().w * 0.5
	}, {
		id : 'intime',
		header : "操作日期",
		align : "left",
		width : getSize().w * 0.1,
		filterable:false
	}];

	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 70) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload | export xls print | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : true,
		dataset : dsOption,
		columns : colsOption,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/xtgl!getAllLog.do',
		exportURL : APP_PATH + '/xtgl!getExportAllLog.do',
		exportFileName : "列表",
		autoLoad : true,
		showIndexColumn : true,
		parameters:{}
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	Event.observe("usersx", "click", function() {
		mygrid.query({"czsm":$("czsm").value});
		mygrid.query({"czsj1":$("czsj1").value});
		mygrid.query({"czsj2":$("czsj2").value});
		mygrid.parameters = {"czsm":$("czsm").value,
		"czsj1":$("czsj1").value,"czsj2":$("czsj2").value
		};
	}, true);
</script>

	
</html>


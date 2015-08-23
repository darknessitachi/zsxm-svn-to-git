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
	</head>
	
	<body style="margin: 0px; margin: 2px;">
		<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
					<tr>
					    <td class="lefttop"></td>
					    <td width="%"  class="centertop"></td>
					    <td class="righttop"></td>
				    </tr>
					<tr>
						<td height="26" class="leftcenter">&nbsp;</td>
					    <td class="center" >
					    	字段
					    	<select name="where" style="width:100px">
					    		<option value="rcname">姓名</option>
					    		<option value="sex_mc">性别</option>
					    		<option value="nation_mc">国籍</option>
					    		<option value="rclb_mc">人才类别</option>
					    		<option value="zgbm_mc">主管部门</option>
					    		<option value="dwdq_mc">单位所在地</option>
					    		<option value="xl_mc">学历</option>
					    		<option value="xw_mc">学位</option>
					    	</select>
					    	=
					    	<s:textfield name="name" cssStyle="width:100px"></s:textfield>
					    	<input type="button" id="queryBtn" class="but" value="查   询" id="b_sendmessage">
					    	<input type="button" id="queryBtn" class="but" value="预   览" id="b_sendmessage" onclick="doquery()">
					    	<input type="button" class="but" value="高级查询" id="" onclick="dofilter();">
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
	</body>
	<script type="text/javascript">
		
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'RCID'},
					{name : 'RCNAME'}, 
		   		    {name : 'SEX_MC'}, 
		   		    {name : 'NATION_MC'},
		   		    {name : 'JG_MC'},  
		   		    {name : 'ZJLB_MC'}, 
		   		    {name : 'ZJNO'}, 
		   		    {name : 'RCLB_MC'}, 
					{name : 'XL_MC'}, 
					{name : 'XW_MC'}, 
					{name : 'ZC_MC'}, 
					{name : 'ZW'}, 
					{name : 'WORKUNIT'}, 
					{name : 'ZGBM_MC'}, 
					{name : 'DWDQ_MC'}, 
					{name : 'XXZY_MC'}, 
					{name : 'CSZY_MC'}
					/*,
					{name : 'url' }
					*/
					]
	};

	var colsOption = [ 
	 {
		id : 'RCNAME',
		header : "姓名",
		headAlign:"center",
		width : 80,
		align : "left"
	}, {
		id : 'SEX_MC',
		header : "性别",
		width : 40,
		align : "center"
		
	}, {
		id : 'NATION_MC',
		header : "国籍",
		headAlign:"center",
		width : 100,
		align : "left"
	}, 
	 {
		id : 'JG_MC',
		header : "籍贯",
		headAlign:"center",
		width : 100,
		align : "left"
	},{
		id : 'ZJLB_MC',
		header : "证件类别",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'ZJNO',
		header : "证件号码",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'RCLB_MC',
		header : "人才类别",
		headAlign:"center",
		align : "left"
	}, {
		id : 'XL_MC',
		header : "学历",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'XW_MC',
		header : "学位",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZC_MC',
		header : "职称",
		width : 100,
		headAlign:"center",
		align : "left"

	}, {
		id : 'ZW',
		header : "职务",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'WORKUNIT',
		header : "工作单位",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'ZGBM_MC',
		header : "主管部门",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'DWDQ_MC',
		header : "单位所在地",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'XXZY_MC',
		header : "所学专业",
		headAlign:"center",
		align : "left"
	}, {
		id : 'CSZY_MC',
		header : "从事专业",
		headAlign:"center",
		align : "left"
	}
	/**
	, {
		id : 'URL',
		header : "详细",
		align : "center", 
		renderer : function(v){
			return '<a href="'+ APP_PATH +'/#" >详情</a>';
		}
	}
	*/
	 ];

	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 60) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload | export xls | print | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : false,
		dataset : dsOption,
		columns : colsOption,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/query!getList.do',
		exportURL : APP_PATH + '/query!exportList.do',
		exportFileName : "列表",
		autoLoad : true,
		showIndexColumn : true,
		parameters:{}
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	Event.observe("queryBtn", "click", function() {
		mygrid.query({"name":$F("name"),"where":$F("where")});
	}, true);
	
	function dofilter(){
		mygrid.showDialog("filter");
	}
	
	function doquery(){
		window.open("rcdaxxwh!preView.do?rcid="+mygrid.getSelectedRecord().RCID+"&opttype=3");
	}
	
</script>
</html>


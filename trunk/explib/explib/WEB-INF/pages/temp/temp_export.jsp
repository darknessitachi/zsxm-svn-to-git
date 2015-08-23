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
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
				
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
.selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
	.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
				
</style>		
	</head>
	
	<body style="margin: 0px; margin: 2px;">
	<s:form name="spForm" action="" method="post">
		<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
					<tr>
					    <td class="lefttop"></td>
					    <td width="%"  class="centertop"></td>
					    <td class="righttop"></td>
				    </tr>
					<tr>
						<td height="26" class="leftcenter">&nbsp;</td>
					    <td class="center" >
					    	模板名称
					    	<span id="xmdis">
					    		<s:textfield name="name" cssStyle="width:100px"></s:textfield>
					    	</span>
					    	<input type="button" id="queryBtn" class="but" value="查   询" id="b_sendmessage">
					    	<input type="button" id="inBtn" class="but" value="新增模板" id="b_sendmessage1">
					    	<input type="button" id="reBtn" class="but" value="修改模板" id="b_sendmessage2">
					    	<input type="button" id="deBtn" class="but" value="删除模板" id="b_sendmessage3">
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
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'TEMPID'},
					{name : 'TEMPMC'}, 
		   		    {name : 'TEMPSM'}
					]
	};

	var colsOption = [ 
	{
		id : 'TEMPID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	}, {
		id : 'TEMPMC',
		header : "模板名称",
		width : 300,
		headAlign:"center",
		align : "left"
		
	}, {
		id : 'TEMPSM',
		header : "模板说明",
		headAlign:"center",
		width : 300,
		align : "left"
	}, {
		id : 'INTIME',
		header : "定义时间",
		headAlign:"center",
		width : 200,
		align : "left"
	}
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
		loadURL : APP_PATH + '/temp!getExpList.do',
		exportURL : APP_PATH + '/temp!exportExpList.do',
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
		mygrid.query({
		"name":$("name").value
		
		});
	}, true);
	
	function queryValue(){
		mygrid.query({
		"name":$("name").value
		});
	}
	
		
	function dofilter(){
		mygrid.showDialog("filter");
	}
	
</script>
</html>


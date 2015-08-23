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

	<body style="margin: 0px; overflow: hidden; margin: 2px;">
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
								    	字段
								    	<select name="where" style="width:100px">
								    		<option value="sbt">日程标题</option>
								    		<!-- <option value="stime">时间范围</option> -->
								    	</select>
								    	=
								    	<s:textfield name="name" cssStyle="width:100px"></s:textfield>
								    	<input type="button" class="but" value="查   询" id="queryBtn" onclick="">
								    	<input type="button" class="but" value="修   改" id="b_sendmessage" onclick="reprow()">
								    	<input type="button" class="but" value="补   录" id="b_sendmessage" onclick="blrow()">
								    	<input type="button" class="but" value="删   除" id="b_sendmessage" onclick="deldata()">
								    	<input type="button" class="but" value="高级查询" id="b_sendmessage" onclick="dofilter();">
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
	<script>
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'SID'},
					{name : 'SBT'},
		   		    {name : 'STRTIME'}, 
		   		    
		   		    {name : 'TSBZ'}, 
		   		    {name : 'INTIME'}
		   		]
	};

	var colsOption = [ 
	{
		id : 'SID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	}, {
		id : 'SBT',
		header : "日程标题",
		headAlign:"center",
		width : 200,
		align : "left",
		renderer:function (v,r){return '<a href="javascript:;" title="点击查看详细信息" onclick="querymx('+r.SID+')">'+v+'</a>';}
	}, {
		id : 'STRTIME',
		header : "开始时间",
		headAlign:"center",
		width : 150,
		align : "left"
	}, 
	 {
		id : 'TSBZ',
		header : "是否提醒",
		headAlign:"center",
		width : 100,
		align : "left",
		renderer:function (v){return v==1?"提醒":"&nbsp";}
	},{
		id : 'INTIME',
		header : "安排时间",
		headAlign:"center",
		width : 150,
		align : "left"
	}
	 ];

	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 70) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		selectRowByCheck : true, 
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload | export xls | print | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : true,
		dataset : dsOption,
		columns : colsOption,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/schedule!getList.do',
		exportURL : APP_PATH + '/schedule!exportList.do',
		exportFileName : "个人日程信息",
		autoLoad : true
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
	
	function querymx(obj){
		openWin("schedule!preView.do?sid="+obj,"600","350");
	}
		
	function reprow(){
		var len = mygrid.getSelectedRecords().length;
		if(len > 1){
			alert("修改只能选择一条记录!");
			return false;
		}
		if(len < 1){
			alert('请选择需要修改的记录!');
			return false;
		}
		if(len==1){
			 openWin("schedule!preScheduleU.do?sid="+mygrid.getSelectedRecord().SID+"&opttype=3","600","350");
		}
	}
	function blrow(){
		var len = mygrid.getSelectedRecords().length;
		if(len > 1){
			alert("补录只能选择一条记录!");
			return false;
		}
		if(len < 1){
			alert('请选择需要补录的记录!');
			return false;
		}
		if(len==1){
			 openWin("schedule!preScheduleB.do?sid="+mygrid.getSelectedRecord().SID+"&opttype=3","600","350");
		}
	}
	function refresh(){
		mygrid.query({"name":$F("name"),"where":$F("where")});
	}
	
	function deldata(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要删除的数据！");
			return false;
		}
		var r = new Array();
		for(var i=0;i<len;i++){
			r.push(mygrid.getSelectedRecords()[i].SID);
		}
		if(window.confirm("请确定需要删除《"+len+"》条记录!")){
			var ajax = new AppAjax("schedule!doScheduleD.do?sid="+r,save_Back).submit();
		}
	}
	

	function save_Back(data,type){
		if(data.message.code >0){
			alert(data.message.text);
			refresh();
		}else{
			alert(data.message.text);
		}
	}

	</script>
</html>

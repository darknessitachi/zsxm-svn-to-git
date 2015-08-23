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
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>			
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
		<style>
.selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }			
</style>	
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
								    	<select name="where" style="width:100px" onchange="changefield(this.value)">
								    		<option value="htbh">合同编号</option>
								    		<option value="zfdz">租房地址</option>
								    		<option value="fzmj">租房面积</option>
								    		<option value="dwid_mc">企业名称</option>
								    	</select>
								    	<span id="xmdis">
								    		<s:textfield name="name" cssStyle="width:100px"></s:textfield>
								    	</span>
								    	
								    	<input type="button" class="but" value="查   询" id="queryBtn" onclick="">
								    	
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
		fields :  [ {name : 'HTID'},
					{name : 'HTLX_MC'},
		   		    {name : 'HTBH'}, 
		   		    {name : 'HTFS'}, 
		   		    {name : 'DWID_MC'}, 
		   		    {name : 'HTSTRDATE'},
		   		    {name : 'ZFDZ'},
		   		    {name : 'FZMJ'},  
		   		    {name : 'DWZJ'}, 
		   		    {name : 'QMJNFZ'}, 
		   		    {name : 'YHZC'}, 
					{name : 'YHZCFZ'},
					{name : 'XSZCFZ'}
				]
	};

	var colsOption = [ 
	{
		id : 'HTID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	}, {
		id : 'HTLX_MC',
		header : "合同类型",
		headAlign:"center",
		width : 120,
		align : "center"
		//renderer:function (v,r){return '<a href="javascript:;" title="点击查看详细信息" onclick="querymx('+r.DWID+')">'+v+'</a>';}
	}, {
		id : 'HTBH',
		header : "合同编号",
		headAlign:"center",
		width : 120,
		align : "left"
		//renderer:function (v,r){return '<a href="javascript:;" title="点击查看详细信息" onclick="querymx('+r.DWID+')">'+v+'</a>';}
		
	}, {
		id : 'HTFS',
		header : "合同份数",
		headAlign:"center",
		width : 80,
		align : "center"
	}, 
	 {
		id : 'DWID_MC',
		header : "企业名称",
		headAlign:"center",
		width : 150,
		align : "left"
	},{
		id : 'HTSTRDATE',
		header : "合同起始时间",
		headAlign:"center",
		width : 150,
		align : "left"
	},{
		id : 'ZFDZ',
		header : "租房地址",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'FZMJ',
		header : "房租面积",
		headAlign:"center",
		width : 70,
		align : "right"
	}, {
		id : 'DWZJ',
		header : "单位租金",
		headAlign:"center",
		width : 100,
		align : "right"
	}, {
		id : 'QMJNFZ',
		header : "全面积年房租",
		width : 70,
		headAlign:"center",
		align : "right"
	}, {
		id : 'YHZC',
		header : "优惠政策",
		width : 100,
		headAlign:"center",
		align : "right"
	},{
		id : 'YHZCFZ',
		header : "优惠政策后房租",
		width : 70,
		headAlign:"center",
		align : "right"
	}, {
		id : 'XSZCFZ',
		header : "享受政策的房租",
		width : 100,
		headAlign:"center",
		align : "right"
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
		loadURL : APP_PATH + '/fzhtwh!getList.do',
		exportURL : APP_PATH + '/fzhtwh!exportList.do',
		exportFileName : "合同信息",
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
		 var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		 openWin("fzht!viewFzht.do?htid="+obj+"&opttype=3",w,h);
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
			 var h = document.body.clientHeight-50;
			 var w = '';
			 if(getSize().w > 965){
				w = 965;
			 }else{
				w = getSize().w;
			 }
			
			 openWin("fzht!preFzhtU.do?htid="+mygrid.getSelectedRecord().HTID+"&opttype=3",w,h);
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
			r.push(mygrid.getSelectedRecords()[i].HTID);
		}
		if(window.confirm("请确定需要删除《"+len+"》条记录!")){
			var ajax = new AppAjax("fzhtwh!doDeletefzht.do?htid="+r,save_Back).submit();
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
	
	function doExport(){
		window.location.href = "fzhtwh!doExportExcel.do";
		//new AppAjax("zsdwwh!doExportExcel.do",function(data){}).submit();
	}

	</script>
</html>

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
	<body style="overflow:hidden;margin:2px;">
		<div id="tabPanel" style="float: left;"></div> 
		<div   id="showTable"  >
				<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
						<tr>
							  <td class="lefttop"></td>
							  <td width="%"  class="centertop"></td>
							  <td class="righttop"></td>
					    </tr>
						<tr>
								<td height="26" class="leftcenter">&nbsp;</td>
							    <td class="center" >
							    	<INPUT id="queryfs" class="selectBut2"  title="专家筛选" value="专家筛选" type=button onclick="O_D('queryfs','flwhsel','bottom');loadSelFlTree();"> 
							    	<s:hidden name="selfldm" id="selfldm"></s:hidden>&nbsp
									<input type="button" class="but" value="选择专家" id="queryBtn" onclick="selrcxx()">
							    	<input type="button" class="but" value="删除专家" id="b_sendmessage" onclick="delrcxx()">
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
		</div>
	<div id='flwhsel' class="fsg_nr" style="display:none;width:300;height:250;">	
	<div loading='0' id="treesel" style="width:300;height:220;overflow:auto;">
		<div id="flwhselload"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
	</div>
	<div class="footer" style="background:#f5f5f5" style="width:299;height:30;">
		<input class="button3" type="button" value="确  定" onclick="queryrcxx();"/>
		&nbsp&nbsp&nbsp&nbsp
	</div>
	</div>
	</body>
<script>
	var treekey = "";
	var diag = null;
	Event.observe(window, "load", function() {
		setpos();
		initTab();
	}, true);
	
	function setpos()
	{
		$("tabPanel").style.height=(getSize().h-20)+"px" ;
		$("tabPanel").style.width = 260+"px";
		$("conTable").style.width=getSize().w-270+"px";
		$("mygrid_container").style.width=getSize().w-270+"px";
		$("mygrid_container").style.height=(getSize().h - 20)+"px" ;
	}
	
   var tabbar = null;
   function initTab(){
	   AppAjaxUtils.useLoadingMessage(false);
       tabbar=new dhtmlXTabBar("tabPanel","top");
   	   tabbar.setImagePath("js/dhtmlxTab/imgs/");
   	   tabbar.setStyle("modern");
   	   tabbar.loadXML("expflwh!getTabpanel.do?tkey=1");
    }

	
	var seltree;
	function loadSelFlTree(){
		if($("treesel").loading != 1){
			seltree=new dhtmlXTreeObject("treesel","100%","100%",0);
			seltree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			//seltree.setOnClickHandler(function(id){setVByTree(id);});
			seltree.enableSmartXMLParsing(true);
			seltree.setDataMode("json");
			//seltree.enableCheckBoxes(true);
			seltree.enableCheckBoxes(1);
			//seltree.enableThreeStateCheckboxes(true);
			seltree.loadJSON("expflwh!getRcflwhTree.do?flbz=1",function(){$('flwhselload').style.display="none";});
			$('treesel').loading = 1;		
		}
	}
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'RCID'}, 
		   		    {name : 'RCNAME'}, 
		   		    {name : 'SEX_MC'}, 
		   		    {name : 'NATION_MC'}, 
		   		    {name : 'ZJLB_MC'}, 
		   		    {name : 'ZJNO'}, 
		   		    {name : 'RCLB_MC'}, 
					{name : 'XL_MC'}, 
					{name : 'XW_MC'}, 
					{name : 'ZC_MC'}, 
					{name : 'ZW'}, 
					{name : 'XXZY_MC'}, 
					{name : 'CSZY_MC'}
					/*,
					{name : 'url' }
					*/
					]
	};

	var colsOption = [ {
		id : 'RCID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	}, {
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
		id : 'SZDQ_MC',
		header : "所在地区",
		width : 80,
		align : "center"
		
	}, {
		id : 'PQCS_',
		header : "聘请次数",
		width : 60,
		align : "center"
		
	}, {
		id : 'ZTPJ_',
		header : "总体评价",
		width : 60,
		align : "center"
		
	}, {
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
		header : "专家类别",
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
		id : 'XZZW',
		header : "职务",
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
	
	 ];

	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 65) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		selectRowByCheck : true, 
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
		loadURL : APP_PATH + '/expflwh!getRcxxSelected.do',
		exportURL : APP_PATH + '/expflwh!exportList.do',
		exportFileName : "列表",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);
	
	
	function delrcxx(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要删除的数据！");
			return false;
		}
		var r = new Array();
		for(var i=0;i<len;i++){
			r.push(mygrid.getSelectedRecords()[i].RCID);
		}
		if(window.confirm("请确定需要删除《"+len+"》条记录!")){
			var ajax = new AppAjax("expflwh!delSelectedflmx.do?rcids="+r,save_Back_mx).submit();
		}
	}
	
		
	function save_Back(data){
		if(data.message.code >0){
			alert("删除成功！");
			refreshfl();
		}else{
			alert(data.message.text);
		}
	}
	
	function save_Back_mx(data){
		if(data.message.code >0){
			alert("删除成功！");
			refreshflmx(treekey);
		}else{
			alert(data.message.text);
		}
	}
	
	function selrcxx(){
		if(treekey != "" && treekey!="root"){
			diag = new Dialog("flwhwindows");
			diag.Title = "选择";
			diag.Width = 890;
			diag.Height = 550;
			diag.ShowMessageRow=false;
			diag.URL = "expflwh!getRcxxWaitSelect.do?fldm="+treekey+"&winid=flwhwindows";
			diag.show();
		}else{
			alert("请选择相应的专家分类");
		}
	}
	
	function insert(){
		 tree.insertNewChild($('pcode').value,$('code').value,$('codename').value); 
	}
	
	function refreshfl(){
		treekey = "";
		loadTree();
	}
	
	function refreshflmx(tkey){
		treekey = tkey;
		mygrid.query({"fldm":treekey,"selfldm":$("selfldm").value});
		mygrid.parameters = {"fldm":treekey,"selfldm":$("selfldm").value};	
	}
	
	function refresh(){
		mygrid.query({"fldm":treekey,"selfldm":$("selfldm").value});
		mygrid.parameters = {"fldm":treekey,"selfldm":$("selfldm").value};	
	}
	
	function queryrcxx(){
		var bhs = seltree.getAllChecked();
		$("selfldm").value = bhs;
		$('flwhsel').style.display="none";
		mygrid.query({"fldm":treekey,"selfldm":$("selfldm").value});
	}
	
	function setTree(id){
		treekey = id;
		refreshflmx();
	}
</script>
</html>

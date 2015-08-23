<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:100%;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:30px;}
		    .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		    .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
		</style>
		<script src="Framework/Main.js" type="text/javascript"></script>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
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
		<s:hidden name="flbz" id="flbz" value="1"></s:hidden>
		<div class="tree" id="treeDiv">
			<div class="ddbox" id="ddbox" style="width:100%;margin-bottom:3px;">
				<div>
					<input type="button"  id="inserxj" value="新增下级" onclick="addfl(0);">
					<input type="button"  id="inserbj" value="新增本级" onclick="addfl(1);">	
					<input type="button"  value="修改" onclick="repfl();">
					<input type="button"  value="删除" onclick="delfl();">
				</div>
			</div>
			<div id="disTree">
			</div>
	    </div>
	     
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
		
	</body>
<script>
	var treekey = "";
	var diag = null;
	Event.observe(window, "load", function() {
		setpos();
		loadTree();
	}, true);
	
	function setpos()
	{	
		$("treeDiv").style.height=(getSize().h-20)+"px" ;
		$("treeDiv").style.width = 260+"px";
		$("showTable").style.width = getSize().w-270+"px";
	}
	
	/** 用于DhtmlxTree */
	var tree;
	function loadTree(){
		$('disTree').innerHTML = "";
		tree=new dhtmlXTreeObject("disTree","100%","92%",0);
		tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
		tree.setOnClickHandler(function(id){setTree(id);});
		tree.enableSmartXMLParsing(true);
		tree.setDataMode("json");
		tree.enableCheckBoxes(false);
		tree.loadJSON("expflwh!getRcbywhTree.do?flbz="+$('flbz').value,function(){});
		
        //tree.setXMLAutoLoading("getMenuTree.do?dwcwgroup="+dwcwgroup+"&czcwgroup="+czcwgroup);
		//tree.loadXML("getMenuTree.do?dwcwgroup="+dwcwgroup+"&czcwgroup="+czcwgroup,function(){setLoadInfo(false)});
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
		align : "left",
		renderer : function (v,r){ return '<a href="expwh!preView.do?rcid='+r.RCID+'&opttype=3" target=_blank>'+v+'</a>';}
	}, {
		id : 'SEX_MC',
		header : "性别",
		width : 40,
		align : "center"
		
	},  {
		id : 'SZDQ_MC',
		header : "所在地区",
		headAlign:"center",
		width : 150,
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
		loadURL : APP_PATH + '/expflwh!getRcxxSelectedby.do',
		exportURL : APP_PATH + '/expflwh!exportListby.do',
		exportFileName : "列表",
		autoLoad : false
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
			var ajax = new AppAjax("expflwh!delSelectedbymx.do?rcids="+r,save_Back_mx).submit();
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
			refresh();
		}else{
			alert(data.message.text);
		}
	}
	
	function selrcxx(){
		if(treekey != "" && treekey!="root"){
			diag = new Dialog("bywhwindows");
			diag.Title = "选择";
			diag.Width = getSize().w-50;
			diag.Height = getSize().h-40;
			diag.ShowMessageRow=false;
			diag.URL = "expflwh!getRcxxWaitSelectBy.do?fldm="+treekey+"&winid=bywhwindows";
			diag.show();
		}else{
			alert("请选择相应的专家分类");
		}
	}
	
	
	function addfl(type){
		var dm = treekey;
		if(treekey == 'root')
			{dm = '';}
		diag = new Dialog("flwhwindows");
		diag.Title = "新增";
		diag.Width = 400;
		diag.Height = 150;
		diag.ShowMessageRow=true;
		diag.MessageTitle = "新增专家标引";
		diag.Message = "请填写相应的专家标引名称";
		diag.URL = "expflwh!preAddbywh.do?flmap.type="+type+"&flmap.fldm="+dm+"&option=1&flbz=1"+"&winid=flwhwindows";
		diag.show();
	}
	
	
	function repfl(){
		if(treekey != "" && treekey!="root"){
			diag = new Dialog("flwhwindows");
			diag.Title = "修改";
			diag.Width = 400;
			diag.Height = 150;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "修改专家标引";
			diag.Message = "请修改相应的专家标引名称";
			diag.URL = "expflwh!preRepbywh.do?fldm="+treekey+"&option=3"+"&winid=flwhwindows";
			diag.show();
		}else{
			alert("请选择需修改的数据！");
		}
	}
	
	function delfl(){
		if(treekey != "" && treekey!="root"){
			if(window.confirm("您确定要删除当前数据！")){
				var ajax = new AppAjax("expflwh!delbywh.do?fldm="+treekey,save_Back).submit();
			}
		}else{
			alert("请选择需删除的数据！");
		}
	}
	
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
			var ajax = new AppAjax("expflwh!delSelectedbymx.do?rcids="+r+"&fldm="+treekey,save_Back_mx).submit();
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
			refresh();
		}else{
			alert(data.message.text);
		}
	}

	
	function insert(){
		 tree.insertNewChild($('pcode').value,$('code').value,$('codename').value); 
	}
	
	function refreshfl(){
		treekey = "";
		loadTree();
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
		refresh(treekey);
	}
</script>
</html>

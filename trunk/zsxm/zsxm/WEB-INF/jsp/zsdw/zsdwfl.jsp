<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:200px;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px;}
		    .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		    .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
		</style>
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
		<div class="tree" id="treeDiv">
			<div class="ddbox" style="width:100%;margin-bottom:3px;">
				<div>
					<b>单位分类</b>
				</div>
			</div>
			<div id="disTree">
			</div>
	     </div>
		<div   id="showTable"  >
			<div id="mygrid_container" style="width:100%;" ></div>
		</div>

	</body>
<script>
	var treekey = "";
	setpos();
	function setpos()
	{
		$("treeDiv").style.height=(getSize().h - 20)+"px" ;
		$("mygrid_container").style.width=getSize().w-210+"px";
		$("mygrid_container").style.height=(getSize().h - 5)+"px" ;
	}
	

	/** 用于DhtmlxTree */
	var tree;
	loadTree();
	function loadTree(){
		$('disTree').innerHTML = "";
		tree=new dhtmlXTreeObject("disTree","100%","92%",0);
		tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
		tree.setOnClickHandler(function(id){setTree(id);});
		tree.enableSmartXMLParsing(true);
		tree.setDataMode("json");
		tree.enableCheckBoxes(false);
		tree.loadJSON("zsdwby!getDwflTree.do",function(){});
	}
	
	var date;
 	date=new Date();
	var optyy = date.getYear()-1;
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ <s:iterator id="header" value="gheades">
						{name : '<s:property id="header" value="gid"/>'},
					</s:iterator>
					{}
				]
	};

	
	var colsOption = [ 
	<s:iterator id="col" value="gcols">
			{
				id : '<s:property id="col" value="gid"/>',
				header : '<s:property id="col" value="gheader"/>',
				width : '<s:property id="col" value="gwidth"/>',
				headAlign:"center",
				align:'<s:property id="col" value="galign"/>',
				isCheckColumn : <s:property id="col" value="gcheckcolumn"/>,
				filterable	: <s:property id="col" value="gfilterable"/>
				<s:if test="gscript==1">
					,renderer:function (v,r){return '<a href="zsdwview!getZsdwAllView.do?dwid='+r.DWID+'" target=_blank title="点击查看详细信息">'+v+'</a>';}
				</s:if>
			},
		</s:iterator>
		{id:'',header:'',filterable:false}
	 ];
	 colsOption.splice(colsOption.length-1,1);
	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 15) + "px",
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
		loadURL : APP_PATH + '/zsdwby!getZsDwfl.do',
		exportURL : APP_PATH + '/zsdwby!doExportZsDwFlList.do',
		exportFileName : "列表",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);
		
	function querymx(obj){
		 var h = document.body.clientHeight-50;
			 var w = '';
			 if(getSize().w > 965){
				w = 965;
			 }else{
				w = getSize().w;
			 }
		
		 openWin("zsdwview!preZsdwU.do?dwid="+obj+"&opttype=3",w,h);
	}
	
	function refreshflmx(){
		mygrid.query({"dm":treekey,"id":tree.getUserData(treekey,'id')});
		mygrid.parameters = {"dm":treekey,"id":tree.getUserData(treekey,'id')};	
	}
	
	function setTree(id){
		treekey = id;
		if(treekey != 'root'){
			refreshflmx();
		}
	}
</script>
</html>

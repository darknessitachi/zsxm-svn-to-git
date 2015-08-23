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
	</head>
	<body style="overflow:hidden;margin:2px;">
		<s:hidden name="flbz" id="flbz"></s:hidden>
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
		if($('flbz').value == 1){
			$("treeDiv").style.height=(getSize().h-10)+"px" ;
		}else{
			$("treeDiv").style.height=(getSize().h-5)+"px" ;
			$('ddbox').style.display="none";
		}
		
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
		tree.loadJSON("expflwh!getRcflwhTree.do?flbz="+$('flbz').value,function(){});
		
        //tree.setXMLAutoLoading("getMenuTree.do?dwcwgroup="+dwcwgroup+"&czcwgroup="+czcwgroup);
		//tree.loadXML("getMenuTree.do?dwcwgroup="+dwcwgroup+"&czcwgroup="+czcwgroup,function(){setLoadInfo(false)});
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
			seltree.loadJSON("expflwh!getRcflwhTree.do",function(){$('flwhselload').style.display="none";});
			$('treesel').loading = 1;		
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
		diag.MessageTitle = "新增专家分类";
		diag.Message = "请填写相应的专家分类名称";
		diag.URL = "expflwh!preAddflwh.do?flmap.type="+type+"&flmap.fldm="+dm+"&option=1&flbz=1"+"&winid=flwhwindows";
		diag.show();
	}
	
	
	function repfl(){
		if(treekey != "" && treekey!="root"){
			diag = new Dialog("flwhwindows");
			diag.Title = "修改";
			diag.Width = 400;
			diag.Height = 150;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "修改专家分类";
			diag.Message = "请修改相应的专家分类名称";
			diag.URL = "expflwh!preRepflwh.do?fldm="+treekey+"&option=3"+"&winid=flwhwindows";
			diag.show();
		}else{
			alert("请选择需修改的数据！");
		}
	}
	
	function delfl(){
		if(treekey != "" && treekey!="root"){
			if(window.confirm("您确定要删除当前数据！")){
				var ajax = new AppAjax("expflwh!delflwh.do?fldm="+treekey,save_Back).submit();
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
			refreshflmx();
		}else{
			alert(data.message.text);
		}
	}
	
	function selrcxx(){
		if(treekey != "" && treekey!="root"){
			diag = new Dialog("flwhwindows");
			diag.Title = "选择";
			diag.Width = 350;
			diag.Height = 200;
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
	
	function refreshflmx(){
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
		window.parent.refreshflmx(treekey);
	}
</script>
</html>

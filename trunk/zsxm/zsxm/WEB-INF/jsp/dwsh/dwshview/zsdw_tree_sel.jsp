<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:100%;border:1px solid #A7C5E2; }
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
		
	</head>
	<body>
		<s:component template="xtitle" theme="app" value='选择'></s:component>
		<s:hidden name="pname" id="pname"></s:hidden>
		<s:hidden name="lbid" id="lbid"></s:hidden>
		<s:hidden name="calldm" id="calldm"></s:hidden>
		<s:hidden name="callmc" id="callmc"></s:hidden>
		<div class="tree" id="treeDiv">
			<div id="disTree">
			</div>
	     </div>
		<div style="background:#efefef;height:35;text-align:right">
			<input class="button3" type="button" value="确   定" onclick="javascript:doTrue();"/>
			<input class="button3" type="button" value="退   出" onclick="closeWindows();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</body>
<script>
	var treekey = "";
	setpos();
	function setpos()
	{
		$("treeDiv").style.height=(getSize().h - 70)+"px" ;
	}
	

	/** 用于DhtmlxTree */
	var tree;
	loadTree();
	function loadTree(){
		$('disTree').innerHTML = "";
		tree=new dhtmlXTreeObject("disTree","100%","92%",0);
		tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
		//tree.setOnClickHandler(function(id){setTree(id);});
		tree.enableSmartXMLParsing(true);
		tree.setDataMode("json");
		tree.enableCheckBoxes(false);
		tree.loadJSON("zsdw!doLoadTree.do?lbid="+$('lbid').value,function(){});
	}
	function doTrue(){
		var itemid = tree.getSelectedItemId();
		var text = tree.getItemText(itemid);
		if(itemid == 'root'){
			itemid = '';
			text = '';
		}
		var xx = $('pname').value
		var yy= eval('parent.document.'+xx);
		yy.setVByTree($('calldm').value,$('callmc').value,itemid,text);
		closeWin(self.name);
	}
	
	function closeWindows(){closeWin(self.name);}
</script>
</html>

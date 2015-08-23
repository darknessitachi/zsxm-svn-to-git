<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
		<style type="text/css">
			.ddbox{background-color:#e5ecf9;width:150px;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px;}
			.tree { float: left;width:200px;border:2px solid #A7C5E2;}
		</style>
	</head>
	<s:form>
	<body >
		<s:component template="xtitle" theme="app" value='选择收件人'></s:component>
	    <div id="xwincontent">
	   	 <div class="tree" id="analys_test_div">
			<div id="treeBox" style="overflow:auto;"></div>
	      </div>
	   </div>
	   
	   <div class="footer">
        	<input type="button" class="btn" value="确定" onclick="getUser()"/>
        	<input type="button" name="resetBtn" class="btn" value="关  闭" onclick="closeWin(self.name);"/>
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
		loadTree();

		var tree;
		var tree_smpl
		function loadTree(){
			tree=new dhtmlXTreeObject("treeBox","97%",(getSize().h - 40 +"px"),0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			//tree.setOnClickHandler(function(id){goCheckId(id);});
			//tree.attachEvent("onOpenEnd",updateTreeSize);
			tree.enableCheckBoxes(1);
			tree.enableThreeStateCheckboxes(true);
			tree.setXMLAutoLoading("message!getUserTree.do");
			tree.loadXML("message!getUserTree.do");	
			//setLoadInfo(false);	
		}	

	function getUser(){
		 var s ,dms ='',names='';
		 s = tree.getAllCheckedBranches().split(",");
		
		 var len = s.length;
		 for(var i=0;i<len;i++){
		 	if(s[i] != "" && s[i]!="root" && s[i]!="#1#" && s[i]!="#2#"){
		 			dms +=',' + s[i];
		 			names +=',' +tree.getItemText(s[i]);
		 	}
		 }
		dms = dms.substr(1);
		names = names.substr(1);
		parent.mainFrameContainer.document.all.tosend.value=names;
		parent.mainFrameContainer.document.all.userdms.value=dms;
		parent.mainFrameContainer.document.all.textusermcs.value = names;
		closeWin(self.name);
	}
	</script>
</html>
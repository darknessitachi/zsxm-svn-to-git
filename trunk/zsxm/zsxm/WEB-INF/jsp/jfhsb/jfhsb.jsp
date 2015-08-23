<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:220px;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:30px;}
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
					<input type="button" class="button3" id="inserxj" value="新增" onclick="add();">	
					<input type="button" class="button3"  id="inserbj" value="修改" onclick="rep();">
					<input type="button" class="button3"  id="inserbj" value="结束" onclick="end();">	
					<input type="button" class="button3"  id="inserbj" value="删除" onclick="del();">
				</div>
			</div>
			<div id="disTree">
			</div>
	     </div>
		<div   id="showTable"  >
				<div id="mygrid_container" style="width:100%;" >
					<iframe id="mainFrameContainer" name="mainFrame" frameborder=0 scrolling="auto"  width="100%" src=""></iframe>
				</div>
		</div>
	
	</body>
<script>
	var treekey = "";
	setpos();
	function setpos()
	{
		$("treeDiv").style.height=(getSize().h - 20)+"px" ;
		$("mygrid_container").style.width=getSize().w-230+"px";
		$("mygrid_container").style.height=(getSize().h - 5)+"px" ;
		$("mainFrameContainer").style.height=(getSize().h - 10)+"px" ;
	}
	

	/** 用于DhtmlxTree */
	var tree;
	loadTree();
	function loadTree(){
		$('disTree').innerHTML = "";
		tree=new dhtmlXTreeObject("disTree","100%","90%",0);
		tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
		tree.setOnClickHandler(function(id){setTree(id);});
		tree.enableSmartXMLParsing(true);
		tree.setDataMode("json");
		tree.enableCheckBoxes(false);
		tree.loadJSON("jfhsb!getJfhsbTree.do",function(){tree.openAllItems('root');});
	}
	
	var treekey = "";
	function setTree(id){
		treekey = id;
		god("jfhsb!getJfhsbList.do?treekey="+treekey);
	}
	
	function god(o){
		if(o!=null && o!= ''){
			$('mainFrameContainer').src = o;
		} 
	}
	
	function add(){
		openWin("jfhsb!preJfhsb.do?opttype=1","480","280");
	}
	function rep(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		openWin("jfhsb!repJfhsb.do?opttype=2&dm="+treekey,"480","280");
	}
	function del(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		
		if(window.confirm('您确定要删除当前填报！')){
			var ajax = new AppAjax("jfhsb!doDelJfhsb.do?dm="+treekey,function(data){del_Back(data)});
			ajax.setAsyn(false);
			ajax.submit();
		}
	}
	
	function del_Back(data){
		if(data != null ){
			if(data.message.code > 0){
				alert('删除成功！');
				loadTree();
			}else{
				alert(data.message.text);
			}
		}
	}
	
	function end(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		
		if(window.confirm('您确定要结束当前填报！')){
			var ajax = new AppAjax("jfhsb!checkWithEnd.do?dm="+treekey,function(data){end_Back(data)});
			ajax.setAsyn(false);
			ajax.submit();
		}		
	}
		
	function end_Back(data){
		if(data != null ){
			if(data.message.code > 0){
				var ajax = new AppAjax("jfhsb!updateEnd.do?dm="+treekey,function(data){do_end_Back(data)});
					ajax.setAsyn(false);
					ajax.submit();
			}else{
				if(window.confirm(data.message.text)){
					var ajax = new AppAjax("jfhsb!updateEnd.do?dm="+treekey,function(data){do_end_Back(data)});
					ajax.setAsyn(false);
					ajax.submit();
				}
			}
		}
	}
	
	function do_end_Back(data){
		if(data != null ){
			if(data.message.code > 0){
				alert('当前填报已结束！');
				loadTree();
			}else{
				alert(data.message.text);
			}
		}
	}	
	function refresh(){
		document.mainFrame.refresh();
	}
</script>
</html>

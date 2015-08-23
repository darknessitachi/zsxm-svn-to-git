<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
		<script src="Framework/Main.js" type="text/javascript"></script>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
		<style type="text/css">
			.ddbox{background-color:#e5ecf9;width:150px;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px;}
			.tree { float: left;height:400px;width:500px;border:2px solid #A7C5E2;}
		</style>
	</head>
	<s:form  name="emasForm" method="post">

	<body >
		<input type="hidden" id="pcode">
		<input type="hidden" id="code">
		<input type="hidden" id="codename">
		<div style="display:none">
		<input type="button" id="insertTree" onclick="insert()">
		<input type="button" id="updateTree" onclick="update()">
		
	</div>

			 <div class="butbar" id="butbar">
								<div class="left">		 	
								      	
								</div>
								<div  id="messageid" style="margin-left:20px;margin-top:4px;">		 	
								      	
								</div>
			</div>	
		   	 <div class="tree">
				<div id="treeBox" style="width:100%;height:100%;overflow:auto;"></div>
		     </div>
	</body>
	</s:form>
	<script type="text/javascript">
		Event.observe(window, "load", function() {
			${'treeBox'}.style.height = getSize().h-40;
			${'treeBox'}.style.width = getSize().w-15;
			loadData();
			loadTree();
		}, true);
		

		var type  ;
		
		var diag = null;
	    function loadData(){
	       var strmessage  = '<input type="button" class="button"  value="新增本级" onclick="adddict(1,1)"/>';
	       			strmessage +='<input type="button" class="button"  value="新增下级" onclick="adddict(1,0)"/>';
	   				strmessage +='<input type="button" class="button"  value="修 改" onclick="updatedict()"/>';
	   				strmessage +='<input type="button" class="button"  value="删 除" onclick="deletedict()"/>';
	   		${'messageid'}.innerHTML = strmessage;
		}
	 	var tree,dictbh = '';
		function loadTree(){
		   $("treeBox").innerHTML = "";
		   	tree=new dhtmlXTreeObject("treeBox","100%","90%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){setTree(id);});
			tree.enableSmartXMLParsing(true);
			tree.setDataMode("json");
			
			tree.enableCheckBoxes(false);
			tree.enableThreeStateCheckboxes(false);
			tree.loadJSON("expgz!getDict.do",function(){});
		}	
		
		var treekey = '';
		function setTree(dm){
			treekey = dm;
		}
		
		function adddict(operate,type){
			var v = treekey;
			if(v == 'root'){v='';}
			diag = new Dialog("gzwhwindows");
			diag.Title = "新建";
			diag.Width = 350;
			diag.Height = 140;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "新建跟踪内容";
			diag.Message = "新建跟踪内容，细化专家的跟踪信息";
			diag.URL = "expgz!getAddDict.do?winid=gzwhwindows&query.type="+type+"&query.dictbh="+v;
			diag.show();
		}

		function insert(){
			tree.insertNewChild($('pcode').value,$('code').value,$('codename').value); 
		}
		
		function updatedict(){
		
		   //var bhs = tree.getAllChecked();
			if(treekey == '')
			{
				alert("请选中需要修改的跟踪内容！");
				return ;
			}
			
			if( treekey == 'root')
			{
				alert("根节点不能修改！请选中其他跟踪内容！");
				return ;
			}
			diag = new Dialog("gzwhwindows");
			diag.Title = "修改";
			diag.Width = 350;
			diag.Height = 100;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "新建跟踪内容";
			diag.Message = "新建跟踪内容，细化专家的跟踪信息";
			diag.URL = "expgz!getUpdateDict.do?query.dictbh="+treekey+"&winid=gzwhwindows";
			diag.show();
		}
		
		function update(){
			 tree.setItemText(treekey,$('codename').value,'sss'); 
		}
		
		function deletedict(){
			if(treekey == ''){
				alert("请选择需要删除的跟踪内容！");
				return ;
			}
			
			if(confirm("确定要删除跟踪内容吗！"))
  			{
				var ajax = new AppAjax("expgz!deleteDict.do",save_Back);
				ajax.add("query.dictbh",treekey);
			   	ajax.submit();
			}	
		}
		
		function save_Back(data){
			if(data.message.code >0){
				tree.deleteItem(treekey,true);	
				treekey = '';
			}else{
				alert(data.message.text);
			}
		}
		
		function refresh(){
			loadTree();
		}
	</script>
</html>
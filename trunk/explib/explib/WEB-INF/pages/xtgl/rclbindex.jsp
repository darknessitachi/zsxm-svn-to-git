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
			<input type="button" class="button"  id="inserxj" value="新增下级" onclick="adddict(0)"/>
			<input type="button" class="button"  disabled id="inserbj" value="新增本级" onclick="adddict(1)"/>
			<input type="button" class="button"  value="修 改" onclick="updatedict()"/>
			<input type="button" class="button"  value="删 除" onclick="deletedict()"/>
		</div>			
	</div>	
	
		   	 <div class="tree">
				<div id="treeBox" style="overflow:auto;height:400px;"></div>
		     </div>
	</body>
	</s:form>
	<script type="text/javascript">
			${'treeBox'}.style.height = window.screen.availHeight * 0.6;
	 	var tree,lbdm = '';
		function loadTree(){
		   $("treeBox").update();
			tree=new dhtmlXTreeObject("treeBox","97%","100%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){
				lbdm = id;
					if(lbdm == 'root'){
						document.getElementById('inserbj').disabled = true;
						document.getElementById('inserxj').disabled = false;
					}else{
						document.getElementById('inserxj').disabled = false;
						document.getElementById('inserbj').disabled = false;
					}
				
			});
			//tree.attachEvent("onOpenEnd",updateTreeSize);
			tree.enableCheckBoxes(1);
			tree.enableThreeStateCheckboxes(true);
	
			tree.setXMLAutoLoading("xtmanager!getRclbTree.do");
			tree.loadXML("xtmanager!getRclbTree.do");
			
		}	
		loadTree();
		
		
		function adddict(type){
		
			 if(lbdm == 'root')
			    	lbdm = '';
				openWin("xtmanager!getAddRclb.do?query.type="+type+"&query.lbdm="+lbdm,"300","145");
				
		}

		function insert(){
			 tree.insertNewChild($('pcode').value,$('code').value,$('codename').value); 
		}
		
		function updatedict(){
			if(!lbdm)
			{
				alert("请选中需要修改的字典节点！");
				return ;
			}
			if( lbdm == 'root')
			{
				alert("根节点不能修改！请选中其他字典节点！");
				return ;
			}
			
			openWin("xtmanager!getUpdateRclb.do?query.lbdm="+lbdm,"300","95");
		}
		
		function update(){
			 tree.setItemText(lbdm,$('codename').value,'sss'); 
		}
		
		function deletedict(){
			var bhs = tree.getAllChecked();
			if(!bhs){
				alert("请选择需要删除的字典节点！");
				return ;
			}
			
			if(confirm("确定要删除字典信息吗！"))
  			{
				var ajax = new AppAjax("xtmanager!deleteRclb.do",save_Back);
				ajax.add("query.lbdm",bhs);
				ajax.submit();
			}	
		}
		
		function save_Back(data){
			if(data.message.code >0){
				var bhs = tree.getAllChecked();
				var bh = bhs.split(",");
				for(var i = 0; i< bh.length ; i++){
					if(bh[i] != 'root'){
						tree.deleteItem(bh[i],true);
					}
				}	
			}else{
				alert(data.message.text);
			}
		}
	</script>
</html>
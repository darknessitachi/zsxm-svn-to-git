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
								      字典类别：<s:select id="dictid"  cssStyle="width:200px;margin-right:4px;" list="listMap.lbList"  listKey="LBID" listValue="LBNAME" onchange="loadData()"/>
				 					 	
								</div>
								<div  id="messageid" style="margin-left:20px;margin-top:4px;">		 	
								      	
								</div>
			</div>	
		   	 <div class="tree">
				<div id="treeBox" style="overflow:auto;"></div>
		     </div>
	</body>
	</s:form>
	<script type="text/javascript">
		${'treeBox'}.style.height = window.screen.availHeight * 0.6;
		var type  ;
		
	    function loadData(){
	        var array = [];
	        <c:forEach items="${listMap.lbList}" var="stat">
	        	array.push({lbid:'${stat.LBID}',lbname:'${stat.LBNAME}',lbframe:'${stat.LBFRAME}'});
	        </c:forEach>
	   		var index = $('dictid').selectedIndex;
	   		type  = array[index].lbframe;
	   		if(type == '1'){  //树结构
	   			var strmessage  = '<input type="button" class="button" disabled id="inserxj" value="新增下级" onclick="adddict(0,0)"/>';
	   				strmessage +='<input type="button" class="button"  disabled id="inserbj" value="新增本级" onclick="adddict(0,1)"/>';
	   				strmessage +='<input type="button" class="button"  value="修 改" onclick="updatedict()"/>';
	   				strmessage +='<input type="button" class="button"  value="删 除" onclick="deletedict()"/>';
	   		}else{
	   			var strmessage  = '<input type="button" class="button"  value="新 增" onclick="adddict(1,1)"/>';
	   				strmessage +='<input type="button" class="button"  value="修 改" onclick="updatedict()"/>';
	   				strmessage +='<input type="button" class="button"  value="删 除" onclick="deletedict()"/>';
	   		}
	   		${'messageid'}.innerHTML = strmessage;

			 loadTree();
	    }
	 	var tree,dictbh = '';
		function loadTree(){
		   $("treeBox").update();
			tree=new dhtmlXTreeObject("treeBox","97%","100%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){
				dictbh = id;
				if(type == '1'){
					if(dictbh == 'root'){
						document.getElementById('inserbj').disabled = true;
						document.getElementById('inserxj').disabled = false;
					}else{
						document.getElementById('inserxj').disabled = false;
						document.getElementById('inserbj').disabled = false;
					}
				}
			});
			//tree.attachEvent("onOpenEnd",updateTreeSize);
			tree.enableCheckBoxes(1);
			tree.enableThreeStateCheckboxes(true);
	
			tree.setXMLAutoLoading("xtmanager!getDict.do?query.lbid="+$("dictid").value);
			tree.loadXML("xtmanager!getDict.do?query.lbid="+$("dictid").value);
			
			/*
			var len = data.length;
			tree.insertNewChild(0,'root',$('dictid').options[$('dictid').selectedIndex].text); 
			for(var i = 0 ; i < len ; i++){
				var code  = data[i].DICTBH.trim();
				var pcode = 'root';
				var clen  = code.length
			    if( clen > 3 ){
			    	pcode = code.substr(0,clen-3);
			    }
			    tree.insertNewChild(pcode,code,data[i].DICTNAME.trim()); 
			}
			tree.closeAllItems(0);		
			tree.openItem('root');
			*/
		}	
		loadData();
		
		
		function adddict(operate,type){
			if(operate == 1){
				openWin("xtmanager!getAddDict.do?query.lbid="+$("dictid").value+"&query.type="+type,"330","200");
			}else{
			    if(dictbh == 'root')
			    	dictbh = '';
				openWin("xtmanager!getAddDict.do?query.lbid="+$("dictid").value+"&query.type="+type+"&query.dictbh="+dictbh,"330","200");
			}	
		}

		function insert(){
			 tree.insertNewChild($('pcode').value,$('code').value,$('codename').value); 
		}
		
		function updatedict(){
		
		   //var bhs = tree.getAllChecked();

			if(!dictbh)
			{
				alert("请选中需要修改的字典节点！");
				return ;
			}
			if( dictbh == 'root')
			{
				alert("根节点不能修改！请选中其他字典节点！");
				return ;
			}
			
			openWin("xtmanager!getUpdateDict.do?query.type="+type+"&query.dictbh="+dictbh+"&query.lbid="+$("dictid").value,"330","200");
		}
		
		function update(){
			 tree.setItemText(dictbh,$('codename').value,'sss'); 
		}
		
		function deletedict(){
			var bhs = tree.getAllChecked();
			if(!bhs){
				alert("请选择需要删除的字典节点！");
				return ;
			}
			
			if(confirm("确定要删除字典信息吗！"))
  			{
				var ajax = new AppAjax("xtmanager!deleteDict.do",save_Back);
				ajax.add("query.dictbh",bhs);
			    ajax.add("query.lbid",$("dictid").value);
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
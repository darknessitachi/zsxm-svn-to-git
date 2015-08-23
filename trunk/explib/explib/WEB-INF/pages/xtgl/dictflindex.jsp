<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
       
       <script src="Framework/Main.js" type="text/javascript"></script>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
		<style type="text/css">
			.ddbox{background-color:#e5ecf9;width:150px;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px;}
			.tree { float: left;height:400px;width:500px;border:2px solid #A7C5E2;}
		</style>
	</head>
	<body>
	<s:form  name="emasForm" method="post">

	
		<input type="hidden" id="pcode">
		<input type="hidden" id="code">
		<input type="hidden" id="codename">
		<div style="display:none">
		<input type="button" id="insertTree" onclick="insert()">
		<input type="button" id="updateTree" onclick="update()">
		
	</div>
		
			 <div class="butbar" id="butbar">
								<div class="left">		
									 专家分类：<s:select id="fldm" name="fldm" cssStyle="width:200px;margin-right:4px;" list="listMap.flList"  listKey="fldm" listValue="flmc" onchange="getDictList()"/>
								      字典类别：
								      <span id="dicthtml">
								      
								      </span>
								      	
								</div>
								<div  id="messageid" style="margin-left:20px;margin-top:4px;float:left;">		 	
								      	
								</div>
								<div style="margin-left:5px;margin-top:4px;float:left;">
									<input type="button" class="button"  value="专家分类配置" onclick="dlbpz();"/>
								</div>
								
			</div>	
		   	 <div class="tree">
				<div id="treeBox" style="overflow:auto;"></div>
		     </div>
	
	</s:form>
	</body>
	<script type="text/javascript">
		${'treeBox'}.style.height = getSize().h-50;
		${'treeBox'}.style.width = getSize().w-10;
		var type  ;
		var diag = null;
		Event.observe(window, "load", function() {
			getDictList();
			
		}, true);
		
		
		var lbframe = 1;
		function getDictList(){
			var a = new AppAjax('xtgl!getLbidByFldm.do?fldm='+$('fldm').value,function(data){
				if(data != null ){
					var s = new StringBuffer('<select id="dictid" name="dictid" onchange="loadData();">');
					var d = data.data;
					for(var i=0;i<d.length;i++){
						
						s.append('<option value="'+d[i].LBID+'">'+d[i].LBNAME+'</option>');
					}
					s.append('</select>');
					$('dicthtml').innerHTML = s.toString();
					loadData();
				}
				
			}).submit();
		}
		
	    function loadData(){
	    	if($('dictid').value==30){
	    		var strmessage  = '<input type="button" class="button"  value="保  存" onclick="saveNlfw()"/>';
	    		${'messageid'}.innerHTML = strmessage;
	    		loadNlfw();
	    	}else{
	    		if($('dictid').value != ''){
	    			if(lbframe == '1'){  //树结构
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
	    		}else{
	    			${'messageid'}.innerHTML = '';
	    			$("treeBox").innerHTML='';
	    		}
		        	    	
	    	}

	    }
	 	var tree,dictbh = '';
		function loadTree(){
		   $("treeBox").innerHTML='';
			tree=new dhtmlXTreeObject("treeBox","90%","100%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){
				dictbh = id;
				if(lbframe == '1'){
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
	
			tree.setXMLAutoLoading("xtgl!getDictfl.do?query.lbid="+$("dictid").value+"&fldm="+$('fldm').value);
			tree.loadXML("xtgl!getDictfl.do?query.lbid="+$("dictid").value+"&fldm="+$('fldm').value);

		}	
		
		
		
		function adddict(operate,type){
			if(operate == 1){
				diag = new Dialog("dictindexwindows");
				diag.Title = '字典维护';
				diag.ShowMessageRow=true;
				diag.MessageTitle = "字典维护";
				diag.Message = "数据字典信息维护";
				diag.Width = 300;
				diag.Height = 120;
				diag.URL = "xtgl!getAddDict.do?query.lbid="+$("dictid").value+"&query.type="+type+"&opttype=3"+"&winid=dictindexwindows";
				diag.show();
				//openWin("xtgl!getAddDict.do?query.lbid="+$("dictid").value+"&query.type="+type,"300","125");
			}else{
			    if(dictbh == 'root')
			    	dictbh = '';
			    diag = new Dialog("dictindexwindows");
				diag.Title = '字典维护';
				diag.ShowMessageRow=true;
				diag.MessageTitle = "字典维护";
				diag.Message = "数据字典信息维护";
				diag.Width = 300;
				diag.Height = 120;
				diag.URL = "xtgl!getAddDict.do?query.lbid="+$("dictid").value+"&query.type="+type+"&query.dictbh="+dictbh+"&opttype=3"+"&winid=dictindexwindows";
				diag.show();	
				//openWin("xtgl!getAddDict.do?query.lbid="+$("dictid").value+"&query.type="+type+"&query.dictbh="+dictbh,"300","145");
			}	
		}

		
		function adddict(operate,type){
			if(operate == 1){
				diag = new Dialog("dictindexwindows");
				diag.Title = '字典维护';
				diag.ShowMessageRow=true;
				diag.MessageTitle = "字典维护";
				diag.Message = "数据字典信息维护";
				diag.Width = 300;
				diag.Height = 120;
				diag.URL = "xtgl!getAddDictfl.do?query.fldm="+$('fldm').value+"&query.lbid="+$("dictid").value+"&query.type="+type+"&opttype=3"+"&winid=dictindexwindows";
				diag.show();
				//openWin("xtgl!getAddDict.do?query.lbid="+$("dictid").value+"&query.type="+type,"300","125");
			}else{
			    if(dictbh == 'root')
			    	dictbh = '';
			    diag = new Dialog("dictindexwindows");
				diag.Title = '字典维护';
				diag.ShowMessageRow=true;
				diag.MessageTitle = "字典维护";
				diag.Message = "数据字典信息维护";
				diag.Width = 300;
				diag.Height = 120;
				diag.URL = "xtgl!getAddDictfl.do?query.fldm="+$('fldm').value+"&query.lbid="+$("dictid").value+"&query.type="+type+"&query.dictbh="+dictbh+"&opttype=3"+"&winid=dictindexwindows";
				diag.show();	
				//openWin("xtgl!getAddDict.do?query.lbid="+$("dictid").value+"&query.type="+type+"&query.dictbh="+dictbh,"300","145");
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
			diag = new Dialog("dictindexwindows");
			diag.Title = '字典维护';
			diag.ShowMessageRow=true;
			diag.MessageTitle = "字典维护";
			diag.Message = "数据字典信息维护";
			diag.Width = 300;
			diag.Height = 100;
			diag.URL = "xtgl!getUpdateDictfl.do?query.fldm="+$('fldm').value+"&query.type="+type+"&query.dictbh="+dictbh+"&query.lbid="+$("dictid").value+"&opttype=3"+"&winid=dictindexwindows";
			diag.show();	
			//openWin("xtgl!getUpdateDict.do?query.type="+type+"&query.dictbh="+dictbh+"&query.lbid="+$("dictid").value,"300","95");
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
				var ajax = new AppAjax("xtgl!deleteDictfl.do",save_Back);
				ajax.add("query.fldm",$('fldm').value);
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
		
		
		function loadNlfw(){
			var ajax = new AppAjax("xtgl!getNlfwList.do",nlfw_Back);
				ajax.submit();
		}
		
		function nlfw_Back(data){
			var str = new StringBuffer("<table id='fxtztable' border=1>");
			if(data != null && data.info != null){
				var len = data.info.length;
				for(var i=0;i<len;i++){
					str.append("<tr>");
					
					if(data.info[i].BZ==0){
						str.append("<td>下限<input type='text' style='width:60px' name='nl1' value='"+data.info[i].NL1+"'>");
						str.append("</td>");
						str.append("<td>上限<input type='text' style='width:60px' name='nl2' value='"+data.info[i].NL2+"'>");
						str.append("</td>");
						str.append('<td>');
						str.append('<select name="bz">');
						str.append('<option value="0" selected>区间</option>');
						str.append('<option value="1">以下</option>');
						str.append('<option value="2">以上</option>');
						str.append('</select>');
						
						str.append("</td>");
					}else if(data.info[i].BZ==1){
						str.append("<td>下限<input type='text' style='width:60px;' name='nl1' value='"+data.info[i].NL1+"'>");
						str.append("</td>");
						str.append("<td>上限<input type='text' style='width:60px' name='nl2' value='"+data.info[i].NL2+"'>");
						str.append("</td>");
						str.append('<td>');
						str.append('<select name="bz">');
						str.append('<option value="0" >区间</option>');
						str.append('<option value="1" selected>以下</option>');
						str.append('<option value="2">以上</option>');
						str.append('</select>');
						
						str.append("</td>");
					}else if(data.info[i].BZ==2){
						str.append("<td>下限<input type='text' style='width:60px' name='nl1' value='"+data.info[i].NL1+"'>");
						str.append("</td>");
						str.append("<td>上限<input type='text' style='width:60px;'   name='nl2' value='"+data.info[i].NL2+"'>");
						str.append("</td>");
						str.append('<td>');
						str.append('<select name="bz">');
						str.append('<option value="0" >区间</option>');
						str.append('<option value="1" >以下</option>');
						str.append('<option value="2" selected>以上</option>');
						str.append('</select>');
						str.append("</td>");
					}
					
					str.append("</tr>");
				}
			}
			str.append("<tr>");
			str.append("<td colspan=3 align=right>");
			str.append("<input type='button' value='增加'  onclick='AddTableRow()'>");
			str.append("<input type='button' value='删除'  onclick='DelTableRow()'>");
			str.append("</td>");
			str.append("</tr>");
			str.append("</table>");
			$('treeBox').innerHTML = str.toString();
		}
		
		function saveNlfw(){
			var ajax = new AppAjax("xtgl!doSaveNlfw.do",saveNlfw_Back);
				ajax.submitForm("emasForm");
		}
		
		function saveNlfw_Back(data){
			if(data.message.code >0){
				alert("保存成功");
			}else{
				alert(data.message.text);
			}
		}
		
		function AddTableRow(){ //读取最后一行的行号，存放在txtTRLastIndex文本框中
			var signFrame = findObj("fxtztable",document);
			var rowID = signFrame.rows.length-1;
			//添加行 onfocus="javascript:this.select();" onblur="RC.isNum(this.id)"
			var newTR = signFrame.insertRow(rowID);
			newTR.id = "txtztr" + signFrame.rows.length;
			
			var newTD1=newTR.insertCell(0);
			newTD1.align="center";
			newTD1.innerHTML = "下限<input type='text' style='width:60px' name='nl1' value=''>";
			
			var newTD2=newTR.insertCell(1);
			newTD2.align="center";
			newTD2.innerHTML = "上限<input type='text' style='width:60px' name='nl2' value=''>";
			
			var newTD3=newTR.insertCell(2);
			newTD3.align="center";
			var sestr='<select name="bz">';
						sestr+='<option value="0" selected>区间</option>';
						sestr+='<option value="1" >以下</option>';
						sestr+='<option value="2" >以上</option>';
						sestr+='</select>';
			newTD3.innerHTML = sestr;
			
		}
		
		function DelTableRow(){
			var signFrame = findObj("fxtztable",document);
			var rowID = signFrame.rows.length-2;
			if(rowID > 1){
				signFrame.deleteRow(rowID);
			}else{
				alert("必需要保留一行数据");
			}
		}
		
		function dlbpz(){
			diag = new Dialog("dlbflxwindows");
			diag.Title = '专家分类（专有字典维护）';
			diag.ShowMessageRow=false;
			diag.Width = 350;
			diag.Height = 450;
			diag.URL = "xtgl!preDlbFldm.do?fldm="+$('fldm').value+"&winid=dlbflxwindows";
			diag.show();
		}
		
		function refresh(){
			getDictList();
		}
		
		function findObj(theObj, theDoc)
		{
			var p, i, foundObj;
			if(!theDoc) theDoc = document;
			if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)
			{ 
			   theDoc = parent.frames[theObj.substring(p+1)].document;   
			   theObj = theObj.substring(0,p); 
			} 
			if(!(foundObj = theDoc[theObj]) && theDoc.all) 
			   foundObj = theDoc.all[theObj]; 
			
			for (i=0; !foundObj && i < theDoc.forms.length; i++)    
			   foundObj = theDoc.forms[i][theObj]; 
			
			for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)    
			   foundObj = findObj(theObj,theDoc.layers[i].document); 
			   
			if(!foundObj && document.getElementById) 
			   foundObj = document.getElementById(theObj);   
			
			return foundObj;
		}
	</script>
</html>
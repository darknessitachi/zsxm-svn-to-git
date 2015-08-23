<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>

				<script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		 <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>	
<style>
	.tree {float: left;overflow:auto;border:1px solid #A7C5E2; }
    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px;}
    .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
    .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
    .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
    .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
</style>
	</head>
<s:form name="zForm" action="xtcs.do" method="post">
	<body>
	    <div style="width:270px;float:left">
	    
	    <div class="tableContainer" id="tableContainer" style="width:100%">
			<table id="dwTable" cellspacing="0">
				<thead>
					<tr>
						
						<td width="40px">
							序号
						</td>
						<td>
							系统参数
						</td>
					</tr>
				</thead>
				<tbody id="ubodys">
					
						<tr onclick="clicked(this,1)">
							<td>1</td>
							<td>词典批次设置</td>
						</tr>
						
						<tr onclick="clicked(this,3)">
							<td>2</td>
							<td>GRID列表设置</td>
						</tr>
						<tr onclick="clicked(this,4)">
							<td>3</td>
							<td>数据区间维护</td>
						</tr>
				</tbody>
			</table>
		</div>
		</div>
		
		<div class="tree" id="treeDiv">
			<div class="ddbox" style="width:100%;margin-bottom:3px;">
				<div style="float:left;margin-left:2px" id='butdiv'>
					
				</div>
			</div>
			<div id="disTree">
				
			</div>
	 </div>
		  
	</body>
</s:form>	

<script type="text/javascript">
	$('treeDiv').style.width = getSize().w - 300;
	$('treeDiv').style.height = getSize().h - 20;
	$('tableContainer').style.height = (getSize().h - 20)+"px"; 
	
	
	var tree;
	function loadTree(type){
		$('disTree').innerHTML = "";
		tree=new dhtmlXTreeObject("disTree","100%","90%",0);
		tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
		tree.setOnClickHandler(function(id){changeButton(id);});
		tree.enableSmartXMLParsing(true);
		tree.setDataMode("json");
		tree.enableCheckBoxes(false);
		if(type==1){
			tree.loadJSON("xmzdefine!doLoadXmzflTree.do",function(){tree.openAllItems('root');});
		}else if(type==2){
			tree.loadJSON("zbdefine!doLoadZbflTree.do",function(){tree.openAllItems('root');});
		}
	}
	var treekey = "";
	function changeButton(id){
		treekey = id;
		if(id == 'root'){
			document.getElementById('inserbj').disabled = true;
			document.getElementById('inserxj').disabled = false;
			document.getElementById('butxg').disabled =true;
			document.getElementById('butsc').disabled =true;
			
		}else{
			if(tree.getUserData(id,'bz')==2){
				document.getElementById('inserxj').disabled = true;
				document.getElementById('inserbj').disabled = false;
				document.getElementById('butxg').disabled =false;
				document.getElementById('butsc').disabled =false;
			}else{
				document.getElementById('inserxj').disabled = false;
				document.getElementById('inserbj').disabled = false;
				document.getElementById('butxg').disabled =false;
				document.getElementById('butsc').disabled =false;
			}
		}
	}
	
	
	function addxmnode(type){
		var dm = treekey;
		if(treekey == 'root')
			{dm = '';}
			alert(dm);
		openWin("xtcswh!preXmnodeI.do?action=1&type="+type+"&fldm="+dm+"&option=1","400","250");
    }
	
	function updatexmnode(){
		openWin("xtcswh!preXmnodeU.do?action=2&fldm="+treekey+"&type=0&option=1","400","250");
	}
	
	function deletexmnode(){
		if(treekey != 'root' && treekey != ''){
			if(window.confirm('您确定要删除当前结点')){
				var ajax = new AppAjax("xtcswh!doDeletexmnode.do?fldm="+treekey,delete_Back).submit();
			}
		}else{
			alert('请选择需要删除的结点!');
		}
	}
	
	function addzbnode(type){
    	var dm = treekey;
		if(treekey == 'root')
			{dm = '';}
		openWin("xtcswh!preZbnodeI.do?action=1&type="+type+"&fldm="+dm+"&option=1","400","250");
    }
	
	function updatezbnode(){
		openWin("xtcswh!preZbnodeU.do?action=2&fldm="+treekey+"&type=0&option=1","400","250");
	}
	
	function deletezbnode(){
		if(treekey != 'root' && treekey != ''){
			if(window.confirm('您确定要删除当前结点')){
				var ajax = new AppAjax("xtcswh!doDeletezbnode.do?fldm="+treekey,delete_Back).submit();
			}	
		}else{
			alert('请选择需要删除的结点!');
		}
	}
	
	function delete_Back(data){
		if(data.message.code >0){
			alert('删除成功!');
			refreshTree();
		}else{
			alert(data.message.text);
		}
	}
	
	
	var oldobj = '';
	var opttype = 0;
    function clicked(obj,id){
    	treekey = '';
    	opttype = id;
    	if(oldobj != ''){
    		oldobj.style.background='white';
    	}
    	obj.style.background="#778899";
    	oldobj = obj;
    	if(id==1){
    		var strmessage  = '<input type="button" class="button" id="inserxj" value="确定设置" onclick="doTrue()"/>&nbsp';
	   		var ajax = new AppAjax("xtconfig!getSjqList.do",get_Back).submit();
	   		$('butdiv').innerHTML = strmessage;
    		
    	}else if(id==2){
    		var strmessage  = '<input type="button" class="button" id="inserxj" value="新增下级" onclick="addzbnode(0)"/>&nbsp';
	   				strmessage +='<input type="button" class="button"  disabled id="inserbj" value="新增同级" onclick="addzbnode(1)"/>&nbsp';
	   				strmessage +='<input type="button" class="button" disabled id="butxg" value="修 &nbsp 改" onclick="updatezbnode()"/>&nbsp';
	   				strmessage +='<input type="button" class="button" disabled id="butsc" value="删 &nbsp 除" onclick="deletezbnode()"/>&nbsp';
	   		$('butdiv').innerHTML = strmessage;
    		loadTree(id);
    	}else if(id==3){
    		
    		var strbut =  '表式选择：<select name="type" id="type" onchange="changeGrid(this.value);">';
	    			strbut += '<option value="1">招资方面</option>';
	    			strbut += '<option value="2">项目进展查询</option>';
	    			strbut += '<option value="3">入驻单位</option>';
	    			strbut += '<option value="4">园区服务查询</option>';
	    			strbut += '<option value="5">项目进展查询</option>';
	    			strbut += '<option value="6">园区服务查询</option>';
	    			strbut += '<option value="7">房租合同</option>';
    			strbut += '</select>';
    			strbut += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="保&nbsp&nbsp存" onclick="saveGrid()"/>&nbsp';
    		$('butdiv').innerHTML = strbut;
    		
    		var ajax = new AppAjax("xtconfig!getGridsz.do?type=1",grid_back).submit();
    	}else if(id==4){
    		
    		var strbut =  '类型选择：<select name="type" id="type" onchange="changeQjwh(this.value);">';
	    			strbut += '<option value="1">注册资本（开办经费）</option>';
	    			strbut += '<option value="2">销售收入</option>';
	    			strbut += '<option value="3">销售收入增长率</option>';
	    			strbut += '<option value="4">税收</option>';
	    			strbut += '<option value="5">税收增长率</option>';
	    			strbut += '<option value="6">员工</option>';
	    			strbut += '<option value="7">员工增长率</option>';
    			strbut += '</select>';
    			strbut += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" value="保&nbsp&nbsp存" onclick="saveQjwh()"/>&nbsp';
    		$('butdiv').innerHTML = strbut;
    		
    		var ajax = new AppAjax("xtconfig!getQjwhsz.do?type=1",qjwh_back).submit();
    		
    		
    	}
    }
    
    function get_Back(data){
    	if(data != null && data.data.length > 0){
    		var d = data.data;
    		var str = new StringBuffer();
    		str.append('<table class="fxtable" cellspacing="0" cellpadding="0">');
    		str.append('<tr><td width=50>显示期批次</td><td width=50>参考期批次</td><td>数据期</td></tr>');
    		for(var i=0;i<d.length;i++){
    			if(d[i].PCH==1){
    				str.append('<tr><td width=50><input type="radio" name="dictbh" value="'+d[i].DICTBH+'" checked/></td><td width=50><input type="radio" name="dictbh2" value="'+d[i].DICTBH+'"/></td><td>'+d[i].DICTNAME+'</td></tr>');
    			}else if(d[i].PCH==2)
    			{
					str.append('<tr><td width=50><input type="radio" name="dictbh" value="'+d[i].DICTBH+'" /></td><td width=50><input type="radio" name="dictbh2" value="'+d[i].DICTBH+'" checked/></td><td>'+d[i].DICTNAME+'</td></tr>');    				
    			}
    			else{
					str.append('<tr><td width=50><input type="radio" name="dictbh" value="'+d[i].DICTBH+'" /></td><td width=50><input type="radio" name="dictbh2" value="'+d[i].DICTBH+'"/></td><td>'+d[i].DICTNAME+'</td></tr>');
    			}
    			
    		}
    		str.append('</table>');
    		$('disTree').innerHTML = str.toString();
    	}
    }
    
    
    function changeQjwh(v){
    	var ajax = new AppAjax("xtconfig!getQjwhsz.do?type="+v,qjwh_back).submit();
    }
    
    function qjwh_back(data){
    	if(data != null && data.data.length > 0){
    		var d = data.data;
    		var str = new StringBuffer();
    		str.append('<table class="fxtable" cellspacing="0" cellpadding="0">');
    		str.append('<tr><td>开始值</td><td>结束值</td></tr>');
    		for(var i=0;i<d.length;i++){
    			
    			str.append('<tr>');
    			str.append('<td width=45><input type="text" style="width:150" name="qstrv" value="'+d[i].QSTRV+'"/></td>');
   				str.append('<td width=45><input type="text" style="width:140" name="qendv" value="'+d[i].QENDV+'"/></td>');
   				
   				str.append('</tr>');
    			
    		}
    		//str.append('<tr><td colspan=2><input type="增加"/></td></tr>');
    		str.append('</table>');
    		$('disTree').innerHTML = str.toString();
    	}
    }
    
    function changeGrid(v){
    	var ajax = new AppAjax("xtconfig!getGridsz.do?type="+v,grid_back).submit();
    }
    
    function grid_back(data){
    	$('disTree').innerHTML = '';
    	if(data != null && data.data.length > 0){
    		var d = data.data;
    		var str = new StringBuffer();
    		str.append('<table class="fxtable" cellspacing="0" cellpadding="0">');
    		str.append('<tr><td width=40>显示</td><td>字段ID</td><td>字段名称</td><td>宽度</td><td>对齐方向</td><td>排序</td></tr>');
    		for(var i=0;i<d.length;i++){
    			
    			str.append('<tr><td><input type="hidden" name="key" value="'+d[i].GID+'"/><select name="gdisplay">');
    			if (d[i].GDISPLAY=='1'){
   					str.append('<option value="1" selected>是</option><option value="0">否</option>');
   				}else{
   					str.append('<option value="1">是</option><option value="0" selected>否</option>');
   				}
   				str.append('</select></td>');
   				
    			str.append('<td width=45><input type="text" style="width:80" name="gid" value="'+d[i].GID+'"/></td>');
   				str.append('<td width=45><input type="text" style="width:140" name="gheader" value="'+d[i].GHEADER+'"/></td>');
   				str.append('<td width=45><input type="text" style="width:70" name="gwidth" value="'+d[i].GWIDTH+'"/></td>');
   				str.append('<td width=45><select name="galign">');
   				if (d[i].GALIGN=='right'){
   					str.append('<option value="left">左对齐</option><option value="right" selected>右对齐</option>');
   				}else{
   					str.append('<option value="left" selected>左对齐</option><option value="right">右对齐</option>');
   				}
   				str.append('</select></td>');
   				str.append('<td width=45><input type="text" style="width:40" name="gpx" value="'+d[i].GPX+'"/></td>');
   				//str.append('<td width=45><input type="text" style="width:40" name="gscript" value="'+d[i].GPX+'"/></td>');
   				str.append('</tr>');
    			
    		}
    		str.append('</table>');
    		$('disTree').innerHTML = str.toString();
    	}
    }
    
    
    function get_ps_Back(data){
    	if(data != null && data.data.length > 0){
    		var str = new StringBuffer();
    		str.append('<table class="fxtable" cellspacing="0" cellpadding="0">');
    		
    		str.append('<tr>');
			str.append('<td style="text-align:right;background:#E7F2FC;font:bold 11px \'lucida Grande\',Verdana;width:120px">每个项目专家参评项目费用</td>');
			str.append('<td>');
			str.append('<s:textfield  name="map.psje" cssStyle="width:200;" value="'+data.data+'"></s:textfield>');
			str.append('</td>');
			str.append('</tr>');
    		str.append('</table>');
    		$('disTree').innerHTML = str.toString();
    	}
    }
    
    function saveQjwh(){
        var ajax = new AppAjax("xtconfig!doSaveQjwh.do",function(data){
    		if(data!=null && data.message.code > 0){
    			alert('保存成功！');
    		}else{
    			alert(data.message.text);
    		}
    	}).submitForm("zForm");
    }
    
    function saveGrid(){
    	var ajax = new AppAjax("xtconfig!doSaveGrid.do",function(data){
    		if(data!=null && data.message.code > 0){
    			alert('保存成功！');
    		}else{
    			alert(data.message.text);
    		}
    	}).submitForm("zForm");
    }
    
    function doTrue(){
    	var db = RC.getRadioV('dictbh');
    	if(db == ''){
    		alert('请选择！');
    		return false;
    	}
    	var db2 = RC.getRadioV('dictbh2');
    	if (db==db2){
    		alert('显示期与参考期不能为同一期');
    		return false;
    	}
    	db = db+'#'+db2;
    	var ajax = new AppAjax("xtconfig!doSjq.do?dictbh="+db,function(data){
    		if(data!=null && data.message.code > 0){
    			alert('设置成功！');
    		}else{
    			alert(data.message.text);
    		}
    	}).submit();
    }
    
</script>



</html>


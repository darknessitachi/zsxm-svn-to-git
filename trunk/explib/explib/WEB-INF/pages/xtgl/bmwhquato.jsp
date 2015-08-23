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
	<body>
<s:form name="czrcForm" method="post">
	
	    <div style="width:270px;float:left">
	    
	    <div class="tableContainer" id="tableContainer" style="width:100%">
			<table id="dwTable" cellspacing="0">
				<thead>
					<tr>
						
						<td width="40px">
							代码
						</td>
						<td>
							部门
						</td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="xtdict">
						<tr onclick="clicked(this,'<s:property value="dictbh"/>')">
							<td><s:property value="dictbh"/></td>
							<td><s:property value="dictname"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		</div>
		
	<div class="tree" id="treeDiv">
			<div class="ddbox" style="width:100%;margin-bottom:3px;">
				<div style="float:left;margin-left:2px" id='butdiv'>
					<input type="button" value="选择被聘专家分类" onclick="getSel()"/>
					&nbsp&nbsp&nbsp
					<input type="button" value="删除" onclick="doDel();"/>
				</div>
			</div>
			<div id="disTree" class="tableContainer">
				<table id="dwTable" cellspacing="0">
				<thead>
					<tr>
						<td width="40px">
							序号
						</td>
						<td width="40px">
							选
						</td>
						<td width="80px">
							代码
						</td>
						<td>
							被聘专家情况
						</td>
					</tr>
				</thead>
				<tbody id="ubodys">
				</tbody>
				</table>
			</div>
	 </div>
		  
	
	      
	

</s:form>	
	</body>
<script type="text/javascript">
	$('treeDiv').style.width = getSize().w - 300;
	$('treeDiv').style.height = getSize().h - 20;
	$('tableContainer').style.height = (getSize().h - 20)+"px"; 
	
	var oldobj = '';
	var bmdm = "";
	 function clicked(obj,id){
    	treekey = '';
    	bmdm = id;
    	if(oldobj != ''){
    		oldobj.style.background='white';
    	}
    	obj.style.background="#778899";
    	oldobj = obj;
    	var ajax = new AppAjax("bmwhQuato!getBmZjList.do?bmdm="+id,getBmzj_Back).submit();
    }
    
    function getBmzj_Back(data){
    	var str = new StringBuffer("");
    	if(data != null && data.info != null){
    		var len = data.info.length;
    		for(var i=0;i<len;i++){
    			
	    		str.append('<tr>');
				str.append('<td>'+(i+1)+'</td>');
				
				str.append('<td>');
				str.append('<input type="checkbox" id="dmkey" name="dmkey" value="'+data.info[i].dictbh+'" />');
				str.append('</td>');
				
				str.append('<td>');
				str.append(data.info[i].dictbh);
				str.append('</td>');
				
				str.append('<td>');
				str.append(data.info[i].dictname);
				str.append('</td>');
				str.append('</tr>');
	    		
    		}
    	}
    	$('ubodys').update(str.toString());
    }
    
    function refresh(){
    	var ajax = new AppAjax("bmwhQuato!getBmZjList.do?bmdm="+bmdm,getBmzj_Back).submit();
    }
    
    function doDel(){
		var ajax = new AppAjax("bmwhQuato!doDelZjqkfl.do?bmdm="+bmdm,dodel_back).submitForm("czrcForm");
	}
	
	function dodel_back(data){
		if(data != null && data.message.code > 0){
			alert('删除成功！');
			refresh();
		}else{
			alert(data.message.text);
		}
	}
	
	function getSel(){
		if(bmdm == ''){
			alert('请选择相应的市属部门！');
			return false;
		}
		openWin("bmwhQuato!getZjWithSelect.do?bmdm="+bmdm,"700","550");
	}
	
</script>



</html>


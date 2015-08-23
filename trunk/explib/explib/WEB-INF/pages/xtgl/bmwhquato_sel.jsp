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
	<s:component template="xtitle" theme="app" value='选择'></s:component>
<s:form name="czrcForm"  method="post">
		<s:hidden name="bmdm"></s:hidden>
		<div class="ddbox" style="width:100%;margin-bottom:3px;">
				<div style="float:left;margin-left:2px" id='butdiv'>
					<input type="button" value="确定选择" onclick="dosel();"/>
					<input type="button" value="关  闭" onclick="javascript:closeWin(self.name);"/>
				</div>
			</div>
	    <div class="tableContainer" id="tableContainer" style="width:100%">
			<table id="dwTable" cellspacing="0">
				<thead>
					<tr>
						
						
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
					<s:iterator value="qList">
						<tr>
							<td><input type="checkbox" name="dmkey" id="dmkey" value='<s:property value="dictbh"/>'/></td>
							<td><s:property value="dictbh"/></td>
							<td><s:property value="dictname"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		
		
	
	</s:form>	      
	
	</body>


<script type="text/javascript">

	$('tableContainer').style.height = (getSize().h - 70)+"px"; 
	
	var oldobj = '';
	 function clicked(obj,id){
    	treekey = '';
    	opttype = id;
    	if(oldobj != ''){
    		oldobj.style.background='white';
    	}
    	obj.style.background="#778899";
    	oldobj = obj;
    	var ajax = new AppAjax("bmwhQuato!getBmZjList.do",get_Back).submit();
    }
    
    function dosel(){
    	var rcsel = RC.checkbox('dmkey');
		if(rcsel.length == 0){
			alert("请选择相应的数据!");
			return false;
		}
		var ajax = new AppAjax("bmwhQuato!doSaveZjqkfl.do",dosel_back).submitForm("czrcForm");
    }
    
    function dosel_back(data){
    	if(data != null && data.message.code > 0){
    		getOpener().refresh();
    		closeWin(self.name);
    	}else{
    		alert(data.message.text);
    	}
    }
</script>



</html>


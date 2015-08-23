<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/gt_grid.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/default/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/china/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/vista/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/mac/skinstyle.css"/>" />
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
				 <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>			
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
.selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }			
</style>		
	</head>
	
	<body style="margin: 0px; margin: 2px;">
	<s:form name="spForm" action="" method="post">
		<s:hidden name="winid" id="winid"></s:hidden>
		<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
					<tr>
					    <td class="lefttop"></td>
					    <td width="%"  class="centertop"></td>
					    <td class="righttop"></td>
				    </tr>
					<tr>
						<td height="26" class="leftcenter">&nbsp;</td>
					    <td class="center" >
					    	字段
					    	<select name="where" style="width:100px" onchange="changefield(this.value)">
				    			<option value="expertmc">专家名</option>
					    		<option value="idcard">证件号码</option>
					    	</select>
					    	<span id="xmdis">
					    		<s:textfield name="name" cssStyle="width:100px"></s:textfield>
					    	</span>
					    	<input type="button" id="queryBtn" class="but" value="查   询" id="b_sendmessage">
					    	
					    	<input type="button" class="but" value="确定选择" id="" onclick="doOk();">
					    	
					    	<input type="button" class="but" value="退   出" id="" onclick="javascript:closeWin();">
					    </td>
					   	<td class="rightcenter"></td>
					</tr>	
				   <tr>
					    <td class="leftbot"></td>
					    <td class="centerbot"></td>
					    <td class="rightbot"></td>
				   </tr>
			</table>
			
			<div id="mygrid_container" style="width:100%;" ></div>
			
	
	</s:form>	
	</body>
	<script type="text/javascript">
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ 
					{name : 'EXPERTID'},
					{name : 'STATE'},
					{name : 'EXPERTMC'}, 
		   		    {name : 'EDUCATION_MC'}, 
		   		    {name : 'DEGREE_MC'},
		   		    {name : 'TECHNICAL_MC'},  
		   		    {name : 'BIRTHDAY'},
		   		    {name : 'SPECIALTYCS_MC'},
		   		    {name : 'LY'},
		   		    {name : 'SZDW'}, 
					{name : 'SEX'},			
					{name : 'LINK1'},
					{name : 'ZTYY'}
					/*,
					{name : 'url' }
					*/
					]
	};

	var colsOption = [ 
	{
		id : 'EXPERTID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	},
	 {
		id : 'EXPERTMC',
		header : "姓名",
		headAlign:"center",
		width : 150,
		align : "left",
		renderer:function (v,d){ return '<a target=_blank href="'+d.LINK1+'">'+v+'</a>'}
	},  {
		id : 'SEX',
		header : "性别",
		headAlign:"center",
		width : 50,
		align : "center",
		renderer:function (v){var ss = '男';if(v==2){ss='女'} return ss;}
	}, {
		id : 'BIRTHDAY',
		header : "出生日期",
		headAlign:"center",
		width : 90,
		align : "center"
	}, {
		id : 'SPECIALTYCS_MC',
		header : "从事专业",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'LY',
		header : "专家来源",
		headAlign:"center",
		width : 80,
		align : "center",
		renderer:function (v){
			var ss = '';
			var vs = v.split(',');
			for(var xx=0;xx<vs.length;xx++){
				if(vs[xx]=='3'){
					ss +='市专家,';
				}else if(vs[xx]=='226'){
					ss +='省专家,';
				}else if(vs[xx]=='220'){
					ss += '征集专家,';
				}
			}
			return ss;
		}
	}, {
		id : 'EDUCATION_MC',
		header : "学历",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'DEGREE_MC',
		header : "学位",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'TECHNICAL_MC',
		header : "职称",
		headAlign:"center",
		width : 200,
		align : "left"
	}, {
		id : 'SZDW',
		header : "所在单位",
		headAlign:"center",
		width : 200,
		align : "left"
	}
	 ];

	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 60) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		selectRowByCheck : true, 
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload  | print | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : false,
		dataset : dsOption,
		columns : colsOption,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/fzgl!getZjpsList.do',
		autoLoad : true,
		showIndexColumn : true,
		parameters:{}
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	Event.observe("queryBtn", "click", function() {
		mygrid.query({
		"name":$F("name"),
		"where":$F("where")
		
		});
	}, true);
	
	function queryValue(){
		mygrid.query({
		"name":$F("name"),
		"where":$F("where")
		
		});
	}
	
		
	function getBpzjqk(){
		var B = COM.checkbox('bpzjqk');
		var len = B.length;
		var str = '';
		for(var i=0;i<len;i++){
			if(str == ''){
				str = "'"+B[i].v+"'";
			}else{
				str += ",'"+B[i].v+"'";
			}
		}
		return str;
	}

	function selTrue(){
		$('yjlbd').style.display = 'none';
	}	
			
	function dofilter(){
		mygrid.showDialog("filter");
	}
	
	function doquery(){
		window.open("expwh!preView.do?rcid="+mygrid.getSelectedRecord().RCID+"&opttype=3");
	}

	var tree;
	function loadTree(dm){
		if($("xtdlb").loading != 1){
			$('xtdlbload').innerHTML = "";
			tree=new dhtmlXTreeObject("xtdlbload","100%","100%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){setV(id);});
			tree.enableSmartXMLParsing(true);
			tree.setDataMode("json");
			tree.enableCheckBoxes(false);
			tree.loadJSON("exp!getQueryTree.do?dm="+dm,function(){$('xtdlbloadimage').style.display="none";});
			$('xtdlb').loading = 1;			
		}
	}
	function setV(id){
		if(id=='root'){
			$('name').value='';
		}else{
			$('name').value=id;
		}
		$('xtdlb').style.display = 'none';
		$('zsxm_button').value = tree.getItemText(id);
	}

	function changefield(v){
		
	}
	
	function queryTj(){
		if($('queryDiv').style.display == ''){
			$('queryDiv').style.display = 'none';
		}else{
			$('queryDiv').style.display = '';
		}
		
	}
	
	function doOk(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要引入的数据！");
			return false;
		}
		var r = new Array();
		for(var i=0;i<len;i++){
			r.push(mygrid.getSelectedRecords()[i].EXPERTID);
		}
		if(window.confirm('您确定要引入'+r.length+' 条数据!')){
			var ajax = new AppAjax("fzgl!doSelZjps.do?keys="+r,function(data){
				if(data.message.code > 0){
					alert('引入成功！');
					window.parent.refreshP();
				}else{
					alert(data.message.text);
				}
			}).submit();
		}
	}
</script>
</html>


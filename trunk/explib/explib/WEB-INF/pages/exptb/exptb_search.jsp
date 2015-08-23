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
				    						<option value="rcname">姓名</option>
				    						<option value="szdq">所在地区</option>
								    		<option value="sex">性别</option>
								    		<option value="nation">国籍</option>
								    		<option value="xl">学历</option>
								    		<option value="xw">学位</option>
					    	</select>
					    	<span id="xmdis">
					    		<s:textfield name="name" cssStyle="width:100px"></s:textfield>
					    	</span>
					    	<input type="button" id="queryBtn" class="but" value="查   询" id="b_sendmessage">
					    	<!-- <input type="button" class="but" value="高级查询" id="" onclick="dofilter();"> -->
					    	<input type="button" class="but" value="高级查询" id="" onclick="queryTj();">
					    	
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
			<div  class="fxtableContainer" id="queryDiv" style="display:none;float:left;width:300px;border: 1px solid #C1DAD7;" >
				<table class="fxtable" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan=4 style="text-align:center;background:#add8e6;font:bold 12px 'lucida Grande',Verdana;">高级查询</td>
					</tr>
					
					
										
					<tr>
						<td class="bt">学历</td>
						<td class="left" colspan=3><s:select name="xl" list="xtdict2" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">学位</td>
						<td class="left" colspan=3><s:select name="xw" list="xtdict3" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">专业技术职务</td>
						<td class="left" colspan=3><s:select name="zc" list="xtdict5" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">专业技术职务等级</td>
						<td class="left" colspan=3><s:select name="zw" list="xtdict23" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					
					<tr>
						<td class="bt">单位性质</td>
						<td class="left" colspan=3><s:select name="dwxz" list="xtdict12" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					
					<tr>
						<td class="bt">年龄区间</td>
						<td class="left" colspan=3><input type="text" name="strnl" value="" style="width:50px" id="strnl" onblur="COM.isNumChar(this.id)"/>&nbsp&nbsp至&nbsp&nbsp<input type="text" name="endnl" value="" style="width:50px" id="endnl" onblur="COM.isNumChar(this.id)"/></td>
					</tr>
					<tr>
						<td class="bt">工作单位</td>
						<td class="left" colspan=3>
							<input type=text name="gzdw"/>
						</td>
					</tr>
					<tr>
						<td class="bt">所在地区</td>
						<td class="left" colspan=3>
							<input type=text name="szdq"/>
						</td>
					</tr>
					
					<tr>
						<td colspan=4 style="text-align:center;background:#add8e6;font:bold 12px 'lucida Grande',Verdana;">
							<input type="button"  class="button" value="查   询" onclick="queryValue()"/>
							&nbsp&nbsp&nbsp
							<input type="button"  class="button" value="隐   藏" onclick="queryTj();"/>
						</td>
					</tr>
				</table>
			</div>
			<div id="mygrid_container" style="width:100%;" ></div>
			
	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id='xtdlbloadimage'>
			<img src="images/skin0/other/upload.gif">数据载入中.....
		</div>
		<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
			
		</div>
		
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
<div id='yjlbd' class="fsg_nr" style="display:none;width:300;height:190;">	
	<div loading='0' id='yjlbdd' style="width:300;height:150;background-color:#E7EAF7;overflow:auto;">
		<s:iterator value="xtdict25">
			<input type="checkbox" name="bpzjqk" id='yjlb<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><span id='ch<s:property value="dictbh"/>'><s:property value="dictname"/></span><br>
		</s:iterator>
	</div>
	
	<div class="footer" style="background:#f5f5f5" style="width:300;height:20;text-align:right">
			<input class="button3" type="button" value="确  定" onclick="selTrue();"/>
		&nbsp&nbsp&nbsp&nbsp
	</div>
	<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
</div>
	
	</s:form>	
	</body>
	<script type="text/javascript">
	$('queryDiv').style.height = (getSize().h - 60) + "px";
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'RCID'},
					{name : 'RCNAME'}, 
		   		    {name : 'SEX_MC'}, 
		   		    {name : 'NATION_MC'},
		   		    {name : 'JG_MC'},  
		   		    {name : 'ZJLB_MC'}, 
		   		    {name : 'ZJNO'}, 
		   		    {name : 'RCLB_MC'}, 
					{name : 'XL_MC'}, 
					{name : 'XW_MC'}, 
					{name : 'ZC_MC'}, 
					{name : 'ZW'}, 
					{name : 'WORKUNIT'}, 
					{name : 'ZGBM_MC'}, 
					{name : 'DWDQ_MC'}, 
					{name : 'XXZY_MC'}, 
					{name : 'CSZY_MC'}
					/*,
					{name : 'url' }
					*/
					]
	};

	var colsOption = [ 
	 {
		id : 'RCNAME',
		header : "姓名",
		headAlign:"center",
		width : 80,
		align : "left"
	}, {
		id : 'SEX_MC',
		header : "性别",
		width : 40,
		align : "center"
		
	}, {
		id : 'NATION_MC',
		header : "国籍",
		headAlign:"center",
		width : 100,
		align : "left"
	}, 
	 {
		id : 'JG_MC',
		header : "籍贯",
		headAlign:"center",
		width : 100,
		align : "left"
	},{
		id : 'ZJLB_MC',
		header : "证件类别",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'ZJNO',
		header : "证件号码",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'RCLB_MC',
		header : "专家类别",
		headAlign:"center",
		align : "left"
	}, {
		id : 'XL_MC',
		header : "学历",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'XW_MC',
		header : "学位",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZC_MC',
		header : "职称",
		width : 100,
		headAlign:"center",
		align : "left"

	}, {
		id : 'ZW',
		header : "职务",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'WORKUNIT',
		header : "工作单位",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'XXZY_MC',
		header : "所学专业",
		headAlign:"center",
		align : "left"
	}, {
		id : 'CSZY_MC',
		header : "从事专业",
		headAlign:"center",
		align : "left"
	}
	/**
	, {
		id : 'URL',
		header : "详细",
		align : "center", 
		renderer : function(v){
			return '<a href="'+ APP_PATH +'/#" >详情</a>';
		}
	}
	*/
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
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload | export xls | print | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : false,
		dataset : dsOption,
		columns : colsOption,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/expquery!getList.do',
		exportURL : APP_PATH + '/expquery!exportList.do',
		exportFileName : "列表",
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
		"where":$F("where"),
		"xl":$F("xl"),
		"xw":$F("xw"),
		"zc":$F("zc"),
		"zw":$F("zw"),
		"dwxz":$F("dwxz"),
		"strnl":$F("strnl"),
		"endnl":$F("endnl")
		});
	}, true);
	
	function queryValue(){
		mygrid.query({
		"name":$F("name"),
		"where":$F("where"),
		"xl":$F("xl"),
		"xw":$F("xw"),
		"zc":$F("zc"),
		"zw":$F("zw"),
		"dwxz":$F("dwxz"),
		"strnl":$F("strnl"),
		"endnl":$F("endnl")
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
		$('xtdlb').loading = 0;
		if(v != 'rcname' && v != 'szdq'){
			var dm = '';
			if(v=='sex'){
				dm=4;
			}else if(v=='nation'){
				dm=20;
			}else if(v=='rclb'){
				dm='rclb';
			}else if(v=='zgbm'){
				dm=21;
			}else if(v=='dwdq'){
				dm=22;
			}else if(v=='xl'){
				dm=2;
			}else if(v=='xw'){
				dm=3;
			}
			$('xmdis').innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(\''+dm+'\');"/><input type="hidden" name="name"/>';
		}else{
			$('xmdis').innerHTML = '<s:textfield name="name" cssStyle="width:100px"></s:textfield>';
		}
	}
	
	function queryTj(){
		if($('queryDiv').style.display == ''){
			$('queryDiv').style.display = 'none';
		}else{
			$('queryDiv').style.display = '';
		}
		
	}
	
	function doOk(){
		window.parent._DialogFrame_exptbwindows.setTbID(mygrid.getSelectedRecord().RCID);
		closeWin();
	}
</script>
</html>


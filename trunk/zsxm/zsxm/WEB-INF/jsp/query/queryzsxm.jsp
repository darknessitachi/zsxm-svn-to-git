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
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>		
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
<style>
.selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }			
</style>		
	</head>

	<body style="margin: 0px; overflow: hidden; margin: 2px;">
		<s:form name="spForm" action="" method="post">
     		
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
								    		<option value="xmmc">项目名称</option>
								    		<option value="xmlb">项目类别</option>
								    		<option value="xmgs">项目概述</option>
								    		<option value="xmjdgs">项目进度概述</option>
								    	</select>
								    	
								    	<span id="xmdis">
								    		<s:textfield name="name" cssStyle="width:100px"></s:textfield>
								    	</span>
								    	<input type="hidden" name="gsearch" id="gsearch"/>
								    	<input type="button" class="but" value="查   询" id="queryBtn" onclick="">
								    	
								    	<input type="button" class="but" value="高级查询" id="b_sendmessage" onclick="gsearchW();">
								    	<input type="button" class="but" value="导出EXCEL" id="b_sendmessage" onclick="doExport();">
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
				
	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id='xtdlbloadimage'>
			<img src="images/skin0/other/upload.gif">数据载入中.....
		</div>
		<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
			
		</div>
		
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>				
		</s:form>
	</body>
	<script>
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ 
					 <s:iterator id="header" value="gheades">
						{name : '<s:property id="header" value="gid"/>'},
					</s:iterator>
					{}
				]	
	};

	var colsOption = [ 
		<s:iterator id="col" value="gcols">
			{
				id : '<s:property id="col" value="gid"/>',
				header : '<s:property id="col" value="gheader"/>',
				width : '<s:property id="col" value="gwidth"/>',
				headAlign:"center",
				align:'<s:property id="col" value="galign"/>',
				isCheckColumn : <s:property id="col" value="gcheckcolumn"/>,
				filterable	: <s:property id="col" value="gfilterable"/>
				<s:if test="gscript==1">
					,renderer:function (v,r){return '<a href="zsxm!getZsxmAllView.do?xmid='+r.XMID+'" target=_blank title="点击查看详细信息">'+v+'</a>';}
				</s:if>
			},
		</s:iterator>
		{id:'',header:'',filterable:false}
	 ];
	colsOption.splice(colsOption.length-1,1);
	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 70) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		selectRowByCheck : true, 
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload | export xls | print | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : true,
		dataset : dsOption,
		columns : colsOption,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/xmquery!getList.do',
		exportURL : APP_PATH + '/xmquery!exportList.do',
		exportFileName : "项目信息",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	Event.observe("queryBtn", "click", function() {
		mygrid.query({"name":$("name").value,"where":$("where").value,"gsearch":$('gsearch').value});
	}, true);		
	
	function dofilter(){
		mygrid.showDialog("filter");
	}
	
	function querymx(obj){
		 var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		 openWin("zsxm!viewZsxm.do?xmid="+obj+"&opttype=3",w,h);
	}
		
	function refresh(){
		mygrid.query({"name":$("name").value,"where":$("where").value,"gsearch":$('gsearch').value});
	}
	
	function doExport(){
		window.location.href = "zsxmwh!doExportExcel.do";
		//new AppAjax("zsdwwh!doExportExcel.do",function(data){}).submit();
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
			tree.loadJSON("zsxm!getXmTree.do?querydm="+dm,function(){$('xtdlbloadimage').style.display="none";});
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
		if(v != 'xmmc' && v!= 'xmgs'){
			if(v=='xmjdgs'){
				$('xmdis').innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(3);"/><input type="hidden" name="name"/>';			
			}else{
				$('xmdis').innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(1);"/><input type="hidden" name="name"/>';
			}
		}else{
			$('xmdis').innerHTML = '<s:textfield name="name" cssStyle="width:100px"></s:textfield>';
		}
	}
	
	function gsearchW(){
		openWin("query!xmHQuery.do",700,300);
	}
	
	function selXmtj(){
		
	}
	
	function setHSearchT(v){
		$('gsearch').value = v;
		refresh();
	}
	
	</script>
</html>

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
								    		<option value="dwmc">单位名称</option>
								    		<option value="dwdm">组织机构代码</option>
								    		<option value="dwzt">单位状态</option>
								    		<option value="dwlx">单位类型</option>
								    		<option value="xm_mc">招资项目</option>
								    	</select>
								    	<span id="xmdis">
								    		<s:textfield name="name" cssStyle="width:100px" onkeydown="queryBykey()"></s:textfield>
								    	</span>
								    	<input type="hidden" name="gsearch" id="gsearch"/>
								    	<input type="button" class="but" value="查   询" id="queryBtn" onclick="">
								    	<input type="button" class="but" value="修   改" id="b_sendmessage" onclick="reprow()">
								    	<input type="button" class="but" value="删   除" id="b_sendmessage" onclick="deldata()">
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
	var date;
 	date=new Date();
	var optyy = date.getYear()-1;
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
					,renderer:function (v,r){return '<a href="javascript:;" title="点击查看详细信息" onclick="querymx('+r.DWID+')">'+v+'</a>';}
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
		loadURL : APP_PATH + '/zsdwwh!getList.do',
		exportURL : APP_PATH + '/zsdwwh!exportList.do',
		exportFileName : "企业信息",
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
	
	function queryBykey(){
		if(event.keyCode==13){
			mygrid.query({"name":$("name").value,"where":$("where").value,"gsearch":$('gsearch').value});
		}
	}
	
	function querymx(obj){
		 var h = document.body.clientHeight-50;
			 var w = '';
			 if(getSize().w > 965){
				w = 965;
			 }else{
				w = getSize().w;
			 }
		
		 openWin("zsdwview!preZsdwU.do?dwid="+obj+"&opttype=3",w,h);
	}
		
	function reprow(){
		var len = mygrid.getSelectedRecords().length;
		if(len > 1){
			alert("修改只能选择一条记录!");
			return false;
		}
		if(len < 1){
			alert('请选择需要修改的记录!');
			return false;
		}
		if(len==1){
			 var h = document.body.clientHeight-50;
			 var w = '';
			 if(getSize().w > 965){
				w = 965;
			 }else{
				w = getSize().w;
			 }
			
			 openWin("zsdw!preZsdwU.do?dwid="+mygrid.getSelectedRecord().DWID+"&opttype=3",w,h);
		}
	}
	
	function refresh(){
		mygrid.query({"name":$("name").value,"where":$("where").value,"gsearch":$('gsearch').value});
	}
	
	function deldata(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要删除的数据！");
			return false;
		}
		var r = new Array();
		for(var i=0;i<len;i++){
			r.push(mygrid.getSelectedRecords()[i].DWID);
		}
		if(window.confirm("请确定需要删除《"+len+"》条记录!")){
			var ajax = new AppAjax("zsdwwh!doDeletezsxm.do?dwid="+r,save_Back).submit();
		}
	}
	

	function save_Back(data,type){
		if(data.message.code >0){
			alert(data.message.text);
			refresh();
		}else{
			alert(data.message.text);
		}
	}
	
	function doExport(){
		window.location.href = "zsdwwh!doExportExcel.do";
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
		if(v != 'dwdm' && v!= 'dwmc' && v!='xm_mc'){
			if(v=='dwzt'){
				$('xmdis').innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(4);"/><input type="hidden" name="name"/>';			
			}else if(v=='dwlx'){
				$('xmdis').innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(9);"/><input type="hidden" name="name"/>';
			}else{
				$('xmdis').innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(1);"/><input type="hidden" name="name"/>';
			}
		}else{
			$('xmdis').innerHTML = '<s:textfield name="name" cssStyle="width:100px"></s:textfield>';
		}
	}	
	
	function gsearchW(){
		openWin("query!dwHQuery.do",860,400);
	}

	function setHSearchT(v){
		$('gsearch').value = v;
		refresh();
	}
			
	</script>
</html>

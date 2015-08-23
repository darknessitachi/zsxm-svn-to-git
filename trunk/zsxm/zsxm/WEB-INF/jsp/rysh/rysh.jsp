<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css" rel="stylesheet"/>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/gt_grid.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/vista/skinstyle.css"/>" />
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
		
		<style type="text/css">
			.tree {float: left;overflow:auto;width:220px;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:30px;}
		    .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		    .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
		</style>
	</head>

	<body style="margin: 0px; overflow: hidden; margin: 2px;">
		<s:form name="spForm" action="" method="post">
     	<div class="tree" id="treeDiv">
			<div class="ddbox" style="width:100%;margin-bottom:3px;">
				<b>选择审核企业</b>
			</div>
			<div id="disTree">
			</div>
	     </div>
	     <div   id="showTable"  >
				<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
							<tr>
								  <td class="lefttop"></td>
								  <td width="%"  class="centertop"></td>
								  <td class="righttop"></td>
						    </tr>
							<tr>
									<td height="26" class="leftcenter">&nbsp;</td>
								    <td class="center" >
								    	<select name="where" style="width:100px">
								    		<option value="xm">姓名</option>
								    	</select>
								    	<span id="xmdis">
								    		<s:textfield name="name" cssStyle="width:100px"></s:textfield>
								    	</span>
								    	<input type="button" class="but" value="查   询" id="queryBtn">
								    	<input type="button" class="but" value="新   增" id="xgBtn" onclick="xzry()">
								    	<input type="button" class="but" value="修   改" id="xgBtn" onclick="xgry()">
								    	<input type="button" class="but" value="归   档" id="gdBtn" onclick="gdry()"/>
								    	<input type="button" class="but" value="冻   结" id="djBtn" onclick="djry()"/>
								    	<input type="button" class="but" value="取消归档" id="qgBtn" onclick="qxopt(8)"/>
								    	<input type="button" class="but" value="取消冻结" id="qdBtn" onclick="qxopt(9)"/>
								    	<input type="button" class="but" value="批量退回" id="qdBtn" onclick="batchth()"/>
								    	<input type="button" class="but" value="批量发回" id="qdBtn" onclick="batchfh()"/>
								    	<input type="button" class="but" value="高级查询" id="" onclick="dofilter();"/>
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
			</div>
		</s:form>
	</body>
	<script>
	setpos();
	function setpos()
	{
		$("treeDiv").style.height=(getSize().h - 20)+"px" ;
		$("showTable").style.width=getSize().w-230+"px";
		$("mygrid_container").style.width=getSize().w-230+"px";
		$("mygrid_container").style.height=(getSize().h - 5)+"px" ;
	}
	/** 用于DhtmlxTree */
	var tree;
	loadTree();
	function loadTree(){
		$('disTree').innerHTML = "";
		tree=new dhtmlXTreeObject("disTree","100%","90%",0);
		tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
		tree.setOnClickHandler(function(id){setTree(id);});
		tree.enableSmartXMLParsing(true);
		tree.setDataMode("json");
		tree.enableCheckBoxes(false);
		tree.loadJSON("rysh!getDwinfoTree.do",function(){tree.openAllItems('root');});
	}
	
	var treekey = "";
	function setTree(id){
		treekey = id;
		refresh();
	}
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'RYID'},
					{name : 'XH'},
		   		    {name : 'XM'}, 
		   		    {name : 'SFZ'}, 
		   		    {name : 'SEX_MC'},
		   		    {name : 'BIRTHDAY'},  
		   		    {name : 'XL_MC'}, 
		   		    {name : 'ZC_MC'}, 
		   		    {name : 'HWLXQK'}, 
					{name : 'YJFX'}, 
					{name : 'QPSJ'}, 
					{name : 'ZJZ'}
					]
	};

	var colsOption = [ 
	{
		id : 'RYID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	}, {
		id : 'SBBZ',
		header : "上报状态",
		headAlign:"center",
		width : 70,
		align : "left",
		renderer:function (v){var str ="待审";if(v==1){str="<font color=blue>待审核</font>";}else if(v==2){str = "<font color='#a52a2a'>审核通过</font>";}else if(v==3){str = "<font color='red'>审核退回</font>";}else if(v==4){str = "<font color='red'>数据发回</font>";} return str;}
	}, {
		id : 'STATUS',
		header : "人员状态",
		headAlign:"center",
		width : 70,
		align : "left",
		renderer:function (v){var str ="正常"; if(v==2){str="<font color='#a52a2a'>归档</font>";}else if(v==5){str="<font color=red>冻结</font>";}  return str;}
	},{
		id : 'OPTION',
		header : "操作",
		headAlign:"center",
		width : 80,
		align : "center",
		renderer:function (v,r){var str ="";if(r.SBBZ==1){str="<a href='javascript:;' onclick='datash(\""+r.DWID+"\",\""+r.RYID+"\");'>数据审核</a>";}else if(r.SBBZ==2){str = "<a href='javascript:;' onclick='datafh(\""+r.DWID+"\",\""+r.RYID+"\");'>数据发回</a>";} return str;}
	},{
		id : 'XM',
		header : "姓名",
		headAlign:"center",
		width : 80,
		align : "left",
		renderer:function (v,r){return str="<a href='javascript:;' onclick='dataview(\""+r.DWID+"\",\""+r.RYID+"\");'>"+v+"</a>";}
	}, {
		id : 'THYY',
		header : "退回原因",
		headAlign:"center",
		width : 140,
		align : "left",
		renderer:function (v,r){if( parseInt(r.SBBZ) == 1 || parseInt(r.SBBZ) ==3 ){return v;}}
		
	}, {
		id : 'SFZ',
		header : "身份证",
		headAlign:"center",
		width : 140,
		align : "left"
		
	}, {
		id : 'ZZMM_MC',
		header : "政治面貌",
		headAlign:"center",
		width : 80,
		align : "left"
		
	}, {
		id : 'JG',
		header : "籍贯",
		headAlign:"center",
		width : 80,
		align : "left"
		
	}, {
		id : 'HJD',
		header : "户籍地",
		headAlign:"center",
		width : 80,
		align : "left"
		
	}, {
		id : 'SEX_MC',
		header : "性别",
		headAlign:"center",
		width : 40,
		align : "center"
	}, 
	 {
		id : 'BIRTHDAY',
		header : "出生年月",
		headAlign:"center",
		width : 100,
		align : "center"
	},{
		id : 'XL_MC',
		header : "学历/学位",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'ZC_MC',
		header : "职称/职务",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'HWLXQK',
		header : "海外留学情况",
		headAlign:"center",
		align : "left"
	}, {
		id : 'YJFX',
		header : "研究方向",
		width : 70,
		headAlign:"center",
		align : "left"
	}, {
		id : 'QPSJ',
		header : "起聘时间",
		width : 80,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZJZ',
		header : "专兼职",
		width : 80,
		headAlign:"center",
		align : "center",
		renderer:function (v){var str ="兼职"; if(v==1){str="专职";} return str;}

	}
	 ];

	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 80) + "px",
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
		loadURL : APP_PATH + '/rysh!doGetList.do',
		exportURL : APP_PATH + '/rysh!doExportList.do',
		exportFileName : "企业人员信息",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	Event.observe("queryBtn", "click", function() {
		mygrid.query({
		"name":$F("name"),
		"where":$F("where"),
		"dwid":treekey
		});
	}, true);
	
	function queryValue(){
		mygrid.query({
		"name":$F("name"),
		"where":$F("where"),
		"dwid":treekey
		});
	}

	function refresh(){
		mygrid.query({
		"name":$F("name"),
		"where":$F("where"),
		"dwid":treekey
		});
	}
	

	function dofilter(){
		mygrid.showDialog("filter");
	}
	
	function dataview(d,r){
		var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		parent.openWin("rysh!view.do?dwid="+d+"&ryid="+r,w,h);
	}
	
	function datash(d,r){
		var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		parent.openWin("rysh!preRysh.do?dwid="+d+"&ryid="+r,w,h);
	}
	
	function datafh(d,r){
		var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		parent.openWin("rysh!preShfh.do?dwid="+d+"&ryid="+r,"450","180");
	}
	
	function xzry(){
		var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		if(treekey != 0 && treekey!='' && treekey!='a1'&& treekey!='a2'){
			parent.openWin("rysh!preRyxz.do?dwid="+treekey,w,h);
		}else{
			alert('请选择相应的单位！');
		}
	}
	
	function xgry(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要修改的数据！");
			return false;
		}
		if(len > 1){
			alert("修改只能选择一条数据！");
			return false;
		}
		var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		parent.openWin("rysh!preRyxg.do?dwid="+mygrid.getSelectedRecord().DWID+"&ryid="+mygrid.getSelectedRecord().RYID,w,h);
	}
	function gdry(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要归档的数据！");
			return false;
		}
		
		if(window.confirm('您确定归档 '+len+' 条数据?')){
			var r = new Array();
			var d = new Array();
			for(var i=0;i<len;i++){
				r.push(mygrid.getSelectedRecords()[i].RYID);
				d.push(mygrid.getSelectedRecords()[i].DWID);
			}
			
			var ajax = new AppAjax("rysh!doRyopt.do?dwid="+d+"&ryid="+r+"&status=2",opt_Back).submit();
		}
	}
	
	function djry(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要冻结的数据！");
			return false;
		}
		
		if(window.confirm('您确定冻结 '+len+' 条数据?')){
			var r = new Array();
			var d = new Array();
			for(var i=0;i<len;i++){
				r.push(mygrid.getSelectedRecords()[i].RYID);
				d.push(mygrid.getSelectedRecords()[i].DWID);
			}
			parent.openWin("rysh!preRydj.do?dwid="+d+"&ryid="+r+"&status=5","320","200");
			//var ajax = new AppAjax("rysh!doRyopt.do?dwid="+d+"&ryid="+r+"&status=5",opt_Back).submit();
		}
	}
	
	function qxopt(v){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要取消的数据！");
			return false;
		}
		
		if(window.confirm('您确定要取消 '+len+' 条数据?')){
			var r = new Array();
			var d = new Array();
			for(var i=0;i<len;i++){
				r.push(mygrid.getSelectedRecords()[i].RYID);
				d.push(mygrid.getSelectedRecords()[i].DWID);
			}
			
			var ajax = new AppAjax("rysh!doRyopt.do?dwid="+d+"&ryid="+r+"&status="+v,opt_Back).submit();
		}
	}
	
	function opt_Back(data){
		if(data != null && data.message.code>0){
			alert('操作成功！');
			refresh();
		}else{
			alert(data.message.text);
		}
	}
	
	function batchth(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要退回的数据！");
			return false;
		}
		var yy = 0;xx=0;
		for(var i=0;i<len;i++){
			if(mygrid.getSelectedRecords()[i].SBBZ == 1){
				++yy;
			}else{
				++xx;
			}
		}
		if(yy == 0){
			alert('没有可以退回的数据！');
			return false;
		}
		if(window.confirm('您选择 '+len+' 条数据，其中 '+yy+' 条可以退回 ! '+xx+' 条状态不能退回！确定吗？')){
			var r = new Array();
			var d = new Array();
			for(var i=0;i<len;i++){
				if(mygrid.getSelectedRecords()[i].SBBZ == 1){
					r.push(mygrid.getSelectedRecords()[i].RYID);
					d.push(mygrid.getSelectedRecords()[i].DWID);
				}
			}
			parent.openWin("rysh!preShth.do?dwid="+d+"&ryid="+r,"450","180");
			//var ajax = new AppAjax("rysh!doRyopt.do?dwid="+d+"&ryid="+r+"",opt_Back).submit();
		}
		
	}
	
	function batchfh(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要发回的数据！");
			return false;
		}
		var yy = 0;xx=0;
		for(var i=0;i<len;i++){
			if(mygrid.getSelectedRecords()[i].SBBZ == 2){
				++yy;
			}else{
				++xx;
			}
		}
		if(yy == 0){
			alert('没有可以发回的数据！');
			return false;
		}		
		if(window.confirm('您选择 '+len+' 条数据，其中 '+yy+' 条可以发回 ! '+xx+' 条状态不能发回！确定吗？')){
			var r = new Array();
			var d = new Array();
			for(var i=0;i<len;i++){
				if(mygrid.getSelectedRecords()[i].SBBZ == 2){
					r.push(mygrid.getSelectedRecords()[i].RYID);
					d.push(mygrid.getSelectedRecords()[i].DWID);
				}
			}
			parent.openWin("rysh!preShfh.do?dwid="+d+"&ryid="+r,"450","180");
			//var ajax = new AppAjax("rysh!doRyopt.do?dwid="+d+"&ryid="+r+"",opt_Back).submit();
		}
		
	}
	</script>
</html>

<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:200px;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:30px;}
		    .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		    .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
		</style>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/gt_grid.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/default/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/china/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/vista/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/mac/skinstyle.css"/>" />
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
         
	</head>
	<body style="overflow:hidden;margin:2px;">
		<div class="tree" id="treeDiv">
			<div class="ddbox" style="width:100%;margin-bottom:3px;">
				<div>
					<input type="button" class="button3" id="inserxj" value="新增" onclick="add();">	
					<input type="button" class="button3"  id="inserbj" value="修改" onclick="rep();">
					<input type="button" class="button3"  id="inserbj" value="结束" onclick="end();">	
					<input type="button" class="button3"  id="inserbj" value="删除" onclick="del();">
				</div>
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
							    	<select name="where" style="width:100">
							    		<option value="dwmc">单位名称</option>
							    	</select>
							    	<s:textfield name="name"></s:textfield>
							    	<input type="button" class="but" value="查    询" id="queryBtn" onclick="queryDw()">
							    	<input type="button" class="but" value="选择单位" id="queryBtn" onclick="seldw()">
							    	<input type="button" class="but" value="删除单位" id="b_sendmessage" onclick="deldw()">
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
	
	</body>
<script>
	var treekey = "";
	setpos();
	function setpos()
	{
		$("treeDiv").style.height=(getSize().h - 20)+"px" ;
		$("conTable").style.width=getSize().w-210+"px";
		$("mygrid_container").style.width=getSize().w-210+"px";
		$("mygrid_container").style.height=(getSize().h - 20)+"px" ;
	}
	

	/** 用于DhtmlxTree */
	var tree;
	loadTree();
	function loadTree(){
		$('disTree').innerHTML = "";
		tree=new dhtmlXTreeObject("disTree","100%","85%",0);
		tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
		tree.setOnClickHandler(function(id){setTree(id);});
		tree.enableSmartXMLParsing(true);
		tree.setDataMode("json");
		tree.enableCheckBoxes(false);
		tree.loadJSON("dwsb!getDwsbTree.do",function(){tree.openAllItems('root');});
		
	}
	
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'DWID'},
					{name : 'DWXZBZ'},
					{name : 'DWDM'},
		   		    {name : 'DWMC'}, 
		   		    {name : 'DWZT_MC'}, 
		   		    {name : 'DWLX_MC'}, 
		   		    {name : 'XM_MC'},
		   		    {name : 'LOGINNAME'},
		   		    {name : 'FRDB'},  
		   		    {name : 'CLRQ'}, 
		   		    {name : 'NWZ_MC'}, 
		   		    {name : 'ZCZB'}, 
					{name : 'JZMJ'}, 
					{name : 'LXRXM'}, 
					{name : 'SEX_MC'}, 
					{name : 'ZC_MC'},
					{name : 'ZW'},
					{name : 'TEL'},
					{name : 'PHONE'},
					{name : 'SSBM'}
				]
	};

	
	var colsOption = [ 
	{
		id : 'DWID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	},{
		id : 'DWXZBZ',
		header : "状态",
		width : 80,
		headAlign:"center",
		align : "center",
		renderer:function (v){var ss ="待报";if(v==5){ss="待审核";}else if(v==3){ss="审核通过";}else if(v==4){ss="审核退回"} return ss;}
	},{
		id : 'DWID11',
		header : "审核操作",
		width : 80,
		headAlign:"center",
		align : "center",
		renderer:function (v,r){var ss = "";if(r.DWXZBZ==5){ ss= '<a href="javascript:;" onclick="datash('+r.DWID+');">数据审核</a>';} return ss;}
	}, {
		id : 'DWDM',
		header : "组织机构代码",
		headAlign:"center",
		width : 120,
		align : "center",
		renderer:function (v,r){return '<a href="javascript:;" title="点击查看详细信息" onclick="querymx('+r.DWID+')">'+v+'</a>';}
	}, {
		id : 'DWMC',
		header : "单位名称",
		headAlign:"center",
		width : 120,
		align : "left",
		renderer:function (v,r){return '<a href="javascript:;" title="点击查看详细信息" onclick="querymx('+r.DWID+')">'+v+'</a>';}
		
	}, {
		id : 'DWZT_MC',
		header : "单位状态",
		headAlign:"center",
		width : 80,
		align : "center"
	}, 
	 {
		id : 'DWLX_MC',
		header : "单位类型",
		headAlign:"center",
		width : 150,
		align : "left"
	},{
		id : 'XM_MC',
		header : "招资项目",
		headAlign:"center",
		width : 150,
		align : "left"
	},{
		id : 'LOGINNAME',
		header : "用户登入名",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'FRDB',
		header : "法人代表",
		headAlign:"center",
		width : 70,
		align : "left"
	}, {
		id : 'CLRQ',
		header : "成立日期",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'NWZ_MC',
		header : "内/外资",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZCZB',
		header : "注册资本",
		width : 100,
		headAlign:"center",
		align : "right"
	},{
		id : 'JZMJ',
		header : "建筑面积",
		width : 70,
		headAlign:"center",
		align : "right"
	},
	{
		id : 'LXRXM',
		header : "联系人姓名",
		width : 70,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'SEX_MC',
		header : "性别",
		width : 40,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'ZC_MC',
		header : "职称",
		width : 100,
		headAlign:"center",
		align : "left"
	}
	 ];
	 
	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 65) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		selectRowByCheck : true, 
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload | export xls print | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : true,
		dataset : dsOption,
		columns : colsOption,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/dwsb!getList.do',
		exportURL : APP_PATH + '/dwsb!exportList.do',
		exportFileName : "列表",
		autoLoad : false
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);
	
	var treekey = "";
	////查询表单的查询函数 ////
	function queryDw() {
		if(treekey == ""){
			alert("请选择相应的填报！");
			return false;
		}
		mygrid.query({"name":$F("name"),"where":$F("where"),"treekey":treekey});
	}
	
	function seldw(){
		if(treekey != "" && treekey!="root" && treekey.length==8){
			openWin("dwsb!getDwsbWaitSelect.do?dm="+treekey,"750","500");
		}else{
			alert("请选择相应的单位填报类型 !");
		}
	}
	
	function deldw(){
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
			var ajax = new AppAjax("dwsb!doDelSelectedDwsb.do?dwids="+r+"&dm="+treekey,save_Back_mx).submit();
		}
	}
	function save_Back_mx(data){
		if(data.message.code >0){
			alert("删除成功！");
			queryDw();
		}else{
			alert(data.message.text);
		}
	}
	
	function setTree(id){
		treekey = id;
		queryDw();
	}
	function add(){
		openWin("dwsb!preDwsb.do?opttype=1","350","220");
	}
	function rep(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		openWin("dwsb!repDwsb.do?opttype=2&dm="+treekey,"350","220");
	}
	function del(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		
		if(window.confirm('您确定要删除当前填报！')){
			var ajax = new AppAjax("dwsb!doDelDwsb.do?dm="+treekey,function(data){del_Back(data)});
			ajax.setAsyn(false);
			ajax.submit();
		}
	}
	
	function del_Back(data){
		if(data != null ){
			if(data.message.code > 0){
				alert('删除成功！');
				loadTree();
			}else{
				alert(data.message.text);
			}
		}
	}
	
	function end(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		
		if(window.confirm('您确定要结束当前填报！')){
			var ajax = new AppAjax("dwsb!checkWithEnd.do?dm="+treekey,function(data){end_Back(data)});
			ajax.setAsyn(false);
			ajax.submit();
		}		
	}
		
	function end_Back(data){
		if(data != null ){
			if(data.message.code > 0){
				var ajax = new AppAjax("dwsb!updateEnd.do?dm="+treekey,function(data){do_end_Back(data)});
					ajax.setAsyn(false);
					ajax.submit();
			}else{
				if(window.confirm(data.message.text)){
					var ajax = new AppAjax("dwsb!updateEnd.do?dm="+treekey,function(data){do_end_Back(data)});
					ajax.setAsyn(false);
					ajax.submit();
				}
			}
		}
	}
	
	function do_end_Back(data){
		if(data != null ){
			if(data.message.code > 0){
				alert('当前填报已结束！');
				loadTree();
			}else{
				alert(data.message.text);
			}
		}
	}	
	function querymx(obj){
		 var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		 openWin("zsdw!viewZsdw.do?dwid="+obj+"&opttype=3",w,h);
	}	
	
	function datash(s){
		var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		openWin("dwsb!preDatash.do?dwids="+s+"&dm="+treekey,w,h);
	}
</script>
</html>

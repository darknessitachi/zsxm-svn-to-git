<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:200px;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:50px;}
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
					<input type="button" class="button3" id="inserxj" value="新增下级" onclick="addfl(0);">	
					<input type="button" class="button3" disabled id="inserbj" value="新增本级" onclick="addfl(1);">	
					<br>
					<input type="button" class="button3" value="修    改" onclick="repfl();">
					<input type="button" class="button3" value="删    除" onclick="delfl();">
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
							    	<INPUT id="queryfs" class="selectBut2"  title="单位标引筛选" value="单位标引筛选" type=button onclick="O_D('queryfs','flwhsel','bottom');loadSelFlTree();">
							    	<s:hidden name="selbydm"></s:hidden>
									<input type="button" class="but" value="选择单位" id="queryBtn" onclick="selrcxx()">
							    	<input type="button" class="but" value="删除单位" id="b_sendmessage" onclick="delrcxx()">
									
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
	<div id='flwhsel' class="fsg_nr" style="display:none;width:300;height:250;">	
	<div loading='0' id="treesel" style="width:300;height:220;overflow:auto;">
		<div id="flwhselload"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
	</div>
	<div class="footer" style="background:#f5f5f5" style="width:299;height:30;">
		<input class="button3" type="button" value="确  定" onclick="queryrcxx();"/>
		&nbsp&nbsp&nbsp&nbsp
	</div>
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
		tree.loadJSON("zsdwby!getDwbywhTree.do",function(){});
	}
	
	var seltree;
	function loadSelFlTree(){
		if($("treesel").loading != 1){
			seltree=new dhtmlXTreeObject("treesel","100%","100%",0);
			seltree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			//seltree.setOnClickHandler(function(id){setVByTree(id);});
			seltree.enableSmartXMLParsing(true);
			seltree.setDataMode("json");
			//seltree.enableCheckBoxes(true);
			seltree.enableCheckBoxes(1);
			//seltree.enableThreeStateCheckboxes(true);
			seltree.loadJSON("zsdwby!getDwbywhTree.do",function(){$('flwhselload').style.display="none";});
			$('treesel').loading = 1;		
		}
	}
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ <s:iterator id="header" value="gheades">
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
					,renderer:function (v,r){return '<a href="zsdwview!getZsdwAllView.do?dwid='+r.DWID+'" target=_blank title="点击查看详细信息">'+v+'</a>';}
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
		loadURL : APP_PATH + '/zsdwby!getZsDwSelected.do',
		exportURL : APP_PATH + '/zsdwby!doExportZsDwList.do',
		exportFileName : "列表",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);
	
	
	function addfl(type){
		var dm = treekey;
		if(treekey == 'root')
			{dm = '';}
		openWin("zsdwby!preAddDwby.do?flmap.type="+type+"&flmap.bydm="+dm+"&option=1","350","220");
	}
	
	
	function repfl(){
		if(treekey != "" && treekey!="root"){
			openWin("zsdwby!preRepDwby.do?bydm="+treekey+"&option=3","350","220");
		}else{
			alert("请选择需修改的数据！");
		}
	}
	
	function delfl(){
		if(treekey != "" && treekey!="root"){
			if(window.confirm("您确定要删除当前数据！")){
				var ajax = new AppAjax("zsdwby!doDelDwby.do?bydm="+treekey,save_Back).submit();
			}
		}else{
			alert("请选择需删除的数据！");
		}
	}
	
	function delrcxx(){
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
			var ajax = new AppAjax("zsdwby!doDelSelectedDwmx.do?dwids="+r+"&bydm="+treekey,save_Back_mx).submit();
		}
	}
	
		
	function save_Back(data){
		if(data.message.code >0){
			alert("删除成功！");
			refreshfl();
		}else{
			alert(data.message.text);
		}
	}
		
	function save_Back_mx(data){
		if(data.message.code >0){
			alert("删除成功！");
			refreshflmx();
		}else{
			alert(data.message.text);
		}
	}
	
	function selrcxx(){
		if(treekey != "" && treekey!="root"){
			openWin("zsdwby!getZsDwWaitSelect.do?bydm="+treekey,"750","500");
		}else{
			alert("请选择相应的项目标引");
		}
	}
	
	function insert(){
		 tree.insertNewChild($('pcode').value,$('code').value,$('codename').value); 
	}
	
	function refreshfl(){
		treekey = "";
		loadTree();
	}
	
	function refreshflmx(){
		mygrid.query({"bydm":treekey,"selbydm":$("selbydm").value});
		mygrid.parameters = {"bydm":treekey,"selbydm":$("selbydm").value};	
	}
	
	function queryrcxx(){
		var bhs = seltree.getAllChecked();
		$("selbydm").value = bhs;
		$('flwhsel').style.display="none";
		mygrid.query({"bydm":treekey,"selbydm":$("selbydm").value});
	}
	
	function setTree(id){
		treekey = id;
		if(treekey == 'root'){
			document.getElementById('inserbj').disabled = true;
			document.getElementById('inserxj').disabled = false;
		}else{
			document.getElementById('inserxj').disabled = false;
			document.getElementById('inserbj').disabled = false;
		}
		
		refreshflmx();
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
</script>
</html>

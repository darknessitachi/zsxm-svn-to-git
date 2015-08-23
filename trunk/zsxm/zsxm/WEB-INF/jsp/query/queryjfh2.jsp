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
     		<s:hidden name="treekey"></s:hidden>
				<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
							<tr>
								  <td class="lefttop"></td>
								  <td width="%"  class="centertop"></td>
								  <td class="righttop"></td>
						    </tr>
							<tr>
									<td height="26" class="leftcenter">&nbsp;</td>
								    <td class="center" >
								    	<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D('zsxm_button','xtdlb','bottom');loadTree();"/>
								    	<s:hidden name="jfhdm"></s:hidden>
								    	&nbsp;|&nbsp;
										<select name="where" style="width:80px" onchange="changeW(this.value)">
											<option value="XM">姓名</option>
										</select>
										<span id="sname">
								    		<input type="text" name="name" value=""/>
										</span>
								    	<input type="button" class="but" value="查   询" id="queryBtn" onclick="">
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
			
			<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:320;height:350;overflow:auto;">
				<div id='xtdlbloadimage'>
					<img src="images/skin0/other/upload.gif">数据载入中.....
				</div>
				<div loading='0' id='xtdlbload' style="width:100%;height:345;background-color:#E7EAF7;overflow:auto;">
					
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
					{name : 'SBID'},
					{name : 'XM'}, 
					{name : 'BZ'}, 
		   		    {name : 'SEX'}, 
		   		    {name : 'B_NL'},
		   		    {name : 'dwmc'},  
		   		    {name : 'XL_MC'}, 
		   		    {name : 'ZC_MC'}, 
		   		    {name : 'ZYTC'}, 
					{name : 'ZJZ'}, 
					{name : 'ZZBZ'}, 
					{name : 'GFDD'}, 
					{name : 'FWMJ'}, 
					{name : 'FWZE'}
					/*,
					{name : 'url' }
					*/
				]
	};

	var colsOption = [ 
	 {
		id : 'SBID',
		header : "序号",
		headAlign:"center",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	}, {
		id : 'XM',
		header : "姓名",
		width : 70,
		headAlign:"center",
		align : "left"
		
	}, {
		id : 'SEX',
		header : "性别",
		headAlign:"center",
		width : 50,
		align : "center",
		renderer:function (v){var ss ="男";if(v==2){ss="女";} return ss;}
	},{
		id : 'BIRTYDAY',
		header : "出生日期",
		headAlign:"center",
		width : 80,
		align : "center"
	}, 
	 {
		id : 'DWMC',
		header : "所在单位",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'XL_MC',
		header : "学历/学位",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'ZC_MC',
		header : "职称/职务",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'ZYTC',
		header : "专业特长",
		headAlign:"center",
		align : "left"
	}, {
		id : 'ZJZ',
		header : "专兼职",
		headAlign:"center",
		width : 60,
		align : "center",
		renderer:function (v){var ss ="专";if(v==2){ss="兼";} return ss;}
	}, {
		id : 'ZZBZ',
		header : "资助标准",
		width : 100,
		headAlign:"center",
		align : "right"
	}, {
		id : 'GFDD',
		header : "购房地点",
		width : 100,
		headAlign:"center",
		align : "right"
	}, {
		id : 'FWMJ',
		header : "房屋面积",
		width : 100,
		headAlign:"center",
		align : "right"
	}, {
		id : 'FWZE',
		header : "房屋总额",
		width : 100,
		headAlign:"center",
		align : "right"
	}
	 ];

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
		loadURL : APP_PATH + '/jfhquery!getAjfList.do',
		exportURL : APP_PATH + '/jfhquery!exportAjfList.do',
		exportFileName : "企业信息",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	Event.observe("queryBtn", "click", function() {
		mygrid.query({"name":$("name").value,"where":$("where").value,"jfhdm":$('jfhdm').value});
	}, true);		
	
	function dofilter(){
		mygrid.showDialog("filter");
	}
	
	function refresh(){
		mygrid.query({"name":$("name").value,"where":$("where").value,"jfhdm":$('jfhdm').value});
	}
	
	
	var tree;
	function loadTree(){
		if($("xtdlb").loading != 1){
			$('xtdlbload').innerHTML = "";
			tree=new dhtmlXTreeObject("xtdlbload","100%","100%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){setV(id);});
			tree.enableSmartXMLParsing(true);
			tree.setDataMode("json");
			tree.enableCheckBoxes(false);
			tree.loadJSON("jfhwh!getJfhwhTreebyAjf.do",function(){$('xtdlbloadimage').style.display="none";});
			$('xtdlb').loading = 1;			
		}
	}
	function setV(id){
		if(id=='root'){
			$('jfhdm').value='';
		}else{
			$('jfhdm').value=id;
		}
		$('xtdlb').style.display = 'none';
		$('zsxm_button').value = tree.getItemText(id);
	}	
	</script>
</html>

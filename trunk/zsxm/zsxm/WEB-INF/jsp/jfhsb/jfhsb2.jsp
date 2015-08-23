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
								    	字段
										<select name="where" style="width:80px" onchange="changeW(this.value)">
											<option value="a.XM">姓名</option>
											<option value="b.BZ">状态</option>
										</select>
										<span id="sname">
								    		<input type="text" name="name" value=""/>
										</span>
								    	<input type="button" class="but" value="查   询" id="queryBtn" onclick="">
								    	<input type="button" class="but" value="修   改" id="xgBtn" onclick="xgJfh()">
								    	<b><a target=_blank href="jfhsb!doExportExcel.do?treekey=<s:property value="treekey"/>"><font color="red">导出金凤凰经费资助表</font></a></b>
									</td>
							
							   	 <td class="rightcenter"></td>
							</tr>
									
						   <tr>
							    <td class="leftbot"></td>
							    <td class="centerbot"></td>
							    <td class="rightbot"></td>
						   </tr>
					</table>
			
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
	},{
		id : 'BZ',
		header : "状态",
		headAlign:"center",
		width : 80,
		headAlign:"center",
		renderer:function (v){var ss ="待提交";if(v==5){ss="待审核";}else if(v==3){ss="审核通过";}else if(v==4){ss="审核退回"} return ss;}
	},{
		id : 'DWID11',
		header : "审核操作",
		width : 80,
		headAlign:"center",
		align : "center",
		renderer:function (v,r){var ss = "";if(r.BZ==5){ ss= '<a href="javascript:;" onclick="datash('+r.SBID+','+r.DWID+','+r.RYID+');">数据审核</a>';} return ss;}
	}, {
		id : 'XM',
		header : "姓名",
		width : 100,
		headAlign:"center",
		align : "left"
		
	}, {
		id : 'HTYY',
		header : "退回原因",
		headAlign:"center",
		width : 140,
		align : "left",
		renderer:function (v,r){if( parseInt(r.BZ) == 5 || parseInt(r.BZ) ==4 ){return v;}}
		
	}, {
		id : 'SEX',
		header : "性别",
		headAlign:"center",
		width : 60,
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
		width : 180,
		align : "left"
	}, {
		id : 'XL_MC',
		header : "学历/学位",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'ZC_MC',
		header : "职称/职务",
		headAlign:"center",
		width : 150,
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
		align : "center"
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
		loadURL : APP_PATH + '/jfhsb!getList.do?treekey='+$F('treekey'),
		exportURL : APP_PATH + '/jfhsb!exportList.do?treekey='+$F('treekey'),
		exportFileName : "企业信息",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	Event.observe("queryBtn", "click", function() {
		mygrid.query({"name":$F("name"),"where":$F("where")});
	}, true);		
	
	function dofilter(){
		mygrid.showDialog("filter");
	}
	
	function refresh(){
		mygrid.query({"name":$F("name"),"where":$F("where")});
	}
	
	function datash(s,d,r){
		var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		parent.openWin("jfhsb!preDatash2.do?sbid="+s+"&dwid="+d+"&ryid="+r,w,h);
	}
	
	function changeW(v){
		if(v == 'a.XM'){
			$('sname').innerHTML = '<input type="text" name="name" value=""/>';
		}else if(v == 'b.BZ'){
			var str = '<select name="name">';
				str += '<option value="5">待审核</option>';
				str += '<option value="3">审核通过</option>';
			str += "</select>";
			$('sname').innerHTML = str;
		}
	}	
	
	function xgJfh(){
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
		parent.openWin("jfhsb!preDataxg2.do?sbid="+mygrid.getSelectedRecord().SBID+"&dwid="+mygrid.getSelectedRecord().DWID+"&ryid="+mygrid.getSelectedRecord().RYID,w,h);
	}
	</script>
</html>

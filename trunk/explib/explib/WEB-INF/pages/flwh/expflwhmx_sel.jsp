<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:260px;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:30px;}
		    .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		    .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
		</style>
		<script src="Framework/Main.js" type="text/javascript"></script>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
		<link rel="STYLESHEET" type="text/css" href="js/dhtmlxTab/dhtmlxtabbar.css">
		<script type="text/javascript" src="js/dhtmlxTab/dhtmlxtabbar.js"></script>
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
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
	</head>
	<body  style="overflow:auto">
	<s:form name="czrcForm" id="form1" action="expflwh!getRcxxWaitSelect.do" method="post">
		<s:component template="xtitle" theme="app" value='选择专家'></s:component>
		<s:hidden name="fldm"></s:hidden>
		<s:hidden name="winid" id="winid"></s:hidden>
		<div class="butbar" id="butbar">
			<div class="left">		 	
				字段：
				 <select name="where" style="width:130px" onchange="changefield(this.value)">
		    		<option value="rcname#text">专家名</option>
		    		<option value="workunit#text">所在单位</option>
		    		<option value="sex#select#4">性别</option>
		    		<option value="zjno#text">证件号码</option>
		    		<option value="xl#select#2">学历</option>
		    		<option value="xw#select#3">学位</option>
		    		<option value="zc#select#5">职称</option>
		    		<option value="birthday#date">出日期小于</option>
		    		<option value="csly#select#14">从事领域</option>
		    		<option value="sxly#select#14">所学领域</option>
		    		<option value="exp_jszc&ly#select#14">熟悉领域</option>
		    		<option value="info#text">个人简介</option>
		    	</select>
		    	
				<span id="xmdis">
		    		<s:textfield name="name" id="name" cssStyle="width:100px"></s:textfield>
		    	</span>
		    	&nbsp;
		    	
				<input type="button" class="button"  value="查   询" onclick="query()"/>
				<input type="button" class="button"  id="plcx_button" value="批量选择" onclick="O_D('plcx_button','plcxdiv','bottom');"/>
				<input type="button" class="button" value="确定选择" onclick="doSave();"/>
				<input type="button" name="resetBtn" class="button" value="关  闭" onclick="closeWin();"/>
			</div>			
		</div>
		<div id="mygrid_container" style="width:100%;" ></div>
		
		<div id="seldiv" style="width:100%;height:80px;border:2px solid #A7C5E2;"></div>
	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id='xtdlbloadimage'>
			<img src="images/skin0/other/upload.gif">数据载入中.....
		</div>
		<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
			
		</div>
		
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>	
	
	<div  id='plcxdiv' class="fsg_nr" style="display:none;width:500;height:400;">
		<div loading='0' id="treesel" style="width:495;height:360;overflow:auto;">
			<font color=red><br><b>&nbsp;&nbsp;&nbsp;直接粘帖姓名或身份证:<b></font>
			<s:textarea rows="20" cols="60" name="plinfo"></s:textarea>
		</div>
		<div class="footer" style="background:#f5f5f5" style="width:495;height:30;">
			<input class="button3" type="button" value="确  定" onclick="doplcxtrue();"/>
			&nbsp&nbsp&nbsp&nbsp
		</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>

<div style="display:none">
 <table id="myHead" style="display:none">  
 <tr>  
     <td rowspan="2" columnId='no'>学号</td>  
    <td colspan="3">基本信息</td>  
     <td colspan="3">成绩</td>  
     <td  >其他</td>  
 </tr>  
 <tr>  
    <td columnId='name'>姓名</td>  
    <td columnId='age'>年龄</td>  
     <td columnId='gender'>性别</td>  
     <td columnId='english'>英语</td>  
     <td columnId='math'>数学</td>  
     <td columnId='total'>总成绩</td>  
     <td columnId='detail'>详细信息</td>  
 </tr>  
</table> 
</div>
	</s:form> 
</body>
<script type="text/javascript">
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'RCID'}, 
		   		    {name : 'RCNAME'}, 
		   		    {name : 'SEX_MC'}, 
		   		    {name : 'NATION_MC'}, 
		   		    {name : 'ZJLB_MC'}, 
		   		    {name : 'ZJNO'}, 
		   		    {name : 'RCLB_MC'}, 
					{name : 'XL_MC'}, 
					{name : 'XW_MC'}, 
					{name : 'ZC_MC'}, 
					{name : 'ZW'}, 
					{name : 'XXZY_MC'}, 
					{name : 'CSZY_MC'}
					/*,
					{name : 'url' }
					*/
					]
	};

	var colsOption = [ {
		id : 'RCID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	}, {
		id : 'RCNAME',
		header : "姓名",
		headAlign:"center",
		width : 80,
		align : "left",
		renderer : function (v,r){ return '<a href="expwh!preView.do?rcid='+r.RCID+'&opttype=3" target=_blank>'+v+'</a>';}
	}, {
		id : 'SEX_MC',
		header : "性别",
		headAlign:"center",
		width : 40,
		align : "center"
		
	}, {
		id : 'WORKUNIT',
		header : "所在单位",
		headAlign:"center",
		width : 120,
		align : "left"
		
	}, {
		id : 'SZDQ_MC',
		header : "所在地区",
		width : 80,
		align : "left"
		
	}, {
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
		id : 'XZZW',
		header : "职务",
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
	
	 ];

	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 125) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		selectRowByCheck : true, 
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : true,
		dataset : dsOption,
		columns : colsOption,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/expflwh!getRcxxWaitSelected.do',
		exportURL : APP_PATH + '/expflwh!exportList.do',
		exportFileName : "列表",
		onClickCell : function(value,record,cell,row,rowNo,columnObj,grid,event) {
			if(rowNo == 0){
				if($('t'+value)){
					if($('t'+value).checked){
						$('t'+value).checked = false;
						$('s'+value).style.display = 'none';
					}else{
						$('t'+value).checked = true;
						$('s'+value).style.display = '';
					}
				}else{
					$('seldiv').innerHTML += "<span id='s"+value+"'><input type='checkbox' id='t"+value+"' name=rcid checked value ='"+value+"'>"+record.RCNAME+"&nbsp|&nbsp</span>";
				}
			}
		},
		autoLoad : false
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);
	
	function query(){
		mygrid.query({
			"name":$("name").value,
			"where":$("where").value,
			"fldm":$("fldm").value,
			"plvalue":$("plinfo").value
		});
	}
	
	function doSave(){
		if(COM.checkbox('rcid').length == 0){
			alert('请选择专家！');
			return false;
		}
		var ajax = new AppAjax("expflwh!selectRcflmx.do",save_Back).submitForm("czrcForm");
	}
	
	function save_Back(data){
		if(data.message.code >0){
			window.parent.refreshmm();
			closeWin();
		}else{
			alert(data.message.text);
		}
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
		$('sel_button').value = tree.getItemText(id);
	}

	function changefield(v){
		$('xtdlb').loading = 0;
		var ss = v.split('#');
		if(ss[1] == 'text'){
			$('xmdis').innerHTML = '<s:textfield name="name" id="name" cssStyle="width:100px"></s:textfield>';
		}else if(ss[1]=='select'){
			$('xmdis').innerHTML = '<INPUT id="sel_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'sel_button\',\'xtdlb\',\'bottom\');loadTree(\''+ss[2]+'\');"/><input type="hidden" name="name" id="name"/>';
		}else if(ss[1] == 'date'){
			$('xmdis').innerHTML = '<input type="text" class="Wdate" name="name" id="name" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,\'%Y-%M-%D\')" MINDATE="1930-01-01" readonly/>';
		}
	}
	
	function doplcxtrue(){
		$('plcxdiv').style.display = 'none';
		 query();
	}
	
</script>
</html>

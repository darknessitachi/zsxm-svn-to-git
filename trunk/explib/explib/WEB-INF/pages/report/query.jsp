<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script type="text/javascript">
			JSLoader.loadStyleSheet("grid/gt_grid.css");
			JSLoader.loadStyleSheet("grid/skin/default/skinstyle.css");
			JSLoader.loadStyleSheet("grid/skin/china/skinstyle.css");
			JSLoader.loadStyleSheet("grid/skin/vista/skinstyle.css");
			JSLoader.loadStyleSheet("grid/skin/mac/skinstyle.css");
			JSLoader.loadStyleSheet("styles/app/stars.css");
			JSLoader.loadStyleSheet("js/dhtmlxTree/dhtmlxtree.css");
			JSLoader.loadJavaScript("Framework/Main.js");
			JSLoader.loadJavaScript("js/app/stars.js");
			JSLoader.loadJavaScript("js/comm.js");
			JSLoader.loadJavaScript("js/dhtmlxTree/dhtmlxcommon.js");
			JSLoader.loadJavaScript("js/dhtmlxTree/dhtmlxtree.js");
			JSLoader.loadJavaScript("js/dhtmlxTree/dhtmlxtree_json.js");
			JSLoader.loadJavaScript("js/dhtmlxTree/dhtmlXTreeExtend.js");
			JSLoader.loadJavaScript("grid/gt_msg_cn.js");
			JSLoader.loadJavaScript("grid/gt_grid_all.js");
			JSLoader.loadJavaScript("js/My97DatePicker/WdatePicker.js");
			JSLoader.loadJavaScript("js/app/search.js");
		</script>
<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
			  .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			   }
			  .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:4px solid #98B1C8; }
</style>		
	</head>
	
	<body style="margin: 0px; margin: 2px;">
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
					    	<select name="where" style="width:130px" onchange="changefield(this.value)">
						    		<option value="rcname#text">专家名</option>
						    		<option value="sex#select#4">性别</option>
						    		<option value="zjno#text">证件号码</option>
						    		<option value="xl#select#2">学历</option>
						    		<option value="xw#select#3">学位</option>
						    		<option value="zc#select#5">职称</option>
						    		<option value="birthday#date">出日期小于</option>
						    		<option value="csly#select#14">从事领域</option>
						    		<option value="sxly#select#14">所学领域</option>
						    		<option value="exp_jszc&ly#select#14">熟悉领域</option>
						    		<option value="gzmc#select#-99">跟踪内容选择</option>
						    		<option value="info#text">个人简介</option>
						    </select>
					    	<span id="xmdis">
					    		<s:textfield name="name" cssStyle="width:100px"></s:textfield>
					    	</span>
					    	<input type="button" id="queryBtn" class="but" value="查   询" id="b_sendmessage">
					    	<input type="button" id="queryBtn1" class="but" value="预   览" id="b_sendmessage" onclick="doquery()">
					    	<input type="button" class="but" value="高级查询" id="" onclick="SQFun.search('searchDiv');">
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

	<div loading='0' id='searchDiv' class="fsg_nr" style="display:none;width:700px;height:250;overflow:auto;">
		<div id="searchDivbutton" style="background:#4682b4;width:100%;height:25;text-align:right">
			<span style="float:left;margin-top:5px;margin-left:20px"><font color="white"><b>高&nbsp;级&nbsp;查&nbsp;询</b></font></span>
			<input type='button' value='关  闭' onclick="javascript:$('searchDiv').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		
		<div class="footer" style="background:#f5f5f5" style="width:100%;height:20;text-align:right">
				<span style="float:left;margin-left:20px">
					<input class="button3" type="button" value="新增行" onclick="SQFun.appendRow();"/>
					<input class="button3" type="button" value="删除行" onclick="SQFun.deleteRow();"/>
					&nbsp;
					<input class="button3" type="button" value="清除条件" onclick="SQFun.destory();"/>
				</span>
				<input class="button3" type="button" value="确定查询" onclick="javascript:queryValue();$('searchDiv').style.display='none';"/>
			&nbsp&nbsp&nbsp&nbsp
		</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
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
		align : "left",
		renderer : function (v,r){ return '<a href="expwh!preView.do?rcid='+r.RCID+'&opttype=3" target=_blank>'+v+'</a>';}
	}, {
		id : 'SEX_MC',
		header : "性别",
		width : 40,
		align : "center"
		
	}, {
		id : 'SZDQ_MC',
		header : "所在地区",
		width : 80,
		align : "center"
		
	}, {
		id : 'PQCS_',
		header : "聘请次数",
		width : 60,
		align : "center"
		
	}, {
		id : 'ZTPJ_',
		header : "总体评价",
		width : 60,
		align : "center"
		
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
	
	var Stars1p ,Stars2p;
	Event.observe(window, "load", function() {
		mygrid.render();
		
	}, true);

	Event.observe("queryBtn", "click", function() {
		mygrid.query({
		"name":$("name").value,
		"where":$("where").value,
		"sql":SQFun.getSQL()
		});
	}, true);
	
	function queryValue(){
		mygrid.query({
		"name":$("name").value,
		"where":$("where").value,
		"sql":SQFun.getSQL()
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
	
	function queryTj(){
		if($('queryDiv').style.display == ''){
			$('queryDiv').style.display = 'none';
		}else{
			$('queryDiv').style.display = '';
		}
		
	}		
	
	
</script>
</html>


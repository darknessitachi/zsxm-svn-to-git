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
			.selectBut2 {
							WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
						}
			.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:4px solid #98B1C8; }	
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
						    		<option value="gzmc#select#-99">跟踪内容选择</option>
						    		<option value="info#text">个人简介</option>
						    		
						    	</select>
						    	
						    	<span id="xmdis">
						    		<s:textfield name="name" id="name" cssStyle="width:100px"></s:textfield>
						    	</span>
						    	
						    	<select name="name2" id="name2" onchange="queryValue();">
						    		<option value="">全部</option>
						    		<option value="0">未确认</option>
						    		<option value="1">已确认</option>
						    	</select>
						    	
						    	<input type="button" class="but" value="查   询" onclick="queryValue();">
						    	<input type="button" class="but" value="修   改" id="b_sendmessage" onclick="reprow()">
						    	<input type="button" class="but" value="删   除" id="b_sendmessaged" onclick="deldata()">
						    	
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
	<script>
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'RCID'},
					{name : 'BGBS'},
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
					{name : 'CSZY_MC'},
					{name : 'ZGSHBZ'},
					{name : 'ROLEDM'},
					{name : 'ZGSHSTATUS'},
					{name : 'BMTYPE'},
					{name : 'RCBZ'}
					/*,
					{name : 'url' }
					*/
					]
	};

	var colsOption = [ 
	{
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
		id : 'ISCLBZ',
		header : "状态",
		headAlign:"center",
		width : 80,
		align : "center",
		renderer : function (v,r){ if(v==1){return "已确认"}else{return "<font color=red>未确认</font>"};}
	}, {
		id : 'OPTION__',
		header : "操作",
		headAlign:"center",
		width : 60,
		align : "center",
		renderer : function (v,r){ if(r.ISCLBZ==0){return "<a href='javascript:;' onclick=qropt('"+r.RCID+"');>确认</a>"};}
	},
	{
		id : 'WORKUNIT',
		header : "工作单位",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'SEX_MC',
		header : "性别",
		width : 40,
		headAlign:"center",
		align : "center"
		
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
		id : 'SZDQ_MC',
		header : "所在地区",
		headAlign:"center",
		width : 150,
		align : "center"
		
	}, {
		id : 'PQCS_',
		header : "聘请次数",
		headAlign:"center",
		width : 60,
		align : "center"
		
	}, {
		id : 'ZTPJ_',
		header : "总体评价",
		headAlign:"center",
		width : 60,
		align : "center"
		
	},
	{
		id : 'DWDQ_MC',
		header : "单位所在地",
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
		loadURL : APP_PATH + '/expwh!getList.do',
		exportURL : APP_PATH + '/expwh!exportList.do',
		exportFileName : "专家信息",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);
	var Stars1p ,Stars2p;
	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	function queryValue(){
		mygrid.query({
			"name":$("name").value,
			"where":$("where").value,
			"name2":$("name2").value,
			"sql":SQFun.getSQL()
		});
	}
	function dofilter(){
		mygrid.showDialog("filter");
	}
	
	var diag = null;
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
			var h = document.body.clientHeight;
			var w = getSize().w;
			if(w > 960){
				w = w-50;
			}
			if(w < 850){
				w = w + 50;
			}
			diag = new Dialog("expwindows");
			diag.Title = '专家信息修改';
			diag.Width = w;
			diag.Height = getSize().h;
			diag.URL = "exp!preUpdate.do?rcid="+mygrid.getSelectedRecord().RCID+"&opttype=3"+"&winid=expwindows";
			diag.show();
		}
	}
	
	function refresh(){
		mygrid.query({
			"name":$("name").value,
			"where":$("where").value,
			"name2":$("name2").value,
			"sql":SQFun.getSQL()
		});
	}
	
	function deldata(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要删除的数据！");
			return false;
		}
		var r = new Array();
		for(var i=0;i<len;i++){
			r.push(mygrid.getSelectedRecords()[i].RCID);
		}
		if(window.confirm('您确定要删除'+r.length+' 条数据!')){
			var ajax = new AppAjax("expwh!deleteExp.do?rcid="+r,del_back).submit();
		}
	}
	
	function del_back(data){
		if(data.message.code >0){
			alert(data.message.text);
			refresh();
		}else{
			alert(data.message.text);
		}
	}
	
	function shrow(){
		 var len = mygrid.getSelectedRecords().length;
		
		 if(len > 1){
			alert("审批只能选择一条记录!");
			return false;
		 }
		 if(len < 1){
			alert('请选择需要审核的记录!');
			return false;
		 }
		 
		 if(len == 1){
		 	var ajax = new AppAjax("expwh!preShrcxxWithCheck.do?rcid="+mygrid.getSelectedRecord().RCID,check_Back).submit();
		 }
	}
	
	function check_Back(data){
		var h = document.body.clientHeight;
		
		if(data != null){
			if(data.message.code > 0){
				openWin("expwh!preShrcxx.do?rcid="+mygrid.getSelectedRecord().RCID+"&opttype=3","850",h);
		 	}else{
				alert(data.message.text);
			}
		}
	}
	
	function ylrow(){
		 var h = document.body.clientHeight;
		 var len = mygrid.getSelectedRecords().length;
		 if(len > 1){
			alert("预览只能选择一条记录!");
			return false;
		 }
		 if(len < 1){
			alert('请选择需要预览的记录!');
			return false;
		 }
		 window.open("expwh!preView.do?rcid="+mygrid.getSelectedRecord().RCID+"&opttype=3");
	}
	
	
	function save_Back(data,type){
		if(data.message.code >0){
			alert(data.message.text);
			refresh();
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
		}else if(ss[1] == 'custom'){
			var s = new StringBuffer();
			s.append('<select style="width:130px" name="name" id="name">');
			var d = ss[2].split(',');
			for(var i=0;i<d.length;i++){
				var dd = d[i];
				s.append('<option value="'+dd.split('&')[0]+'">'+dd.split('&')[1]+'</option>');	
			}
			s.append('</select>');
			$('xmdis').innerHTML = s.toString()+'&nbsp;';
		}
	}
	
	function queryTj(){
		if($('queryDiv').style.display == ''){
			$('queryDiv').style.display = 'none';
		}else{
			$('queryDiv').style.display = '';
		}
		
	}
	
	function qropt(r){
		/**
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要确认的数据！");
			return false;
		}
		var r = new Array();
		for(var i=0;i<len;i++){
			r.push(mygrid.getSelectedRecords()[i].RCID);
		}
		if(window.confirm('您确定要确认'+r.length+' 条数据!')){
			var ajax = new AppAjax("expwh!doQropt.do?rcid="+r,save_Back).submit();
		}**/
		var ajax = new AppAjax("expwh!doQropt.do?rcid="+r,save_Back).submit();
	}
	
	function Hsearch(){
		diag = new Dialog("searchwindows");
		diag.Title = "高级查询";
		diag.Width = 800;
		diag.Height = 230;
		diag.ShowMessageRow=true;
		diag.MessageTitle = "自定义查询";
		diag.Message = "请自定义你需要查询的信息";
		diag.URL = "search.do?winid=searchwindows";
		diag.show();
	}
	
	function selTrue(){
			$('yjlbd').style.display = 'none';
	}	
	
	</script>
</html>

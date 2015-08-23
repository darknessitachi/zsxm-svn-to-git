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
		.tree {float: left;overflow:auto;width:100%;height:100px;border:1px solid #A7C5E2; }
    	.ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px;}
	
</style>		
	</head>

	<body style="margin: 0px; overflow: hidden; margin: 2px;">
		<s:form name="spForm" action="" method="post">
     			<s:hidden name="type"></s:hidden>
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
								    	<input type="button" class="but" value="查   询" id="queryBtn" onclick="">
								    	<input type="button" class="but" value="新建跟踪" id="b_sendmessage" onclick="newGz()">
								    	<input type="button" class="but" value="修改跟踪" id="b_sendmessage" onclick="moGz()">
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
				
				<div id="seldiv" style="width:100%;height:80px;border:2px solid #A7C5E2;">
				</div>

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
	var diag = null;
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [
					{name : 'RCID'},	
					{name : 'GZ'},
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
		id : 'RCID',
		header : "序号",
		width : 20,
		align : "center",
		isCheckColumn : true,
		filterable : false
	},
	 {
		id : 'GZ',
		header : "跟踪",
		width : 40,
		align : "center",
		renderer : function (v){if(v!="0"){return '<img title="已跟踪" src=images/skin0/other/gz.jpg />'}}
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
		width : 40,
		align : "center"
		
	},  {
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
		height : (getSize().h - 135) + "px",
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
		loadURL : APP_PATH + '/expwh!getList.do',
		exportURL : APP_PATH + '/expwh!exportList.do',
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
					$('seldiv').innerHTML += "<span id='s"+value+"'><input type='checkbox' id='t"+value+"' name=dmkey checked value ='"+value+"'>"+record.RCNAME+"&nbsp|&nbsp</span>";
				}
			}
		},
		autoLoad : true
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
	function refresh(){
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
			

	function newGz(){
		var ch = COM.checkbox('dmkey');
		var len = ch.length;
		if(len < 1){
			alert('请选择需要跟踪的专家记录!');
			return false;
		}
		var r = new Array();
		for(var i=0;i<len;i++){
			r.push(ch[i].v);
		}
		if($('type').value == 1){
			diag = new Dialog("gzwhwindows");
			diag.Title = "新建";
			diag.Width = 640;
			diag.Height = 405;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "新建跟踪";
			diag.Message = "新建跟踪后可以及时了解专家的最新动态";
			diag.URL = "expgz!preGzI.do?rcid="+r+"&winid=gzwhwindows";
			diag.show();
		}else if($('type').value == 2){
			diag = new Dialog("gzwhwindows");
			diag.Title = "新建";
			diag.Width = 640;
			diag.Height = 405;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "新建跟踪";
			diag.Message = "新建跟踪后可以及时了解专家的最新动态";
			diag.URL = "expgz!preGzI.do?rcid="+r+"&winid=gzwhwindows";
			diag.show();
		}
	}

	function dofilter(){
		mygrid.showDialog("filter");
	}
		
	function moGz(){
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
			
			if($('type').value == 1){
				diag = new Dialog("gzwhwindows");
				diag.Title = "跟踪";
				diag.Width = 800;
				diag.Height = 465;
				diag.ShowMessageRow=true;
				diag.MessageTitle = "新建跟踪";
				diag.Message = "新建跟踪后可以及时了解专家的最新动态";
				diag.URL = "expgz!preGzU.do?rcid="+mygrid.getSelectedRecord().RCID+"&winid=gzwhwindows";
				diag.show();
			}else if($('type').value == 2){
				diag = new Dialog("gzwhwindows");
				diag.Title = "跟踪";
				diag.Width = 800;
				diag.Height = 465;
				diag.ShowMessageRow=true;
				diag.MessageTitle = "新建跟踪";
				diag.Message = "新建跟踪后可以及时了解专家的最新动态";
				diag.URL = "expgz!preGzU.do?rcid="+mygrid.getSelectedRecord().RCID+"&winid=gzwhwindows";
				diag.show();
			}
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
	
	function selected(id,mc,isc){
		if(isc){
			if($('sel'+id)){
				$('sel'+id).checked=true;
			}else{
				$('tdselect').update($('tdselect').innerHTML+'<input type="checkbox" name="dmkey" checked id="sel'+id+'" value="'+id+'">'+mc+'&nbsp&nbsp|&nbsp&nbsp');
			}
		}else{
			if($('sel'+id)){
				$('sel'+id).checked=false;
			}
		}
		
	}
	function queryTj(){
		if($('queryDiv').style.display == ''){
			$('queryDiv').style.display = 'none';
		}else{
			$('queryDiv').style.display = '';
		}
		
	}
	
	function refresh(){
		$('seldiv').innerHTML = "";
		mygrid.query();
	}
	
		
	</script>
</html>

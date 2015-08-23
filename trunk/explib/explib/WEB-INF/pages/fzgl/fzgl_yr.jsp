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
		<script src="Framework/Main.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
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
						    		<option value="rcname">姓名</option>
						    		<option value="sex">性别</option>
						    		<option value="nation">国籍</option>
						    		<option value="zgbm">主管部门</option>
						    		<option value="xl">学历</option>
						    		<option value="xw">学位</option>
						    		<option value="status_">状态</option>
						    	</select>
						    	<span id="xmdis">
						    		<s:textfield name="name" id="name" cssStyle="width:100px"></s:textfield>
						    	</span>
						    	<input type="button" class="but" value="查   询" id="queryBtn" onclick="">
						    	<input type="button" class="but" value="引入数据" id="" onclick="preYr()">
						    	<input type="button" class="but" value="数据同步" id="b_sendmessaged" onclick="datatb()">
						    	<input type="button" class="but" value="高级查询" id="" onclick="queryTj();">
							</td>
					
					   	 <td class="rightcenter"></td>
							</tr>
									
						   <tr>
							    <td class="leftbot"></td>
							    <td class="centerbot"></td>
							    <td class="rightbot"></td>
						   </tr>
					</table>
					
				<div  class="fxtableContainer" id="queryDiv" style="float:left;width:300px;border: 1px solid #C1DAD7;display:none" >
				<table class="fxtable" align="center" cellpadding="0" cellspacing="0">
					
					<tr>
						<td colspan=4 style="text-align:center;background:#add8e6;font:bold 12px 'lucida Grande',Verdana;">高级查询</td>
					</tr>
					
					<tr>
						<td class="bt">学历</td>
						<td  colspan=3><s:select name="xl" list="xtdict2" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">学位</td>
						<td  colspan=3><s:select name="xw" list="xtdict3" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">专业技术职务</td>
						<td  colspan=3><s:select name="zc" list="xtdict5" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">专业技术职务等级</td>
						<td  colspan=3><s:select name="zw" list="xtdict23" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">主管部门</td>
						<td  colspan=3><s:select name="zgbm" list="xtdict21" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">单位性质</td>
						<td  colspan=3><s:select name="dwxz" list="xtdict12" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					
					<tr>
						<td class="bt">年龄区间</td>
						<td  colspan=3><input type="text" name="strnl" value="" style="width:50px" id="strnl" onblur="COM.isNumChar(this.id)"/>&nbsp&nbsp至&nbsp&nbsp<input type="text" name="endnl" value="" style="width:50px" id="endnl" onblur="COM.isNumChar(this.id)"/></td>
					</tr>
					<tr>
						<td class="bt">资助项目类别</td>
						<td  colspan=3><s:select name="zzxmlb" list="xtdict28" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>					
					</tr>
					
					<tr>
						<td colspan=4 style="text-align:center;background:#add8e6;font:bold 12px 'lucida Grande',Verdana;">
							<input type="button"  class="button" value="查   询" onclick="queryValue()"/>
							&nbsp&nbsp&nbsp
							<input type="button"  class="button" value="隐   藏" onclick="queryTj();"/>
						</td>
					</tr>
				</table>
			</div>	
		<div id="mygrid_container" style="width:100%;" ></div>
	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id='xtdlbloadimage'>
			<img src="images/skin0/other/upload.gif">数据载入中.....
		</div>
		<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
			
		</div>
		
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>	
	
	<div id='yjlbd' class="fsg_nr" style="display:none;width:300;height:190;">	
	<div loading='0' id='yjlbdd' style="width:300;height:150;background-color:#E7EAF7;overflow:auto;">
		<s:iterator value="xtdict25">
			<input type="checkbox" name="bpzjqk" id='yjlb<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><span id='ch<s:property value="dictbh"/>'><s:property value="dictname"/></span><br>
		</s:iterator>
	</div>
	
	<div class="footer" style="background:#f5f5f5" style="width:300;height:20;text-align:right">
			<input class="button3" type="button" value="确  定" onclick="selTrue();"/>
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
		align : "left"
	}, {
		id : 'SEX_MC',
		header : "性别",
		width : 40,
		align : "center"
		
	}, {
		id : 'NATION_MC',
		header : "国籍",
		headAlign:"center",
		width : 100,
		align : "left"
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
		id : 'ZGBM_MC',
		header : "主管部门",
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
		loadURL : APP_PATH + '/fzgl!getList.do',
		exportURL : APP_PATH + '/fzgl!exportList.do',
		exportFileName : "专家信息",
		autoLoad : true
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	Event.observe("queryBtn", "click", function() {
		mygrid.query({
			"name":$("name").value,
			"where":$("where").value,
			"xl":$("xl").value,
			"xw":$("xw").value,
			"zc":$("zc").value,
			"zw":$("zw").value,
			"zgbm":$("zgbm").value,
			"dwxz":$("dwxz").value,
			"strnl":$("strnl").value,
			"endnl":$("endnl").value,
			"zzxmlb":$("zzxmlb").value
		});
	}, true);
	
	function queryValue(){
		mygrid.query({
			"name":$("name").value,
			"where":$("where").value,
			"xl":$("xl").value,
			"xw":$("xw").value,
			"zc":$("zc").value,
			"zw":$("zw").value,
			"zgbm":$("zgbm").value,
			"dwxz":$("dwxz").value,
			"strnl":$("strnl").value,
			"endnl":$("endnl").value,
			"zzxmlb":$("zzxmlb").value
		});
	}
	function dofilter(){
		mygrid.showDialog("filter");
	}

	
	var diag = null;
	
	function preYr(){
		diag = new Dialog("yrwindows");
		diag.Title = '引入';
		diag.Width = 400;
		diag.Height = 100;
		diag.ShowMessageRow=true;
		diag.MessageTitle = "引入数据";
		diag.Message = "请选择相应的数库";
		diag.URL = "fzgl!preyrdata.do?opttype=3"+"&winid=yrwindows";
		diag.show();
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
			var h = document.body.clientHeight;
			diag = new Dialog("expwindows");
			diag.Title = '专家信息修改';
			diag.Width = 980;
			diag.Height = 580;
			diag.URL = "exp!preUpdate.do?rcid="+mygrid.getSelectedRecord().RCID+"&opttype=3"+"&winid=expwindows";
			diag.show();
		}
	}
	
	function refresh(){
		mygrid.query({
			"name":$("name").value,
			"where":$("where").value,
			"xl":$("xl").value,
			"xw":$("xw").value,
			"zc":$("zc").value,
			"zw":$("zw").value,
			"zgbm":$("zgbm").value,
			"dwxz":$("dwxz").value,
			"strnl":$("strnl").value,
			"endnl":$("endnl").value,
			"zzxmlb":$("zzxmlb").value
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
		$('zsxm_button').value = tree.getItemText(id);
	}

	function changefield(v){
		$('xtdlb').loading = 0;
		if(v != 'rcname' && v!='status_'){
			var dm = '';
			if(v=='sex'){
				dm=4;
			}else if(v=='nation'){
				dm=20;
			}else if(v=='rclb'){
				dm='rclb';
			}else if(v=='zgbm'){
				dm=21;
			}else if(v=='dwdq'){
				dm=22;
			}else if(v=='xl'){
				dm=2;
			}else if(v=='xw'){
				dm=3;
			}
			$('xmdis').innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(\''+dm+'\');"/><input type="hidden" name="name"/>';
		}else if(v=='status_'){
			var s = '<select style="width:120px" name="name">';
			s+='<option value=0>入库</option>';
			s+='<option value=1>等待主管审批</option>';
			s+='<option value=2>等待市属部门审批</option>';
			s+='<option value=3>主管退回修改</option>';
			s+='<option value=4>市属部门退回修改</option>';
			s+= '</select>';
			$('xmdis').innerHTML = s;
		}else{
			$('xmdis').innerHTML = '<s:textfield name="name" cssStyle="width:100px"></s:textfield>';
		}
	}
	
	function queryTj(){
		if($('queryDiv').style.display == ''){
			$('queryDiv').style.display = 'none';
		}else{
			$('queryDiv').style.display = '';
		}
		
	}	
	function selTrue(){
		$('yjlbd').style.display = 'none';
	}
	
	var diag = null;
	function datatb(){
		var len = mygrid.getSelectedRecords().length;
		if(len > 1){
			alert("同步只能选择一条记录!");
			return false;
		}
		if(len < 1){
			alert('请选择需要同步的记录!');
			return false;
		}
		if(len==1){
			var h = document.body.clientHeight;
			diag = new Dialog("exptbwindows");
			diag.Title = '专家信息同步';
			diag.Width = 980;
			diag.Height = getSize().h-20 ;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "专家信息同步";
			diag.Message = "如果专家信息中有需要同步至现有信息中的，请选中前面的复选框，如果专家信息需要修改后同步的，可以框中直接修改信息!";
			diag.URL = "exptb!preExptb.do?tbtype=WB&rcidtb="+mygrid.getSelectedRecord().RCID+"&sfzh="+mygrid.getSelectedRecord().ZJNO+"&opttype=3"+"&winid=exptbwindows";
			diag.show();
		}
	}	
	</script>
</html>

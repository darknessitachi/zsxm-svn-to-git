<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/gt_grid.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/vista/skinstyle.css"/>" />
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
	
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
								    	<select name="where" style="width:100px">
								    		<option value="xm">姓名</option>
								    	</select>
								    	<span id="xmdis">
								    		<s:textfield name="name" cssStyle="width:100px"></s:textfield>
								    	</span>
								    	<input type="button" class="but" value="查   询" id="queryBtn">
								    	
								    	<input type="button" class="but" value="新   增" id="b_sendmessage" onclick="addrow()">
								    	<input type="button" class="but" value="修   改" id="b_sendmessage" onclick="reprow()">
								    	<input type="button" class="but" value="删   除" id="b_sendmessage" onclick="deldata()">
								    	<input type="button" class="but" value="上报数据" id="b_sendmessage" onclick="sbdata()">
								    	<input type="button" class="but" value="打   印" id="b_sendmessage" onclick="printry()">
								    	<input type="button" class="but" value="高级查询" id="" onclick="dofilter();">
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
		</s:form>
	</body>
	<script>
	
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
		renderer:function (v){var str ="待报";if(v==1){str="<font color=blue>上报(待审)</font>";}else if(v==2){str = "<font color='#a52a2a'>审核通过</font>";}else if(v==3){str = "<font color='red'>审核退回</font>";}else if(v==4){str = "<font color='red'>数据发回</font>";} return str;}
	}, {
		id : 'STATUS',
		header : "人员状态",
		headAlign:"center",
		width : 70,
		align : "left",
		renderer:function (v){var str ="正常"; if(v==2){str="<font color='#a52a2a'>归档</font>";}else if(v==5){str="<font color=red>冻结</font>";}  return str;}
	},{
		id : 'XM',
		header : "姓名",
		headAlign:"center",
		width : 80,
		align : "left"
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
		loadURL : APP_PATH + '/zsdw!getRyxxList.do',
		exportURL : APP_PATH + '/zsdw!exportRyxxList.do',
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
		"where":$F("where")
		});
	}, true);
	
	function queryValue(){
		mygrid.query({
		"name":$F("name"),
		"where":$F("where")
		});
	}
	function dofilter(){
		mygrid.showDialog("filter");
	}
	function addrow(){
		var h = document.body.clientHeight;
		openWin("zsdw!preRyxxI.do?opttype=1","880",h);
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
			
			if(mygrid.getSelectedRecord().SBBZ == 1 ){
				alert('数据审核中，不能修改');
				return false;
			}
			
			
			//后台重新检查一遍
			openWin("zsdw!preRyxxU.do?ryid="+mygrid.getSelectedRecord().RYID+"&opttype=3","880",h);
		}
	}
	
	function refresh(){
		mygrid.query({
		"name":$F("name"),
		"where":$F("where")
		});
	}
	
	function getBpzjqk(){
		var B = RC.checkbox('bpzjqk');
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
	
	function deldata(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要删除的数据！");
			return false;
		}
		if(window.confirm("您确定要删除 "+len+" 条数据！")){
			var r = new Array();
			for(var i=0;i<len;i++){
				r.push(mygrid.getSelectedRecords()[i].RYID);
			}
			var ajax = new AppAjax('zsdw!doRyxxD.do?ryid='+r,function (data){
				if(data != null && data.message.code > 0){
					alert('删除成功');
					refresh();
				}else{
					alert(data.message.text);
				}
			}).submit();
		}
		
		
	}
	
	function sbdata(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要上报的数据！");
			return false;
		}
		if(window.confirm("您确定要上报 "+len+" 条数据！")){
			var r = new Array();
			for(var i=0;i<len;i++){
				r.push(mygrid.getSelectedRecords()[i].RYID);
			}
			var ajax = new AppAjax('zsdw!doRyxxSb.do?ryid='+r,function (data){
				if(data != null && data.message.code > 0){
					alert('上报成功');
					refresh();
				}else{
					alert(data.message.text);
				}
			}).submit();
		}
		
		
	}

	function check_Back(data){
		var h = document.body.clientHeight;
		
		if(data != null){
			if(data.message.code > 0){
				openWin("rcdaxxwh!preShrcxx.do?rcid="+mygrid.getSelectedRecord().RCID+"&opttype=3","850",h);
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
		 window.open("rcdaxxwh!preView.do?rcid="+mygrid.getSelectedRecord().RCID+"&opttype=3");
	}
	
	
	function save_Back(data,type){
		if(data.message.code >0){
			alert(data.message.text);
			refresh();
		}else{
			alert(data.message.text);
		}
	}
	
	
	function printry(){
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert('请选择需要打印的记录!');
			return false;
		 }
		 if(len > 1){
			alert("打印只能选择一条记录!");
			return false;
		 }
		 if(mygrid.getSelectedRecord().SBBZ != 2){
		 	alert('当前信息还未审核通过！不能打印');
		 	return false;
		 }
		 
		 alert('开始打印');
	}
	</script>
</html>

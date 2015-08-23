<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		  <SCRIPT LANGUAGE="Javascript" SRC="js/ajax-dynamic-list.js"></SCRIPT>
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/gt_grid.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/default/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/china/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/vista/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/mac/skinstyle.css"/>" />
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
	</head>
	<body  style="overflow:auto">
	<s:form name="emasForm" id="form1" method="post">
	
		<div class="butbar" id="butbar">
						<div class="left">
								<input type="button"  class="button" id="usersx" value="刷 新" onclick="queryData()">
								<input type="button"  class="button" value="新 增" id="b_message" onclick="edituser(0)">
								<input type="button"  class="button" value="修 改" onclick="edituser(1)">
								<input type="button"  class="button" value="删 除" onclick="deleteUser()">
						</div>
		</div>
	<div id="mygrid_container" style="border:0px solid #cccccc;background-color:#f3f3f3;padding:5px;width:100%;" ></div>
		

	</s:form> 
	
<div id=ab style="font-size:10pt;position: absolute; width: 120; height: 30; background-color: #abd6ff; display: none; left: 11; top: 36"></div>
</body>
<script type="text/javascript">

function deleteUser(){
		var strid = '';
		
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要删除的数据！");
			return false;
		}
		for(var i=0;i<len;i++){
			strid += ','+mygrid.getSelectedRecords()[i].userid;
		}
		if(confirm("确定要删除"+len+"条用户信息吗！"))
  		{
				strid = strid.substr(1);
		
				var ajax = new AppAjax("xtmanager!deleteUser.do",operatecall_Back);
				ajax.add("query.userid",strid);
				ajax.setAsyn(false);
				ajax.submit();
				mygrid.query();
			}
		
		
	} 	
		
function operatecall_Back(data){
	if(data.message != null ){
				if(data.message.code > 0){
					alert("用户删除成功！");
					
				}else{
					alert(data.message.text);
				}
			}
}

function edituser(cztype){
	var strid = '';
	if(cztype == 1){ //修改
		var len = mygrid.getSelectedRecords().length;
		if(len < 1){
			alert("请选择需要删除的数据！");
			return false;
		}
		
		if(len > 1){
				alert('你只能选择一名用户进行修改！');
				return ;
		}
		strid = mygrid.getSelectedRecords()[0].userid;
	}
	openWin("xtmanager!getUserEdit.do?query.userid="+strid,"500","240");
	
}


	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'userid'},
		   		    {name : 'loginname'}, 
		   		    {name : 'cnname'}, 
		   		    {name : 'bm'},
		   		    {name : 'px'},
		   		    {name : 'rolemc'}
		   		   ]
	};

	var colsOption = [{
		id : 'userid',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	},{
		id : 'loginname',
		header : "用户登录名",
		width : getSize().w * 0.1,
		align : "left"
	}, {
		id : 'cnname',
		header : "用户姓名",
		width : getSize().w * 0.15,
		align : "left"
	}, {
		id : 'bm',
		header : "部门及职务",
		width : getSize().w * 0.15,
		align : "left"
	}, {
		id : 'px',
		header : "排序号",
		width : getSize().w * 0.1,
		align : "left"
	}, {
		id : 'rolemc',
		header : "角色名称",
		align : "left",
		width : getSize().w * 0.1
	}, {
		id : 'rolemc',
		header : "菜单权限",
		align : "left",
		width : getSize().w * 0.1,
		renderer:function (v,r){return '<a href="javascript:;" title="点击配置用户权限" onclick="getMenu('+r.userid+')">配置权限</a>';}
	}];

	var gridOption = {
		id : grid_demo_id,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 70) + "px",
		pageSize : 25,
		container : 'mygrid_container',
		showGridMenu : true,
		allowCustomSkin : true,
		allowFreeze : true,
		selectRowByCheck : true, 
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
		loadURL : APP_PATH + '/xtmanager!getAlluser.do',
		exportURL : APP_PATH + '/xtmanager!getExportAlluser.do',
		exportFileName : "列表",
		autoLoad : true,
		showIndexColumn : true,
		parameters:{}
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);


	
    function queryData(){
   	 	mygrid.query();
    }
    
	function getMenu(userId){
       if(!userId) return false;
       openWin("quto!menu.do?userid="+userId,"450","400");
	}
</script>

	
</html>


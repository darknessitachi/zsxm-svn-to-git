<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		
		<script src="Framework/Main.js" type="text/javascript"></script>
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
		<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
					<tr>
					    <td class="lefttop"></td>
					    <td width="%"  class="centertop"></td>
					    <td class="righttop"></td>
				    </tr>
					<tr>
						<td height="26" class="leftcenter">&nbsp;</td>
					    <td class="center" >
					    		
								<input type="button"  class="but" value="新 增" id="b_message" onclick="edituser(0)">
								<input type="button"  class="but" value="修 改" onclick="edituser(1)">
								<input type="button"  class="but" value="删 除" onclick="deleteUser()">
								<input type="button"  class="but" id="usersx" value="刷 新" onclick="queryData()">
					    </td>
					   	<td class="rightcenter"></td>
					</tr>	
				   <tr>
					    <td class="leftbot"></td>
					    <td class="centerbot"></td>
					    <td class="rightbot"></td>
				   </tr>
			</table>

	<div id="mygrid_container" style="border:0px solid #cccccc;background-color:#f3f3f3;padding:0px;width:100%;" ></div>
		

	</s:form> 
	
<div id=ab style="font-size:10pt;position: absolute; width: 120; height: 30; background-color: #abd6ff; display: none; left: 11; top: 36"></div>
</body>
<script type="text/javascript">
var diag = null;
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
		
				var ajax = new AppAjax("xtgl!deleteUser.do",operatecall_Back);
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
    diag = new Dialog("userindexwindows");
	diag.Title = '用户维护';
	diag.ShowMessageRow=true;
	diag.MessageTitle = "用户维护";
	diag.Message = "填写相关的用户信息";
	diag.Width = 450;
	diag.Height = 350;
	diag.URL = "xtgl!getUserEdit.do?query.userid="+strid+"&opttype=3"+"&winid=userindexwindows";
	diag.show();
	
	
}


	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_id = "myGrid1";
	var dsOption = {
		fields :  [ {name : 'userid'},
		   		    {name : 'loginname'}, 
		   		    {name : 'cnname'},
		   		    {name : 'bm'},
		   		    {name : 'zw'}, 
		   		    {name : 'rolemc'},
		   		    {name : 'bmtype'},
		   		    {name : 'bmdm_mc'}
		   		   ]
	};

	var colsOption = [{
		id : 'userid',
		header : "序号",
		headAlign:"center",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	},{
		id : 'loginname',
		header : "用户登录名",
		headAlign:"center",
		width : getSize().w * 0.1,
		align : "left"
	}, {
		id : 'cnname',
		header : "用户姓名",
		headAlign:"center",
		width : getSize().w * 0.15,
		align : "left"
	}, {
		id : 'bmmc',
		header : "部门",
		headAlign:"center",
		align : "left",
		width : getSize().w * 0.15
	}, {
		id : 'zw',
		header : "职务",
		headAlign:"center",
		align : "left",
		width : getSize().w * 0.15
	},{
		id : 'rolemc',
		header : "角色名称",
		headAlign:"center",
		align : "left",
		width : getSize().w * 0.1
	}, {
		id : 'quato',
		header : "菜单权限",
		headAlign:"center",
		align : "center",
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
		loadURL : APP_PATH + '/xtgl!getAlluser.do',
		exportURL : APP_PATH + '/xtgl!getExportAlluser.do',
		exportFileName : "列表",
		autoLoad : true,
		showIndexColumn : true,
		parameters:{}
	};

	var mygrid = new Sigma.Grid(gridOption);

	Event.observe(window, "load", function() {
		mygrid.render();
	}, true);

	function refresh(){
		mygrid.query();
	}
	
    function queryData(){
   	 	mygrid.query();
    }
	function getMenu(userId){
       if(!userId) return false;
        diag = new Dialog("userindexwindows");
		diag.Title = '权限维护';
		diag.ShowMessageRow=true;
		diag.MessageTitle = "权限维护";
		diag.Message = "维护用户权限";
		diag.Width = 450;
		diag.Height = 300;
		diag.URL = "quto!menu.do?userid="+userId+"&opttype=3"+"&winid=userindexwindows";
		diag.show();
      // openWin("quto!menu.do?userid="+userId,"450","400");
	}
</script>

	
</html>


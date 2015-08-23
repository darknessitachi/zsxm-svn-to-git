<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>分类设置</title>	
		<style type="text/css">
			.tree {float: left;overflow:auto;width:220px;border:1px solid #A7C5E2; }
		    .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:30px;}
		    .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		    .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
		</style>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
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
         
	</head>
	<body style="overflow:hidden;margin:2px;">
		<div class="tree" id="treeDiv">
			<div class="ddbox" style="width:100%;margin-bottom:3px;">
				<div>
					<input type="button" class="button3" id="inserxj" value="新增" onclick="add();">	
					<input type="button" class="button3"  id="inserbj" value="修改" onclick="rep();">
					<input type="button" class="button3"  id="inserbj" value="结束" onclick="end();">	
					<input type="button" class="button3"  id="inserbj" value="删除" onclick="del();">
				</div>
			</div>
			<div id="disTree">
			</div>
	     </div>
		<div   id="showTable"  >
				<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
						<tr>
							  <td class="lefttop"></td>
							  <td width="%"  class="centertop"></td>
							  <td class="righttop"></td>
					    </tr>
						<tr>
								<td height="26" class="leftcenter">&nbsp;</td>
							    <td class="center" >
							    	<select name="where" style="width:100">
							    		<option value="qymc">单位名称</option>
							    	</select>
							    	<s:textfield name="name" cssStyle="width:100"></s:textfield>
							    	<input type="button" class="button3" value="查    询" id="queryBtn" onclick="queryDw()">
							    	<input type="button" class="button3" value="高级查询" id="" onclick="dofilter();">
							    	<input type="button" class="button3" value="选择单位" id="queryBtn" onclick="seldw()">
							    	<input type="button" class="button3" value="删除单位" id="b_sendmessage" onclick="deldw()">
							    </td>
						
						   	 <td class="rightcenter"></td>
						</tr>
								
					   <tr>
						    <td class="leftbot"></td>
						    <td class="centerbot"></td>
						    <td class="rightbot"></td>
					   </tr>
				</table>
				<div id="mygrid_container_1" style="display:none;width:100%;" ></div>
				<div id="mygrid_container_2" style="display:none;width:100%;" ></div>
				<div id="mygrid_container_3" style="display:none;width:100%;" ></div>
		</div>
	
	</body>
<script>

  

	var treekey = "";
	setpos();
	function setpos()
	{
		$("treeDiv").style.height=(getSize().h - 20)+"px" ;
		$("conTable").style.width=getSize().w-230+"px";
		$("mygrid_container_1").style.width=getSize().w-230+"px";
		$("mygrid_container_1").style.height=(getSize().h - 40)+"px" ;
		
		$("mygrid_container_2").style.width=getSize().w-230+"px";
		$("mygrid_container_2").style.height=(getSize().h - 40)+"px" ;
		
		$("mygrid_container_3").style.width=getSize().w-230+"px";
		$("mygrid_container_3").style.height=(getSize().h - 40)+"px" ;
	}
	

	/** 用于DhtmlxTree */
	var tree;
	loadTree();
	function loadTree(){
		$('disTree').innerHTML = "";
		tree=new dhtmlXTreeObject("disTree","100%","90%",0);
		tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
		tree.setOnClickHandler(function(id){setTree(id);});
		tree.enableSmartXMLParsing(true);
		tree.setDataMode("json");
		tree.enableCheckBoxes(false);
		tree.loadJSON("dwsb!getDwsbTree.do",function(){tree.openAllItems('root');});
		
	}
	
	
	var APP_PATH = '<c:url value="/"/>';
	var grid_demo_zrqy = "myGrid_zrqy";
	var dsOption_zrqy = {
		fields :  [ 
					{name : 'SBDM'},
					{name : 'DWDM'},
					{name : 'DWID'},
					{name : 'DWZT_MC'}, 
					{name : 'DWLX_MC'}, 
					{name : 'DWPASSWORD'},
					{name : 'LXR'},
					{name : 'LXRDH'},
					{name : 'CZ'},
					{name : 'SBDM_MC'},
					{name : 'ZCZJ'},  
					{name : 'QYMC'}, 
		   		    {name : 'QYDJZCLX_MC'}, 
		   		    {name : 'ZRQYRS'},
					{name : 'CDGLGJDFXMS'},
					{name : 'HYLB'},
					{name : 'BSRS'},
					{name : 'GJJXMS'},
					{name : 'GXJSQY'},
					{name : 'SSRS'},
					{name : 'SJXMS'},
					{name : 'SFSSQY'},
					{name : 'YJSJYSXLS'},
					{name : 'SSJXMS'},
					{name : 'YFJGS'},
					{name : 'BKXLS'},
					{name : 'QJXMS'},
					{name : 'GXJSCPS'},
					{name : 'DZXLS'},
					{name : 'GLGJDFDZJF'},
					{name : 'GJJXMJF'},
					{name : 'SJXMJF'},
					{name : 'SSJXMJF'},
					{name : 'QJXMJF'},
					{name : 'CDGLQYXMS'},
					{name : 'GLQYXMDZJF'},
					{name : 'KJHDJFZCZE'},
					{name : 'YJSYFZZC'},
					{name : 'XCPKFJFZC'},
					{name : 'GJZCS'},
					{name : 'HGJWJZJRS'},
					{name : 'GJZCHBSRS'},
					{name : 'ZRQYZSR'},
					{name : 'ZRQYGYZCZ'},
					{name : 'ZRQYJLR'},
					{name : 'ZRQYSJSJ'},
					{name : 'ZRQYCKCH'},
					{name : 'ZBSQTDQKPXS'},
					{name : 'SQZLS'},
					{name : 'FMZLS'},
					{name : 'QZSQZLS'},
					{name : 'QZFMZLS'},
					{name : 'GMGWJSZLS'},
					{name : 'STATUS'}
					
					]
	};

	var colsOption_zrqy = [
	 {
		id : 'DWID',
		header : "序号",
		width : 50,
		align : "center",
		headAlign:"center",
		isCheckColumn : true,
		filterable : false
	},{
		id : 'STATUS',
		header : "状态",
		headAlign:"center",
		width : 100,
		align : "left",
		renderer:function (v){var str ="待上报"; if(v==5){str="数据已上报(待审)";}else if(v==3){str="数据审核通过";}else if(v==4){str="<font color=red>数据审核退回</font>";} return str;}
	},{
		id : 'DWID11',
		header : "审核操作",
		width : 80,
		headAlign:"center",
		align : "center",
		renderer:function (v,r){var ss = "";if(r.STATUS==5){ ss= '<a href="javascript:;" onclick="datash('+r.DWID+',1);">数据审核</a>';} return ss;}
	},{
		id : 'DWDM',
		header : "组织机构代码",
		headAlign:"center",
		width : 120,
		align : "center"
	}, {
		id : 'QYMC',
		header : "单位名称",
		headAlign:"center",
		width : 150,
		align : "left",
		renderer:function (v,r){return '<a href="dwsb!dwsb1view.do?sbdm='+r.SBDM+'&dwids="'+r.DWID+' target=_blank title="点击查看详细信息">'+v+'</a>';}
	},{
		id : 'DWZT_MC',
		header : "单位状态",
		headAlign:"center",
		width : 120,
		align : "center"
	}, {
		id : 'DWLX_MC',
		header : "单位类型",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'DWPASSWORD',
		header : "用户登入密码",
		headAlign:"center",
		width : 80,
		align : "left"
	}, {
		id : 'LXR',
		header : "联系人",
		headAlign:"center",
		width : 80,
		align : "left"
	}, {
		id : 'LXRDH',
		header : "联系人电话",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'CZ',
		header : "传真",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'ZCZJ',
		header : "注册资金(万元)",
		width : 40,
		align : "center"
		
	}, {
		id : 'QYDJZCLX_MC',
		header : "企业登记注册类型",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'HYLB',
		header : "行业类别",
		headAlign:"center",
		width : 100,
		align : "left"
	},{
		id : 'YFJGS',
		header : "研发机构数",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'GXJSCPS',
		header : "高新技术产品数",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'ZRQYZSR',
		header : "在孵企业总收入",
		headAlign:"center",
		align : "left"
	}, {
		id : 'ZRQYGYZCZ',
		header : "在孵企业工业总产值",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZRQYJLR',
		header : "在孵企业净利润",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZRQYSJSJ',
		header : "在孵企业上缴税金",
		width : 100,
		headAlign:"center",
		align : "left"

	}, {
		id : 'ZRQYCKCH',
		header : "在孵企业出口创汇",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'ZBSQTDQKPXS',
		header : "在本市其他地区开票销售",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'ZRQYRS',
		header : "在孵企业从业人员数",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'BSRS',
		header : "在孵企业从业人员数(博士)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'SSRS',
		header : "在孵企业从业人员数(硕士)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'YJSJYSXLS',
		header : "在孵企业从业人员数(研究生学历及以上)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'BKXLS',
		header : "在孵企业从业人员数(本科学历)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'DZXLS',
		header : "在孵企业从业人员数(大专学历)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'GJZCS',
		header : "在孵企业从业人员数(高级职称)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'HGJWJZJRS',
		header : "在孵企业从业人员数(海归人才及外籍专家人员数)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'GJZCHBSRS',
		header : "在孵企业从业人员数(高级职称或博士学位人员数)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'SQZLS',
		header : "申请专利项数",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'FMZLS',
		header : "申请专利项数(发明专利)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'QZSQZLS',
		header : "授权专利项数",
		headAlign:"center",
		align : "left"
	}, {
		id : 'QZFMZLS',
		header : "授权专利项数(发明专利)",
		headAlign:"center",
		align : "left"
	}, {
		id : 'GMGWJSZLS',
		header : "购买国外技术专利数",
		headAlign:"center",
		align : "left"
	}, {
		id : 'CDGLGJDFXMS',
		header : "承担各类国家和地方项目数",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'GJJXMS',
		header : "承担各类国家和地方项目数(国家级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'SJXMS',
		header : "承担各类国家和地方项目数(省级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'SSJXMS',
		header : "承担各类国家和地方项目数(市级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'QJXMS',
		header : "承担各类国家和地方项目数(区级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'GLGJDFDZJF',
		header : "各类国家和地方项目到帐经费",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'GJJXMJF',
		header : "各类国家和地方项目到帐经费(国家级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'SJXMJF',
		header : "各类国家和地方项目到帐经费(省级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'SSJXMJF',
		header : "各类国家和地方项目到帐经费(市级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'QJXMJF',
		header : "各类国家和地方项目到帐经费(区级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'CDGLQYXMS',
		header : "承担各类企业项目数",
		headAlign:"center",
		align : "left"
	},{
		id : 'GLQYXMDZJF',
		header : "各类企业项目到帐经费",
		headAlign:"center",
		align : "left"
	},
	{
		id : 'KJHDJFZCZE',
		header : "科技活动经费支出总额",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'YJSYFZZC',
		header : "研究与试验发展支出",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'XCPKFJFZC',
		header : "新产品开发经费支出",
		width : 100,
		headAlign:"center",
		align : "left"
	}
];

	var gridOption_zrqy = {
		id : grid_demo_zrqy,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 80) + "px",
		pageSize : 25,
		container : 'mygrid_container_1',
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
		dataset : dsOption_zrqy,
		columns : colsOption_zrqy,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/dwsb!getList.do',
		exportURL : APP_PATH + '/dwsb!exportList.do?value=1',
		exportFileName : "在孵企业",
		autoLoad : false
	};
	
	
	var grid_demo_yfjg = "myGrid_yfjg";
	var dsOption_yfjg = {
		fields :  [ {name : 'SBDM'},
					{name : 'DWDM'},
					{name : 'DWID'},
					{name : 'DWZT_MC'}, 
					{name : 'DWLX_MC'}, 
					{name : 'DWPASSWORD'},
					{name : 'LXR'},
					{name : 'LXRDH'},
					{name : 'CZ'},
					{name : 'SBDM_MC'},
					{name : 'ZCZJ'},  
					{name : 'QYMC'}, 
		   		    {name : 'YFJGS'},
					{name : 'JYDLFRZG'}, 
					{name : 'SJYSYFJG'}, 
					{name : 'RHQYS'},
					{name : 'ZRQY'},
					{name : 'GMYSQY'},
					{name : 'GXJSQY'},
					{name : 'SSQY'},
					{name : 'RHQYGXJSCPS'},
					{name : 'ZSR'},
					{name : 'ZZC'},
					{name : 'JYJLR'},
					{name : 'SJSJ'},
					{name : 'CKCH'},
					{name : 'ZBSQTDQSR'},
					{name : 'ZYJSRYS'},
					{name : 'BS'},
					{name : 'SS'},
					{name : 'YJSXLJYS'},
					{name : 'BKXL'},
					{name : 'DZXL'},
					{name : 'GJZC'},
					{name : 'HGRCJWJZJRYS'},
					{name : 'GJZCHBSXWRYS'},
					{name : 'SQZLXS'},
					{name : 'FMZL'},
					{name : 'SSQZLXS'},
					{name : 'SFMZL'},
					{name : 'GMGWJSZLS'},
					{name : 'CDGLGJHDFXMS'},
					{name : 'GJJXM'},
					{name : 'SJXM'},
					{name : 'SSJXM'},
					{name : 'QJXM'},
					{name : 'GLGJHDFXMDZJF'},
					{name : 'SGJJXM'},
					{name : 'SJXM2'},
					{name : 'SSJXM2'},
					{name : 'SQJXM'},
					{name : 'CDGLQYXMS'},
					{name : 'GLQYXMDZJF'},
					{name : 'KJHDJFZCZE'},
					{name : 'YJYSYFZZC'},
					{name : 'XCPKFJFZC'},
					{name : 'STATUS'}
				]
	};

	var colsOption_yfjg = [ 
	 {
		id : 'DWID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	},{
		id : 'STATUS',
		header : "状态",
		headAlign:"center",
		width : 100,
		align : "left",
		renderer:function (v){var str ="待上报"; if(v==5){str="数据已上报(待审)";}else if(v==3){str="数据审核通过";}else if(v==4){str="<font color=red>数据审核退回</font>";} return str;}
	},{
		id : 'DWID11',
		header : "审核操作",
		width : 80,
		headAlign:"center",
		align : "center",
		renderer:function (v,r){var ss = "";if(r.STATUS==5){ ss= '<a href="javascript:;" onclick="datash('+r.DWID+',2);">数据审核</a>';} return ss;}
	},{
		id : 'DWDM',
		header : "组织机构代码",
		headAlign:"center",
		width : 120,
		align : "center"
	}, {
		id : 'QYMC',
		header : "单位名称",
		headAlign:"center",
		width : 150,
		align : "left",
		renderer:function (v,r){return '<a href="dwsb!dwsb2view.do?sbdm='+r.SBDM+'&dwids="'+r.DWID+' target=_blank title="点击查看详细信息">'+v+'</a>';}
	},{
		id : 'DWZT_MC',
		header : "单位状态",
		headAlign:"center",
		width : 120,
		align : "center"
	}, {
		id : 'DWLX_MC',
		header : "单位类型",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'DWPASSWORD',
		header : "用户登入密码",
		headAlign:"center",
		width : 80,
		align : "left"
	}, {
		id : 'LXR',
		header : "联系人",
		headAlign:"center",
		width : 80,
		align : "left"
	}, {
		id : 'LXRDH',
		header : "联系人电话",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'CZ',
		header : "传真",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'ZCZJ',
		header : "注册资金(万元)",
		headAlign:"center",
		width : 100,
		align : "center"
		
	},{
		id : 'YFJGS',
		header : "研发机构数",
		headAlign:"center",
		width : 100,
		align : "center"
	},{
		id : 'JYDLFRZG',
		header : "研发机构数(具有独立法人资格)",
		headAlign:"center",
		width : 100,
		align : "center"
	},{
		id : 'SJYSYFJG',
		header : "研发机构数(省级以上研发机构)",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'RHQYS',
		header : "孵化企业数",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'ZRQY',
		header : "孵化企业数(在孵企业)",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'GMYSQY',
		header : "孵化企业数(规模以上企业)",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'GXJSQY',
		header : "孵化企业数(高新技术企业)",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'SSQY',
		header : "孵化企业数(上市企业)",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'RHQYGXJSCPS',
		header : "孵化企业高新技术产品数",
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZSR',
		header : "孵化企业总收入",
		width : 100,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZZC',
		header : "孵化企业总支出",
		width : 100,
		headAlign:"center",
		align : "center"
	}, {
		id : 'JYJLR',
		header : "孵化企业结余(净利润)",
		width : 100,
		headAlign:"center",
		align : "center"

	}, {
		id : 'SJSJ',
		header : "孵化企业上缴税金",
		width : 100,
		headAlign:"center",
		align : "center"

	}, {
		id : 'CKCH',
		header : "孵化企业出口创汇",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'ZBSQTDQSR',
		header : "孵化企业在本市其他地区收入",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'BS',
		header : "专业技术人员数(博士)",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'SS',
		header : "专业技术人员数(硕士)",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'YJSXLJYS',
		header : "专业技术人员数(研究生学历及以上)",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'BKXL',
		header : "专业技术人员数(本科学历)",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'DZXL',
		header : "专业技术人员数(大专学历)",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'GJZC',
		header : "专业技术人员数(高级职称)",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'HGRCJWJZJRYS',
		header : "专业技术人员数(海归人才及外籍专家人员数)",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'GJZCHBSXWRYS',
		header : "专业技术人员数(高级职称或博士学位人员数)",
		width : 100,
		headAlign:"center",
		align : "center"
	},{
		id : 'SQZLXS',
		header : "申请专利项数",
		width : 100,
		headAlign:"center",
		align : "center"
	},{
		id : 'FMZL',
		header : "申请专利项数(发明专利)",
		width : 100,
		headAlign:"center",
		align : "center"
	}, {
		id : 'SSQZLXS',
		header : "授权专利项数",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'SFMZL',
		header : "授权专利项数(发明专利)",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'GMGWJSZLS',
		header : "购买国外技术专利数",
		headAlign:"center",
		width : 100,
		align : "center"
	}, 
	{
		id : 'CDGLGJHDFXMS',
		header : "承担各类国家和地方项目数",
		width : 100,
		headAlign:"center",
		align : "center"
	}, 
	{
		id : 'GJJXM',
		header : "承担各类国家和地方项目数(国家级项目)",
		width : 100,
		headAlign:"center",
		align : "center"
	}, 
	{
		id : 'SJXM',
		header : "承担各类国家和地方项目数(省级项目)",
		width : 100,
		headAlign:"center",
		align : "center"
	}, 
	{
		id : 'SSJXM',
		header : "承担各类国家和地方项目数(市级项目)",
		width : 100,
		headAlign:"center",
		align : "center"
	}, 
	{
		id : 'QJXM',
		header : "承担各类国家和地方项目数(区级项目)",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'GLGJHDFXMDZJF',
		header : "各类国家和地方项目到帐经费",
		width : 100,
		headAlign:"center",
		align : "center"
	}, 
	{
		id : 'SGJJXM',
		header : "各类国家和地方项目到帐经费(国家级项目)",
		width : 100,
		headAlign:"center",
		align : "center"
	}, 
	{
		id : 'SJXM2',
		header : "各类国家和地方项目到帐经费(省级项目)",
		width : 100,
		headAlign:"center",
		align : "center"
	}, 
	{
		id : 'SSJXM2',
		header : "各类国家和地方项目到帐经费(市级项目)",
		width : 100,
		headAlign:"center",
		align : "center"
	}, 
	{
		id : 'SQJXM',
		header : "各类国家和地方项目到帐经费(区级项目)",
		width : 100,
		headAlign:"center",
		align : "center"
	}, {
		id : 'CDGLQYXMS',
		header : "承担各类企业项目数",
		headAlign:"center",
		width : 100,
		align : "center"
	}, {
		id : 'GLQYXMDZJF',
		header : "各类企业项目到帐经费",
		headAlign:"center",
		width : 100,
		align : "center"
	},
	{
		id : 'KJHDJFZCZE',
		header : "科技活动经费支出总额",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'YJYSYFZZC',
		header : "研究与试验发展支出",
		width : 100,
		headAlign:"center",
		align : "center"
	},
	{
		id : 'XCPKFJFZC',
		header : "新产品开发经费支出",
		width : 100,
		headAlign:"center",
		align : "center"
	}
 ];

	var gridOption_yfjg = {
		id : grid_demo_yfjg,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 80) + "px",
		pageSize : 25,
		container : 'mygrid_container_2',
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
		showIndexColumn : false,
		dataset : dsOption_yfjg,
		columns : colsOption_yfjg,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/dwsb!getList.do',
		exportURL : APP_PATH + '/dwsb!exportList.do?value=2',
		exportFileName : "研发机构",
		autoLoad : false
	};
	
	var grid_demo_xqyx = "myGrid_xqyx";
	var dsOption_xqyx = {
		fields :  [ {name : 'SBDM'},
					{name : 'DWDM'},
					{name : 'DWID'},
					{name : 'DWZT_MC'}, 
					{name : 'DWLX_MC'}, 
					{name : 'DWPASSWORD'},
					{name : 'LXR'},
					{name : 'LXRDH'},
					{name : 'CZ'},
					{name : 'SBDM_MC'},
					{name : 'ZCZJ'},  
					{name : 'QYMC'}, 
		   		    {name : 'YFJGS'},
					{name : 'JYDLFRZG'}, 
					{name : 'SJYSYFJG'}, 
					{name : 'RHQYS'},
					{name : 'ZRQY'},
					{name : 'GMYSQY'},
					{name : 'GXJSQY'},
					{name : 'SSQY'},
					{name : 'RHQYGXJSCPS'},
					{name : 'ZSR'},
					{name : 'ZZC'},
					{name : 'JYJLR'},
					{name : 'SJSJ'},
					{name : 'CKCH'},
					{name : 'ZBSQTDQSR'},
					{name : 'ZYJSRYS'},
					{name : 'BS'},
					{name : 'SS'},
					{name : 'YJSXLJYS'},
					{name : 'BKXL'},
					{name : 'DZXL'},
					{name : 'GJZC'},
					{name : 'HGRCJWJZJRYS'},
					{name : 'GJZCHBSXWRYS'},
					{name : 'SQZLXS'},
					{name : 'FMZL'},
					{name : 'SSQZLXS'},
					{name : 'SFMZL'},
					{name : 'GMGWJSZLS'},
					{name : 'CDGLGJHDFXMS'},
					{name : 'GJJXM'},
					{name : 'SJXM'},
					{name : 'SSJXM'},
					{name : 'QJXM'},
					{name : 'GLGJHDFXMDZJF'},
					{name : 'SGJJXM'},
					{name : 'SJXM2'},
					{name : 'SSJXM2'},
					{name : 'SQJXM'},
					{name : 'CDGLQYXMS'},
					{name : 'GLQYXMDZJF'},
					{name : 'KJHDJFZCZE'},
					{name : 'YJYSYFZZC'},
					{name : 'XCPKFJFZC'},
					{name : 'STATUS'}
				]
		};

	var colsOption_xqyx = [ 
	  {
		id : 'DWID',
		header : "序号",
		width : 50,
		align : "center",
		isCheckColumn : true,
		filterable : false
	},{
		id : 'STATUS',
		header : "状态",
		headAlign:"center",
		width : 100,
		align : "left",
		renderer:function (v){var str ="待上报"; if(v==5){str="数据已上报(待审)";}else if(v==3){str="数据审核通过";}else if(v==4){str="<font color=red>数据审核退回</font>";} return str;}
	},{
		id : 'DWID11',
		header : "审核操作",
		width : 80,
		headAlign:"center",
		align : "center",
		renderer:function (v,r){var ss = "";if(r.STATUS==5){ ss= '<a href="javascript:;" onclick="datash('+r.DWID+',3);">数据审核</a>';} return ss;}
	},{
		id : 'DWDM',
		header : "组织机构代码",
		headAlign:"center",
		width : 120,
		align : "center"
	}, {
		id : 'QYMC',
		header : "单位名称",
		headAlign:"center",
		width : 150,
		align : "left",
		renderer:function (v,r){return '<a href="dwsb!dwsb3view.do?sbdm='+r.SBDM+'&dwids="'+r.DWID+' target=_blank title="点击查看详细信息">'+v+'</a>';}
	},{
		id : 'DWZT_MC',
		header : "单位状态",
		headAlign:"center",
		width : 120,
		align : "center"
	}, {
		id : 'DWLX_MC',
		header : "单位类型",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'DWPASSWORD',
		header : "用户登入密码",
		headAlign:"center",
		width : 80,
		align : "left"
	}, {
		id : 'LXR',
		header : "联系人",
		headAlign:"center",
		width : 80,
		align : "left"
	}, {
		id : 'LXRDH',
		header : "联系人电话",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'CZ',
		header : "传真",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'ZCZJ',
		header : "注册资金(万元)",
		width : 40,
		align : "center"
		
	},{
		id : 'YFJGS',
		header : "研发机构数",
		headAlign:"center",
		width : 80,
		align : "center"
	},{
		id : 'JYDLFRZG',
		header : "研发机构数(具有独立法人资格)",
		headAlign:"center",
		width : 80,
		align : "center"
	},{
		id : 'SJYSYFJG',
		header : "研发机构数(省级以上研发机构)",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'RHQYS',
		header : "孵化企业数",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'ZRQY',
		header : "孵化企业数(在孵企业)",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'GMYSQY',
		header : "孵化企业数(规模以上企业)",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'GXJSQY',
		header : "孵化企业数(高新技术企业)",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'SSQY',
		header : "孵化企业数(上市企业)",
		headAlign:"center",
		width : 150,
		align : "left"
	}, {
		id : 'RHQYGXJSCPS',
		header : "孵化企业高新技术产品数",
		headAlign:"center",
		align : "left"
	}, {
		id : 'ZSR',
		header : "孵化企业总收入",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZZC',
		header : "孵化企业总支出",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'JYJLR',
		header : "孵化企业结余(净利润)",
		width : 100,
		headAlign:"center",
		align : "left"

	}, {
		id : 'SJSJ',
		header : "孵化企业上缴税金",
		width : 100,
		headAlign:"center",
		align : "left"

	}, {
		id : 'CKCH',
		header : "孵化企业出口创汇",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'ZBSQTDQSR',
		header : "孵化企业在本市其他地区收入",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'ZYJSRYS',
		header : "专任教师数",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'BS',
		header : "专任教师数(博士)",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'SS',
		header : "专任教师数(硕士)",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'YJSXLJYS',
		header : "专任教师数(研究生学历及以上)",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'BKXL',
		header : "专任教师数(本科学历)",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'DZXL',
		header : "专任教师数(大专学历)",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'GJZC',
		header : "专任教师数(高级职称)",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'HGRCJWJZJRYS',
		header : "专任教师数(海归人才及外籍专家人员数)",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'GJZCHBSXWRYS',
		header : "专任教师数(高级职称或博士学位人员数)",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'SQZLXS',
		header : "申请专利项数",
		width : 100,
		headAlign:"center",
		align : "left"
	},{
		id : 'FMZL',
		header : "申请专利项数(发明专利)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'SSQZLXS',
		header : "授权专利项数",
		headAlign:"center",
		align : "left"
	}, {
		id : 'SFMZL',
		header : "授权专利项数(发明专利)",
		headAlign:"center",
		align : "left"
	}, {
		id : 'GMGWJSZLS',
		header : "购买国外技术专利数",
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'CDGLGJHDFXMS',
		header : "承担各类国家和地方项目数",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'GJJXM',
		header : "承担各类国家和地方项目数(国家级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'SJXM',
		header : "承担各类国家和地方项目数(省级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'SSJXM',
		header : "承担各类国家和地方项目数(市级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'QJXM',
		header : "承担各类国家和地方项目数(区级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'GLGJHDFXMDZJF',
		header : "各类国家和地方项目到帐经费",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'SGJJXM',
		header : "各类国家和地方项目到帐经费(国家级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'SJXM2',
		header : "各类国家和地方项目到帐经费(省级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'SSJXM2',
		header : "各类国家和地方项目到帐经费(市级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, 
	{
		id : 'SQJXM',
		header : "各类国家和地方项目到帐经费(区级项目)",
		width : 100,
		headAlign:"center",
		align : "left"
	}, {
		id : 'CDGLQYXMS',
		header : "承担各类企业项目数",
		headAlign:"center",
		align : "left"
	}, {
		id : 'GLQYXMDZJF',
		header : "各类企业项目到帐经费",
		headAlign:"center",
		align : "left"
	},
	{
		id : 'KJHDJFZCZE',
		header : "科技活动经费支出总额",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'YJYSYFZZC',
		header : "研究与试验发展支出",
		width : 100,
		headAlign:"center",
		align : "left"
	},
	{
		id : 'XCPKFJFZC',
		header : "新产品开发经费支出",
		width : 100,
		headAlign:"center",
		align : "left"
	}

	 ];

	var gridOption_xqyx = {
		id : grid_demo_xqyx,
		skin : "vista",
		width : "100%",
		height : (getSize().h - 80) + "px",
		pageSize : 25,
		container : 'mygrid_container_3',
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
		dataset : dsOption_xqyx,
		columns : colsOption_xqyx,
		remoteSort : true,
		remoteFilter : true,
		loadURL : APP_PATH + '/dwsb!getList.do',
		exportURL : APP_PATH + '/dwsb!exportList.do?value=3',
		exportFileName : "西区院校",
		autoLoad : false
	};
	
	var mygrid_zrqy = new Sigma.Grid(gridOption_zrqy);
	var mygrid_yfjg = new Sigma.Grid(gridOption_yfjg);
	var mygrid_xqyx = new Sigma.Grid(gridOption_xqyx);
	
	Event.observe(window, "load", function() {
		//mygrid_zrqy.render();
		mygrid_zrqy.render();
		mygrid_yfjg.render();
		mygrid_xqyx.render();
	}, true);
	

	var treekey = "";
	////查询表单的查询函数 ////
	function queryDw() {
		if(treekey == ""){
			alert("请选择相应的填报！");
			return false;
		}
		$('mygrid_container_1').style.display = "none";
		$('mygrid_container_2').style.display = "none";
		$('mygrid_container_3').style.display = "none";
		if(tree.getUserData(treekey,'bz')==1){
			$('mygrid_container_1').style.display = "";
			mygrid_zrqy.query({"name":$F("name"),"where":$F("where"),"treekey":treekey});
			mygrid_zrqy.exportURL = APP_PATH+'/dwsb!exportList.do?value=1'+'&treekey='+treekey;
		}else if(tree.getUserData(treekey,'bz')==2){
			$('mygrid_container_2').style.display = "";
			mygrid_yfjg.query({"name":$F("name"),"where":$F("where"),"treekey":treekey});
			mygrid_yfjg.exportURL = APP_PATH+'/dwsb!exportList.do?value=2'+'&treekey='+treekey;
		}else if(tree.getUserData(treekey,'bz')==3){
			$('mygrid_container_3').style.display = "";
			mygrid_xqyx.query({"name":$F("name"),"where":$F("where"),"treekey":treekey});
			mygrid_xqyx.exportURL = APP_PATH+'/dwsb!exportList.do?value=3'+'&treekey='+treekey;
		}
	}
	function dofilter(){
		if(tree.getUserData(treekey,'bz')==1){
			mygrid_zrqy.showDialog("filter");
		}else if(tree.getUserData(treekey,'bz')==2){
			mygrid_yfjg.showDialog("filter");
		}else if(tree.getUserData(treekey,'bz')==3){
			mygrid_xqyx.showDialog("filter");
		}
	}	
	
	function seldw(){
		if(treekey != "" && treekey!="root" && treekey.length==8){
			openWin("dwsb!getDwsbWaitSelect.do?dm="+treekey+"&dmlx="+tree.getUserData(treekey,'bz'),"750","500");
		}else{
			alert("请选择相应的单位填报类型 !");
		}
	}
	
	function deldw(){
		var len = 0;
		if(tree.getUserData(treekey,'bz')==1){
			var len = mygrid_zrqy.getSelectedRecords().length;
		}else if(tree.getUserData(treekey,'bz')==2){
			var len = mygrid_yfjg.getSelectedRecords().length;
		}else if(tree.getUserData(treekey,'bz')==3){
			var len = mygrid_xqyx.getSelectedRecords().length;
		}
		if(len < 1){
			alert("请选择需要删除的数据！");
			return false;
		}
		var r = new Array();
		for(var i=0;i<len;i++){
			if(tree.getUserData(treekey,'bz')==1){
				r.push(mygrid_zrqy.getSelectedRecords()[i].DWID);
			}else if(tree.getUserData(treekey,'bz')==2){
				r.push(mygrid_yfjg.getSelectedRecords()[i].DWID);
			}else if(tree.getUserData(treekey,'bz')==3){
				r.push(mygrid_xqyx.getSelectedRecords()[i].DWID);
			}
		}
		if(window.confirm("请确定需要删除《"+len+"》条记录!")){
			var ajax = new AppAjax("dwsb!doDelSelectedDwsb.do?dwids="+r+"&dm="+treekey+"&dmlx="+tree.getUserData(treekey,'bz'),save_Back_mx).submit();
		}
	}
	function save_Back_mx(data){
		if(data.message.code >0){
			alert("删除成功！");
			queryDw();
		}else{
			alert(data.message.text);
		}
	}
	
	function setTree(id){
		treekey = id;
		queryDw();
	}
	function add(){
		openWin("dwsb!preDwsb.do?opttype=1","350","220");
	}
	function rep(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		openWin("dwsb!repDwsb.do?opttype=2&dm="+treekey,"350","220");
	}
	function del(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		
		if(window.confirm('所有数据都将删除！您确定要删除当前填报??')){
			var ajax = new AppAjax("dwsb!doDelDwsb.do?dm="+treekey+"&dmlx="+tree.getUserData(treekey,'bz'),function(data){del_Back(data)});
			ajax.setAsyn(false);
			ajax.submit();
		}
	}
	
	function del_Back(data){
		if(data != null ){
			if(data.message.code > 0){
				alert('删除成功！');
				loadTree();
			}else{
				alert(data.message.text);
			}
		}
	}
	
	function end(){
		if(treekey.length != 4){
			alert('请选择相应填报名称！');
			return false;
		}
		
		if(window.confirm('您确定要结束当前填报！')){
			var ajax = new AppAjax("dwsb!checkWithEnd.do?dm="+treekey,function(data){end_Back(data)});
			ajax.setAsyn(false);
			ajax.submit();
		}		
	}
		
	function end_Back(data){
		if(data != null ){
			
			if(data.message.code > 0){
				var ajax = new AppAjax("dwsb!updateEnd.do?dm="+treekey,function(data){do_end_Back(data)});
					ajax.setAsyn(false);
					ajax.submit();
			}else{
				if(window.confirm(data.message.text)){
					var ajax = new AppAjax("dwsb!updateEnd.do?dm="+treekey,function(data){do_end_Back(data)});
					ajax.setAsyn(false);
					ajax.submit();
				}
			}
		}
	}
	
	function do_end_Back(data){
		if(data != null ){
			if(data.message.code > 0){
				alert('当前填报已结束！');
				loadTree();
			}else{
				alert(data.message.text);
			}
		}
	}	
	function querymx(obj){
		 var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		 openWin("zsdw!viewZsdw.do?dwid="+obj+"&opttype=3",w,h);
	}	
	
	function datash(s,dmlx){
		var h = document.body.clientHeight-50;
		 var w = '';
		 if(getSize().w > 940){
			w = 940;
		 }else{
			w = getSize().w;
		 }
		
		openWin("dwsb!preDatash.do?dwids="+s+"&dm="+treekey+"&dmlx="+dmlx,w,h);
	}
</script>
</html>

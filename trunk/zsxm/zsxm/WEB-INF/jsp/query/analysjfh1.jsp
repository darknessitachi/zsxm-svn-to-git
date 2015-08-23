<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>企业统计分析</title>
       <%@ include file="/common/meta.jsp"%>
       <SCRIPT LANGUAGE="Javascript" SRC="FlashChart/swfobject.js"></SCRIPT>
       	<link rel="stylesheet" type="text/css" href="<s:url value="/grid/gt_grid.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/default/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/china/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/vista/skinstyle.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/grid/skin/mac/skinstyle.css"/>" />
		<script type="text/javascript" src="<s:url value="/grid/gt_msg_cn.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/grid/gt_grid_all.js"/>"></script>
 		<script language="JavaScript" src="<s:url value="/FusionCharts/FusionCharts.js"/>"></script>
	</head>
	<body style="margin: 0px; margin: 2px;">
		<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
				<tr>
				    <td class="lefttop"></td>
				    <td width="%"  class="centertop"></td>
				    <td class="righttop"></td>
			    </tr>
				<tr>
					<td height="26" class="leftcenter">&nbsp;</td>
				    <td class="center" >
				    	统计内容 <select name="cxtype" style="width: 150px">
				    	   <option value="1">单位状态</option>
				    	   <option value="2">内/外资</option>
				    	   <option value="3">单位类型</option>
				    	   
				    	 </select>
				    	<input type="button" id="queryBtn" class="but" value="统   计" id="b_sendmessage" onclick="query();">
				    </td>
				   	<td class="rightcenter"></td>
				</tr>	
			   <tr>
				    <td class="leftbot"></td>
				    <td class="centerbot"></td>
				    <td class="rightbot"></td>
			   </tr>
		</table>
		<div id="grid_container">
			<div id="grid_m_container" style="width:400px;height:250px;margin:5px;float: left"></div>
			<div id="chart_container"  style="height:250px;margin:5px;border: 1px solid #C1DAD7;float: left"></div>
			<div id="grid_x_container" style="width:100%;height:300px;margin:5px;float: right"></div>
		</div>
	</body>

	<script type="text/javascript">
	
	var APP_PATH = '<c:url value="/"/>';

	var dsOption_dep = {
		fields : [ {
			name : 'tp'
		}, {
			name : 'rs'
		}, {
			name : 'bl'
		}, {
			name : 'dm'
		}
		 ],
		uniqueField : 'tp'
	};

	var colsOption_dep = [ {
		id : 'tp',
		header : "类型",
		width : 100
	}, {
		id : 'rs',
		header : "数量",
		width : 100
	}, {
		id : 'bl',
		header : "占比(%)",
		width : 100
	} ];

	var gridOption_dep = {
		id : 'grid_dep',
		loadURL : APP_PATH + '/dwanalys!queryListByType.do',
		exportURL : APP_PATH + '/dwanalys!doExportList.do',
		container : 'grid_m_container',
		toolbarContent : 'reload | print | export xls',
		pageSize : 100,
		
		remotePaging : false,
		skin : "vista",
		dataset : dsOption_dep,

		columns : colsOption_dep,
		stripeRows	: false,
		exportFileName : "单位统计",
	//	autoSelectFirstRow : true,
		afterSelectRow : function(record, row, rowNo, grid) {
			var param = {
				depNo : record['dm'],
				cxtype : $("cxtype").value
			};
			Sigma.$grid('grid_std').parameters = param;
			Sigma.$grid('grid_std').query(param);
		},
		afterRefresh : function() {
			Sigma.$grid('grid_std').cleanContent();
		},
		autoLoad : false,
		onComplete  : function(grid){
			bind2chart(grid.dataset.data);
		}
	};
	
	var grid_dep = new Sigma.Grid(gridOption_dep);

	////定义子表 : 学生信息列表 ////
	var dsOption_std = {
			fields : [ {name : 'DWID'},
					{name : 'DWDM'},
		   		    {name : 'DWMC'}, 
		   		    {name : 'DWZT_MC'}, 
		   		    {name : 'DWLX_MC'}, 
		   		    {name : 'XM_MC'},
		   		    {name : 'LOGINNAME'},
		   		    {name : 'FRDB'},  
		   		    {name : 'CLRQ'}, 
		   		    {name : 'NWZ_MC'}, 
		   		    {name : 'ZCZB'}, 
					{name : 'JZMJ'},
					{name : 'HGTDPC'},
					{name : 'SWGLM'},
					{name : 'NSRSBH'}
				]
	};
var colsOption_std = [ 
	{
		id : 'DWDM',
		header : "组织机构代码",
		headAlign:"center",
		width : 120,
		align : "center",
		renderer:function (v,r){return '<a href="javascript:;" title="点击查看详细信息" onclick="querymx('+r.DWID+')">'+v+'</a>';}
	}, {
		id : 'DWMC',
		header : "单位名称",
		headAlign:"center",
		width : 120,
		align : "left",
		renderer:function (v,r){return '<a href="javascript:;" title="点击查看详细信息" onclick="querymx('+r.DWID+')">'+v+'</a>';}
		
	}, {
		id : 'DWZT_MC',
		header : "单位状态",
		headAlign:"center",
		width : 80,
		align : "center"
	}, 
	 {
		id : 'DWLX_MC',
		header : "单位类型",
		headAlign:"center",
		width : 150,
		align : "left"
	},{
		id : 'XM_MC',
		header : "招资项目",
		headAlign:"center",
		width : 150,
		align : "left"
	},{
		id : 'LOGINNAME',
		header : "用户登入名",
		headAlign:"center",
		width : 80,
		align : "center"
	}, {
		id : 'FRDB',
		header : "法人代表",
		headAlign:"center",
		width : 70,
		align : "left"
	}, {
		id : 'CLRQ',
		header : "成立日期",
		headAlign:"center",
		width : 100,
		align : "left"
	}, {
		id : 'NWZ_MC',
		header : "内/外资",
		width : 70,
		headAlign:"center",
		align : "center"
	}, {
		id : 'ZCZB',
		header : "注册资本",
		width : 100,
		headAlign:"center",
		align : "right"
	},{
		id : 'JZMJ',
		header : "建筑面积",
		width : 70,
		headAlign:"center",
		align : "right"
	}, {
		id : 'HGTDPC',
		header : "海归团队批次",
		width : 100,
		headAlign:"center",
		align : "center"
	}, {
		id : 'SWGLM',
		header : "税务管理码",
		width : 100,
		headAlign:"center",
		align : "right"
	},{
		id : 'NSRSBH',
		header : "纳税人识别号",
		width : 100,
		headAlign:"center",
		align : "right"
	}
	 ];
	 
	var gridOption_std = {
	    skin : "vista",
		id : 'grid_std',
		width : "100%",
		height : (getSize().h - 320) + "px",
		pageSize : 25,
		container : 'grid_x_container',
		showGridMenu : true,
		allowCustomSkin : true,
		allowFreeze : true,
		allowHide : true,
		allowGroup : true,
		stripeRows : true,
		toolbarContent : 'nav | goto | pagesize | reload | export xls print | filter | state | info',
		pageSizeList : [ 25, 50, 100, 200, 500 ],
		lightOverRow : true,
		showIndexColumn : false,
		dataset : dsOption_std,
		columns : colsOption_std,
		remoteSort : true,
		remoteFilter : false,
		loadURL : APP_PATH + '/dwquery!getList.do',
		exportURL : APP_PATH + '/dwquery!exportList.do',
		exportFileName : "列表",
		autoLoad : false,
		showIndexColumn : true

	};
	var grid_std = new Sigma.Grid(gridOption_std);

	////页面加载后 创建列表 ////
	Sigma.Utils.onLoad(function() {
		grid_dep.render();
		grid_std.render();
	});

	//////////////////////////////////////////////////////////

	////查询表单的查询函数 ////
	function query() {
		var param = {
			type : Sigma.U.getValue(Sigma.$('cxtype'))
		}
		// 由于姓名是模糊查询 所以在这里拼接 % .
	
		Sigma.$grid('grid_dep').parameters = param;
		Sigma.$grid('grid_dep').query(param);
	}

	function bind2dateset(data){
		   var dataset = null;
		   if(data){
		      dataset = { caption : '企业分布情况',
		                  xAxisName : '类别',
		                  yAxisName : '数量',
		                  charttype : '2', //0:柱状;>0:饼状
		                  multi : false, //多数据
		                  header : [],
		                  ds : {x:[],y:[]}
		         
		      }; 

		      dataset["header"].push("数量");

		      var t = [] , t1 = [];
		      for(var i=0 ;i < data.length ; i ++){
		         
		         var d =  data[i];

		         var ls_tp = d["tp"];
		         var ls_rs = d["rs"];
		         var ls_bl = d["bl"];
  
		          t.push(ls_tp);
		          t1.push(ls_rs);
		      }
		      
	         if(t.length > 0){
	            dataset["ds"]["x"].push(t);
	         } 
	         if(t1.length > 0){
	            dataset["ds"]["y"].push(t1);   
	         }
		         
		
		      if(dataset["header"].length > 1){
	             dataset["multi"] = true;   
	          }
		   }
		   
		   return dataset;
		}
		
		function tran2xml(ds){
		  var str = '<?xml version="1.0" encoding="gbk"?>';
		  str += "<graph showNames='1' rotateYAxisName='0'  showFCMenuItem='0' showvalues='0' enablesmartlabels='1' bgcolor='e1f5ff' caption='"+ds.caption+"' decimalPrecision='0' baseFont='宋体' baseFontSize='12' xAxisName='"+ds.xAxisName+"' yAxisName='"+ds.yAxisName+"' showvalues='0' decimals='2' formatnumberscale='0' useroundedges='1'>";
		  if(ds["ds"]["y"] && ds["ds"]["y"].length == 1){
		     for(var i =0 ;i < ds["ds"]["y"].length ; i ++){
		        for(var j =0 ; j < ds["ds"]["y"][i].length ; j ++){
		          str += "<set name='"+ds["ds"]["x"][i][j]+"' value='"+ds["ds"]["y"][i][j]+"' color='"+getRandomColor(ds["ds"]["x"][i][j])+"'/>";
		        }
		     }
		  }else{
		     str += " <categories>";
		     for(var i = 0 ;i <ds["header"].length ; i ++){
		        str += "<category name='"+ds["header"][i]+"'/>";
		     }
		     str += " </categories>"; 
		     if(ds["ds"]["x"][0] != null ){//王晓杰 2009-10-26
			     for(var i = 0 ; i < ds["ds"]["x"][0].length;i++){
			        str +="<dataset seriesname='"+ds["ds"]["x"][0][i]+"' color='"+getRandomColor(ds["ds"]["x"][0][i])+"'>";
			        for(var k = 0 ; k < ds["ds"]["y"].length ; k ++){
			           str +="<set value='"+ds["ds"]["y"][k][i]+"'/>";
			        }
			        str += "</dataset>";
			     }
		     }
		  }
		  
		  str += "</graph>";
		  return str;
		}
		
		function bind2chart(data){
		   if(data){
		      var ds = bind2dateset(data);
		      if(ds){
		         var xmlstr = tran2xml(ds);
		         var myChart = null;
		         if(parseFloat(ds["charttype"]) == "0"){
		            if(ds["multi"] == false){
		               myChart = new FusionCharts("FusionCharts/Column3D.swf", "myChartId", "400", "250");
		            }else{
		               myChart = new FusionCharts("FusionCharts/MSColumn3D.swf", "myChartId", "400", "250");
		            }
		         }else{
		            myChart = new FusionCharts("FusionCharts/Pie3D.swf", "myChartId", "400", "250",0,0);
		         }
		         
		         myChart.setDataXML(xmlstr);
				 myChart.render("chart_container");
		      }
		   }
		
		}
		
	    function getRandomColor(num){
	       var n="c"+(num || "0");
	       if(typeof cmap == "undefined"){
	          cmap = {};
	       }
	       if(typeof cmap[n] == "undefined"){
	          var str = "0123456789ABCDEF";
	          var t ="";
	          for(var j = 0 ; j < 6 ;j++){
	            t = t + str.charAt(Math.random()*str.length);
	          }
	          cmap[n] = t;
	       }
	       return cmap[n];
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
</script>

</html>
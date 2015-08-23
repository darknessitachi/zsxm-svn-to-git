<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>	
	<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='高级查询'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="xmid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div id="tableContainer" style="height:90%;overflow:auto">
				<table class="fxtable"  cellpadding="0" cellspacing="0">
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">项目名称</td>
						<td class="left" colspan=3>
							<input type="text" name="xmmc"/>
						</td>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">项目承担单位名称</td>
						<td class="left" colspan=3>
							<input type="text" name="dwmc"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">文号</td>
						<td class="left" colspan=3>
							<input type="text" name="xmwh"/>
						</td>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">立项编号</td>
						<td class="left" colspan=3>
							<input type="text" name="xmbh"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">日期区间</td>
						<td class="left" colspan=3>
							<input type="text" class="Wdate" name="strdate" id="strdate" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
							-
							<input type="text" class="Wdate" name="enddate" id="enddate" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
						</td>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">立项年份</td>
						<td class="left" colspan=3>
							<s:select name="lxrq" list="xtdict19" cssStyle="width:130" listKey="dictname" listValue="dictname" headerKey="" headerValue="--请选择--" />
						</td>		
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">立项级别</td>
						<td class="left" colspan=3>
							<s:select name="xmjb" list="xtdict16" cssStyle="width:130" listKey="dictbh" listValue="dictname" headerKey="" headerValue="--请选择--" />
							
						</td>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">计划类别</td>
						<td class="left" colspan=3>
							<s:select name="jhlb" list="xtdict20" cssStyle="width:130" listKey="dictbh" listValue="dictname" headerKey="" headerValue="--请选择--" />
							
						</td>
					</tr>
					
				</table>
	</div>	
	
	
	
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确   定" onclick="javascript:doTrue();"/>
		<input class="button3" type="button" value="退   出" onclick="closeWindows();"/>
	</div>
	
	
	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id='xtdlbloadimage'>
			<img src="images/skin0/other/upload.gif">数据载入中.....
		</div>
		<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
			
		</div>
		
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	</s:form>
	</body>
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		// $('xwincontent').style.height = (getSize().h - 105)+"px"; 
		var reload = 0;
		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			if(reload == 1){
				var xx = $('pname').value
				var yy= eval('parent.document.'+xx);
				yy.refresh();
			}
			parent.closeXwin(sid);
		}
		$('tableContainer').style.height = (getSize().h - 70)+"px"; 
		var tree;
		var sx = '';
		function loadTree(dm,sx_){
			sx = sx_;
			if($("xtdlb").loading != 1){
				$('xtdlbload').innerHTML = "";
				tree=new dhtmlXTreeObject("xtdlbload","100%","100%",0);
				tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				tree.setOnClickHandler(function(id){setV(id);});
				tree.enableSmartXMLParsing(true);
				tree.setDataMode("json");
				tree.enableCheckBoxes(false);
				tree.loadJSON("zsxm!getXmTree.do?querydm="+dm,function(){$('xtdlbloadimage').style.display="none";});
				$('xtdlb').loading = 1;			
			}
		}
		
		function setV(id){
			if(id=='root'){
				$(sx).value='';
			}else{
				$(sx).value=id;
			}
			$('xtdlb').style.display = 'none';
			$(sx+'_but').value = tree.getItemText(id);
		}
		
		function doTrue(){
			var RH = ' 1=1 ';
			if($('strdate').value != '' ){
				RH += " and convert(varchar(20),strdate,23) >='"+$('strdate').value+"'";
			}
			if($('enddate').value != '' ){
				RH += " and convert(varchar(20),enddate,23) <='"+$('enddate').value+"'";
			}
			if($('xmmc').value.trim() != '' ){
				RH += " and xmmc like '%"+$('xmmc').value.trim()+"%'";
			}
			
			if($('dwmc').value.trim() != '' ){
				RH += " and dwmc like '%"+$('dwmc').value.trim()+"%'";
			}
			
			if($('xmwh').value.trim() != '' ){
				RH += " and xmwh like '%"+$('xmwh').value.trim()+"%'";
			}
			
			if($('xmbh').value.trim() != '' ){
				RH += " and xmbh like '%"+$('xmbh').value.trim()+"%'";
			}
			
			if($('lxrq').value != '' ){
				RH += " and year(lxrq) = '"+$('lxrq').value+"'";
			}
			if($('xmjb').value != '' ){
				RH += " and xmjb = '"+$('xmjb').value+"'";
			}
			if($('jhlb').value != '' ){
				RH += " and jhlb like '"+$('jhlb').value+"%'";
			}
			
			getOpener().setHSearchT(RH);
			closeWindows();
		}
		
	</script>
</html>


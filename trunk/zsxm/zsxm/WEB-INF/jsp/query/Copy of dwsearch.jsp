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
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">机构代码</td>
						<td class="left">
							<input type="text" name="dwdm" style="width:140"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">单位名称</td>
						<td class="left">
							<input type="text" name="dwmc" style="width:140"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">招资项目</td>
						<td class="left">
							<input type="text" name="xm_mc" style="width:140"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">档案编号</td>
						<td class="left">
							<input type="text" name="dabh" style="width:140"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">领军型人才</td>
						<td class="left">
							<select name="isljx" id="isljx" style="width:140">
								<option value=""></option>
								<option value=1>有</option>
								<option value=0>无</option>
							</select>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">股权比例</td>
						<td class="left">
							<select name="isgqbl" id="isgqbl" style="width:140">
								<option value=""></option>
								<option value=1>有</option>
								<option value=0>无</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">办公地点</td>
						<td class="left" colspan=5>
							常州科教城
								<s:select name="bgdd1" list="xtdict6" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
								<s:select name="bgdd2" list="xtdict7" cssStyle="width:50" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
								座
								<s:select name="bgdd3" list="xtdict8" cssStyle="width:50" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
								层
								<s:textfield name="bgdd4" cssStyle="width:50"></s:textfield>
								房间
						</td>
						
					</tr>
					<tr>	
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">单位类型</td>
						<td class="left">
							<s:select name="dwlx" list="xtdict9" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择单位类型--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">单位状态</td>
						<td class="left">
							<s:select name="dwzt" list="xtdict4" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择单位类型--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">内外资</td>
						<td class="left">
							<s:select name="nwz" list="xtdict5" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择单位类型--"/>
						</td>	
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">法人代表</td>
						<td class="left">
							<input type="text" name="frdb" style="width:140"/>
						</td>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">成立日期</td>
						<td class="left">
							<input type="text" class="Wdate" name="clrq" id="clrq" style="text-align:left;width:140" value="" onfocus="new WdatePicker(this,'%Y-%M')" MINDATE="1960-01-01" readonly/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">跟踪人</td>
						<td class="left">
							<s:select name="dwgzr" list="xtusers" cssStyle="width:140" listKey="userid" listValue="cnname" headerKey="" headerValue="--请选择--" />
						</td>
						
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">入驻时间</td>
						<td class="left">
							<s:select name="rzyqsj" list="xtdict33" cssStyle="width:140" listKey="dictname" listValue="dictname"  headerKey="" headerValue="--请选择--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">注册资本</td>
						<td class="left">
							<s:select name="zczb" list="zczbs" cssStyle="width:140" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">建筑面积</td>
						<td class="left">
							<s:select name="jzmj" list="jzmzs" cssStyle="width:140" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"/>
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">上年销售收入</td>
						<td class="left">
							<s:select name="snsssrs" list="snsssrs" cssStyle="width:140" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">销售收入增长率</td>
						<td class="left">
							<s:select name="sssrzzs" list="sssrzzs" cssStyle="width:140" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">高新技术企业</td>
						<td class="left">
							<s:select name="gxjsqy" list="xtdict34" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">上年缴纳税收</td>
						<td class="left">
							<s:select name="snjnss" list="snjnss" cssStyle="width:140" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">缴纳税收增长率</td>
						<td class="left">
							<s:select name="jnsszc" list="jnsszc" cssStyle="width:140" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">省双创批次</td>
						<td class="left">
							<s:select  id="sscpc" name="sscpc" list="xtdict45" cssStyle="width:140;"  listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">上年底员工数</td>
						<td class="left">
							<s:select name="sndygs" list="sndygs" cssStyle="width:140" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"/>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">员工增长率</td>
						<td class="left">
							<s:select name="ygszzl" list="ygszzl" cssStyle="width:140" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"/>
						</td>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">市双创批次</td>
						<td class="left">
							<s:select  id="tdpc" name="tdpc" list="xtdict30" cssStyle="width:140;"  listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--"/>
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">软件企业</td>
						<td class="left">
							<s:select name="rjqy" list="xtdict35" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">动漫企业</td>
						<td class="left">
							<s:select name="dmqy" list="xtdict36" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">留学生企业</td>
						<td class="left">
							<select name="lxsqy" id="lxsqy">
								<option value=""></option>
								<option value=1>是</option>
								<option value=0>否</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">国家创新型科技园区</td>
						<td class="left">
							<s:select name="gjcxkjyq" list="xtdict37" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">国家七大新兴产业</td>
						<td class="left">
							<s:select name="gjqdxxcy" list="xtdict38" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">现代服务业</td>
						<td class="left">
							<s:select name="xdfwy" list="xtdict39" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">服务外包</td>
						<td class="left">
							<s:select name="fwwb" list="xtdict40" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">两化融合</td>
						<td class="left">
							<s:select name="lhrh" list="xtdict41" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">CMMI认证</td>
						<td class="left">
							<s:select name="cmmi" list="xtdict42" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">商标</td>
						<td class="left">
							<s:select name="dwsb" list="xtdict43" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">主营产品</td>
						<td class="left" colspan=3>
							<input type="text" name="dwzycp"/>
						</td>
						
						
					</tr>
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">省双创团队</td>
						<td class="left">
							<select name="ssctd" id="ssctd"  style="width:140">
								<option value=""></option>
								<option value=1>是</option>
								<option value=0>否</option>
							</select>
						</td>
						
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">市双创团队</td>
						<td class="left" colspan=3>
							<select name="sssctd" id="sssctd" style="width:140">
								<option value=""></option>
								<option value=1>是</option>
								<option value=0>否</option>
							</select>
						</td>
						
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">承担纵向项目情况</td>
						<td class="left" colspan=5>
							立项年份:<s:select name="cdlxrq" list="xtdict19" cssStyle="width:130" listKey="dictname" listValue="dictname"  headerKey="" headerValue="--请选择--" />
							&nbsp;&nbsp;&nbsp;
							立项级别:<s:select name="cdxmjb" list="xtdict16" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--"/>
							&nbsp;&nbsp;&nbsp;
							计划类别:<s:select name="cdjhlb" list="xtdict20" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
							&nbsp;&nbsp;&nbsp;
							
						</td>
						
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">园区服务记录</td>
						<td class="left" colspan=5>
							日期区间:
							<input type="text" class="Wdate" name="yqrq1" id="yqrq1" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
							-
							<input type="text" class="Wdate" name="yqrq2" id="yqrq2" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
							&nbsp;&nbsp;&nbsp;
							服务单位人:
							<s:select name="yqfwr" list="xtusers" cssStyle="width:130" listKey="userid" listValue="cnname" headerKey="" headerValue="--请选择--" />
							&nbsp;&nbsp;&nbsp;
							
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
			if($('dwdm').value != '' ){
				RH += " and dwdm like '%"+$('dwdm').value+"%'";
			}
			if($('dwmc').value != '' ){
				RH += " and dwmc like '%"+$('dwmc').value+"%'";
			}
			if($('xm_mc').value != '' ){
				RH += " and xm_mc like '"+$('xm_mc').value+"'";
			}
			if($('dwlx').value != '' ){
				RH += " and dwlx = '"+$('dwlx').value+"'";
			}
			if($('dwzt').value != '' ){
				RH += " and dwzt = '"+$('dwzt').value+"'";
			}
			if($('nwz').value != '' ){
				RH += " and nwz = '"+$('nwz').value+"'";
			}
			if($('frdb').value != '' ){
				RH += " and frdb like '%"+$('frdb').value+"%'";
			}
			
			if($('dwgzr').value != '' ){
				RH += " and dwgzr = '"+$('dwgzr').value+"'";
			}
			if($('rzyqsj').value != '' ){
				RH += " and rzyqsj = '"+$('rzyqsj').value+"'";
			}
			
			if($('gxjsqy').value != '' ){
				RH += " and gxjsqy = '"+$('gxjsqy').value+"'";
			}
			
			if($('sscpc').value != '' ){
				RH += " and sscpc = '"+$('sscpc').value+"'";
			}
			
			if($('tdpc').value != '' ){
				RH += " and tdpc = '"+$('tdpc').value+"'";
			}
			
			if($('rjqy').value != '' ){
				RH += " and rjqy = '"+$('rjqy').value+"'";
			}
			
			if($('dmqy').value != '' ){
				RH += " and dmqy = '"+$('dmqy').value+"'";
			}
			
			if($('lxsqy').value != '' ){
				RH += " and isnull(lxsqy,0) = '"+$('lxsqy').value+"'";
			}
			if($('clrq').value != '' ){
				RH += " and isnull(clrq,'') like '"+$('clrq').value+"%'";
			}
			
			if($('dabh').value.trim() != ''){
				RH += " and isnull(dabh,'')+isnull(rzyqsj,'') like '"+$('dabh').value+"%'";
			}
			
			if($('isljx').value != ''){
				if($('isljx').value == '1'){
					RH += " and dwid in ( select distinct dwid from DW_RYXX_TR )";
				}else{
					RH += " and dwid not in ( select distinct dwid from DW_RYXX_TR )";
				}
			}
			
			if($('isgqbl').value != ''){
				if($('isgqbl').value == '1'){
					RH += " and dwid in ( select distinct dwid from dw_gqbl where isnull(tzlx,'')<>'')";
				}else{
					RH += " and dwid not in ( select distinct dwid from dw_gqbl where isnull(tzlx,'')<>'' )";
				}
			}
			
			if($('bgdd1').value != '' ){
				RH += " and isnull(bgdd1,'') = '"+$('bgdd1').value+"'";
			}
			
			if($('bgdd2').value != '' ){
				RH += " and isnull(bgdd2,'') = '"+$('bgdd2').value+"'";
			}
			if($('bgdd3').value != '' ){
				RH += " and isnull(bgdd3,'') = '"+$('bgdd3').value+"'";
			}
			
			if($('bgdd4').value.trim() != '' ){
				RH += " and isnull(bgdd4,'') = '"+$('bgdd4').value.trim()+"'";
			}
			
			
			if($('zczb').value != '' ){
				var s = $('zczb').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(zczb,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(zczb,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			if($('jzmj').value != '' ){
				var s = $('jzmj').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(jzmj,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(jzmj,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			if($('snsssrs').value != '' ){
				var s = $('snsssrs').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(SNXSSR,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(SNXSSR,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			if($('sssrzzs').value != '' ){
				var s = $('sssrzzs').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
				if(endv !='以上'){
					RH += " and ((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
				}
				RH += ')';
			}
			if($('snjnss').value != '' ){
				var s = $('snjnss').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(snjnss,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(snjnss,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			
			if($('jnsszc').value != '' ){
				var s = $('jnsszc').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (((isnull(convert(numeric(20,2),snjnss),0)-isnull(snjnss_,0))/isnull(nullif(snjnss_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
				if(endv !='以上'){
					RH += " and ((isnull(convert(numeric(20,2),snjnss),0)-isnull(snjnss_,0))/isnull(nullif(snjnss_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
				}
				RH += ')';
			}
			
			if($('sndygs').value != '' ){
				var s = $('sndygs').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(sndygs,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(sndygs,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			
			if($('ygszzl').value != '' ){
				var s = $('ygszzl').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (((isnull(convert(numeric(20,2),sndygs),0)-isnull(sndygs_,0))/isnull(nullif(sndygs_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
				if(endv !='以上'){
					RH += " and ((isnull(convert(numeric(20,2),sndygs),0)-isnull(sndygs_,0))/isnull(nullif(sndygs_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
				}
				RH += ')';
			}
			
			if($('gjcxkjyq').value != '' ){
				RH += " and gjcxkjyq like '"+$('gjcxkjyq').value+"&'";
			}
			
			
			if($('gjqdxxcy').value != '' ){
				RH += " and gjqdxxcy like '"+$('gjqdxxcy').value+"%'";
			}
			
			if($('xdfwy').value != '' ){
				RH += " and xdfwy like '"+$('xdfwy').value+"%'";
			}
			
			if($('fwwb').value != '' ){
				RH += " and fwwb like '"+$('fwwb').value+"%'";
			}
			
			if($('lhrh').value != '' ){
				RH += " and lhrh = '"+$('lhrh').value+"'";
			}
			
			
			if($('cmmi').value != '' ){
				RH += " and cmmi = '"+$('cmmi').value+"'";
			}
			
			if($('dwsb').value != '' ){
				RH += " and cmmi = '"+$('dwsb').value+"'";
			}
			
			if($('dwzycp').value != '' ){
				RH += " and dwzycp like '%"+$('dwzycp').value+"%'";
			}
			
			if($('ssctd').value != '' ){
				if($('ssctd').value == 1){
					RH += " and isnull(sscpc,'') !='' ";
				}else{
					RH += " and isnull(sscpc,'') = ''";
				}
			}
			
			if($('sssctd').value != '' ){
				if($('sssctd').value == 1){
					RH += " and isnull(tdpc,'') !='' ";
				}else{
					RH += " and isnull(tdpc,'') = ''";
				}
			}
			
			if($('cdlxrq').value != '' ){
				RH += " and dwid in ( select dwid from dw_cdxm where YEAR(LXRQ)='"+$('cdlxrq').value+"')";
			}
			
			if($('cdxmjb').value != '' ){
				RH += " and dwid in ( select dwid from dw_cdxm where xmjb='"+$('cdxmjb').value+"')";
			}
			
			if($('cdjhlb').value != '' ){
				RH += " and dwid in ( select dwid from dw_cdxm where jhlb like '"+$('cdjhlb').value+"%')";
			}
			
			if($('yqrq1').value != '' ){
				RH += " and dwid in (select dwid from dw_yqfw where convert(varchar(20),rq,23) >='"+$('yqrq1').value+"'";
				if($('yqrq2').value != '' ){
					RH += " and convert(varchar(20),rq,23) <='"+$('yqrq2').value+"'";
				}
				RH += ')';
			}
			
			if($('yqfwr').value != '' ){
				RH += " and dwid in ( select dwid from dw_yqfw where yqfwr = '"+$('yqfwr').value+"')";
			}
			
			getOpener().setHSearchT(RH);
			closeWindows();
		}
		
	</script>
</html>


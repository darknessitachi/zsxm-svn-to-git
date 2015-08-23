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
        		
        <script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
			  a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		      .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
.tableContainer2 {width: 100%;overflow: auto;margin: 0;border-bottom: solid 1px #bacddc;border-left: solid 1px #bacddc;border-right: solid 1px #bacddc;border-top: solid 1px #bacddc;}
.tableContainer2 thead tr {position:relative;top: expression(offsetParent.scrollTop);height:25px}
.tableContainer2 table tfoot tr {position: relative;overflow-x: hidden;top: expression(parentNode.parentNode.offsetHeight >= offsetParent.offsetHeight?offsetParent.offsetHeight + offsetParent.scrollTop - parentNode.parentNode.offsetHeight:offsetParent.offsetHeight - parentNode.parentNode.offsetHeight);}
.tableContainer2 td:last-child {padding-right: 20px;}

.tableContainer2 table {	margin: 0;width: 100%;background-color: #fff;border-right: solid 1px #bacddc;border-collapse: collapse;}
.tableContainer2 thead td,.tableContainer thead th,.tableContainer tfoot td {text-align: center;	background:url(../../images/skin0/table/table.png);	font-size : 12px;color: #333;height:25px;white-space: nowrap;}	

.tableContainer2  .alt td{background:#F7FBFE;}
.tableContainer2 .over td{background:#F3F3F3}
.tableContainer2 .click td{background:#BAEB5E}	

</style>
	</head>

	<body>
	<s:form name="zsdwForm" action='zsdw.do' method="post" >
	<input type="hidden" name="autodwdm" id="autodwdm" value=""/>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
	
 <!--个人信息 start-->
 <div style="width:100%">
 
	<div class="title "onclick=""><h2><span class="red fn">*</span>单位基本信息</h2><div class="img_right" id="s1" ></div></div>
	
	<div class="tableContainer2" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
    		<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>档案号</td>
				<td>
					<s:textfield name="zsdw.dabh" cssStyle="width:70px"></s:textfield>
					-
					<span id="dah2_">
						<s:property value="zsdw.rzyqsj"/>
					</span>
				</td>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">入驻园区时间</td>
				<td class="tdright" ><s:select name="zsdw.rzyqsj" list="xtdict33" cssStyle="width:140" listKey="dictname" listValue="dictname"  headerKey="" headerValue="--请选择--" onchange="setDah(this.value)"/></td>
				
				
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位类型</td>
				<td class="tdright"><s:select name="zsdw.dwlx" list="xtdict9" cssStyle="width:150" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择单位类型--" onchange="ctrlDwdm(this.value)"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>组织机构代码</td>
				<td class="tdright">
				<s:textfield cssStyle="width:90px" name="zsdw.dwdm_8" maxlength="8"></s:textfield>-<s:textfield cssStyle="width:30px" name="zsdw.dwdm_1" maxlength="1"></s:textfield>
				<s:hidden name="zsdw.dwdm"></s:hidden>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位名称</td>
				<td>
					<s:textfield name="zsdw.dwmc" cssStyle="width:150px"></s:textfield>
				</td>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">用户登入密码</td>
				<td class="tdright" ><s:textfield cssStyle="width:130px" name="zsdw.dwpassword"/></td>
				
				
			</tr>
			
			<tr>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位状态</td>
				<td class="tdright" colspan=3>
					<s:radio name="zsdw.dwzt" list="xtdict4" listKey="dictbh" listValue="dictname" value="zsdw.dwzt"></s:radio>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">法人代表</td>
				<td><s:textfield cssStyle="width:130px" name="zsdw.frdb"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">成立日期</td>
				<td class="tdright"><input type="text" class="Wdate" name="zsdw.clrq" id="date" style="text-align:left;width:130" value="<s:property value="zsdw.clrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/></td>
				
			</tr>
			
			<tr>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">内/外资</td>
				<td>
					<s:radio name="zsdw.nwz" list="xtdict5" listKey="dictbh" listValue="dictname" value="zsdw.nwz"></s:radio>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">注册资本</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.zczb" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/><span class="red fn">(人民币/万元)</span></td>
				
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">招资项目</td>
				<td>
					<INPUT id="zzxm_button" class="selectBut2"  title="选择项目" value="<s:property value="qzzxm.xmmc==''?'选择项目':qzzxm.xmmc"/>" type=button onclick="selZzxm()">
					<s:hidden name="qzzxm.xmid"></s:hidden>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">建筑面积</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.jzmj" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/><span class="red fn">(平方/米)</span></td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">办公地点</td>
				<td colspan=3>
				常州科教城
				<s:select name="zsdw.bgdd1" list="xtdict6" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				<s:select name="zsdw.bgdd2" list="xtdict7" cssStyle="width:50" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
				座
				<s:select name="zsdw.bgdd3" list="xtdict8" cssStyle="width:50" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
				层
				<s:textfield name="zsdw.bgdd4" cssStyle="width:50"></s:textfield>
				房间
				</td>
			</tr>
			<!-- 
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">行业分类</td>
				<td colspan=3>
					<s:iterator value="xtdict10">
						<input type="checkbox" name="dwsx" id='dwsx<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><s:property value="dictname"/>
					</s:iterator>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">产业分类</td>
				<td colspan=3>
					<s:iterator value="xtdict11">
						<input type="checkbox" name="cyfl"  id='cyfl<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><s:property value="dictname"/>
					</s:iterator>
				</td>
			</tr>
			 -->
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">行业分类</td>
				<td colspan=3>
					<INPUT id="rcdarcxx.hyfl_button" class="selectBut2"  title="行业分类选择" value="<s:property value="zsdw.hymc"/>" type=button onclick="selectTree(32,'zsdw.hyfl','rcdarcxx.hyfl_button');">
					<s:hidden name="zsdw.hyfl"></s:hidden>
					<div id="hyflmc"></div>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">主营产品</td>
				<td colspan=3><s:textarea name="zsdw.dwzycp" cols="55" rows="4"></s:textarea></td>
			</tr>
			
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">金凤凰单位</td>
				<td>
					<s:if test="zsdw.isjfh==1">
						<input type="radio" name="zsdw.isjfh" value="1" checked/>是
						&nbsp;&nbsp;&nbsp;
						<input type="radio" name="zsdw.isjfh" value="0" />否
					</s:if>
					<s:else>
						<input type="radio" name="zsdw.isjfh" value="1" />是
						&nbsp;&nbsp;&nbsp;
						<input type="radio" name="zsdw.isjfh" value="0" checked/>否
					</s:else>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位跟踪人</td>
				<td>
					<s:select  id="zsdw.dwgzr" name="zsdw.dwgzr" list="dwgzrs" cssStyle="width:100;" listKey="userid" listValue="cnname"  headerKey="" headerValue="--选--" />
				</td>
			</tr>	
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位简介</td>
				<td colspan=3><s:textarea name="zsdw.dwjj" cols="55" rows="4"></s:textarea></td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">税务管理码</td>
				<td><s:textfield cssStyle="width:130px" name="zsdw.swglm"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">纳税人识别号</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.nsrsbh"/></td>
				
				<s:hidden name="zsdw.snxssr"></s:hidden>
				<s:hidden name="zsdw.snjnss"></s:hidden>
				<s:hidden name="zsdw.sndygs"></s:hidden>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">备注</td>
				<td colspan=3><s:textarea name="zsdw.sm" cols="55" rows="4"></s:textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=center style="background:#5f9ea0;height:30px"><b><font color=white>单位属性</font></b></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">领军型人才</td>
				<td>
				<s:if test="zsdw.isljx>0">
					★
				</s:if>
				&nbsp;
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">股权比例</td>
				<td>
				<s:if test="zsdw.istzbl>0">
					▲
				</s:if>
				&nbsp;</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">省双创团队</td>
				<td>
					<span id="sscpc_dis">
					<s:if test="zsdw.sscpc!=''">
						是
					</s:if>
					<s:else>
						否
					</s:else>
					</span>
					<s:hidden  name="zsdw.ssctd" value =""></s:hidden>	
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">省双创批次</td>
				<td ><s:select  id="zsdw.sscpc" name="zsdw.sscpc" list="xtdict45" cssStyle="width:100;"  listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--"  onchange="pcdis(this.value,'sscpc_dis');"/></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">市双创团队</td>
				<td>
					<span id="tdpc_dis">
					<s:if test="zsdw.tdpc!=''">
						是
					</s:if>
					<s:else>
						否
					</s:else>
					</span>
					<s:hidden  name="zsdw.hgtdpc" value =""></s:hidden>		
				</td>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">市双创批次</td>
				<td>
					<s:select  id="zsdw.tdpc" name="zsdw.tdpc" list="xtdict30" cssStyle="width:100;"  listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" onchange="pcdis(this.value,'tdpc_dis');"/>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">高新技术企业</td>
				<td><s:select name="zsdw.gxjsqy" list="xtdict34" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">留学生企业</td>
				<td>
					<s:if test="zsdw.lxsqy==1">
						<input type="radio" name="zsdw.lxsqy" value="1" checked/>是
						&nbsp;&nbsp;&nbsp;
						<input type="radio" name="zsdw.lxsqy" value="0" />否
					</s:if>
					<s:else>
						<input type="radio" name="zsdw.lxsqy" value="1" />是
						&nbsp;&nbsp;&nbsp;
						<input type="radio" name="zsdw.lxsqy" value="0" checked/>否
					</s:else>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">软件企业</td>
				<td><s:select name="zsdw.rjqy" list="xtdict35" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">动漫企业</td>
				<td><s:select name="zsdw.dmqy" list="xtdict36" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">CMMI认证</td>
				<td><s:select name="zsdw.cmmi" list="xtdict42" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">商标</td>
				<td><s:select name="zsdw.dwsb" list="xtdict43" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">国家七大新兴产业(十二五期间)</td>
				<td colspan=3>
					<INPUT id="rcdarcxx.gjqdxxcy" class="selectBut2"  title="行业分类选择" value="<s:property value="zsdw.gjqdxxcy_mc"/>" type=button onclick="selectTree(38,'zsdw.gjqdxxcy','rcdarcxx.gjqdxxcy');">
					<s:hidden name="zsdw.gjqdxxcy"></s:hidden>
				</td>
			</tr>
			<tr>	
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">国家创新型科技园区(常州市)</td>
				<td colspan=3><s:select name="zsdw.gjcxkjyq" list="xtdict37" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">现代服务业</td>
				<td colspan=3>
					<s:select name="zsdw.xdfwy" list="xtdict39" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
			</tr>
			<tr>	
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">服务外包</td>
				<td colspan=3>
					<INPUT id="rcdarcxx.fwwb" class="selectBut2"  title="行业分类选择" value="<s:property value="zsdw.fwwb_mc"/>" type=button onclick="selectTree(40,'zsdw.fwwb','rcdarcxx.fwwb');">
					<s:hidden name="zsdw.fwwb"></s:hidden>
				</td>
			</tr>
			<tr>	
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">两化融合</td>
				<td colspan=3><s:select name="zsdw.lhrh" list="xtdict41" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
			</tr>
			
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">引进类别</td>
				<td colspan=3>
					<INPUT id="rcdarcxx.nation_button" class="selectBut2"  title="选择引进类别" value="选择引进类别" type=button onclick="O_D('rcdarcxx.nation_button','yjlbd','bottom');">
					<div id="yjlbmc"></div>
				</td>
			</tr>	
			
		</table>
<div id='yjlbd' class="fsg_nr" style="display:none;width:360;height:210;">	
	<div loading='0' id='yjlbdd' style="width:360;height:190;background-color:#E7EAF7;overflow:auto;">
		<s:iterator value="xtdict13">
			<input type="checkbox" name="yjlb" id='yjlb<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><span id='ch<s:property value="dictbh"/>'><s:property value="dictname"/></span><br>
		</s:iterator>
	</div>
	
	<div class="footer" style="background:#f5f5f5" style="width:360;height:20;text-align:right">
			<input class="button3" type="button" value="确  定" onclick="selTrue();"/>
		&nbsp&nbsp&nbsp&nbsp
	</div>
	<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
</div>	

	
	<div id='xtdlb32' class="fsg_nr" style="display:none;width:240;height:200;">
		<div loading='0' id='xtdlb32load' style="width:240;height:180;overflow:auto;">
			<div id="xtdlb32loading"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		</div>
		<div class="footer" style="background:#6495ed" style="width:240;text-align:right">
				<input type="button" value="确  定" onclick="setVByTree('xtdlb32');"/>
			&nbsp&nbsp&nbsp&nbsp
		</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	</div>
	</div>
	<div  style="width:770px;text-align:center">
		<input class="btn_submit1" type="button" value="保    存" onclick="javascript:doSave(1);"/>
		<input class="btn_submit1" type="button" value="保存并下一步" onclick="javascript:doSave(2);"/>
		<s:if test="opttype==3">
				<input class="btn_submit1" type="button" value="保存并退出" onclick="javascript:doSave(4);"/>
			</s:if>
			<s:else>
				<input class="btn_submit1" type="button" value="填写完成" onclick="javascript:doSave(3);"/>
		</s:else>
	</div>

	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 80)+"px"; 
		/**
		<s:iterator value="dwsxs">
			$('dwsx<s:property value="seldm"/>').checked = true;
		</s:iterator>
		
		
		<s:iterator value="cyfls">
			$('cyfl<s:property value="seldm"/>').checked = true;
		</s:iterator>
		**/
		
		<s:iterator value="jszys">
			$('jszy<s:property value="seldm"/>').checked = true;
			if('<s:property value="seldm"/>'=='999'){
				$('jszyothermc').value = '<s:property value="othermc"/>';
			}
		</s:iterator>
		
		
		<s:iterator value="yjlbs">
			$('yjlb<s:property value="seldm"/>').checked = true;
			$('yjlbmc').innerHTML += '<s:property value="selmc"/>&nbsp|&nbsp';
		</s:iterator>
		
		
		function setDah(v){
			$('dah2_').innerText = v;
		}
		function ctrlDwdm(v){
			if(v=='007'){
				$('zsdw.dwdm').value='';
				if($('autodwdm').value == ''){
					var ajax = new AppAjax("zsdw!getAutoDwdm.do",function(data){
						$('zsdw.dwdm_8').value=data.autodwdm;
						$('autodwdm').value = data.autodwdm;
					}).submit();
				}else{
					$('zsdw.dwdm_8').value=$('autodwdm').value;
				}
				$('zsdw.dwdm_1').value='1';
			}else{
				$('zsdw.dwdm_8').value='';
				$('zsdw.dwdm_1').value='';
				$('zsdw.dwdm').value='';
			}
		}
		function doSave(type){
			
			if($('zsdw.dwlx').value.trim() ==''){
				alert('请选择单位类型！');
				return false;
			}
			
			if($('zsdw.dwdm_8').value.trim() == ''){
				alert('请填写组织机构代码的前<8>位!');
				return false;
			}
			if($('zsdw.dwdm_8').value.trim().length !=8 ){
				alert('前面组织机构代码必需<8>位!');
				return false;
			}
			if($('zsdw.dwdm_1').value.trim() == ''){
				alert('请填写组织机构代码的后<1>位!');
				return false;
			}
			if($('zsdw.dwdm_1').value.trim().length !=1 ){
				alert('后面组织机构代码必需<1>位!');
				return false;
			}
			$('zsdw.dwdm').value = $('zsdw.dwdm_8').value.trim()+'-'+$('zsdw.dwdm_1').value.trim();
			
			if($('zsdw.dwmc').value.trim() ==''){
				alert('单位名称不能为空！');
				return false;
			}
			
			
			
			var ajax = new AppAjax("zsdw!doSaveZsdw.do",function(data){save_Back(data,type)}).submitForm("zsdwForm");
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				$('dwid').value=data.message.text;
				parent.reload = 1;
				if(type == 2){//保存下一步
					parent.setDwid(data.message.text,2,true);
				}else if(type==3){//填写完成
					parent.setDwid('',1,true);
				}else if(type==4){
					parent.closeWindows();
				}else{
					alert('保存成功!');
					parent.setDwid(data.message.text,1,false);
				}
			}else{
				alert(data.message.text);
			}
		}
		
		function selTrue(){
			$('yjlbd').style.display = 'none';
			var ych = RC.checkbox('yjlb');
			$('yjlbmc').innerHTML = '';
			for(var y=0;y<ych.length;y++){
				$('yjlbmc').innerHTML += $('ch'+ych[y].v).innerHTML +'&nbsp|&nbsp';
			}
		}
		
		function selZzxm(){
			parent.openWin("zsdw!getZsxmWaitSelect.do?pname="+$('pname').value,"650","400");
		}
		
		function setZzxm(id,mc){
			$('qzzxm.xmid').value=id;
			$('zzxm_button').value = mc;
		}
		
		function setVByTree(id_dm,id_mc,dm,mc){
			$(id_dm).value = dm;
			$(id_mc).value = mc;
		}
		
		function hgchecked(v){
			if(v=='1'){
				$('zsdw.hgtdpc').readOnly = false;
				$('zsdw.hgtdpc').style.background="white";
				$('zsdw.tdpc').disabled = false;
			}else{
				$('zsdw.hgtdpc').readOnly = true;
				$('zsdw.hgtdpc').style.background="#efefef";
				$('zsdw.hgtdpc').value="";
				$('zsdw.tdpc').disabled = true;
			}
		}
		function shgchecked(v){
			if(v=='1'){
				$('zsdw.ssctd').readOnly = false;
				$('zsdw.ssctd').style.background="white";
				$('zsdw.sscpc').disabled = false;
			}else{
				$('zsdw.ssctd').readOnly = true;
				$('zsdw.ssctd').style.background="#efefef";
				$('zsdw.ssctd').value="";
				$('zsdw.sscpc').disabled = true;
			}
		}
		
		function selectTree(lbid,calldm,callmc){
			parent.openWin("zsdw!preloadtree.do?lbid="+lbid+"&calldm="+calldm+"&callmc="+callmc+"&pname="+$('pname').value,"400","400");
		}
		
		var tree;
		/**
		function loadHyflTree(){
			if($("xtdlb32").loading != 1){
				tree=new dhtmlXTreeObject("xtdlb32load","100%","100%",0);
				tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				//tree.setOnClickHandler(function(id){setVByTree(id);});
				tree.enableSmartXMLParsing(true);
				tree.setDataMode("json");
				tree.enableThreeStateCheckboxes(true);
				tree.enableCheckBoxes(true);
				tree.loadJSON("zsdw!doLoadHyflTree.do?dwid="+$('dwid').value,function(){$('xtdlb32loading').style.display="none";});
				$('xtdlb32').loading = 1;			
			}
		}
		
		function setVByTree(id){
			$('zsdw.hyfl').value=tree.getAllCheckedBranches();
			setHymc();
			noneDiv(id);
		}
		**/
		
		function setHymc(){
			var hv = $('zsdw.hyfl').value;
			var hmc = '';
			$('hyflmc').innerHTML = '';
			if(hv != null && hv!=''){
				var hvs = hv.split(',');
				for(var i=0;i<hvs.length;i++){
					
					if(hvs[i]!='root' && hvs[i].length > 3){
						hmc += '&nbsp|&nbsp'+tree.getItemText(hvs[i]);
					}
				}
			}
			$('hyflmc').innerHTML = hmc;
		}
		function noneDiv(id){
			$(id).style.display='none';
		}
		
		
		function pcdis(v,id){
			if(v!=''){
				$(id).innerHTML = '是';
			}else{
				$(id).innerHTML = '否';
			}
		}
			
		function O_D(obj1, obj2, location) { //打开div在打开元素的什么位置，obj1:点击的元素，obj2:要打开的div;location:left,top,right,bottom
			var btn = document.getElementById(obj1);
			var obj = document.getElementById(obj2);
			var h = btn.offsetHeight;
			var w = btn.offsetWidth;
			var x = btn.offsetLeft;
			var y = btn.offsetTop;
			
			while (btn = btn.offsetParent) {
				y += btn.offsetTop;
				x += btn.offsetLeft;
			}
		
			var hh = obj.offsetHeight;
			var ww = obj.offsetWidth;
			var xx = obj.offsetLeft;// style.left;
			var yy = obj.offsetTop;// style.top;
		
			var obj2location = location.toLowerCase();
		
			var showx, showy;
		
			if (obj2location == "left" || obj2location == "l" || obj2location == "top"
					|| obj2location == "t" || obj2location == "u"
					|| obj2location == "b" || obj2location == "r"
					|| obj2location == "up" || obj2location == "right"
					|| obj2location == "bottom") {
				if (obj2location == "left" || obj2location == "l") {
					showx = x - ww;
					showy = y;
				}
				if (obj2location == "top" || obj2location == "t" || obj2location == "u") {
					showx = x;
					showy = y - hh;
				}
				if (obj2location == "right" || obj2location == "r") {
					showx = x + w;
					showy = y;
				}
				if (obj2location == "bottom" || obj2location == "b") {
					showx = x;
					showy = y + h;
				}
			} else {
				showx = xx;
				showy = yy;
			}
			obj.style.left = showx + "px";
			obj.style.top = (showy-30) + "px";
			if (obj.style.display == "block") {
				obj.style.display = "none";
			} else {
				obj.style.display = "block";
			}
		}
	</script>
</html>


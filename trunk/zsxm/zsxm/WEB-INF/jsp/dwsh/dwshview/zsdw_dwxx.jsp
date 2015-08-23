<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
        <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
        <script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
<style>
	.ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px}
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		      .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		      .t10 { 
				    BORDER-RIGHT: #ffff00 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #ffff00 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; BORDER-LEFT: #ffff00 1px solid; COLOR: #ffff00; PADDING-TOP: 5px; BORDER-BOTTOM: #ffff00 1px solid; HEIGHT: 20px; BACKGROUND-COLOR:#2f4f4f 
				} 
				.t10:hover { 
				    BORDER-RIGHT: #0000ff 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #0000ff 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; BORDER-LEFT: #0000ff 1px solid; COLOR: #333333; PADDING-TOP: 5px; BORDER-BOTTOM: #0000ff 1px solid; HEIGHT: 20px; BACKGROUND-COLOR: #c8d8f0 
				}
</style>
	</head>

	<body>
	<s:form name="zsdwForm" id="zsdwForm" action='zsdw!preZsdw.do' method="post" >
	<input type="hidden" name="autodwdm" id="autodwdm" value=""/>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
	
	<s:hidden name="zsdw.dwpassword"></s:hidden>
	<s:hidden name="zsdw.dah"></s:hidden>
	<s:hidden name="zsdw.isjfh"></s:hidden>

 <!--个人信息 start-->
 <div style="width:100%">
 <div class="title"  onclick=""><h2> 单位基本信息 &nbsp&nbsp&nbsp&nbsp&nbsp
	
	</h2>
	<div class="img_right" id="s1" ></div>
	</div>
	<div class="tableContainer" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位类型</td>
				<td class="tdright"><s:select id="zsdw.dwlx" name="zsdw.dwlx" list="xtdict9" cssStyle="width:150" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择单位类型--"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>组织机构代码</td>
				<td class="tdright">
				<s:property value="zsdw.dwdm_8"/>-<s:property value="zsdw.dwdm_1"/>
				<s:hidden name="zsdw.dwdm"></s:hidden>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位名称</td>
				<td>
					<s:textfield id="zsdw.dwmc" name="zsdw.dwmc" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px"></td>
				<td class="tdright">
					&nbsp;
				</td>
				
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
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位简介</td>
				<td colspan=3><s:textarea name="zsdw.dwjj" cols="55" rows="4"></s:textarea></td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">税务管理码</td>
				<td><s:textfield cssStyle="width:130px" name="zsdw.swglm"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">纳税人识别号</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.nsrsbh"/></td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">备注</td>
				<td colspan=3><s:textarea name="zsdw.sm" cols="55" rows="4"></s:textarea></td>
			</tr>
			
			<tr>
				<td colspan=4 align=center style="background:#5f9ea0;height:30px"><b><font color=white>单位属性</font></b></td>
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
					<div id="yjlbmc"></div>
				</td>
			</tr>	
		</table>
		
					
		<div id='yjlbd' class="fsg_nr" style="display:none;width:360;height:240;">	
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
		
	
	</div>

	
	</div>
	
		
	
	<div id="butdiv" style="width:770px;text-align:center">
		
	</div>
	

	</s:form>
	
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 60)+"px"; 
		
		<s:iterator value="yjlbs">
			$('yjlb<s:property value="seldm"/>').checked = true;
			$('yjlbmc').innerHTML += '<s:property value="selmc"/>&nbsp|&nbsp';
		</s:iterator>
		
		init();
		function init(){
			var xx = document.getElementsByTagName("input");
			for(var i=0;i<xx.length;i++){
				xx[i].disabled = true;
			}
			xx = document.getElementsByTagName("select");
			for(var i=0;i<xx.length;i++){
				xx[i].disabled = true;
			} 
			xx = document.getElementsByTagName("textarea");
			for(var i=0;i<xx.length;i++){
				xx[i].disabled = true;
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
			obj.style.top = (showy-47) + "px";
			if (obj.style.display == "block") {
				obj.style.display = "none";
			} else {
				obj.style.display = "block";
			}
		}		
	</script>
</html>


<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
        <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='单位信息预览'></s:component>
	<s:form name="zsxmForm" action='zsxm!preZsxmU.do' method="post" >

	<s:hidden name="xmid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->

<div id="zmain" style="width:100%px;overflow:auto" >
	<div class="title "onclick=""><h2><span class="red fn">*</span> 单位基本信息</h2><div class="img_right" id="s1" ></div></div>
	
	<div class="tableContainer" id="tableContainer" >
    	<table class="fxtable"  cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位类型</td>
				<td class="tdright">
				<s:property value="maps.dwxx.dwlx_mc"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px"><span class="red fn">*</span>组织机构代码</td>
				<td class="tdright">
				<s:property value="maps.dwxx.dwdm"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位名称</td>
				<td>
					<s:property value="maps.dwxx.dwmc"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">单位状态</td>
				<td class="tdright">
					<s:property value="maps.dwxx.dwzt_mc"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">招资项目</td>
				<td>
					<s:property value="maps.dwxx.xm_mc"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">内/外资</td>
				<td>
					<s:property value="maps.dwxx.nwz_mc"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">引进类别</td>
				<td colspan=3>
					<s:iterator value="maps.yjlbs">
						<s:property value="seldm_mc"/>&nbsp|&nbsp
					</s:iterator>
				</td>
			</tr>	
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">用户登入名</td>
				<td><s:property value="maps.dwxx.loginname"/>&nbsp</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">密码</td>
				<td class="tdright"><s:property value="maps.dwxx.password"/>&nbsp</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">法人代表</td>
				<td><s:property value="maps.dwxx.frdb"/>&nbsp</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">成立日期</td>
				<td class="tdright">
				<s:property value="maps.dwxx.clrq"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位简介</td>
				<td colspan=3>
				<s:property value="maps.dwxx.dwjj"/>&nbsp
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">办公地点</td>
				<td colspan=3>
				常州科教城
				<s:property value="maps.dwxx.bgdd1_mc"/>-<s:property value="maps.dwxx.bgdd2_mc"/>
				-<s:property value="maps.dwxx.bgdd3_mc"/>-<s:property value="maps.dwxx.bgdd4"/>
				
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">注册资本</td>
				<td class="tdright">
				<s:property value="maps.dwxx.zczb"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">建筑面积</td>
				<td class="tdright">
				<s:property value="maps.dwxx.jzmj"/>&nbsp
				</td>
				
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">行业分类</td>
				<td colspan=3>
					<s:iterator value="maps.dwsxs">
						<s:property value="seldm_mc"/>&nbsp|&nbsp
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">产业分类</td>
				<td colspan=3>
					<s:iterator value="maps.cyfls">
						<s:property value="seldm_mc"/>&nbsp|&nbsp
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">技术专业</td>
				<td colspan=3>
					<s:iterator value="maps.jszys">
						<s:if test="dictbh=='999'">
							<s:property value="seldm_mc"/>
							(<s:property value="othermc"/>)
						</s:if>
						<s:else>
							<s:property value="seldm_mc"/>
						</s:else>
						&nbsp|&nbsp
					</s:iterator>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">税务管理码</td>
				<td class="tdright">
				<s:property value="maps.dwxx.swglm"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">纳税人识别号</td>
				<td class="tdright">
					<s:property value="maps.dwxx.nsrsbh"/>&nbsp
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">海归团队批次</td>
				<td class="tdright" colspan=3>
				<s:property value="maps.dwxx.hgtdpc"/>&nbsp
				</td>
				
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">备注</td>
				<td class="tdright" colspan=3>
				<s:property value="maps.dwxx.sm"/>&nbsp
				</td>
				
			</tr>
			
		</table>
	</div>
	<div class="title "onclick=""><h2>联系人</h2><div class="img_right" id="s1" ></div></div>
	<div  class="tableContainer" id="tableContainer1" style="width:100%">
		<table id="tac">
			<thead>
				<tr>
					
					<td width="50px">序号</td>
					<td>姓名</td>
					<td>性别</td>
					<td>职称</td>
					<td>职务</td>
					<td>电话</td>
					<td>手机</td>
					<td>邮箱</td>
					<td>QQ</td>
					<td>所属部门</td>
					<td width="90">第一联系人</td>
				</tr>
			</thead>
			<s:iterator value="maps.lxr">
				<tr>
					
						<td><s:property value="xh"/></td>
						<td><s:property value="lxrxm"/></td>
						<td>
							<s:property value="sex_mc"/>
						</td>
						<td>
							<s:property value="zc_mc"/>
						</td>
						<td>
							<s:property value="zw"/>
						</td>
						<td>
							<s:property value="tel"/>
						</td>
						<td>
							<s:property value="phone"/>
						</td>
						<td>
							<s:property value="email"/>
						</td>
						<td>
							<s:property value="qq"/>
						</td>
						<td>
							<s:property value="ssbm"/>
						</td>
						<td align="center">
							<s:if test="dylxrbz==1">
								是
							</s:if>
							<s:else>
								&nbsp
							</s:else>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	<div class="title "onclick=""><h2>单位股权比例</h2><div class="img_right" id="s1" ></div></div>
	<div  class="tableContainer" id="tableContainer1" style="width:100%">
		<table id="tac">
			<thead>
				<tr>
					
					<td width="50px">序号</td>
					<td>姓名(名称)</td>
					<td>证照号码</td>
					<td>投资额(万元)</td>
					<td>投资比例</td>
				</tr>
			</thead>
			<s:iterator value="maps.gqbl">
				<tr>
					
						<td><s:property value="xh"/></td>
						<td>
							<s:property value="xm"/>
						</td>
						<td>
							<s:property value="zjno"/>
						</td>
						<td>
							<s:property value="tzje"/>
						</td>
						<td>
							<s:property value="tzbl"/>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	<div class="title " onclick=""><h2>承担纵向项目情况</h2><div class="img_right" id="s1" ></div></div>
	<div  class="tableContainer" id="tableContainer2" style="width:100%">
		<table id="tac">
			<thead>
				<tr>
					
					<td width="50px">序号</td>
					<td>项目名称</td>
					<td>立项编号</td>
					<td>立项级别</td>
					<td>计划类别</td>
					<td>立项金额</td>
					<td>立项日期</td>
					<td>项目执行期</td>
				</tr>
			</thead>
			<s:iterator value="maps.cdxm">
				<tr>
					
						<td><s:property value="xh"/></td>
						<td>
							<s:property value="xmmc"/>
						</td>
						<td>
							<s:property value="xmbh"/>
						</td>
						<td>
							<s:property value="xmjb_mc"/>
						</td>
						<td>
							<s:property value="jhlb_mc"/>
						</td>
						<td>
							<s:property value="%{FormatNumber(xmje)}"/>
						</td>
						<td>
							<s:property value="lxrq"/>
						</td>
						<td>
							<s:property value="strdate"/>
							至
							<s:property value="enddate"/>
						</td>
				</tr>				
			</s:iterator>
			<tr>
				<td colspan=2 align=center><font color=red>合计:</font></td>
				<td></td><td></td><td></td>
				<td><s:property value="%{FormatNumber(maps.cdxmhjje)}"/></td>
				<td></td><td></td>
			</tr>
			
		</table>		
	</div>
	<div class="title "onclick=""><h2>拥有知识产权情况</h2><div class="img_right" id="s1" ></div></div>
	<div  class="tableContainer" id="tableContainer4" style="width:100%">
		<table id="tac">
			<thead>
				<tr>
					
					<td width="50px">序号</td>
					<td>名称</td>
					<td>专利(著作权)</td>
					<td>类型</td>
				</tr>
			</thead>
			<s:iterator value="maps.zscq">
				<tr>
					
						<td><s:property value="xh"/></td>
						<td>
							<s:property value="zsmc"/>
						</td>
						<td>
							<s:property value="zsno"/>
						</td>
						<td>
							<s:property value="zslx"/>
						</td>
						
				</tr>				
			</s:iterator>
		</table>		
	</div>
	<div class="title "onclick=""><h2>奖励情况</h2><div class="img_right" id="s1" ></div></div>
	<div  class="tableContainer" id="tableContainer5" style="width:100%">
		<table id="tac">
			<thead>
				<tr>
					
					<td width="50px">序号</td>
					<td>项目名称</td>
					<td>获奖时间</td>
					<td>获奖级别</td>
					<td>获奖类别</td>
					<td>获取资助金额</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="maps.hjqk">
				<tr>
					
						<td><s:property value="xh"/></td>
						<td>
							<s:property value="jxmc"/>
						</td>
						<td>
							<s:property value="hjrq"/>
						</td>
						<td>
							<s:property value="hjjb_mc"/>
						</td>
						<td>
							<s:property value="hjlb_mc"/>
						</td>
						<td align="right">
							<s:property value="%{FormatNumber(zzje)}"/>
						</td>
						<td>
							<s:property value="sm"/>
						</td>	
				</tr>				
			</s:iterator>
		</table>		
	</div>
	
	<div class="title "onclick=""><h2>机构人员情况</h2><div class="img_right" id="s1" ></div></div>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table id="tac">
			<thead>
				<tr>
					<td width="20px"><input type="checkbox" id="allcheckbox" onclick="RC.checkboxAll('dmkey',this.checked)"></td>
					<td width="50px">序号</td>
					<td>姓名</td>
					<td>性别</td>
					<td>出生年月</td>
					<td>学历/学位</td>
					<td>职称/职务</td>
					<td>海外留学情况</td>
					<td>研究方向</td>
					<td>起聘时间</td>
					<td>专兼职</td>
				</tr>
			</thead>
			<s:iterator value="maps.ryxx">
				<tr>
					<td><input type="checkbox" id="dmkey" name="dmkey" value='<s:property value="xh"/>'></td>
						<td><s:property value="xh"/></td>
						<td><s:property value="xm"/></td>
						<td>
							<s:property value="sex_mc"/>
						</td>
						<td>
							<s:property value="birthday"/>
						</td>
						<td>
							<s:property value="xl_mc"/>
						</td>
						<td>
							<s:property value="zc_mc"/>
						</td>
						<td>
							<s:property value="hwlxqk"/>
						</td>
						<td>
							<s:property value="yjfx"/>
						</td>
						
						<td>
							<s:property value="qpsj"/>
						</td>
						<td align="center">
							<s:if test="zjz==1">
								专职
							</s:if>
							<s:else>
								兼职
							</s:else>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
</div>
	</s:form>
	
	<script type="text/javascript">
		$('zmain').style.height = (getSize().h - 30)+"px"; 
	</script>
	</body>

</html>


<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		
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

	<body style="text-align:center;overflow:auto">
	
	<s:form name="zsxmForm" action='' method="post" >

	<s:hidden name="xmid"></s:hidden>

 <div style="width:95%" id="mh">
 
	<div id="tableContainer" style="height:90%;overflow:auto">
    	<table class="fxtable"  cellpadding="0">
    		<tr>
    			<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">档案编号</td>
				<td colspan=3>
					<s:property value="zsdw.dabh"/>&nbsp;
				</td>
			</tr>
    		<tr>
    			<td colspan="4" style="text-align:center;background:white"><b><font size=4>常州科教城入驻机构情况一览表</font></b></td>
    		</tr>
    		<tr>
    			<td colspan="4" style="background:#efefef"><b>一、单位概况</b></td>
    		</tr>
    		
    		
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">单位类型</td>
				<td>
					<s:property value="zsdw.dwlx_mc"/>&nbsp;
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">组织机构代码</td>
				<td>
					<s:property value="zsdw.dwdm"/>&nbsp;
				</td>
				
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">单位名称</td>
				<td colspan=3>
					<s:property value="zsdw.dwmc"/>&nbsp;
				</td>
				
			</tr>
			
			<tr>
				
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">单位状态</td>
				<td>
					<s:property value="zsdw.dwzt_mc"/>&nbsp;
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">入驻园区时间</td>
				<td >
					<s:property value="zsdw.rzyqsj"/>&nbsp;
				</td>
				
			</tr>
			
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">法人代表</td>
				<td><s:property value="zsdw.frdb"/>&nbsp;</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">成立日期</td>
				<td><s:property value="zsdw.clrq"/>&nbsp;</td>
				
			</tr>
			
			<tr>
				
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">内/外资</td>
				<td>
					<s:property value="zsdw.nwz_mc"/>&nbsp;

				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">注册资本</td>
				<td>
					<s:property value="zsdw.zczb"/>&nbsp;
				
				</td>
				
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">招资项目</td>
				<td>
					<s:property value="qzzxm.xmmc"/>&nbsp;
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">建筑面积</td>
				<td>
					<s:property value="zsdw.jzmj"/>&nbsp;
				</td>
				
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">办公地点</td>
				<td colspan=3>
				
				常州科教城
				<s:property value="zsdw.bgdd1_mc"/>&nbsp;
				<s:property value="zsdw.bgdd2_mc"/>&nbsp;
				座
				<s:property value="zsdw.bgdd3_mc"/>&nbsp;
				层
				<s:property value="zsdw.bgdd4"/>&nbsp;
				房间
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">行业分类</td>
				<td colspan=3>
					<s:property value="zsdw.hymc"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">主营产品</td>
				<td colspan=3>
					<s:property value="zsdw.dwzycp"/>&nbsp;
				</td>
			</tr>
			
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">金凤凰单位</td>
				<td>
					<s:if test="zsdw.isjfh==1">
						是
						
					</s:if>
					<s:else>
						否
					</s:else>
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">单位跟踪人</td>
				<td>
					<s:property value="zsdw.dwgzr_mc"/>&nbsp;
				</td>
			</tr>	
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">单位简介</td>
				<td colspan=3>
					<s:property value="zsdw.dwjj"/>&nbsp;
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">税务管理码</td>
				<td><s:property value="zsdw.swglm"/>&nbsp;</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">纳税人识别号</td>
				<td><s:property value="zsdw.nsrsbh"/>&nbsp;</td>
				
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">备注</td>
				<td colspan=3><s:property value="zsdw.sm"/>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=4 align=left style="background:#efefef;height:30px"><b>二、单位属性</b></td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">领军型人才</td>
				<td>
				<s:if test="zsdw.isljx>0">
					★
				</s:if>
				&nbsp;
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">股权比例</td>
				<td>
				<s:if test="zsdw.istzbl>0">
					▲
				</s:if>
				&nbsp;</td>
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">省双创团队</td>
				<td>
					<span id="sscpc_dis">
					<s:if test="zsdw.sscpc!=''">
						是
					</s:if>
					<s:else>
						否
					</s:else>
					</span>
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">省双创批次</td>
				<td >
					<s:property value="zsdw.sscpc_mc"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">市双创团队</td>
				<td>
					<span id="tdpc_dis">
					<s:if test="zsdw.tdpc!=''">
						是
					</s:if>
					<s:else>
						否
					</s:else>
					</span>
				</td>
				
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">市双创批次</td>
				<td>
					<s:property value="zsdw.tdpc_mc"/>&nbsp;
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">高新技术企业</td>
				<td><s:property value="zsdw.gxjsqy_mc"/>&nbsp;</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">留学生企业</td>
				<td>
					<s:if test="zsdw.lxsqy==1">
						是
						
					</s:if>
					<s:else>
						否
					</s:else>
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">软件企业</td>
				<td><s:property value="zsdw.rjqy_mc"/>&nbsp;</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">动漫企业</td>
				<td><s:property value="zsdw.dmqy_mc"/>&nbsp;</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">CMMI认证</td>
				<td><s:property value="zsdw.cmmi_mc"/>&nbsp;</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">商标</td>
				<td><s:property value="zsdw.dwsb_mc"/>&nbsp;</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">国家七大新兴产业(十二五期间)</td>
				<td colspan=3>
					<s:property value="zsdw.gjqdxxcy_mc"/>&nbsp;
				</td>
			</tr>
			<tr>	
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">国家创新型科技园区(常州市)</td>
				<td colspan=3>
				<s:property value="zsdw.gjcxkjyq_mc"/>&nbsp;
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">现代服务业</td>
				<td colspan=3>
					<s:property value="zsdw.xdfwy_mc"/>&nbsp;
				</td>
			</tr>
			<tr>	
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">服务外包</td>
				<td colspan=3>
					<s:property value="zsdw.fwwb_mc"/>&nbsp;
				</td>
			</tr>
			<tr>	
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">两化融合</td>
				<td colspan=3>
					<s:property value="zsdw.lhrh_mc"/>&nbsp;
				</td>
			</tr>
			
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">引进类别</td>
				<td colspan=3>
					<div id="yjlbmc"></div>
				</td>
			</tr>	
			
		</table>
	</div>
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">三、联系人</td>
			</tr>
		</table>
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
					<td width="150px">备注</td>
					<td width="90">第一联系人</td>
				</tr>
			</thead>
			<s:iterator value="qlist">
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
						<td width="150px">
							<s:property value="sm"/>
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
	
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">四、股权比例</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>姓名(名称)</td>
					<td>证照号码</td>
					<td>投资额(万元)</td>
					<td>投资比例</td>
					<td>股权投资</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist1">
				<tr>
						<td><s:property value="xh"/></td>
						<td>
							<s:property value="xm"/>
						</td>
						<td>
							<s:property value="zjno"/>
						</td>
						<td align=right>
							<s:property value="tzje"/>
						</td>
						<td>
							<s:property value="tzbl"/>
						</td>
						<td>
							<s:property value="tzlx_mc"/>
						</td>
						<td>
							<s:property value="sm"/>
						</td>
				</tr>				
			</s:iterator>
		</table>	
	</div>
	
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">五、园区承诺优惠条件</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>项目执行期</td>
					<td>优惠类别</td>
					<td>优惠内容</td>
					<td>优惠金额</td>
					<td>园区承诺人</td>
					<td width="150px">备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist2">
				<tr>
						<td><s:property value="xh"/></td>
						<td><s:property value="stryhq"/>--<s:property value="endyhq"/></td>
						<td>
							<s:property value="yhlb_mc"/>
						</td>
						<td>
							<s:property value="yhnr"/>
						</td>
						<td>
							<s:property value="yhje"/>
						</td>
						<td>
							<s:property value="yhcnr_mc"/>
						</td>
						<td width="150px">
							<s:property value="sm"/>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">六、领军人才情况</td>
			</tr>
		</table>
		<table id="tac" >
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>姓名</td>
					<td>性别</td>
					<td>出生年月</td>
					<td>学历/学位</td>
					<td>职称/职务</td>
					<td>海外留学情况</td>
					<td>研究方向</td>
					<td>领军型人才</td>
					<td>专兼职</td>
					<td width="150px">备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist3">
				<tr>
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
							<s:property value="ljx_mc"/>
						</td>
						<td align="center">
							<s:if test="zjz==1">
								专职
							</s:if>
							<s:else>
								兼职
							</s:else>
						</td>
						<td width="150px">
							<s:property value="sm"/>
						</td>
				</tr>				
			</s:iterator>
		</table>			
	</div>
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">七、拥有知识产权情况</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>申请年份</td>
					<td>类型</td>
					<td>专利(著作权)</td>
					<td>名称</td>
					<td>申请人或专利权人或著作权人</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist4">
				<tr>
						<td><s:property value="xh"/></td>
						<td>
							<s:property value="sqnf"/>
						</td>
						<td>
							<s:property value="zslx_mc"/>
						</td>
						<td>
							<s:property value="zsno"/>
						</td>
						<td>
							<s:property value="zsmc"/>
						</td>
						<td>
							<s:property value="sqr"/>
						</td>
						
						<td>
							<s:property value="sm"/>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">八、承担纵向项目情况</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>立项年份</td>
					<td>项目名称</td>
					<td>文号</td>
					<td>立项编号</td>
					<td>立项级别</td>
					<td>计划类别</td>
					<td>立项金额(万元)</td>
					
					<td>项目执行期</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist5" id="qlist" status="listStat">
				<tr>
						<td> <s:property value="#listStat.index+1"/></td>
						<td>
							<s:property value="lxrq"/>
						</td>
						<td>
							<s:property value="xmmc"/>
						</td>
						<td>
							<s:property value="xmwh"/>
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
							<s:property value="xmje"/>
						</td>
						
						<td>
							<s:property value="strdate"/>
							至
							<s:property value="enddate"/>
						</td>
						<td>
							<s:property value="sm"/>
						</td>
				</tr>				
			</s:iterator>
		</table>			
	</div>
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">九、承担横向项目情况</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>年份</td>
					<td>项目名称</td>
					<td>对方单位名称</td>
					<td>邮政编码</td>
					<td>合同类别</td>
					<td>合同金额(万元)</td>
					<td>达产后预计销售(万元)</td>
					<td>项目负责人</td>
					<td>合同签订日期</td>
					
					<td>项目执行期</td>
					<td>地区</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist6">
				<tr>
						<td><s:property value="xh"/></td>
						<td>
							<s:property value="xmnf"/>
						</td>
						<td>
							<s:property value="xmmc"/>
						</td>
						<td>
							<s:property value="dfdwmc"/>
						</td>
						<td>
							<s:property value="yzbm"/>
						</td>
						<td>
							<s:property value="htlb_mc"/>
						</td>
						<td>
							<s:property value="htje"/>
						</td>
						<td>
							<s:property value="yjxx"/>
						</td>
						<td>
							<s:property value="xmfzr"/>
						</td>
						<td>
							<s:property value="htqdrq"/>
						</td>
						
						<td>
							<s:property value="strdate"/>
							至
							<s:property value="enddate"/>
						</td>
						<td>
							<s:property value="dq_mc"/>
						</td>
						<td>
							<s:property value="sm"/>
						</td>
				</tr>				
			</s:iterator>
		</table>			
	</div>
	
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">十、实验室、研发中心建设情况表</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>建设年份</td>
					<td>名称</td>
					<td>合建单位</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist7" id="qlist7" status="listStat">
				<tr>
						<td> <s:property value="#listStat.index+1"/></td>
						<td>
							<s:property value="jsnf"/>
						</td>
						<td>
							<s:property value="jsmc"/>
						</td>
						<td>
							<s:property value="hjdw"/>
						</td>
						
						<td>
							<s:property value="bz"/>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">十一、研发机构孵化企业情况</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>孵化企业名称</td>
					<td>内资/外资</td>
					<td>注册资本(万元)</td>
					<td>成立日期</td>
					<td>研发机构占股(%)</td>
					<td>其他股东占股情况(%)</td>
					<td>主要联络人</td>
					<td>联系电话</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist8" id="qlist8" status="listStat">
				<tr>
						<td> <s:property value="#listStat.index+1"/></td>
						<td>
							<s:property value="rhqymc"/>
						</td>
						<td>
							<s:if test="nwz==1">
								内资
							</s:if>
							<s:else>
								外资
							</s:else>
							
						</td>
						<td>
							<s:property value="zczb"/>
						</td>
						<td>
							<s:property value="clrq"/>
						</td>
						<td>
							<s:property value="yfjgzg"/>
						</td>
						<td>
							<s:property value="qtgdzg"/>
						</td>
						<td>
							<s:property value="zylxr"/>
						</td>
						<td>
							<s:property value="lxtel"/>
						</td>
						<td>
							<s:property value="bz"/>
						</td>
				</tr>				
			</s:iterator>
		</table>	
	</div>
	
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">十二、奖励情况</td>
			</tr>
		</table>
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
			<s:iterator value="qlist9">
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
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">十三、历史数据</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>数据期</td>
					<td>销售收入</td>
					<td>缴纳税金</td>
					<td>员工数</td>
					<td width="150px">备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist10" id="qlist10" status="listStat">
				<tr>
						<td> <s:property value="#listStat.index+1"/></td>
						<td>
							<s:property value="sjq"/>
						</td>
						<td>
							<s:property value="xssr"/>
						</td>
						<td>
							<s:property value="jnsj"/>
						</td>
						
						<td>
							<s:property value="ygs"/>
						</td>
						<td width="150px">
							<s:property value="sm"/>
						</td>
				</tr>				
			</s:iterator>
		</table>			
	</div>
	
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">十四、园区服务</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>日期</td>
					<td>园区服务人</td>
					<td>单位接待人</td>
					<td>服务内容</td>
					<td width="150px">备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist11">
				<tr>
						<td><s:property value="xh"/></td>
						<td><s:property value="rq"/></td>
						<td>
							<s:property value="yqfwr_mc"/>
						</td>
						<td>
							<s:property value="dwjdr"/>
						</td>
						<td>
							<s:property value="fwnr"/>
						</td>
						<td width="150px">
							<s:property value="sm"/>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		<s:iterator value="yjlbs">
			$('yjlbmc').innerHTML += '<s:property value="selmc"/>&nbsp|&nbsp';
		</s:iterator>
	</script>
</html>


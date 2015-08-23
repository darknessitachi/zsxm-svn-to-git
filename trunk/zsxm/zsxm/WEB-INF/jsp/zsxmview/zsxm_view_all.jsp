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
    	<table class="fxtable" cellspacing="0"  cellpadding="0">
    		<tr>
    			<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">项目编号</td>
				<td colspan=3>
					<s:property value="zsxm.xmbh"/>&nbsp;
				</td>
			</tr>
    		<tr>
    			<td colspan="4" style="text-align:center;background:white"><b><font size=4>常州科教城招资项目情况一览表</font></b></td>
    		</tr>
    		<tr>
    			<td colspan="4" style="background:#efefef"><b>一、项目概况</b></td>
    		</tr>
    		
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%"><span class="red fn">*</span>项目名称</td>
				<td>
					<s:property value="zsxm.xmmc"/>&nbsp;
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">日期</td>
				<td>
					<s:property value="zsxm.rq"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%"><span class="red fn">*</span>项目类别</td>
				<td  colspan=3>
					<s:property value="zsxm.xmlb_mc"/>&nbsp;
				</td>
				
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%"><span class="red fn">*</span>星级</td>
				<td  colspan=3>
					<s:property value="zsxm.xmxj_mc"/>&nbsp;
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">项目概述</td>
				<td colspan=3 style="width:85%">
					<s:property value="zsxm.xmgs"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%"><span class="red fn">*</span>项目进度概述</td>
				<td colspan=3>
					<s:property value="zsxm.xmjds_mc"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">进展情况</td>
				<td colspan=3>
					<s:property value="zsxm.xmjzqk"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">预计投入</td>
				<td colspan=3>
					<s:property value="zsxm.yjtr"/>&nbsp;
				<span class="red fn">（人民币/万元）</span>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">对方联系人</td>
				<td>
					<s:property value="zsxm.dflxr"/>&nbsp;
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">对方联系人电话</td>
				<td>
					<s:property value="zsxm.dflxrdh"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">对方联系人手机</td>
				<td>
					<s:property value="zsxm.dflxrsj"/>&nbsp;
				</td>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">项目跟踪人</td>
				<td>
					<s:property value="zsxm.xmgzr_mc"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">拟入驻单位名称</td>
				<td colspan=3>
					<s:property value="zsxm.ndwmc"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan=4 style="text-align:left;">
					<b>项目推进中可能遇到的问题或困难 :</b><br>
					<s:property value="zsxm.xmwt"/>&nbsp;
				</td>
				
			</tr>
			
			
			
			<tr>
				<td style="text-align:center;font:bold 12px 'lucida Grande',Verdana;width:15%">备注</td>
				<td colspan=3>
					<s:property value="zsxm.bzxx"/>&nbsp;
				</td>
			</tr>
		</table>
	</div>
	
	<br>
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">二、进展情况</td>
			</tr>
		</table>
		<table id="tac">
			
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>日期</td>
					<td>园区接待人</td>
					<td>来访客人</td>
					<td>商谈内容</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist">
				<tr>
					
						<td><s:property value="xh"/></td>
						<td><s:property value="rq"/></td>
						<td>
							<s:property value="yqjdr_mc"/>
						</td>
						<td>
							<s:property value="lkfr"/>
						</td>
						<td>
							<s:property value="stnr"/>
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
				<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">三、对方联系人</td>
			</tr>
		</table>
		<table id="tac">
			<thead>
				<tr>
					<td width="50px">序号</td>
					<td>姓名</td>
					<td>电话</td>
					<td>手机</td>
					<td>邮箱</td>
					<td>备注</td>
					<td width="90">第一联系人</td>
				</tr>
			</thead>
			<s:iterator value="qlist2">
				<tr>
						<td><s:property value="xh"/></td>
						<td><s:property value="dflxr"/></td>
						<td>
							<s:property value="dftel"/>
						</td>
						<td>
							<s:property value="dfphone"/>
						</td>
						<td>
							<s:property value="dfemail"/>
						</td>
						<td>
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
</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		
	</script>
</html>


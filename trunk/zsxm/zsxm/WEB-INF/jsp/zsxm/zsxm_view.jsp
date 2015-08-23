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
	<s:component template="xtitle" theme="app" value='项目信息预览'></s:component>
	<s:form name="zsxmForm" action='zsxm!preZsxmU.do' method="post" >

	<s:hidden name="xmid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->
 <div style="width:100%" id="mh">

	<div id="tableContainer" style="height:90%;overflow:auto">
    	<table class="fxtable" cellspacing="0"  cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>项目名称</td>
				<td style="width:230px"><s:property value="zsxm.xmmc"/>&nbsp</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">日期</td>
				<td class="tdright"><s:property value="zsxm.rq"/>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>项目类别</td>
				<td  colspan=3>
					<s:property value="zsxm.xmlb_mc"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>星级</td>
				<td  colspan=3><s:property value="zsxm.xmxj_mc"/>&nbsp</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">项目概述</td>
				<td colspan=3><s:property value="zsxm.xmgs"/>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>项目进度概述</td>
				<td colspan=3><s:property value="zsxm.xmjdgs_mc"/>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">进展情况</td>
				<td colspan=3><div id="xmjzqkd"></div>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">预计投入</td>
				<td colspan=3>
				<s:property value="zsxm.yjtr"/>&nbsp
				<span class="red fn">（人民币/万元）</span>
				</td>
			</tr>
			<tr>
				<td colspan=4 style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;">项目推进中可能遇到的问题或困难</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px" style="height:0px; line-height:0px;"></td>
				<td colspan=3>
					<s:property value="zsxm.xmwt"/>&nbsp
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">对方联系人</td>
				<td class="tdright"><s:property value="zsxm.dflxr"/>&nbsp</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">对方联系人电话</td>
				<td class="tdright"><s:property value="zsxm.dflxrdh"/>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">对方联系人手机</td>
				<td class="tdright"><s:property value="zsxm.dflxrsj"/>&nbsp</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">项目跟踪人</td>
				<td class="tdright"><s:property value="zsxm.xmgzr_mc"/>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px" style="height:0px; line-height:0px;">拟入驻单位名称</td>
				<td colspan=3>
					<s:property value="zsxm.ndwmc"/>&nbsp
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">备注</td>
				<td colspan=3><s:property value="zsxm.bzxx"/>&nbsp</td>
			</tr>
		</table>
	</div>

</div>	
	</s:form>
	<script type="text/javascript">
		var xx = '<s:property value="zsxm.xmjzqk"/>';
		$('xmjzqkd').innerHTML = xx.replaceAll('####','<br>');
		$('tableContainer').style.height = getSize().h -50;
	</script>
	</body>

</html>


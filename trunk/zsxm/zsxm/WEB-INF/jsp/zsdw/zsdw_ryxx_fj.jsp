<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
			.xwin3 fieldset{ border:0px; border-top:1pt solid #b0d4f8; width:100%; height:25px; line-height:25px; font-weight:700; color:#285593;}
		.xwin3 fieldset legend.open{ background:url(images/skin0/rcda/filedsetopen.png) no-repeat; padding-left:20px;cursor:pointer;}
		.xwin3 fieldset legend.close{ background:url(images/skin0/rcda/filedsetclose.png) no-repeat; padding-left:20px;cursor:pointer;}
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='附件查看'></s:component>
	<s:form name="czrcForm" id="czrcForm" action='rcda!preJszcI.do'  method="post"  enctype="multipart/form-data" >
	<s:hidden name="pname" id="pname"></s:hidden>
	<s:hidden name="dwid" id="dwid"></s:hidden>
	<s:hidden name="ryid" id="ryid"></s:hidden>
	
 <!--个人信息 start-->

	<div id="xcontent">
	<div class="xwin3">
		<fieldset> <legend class="close" id="leg1"><font color=red><b>金凤凰--(生活资助和住房补助)需要提供如下附件材料</b><font color=blue></font></font></legend></fieldset>
	</div>
	<div class="xwincontent1" id="xwincontent1" >
		<table width="700" cellpadding="0" cellspacing="0">
			
			<tr>
			<td>
				<fieldset> <legend><b><font color=blue>三项中至少上传一项资料</font></b></legend> 
					<table width="100%" cellpadding="0" cellspacing="0" >
					<tr>
					<td valign="top"  style="width:260px;">(1) &nbsp;经费资助（奖励）申请表:</td>
					<td>
						<s:if test="fjs11.size>0">
							<s:iterator value="fjs11">
								<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
									<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
									
								</span>
							</s:iterator>
						</s:if>
						<s:else>
							<span id="fj_1_1_1"><font color=blue>--没有上传附件--</font></span>
						</s:else>
					</td>
					</tr>
					<tr>
					<td valign="top"  style="width:260px;">(2) &nbsp;学历证或学位证:</td>
					<td>		
					
						<s:if test="fjs12.size>0">
							<s:iterator value="fjs12">
								<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
									<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
									
								</span>
							</s:iterator>
						</s:if>
						<s:else>
							<span id="fj_1_2_1"><font color=blue>--没有上传附件--</font></span>
						</s:else>
					
					</td>
					<tr>
					<td valign="top"  style="width:260px;">
					(3) &nbsp;留学人员提供教育部国外学历学位认证书:
					</td>
					<td>
						
						<s:if test="fjs13.size>0">
							<s:iterator value="fjs13">
								<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
									<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
									
								</span>
							</s:iterator>
						</s:if>
						<s:else>
							<span id="fj_1_3_1"><font color=blue>--没有上传附件--</font></span>
						</s:else>
					
					
					</td>
					</tr>
					</table>
				</fieldset>
			</td>
			</tr>
			<tr>
				<td width="100%">
					<fieldset align="center"> <legend><b><font color=blue>三项中至少上传一项资料</font></b></legend> 
						<table width="100%" cellpadding="0" cellspacing="0" border=0>
						<tr>
						<td valign="top"  style="width:260px;">
						(1) &nbsp;任职资格证书:
						</td>
						<td>
							<s:if test="fjs21.size>0">
								<s:iterator value="fjs21">
									<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
										
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj_2_1_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						</td>
						</tr>
						<tr>
						<td  valign="top" style="width:260px;">
						(2) &nbsp;聘用（任）合同:
						</td>
						<td>	
							<s:if test="fjs22.size>0">
								<s:iterator value="fjs22">
									<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
										
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj_2_2_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						</td>
						</tr>
						<tr>
						<td  valign="top" style="width:260px;">
						(3) &nbsp;职称确认批文:
						</td>
						<td>	
							<s:if test="fjs23.size>0">
								<s:iterator value="fjs23">
									<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
										
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj_2_3_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						</td>
						</tr>
						</table>
						
					</fieldset>
				</td>
			</tr>
			<tr>
				<td>
					<fieldset align="center"> <legend><b><font color=blue>可选可不选</font></b></legend> 
						<table width="100%" cellpadding="0" cellspacing="0" >
						<tr>
						<td valign="top"  style="width:260px;">
						(1) &nbsp;专家证书:
						</td>
						<td>	
							<s:if test="fjs31.size>0">
								<s:iterator value="fjs31">
									<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
										
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj_3_1_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						
						</td>
						</tr>
						<tr>
						<td valign="top" style="width:260px;">
						(2) &nbsp;学术科研成果证明材料:
						</td>
						<td>
							<s:if test="fjs32.size>0">
								<s:iterator value="fjs32">
									<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
										
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj_3_2_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						</td>
						</tr>
						<tr>
						<td valign="top" style="width:260px;">
						(3) &nbsp;单位实际工作时间证明材料:
						</td>
						<td>
							<s:if test="fjs33.size>0">
								<s:iterator value="fjs33">
									<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
										
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj_3_3_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						
						</td>
						</tr>
						<tr>
						<td valign="top" style="width:260px;">
						(4) &nbsp;其他辅证材料:
						</td>
						<td>	
							<s:if test="fjs34.size>0">
								<s:iterator value="fjs34">
									<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
										
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj_3_4_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						
						</td>
						</tr>
						</table>
					</fieldset>
				</td>
			</tr>
			
		</table>
	</div>
	<div class="xwin3">
		<fieldset> <legend class="close" id="leg2"><font color=red><b>金凤凰--(安家费申报)需要提供如下附件材料</b><font color=blue></font></font></legend></fieldset>
	</div>
	<div class="xwincontent1" id="xwincontent2" >
		<table width="700" cellpadding="0" cellspacing="0">
			
			<tr>
			<td>
				<fieldset> <legend><b><font color=blue>两项资料必须上传</font></b></legend> 
					<table width="100%" cellpadding="0" cellspacing="0" >
					<tr>
					<td valign="top"  style="width:260px;">(1) &nbsp;安家费申请表:</td>
					<td>
						<s:if test="fj2s11.size>0">
							<s:iterator value="fj2s11">
								<span id="fj2_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
									<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
									
								</span>
							</s:iterator>
						</s:if>
						<s:else>
							<span id="fj2_1_1_1"><font color=blue>--没有上传附件--</font></span>
						</s:else>
					</td>
					</tr>
					<tr>
					<td valign="top"  style="width:260px;">(2) &nbsp;安家费使用协议:</td>
					<td>		
					
						<s:if test="fj2s12.size>0">
							<s:iterator value="fj2s12">
								<span id="fj2_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
									<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
									
								</span>
							</s:iterator>
						</s:if>
						<s:else>
							<span id="fj2_1_2_1"><font color=blue>--没有上传附件--</font></span>
						</s:else>
					
					</td>
					</tr>
					</table>
				</fieldset>
			</td>
			</tr>
			<tr>
				<td width="100%">
					<fieldset align="center"> <legend><b><font color=blue>两项中至少上传一项资料</font></b></legend> 
						<table width="100%" cellpadding="0" cellspacing="0" border=0>
						<tr>
						<td valign="top"  style="width:260px;">
						(1) &nbsp;《购房合同》及付款凭证:
						</td>
						<td>
							<s:if test="fj2s21.size>0">
								<s:iterator value="fj2s21">
									<span id="fj2_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
										
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj2_2_1_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						</td>
						</tr>
						<tr>
						<td  valign="top" style="width:260px;">
						(2) &nbsp;产权证和土地证:
						</td>
						<td>	
							<s:if test="fj2s22.size>0">
								<s:iterator value="fj2s22">
									<span id="fj2_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
										<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载"><s:property value="fjmc"/></a>
									</span>
								</s:iterator>
							</s:if>
							<s:else>
								<span id="fj2_2_2_1"><font color=blue>--没有上传附件--</font></span>
							</s:else>
						
						</td>
						</tr>
						
						</table>
						
					</fieldset>
				</td>
			</tr>
			
		</table>
	</div>
	</div>
	
</s:form>

	<iframe name="uploadFrame" height="0px" width="opx">
	</iframe>	
	</body>
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		//$('xwincontent1').style.height = 350+"px"; 
		
	</script>
</html>


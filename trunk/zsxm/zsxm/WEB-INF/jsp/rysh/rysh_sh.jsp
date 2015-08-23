<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		
		<script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		.xwin3 fieldset{ border:0px; border-top:1pt solid #b0d4f8; width:100%; height:25px; line-height:25px; font-weight:700; color:#285593;}
		.xwin3 fieldset legend.open{ background:url(images/skin0/rcda/filedsetopen.png) no-repeat; padding-left:20px;cursor:pointer;}
		.xwin3 fieldset legend.close{ background:url(images/skin0/rcda/filedsetclose.png) no-repeat; padding-left:20px;cursor:pointer;}
		.selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='机构人员审核'></s:component>
	<s:form name="czrcForm" id="czrcForm" action='rcda!preJszcI.do'  method="post"  enctype="multipart/form-data" >
	<s:hidden name="pname" id="pname"></s:hidden>
	<s:hidden name="dwid" id="dwid"></s:hidden>
	<s:hidden name="ryid" id="ryid"></s:hidden>
	<s:hidden name="fjpath" id="fjpath"></s:hidden>
 	<!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" style="overflow:auto">

		<table align="center" cellpadding="0" cellspacing="0" id="tab2" >
			<tr>
				<s:if test="zsdw.thyy!=''">
					<td class="tdleft"><font color=red>退回原因：</font></td>
					<td colspan=7>
						<s:property value="zsdw.thyy"/>
					</td>
				</s:if>
				<s:else>
					
				</s:else>
			</tr>
      		<tr>
				<td class="tdleft">姓名：</td>
				<td>
					<s:property value="zsdw.xm"/>
				</td>
				
				<td class="tdleft">性别：</td>
				<td>
					<s:property value="zsdw.sex_mc"/>
				</td>
				
				<td class="tdleft">身份证：</td>
				<td>
					<s:property value="zsdw.sfz"/>
				</td>
				<td  align="center" colspan=2 rowspan=5>
					<img src="images/skin0/boy.gif" id="imagespig" style="width:100;height:100;"/>
					
				</td>
			</tr>
			<tr>
				<td class="tdleft">政治面貌：</td>
				<td>
					<s:property value="zsdw.zzmm_mc"/>
				</td>
				<td class="tdleft">籍贯：</td>
				<td>
					<s:property value="zsdw.jg"/>
				</td>
				<td class="tdleft">户籍地：</td>
				<td>
					<s:property value="zsdw.hjd"/>
				</td>
				<td  align="center" colspan=2></td>
			</tr>
			<tr>
				<td class="tdleft">出生年月：</td>
				<td>
					<s:property value="zsdw.birthday"/>
				</td>
				
				<td class="tdleft">专兼职：</td>
				<td>
				<s:if test="zsdw.zjz==''">
					专
				</s:if>
				<s:else>
					<s:if test="zsdw.zjz==1">专</s:if>
					<s:if test="zsdw.zjz==2">兼</s:if>				
				</s:else>
				</td>
				
				
				<td class="tdleft">起聘时间：</td>
				<td>
					<s:property value="zsdw.qpsj"/>
				</td>
				<td  align="center" colspan=2></td>
			</tr>
			
			<tr>
				<td class="tdleft">学历/学位：</td>
				<td>
					<s:property value="zsdw.xl_mc"/>
				</td>
				<td class="tdleft">职称/职务：</td>
				<td>
					<s:property value="zsdw.zc_mc"/>
				</td>
				
				<td>海外留学情况：</td>
				<td>
					<s:property value="zsdw.hwlxqk"/>
				</td>
				<td  align="center" colspan=2></td>
			</tr>
			<tr>
				<td class="tdleft">领军型人才：</td>
				<td colspan=7>
					<span id='xtdict28mc'></span>
				</td>
				
			</tr>
			<tr>
				
				<td class="tdleft">研究方向：</td>
				<td colspan=7>
					<s:property value="zsdw.yjfx"/>
				</td>
			</tr>
			<tr>
				<td>金凤凰起止时间：</td>
				<td colspan=7>
					<input type="text" class="Wdate" name="zsdw.jfhkstime" id="jfhkstime" style="text-align:left;width:130" value="<s:property value="zsdw.jfhkstime"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')"  MINDATE="1960-01-01" readonly/>
					--
					<input type="text" class="Wdate" name="zsdw.jfhjstime" id="jfhjstime" style="text-align:left;width:130" value="<s:property value="zsdw.jfhjstime"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
			</tr>
			<tr>
				<td>金凤凰上报选择：</td>
				<td colspan=7>
					<input type="checkbox" value='1' id="jfhtype1" name="jfhsbtype"/>生活资助&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value='2' id="jfhtype2" name="jfhsbtype"/>住房补助&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value='3' id="jfhtype3" name="jfhsbtype"/>安家费&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td class="tdleft">资助标准：</td>
				<td colspan=7>
					生活资助标准<s:textfield name="zsdw.shzzbz" cssStyle="width:100px"></s:textfield>&nbsp;&nbsp;&nbsp;
					住房补助<s:textfield name="zsdw.zfzzbz" cssStyle="width:100px"></s:textfield>&nbsp;&nbsp;&nbsp;
					安家费<s:textfield name="zsdw.ajfzzbz" cssStyle="width:100px"></s:textfield>
				</td>
			</tr>	
			
			<tr>
				<td class="tdleft">简历<br>(高中开始)</td>
				<td class="tdright" colspan=7>
					<s:textarea name="zsdw.ryjl" id="zsdw.ryjl" cols="80" rows="8"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="tdleft">备注</td>
				<td class="tdright" colspan=7>
					<s:textarea name="zsdw.sm" id="zsdw.sm" cols="80" rows="3"></s:textarea>
				</td>
			</tr>
		</table>
		<s:if test="isjfh == 1">
		<div id="xcontent">
			<div class="xwin3">
				<fieldset> <legend class="close" id="leg1" onclick="javascript:setscol(this,'1');"><font color=red><b>金凤凰--(生活资助)需要提供如下附件材料</b><font color=blue>(点击展开)</font></font></legend></fieldset>
			</div>
			<div class="xwincontent1" id="xwincontent1" style="display:none">
				<table width="700" cellpadding="0" cellspacing="0">
					<tr>
					<td>
						<fieldset> 
							<legend><b><font color=blue>四项中至少上传一项资料</font></b></legend> 
							<table width="100%" cellpadding="0" cellspacing="0" >
							<tr>
							<td valign="top"  style="width:260px;">(1) &nbsp;经费资助（奖励）申请表:</td>
							<td>
								<s:if test="fjs11.size>0">
									<s:iterator value="fjs11">
										<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
											<s:property value="fjmc"/>
											&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
										</span>
									</s:iterator>
								</s:if>
								<s:else>
									<span id="fj_1_1_1"><font color=blue>--没有上传附件--</font></span>
								</s:else>
							</td>
							</tr>
							<tr>
							<td valign="top"  style="width:260px;">(2) &nbsp;学历证和学位证:</td>
							<td>		
							
								<s:if test="fjs12.size>0">
									<s:iterator value="fjs12">
										<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
											<s:property value="fjmc"/>
											&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
											<s:property value="fjmc"/>
											&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
										</span>
									</s:iterator>
								</s:if>
								<s:else>
									<span id="fj_1_3_1"><font color=blue>--没有上传附件--</font></span>
								</s:else>
							
							
							</td>
							</tr>
							<tr>
								<td  valign="top" style="width:260px;">
								(4) &nbsp;职称确认批文:
								</td>
								<td>	
									<s:if test="fjs23.size>0">
										<s:iterator value="fjs23">
											<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
						<td width="100%">
							<fieldset align="center"> <legend><b><font color=blue>二项中至少上传一项资料</font></b></legend> 
								<table width="100%" cellpadding="0" cellspacing="0" border=0>
								<tr>
								<td valign="top"  style="width:260px;">
								(1) &nbsp;任职资格证书:
								</td>
								<td>
									<s:if test="fjs21.size>0">
										<s:iterator value="fjs21">
											<span id="fj_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
											</span>
										</s:iterator>
									</s:if>
									<s:else>
										<span id="fj_2_2_1"><font color=blue>--没有上传附件--</font></span>
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
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
				<fieldset> <legend class="close" id="leg3" onclick="setscol(this,'3')"><font color=red><b>金凤凰--(住房补助)需要提供如下附件材料</b><font color=blue>(点击展开)</font></font></legend></fieldset>
			</div>
			<div class="xwincontent1" id="xwincontent3" style="display:none">
				<table width="700" cellpadding="0" cellspacing="0">
					
					<tr>
					<td>
						<fieldset> 
							<legend><b><font color=blue>两项中至少上传一项资料</font></b></legend> 
							<table width="100%" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top"  style="width:260px;">
								(1) &nbsp;租房合同(城内):
								</td>
								<td>
									
									<s:if test="fj3s11.size>0">
										<s:iterator value="fj3s11">
											<span id="fj3_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
											</span>
										</s:iterator>
									</s:if>
									<s:else>
										<span id="fj3_1_1_1"><font color=blue>--没有上传附件--</font></span>
									</s:else>
								</td>
							</tr>
							<tr>
								<td valign="top"  style="width:260px;">
								(2) &nbsp;租房合同(城内):
								</td>
								<td>
									
									<s:if test="fj3s12.size>0">
										<s:iterator value="fj3s12">
											<span id="fj3_<s:property value="tt"/>_<s:property value="xx"/>_<s:property value="yy"/>">
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
											</span>
										</s:iterator>
									</s:if>
									<s:else>
										<span id="fj3_1_2_1"><font color=blue>--没有上传附件--</font></span>
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
				<fieldset> <legend class="close" id="leg2" onclick="setscol(this,'2')"><font color=red><b>金凤凰--(安家费申报)需要提供如下附件材料</b><font color=blue>(点击展开)</font></font></legend></fieldset>
			</div>
			<div class="xwincontent1" id="xwincontent2" style="display:none">
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
											<s:property value="fjmc"/>
											&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
											<s:property value="fjmc"/>
											&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
												<s:property value="fjmc"/>
												&nbsp;&nbsp;<a href="http://<s:property value="fjpath"/>/upload/<s:property value="fj"/>" target=_blank title="点击下载">(下载附件)</a>
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
		</s:if>
	</div>
	<div class="footer" style="text-align:center;background:#f5f5f5">
		<input class="button3" type="button" value="审核通过" onclick="javascript:doSave(1);"/>
		<input class="button3" type="button" value="审核退回" onclick="javascript:doSave(2);"/>
		回退原因:<input type="text" name="thyy" value=""/>
	</div>
	</s:form>
	


	<iframe name="uploadFrame" height="0px" width="opx">
	</iframe>	
	</body>

	<script type="text/javascript">
		
		Event.observe(window, "load", function() {
			$('xwincontent').style.height = (getSize().h-70)+"px";
			if('<s:property value="zsdw.fjmc"/>' != '' && '<s:property value="zsdw.fjmc"/>' != null){
				$('imagespig').src = 'http://<s:property value="zsdw.fjpath"/>/upload/<s:property value="zsdw.fjmc"/>';
			}
			init();
		}, true);
		
		function init(){
			<s:iterator value="xtdict28list">
				$('xtdict28mc').innerHTML += '<s:property value="dictname"/>'+'&nbsp|&nbsp';
			</s:iterator>
			<s:iterator value="jfhsbtypes">
				$('jfhtype<s:property value="jfhtype"/>').checked = true;
			</s:iterator>
		}
		
		var reload = 0;
		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			getOpener().refresh();
			parent.closeXwin(sid);
		}
		
		function doSave(type){
			if(type==2){
				if($('thyy').value==''){
					alert('请填写回退原因!');
					return false;
				}
				var ajax = new AppAjax("rysh!doShth.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else{
				var ajax = new AppAjax("rysh!doShtg.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}
			
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
			
				if(type == 1){
					alert('数据已审核通过');
				}else{
					alert('数据已退回');
				}
				getOpener().refresh();
				closeWin(self.name);
			}else{
				alert(data.message.text);
			}
		}
		
		var top = 0;
		function setscol(obj,t){
			if(top == 0){
				top = 130;
			}else{
				top = null;
			}
			if(t=='1'){
				if(obj.className=='close'){
					obj.className = 'open';
					$('xwincontent'+t).style.display = '';
					$('xwincontent2').style.display = 'none';
					$('xwincontent3').style.display = 'none';
					$('leg2').className = "close";
					$('leg3').className = "close";
					//winReSize(self.name,600,null,top,null);
				}else{
					obj.className = 'close';
					$('xwincontent'+t).style.display = 'none';
					//winReSize(self.name,330,null,top,null);
				}
			}else if(t=='2'){
				if(obj.className=='close'){
					obj.className = 'open';
					$('xwincontent'+t).style.display = '';
					$('xwincontent1').style.display = 'none';
					$('xwincontent3').style.display = 'none';
					$('leg1').className = "close";
					$('leg3').className = "close";
					//winReSize(self.name,600,null,top,null);
				}else{
					obj.className = 'close';
					$('xwincontent'+t).style.display = 'none';
					//winReSize(self.name,330,null,top,null);
				}
			}else{
				if(obj.className=='close'){
					obj.className = 'open';
					$('xwincontent'+t).style.display = '';
					$('xwincontent1').style.display = 'none';
					$('xwincontent2').style.display = 'none';
					$('leg1').className = "close";
					$('leg2').className = "close";
					//winReSize(self.name,600,null,top,null);
				}else{
					obj.className = 'close';
					$('xwincontent'+t).style.display = 'none';
					//winReSize(self.name,330,null,top,null);
				}
			}
			
		}
		function setJsTime(){
			var v = $('jfhkstime').value;
			if(v != ''){
				$('jfhjstime').value=parseFloat(v.split('-')[0])+3+'-'+v.split('-')[1]+'-'+v.split('-')[2];
			}
		}
	</script>
</html>


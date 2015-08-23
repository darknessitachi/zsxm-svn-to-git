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
	<s:component template="xtitle" theme="app" value='机构人员情况'></s:component>
	<s:form name="czrcForm" id="czrcForm" action='rcda!preJszcI.do'  method="post"  enctype="multipart/form-data" >
	<s:hidden name="pname" id="pname"></s:hidden>
	<s:hidden name="dwid" id="dwid"></s:hidden>
	<s:hidden name="ryid" id="ryid"></s:hidden>
	<s:hidden name="fjpath" id="fjpath"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >

		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		<tr>
				<td class="tdleft">姓名</td>
				<td>
					<s:textfield  name="zsdw.xm" id="zsdw.xm" cssStyle="width:130;"></s:textfield>
				</td>
				
				<td class="tdleft">性别</td>
				<td>
					<s:select name="zsdw.sex" id="zsdw.sex" list="xtdict14" cssStyle="width:130" listKey="dictbh" listValue="dictname"/>
				</td>
				
				<td class="tdleft">身份证</td>
				<td>
					<s:textfield  name="zsdw.sfz" id="zsdw.sfz" cssStyle="width:130;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdleft">出生年月</td>
				<td>
					<input type="text" class="Wdate" name="zsdw.birthday" id="zsdw.birthday" style="text-align:left;width:130" value="<s:property value="zsdw.birthday"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
				<td class="tdleft">专兼职</td>
				<td>
				<s:if test="zsdw.zjz==''">
					<input type="radio" name="zsdw.zjz" id="zsdw.zjz" value="1" checked/>专
					&nbsp&nbsp&nbsp&nbsp
					<input type="radio" name="zsdw.zjz" id="zsdw.zjz" value="2"/>兼				
				</s:if>
				<s:else>
					<input type="radio" name="zsdw.zjz" id="zsdw.zjz" value="1" <s:if test="zsdw.zjz==1">checked</s:if>/>专
					&nbsp&nbsp&nbsp&nbsp
					<input type="radio" name="zsdw.zjz" id="zsdw.zjz" value="2" <s:if test="zsdw.zjz==2">checked</s:if>/>兼				
				</s:else>
				</td>
				
				
				<td class="tdleft">起聘时间</td>
				<td>
					<input type="text" class="Wdate" name="zsdw.qpsj" id="zsdw.qpsj" style="text-align:left;width:130" value="<s:property value="zsdw.qpsj"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
			</tr>
			
			<tr>
				<td class="tdleft">学历/学位</td>
				<td>
					<s:select name="zsdw.xl" id="zsdw.xl" list="xtdict25" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
				<td class="tdleft">职称/职务</td>
				<td>
					<s:select name="zsdw.zc" id="zsdw.xl" list="xtdict15" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
				
				<td class="tdleft">海外留学情况</td>
				<td>
					<s:textfield  name="zsdw.hwlxqk" id="zsdw.hwlxqk" cssStyle="width:130;"></s:textfield>
				</td>
			</tr>
			
			<tr>	
				<td class="tdleft">研究方向</td>
				<td colspan=5>
					<s:textfield  name="zsdw.yjfx" id="zsdw.yjfx" cssStyle="width:230;"></s:textfield>
				</td>
				
			</tr>
		
			<tr>
				<td class="tdleft">备注</td>
				<td class="tdright" colspan=5>
					<s:textarea name="zsdw.sm" id="zsdw.sm" cols="60" rows="3"></s:textarea>
				</td>
			</tr>
		</table>
		
	
	</div>
	<br>
	<div id="xcontent">
	<div class="xwin3">
		<fieldset> <legend class="close" id="leg1" onclick="javascript:setscol(this,'1');"><font color=red><b>金凤凰--(生活资助和住房补助)需要提供如下附件材料</b><font color=blue>(点击展开)</font></font></legend></fieldset>
	</div>
	<div class="xwincontent1" id="xwincontent1" style="display:none">
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
									<s:property value="fjmc"/>
									
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
									<s:property value="fjmc"/>
									
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
										<s:property value="fjmc"/>
										
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
										<s:property value="fjmc"/>
										
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
										<s:property value="fjmc"/>
										
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
	<div class="footer" style="background:#f5f5f5">
		<s:if test="opttype==3">
			<input class="button3" type="button" value="保   存" onclick="javascript:doSave(3);"/>
		</s:if>
		<s:else>
			<input class="button3" type="button" value="保存继续" onclick="javascript:doSave(1);"/>
			<input class="button3" type="button" value="保存退出" onclick="javascript:doSave(2);"/>
		</s:else>
		
		<input class="button3" type="button" value="退   出" onclick="closeWindows();"/>
	</div>
</s:form>

	<iframe name="uploadFrame" height="0px" width="opx">
	</iframe>	
	</body>
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		$('xcontent').style.height = 350+"px"; 
		$('xwincontent1').style.height = 310+"px";
		$('fjpath').style.height = window.location.host;
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
					$('leg2').className = "close";
					winReSize(self.name,600,null,top,null);
				}else{
					obj.className = 'close';
					$('xwincontent'+t).style.display = 'none';
					winReSize(self.name,330,null,top,null);
				}
			}else{
				if(obj.className=='close'){
					obj.className = 'open';
					$('xwincontent'+t).style.display = '';
					$('xwincontent1').style.display = 'none';
					$('leg1').className = "close";
					winReSize(self.name,600,null,top,null);
				}else{
					obj.className = 'close';
					$('xwincontent'+t).style.display = 'none';
					winReSize(self.name,330,null,top,null);
				}
			}
			
		}
		
		function doSave(type){
			
			if($('zsdw.xm').value.trim() == ''){
				alert('姓名不能为空，请填写！');
				return false;
			}
			if($('zsdw.sfz').value.trim() == ''){
				alert('身份证不能为空，请填写！');
				return false;
			}
			if(type ==1){
				var ajax = new AppAjax("zsdw!doRyxxI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("zsdw!doRyxxI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("zsdw!doRyxxU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				if(type == 1){//保存继续
					reload = 1;
					document.all.czrcForm.reset();
				}else if(type==2){//保存退出
					reload = 1;
					closeWindows();
				}else if(type==3){
					reload = 1;
					closeWindows();
				}
			}else{
				alert(data.message.text);
			}
		}
		var tt = '';//附件类型
		var xx = '';//附件类型 中 的种类
		var yy = '';//附件类型中的种类中的序号
		var fjmc = '';
		var ttype = '';
		function uploadFile(obj,t,x,y,ty){
			showwaitform('附件上传中，请稍侯.....');
			tt = t;
			xx = x;
			yy = y;
			ttype = ty;
			fjmc = getFileName(obj.value);
			document.czrcForm.action = "upload!doUpload.do"
			document.czrcForm.target = "uploadFrame";
			document.czrcForm.submit();
		}
		function setTdImage(uploadOkFileName){
				hidewaitform();
				var sb = new StringBuffer();
				if(uploadOkFileName!= null && uploadOkFileName!= '' && uploadOkFileName!='undefined'){
					sb.append('<input type=hidden name="fj" value="'+uploadOkFileName+'"/>');
					sb.append('<input type=hidden name="fjmc" value="'+fjmc+'"/>');
					sb.append('<input type=hidden name="tt" value="'+tt+'"/>');
					sb.append('<input type=hidden name="xx" value="'+xx+'"/>');
					sb.append('<input type=hidden name="yy" value="'+yy+'"/>');
					sb.append('<input type=hidden name="ttype" value="'+ttype+'"/>');
					sb.append(fjmc);
					if(ttype == 1){
						sb.append("&nbsp&nbsp<img src='images/skin0/other/del.jpg' title='删除此附件' style='cursor:hand;' onclick='delFj(\"fj_"+tt+"_"+xx+"_"+yy+"\",\""+uploadOkFileName+"\");'/>");
					}else{
						sb.append("&nbsp&nbsp<img src='images/skin0/other/del.jpg' title='删除此附件' style='cursor:hand;' onclick='delFj2(\"fj2_"+tt+"_"+xx+"_"+yy+"\",\""+uploadOkFileName+"\");'/>");
					}
					
				}
				if(ttype == 1){
					$('fj_'+tt+'_'+xx+'_'+yy).innerHTML = sb.toString();
				}else{
					$('fj2_'+tt+'_'+xx+'_'+yy).innerHTML = sb.toString();
				}
				
		}
		function delFj(ind,fjname){
			if(window.confirm('您确定要删除此附件?')){
				if($("ryid").value != ""){
					new AppAjax("zsdw!doRyxxFjD.do?dwid="+$('dwid').value+"&ryid="+$('ryid').value+"&fjname="+fjname,function(data){}).submit();
				}
				$(ind).innerHTML = '<s:file cssStyle="width:350px" name="upload"  onchange="uploadFile(this,'+ind.split('_')[1]+','+ind.split('_')[2]+','+ind.split('_')[3]+',1);"></s:file>';
		  	}
		}
		function delFj2(ind,fjname){
			if(window.confirm('您确定要删除此附件?')){
				if($("ryid").value != ""){
					new AppAjax("zsdw!doRyxxFjD.do?dwid="+$('dwid').value+"&ryid="+$('ryid').value+"&fjname="+fjname,function(data){}).submit();
				}
				$(ind).innerHTML = '<s:file cssStyle="width:350px" name="upload"  onchange="uploadFile(this,'+ind.split('_')[1]+','+ind.split('_')[2]+','+ind.split('_')[3]+',2);"></s:file>';
		  	}
		}
	     function   getFileName(fullPath){   
			  var   str1=fullPath;   
			  var   regstr=/\\/;   
			  var   regresult=new   RegExp(regstr)   
			  var   sss=str1.split(regresult,'100');   
			  return sss[sss.length-1];
		  }     
	</script>
</html>


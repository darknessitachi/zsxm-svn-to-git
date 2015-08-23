<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
	 <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>	
	<style>

	</style>
	</head>

	<body>
	
	<s:form name="czrcForm" action='czrc!classinfo.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
	<s:hidden name="winid"></s:hidden>
 <!--个人信息 start-->

	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">
				
	       			<table align="center" class="fxtable" cellpadding="0" cellspacing="0" id="tab5">
	       				<tr>
							<td class="bt" >获资助项目类别</td>
							<td  colspan=3><s:select name="exp.zzxmlb" list="xtdict28" cssStyle="width:300" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" onchange="changeZzxmlb(this.value)"/></td>					
							
						</tr>
		       			<tr>
							<td class="bt" style="width:150">科研项目名称<font color=red>*</font></td>
							<td  colspan=3><s:textfield cssStyle="width:300px" name="exp.xmmc" id="exp.xmmc"/></td>
	
						</tr>
						
						<tr  id="kytr1" style="display:none">
							<td colspan=4 align=center style="background:#d3d3d3"><b>科研成果</b></td>
						</tr>
						<tr  id="kytr2" style="display:none">	
							<td  class="bt" >论文</td>
							<td ><s:textfield cssStyle="width:90px" name="exp.kylws" onfocus="javascript:this.select();" onblur="COM.isNumChar(this.id)"/>篇</td>
							<td  class="bt" >著作</td>
							<td ><s:textfield cssStyle="width:90px" name="exp.kyzzs" onfocus="javascript:this.select();" onblur="COM.isNumChar(this.id)"/>部</td>
						</tr>
						<tr  id="kytr3" style="display:none">	
							<td  class="bt" >产值</td>
							<td ><s:textfield cssStyle="width:90px" name="exp.kycz" onfocus="javascript:this.select();" onblur="COM.isNum(this.id)"/>万元</td>
							<td  class="bt" >专利</td>
							<td ><s:textfield cssStyle="width:90px" name="exp.kyzls" onfocus="javascript:this.select();" onblur="COM.isNumChar(this.id)"/>件</td>
						</tr>
						<tr  id="kytr4" style="display:none">	
							<td  class="bt" >其他成果</td>
							<td colspan=3><s:textfield cssStyle="width:230px" name="exp.kyqtcg"/></td>
							
						</tr>
						<tr  id="kytr5" style="display:none">
							<td colspan=4><hr style="background:#d3d3d3"></hr></td>
						</tr>
						<tr>
							<td class="bt" >项目来源</td>
							<td ><s:select name="exp.xmly" list="xtdict26" cssStyle="width:130" listKey="dictbh" listValue="dictname" /></td>					
							<td class="tdleft" style="width:120px">本人作用</td>
							<td  ><s:select name="exp.brzy" list="xtdict24" cssStyle="width:130" listKey="dictbh" listValue="dictname" /></td>
						</tr>
						<tr>	
							<td  class="bt" >完成情况</td>
							<td ><s:select name="exp.wcqk" list="xtdict19" cssStyle="width:130" listKey="dictbh" listValue="dictname" /></td>
							<td  class="bt" >获资助金额</td>
							<td ><s:textfield cssStyle="width:130px" name="exp.zzje" onfocus="javascript:this.select();" onblur="COM.isNum(this.id)"/></td>
						</tr>
						<tr>	
							
							<td class="bt" >项目起止时间</td>
							<td colspan=3>
								<input type="text" class="Wdate" name="exp.wcrq" id="exp.wcrq" style="text-align:left;width:130" value="<s:property value="exp.wcrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/>
								--
								<input type="text" class="Wdate" name="exp.wcendrq" id="exp.wcendrq" style="text-align:left;width:130" value="<s:property value="exp.wcendrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/>
							</td>
						</tr>
					</table>					
				
	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<s:if test="opttype==3">
			<input class="button3" type="button" value="保   存" onclick="javascript:doSave(3);"/>
		</s:if>
		<s:else>
			<!-- <input class="button3" type="button" value="保   存" onclick="javascript:doSave(1);"/> -->
			<input class="button3" type="button" value="保   存" onclick="javascript:doSave(2);"/>
		</s:else>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>
	
	</s:form>
	</body>

	<script type="text/javascript">
	
		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		$('exp.xmmc').focus();
		var reload = 0;
		
		function doSave(type){
			
			if($('exp.zzxmlb').value == ''){
				alert('请选择获资助项目类别！');
				return false;
			}
			if($('exp.xmmc').value == ''){
				alert('请填写科研项目名称');
				return false;
			}
			
			if($('exp.wcrq').value>$('exp.wcendrq').value){
				alert('开始日期不能小于结束日期！');
				return false;
			}
			
			
			if(type ==1){
				var ajax = new AppAjax("exp!doKtxmI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("exp!doKtxmI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("exp!doKtxmU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				if(type == 1){//保存继续
					reload = 1;
					document.all.czrcForm.reset();
				}else if(type==2){//保存退出
					reload = 1;
					window.parent.refreshmm();
					closeWin();
				}else if(type==3){
					reload = 1;
					window.parent.refreshmm();
					closeWin();
				}
			}else{
				alert(data.message.text);
			}
		}	
		changeZzxmlb($('exp.zzxmlb').value);
		function changeZzxmlb(v){
			if(v == '001' || v=='002'){
				$('kytr1').style.display = '';
				$('kytr2').style.display = '';
				$('kytr3').style.display = '';
				$('kytr4').style.display = '';
				$('kytr5').style.display = '';
			}else{
				$('kytr1').style.display = 'none';
				$('kytr2').style.display = 'none';
				$('kytr3').style.display = 'none';
				$('kytr4').style.display = 'none';
				$('kytr5').style.display = 'none';
				$('exp.kylws').value = '';
				$('exp.kyzzs').value = '';
				$('exp.kycz').value = '';
				$('exp.kyzls').value = '';
				$('exp.kyqtcg').value = '';
			}
		}
	</script>
</html>


<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
	 <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>	
	<style>

	</style>
	</head>

	<body>
	
	<s:form name="czrcForm" action='exp!classinfo.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->

	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">

				       	<table align="center" class="fxtable" cellpadding="0" cellspacing="0" id="tab11" style="">
				       			<tr>
				       				<td class="bt" style="width:160px">知识产权编号<font color=red>*</font></td>
									<td><s:textfield cssStyle="width:130px" id="exp.zsbh" name="exp.zsbh"/></td>
									<td class="bt" style="width:160px">知识产权名称<font color=red>*</font></td>
									<td><s:textfield cssStyle="width:130px" id="exp.zsmc" name="exp.zsmc"/></td>
								</tr>
								
								<tr>	
									<td class="bt" style="width:160px">知识产权类型</td>
									<td ><s:select name="exp.zsno" list="xtdict17" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
									<td class="bt" >授权时间</td>
									<td ><input type="text" class="Wdate" name="exp.hdrq" id="zscq1_rq" style="text-align:left;width:130" value="<s:property value="exp.hdrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/></td>
								</tr>
								<tr>
				       				<td class="bt" style="width:160px">申请人<font color=red>*</font></td>
									<td><s:textfield cssStyle="width:130px" id="exp.sqr" name="exp.sqr"/></td>
									<td class="bt" style="width:160px">权利人<font color=red>*</font></td>
									<td><s:textfield cssStyle="width:130px" id="exp.qlr" name="exp.qlr"/></td>
								</tr>
								<tr>
									<td class="bt" style="width:180px">来常前知识产权情况</td>
									<td  colspan=3>
										<s:if test="exp.iscz==2">
											<input type="radio" name="exp.iscz" value="1" id="exp.iscz1"><label for="exp.iscz1">来常前</label>
											&nbsp&nbsp&nbsp&nbsp
											<input type="radio" name="exp.iscz" checked value="2" id="exp.iscz2"><label for="exp.iscz2">来常后</label>
										</s:if>
										<s:else>
											<input type="radio" name="exp.iscz" checked value="1"  id="exp.iscz1"><label for="exp.iscz1">来常前</label>
											&nbsp&nbsp&nbsp&nbsp
											<input type="radio" name="exp.iscz" value="2" id="exp.iscz2"><label for="exp.iscz2">来常后</label>
										</s:else>
									</td>
								</tr>
								<tr>
									<td class="bt" style="width:160px">注册地点<font color=red>*</font></td>
									<td  colspan=3>
										<s:if test="exp.zcdd==2">
											<input type="radio" name="exp.zcdd" value="1" id="exp.zcdd1"><label for="exp.zcdd1">国内</label>
											&nbsp&nbsp&nbsp&nbsp
											<input type="radio" name="exp.zcdd" checked value="2" id="exp.zcdd2"><label for="exp.zcdd2">国外</label>
										</s:if>
										<s:else>
											<input type="radio" name="exp.zcdd" checked value="1" id="exp.zcdd1"><label for="exp.zcdd1">国内</label>
											&nbsp&nbsp&nbsp&nbsp
											<input type="radio" name="exp.zcdd" value="2" id="exp.zcdd2"><label for="exp.zcdd2">国外</label>
										</s:else>
									</td>
								</tr>
								<tr>	
									<td class="bt" style="width:160px">备注</td>
									<td  colspan=3><s:textfield cssStyle="width:300px" name="exp.sm"/></td>
				
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
	
		var reload = 0;
		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		$('exp.zsbh').focus();
		
		function doSave(type){
			if($('exp.zsmc').value == ''){
				alert('请填写相应的知识产权名称!');
				return false;
			}
			if(type ==1){
				var ajax = new AppAjax("exp!doZscqI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("exp!doZscqI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("exp!doZscqU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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
	</script>
</html>


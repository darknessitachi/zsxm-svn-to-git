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
	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">
				
					<table align="center" class="fxtable"  cellpadding="0" cellspacing="0"  id="tab6" style="">
			       			<tr>
								<td class="bt">奖项名称<font color=red>*</font></td>
								<td colspan=3><s:textfield cssStyle="width:300px" id="exp.jxmc" name="exp.jxmc"/></td>
							</tr>
							
							<tr>	
								<td class="bt">获奖等级</td>
								<td><s:select name="exp.hjdj" list="xtdict" cssStyle="width:130" listKey="dictbh" listValue="dictname" /></td>
								<td class="bt">获奖时间</td>
								<td><input type="text" class="Wdate" name="exp.hjrq" id="ryhj1_rq" style="text-align:left;width:130" value="<s:property value="exp.hjrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/></td>
							</tr>
							<tr>
								<td class="bt">授予部门</td>
								<td colspan=3><s:textfield cssStyle="width:300px" name="exp.bjbm"/></td>
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
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		 
		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		$('exp.jxmc').focus();
		var reload = 0;
		
		
		function doSave(type){
			if($('exp.jxmc').value == ''){
				alert('请填写相应的奖项名称!');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("exp!doRyhjI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("exp!doRyhjI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("exp!doRyhjU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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


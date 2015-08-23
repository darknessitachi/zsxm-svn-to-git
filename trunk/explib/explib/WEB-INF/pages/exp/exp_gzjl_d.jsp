<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
	 <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>		
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
    			
				<table align="center" class="fxtable"  cellpadding="0" cellspacing="0"  id="tab4" style="">
	       			<tr>
						<td class="bt" style="width:100px">开始日期</td>
						<td ><input type="text" class="Wdate" name="exp.brq" id="exp.brq" style="text-align:left;width:130" value="<s:property value="exp.brq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/></td>
						<td class="bt" style="width:100px">
						<s:if test="exp.nowbz==1">
							<input type="checkbox" name="exp.nowbz" id="exp.nowbz" value="1" checked/>至今&nbsp;
						</s:if>
						<s:else>
							<input type="checkbox" name="exp.nowbz" id="exp.nowbz" value="1"/>至今&nbsp;
						</s:else>
						结束日期</td>
						<td>
						<input type="text" class="Wdate" name="exp.erq" id="exp.erq" style="text-align:left;width:130" value="<s:property value="exp.erq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/>
						
						</td>
					</tr>
					
					<tr>	
						<td class="bt"  style="width:100px">所在单位及部门<font color=red>*</font></td>
						<td  colspan=3><s:textfield cssStyle="text-align:left;width:300" name="exp.dwbm" id="exp.dwbm"/></td>
					</tr>
					<tr>	
						<td class="bt"  style="width:100px">从事岗位或职务</td>
						<td  colspan=3><s:textfield cssStyle="text-align:left;width:300" name="exp.zw"/></td>
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
		$('exp.dwbm').focus();
		var reload = 0;
		
		function doSave(type){
			if(!$('exp.nowbz').checked){
				if(($('exp.brq').value>$('exp.erq').value)){
					alert('开始日期不能小于结束日期！');
					return false;
				}
			}else{
				if($('exp.brq').value==''){
					alert('请填开始日期');
					return false;
				}
			}
			
			if($('exp.dwbm').value.trim()==''){
				alert('所在单位及部门不能为空！');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("exp!doGzjlI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("exp!doGzjlI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("exp!doGzjlU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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


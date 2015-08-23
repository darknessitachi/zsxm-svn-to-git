<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
	<script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='房租收取'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="htid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >

		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		<tr>
				<td class="tdleft">收缴日期</td>
				<td colspan=3>
					<input type="text" class="Wdate" name="fzht.sjdate" id="date2" style="text-align:left;width:130" value="<s:property value="fzht.sjdate"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
					--
					<input type="text" class="Wdate" name="fzht.sjenddate" id="date2" style="text-align:left;width:130" value="<s:property value="fzht.sjenddate"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
			</tr>
			<tr>	
				<td class="tdleft">收缴年份</td>
				<td>
					<s:select name="fzht.sjnf" list="xtdict33" cssStyle="width:140" listKey="dictname" listValue="dictname"  headerKey="" headerValue="--请选择--"/>
				</td>
				
				<td class="tdleft">应收房租</td>
				<td>
					<s:textfield  name="fzht.ysfzz" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);isCheckSum();"></s:textfield>
					<font color=red>(元)</font>
				</td>
			</tr>
			<tr>	
				<td class="tdleft">已收房租</td>
				<td>
					<s:textfield  name="fzht.ysfz" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);isCheckSum();"></s:textfield>
					<font color=red>(元)</font>
				</td>
				
				<td class="tdleft">未收房租</td>
				<td>
					<s:textfield  name="fzht.wsfz" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
					<font color=red>(元)</font>
				</td>
			</tr>
			
			<tr>
				<td class="tdleft">备注</td>
				<td colspan=3>
					<s:textfield  name="fzht.bz" cssStyle="width:260;"></s:textfield>
				</td>
			</tr>
		</table>	


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
	</body>
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		// $('xwincontent').style.height = (getSize().h - 105)+"px"; 
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
		
		function doSave(type){
			if($('fzht.sjdate').value.trim() ==''){
				alert('收缴起始日期不能为空！请填写');
				return false;
			}
			if($('fzht.sjenddate').value.trim() ==''){
				alert('收缴结束日期不能为空！请填写');
				return false;
			}
			if(type ==1){
				var ajax = new AppAjax("fzht!doFzsqI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("fzht!doFzsqI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("fzht!doFzsqU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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
		
		function isCheckSum(){
			$('fzht.wsfz').value = (parseFloat($('fzht.ysfzz').value)-parseFloat($('fzht.ysfz').value)).toFixed(4); 
		}
		
	</script>
</html>


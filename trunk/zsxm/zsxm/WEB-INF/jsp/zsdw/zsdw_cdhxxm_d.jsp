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
	<s:component template="xtitle" theme="app" value='承担横向项目情况'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >

		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		<tr>
      			<td class="tdleft">年份</td>
				<td>
					<s:select name="zsdw.xmnf" list="xtdict19" cssStyle="width:130" listKey="dictname" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
				<td class="tdleft">项目名称</td>
				<td>
					<s:textfield  name="zsdw.xmmc" cssStyle="width:130;"></s:textfield>
				</td>
				
				
			</tr>
			
			<tr>	
				<td class="tdleft">对方单位名称</td>
				<td>
					<s:textfield  name="zsdw.dfdwmc" cssStyle="width:130;"></s:textfield>
				</td>
				<td class="tdleft">邮政编码</td>
				<td>
					<s:textfield  name="zsdw.yzbm" cssStyle="width:130;"></s:textfield>
				
				</td>
				
				
			</tr>
			<tr>	
				<td class="tdleft">合同类别</td>
				<td>
					
					<s:select name="zsdw.htlb" list="xtdict21" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--"/>
				</td>
				
				<td class="tdleft">合同金额</td>
				<td>
					<s:textfield  name="zsdw.htje" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
					<font color=red>(万元)</font>
				</td>
				
			</tr>
			<tr>
				<td>达产后预计销售</td>
				<td>
					<s:textfield  name="zsdw.yjxx" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
					<font color=red>(万元)</font>
				</td>
				
				<td class="tdleft">项目负责人</td>
				<td>
					<s:textfield  name="zsdw.xmfzr" cssStyle="width:130;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdleft">合同签订日期</td>
				<td>
					<input type="text" class="Wdate" name="zsdw.htqdrq" id="date1" style="text-align:left;width:130" value="<s:property value="zsdw.htqdrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
				<td class="tdleft">地区</td>
				<td>
					<s:select name="zsdw.dq" list="xtdict22" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
			</tr>
			<tr>
				<td class="tdleft">项目执行期</td>
				<td colspan=3>
					<input type="text" class="Wdate" name="zsdw.strdate" id="date2" style="text-align:left;width:130" value="<s:property value="zsdw.strdate"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
					--
					<input type="text" class="Wdate" name="zsdw.enddate" id="date3" style="text-align:left;width:130" value="<s:property value="zsdw.enddate"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
			</tr>
			<tr>
				<td class="tdleft">备注</td>
				<td colspan=3>
					<s:textfield  name="zsdw.sm" cssStyle="width:260;"></s:textfield>
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
			if($('zsdw.xmmc').value.trim() ==''){
				alert('项目名称不能为空！请填写');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("zsdw!doCdhxxmI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("zsdw!doCdhxxmI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("zsdw!doCdhxxmU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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
		
	</script>
</html>


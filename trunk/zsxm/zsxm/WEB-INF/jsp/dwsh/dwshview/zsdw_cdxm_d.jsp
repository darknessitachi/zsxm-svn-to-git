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
	<s:component template="xtitle" theme="app" value='承担纵向项目情况'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >

		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		<tr>
				<td class="tdleft">项目名称</td>
				<td>
					<s:textfield  name="zsdw.xmmc" cssStyle="width:130;"></s:textfield>
				</td>
				<td class="tdleft">文号</td>
				<td>
					<s:textfield  name="zsdw.xmwh" cssStyle="width:130;"></s:textfield>
				</td>
				
			</tr>
			
			<tr>	
				<td class="tdleft">立项编号</td>
				<td>
					<s:textfield  name="zsdw.xmbh" cssStyle="width:130;"></s:textfield>
				
				</td>
				
				<td class="tdleft">立项金额</td>
				<td>
					<s:textfield  name="zsdw.xmje" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
					<font color=red>(人民币/万元)</font>
				</td>
			</tr>
			<tr>	
				
				<td class="tdleft">立项级别</td>
				<td>
					
					<s:select name="zsdw.xmjb" list="xtdict16" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" onchange="changelxjb(this.value);"/>
				</td>
				<td class="tdleft">计划类别</td>
				<td>
					<s:select name="zsdw.jhlb" list="xtdict20" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
					<!--<s:textfield  name="zsdw.jhlb" cssStyle="width:130;"></s:textfield>-->
				</td>
			</tr>
			<tr>
				<td class="tdleft">立项年份</td>
				<td colspan=3>
					<s:select name="zsdw.lxrq" list="xtdict19" cssStyle="width:130" listKey="dictname" listValue="dictname"  headerKey="" headerValue="--请选择--" />
					<!-- <input type="text" class="Wdate" name="zsdw.lxrq" id="date1" style="text-align:left;width:130" value="<s:property value="zsdw.lxrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/> -->
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
			if($('zsdw.xmwh').value.trim() == ''){
			    alert('文号不能为空！请填写');
				return false;
			}
			if($('zsdw.xmbh').value.trim()=='' ){
			    alert('立项编号不能为空！请填写');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("zsdw!doCdxmI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("zsdw!doCdxmI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("zsdw!doCdxmU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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
		
		function changelxjb(v){
			if(v==''){
				$('zsdw.jhlb').options.length=0;
				return false;
			}
			var ajax = new AppAjax("zsdw!getDictListByDlb.do?dictbh="+v,change_back).submit();
		}
		
		function change_back(data){
			
			if(data != null && data.info.length>0){
				$('zsdw.jhlb').options.length=0;
				for(var i=0;i<data.info.length;i++){
					$('zsdw.jhlb').options[i] =  new Option(data.info[i].DICTNAME,data.info[i].DICTBH);
				}
			}else{
				$('zsdw.jhlb').options.length= 0;
			}
		}
	</script>
</html>


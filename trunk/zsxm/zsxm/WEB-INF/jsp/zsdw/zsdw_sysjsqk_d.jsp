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
	<s:component template="xtitle" theme="app" value='实验室、研发中心建设情况表'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >

		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		<tr>
      			<td class="tdleft">建设年份</td>
				<td colspan=3>
					<s:select name="zsdw.jsnf" list="xtdict19" cssStyle="width:130" listKey="dictname" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
				
				
			</tr>
			<tr>
				<td class="tdleft">名称</td>
				<td colspan=3>
					<s:textfield  name="zsdw.jsmc" cssStyle="width:260;"></s:textfield>
				</td>
			</tr>
			<tr>	
				<td class="tdleft">合建单位</td>
				<td colspan=3>
					<s:textfield  name="zsdw.hjdw" cssStyle="width:260;"></s:textfield>
				
				</td>
				
			</tr>
			
			<tr>
				<td class="tdleft">备注</td>
				<td colspan=3>
					<s:textfield  name="zsdw.bz" cssStyle="width:260;"></s:textfield>
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
			if($('zsdw.jsnf').value.trim() ==''){
				alert('请选择建设年份');
				return false;
			}
			if($('zsdw.jsmc').value.trim() == ''){
			    alert('名称不能为空！请填写');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("zsdw!doSysjsqkI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("zsdw!doSysjsqkI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("zsdw!doSysjsqkU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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


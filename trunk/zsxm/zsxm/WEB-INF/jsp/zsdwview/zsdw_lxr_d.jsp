<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='联系人'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >

		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		<tr>
				<td class="tdleft">姓名</td>
				<td>
					<s:textfield  name="zsdw.lxrxm" cssStyle="width:130;"></s:textfield>
				</td>
				
				<td class="tdleft">性别</td>
				<td>
					<s:select name="zsdw.sex" list="xtdict14" cssStyle="width:130" listKey="dictbh" listValue="dictname"/>
				</td>
			</tr>
			
			<tr>
				<td class="tdleft">职称</td>
				<td>
					<s:select name="zsdw.zc" list="xtdict15" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
				<td class="tdleft">职务</td>
				<td>
					<s:textfield  name="zsdw.zw" cssStyle="width:130;"></s:textfield>
				</td>
			</tr>
			<tr>	
				<td class="tdleft">电话</td>
				<td>
					<s:textfield  name="zsdw.tel" cssStyle="width:130;"></s:textfield>
				</td>
				<td class="tdleft">手机</td>
				<td>
					<s:textfield  name="zsdw.phone" cssStyle="width:130;"></s:textfield>
				</td>
			</tr>
			<tr>	
				<td class="tdleft">邮箱</td>
				<td>
					<s:textfield  name="zsdw.email" cssStyle="width:130;"></s:textfield>
				</td>
				<td class="tdleft">QQ</td>
				<td>
					<s:textfield  name="zsdw.qq" cssStyle="width:130;"></s:textfield>
				</td>
			</tr>
			<tr>	
				<td class="tdleft">所属部门</td>
				<td colspan=3>
					<s:textfield  name="zsdw.ssbm" cssStyle="width:260;"></s:textfield>
				</td>
			</tr>
			<tr>	
				<td class="tdleft">备注</td>
				<td colspan=3>
					<s:textarea name="zsdw.sm" cols="37" rows="3"></s:textarea>
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
			if($('zsdw.lxrxm').value.trim() == ''){
				alert('姓名不能为空，请填写！');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("zsdw!doLxrI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("zsdw!doLxrI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("zsdw!doLxrU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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


<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
		<style type="text/css">
			
			  .title {	background:#B8CDE3;	color:#003770;line-height:26px;height:26px;padding-left:15px;border:1px solid #fff;font-weight:bold;text-align:left;}
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;height:20px;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		      .btn {
		         height:22px;
		      }
		    .editor {
			    margin-top: 5px;
			    margin-bottom: 5px;
		    }
		</style>
	</head>
	<s:form name="emasForm" action="xtmanager!updteUser.do" method="post">
	<body >
		<input type="hidden" name="query.userid" value="${listMap.userid}">
		<s:component template="xtitle" theme="app" value='编辑用户'></s:component>
		<table class="fxtable" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">用户名称：</td>
				<td id='t08'>
					<input type="text" id="cnname" name="query.cnname" value="${listMap.CNNAME}">
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">排序号：</td>
				<td id='t08'>
					<input type="text" id="px" name="query.px" value="${listMap.PX}">
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">登录名称：</td>
				<td id='t08'>
					<input type="text" id="loginname" name="query.loginname" value="${listMap.LOGINNAME}">
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">登录密码：</td>
				<td id='t08'>
					<input type="password" id="password" name="query.password" value="${listMap.PASSWORD}">
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">部门职务：</td>
				<td id='t08' colspan=3 >
					<input type="text" style="width:230" id="bm" name="query.bm" value="${listMap.BM}">
				</td>
				<!-- 
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">职务：</td>
				<td id='t08'>
					<input type="text" id="cnname" name="query.zw" value="${listMap.ZW}">
				</td>
				 -->
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">角色分配：</td>
				<td id='t08' colspan=3>
					<s:select id="roledm" name="query.roledm" cssStyle="width:230px;margin-right:4px;" list="listMap.role" listKey="ROLEDM" listValue="ROLEMC" />
				</td>
			</tr>

		</table>	
	   
	   
	   
	   <div class="footer">			
	         <c:choose>
				<c:when test="${empty listMap.ERRORCODE}">
						<input type="button" class="button" value="保  存" onclick="editUser()"/>
				</c:when>
				<c:otherwise>
					<font color="red">用户已经不存在！</font>
				</c:otherwise>
				</c:choose>
	         
        	
        	   <input type="button" name="resetBtn" class="button" value="关  闭" onclick="closeWin(self.name);"/>
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
		document.getElementById('roledm').value = '${listMap.ROLEDM}';
	
		function editUser(){
				
			if(!document.getElementById('cnname').value){
				alert('请输入用户名称!');
				return;
			}
				
			if(!document.getElementById('loginname').value){
				alert('请输入登录名称!');
				return;
			}
				
			if(!document.getElementById('password').value){
				alert('请输入登录密码!');
				return;
			}
			if(!document.getElementById('roledm').value){
				alert('请输入角色代码!');
				return;
			}
			
			var ajax = new AppAjax("xtmanager!editUser.do",save_Back);
			ajax.submitForm("emasForm");
		}
		
	function save_Back(data){
		if(data.message.code >0){
			parent.mainFrameContainer.document.all.usersx.onclick();
			closeWin(self.name);	
		}else if(data.message.code == 0) {
			document.getElementById('loginname').value ='';
			document.getElementById('loginname').focus();
			alert(data.message.text);
			
		}else{
			alert(data.message.text);
		}
	}
	</script>
</html>
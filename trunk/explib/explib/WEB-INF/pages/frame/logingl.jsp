<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/meta.jsp"%>
<title>专家评审管理信息系统</title>
<style>
	*{ margin:0px; padding:0px; list-style:none}
	body{ background:url(images/skin0/index/login.jpg) center top}
	.login{ background:url(images/skin0/index/log_bg.png);width:1000px; height:700px; margin:0px auto}
	.login_text{width:350px; position:relative; top:430px; left:570px; font-size:12px; color:#0683b7}
	.log_but{ background:url(images/skin0/index/login_but.jpg); height:82px; width:86px; border:0px; }
	.log_tt{ height:27px; width:255px; border:1pt solid #0683b7;line-height:22px}
</style> 
</head>

<body>
<s:form name="form" id="form" action="login!checklogin.do" method="post" onsubmit="return on_form_submit();">
<input type=hidden name="logintype" value=1 />
<div class="login">
 
 <table  border="0" cellspacing="0" cellpadding="0" class="login_text">

  <tr>
    <td height="40" width="60"></td>
    <td>
    <s:textfield name="loginname" id="loginname" cssClass="log_tt" />
    </td>
    <td rowspan=3>
    	&nbsp;
	    <input type="submit" class="log_but" value="" />
    </td>
  </tr>

  <tr>
  	<td colspan=3 height=14></td>
  </tr>
 
  <tr>
    <td height="40"></td>
    <td>
    <s:password name="password"  id="password" cssClass="log_tt" />
    </td>
    
  </tr>
  
  <tr>
  <td colspan=3>
  <font color="red"><s:actionerror/></font>
  </td>
  
  </tr>
  
  
</table>
</div>
</s:form>
<script type="text/javascript">
	
     function on_form_submit(){
         if(document.getElementById("loginname").value == ""){
             alert("用户名不能为空");
             document.getElementById("loginname").focus();
             return false;
         }
         if(document.getElementById("password").value == ""){
             alert("登录口令不能为空");
             document.getElementById("password").focus();
             return false;
         }

         return true;
    }
</script>
</body>
</html>
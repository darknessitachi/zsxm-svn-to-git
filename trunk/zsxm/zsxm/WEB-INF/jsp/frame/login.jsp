<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/meta.jsp"%>
<title>常州科教城管理信息系统</title>
<style>
	*{ margin:0px; padding:0px; list-style:none}
	body{ background:url(images/skin0/index/login.png) center top}
	.login{ background:url(images/skin0/index/log_bg.png);width:705px; height:700px; margin:0px auto}
	.login_text{width:350px; position:relative; top:330px; left:330px; font-size:12px; color:#0683b7}
	.log_but{ background:url(images/skin0/index/logbut.png); height:30px; width:66px; border:0px; margin-right:5px}
	.log_tt{ background:url(images/skin0/index/log_text.png); height:22px; width:200px; border:1pt solid #0683b7;line-height:22px}
</style> 
</head>

<body>
<s:form name="lf" action="login!login.do" method="post" onsubmit="return on_form_submit();">
<div class="login">
 <table  border="0" cellspacing="0" cellpadding="0" class="login_text">
 <tr>
 	
 </tr>
  <tr>
    <td height="35" width="60">用户名：</td>
    <td>
    <s:textfield name="xtuser.loginname" id="xtuser.loginname" cssClass="log_tt" />
    </td>
  </tr>
  <tr>
    <td height="35">密&nbsp&nbsp&nbsp&nbsp码：</td>
    <td>
    <s:password name="xtuser.password"  id="xtuser.password" cssClass="log_tt" />
    </td>
  </tr>
  <tr>
    <td height="50">&nbsp;</td>
    <td>
	    <input type="submit" name="button" id="button" value="登 陆" class="log_but" />
	    <input type="reset"  name="button2" id="button2" value="取 消" class="log_but"/>
	   
    </td>
  </tr>
  <tr>
  <td colspan=2>
  <font color="red"><s:actionerror/></font>
  </td>
  </tr>
</table>
</div>
</s:form>
<script type="text/javascript">
	function rczc(){
		window.location.href = "login!preRczc.do";
	}
     function on_form_submit(){
         if(document.getElementById("xtuser.loginname").value == ""){
             alert("用户名不能为空");
             document.getElementById("xtuser.loginname").focus();
             return false;
         }
         if(document.getElementById("xtuser.password").value == ""){
             alert("登录口令不能为空");
             document.getElementById("xtuser.password").focus();
             return false;
         }

         return true;
    }
</script>
</body>
</html>
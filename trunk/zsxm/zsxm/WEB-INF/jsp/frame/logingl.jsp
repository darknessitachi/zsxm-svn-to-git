<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>常州高层人才信息系统</title>
		<%@ include file="/common/meta.jsp"%>
		<title>常州高层人才管理中心</title>
		<style>
		
body{ 
	font-family: "宋体", Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
 overflow:hidden; 
}
html {
	SCROLLBAR-HIGHLIGHT-COLOR: #f2f2f2;
	SCROLLBAR-3DLIGHT-COLOR: #ffffff;
	SCROLLBAR-ARROW-COLOR: #ffffff;
	SCROLLBAR-TRACK-COLOR: #f2f2f2;
	SCROLLBAR-DARKSHADOW-COLOR: #ffffff;
	SCROLLBAR-BASE-COLOR: #e1e1e1;
	}
a {
	font-size: 12px;
	color: #7c7c7a;
}
a:link {
	text-decoration: none;
	color: #7c7c7a;
}
a:visited {
	text-decoration: none;
	color: #7c7c7a;
}
a:hover {
	text-decoration: none;
	color: #006699;
}
a:active {
	text-decoration: none;
	color:#006699;
}
 a{blr:expression(this.onFocus=this.blur())} 
*{ margin:0px; padding:0px; list-style:none; font-size:12px}

.login{ height:313px; width:602px; background:url(images/skin0/index/login.jpg) no-repeat; margin:0px auto; margin-top:100px; border:5px solid #fff}
.login_box{ margin:0px auto;margin-top:180px; color:#fff}
.login_box .txt{ background:url(images/skin0/index/text.jpg) no-repeat ;   height:17px; width:141px; border:1pt solid #fff; color:#006699; padding-left:5px; padding-right:5px; padding-top:3px}
.login_box .login_but{ background:url(images/skin0/index/go.gif) no-repeat; height:22px; width:73px; border:0px}
.login_box .rest_but{ background:url(images/skin0/index/rest.gif) no-repeat; height:22px; width:73px; border:0px}
.nav{ border:1pt solid #acabab; border-top:0px; overflow:hidden; width:100%}
		</style>
	</head>
	<body bgcolor="#006699">
		<s:form name="lf" action="login!doGlCheckLogin" method="post"
			onsubmit="return on_form_submit();">
			<div class="login">
				
				<table width="300" border="0" align="center" cellpadding="0"
					cellspacing="0" class="login_box">
					<tr>
					<td colspan=2><font color=red><s:actionerror/></font></td>
					</tr>
					<tr>
						<td width="59" height="30" align="right">
							用户名：
						</td>
						<td width="167">
							<s:textfield name="xtuser.loginname"  cssClass="txt" />
						</td>
						
					</tr>
					<tr>
						<td height="30" align="right">
							密 码：
						</td>
						<td>
							<s:password name="xtuser.password" cssClass="txt" />
						</td>
						
					</tr>
					<tr align="center">
						<td colspan=2 height="40">
							<a href="#"><img src="images/skin0/index/go.gif" width="73" height="22"
									border="0" onclick="lf.submit();"/>
							</a>
						&nbsp&nbsp&nbsp&nbsp&nbsp
							<a href="#"><img src="images/skin0/index/rest.gif" width="73"
									height="22" border="0" onclick="lf.reset();"/>
							</a>
							<a href="login!preRczc.do">人才注册</a>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
		<script type="text/javascript">
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
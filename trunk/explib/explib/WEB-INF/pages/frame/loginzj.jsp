<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<meta charset=utf-8" />
<title>常州市专家管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #016aa9;
	overflow:hidden;
}
.STYLE1 {
	color: #000000;
	font-size: 12px;
}
-->
</style></head>

<body>
<s:form name="form" id="form" action="login!checkloginzj.do">
<input type=hidden name="logintype" value=2>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="235" background="images/main/login_03.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="53"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="394" height="53" background="images/main/login_05.gif">&nbsp;</td>
            <td width="206" background="images/main/login_06.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16%" height="25"><div align="right"><span class="STYLE1">用户</span></div></td>
                <td width="57%" height="25"><div align="center">
                  <input type="text" id="loginname" name="loginname" style="width:105px; height:17px; border:solid 1px #7dbad7; font-size:12px; ">
                </div></td>
                <td width="27%" height="25">
                	<div align="left">
                	<input id=btnLogin style="width:60px;height:22px" type="submit" value=" 登 录 " name=btnLogin/>
                </div>
                </td>
              </tr>
              <tr>
                <td height="25"><div align="right"><span class="STYLE1">密码</span></div></td>
                <td height="25"><div align="center">
                  <input type="password" id="password" name="password" style="width:105px; height:17px;  border:solid 1px #7dbad7; font-size:12px;">
                </div></td>
                <td height="25">
                <div align="left">
                	&nbsp;<a href="login!preExpzc.do" ><font size=2 color=white>专家注册</font></a>
                </div>
                </td>
              </tr>
            </table></td>
            <td width="362" background="images/main/login_07.gif">&nbsp;</td>
          </tr>
          
        </table></td>
      </tr>
      
      <tr>
        <td height="213" background="images/main/login_08.gif" align=center><s:actionerror cssStyle="color:red"/>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</s:form>
<script type="text/javascript">
	init();
	function init(){
		document.getElementById('loginname').focus();
	}
</script>
</body>
</html>

<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
-->
</style>
<script>
	var dialogindex = 0;
	function getIndex(){
		return ++dialogindex;
	}
</script>
</head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
  <tr>
    <td><iframe name="mainframe" height="100%" width="100%" border="0" frameborder="0" src="frame!middlegl.do"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe></td>
    <td width="6" bgcolor="#1873aa" style=" width:6px;">&nbsp;</td>
  </tr>
</table>
</body>
</html>

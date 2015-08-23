<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title></title>
	</head>
	<style>

	</style>
	<body style="overflow:hidden;">
		
	</body>
	<script>
		reback();
		function reback(){
			parent.buf('<s:property value="reMap.code"/>','<s:property value="reMap.text"/>','<s:property value="reMap.fid"/>');
		}
		
   </script>
</html>


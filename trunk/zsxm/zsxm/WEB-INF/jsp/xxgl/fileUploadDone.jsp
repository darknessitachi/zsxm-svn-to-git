<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	
		<script type="text/javascript">
			if('<s:property value="targetFileName"/>'!==""){
				
				parent.setTdImage('<s:property value="targetFileName"/>');
			}else{
				alert("没有取到文件名称，可能上传失败！");
			}
		</script>
</html>
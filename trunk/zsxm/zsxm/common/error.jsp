<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		
		<title>Error Page</title>
		<script src="<c:url value="/js/prototype.js"/>" type="text/javascript"></script>
		<script type="text/javascript">
			function closeWin(){
				var pe = window.opener;
				if (!pe) {
					pe = window.dialogArguments;
				}
				if (pe) {
					window.close();
				} else {
					var s = self.name;
					parent.closeXwin(s);
				}
			}
		</script>
	</head>
	<%
	Throwable ex = null;
	if(exception!=null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Exception)request.getAttribute("javax.servlet.error.exception");
	%>
	<body>
		<div id="content">
				<div>
					<h3>
						系统运行异常:<br>
						<%
							if(ex!=null)
								out.println(ex.getMessage());
						%>
					</h3>
				</div>
				<div>
					<!-- <input type="button" value="返回" onclick="history.back();history.back();" /> -->
					<input type="button" value="关闭" onclick="javascript:closeWin();">
				</div>
				<div><a href="#" onclick="$('detail_error_msg').toggle();">Administrator click here to get the detail.</a></div>
				<div id="detail_error_msg" style="display: none">
					<% if (ex != null) { %>
					<pre>
						<% ex.printStackTrace(new java.io.PrintWriter(out)); %>
					</pre>
					<% } %>
				</div>
			</div>
	</body>
</html>
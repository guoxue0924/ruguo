<%
response.setStatus(404);
//如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print("页面不存在.");
}else{
%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@page import="com.foundation.common.web.Servlets"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>404 - 页面不存在</title>
		<link href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	</head>

	<body>
		<br>
		<br>
		<div class="container">
			<div class="jumbotron text-center">
				<h1>404</h1>
				<p class="text-danger">抱歉！您访问的页面不存在。请重试。</p>
				<p>
					<a class="btn btn-primary btn-lg" href="#" role="button" onclick="history.go(-1);" >返回上一页</a>
				</p>
			</div>
		</div>
	</body>

</html>
<%
out.print("<!--"+request.getAttribute("javax.servlet.forward.request_uri")+"-->");
} out = pageContext.pushBody();
%>
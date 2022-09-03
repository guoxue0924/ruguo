<%
response.setStatus(401);

//如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print("没有访问权限");
//输出异常信息页面
}else{
%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@page import="com.foundation.common.web.Servlets"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>401 - 没有访问权限</title>
		<link href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	</head>

	<body>
		<br>
		<br>
		<div class="container">
			<div class="jumbotron text-center">
				<h1>401</h1>
				<p class="text-danger">抱歉！没有访问权限。</p>
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
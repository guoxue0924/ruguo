<%
response.setStatus(500);
//获取异常类
Throwable ex = Exceptions.getThrowable(request);
if (ex != null){
	LoggerFactory.getLogger("500.jsp").error(ex.getMessage(), ex);
}
//如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print("500 系统内部错误");
}else{
%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@page import="com.foundation.common.utils.Exceptions"%>
<%@page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@page import="com.foundation.common.web.Servlets"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>500 - 系统内部错误</title>
		<link href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	</head>

	<body>
		<br>
		<br>
		<div class="container">
			<div class="jumbotron text-center">
				<h1>500</h1>
				<p class="text-danger">抱歉！无法连接服务器或者服务器出现异常。</p>
				<p class="text-danger">如果您在操作时，重复出现此错误，请与系统开发人员联系。</p>
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
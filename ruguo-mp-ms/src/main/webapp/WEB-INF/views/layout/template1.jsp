<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<tiles:insertAttribute name="common" />
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxModules" value="${pageContext.request.contextPath}/modules"/>
<c:set var="ctxPlugins" value="${pageContext.request.contextPath}/plugins"/>
<head>
	<tiles:insertAttribute name="head" />
</head>

<body>
<tiles:insertAttribute name="header" />
<div class="container" style="min-height: 400px">
	<div class="">
		<div class="col-xs-2-sm" role="complementary">
			<tiles:insertAttribute name="menu" />
		</div>
		<div class="col-xs-10-sm" style="padding-left: 10px" role="main">
			<tiles:insertAttribute name="content" />
		</div>
		</div>
	</div>
</div>

<tiles:insertAttribute name="footer" />

</body>
</html>
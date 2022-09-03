<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>${fns:getConfig('productName')}</title>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/plugins/bootstrap/css/font-awesome.min.css" rel="stylesheet">
<link href="${ctx}/modules/login/css/login.css" rel="stylesheet">
<%-- <c:set var="ctxModules" value="${pageContext.request.contextPath}/modules"/> --%>
<%-- <c:set var="ctxPlugins" value="${pageContext.request.contextPath}/plugins"/> --%>
<!--[if lt IE 9]>
<script src="${ctx}/plugins/bootstrap/js/html5shiv.min.js"></script>
<script src="${ctx}/plugins/bootstrap/js/ie8-responsive-file-warning.js"></script>
<script src="${ctx}/plugins/bootstrap/js/respond.min.js"></script>
<![endif]-->

</head>

<body>
<div class="parent">
<header class="jumbotron-login">
        <div class="row ">

            <div class="col-xs-6 col-xs-offset-1">
                <div id="bglogin"></div>
            </div>
            <div class="col-xs-4">
                <div class="panel panel-default panel-login">
                    <div class="panel-title">
                        用户登录
                        <small>UserLogin</small>
                    </div>
                    <br>
                    <div class="panel-body">
                        <form role="form" id="loginForm" action="${ctx}/login" method="post">
                            <div class="form-group">
                                <label><i class="fa fa-user fa-lg" aria-hidden="true"></i> 用户名：</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
                            </div>
                            <br/>
                            <div class="form-group">
                                <label><i class="fa fa-lock fa-lg" aria-hidden="true"></i> 密&nbsp;&nbsp;&nbsp;码：</label>
                                <!--<a href="#" class="link-login">忘记密码？</a>-->
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码">
                            </div>
                            <div align="right"><a href="#" id="forgetPassword">忘记密码？</a></div>
                            <label class="error">${message }</label>
                            <button type="submit" id="loginBtn" class="btn btn-primary btn-block" data-loading-text="登录中..." autocomplete="off">登 录</button>
                        </form>
                    </div>
                </div>

            </div>

        </div>

        <br>
        <br>
        <hr>
        <div class="col-xs-6 col-xs-offset-3">
            <div class="media">
                <div class="media-left">
                </div>

                <div class="media-body">
                    <p class="">
                        Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - ${fns:getConfig('version')}
                    </p>
                    <!--<p class="">-->
                    <!--技术支持：北京图凌科技-->
                    <!--</p>-->
                </div>
            </div>
        </div>
</header>
</div>
<script type="text/javascript">
	var webAppPath = "${ctx}";
</script>
<script src="${ctx}/plugins/require/require.js" defer async="true"
        data-main="${ctx}/modules/login/js/sysLogin"></script>
</body>
</html>
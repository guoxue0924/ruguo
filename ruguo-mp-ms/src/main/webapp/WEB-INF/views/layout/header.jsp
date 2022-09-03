<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>


<nav class="navbar navbar-bg-sy">
    <div class="container">
        <div class="navbar-header">
<%--             <a class="brand" href="#" id="link_brand">
                <img alt="Brand" src="<%=request.getContextPath()%>/common/images/logo.png">
            </a> --%>
			<div class="brand">${fns:getConfig('productName')}</div>
        </div>
        <div class="col-xs-8 navbar-right navbar-content-sy">
            <div class="col-xs-12">
                <ul class="nav navbar-nav">
               <%--      <li><a href="#" id="link_home"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
<!--                    <li><a href="#">我的工作台</a></li>-->
<!--                    <li><a href="#">代办事项</a></li>-->
					<c:if test="${!empty user}">
                    <li><a href="#" id="link_manager">管理中心</a></li>
					</c:if> --%>
<%-- 					<c:set var="firstMenu" value="true"/> --%>
<%-- 					<c:set var="firstMenuId" value="27"/> --%>
<%-- 				<shiro:hasRole name="dept"> --%>
					<c:set var="menuParentId" value="${param.pid}"/>
					<c:if test="${empty menuParentId}">
					    <c:set var="menuParentId" value="${not empty currentMenu ? currentMenu.parent.parent.id : fns:getFristMenuID()}"/>
					</c:if>
					<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
						<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
							<li class="">
								<c:if test="${empty menu.href}">
									<a class="${menu.id eq (menuParentId)?'active':''}" href="<%=request.getContextPath()%>/sys/menu/show?pid=${menu.id}" data-id="${menu.id}"><span>${menu.name}</span></a>
								</c:if>
								<%-- <c:if test="${not empty menu.href}">
									<a class="menu" href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}" data-id="${menu.id}" target="mainFrame"><span>${menu.name}</span></a>
								</c:if> --%>
							</li>
							<%-- <c:if test="${firstMenu}">
								<c:set var="firstMenuId" value="${menu.id}"/>
							</c:if>
							<c:set var="firstMenu" value="false"/> --%>
						</c:if>
					</c:forEach>
<%-- 				</shiro:hasRole> --%>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle loginname" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false"><i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i>&nbsp;
                            您好，${fns:getUser().loginName} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#" id="to_userinfo">个人信息</a></li>
                            <li><a href="#" id="to_password">修改密码</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#" id="to_logout">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
                
            </div>
        </div>
    </div>
</nav>

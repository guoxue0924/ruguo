<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<c:set var="menuParentId" value="${param.pid}"/>
<c:if test="${empty menuParentId}">
    <c:set var="menuParentId" value="${not empty currentMenu ? currentMenu.parent.parent.id : fns:getFristMenuID()}"/>
</c:if>

<c:set var="menuList" value="${fns:getMenuList()}"/>
<%-- <c:out value="${menuParentId}"/> --%>
<%-- <c:out value="${currentMenu.id}"/> --%>
<%-- <c:out value="${currentMenu.parent.id}"/> --%>
<nav class="bs-docs-sidebar">
    <ul class="nav bs-docs-sidenav">
        <c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
            <c:if test="${menu.parent.id eq (not empty menuParentId ? menuParentId:1) && menu.isShow eq '1'}">

                <li class="active">
                    <a href="#" class="fmenu"><span class="glyphicon glyphicon-menu-down"></span> ${menu.name}</a>
                    <ul class="nav">
                        <c:forEach items="${menuList}" var="menu2">
                            <c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
                                <li class=""><a class="${menu2.id eq (currentMenu.id)?'active':''}" data-menuid="${menu2.id}" data-menupid="${menuParentId }"
                                                href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}">
                                    ${menu2.name}</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </li>

            </c:if>
        </c:forEach>
    </ul>
</nav>

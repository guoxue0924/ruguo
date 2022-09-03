<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<footer>
    <div class="container">
        <hr>
        <br>

        <p class="pull-right"><a href="#">Back to top</a></p>

        <p class="text-center">
            Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - ${fns:getConfig('version')}
        </p>
        <br>
    </div>
</footer>
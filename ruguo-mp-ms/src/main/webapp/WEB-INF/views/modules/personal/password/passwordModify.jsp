<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default">

	<div class="panel-heading-blue">
        <div class="panel-title-blue">
      		 修改密码
        </div>
    </div>
	
	<form id="optForm" name="optForm">
	    <input type="hidden" id="resetPaword" name="resetPaword" value="${resetPaword}"> 
	    <input type="hidden" id="resetUserInfo" name="resetUserInfo" value="${resetUserInfo}">
		<div class="panel-body form-horizontal">
		
		    <div class="form-group" data-msg-direction="d">
	            <label class="col-xs-4 control-label required">旧密码：</label>
	            <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus" data-content="请输入旧密码">
	                <input id="oldPassword" name="oldPassword" type="password" class="form-control" />
	            </div>
		    </div>
		    
		    <div class="form-group" data-msg-direction="d">
	            <label class="col-xs-4 control-label required">新密码：</label>
	            <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus" data-content="请输入新密码">
	                <input id="newPassword" name="newPassword" type="password" class="form-control" />
	            </div>
		    </div>
		    
		    <div class="form-group" data-msg-direction="d">
	            <label class="col-xs-4 control-label required">确认新密码：</label>
	            <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus" data-content="请再次输入新密码">
	                <input id="confirmNewPassword" name="confirmNewPassword" type="password" class="form-control" />
	            </div>
		    </div>
		    
		    <div class="form-group" data-msg-direction="d">
		    	<div class="col-xs-3"></div>
	            <label class="col-xs-6 checkbox-inline" style="">密码规则: 12-32个字符组成，同时包含大写字母、小写字母、数字和符号！</label>
		    </div>
	    </div>
	</form>
	
	<div class="panel-footer text-center">
		<button type="button" class="btn btn-primary" data-formatter="commands" id="saveBtn">保存</button>
		<!-- <button type="button" class="btn btn-default" data-formatter="commands" id="cancelBtn">取消</button> -->
    </div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/personal/password/passwordModify"></script>
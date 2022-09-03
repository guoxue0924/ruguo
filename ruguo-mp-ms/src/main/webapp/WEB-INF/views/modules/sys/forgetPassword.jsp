<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">

        <div class="panel-body form-horizontal">
            <div class="form-group" data-msg-direction="r">
                <label >
                  忘记密码请与管理员联系重置密码~
                </label>
            </div>
            <div class="form-group" data-msg-direction="r">
                <label style="float: left;">
                  管理员邮箱:
                </label>

                <div >
                    <label>${user.email }</label>
                </div>
            </div>
            
           
        </div>

    <div class="text-center">
       
        
		<button type="button" data-formatter="commands"
                id="btnCancel" class="btn btn-default">
            返回
        </button>
</div>
</div>
		
<script src="<%=request.getContextPath()%>/modules/login/js/forgetPassword.js"></script>
		
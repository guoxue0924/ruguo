<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">

        <form id="userForm" name="userForm">
            <div class="panel-body form-horizontal">
                <div class="form-group">
                    <label class="col-xs-4 control-label">
                        姓名：
                    </label>
                    <div class="col-xs-8-sm">
	                    <span class="control-label-text">
	                         ${demoUser.name}
	                    </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label">
                        邮箱：
                    </label>
                    <div class="col-xs-8-sm">
		                <span class="control-label-text">
		                         ${demoUser.email}
		                </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label">
                        电话：
                    </label>
                    <div class="col-xs-8-sm">
		                <span class="control-label-text">
		                         ${demoUser.phone}
		                </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label">
                        手机：
                    </label>
                    <div class="col-xs-8-sm">
		                <span class="control-label-text">
		                         ${demoUser.mobile}
		                </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label">
                        出生日期：
                    </label>
                    <div class="col-xs-8-sm">
		                <span class="control-label-text">
		                     <fmt:formatDate value="${demoUser.birthday}" type="date"/>
		                </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label">
                        用户类型：
                    </label>
                    <div class="col-xs-8-sm">
		                <span class="control-label-text">
		                     ${fns:getDictLabel(demoUser.userType,'sys_user_type','')}
		                </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4 control-label">
                        是否可登录：
                    </label>
                    <div class="col-xs-3-sm">
		                <span class="control-label-text">
		                ${fns:getDictLabel(demoUser.loginFlag,'DICT_IDENTITY_FLAG','')}
		                </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4 control-label text-right">兴趣爱好：</label>

                    <div class="col-xs-8-sm">
                    	<span class="control-label-text">
		                	<c:forEach items="${fns:getDictListNoBlank('DICT_BABY_NUMBER')}" var="DICT_BABY_NUMBER">
		                   		<c:if test="${fn:contains(demoUser.hobby, DICT_BABY_NUMBER.value)}">${DICT_BABY_NUMBER.label}</c:if>
							</c:forEach>   
	                	</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4 control-label">
                        备注：
                    </label>
                    <div class="col-xs-7-sm">
                		<span class="control-label-text">
                         ${demoUser.remark}
                 		</span>
                    </div>
                </div>
                
            </div>
        </form>
        <hr>
        <div class="col-xs-12 text-center">
            <button class="btn btn-primary" id="btnCancle">
                取消
            </button>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/demo/user/userDetail.js"></script>

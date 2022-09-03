<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">
	 <form id="userFormEdit" name="userFormEdit">
		<input type="hidden" id="userFromId" name="id" value="${result.id}">
		<input type="hidden" id = "userrole" name="userrole" value="${result.userRole}">
        <div class="panel-body form-horizontal">
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                                                                            真实姓名
                </label>
                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus">
                     <input type="text" class="form-control" id="name1" value="${result.name}" name="name"/>
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                                                                            用户名
                </label>
                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus">
                     <input type="text" class="form-control" id="loginName1" value="${result.loginName}" name="loginName"/>
                </div>
            </div>
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                                                                          所属角色
                </label>
                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus">
					<select id="userRole1" name="userRole" class="selectpicker" title="请选择" data-style="btn-sm btn-default">
					</select>
                </div>
            </div>
            
        </div>
    </form>
    <div class="panel-footer text-center">
        <button type="button" data-formatter="commands" id="saveBtn"
				class="btn btn-primary">保存</button>
	    <button type="button" data-formatter="commands" id="btnCancle"
				class="btn btn-default">关闭</button>
    </div>
</div>
</div>
<script src="<%=request.getContextPath()%>/modules/usermanage/userEdit.js"></script>


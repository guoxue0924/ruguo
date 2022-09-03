<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">

        <form id="userForm" name="userForm">
            <input type="hidden" name="id" value="${demoUser.id}">
            <div class="panel-body form-horizontal">
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        姓名：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入20位有效字符">
                        <input type="text" class="form-control" id="sname" name="name" value="${demoUser.name}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        邮箱：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入20位有效字符">
                        <input type="text" class="form-control" name="email" value="${demoUser.email}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                        电话：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入20位有效字符">
                        <input type="text" class="form-control" name="phone" value="${demoUser.phone}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        手机：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入20位有效字符">
                        <input type="text" class="form-control" name="mobile" value="${demoUser.mobile}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        出生日期：
                    </label>

                    <div class="col-xs-4-sm">
                        <div class="input-group date form_datetime"
                             data-date-format="dd-MM-yyyy">
                            <input class="form-control" size="10" type="text" value="<fmt:formatDate value="${demoUser.birthday}" type="date"/>"
                            name="birthday">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        人员选择：
                    </label>

                    <div class="col-xs-4-sm">
                    	<div class="input-group">
                            <input type="text" class="form-control" id="s_user2" name="" placeholder="点击按钮选择人员"
                                   autocomplete="off">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" id="user2btn"><span
                                        class="glyphicon glyphicon-search"></span></button>
                            </span>

                        </div>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        用户类型（单选）：
                    </label>

                    <div class="col-xs-4-sm">
                        <select class="selectpicker form-control" id="userType" name="userType" data-actionsBox="true"
                                title="请选择">
                            <c:forEach items="${fns:getDictList('DICT_EDUCATE_GRADE')}" var="DICT_EDUCATE_GRADE">
                                <option value="${DICT_EDUCATE_GRADE.value}"
                                <c:if test="${demoUser.userType eq DICT_EDUCATE_GRADE.value}">selected</c:if>
                                >${DICT_EDUCATE_GRADE.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       学历（多选）：
                    </label>

                    <div class="col-xs-4-sm">
                        <select class="selectpicker form-control" id="education" name="education" data-actionsBox="true"
                                title="请选择" multiple>
                            <c:forEach items="${fns:getDictListNoBlank('DICT_NATION_TYPE')}" var="DICT_NATION_TYPE">
                                <option value="${DICT_NATION_TYPE.value}"
                                <c:if test="${fns:contains(demoUser.education, DICT_NATION_TYPE.value)}">selected</c:if>
                                >${DICT_NATION_TYPE.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        省：
                    </label>

                    <div class="col-xs-4-sm">
                        <select class="selectpicker selectArea2" name="province" id="province" data-area-id="province" data-area-value="210000000"
                                 title="请选择" data-live-search="true" data-size="15">
                         </select>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        市：
                    </label>

                    <div class="col-xs-4-sm">
                        <select class="selectpicker selectArea2" name="city" id="city" data-area-id="city" data-area-value="210100000"
                                 title="请选择">
                         </select>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        县/区划：
                    </label>

                    <div class="col-xs-4-sm">
                        <select class="selectpicker selectArea2" name="county" id="county" data-area-id="county" data-area-value="210101000"
                                 title="请选择">
                         </select>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        乡镇/街道：
                    </label>

                    <div class="col-xs-4-sm">
                        <select class="selectpicker selectArea2" name="town" id="town" data-area-id="town" data-area-value="210101001"
                                 title="请选择">
                         </select>
                    </div>
                </div>

                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        是否可登录：
                    </label>

                    <div class="col-xs-3-sm">
                        <c:forEach items="${fns:getDictListNoBlank('DICT_IDENTITY_FLAG')}" var="DICT_IDENTITY_FLAG">
                            <label class="radio-inline">
                                <input type="radio" name="loginFlag"
                                       value="${DICT_IDENTITY_FLAG.value}"
                                <c:if test="${demoUser.loginFlag eq DICT_IDENTITY_FLAG.value}">checked</c:if>
                                > ${DICT_IDENTITY_FLAG.label}
                            </label>
                        </c:forEach>

                    </div>

                </div>

                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label text-right">兴趣爱好：</label>

                    <div class="col-xs-8-sm">
                        <c:forEach items="${fns:getDictListNoBlank('DICT_BABY_NUMBER')}" var="DICT_BABY_NUMBER">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="hobby" value="${DICT_BABY_NUMBER.value}"
                                <c:if test="${fn:contains(demoUser.hobby, DICT_BABY_NUMBER.value)}">checked</c:if>
                                > ${DICT_BABY_NUMBER.label}
                            </label>
                        </c:forEach>

                    </div>
                </div>

                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                        备注：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="最大可输入500个汉字" data-placement="right">
                                            <textarea name="remark" class="form-control"
                                                      onpropertychange="if(value.length>500) value=value.substr(0,500)"
                                                      rows="3">${demoUser.remark}</textarea>
                    </div>
                </div>
            </div>
        </form>
        <hr>
        <div class="col-xs-12 text-center">
            <button class="btn btn-default" id="btnCancle">
                取消
            </button>
            <button type="button" data-formatter="commands"
                    id="btnReset" class="btn btn-default">
                重置
            </button>
            <button type="button" data-formatter="commands"
                    id="btnSave" class="btn btn-primary">
                保存
            </button>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/demo/user/userEdit.js"></script>
        
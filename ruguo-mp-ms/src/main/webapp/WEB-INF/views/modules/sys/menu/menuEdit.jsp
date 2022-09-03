<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<style>
	.help-block {
	    margin-bottom: 0;
	}
</style>
<div class="row">
    <div class="col-xs-12">

        <form id="menuForm" name="menuForm">
            <input type="hidden" id="menuid" name="id" value="${menu.id}">
                
            <div class="panel-body form-horizontal">
	            <div class="form-group" data-msg-direction="r">
	                <label class="col-xs-4 control-label required">
	                上级菜单：
	                </label>
	
	                <div class="col-xs-4-sm">
<%-- 	                <sys:treeselect id="menu" name="parent.id" value="${menu.parent.id}" labelName="parent.name" labelValue="${menu.parent.name}" --%>
<%-- 					title="菜单" url="/sys/menu/treeData" extId="${menu.id}" cssClass="required"/> --%>
	                	<div class="input-group">
	                		<input type="hidden" id="parentid" name="parent.id" value="${menu.parent.id}">
	                        <input type="text" class="form-control" id="parentname" name="parent.name" readOnly="true"
	                               autocomplete="off" value="${menu.parent.name}">
	                        <span class="input-group-btn">
	                            <button class="btn btn-default" type="button" id="menuBtn"><span
	                                    class="glyphicon glyphicon-search"></span></button>
	                        </span>
	
	                    </div>
	                </div>
	            </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        名称：
                    </label>

                    <div class="col-xs-4-sm">
                        <input type="text" class="form-control" name="name" value="${menu.name}" autocomplete="off">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                       链接：
                    </label>

                    <div class="col-xs-7-sm">
                        <input type="text" class="form-control" name="href" value="${menu.href}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                        目标：
                    </label>

                    <div class="col-xs-4-sm">
                        <input type="text" class="form-control" name="target" value="${menu.target}" autocomplete="off">
                        <span  class="help-block">"init"：一级菜单下默认加载页面</span >
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                        权限标识：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" name="permission" value="${menu.permission}" autocomplete="off">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        排序：
                    </label>

                    <div class="col-xs-4-sm">
                        <input type="text" class="form-control" name="sort" value="${menu.sort}" autocomplete="off">
                    </div>
                </div>
                
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        是否可见：
                    </label>

                    <div class="col-xs-3-sm">
                        <c:forEach items="${fns:getDictListNoBlank('DICT_SHOW_HIDE')}" var="DICT_SHOW_HIDE">
                            <label class="radio-inline">
                                <input type="radio" name="isShow"
                                       value="${DICT_SHOW_HIDE.value}"
                                <c:if test="${menu.isShow eq DICT_SHOW_HIDE.value}">checked</c:if>
                                > ${DICT_SHOW_HIDE.label}
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
                                                      rows="3">${menu.remark}</textarea>
                    </div>
                </div>
            </div>
        </form>
        <hr>
        <div class="col-xs-12 text-center">
            <button class="btn btn-default" id="btnCancle">
                取消
            </button>
            <!-- <button type="button" data-formatter="commands"
                    id="btnReset" class="btn btn-default">
                重置
            </button> -->
            <button type="button" data-formatter="commands"
                    id="btnSave" class="btn btn-primary">
                保存
            </button>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/sys/menu/menuEdit.js"></script>
        
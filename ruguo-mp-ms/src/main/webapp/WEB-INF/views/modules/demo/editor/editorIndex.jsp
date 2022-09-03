<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">

    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            富文本编辑
        </div>
    </div>

    <form id="userForm" name="userForm">
        <div class="panel-body form-horizontal">
            <%-- <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    姓名：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                    <input type="text" class="form-control" name="name">
                </div>
            </div>
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    性别：
                </label>

                <div class="col-xs-3-sm">
                    <select class="selectpicker" id="gender" name="gender" 
                            data-title="请选择1">
                        <c:forEach items="${fns:getDictList('DICT_GENDER_ITEM')}"
							var="DICT_GENDER_ITEM">
							<option value="${DICT_GENDER_ITEM.value}">${DICT_GENDER_ITEM.label}</option>
						</c:forEach>
                    </select>
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    邮箱：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                    <input type="text" class="form-control" name="email" value="">
                </div>
            </div> --%>
            
<!--             <div id="summernote">Hello Summernote</div> -->
<!-- 回显数据展示 -->
            <div id="summernote">aosdijfoaisdjfoasijdofiasjdofijasodjfaosijfdo</div>

        </div>
    </form>

    <div class="panel-footer text-center">
        <button type="button" data-formatter="commands"
                id="btnDisabled" class="btn btn-danger">
           禁用
        </button>
        <button type="button" data-formatter="commands"
                id="btnEnabled" class="btn btn-success">
           启用
        </button>
        <button type="button" data-formatter="commands"
                id="btnGetContents" class="btn btn-primary">
           获取内容
        </button>
        <button type="button" data-formatter="commands"
                id="btnReset" class="btn btn-default">
            重置
        </button>
        <button type="button" data-formatter="commands"
                id="btnEmpty" class="btn btn-success">
            校验空文本
        </button>
        <button type="button" data-formatter="commands"
                id="btnSetVal1" class="btn btn-primary">
            insertText
        </button>
        <button type="button" data-formatter="commands"
                id="btnSetVal2" class="btn btn-primary">
            setCode
        </button>
    </div>

</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/editor/editorIndex"></script>
		

		
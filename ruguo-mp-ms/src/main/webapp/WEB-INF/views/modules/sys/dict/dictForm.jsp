<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">

    <form id="dictForm" name="dictForm">
        <div class="panel-body form-horizontal">

            <div class="form-group" data-msg-direction="r" style="display: none;">
                <label class="col-xs-4 control-label">
                    主键：
                </label>

                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                     data-content="长度不大于100">
                    <input type="text" class="form-control" id="id" name="id" value="${result.id}">
                </div>
            </div>

            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    键值：
                </label>

                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                     data-content="长度不大于100">
                    <input type="text" class="form-control" id="value" name="value" value="${result.value}">
                </div>
            </div>

            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    标签：
                </label>

                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                     data-content="长度不大于50">
                    <input type="text" class="form-control" id="label" name="label" value="${result.label}">
                </div>
            </div>

            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    类型：
                </label>

                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                     data-content="长度不大于50">
                    <input type="text" id="type" class="form-control" name="type" value="${result.type}">
                </div>
            </div>

            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    描述：
                </label>

                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                     data-content="长度不大于50">
                    <input type="text" id="description" class="form-control" name="description" value="${result.description}">
                </div>
            </div>

            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    排序：
                </label>

                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入最多11位有效数字">
                    <input type="text" id="sort" class="form-control" name="sort" value="${result.sort}">
                </div>
            </div>

            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">
                    备注：
                </label>

                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                     data-content="长度不大于500">
                    <textarea id="remark" class="form-control" name="remark">${result.remark}</textarea>
                </div>
            </div>

        </div>
    </form>

    <div class="panel-footer text-center">

        <button type="button" data-formatter="commands"
                id="btnSave" class="btn btn-primary">
            保存
        </button>

        <button type="button" data-formatter="commands"
                id="btnReset" class="btn btn-default">
            重置
        </button>

        <button type="button" data-formatter="commands"
                id="btnClose" class="btn btn-default">
            关闭
        </button>

    </div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/modules/sys/dict/dictForm.js"></script>
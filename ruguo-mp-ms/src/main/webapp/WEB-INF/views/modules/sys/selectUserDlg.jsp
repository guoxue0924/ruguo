<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12" style="height:100px;">
        <form id="selectUserSearchForm" name="searchForm">
            <div class="panel-body form-horizontal search-body">

                <div class="col-xs-12-sm">
                    <div class="form-group" data-msg-direction="r">
                        <label class="col-xs-4 control-label required">
                            人员：
                        </label>
                        <div class="col-xs-7-sm" data-toggle="popover" data-trigger="focus"
                             data-content="请输入20位有效字符">
                            <input type="text" class="form-control typeahead" name="name" autocomplete="off">
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <hr>
        <div class="col-xs-12 text-center">
            <button type="button" data-formatter="commands"
                    id="btnSelectUserDialogSave" class="btn btn-primary">
                确定
            </button>
            <button class="btn btn-primary" id="btnSelectUserDialogCancle">
                关闭
            </button>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/sys/selectUserDlg.js"></script>

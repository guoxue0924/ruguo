<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">
        <form id="memberLevelForm" name="memberLevelForm">
            <input type="hidden" name="id" value="${memberLevelInfo.id}"/>
            <div class="panel-body form-horizontal">
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       会员等级名称：
                    </label>
                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入50位有效字符">
                        <input type="text" class="form-control" id="smemberLevelName" name="memberLevelName" value="${memberLevelInfo.memberLevelName}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       会员等级描述：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入200位有效字符">
                        <input type="text" class="form-control" id="smemberLevelDesc" name="memberLevelDesc" value="${memberLevelInfo.memberLevelDesc}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       会员折扣：
                    </label>

                    <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入1-100有效数字">
                        <input type="text" class="form-control" id="memberLevelDiscount" name="memberLevelDiscount" value="${memberLevelInfo.memberLevelDiscount}">
                    </div>
                    <div class="col-xs-1-sm" style="margin-left: 10px;margin-top: 10px;">
                 <div >%</div>
                 </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        等级下限：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="smemberLevelMin" name="memberLevelMin" value="${memberLevelInfo.memberLevelMin}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        等级上限：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="smemberLevelMax" name="memberLevelMax" value="${memberLevelInfo.memberLevelMax}">
                    </div>
                </div>
            </div>
        </form>
        <hr>
        <div class="col-xs-12 text-center">
            <button type="button" data-formatter="commands"
                    id="btnSave" class="btn btn-primary">
                保存
            </button>
            <button type="button" data-formatter="commands"
                    id="btnReset" class="btn btn-default">
                重置
            </button>
            <button class="btn btn-default" id="btnCancle">
                取消
            </button>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/member/memberlevel/memberLevelEdit.js"></script>
        
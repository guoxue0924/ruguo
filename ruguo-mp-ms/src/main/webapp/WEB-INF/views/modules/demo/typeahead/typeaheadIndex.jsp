<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">

    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            快速查询
        </div>
    </div>

    <form id="userForm" name="userForm">
        <div class="panel-body form-horizontal">
             <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    数据：
                </label>

                <div class="col-xs-6-sm" id="source">
                    
                </div>
            </div>
             <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    查询name：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                    <input type="text" class="form-control typeahead" name="name" autocomplete="off">
                </div>
            </div>

        </div>
    </form>

    <div class="panel-footer text-center">
        <button type="button" data-formatter="commands"
                id="btnGetContents" class="btn btn-primary">
           获取内容
        </button>
        <button type="button" data-formatter="commands"
                id="btnReset" class="btn btn-default">
            重置
        </button>
    </div>

</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/typeahead/typeaheadIndex"></script>
		

		
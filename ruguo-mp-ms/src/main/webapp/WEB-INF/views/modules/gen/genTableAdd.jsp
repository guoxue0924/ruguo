<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">

        <div class="panel-body form-horizontal">
	        <div class="form-group" data-msg-direction="r">
	            <label class="col-xs-4 control-label required">
	                数据源：
	            </label>
	
	            <div class="col-xs-6-sm">
	            	<select class="selectpicker form-control" name="datasource" id="datasource"
	                        title="请选择">
		                <c:forEach items="${config.dataSourceList}" var="dict">
							<option value="${fns:escapeHtml(dict.value)}">${fns:escapeHtml(dict.label)}</option>
						</c:forEach>
					</select>
	            </div>
	        </div>
	        <div class="form-group" data-msg-direction="r">
	            <label class="col-xs-4 control-label required">
	                表名：
	            </label>
	
	            <div class="col-xs-6-sm">
	                <select class="selectpicker form-control" name="tablename" id="tablename" data-live-search="true"
	                        title="请选择">
	                    <c:forEach items="${tableList}" var="list">
	                        <option value="${list.name}" data-subtext="${list.comments}">
	                        ${list.name}</option>
	                    </c:forEach>
	                </select>
	            </div>
	        </div>
        </div>
        <hr>
        <div class="col-xs-12 text-center">
            <button class="btn btn-default" id="btnCancle">
                取消
            </button>
            <button type="button" data-formatter="commands"
                    id="btnNext" class="btn btn-primary">
                下一步
            </button>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/gen/genTableAdd.js"></script>
        
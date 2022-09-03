<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<style>
<!--
.help-block {
	margin-top: 7px;
    margin-bottom: 7px;
}
-->
</style>
<div class="panel panel-default panel-search">
	<div class="panel-heading-blue">
		<div class="panel-title-blue">
			生成方案添加
		</div>
	</div>
	<div class="panel-body form-horizontal">
		<form id="tableForm" name="tableForm">
			<input type="hidden" name="id" value="${genScheme.id}" />
			<input type="hidden" id="flag" name="flag" value="0" />
			<div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label required">
                        业务表名：
                </label>

				<div class="col-xs-4 col-lg-3">
					<select class="selectpicker" name="genTable.id" data-live-search="true" id="gentableid"
	                        title="请选择" ${empty genScheme.id? '': 'disabled'}>
	                    <c:forEach items="${tableList}" var="list">
	                        <option value="${list.id}" data-subtext="${list.comments}" ${list.id==genScheme.genTable.id? 'selected': ''}>
	                        ${list.datasource}.${list.name}</option>
	                    </c:forEach>
	                </select>
				</div>
<!-- 				<span class="help-block">生成的数据表，一对多情况下请选择主表。</span> -->
				<span class="help-block">生成的数据表。</span>
			</div>
			<div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label required">
                        模板分类：
                </label>

				<div class="col-xs-4 col-lg-3">
					<select class="selectpicker" name="category" id="category" title="请选择">
						<c:forEach items="${config.categoryList}" var="categoryList">
							<option value="${categoryList.value}" ${categoryList.value==genScheme.category? 'selected': ''}>${categoryList.label}</option>
						</c:forEach>
					</select>
				</div>
				<span class="help-block">生成结构：{包名}/{模块名}/{分层(dao,entity,service,web)}/{子模块名}/{java类}</span>
			</div>
			<div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label required">
                        方案名称：
                </label>

				<div class="col-xs-4 col-lg-3">
					<input type="text" class="form-control" id="fname" name="name" value="${genScheme.name}">
				</div>
			</div>
			
			<div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label required">
                       生成包路径：
                    </label>

				<div class="col-xs-4 col-lg-3" data-toggle="popover" data-trigger="focus" data-content="请输入64位有效字符">
					<input type="text" class="form-control" name="packageName" value="${genScheme.packageName}" maxlength="64">
				</div>
				<span class="help-block">建议模块包：com.tuling.modules</span>
			</div>
			<div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label required">
                        生成模块名：
                </label>

				<div class="col-xs-4 col-lg-3" data-toggle="popover" data-trigger="focus" data-content="请输入64位有效字符">
					<input type="text" class="form-control" name="moduleName" value="${genScheme.moduleName}" maxlength="64">
				</div>
				<span class="help-block">可理解为子系统名，例如 demo</span>
				
			</div>
			<div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label">
                       生成子模块名：
                </label>

				<div class="col-xs-4 col-lg-3" data-toggle="popover" data-trigger="focus" data-content="请输入64位有效字符">
					<input type="text" class="form-control" name="subModuleName" value="${genScheme.subModuleName}" maxlength="64">
				</div>
				<span class="help-block">可选，分层下的文件夹</span>
			</div>
			<div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label required">
                       生成功能描述：
                    </label>

				<div class="col-xs-4 col-lg-3" data-toggle="popover" data-trigger="focus" data-content="请输入64位有效字符">
					<input type="text" class="form-control" id="functionName" name="functionName" value="${genScheme.functionName}" maxlength="64">
				</div>
				<span class="help-block">将设置到类描述</span>
			</div>
			<%-- <div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label required">
                       生成功能名：
                </label>

				<div class="col-xs-4 col-lg-3" data-toggle="popover" data-trigger="focus" data-content="请输入64位有效字符">
					<input type="text" class="form-control" name="functionNameSimple" value="${genScheme.functionNameSimple}" maxlength="64">
				</div>
				<span class="help-block">用作功能提示，如：保存“某某”成功</span>
				
			</div> --%>
			<div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label required">
                       生成功能作者：
                </label>

				<div class="col-xs-4 col-lg-3" data-toggle="popover" data-trigger="focus" data-content="请输入12位有效字符">
					<input type="text" class="form-control" name="functionAuthor" value="${genScheme.functionAuthor}" maxlength="12">
				</div>
				<span class="help-block">功能开发者</span>
			</div>
			<div class="form-group" data-msg-direction="r">
                <label class="col-xs-4-sm control-label">
                    生成选项：
                </label>

                <div class="col-xs-4">
                	<label class="checkbox-inline">
                        <input type="checkbox" name="replaceFile" value="1" checked> 是否替换现有文件
                    </label>
                </div>

            </div>
			<div class="panel-footer text-center">
				<button type="button" class="btn btn-primary btn-bottom btn-wide btnSave" value="0">保存</button>
				<button type="button" class="btn btn-success btn-bottom btn-wide btnSave" value="1">保存并生成代码</button>
				<button type="button" id="btnCancel" class="btn btn-default btn-bottom btn-wide" onclick="history.go(-1)">返回</button>
			</div>
		</form>
	</div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true" data-main="<%=request.getContextPath()%>/modules/gen/genSchemeEdit"></script>
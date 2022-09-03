<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">
	<div class="panel-heading-blue">
		<div class="panel-title-blue">意见反馈</div>
		<button class="btn btn-default btn-xs btn-wide pull-right"
			id="resetBtn">重置</button>
		<button type="button"
			class="btn btn-primary btn-xs btn-wide pull-right search-button"
			id="btnQuery">查询</button>
	</div>
	<form id="feedbackForm" name="feedbackForm">
		<div class="panel-body form-horizontal search-body" id="searchPanel3">
			<div class="col-xs-5">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">用户名：</label>
					 <div class="col-xs-7-sm">
						<input type="text" maxlength="64" value="" name="name"
							class="form-control" placeholder="请输入用户名">
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">开始时间：</label>
					<div class="col-xs-7-sm">
						<div class="input-group input-group-sm date form_datetime">
							<input class="form-control" id="startTime"
								name="startTime" size="7" type="text"
								placeholder="请输入开起始时间" readonly="readonly"> <span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">终止时间：</label>
					<div class="col-xs-7-sm">
						<div class="input-group input-group-sm date form_datetime">
							<input class="form-control" id="endTime"
								name="endTime" size="7" type="text"
								placeholder="请输入终止时间" readonly="readonly"> <span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
			</div>
			
			 <div class="col-xs-5">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">操作状态：</label>

					<div class="col-xs-7-sm">
						<select class="selectpicker" name="operationState"
							id="operationState" title="请选择" data-style="btn-sm btn-default">
							 <c:forEach items="${fns:getDictList('DICT_AGREE_REPLY')}"
                                           var="DICT_AGREE_REPLY">
                                    <option value="${DICT_AGREE_REPLY.value}">${DICT_AGREE_REPLY.label}</option>
                             </c:forEach>
						</select>
					</div>
				</div>
			</div> 
		</div>
	</form>
	<div class="panel panel-default panel-search">
		<div class=" panel-body">
			<table id="memberFeedbackGrid" class="table table-hover">
				<thead class="bg-default" id="thead">
					<tr>
						<th data-header-align="left" data-align="left" data-column-id="id"
							data-identifier="false" data-visible="false"
							data-visibleinselection="false"></th>
						<th data-align="center" data-column-id="name"
							data-formatter="name">用户名</th>
						<th data-align="center" data-column-id="document"
							data-formatter="document">反馈内容</th>
						<th data-column-id="mobilePhone"
							data-formatter="mobilePhone" data-align="center">手机号</th>
						<th data-align="center" data-column-id="email"
							data-formatter="email">邮箱</th>
						<th data-column-id="updateTime"
							data-formatter="updateTime" data-align="center" data-formatter="updateTime">反馈时间</th>
						<th data-align="center" data-column-id="operationState" data-formatter="operationState">操作状态</th>
						<th data-align="center" data-column-id="replyContent" data-formatter="replyContent">回复内容</th>
						<th data-align="left" data-column-id="replyPersonnelName"
							data-formatter="replyPersonnelName">操作人</th>
						
						<th data-align="center" data-formatter="operation"
							data-sortable="false">操作</th>
					</tr>
			</table>
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js"defer async="true"
	data-main="<%=request.getContextPath()%>/modules/personal/feedback/feedbackIndex"></script>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default panel-search">
	<div class="panel-heading-blue">
		<div class="panel-title-blue">会员查询</div>
		<button type="button"
			class="btn btn-primary btn-xs btn-wide pull-right search-button"
			id="btnExport">导出</button>
		<button class="btn btn-default btn-xs btn-wide pull-right"
			id="resetBtn">重置</button>
		<button type="button"
			class="btn btn-primary btn-xs btn-wide pull-right search-button"
			id="btnQuery">查询</button>
	</div>
	<form id="memberBasicInfoForm" name="memberBasicInfoForm">
		<div class="panel-body form-horizontal search-body" id="searchPanel3">
			<div class="col-xs-12-sm">
			<div class="col-xs-5">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">会员名称：</label>
					 <div class="col-xs-7-sm">
						<input type="text" maxlength="64" value="" name="name"
							class="form-control" placeholder="请输入会员名称">
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">会员手机号：</label>

					 <div class="col-xs-7-sm">
						<input type="text" maxlength="64" value="" name="relaPerMobilePhone"
							class="form-control" placeholder="请输入会员手机号">
					</div>
				</div>
			</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">注册起始时间：</label>
					<div class="col-xs-7-sm">
						<div class="input-group input-group-sm date form_datetime">
							<input class="form-control" id="startCreateTime"
								name="startCreateTime" size="7" type="text"
								placeholder="请输入注册起始时间" readonly="readonly"> <span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">注册终止时间：</label>
					<div class="col-xs-7-sm">
						<div class="input-group input-group-sm date form_datetime">
							<input class="form-control" id="endCreateTime"
								name="endCreateTime" size="7" type="text"
								placeholder="请输入注册终止时间" readonly="readonly"> <span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-xs-5" style="display:none">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">会员等级：</label>

					<div class="col-xs-7-sm">
						<select class="selectpicker" name="memberLevelCode"
							id="memberLevelCode" title="请选择" data-style="btn-sm btn-default">
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-5" style="display:none">
				<div class="form-group form-group-sm">
					<label class="col-xs-5-sm control-label">来源机构：</label>

					<div class="col-xs-7-sm">
						<select class="selectpicker" name="orgCode" id="orgCode"
							title="请选择" data-style="btn-sm btn-default">
						</select>
					</div>
				</div>
			</div>
		</div>
	
	</form>
	<div class="panel panel-default panel-search">
		<div class=" panel-body">
			<table id="memberBasicInfoGrid" class="table table-hover">
				<thead class="bg-default" id="thead">
					<tr>
						<th data-header-align="left" data-align="left" data-column-id="id"
							data-identifier="false" data-visible="false"
							data-visibleinselection="false"></th>
						<th data-align="center" data-column-id="name"
							data-formatter="relaPerName">会员名称</th>
						<th data-align="center" data-column-id="genderName"
							data-formatter="relaPerGenderName">会员性别</th>
						<th data-align="center" data-column-id="birthday"
							data-formatter="relaPerBirthday">出生日期</th>
						<th data-column-id="mobilePhone"
							data-formatter="relaPerMobilePhone" data-align="center">手机号</th>
						<th data-column-id="createTime"
							data-formatter="createTime" data-align="center">注册时间</th>
						<th data-align="center" data-column-id="memberLevelName">会员等级</th>
						
						<th data-align="center" data-formatter="operation"
							data-sortable="false">操作</th>
					</tr>
			</table>
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js"
	defer async="true"
	data-main="<%=request.getContextPath()%>/modules/member/memberquery/memberBasicInfoIndex"></script>



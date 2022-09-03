<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<html>
<head>
<title>账号管理</title>
<meta charset="UTF-8">

</head>
<body>
	<div class="panel panel-default panel-search">
		<div class="panel-heading-blue">
			<div class="panel-title-blue">账号管理</div>
			<button class="btn btn-default btn-xs btn-wide pull-right"
				id="resetBtn">重置</button>
			<button class="btn btn-primary btn-xs btn-wide pull-right"
				id="queryBtn">查询</button>
		</div>
		<form id="userForm" name="userForm">
			<div class="panel-body form-horizontal search-body">
				<div class="col-xs-12-sm">
					<div class="col-xs-4">
						<div class="form-group form-group-sm">
							<label class="col-xs-5-sm control-label">所属角色：</label>

							<div class="col-xs-7-sm">
								<select id="userRole" name="userRole" class="selectpicker"
									title="请选择" data-style="btn-sm btn-default">
									<option></option>
									<c:forEach items="${roleList}"
										var="roleList">
										<option value="${roleList.id}">${roleList.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-xs-4">
						<div class="form-group form-group-sm">
							<label class="col-xs-5-sm control-label">用户名：</label>

							<div class="col-xs-7-sm">
								<input type="text" maxlength="64" value="" name="loginName"
									id="loginName" class="form-control">
							</div>
						</div>
					</div>
					<!-- 删除身份证号查询 by liuhuan at 20170509 -->
					<div class="col-xs-4">
						<div class="form-group form-group-sm">
							<label class="col-xs-5-sm control-label">真实姓名：</label>
							<div class="col-xs-7-sm">
								<input type="text" maxlength="64" value="" name="name" id="name"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">账号状态：</label>

						<div class="col-xs-7-sm">
							<select id="loginFlag" name="loginFlag" class="selectpicker" title="请选择" data-style="btn-sm btn-default">
								<c:forEach items="${fns:getDictList('DICT_START_STATUS')}" var="DICT_START_STATUS">
									<option value="${DICT_START_STATUS.value}">${DICT_START_STATUS.label}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
					<!-- <div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">手机号码：</label>

						<div class="col-xs-7-sm">
							<input type="text" maxlength="12" value="" name="mobilePhone"
							 onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" 
								id="mobilePhone" class="form-control">
						</div>
					</div>
				</div> -->
					<!-- <div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label"></label>
						<div class="col-xs-7-sm">
							
						</div>
					</div>
				</div>	 -->
				</div>
			</div>
		</form>
		<div class="panel-heading-gray text-right">
			<button class="btn btn-primary btn-xs" id="createBtn">添加账号</button>
			<button class="btn btn-primary btn-xs" id="openBtn">启用</button>
			<button class="btn btn-primary btn-xs" id="stopBtn">停用</button>
		</div>
		<div class=" panel-body">
			<table id="userGridList" class="table table-hover">
				<thead class="bg-default" id="thead">
					<tr>
						<th data-header-Align="left" data-align="left"
							data-column-id="id" data-identifier="true" data-visible="false"
							data-visibleinselection="false">序号</th>
						<!-- <th data-width="140px" data-align="center"
							data-column-id="userType" data-sortable="false"
							data-formatter="userType">账号类型</th> -->
						<th data-align="center"
							data-column-id="userRoleName" data-sortable="false">所属角色</th>
						<th data-align="center"
							data-column-id="loginName" data-sortable="false">用户名</th>
						<th data-align="center" data-column-id="name"
							data-sortable="false">真实姓名</th>
						<!-- <th data-width="180px" data-align="center"
							data-column-id="orgName" data-sortable="false">所属机构</th> -->
						<!-- 删除身份证号显示 by liuhuan at 20170509 
					<th data-width="180px" data-align="center"
						data-column-id="certificateNo" 
						data-sortable="false">身份证号</th> 
					-->
						<!-- <th data-width="100px" data-align="center"
							data-column-id="birthday" data-sortable="false">出生年月</th> -->
						<!-- <th data-width="100px" data-align="center"
							data-column-id="educateGrade" data-formatter="educateGrade"
							data-sortable="false">文化程度</th> 
						<th data-width="100px" data-align="center"
							data-column-id="positionTitle" data-formatter="positionTitle"
							data-sortable="false">职称</th>-->
						<!-- <th data-width="100px" data-align="left"
							data-column-id="department" data-formatter="department"
							data-sortable="false">部门/科室</th> -->
						<th data-align="left"
							data-column-id="contactWay" data-sortable="false">联系方式</th>
						<th data-align="left" data-column-id="email"
							data-sortable="false">邮箱</th>
						<th data-align="left" data-column-id="unitName"
							data-sortable="false">单位名称</th>
						<th data-align="left" data-column-id="address"
							data-sortable="false">单位地址</th>
						<th data-align="center"
							data-column-id="loginFlag" data-formatter="loginFlag"
							data-sortable="false">账号状态</th>
						<th data-align="center"
							data-formatter="operation" data-sortable="false">操作</th>
					</tr>
				</thead>
				<tbody id="tbody">

				</tbody>
			</table>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/plugins/require/require.js"
		defer async="true"
		data-main="<%=request.getContextPath()%>/modules/usermanage/userQuery"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">
	<div class="panel-heading-blue">
		<div class="panel-title-blue">角色管理</div>
		<button class="btn btn-primary btn-xs btn-wide pull-right" id="btnAdd">添加角色</button>
		<button class="btn btn-primary btn-xs btn-wide pull-right"
			id="btnReset" style="display: none;">重置</button>
		<button class="btn btn-primary btn-xs btn-wide pull-right"
			id="btnQuery" style="display: none;">查询</button>
	</div>

	<div class=" panel-body">
		<table id="roleGrid" class="table table-hover">
			<thead class="bg-default" id="thead">
				<tr>
					<th data-header-Align="left" data-align="left"
						data-column-id="id" data-identifier="true" data-visible="false"
						data-visibleinselection="false">序号</th>

					<th data-align="center" data-column-id="name"
						data-sortable="false">角色名称</th>

					<th data-align="center" data-column-id="enname"
						data-sortable="false" data-formatter="enname">英文名称</th>

					<!-- <th data-width="" data-align="center" data-column-id="roleLevel"
						data-sortable="false" data-formatter="roleLevel">创建时间</th>

					<th data-width="" data-align="left" data-column-id="dataScope"
						data-sortable="false" data-formatter="dataScope">数据访问权限</th> -->

					<th data-align="center"
						data-formatter="operation" data-sortable="false">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>


<script src="<%=request.getContextPath()%>/plugins/require/require.js"
	defer async="true"
	data-main="<%=request.getContextPath()%>/modules/sys/role/roleList"></script>





<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<%-- <%@ include file="/WEB-INF/views/include/common.jsp"%> --%>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> --%>


<div class="panel panel-default panel-search">
	<div class="panel-heading-blue">
		<div class="panel-title-blue">区划管理</div>
		<button class="btn btn-primary btn-xs btn-wide pull-right" id="btnReset">重置</button>
		<button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery">查询</button>

	</div>
	<form id="searchForm" name="searchForm">
		<div class="panel-body form-horizontal search-body">

			<div class="col-xs-12-sm">
				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">区划名称：</label>

						<div class="col-xs-7-sm">
							<input type="text" maxlength="64"  name="name" id="name" autocomplete="off"
								class="form-control">
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">区划代码：</label>

						<div class="col-xs-7-sm">
							<input type="text" maxlength="64" value="" name="codeFuzzy"
								id="codeFuzzy" class="form-control">
						</div>
					</div>
				</div>

				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">区划级别：</label>

						<div class="col-xs-7-sm">
							<select class="selectpicker " name="level" id="level" title="请选择"
								data-style="btn-sm btn-default">
								<c:forEach items="${fns:getDictList('DICT_ORG_LEVEL')}"
									var="DICT_ORG_LEVEL">
									<option value="${DICT_ORG_LEVEL.value}">${DICT_ORG_LEVEL.label}</option>
								</c:forEach>
							</select>
						</div>



					</div>
				</div>

			</div>

			<div class="col-xs-12-sm">

				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">区划标识（国家试点县）：</label>

						<div class="col-xs-7-sm">
							<select class="selectpicker " name="zoneTag" id="zoneTag"
								title="请选择" data-style="btn-sm btn-default">
								<c:forEach items="${fns:getDictList('DICT_ZONE_QUBS')}"
									var="DICT_ZONE_QUBS">
									<option value="${DICT_ZONE_QUBS.value}">${DICT_ZONE_QUBS.label}</option>
								</c:forEach>
							</select>
						</div>


					</div>
				</div>

				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">区划标识（贫困县）：</label>

						<div class="col-xs-7-sm">
							<select class="selectpicker " name="isPoorCounty"
								id="isPoorCounty" title="请选择" data-style="btn-sm btn-default">
								<c:forEach items="${fns:getDictList('DICT_IDENTITY_FLAG')}"
									var="DICT_IDENTITY_FLAG">
									<option value="${DICT_IDENTITY_FLAG.value}">${DICT_IDENTITY_FLAG.label}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>

				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">区划标识（全口径覆盖区）：</label>



						<div class="col-xs-7-sm">
							<select class="selectpicker " name="isAllCover" id="isAllCover"
								title="请选择" data-style="btn-sm btn-default">
								<c:forEach items="${fns:getDictList('DICT_IDENTITY_FLAG')}"
									var="DICT_IDENTITY_FLAG">
									<option value="${DICT_IDENTITY_FLAG.value}">${DICT_IDENTITY_FLAG.label}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>


			</div>


			<div class="col-xs-12-sm">

				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">区划状态：</label>


						<div class="col-xs-7-sm">
							<select class="selectpicker " name="isEnable" id="isEnable"
								title="请选择" data-style="btn-sm btn-default">
								<c:forEach items="${fns:getDictList('DICT_START_STATUS')}"
									var="DICT_START_STATUS">
									<c:if test="${DICT_START_STATUS.value!='2'}">
									<option value="${DICT_START_STATUS.value}">${DICT_START_STATUS.label}</option>
									</c:if>
								</c:forEach>
								
						
							</select>
						</div>
					</div>
				</div>

				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">启用开始时间：</label>

						<div class="col-xs-7-sm">
							<div class="input-group date form_datetime"
								data-date-format="dd MM yyyy">
								<input class="form-control" size="10" type="text" value=""
									name="startEnableDate" id="startEnableDate"> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
						</div>



					</div>
				</div>
				
				
				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">启用终止时间：</label>

						<div class="col-xs-7-sm">
							<div class="input-group date form_datetime"
								data-date-format="dd MM yyyy">
								<input class="form-control" size="10" type="text" value=""
									name="endEnableDate" id="endEnableDate"> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
						</div>



					</div>
				</div>
				
				
				

		
			</div>



			<div class="col-xs-12-sm">
			
			
			    <div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">省：</label>

						<div class="col-xs-7-sm">
							<select class="selectpicker selectArea" name="provinceCode"
								id="provinceCode" data-area-id="province" title="请选择"
								data-style="btn-sm btn-default" data-live-search="true"
								data-size="15">
							</select>
						</div>
					</div>
				</div>
				
				
				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">市：</label>

						<div class="col-xs-7-sm">
							<select class="selectpicker selectArea" name="cityCode"
								id="cityCode" data-area-id="city" title="请选择"
								data-style="btn-sm btn-default">
							</select>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">区/县：</label>

						<div class="col-xs-7-sm">
							<select class="selectpicker selectArea" name="countyCode"
								id="countyCode" data-area-id="county" title="请选择"
								data-style="btn-sm btn-default">
							</select>
						</div>
					</div>
				</div>
				
			
				
				
			</div>
			
			
			
			<div class="col-xs-12-sm">
			
			
				
				<div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">乡/街道：</label>

						<div class="col-xs-7-sm">
							<select class="selectpicker selectArea" name="townCode"
								id="townCode" data-area-id="town" title="请选择"
								data-style="btn-sm btn-default">
							</select>
						</div>
					</div>
				</div>
				
				
			</div>
			





		</div>

	</form>
	<%--<form id="exportForm" name="exportForm">--%>
		<%--<div class="col-xs-12-sm">--%>
				<%--<div class="form-group form-group-sm">--%>
					<%--<label class="col-xs-4 control-label">导入：</label>--%>
					<%--<div class="col-xs-3-sm">--%>
						<%--<input type="file" class="file-input" id="file1" name="files" value="" tabindex="-1" data-maxfiles="3" data-filesize="2097152" data-filetype="txt|doc">--%>
						<%--<div class="input-group">--%>
							<%--<input type="text" class="form-control file-input-label" placeholder="请选择文件" readOnly--%>
								   <%--aria-describedby="sizing-addon3" autocomplete="off">--%>
							<%--<span class="input-group-btn" tabindex="0">--%>
                         	<%--<label for="file1" class="btn btn-default form-control file-input-label-for">--%>
                 				<%--<span class="glyphicon glyphicon-folder-open"></span>--%>
                 			<%--</label>--%>
                 		<%--</span>--%>
						<%--</div>--%>
					<%--</div>--%>
					<%--<div class="col-xs-1-sm">--%>
						<%--<a class="btn btn-link file-append"><i class="fa fa-plus-circle fa-lg" aria-hidden="true"></i></a>--%>
					<%--</div>--%>
				<%--</div>--%>
		<%--</div>--%>
	<%--</form>

	<form id="importForm" name="importForm" enctype="multipart/form-data">
		<div class="form-group" data-msg-direction="r">
			<label class="col-xs-4 control-label">导入：</label>
			<div class="col-xs-3-sm">
				<input type="file" class="file-input" id="file1" name="files" value="" tabindex="-1" data-maxfiles="3" data-filesize="20971520" data-filetype="xlsx">
				<div class="input-group">
					<input type="text" class="form-control file-input-label" placeholder="请选择文件" readOnly
						   aria-describedby="sizing-addon3" autocomplete="off">
					<span class="input-group-btn" tabindex="0">
						<label for="file1" class="btn btn-default form-control file-input-label-for">
							<span class="glyphicon glyphicon-folder-open"></span>
						</label>
					</span>

					<input type="hidden" name="importType" class="form-control" value="dict_zone">
				</div>
			</div>
		</div>
	</form>
	--%>

	<div class="panel-heading-gray text-right">

		<!-- <button class="btn btn-primary btn-xs" id="btnImportExcel" >导入</button> -->
		<button class="btn btn-primary btn-xs" id="btnStartAll" style="display: none;">机构全部启用</button>
<%-- 		<shiro:hasPermission name="dictzone:exportBtn"> --%>
<!-- 			<button class="btn btn-primary btn-xs" id="btnExportExcel">导出</button> -->
<%-- 		</shiro:hasPermission> --%>
<%-- 		<shiro:hasPermission name="dictzone:exportPageBtn"> --%>
<!-- 			<button class="btn btn-primary btn-xs" id="btnExportExcelPage">导出本页</button> -->
<%-- 		</shiro:hasPermission> --%>
	    <button class="btn btn-primary btn-xs" id="btnStop" >停用</button>
	     
<%-- 	    <shiro:hasPermission name="dictzone:btnDelete"> --%>
		<button class="btn btn-primary btn-xs" id="btnDelete">删除</button>
<%-- 		</shiro:hasPermission> --%>
		
<%-- 		<shiro:hasPermission name="dictzone:btnEnable"> --%>
		<button class="btn btn-primary btn-xs" id="btnEnable">启用</button>
<%-- 		</shiro:hasPermission> --%>
		<!-- by liuhuan at 20170509 start -->
		
<%-- 		<shiro:hasPermission name="dictzone:btnEdit"> --%>
			<button class="btn btn-primary btn-xs" id="btnEdit">编辑</button>
<%-- 		</shiro:hasPermission> --%>
		
		<button class="btn btn-primary btn-xs" id="btnAdd">添加下级区划</button>
		<!-- by liuhuan at 20170509 end -->
	</div>
	<div class=" panel-body">
		<table id="userGrid" class="table table-hover">
			<thead class="bg-default" id="thead">

				<tr>
					<th data-width="58px" data-header-Align="left" data-align="left"
						data-column-id="id" data-identifier="true" data-visible="false"
						data-visibleinselection="false">序号</th>
					<th data-width="140px" data-align="left"
						data-column-id="fullName" data-sortable="false"
						data-visibletitle="true">区划名称</th>
					<th data-width="100px" data-align="center" data-column-id="code"
						data-visibletitle="true" data-sortable="false">区划代码</th>

					<th data-width="100px" data-align="left"
						data-column-id="province" data-visibletitle="true"
						data-sortable="false">省</th>

					<th data-width="100px" data-align="left" data-column-id="city"
						data-visibletitle="true" data-sortable="false">市</th>

					<th data-width="100px" data-align="left" data-column-id="county"
						data-visibletitle="true" data-sortable="false">县</th>

					<th data-width="100px" data-align="left" data-column-id="town"
						data-visibletitle="true" data-sortable="false">乡</th>

					<th data-width="100px" data-align="center" data-column-id="level"
						data-visibletitle="true" data-visibleinselection="false"
						data-formatter="level" data-sortable="false">区划级别</th>

					<th data-width="200px" data-align="left"
						data-column-id="parentFullName" data-visibletitle="true"
						data-sortable="false">父级区划</th>


					<th data-width="200px" data-align="center" data-column-id="zoneTag"
						data-formatter="zoneTag" data-visibletitle="true"
						data-sortable="false">区划标识（国家试点县）</th>


					<th data-width="200px" data-align="center"
						data-formatter="isPoorCounty" data-column-id="isPoorCounty"
						data-visibletitle="true" data-sortable="false">区划标识（贫困县）</th>


					<th data-width="200px" data-align="center"
						data-formatter="isAllCover" data-column-id="isAllCover"
						data-visibletitle="true" data-sortable="false">区划标识（全口径覆盖地区）</th>

					<th data-width="100px" data-align="center"
						data-formatter="isEnable" data-column-id="isEnable"
						data-visibletitle="true" data-sortable="false">区划状态</th>

					<th data-width="150px" data-align="center"
						data-formatter="enableDate" data-column-id="enableDate"
						data-visibletitle="true" data-sortable="false">启用时间</th>



					<th data-width="150px" data-align="center"
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
	data-main="<%=request.getContextPath()%>/modules/dictzone/dictZoneList"></script>





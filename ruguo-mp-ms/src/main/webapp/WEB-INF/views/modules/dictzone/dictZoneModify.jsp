<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>


<input type="hidden" value="${result.isEnable}" id="isEnables" />

<div class="row">
	<div class="col-xs-12">

		<form id="userForm" name="userForm">
			<div class="panel-body form-horizontal">

				<div class="col-xs-12-sm">
					<div class="col-xs-6">
						<div class="form-group form-group-sm">
							<label class="col-xs-5-sm control-label">父级区划：</label>

							<div class="col-xs-7-sm">
								<input type="text" maxlength="64" name="parentName" disabled
									id="parentName" class="form-control"
									value="${result.parentFullName}">
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group form-group-sm" data-msg-direction="d">
							<label class="col-xs-5 control-label required">名称：</label>

							<div class="col-xs-7-sm">
								<input type="text" maxlength="64" name="name" id="name"
									class="form-control" value="${result.name}">
							</div>
						</div>
					</div>


				</div>


				<div class="col-xs-12-sm">
					<div class="col-xs-6">
						<div class="form-group form-group-sm" data-msg-direction="d">
							<label class="col-xs-5 control-label required">区划代码：</label>

							<div class="col-xs-7-sm">
								<input type="text" maxlength="64" name="code" id="code"
									class="form-control" value="${result.code}">
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group form-group-sm">
							<label class="col-xs-5-sm control-label">区划级别：</label>

							<div class="col-xs-7-sm">
								<select class="selectpicker " name="level" id="levels"
									title="请选择" data-style="btn-sm btn-default">
									<c:forEach items="${fns:getDictList('DICT_ORG_LEVEL')}"
										var="DICT_ORG_LEVEL">

										<option value="${DICT_ORG_LEVEL.value}"
											${DICT_ORG_LEVEL.value == result.level ?'selected':''}>${DICT_ORG_LEVEL.label}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>


				</div>



				<div class="col-xs-12-sm">
					<div class="col-xs-6">
						<div class="form-group form-group-sm">
							<label class="col-xs-5-sm control-label">区划标识 （国家试点县）：</label>

							<div class="col-xs-7-sm">
								<select class="selectpicker " name="zoneTag" id="zoneTags"
									title="请选择" data-style="btn-sm btn-default">
									<c:forEach items="${fns:getDictList('DICT_ZONE_QUBS')}"
										var="DICT_ZONE_QUBS">
										<option value="${DICT_ZONE_QUBS.value}"
											${DICT_ZONE_QUBS.value == result.zoneTag ?'selected':''}>${DICT_ZONE_QUBS.label}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group form-group-sm">
							<label class="col-xs-5-sm control-label">区划标识（贫困县）：</label>

							<div class="col-xs-7-sm">
								<select class="selectpicker " name="isPoorCounty"
									id="isPoorCountys" title="请选择" data-style="btn-sm btn-default">
									<c:forEach items="${fns:getDictList('DICT_IDENTITY_FLAG')}"
										var="DICT_IDENTITY_FLAG">
										<option value="${DICT_IDENTITY_FLAG.value}"
											${DICT_IDENTITY_FLAG.value == result.isPoorCounty  ?'selected':''}>${DICT_IDENTITY_FLAG.label}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>


				</div>



				<div class="col-xs-12-sm">
					<div class="col-xs-6">
						<div class="form-group form-group-sm">
							<label class="col-xs-5-sm control-label">区划标识（全口径覆盖地区）：</label>

							<div class="col-xs-7-sm">
								<select class="selectpicker " name="isAllCover" id="isAllCovers"
									title="请选择" data-style="btn-sm btn-default">
									<c:forEach items="${fns:getDictList('DICT_IDENTITY_FLAG')}"
										var="DICT_IDENTITY_FLAG">
										<option value="${DICT_IDENTITY_FLAG.value}"
											${DICT_IDENTITY_FLAG.value == result.isPoorCounty  ?'selected':''}>${DICT_IDENTITY_FLAG.label}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>



					<div class="col-xs-6">
						<div class="form-group form-group-sm">

							<div class="col-xs-7-sm">
								<input type="hidden" maxlength="64" name="parentId"
									id="parentId" class="form-control" value="${result.id}">
							</div>
						</div>
					</div>


				</div>



			</div>
		</form>
		<hr>
		<div class="col-xs-12 text-center">

		    <button class="btn btn-primary btn-sm btn-bottom btn-wide" id="btnSave">保&nbsp;&nbsp;存</button>
		    <button class="btn btn-primary btn-sm btn-bottom btn-wide" id="btnCancle">关&nbsp;&nbsp;闭</button>

		</div>

	</div>
</div>


<script
	src="<%=request.getContextPath()%>/modules/dictzone/dictZoneModify.js"></script>


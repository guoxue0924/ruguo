<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="row">
	<div class="col-xs-11">
		<form id="roleForm" name="roleForm">
			<div class="panel-body form-horizontal">
				<div class="col-xs-12-sm">
					<div class="col-xs-6">
						<div class="form-group form-group-sm" data-msg-direction="d">
							<label class="col-xs-6 control-label required">角色名称：</label>
							<div class="col-xs-6-sm">
								<input type="hidden" maxlength="100" name="id"
									   id="id" class="form-control" value="${result.id}">
								<input type="hidden" maxlength="100" name="oldName"
									   id="oldName" class="form-control" value="${result.id}">
								<input type="text" maxlength="100" name="name" autocomplete="off"
									   id="name" class="form-control" value="${result.name}">
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group form-group-sm" data-msg-direction="d">
							<label class="col-xs-6 control-label required" >英文名称：</label>
							<div class="col-xs-6-sm">
								<input type="hidden" maxlength="100" name="oldEnname"
									   id="oldEnname" class="form-control" value="${result.id}">
								<input type="text" maxlength="32" name="enname" id="enname" autocomplete="off"
									   class="form-control" value="${result.enname}">
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-xs-12-sm">
					<div class="col-xs-12">
						<div class="form-group form-group-sm" data-msg-direction="d">
							<label class="col-xs-3 control-label">角色授权： </label>
							<div class="col-xs-9-sm">
								<div id="menuTree" class="ztree" style="margin-top:3px;float:left;"></div>
								<input type="hidden" name="menuIds"
									   id="menuIds" class="form-control" >
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-12-sm">
					<div class="col-xs-6">
						<div class="form-group form-group-sm" data-msg-direction="d">
							<label class="col-xs-6 control-label">备注：</label>
							<div class="col-xs-6-sm">
								<textarea id="remark" name="remark" class="form-control" onpropertychange="if(value.length>500) value=value.substr(0,500)" rows="3"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="col-xs-12">
		<hr>
		</div>
		<div class="col-xs-12 text-center">
			<button class="btn btn-primary btn-sm btn-bottom btn-wide" id="btnSave">保&nbsp;&nbsp;存</button>
			<button class="btn btn-default btn-sm btn-bottom btn-wide" id="btnCancle">关&nbsp;&nbsp;闭</button>
		</div>
</div>


<script src="<%=request.getContextPath()%>/modules/sys/role/roleForm.js"></script>


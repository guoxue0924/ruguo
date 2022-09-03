<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<form id="myServiceInfoForm" name="myServiceInfoForm">
			<div class="panel-body form-horizontal">
			<input type="hidden" id="code" name="code" value="${myServiceInfo.serviceCode }">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 用户名： </label>
						<div class="col-xs-5-sm">
							<span class="control-label-text">
								${myServiceInfo.memberName} </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 姓名： </label>
						<div class="col-xs-5-sm">
							<span class="control-label-text">
								${myServiceInfo.relaPerName} </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 性别： </label>
						<div class="col-xs-5-sm">
							<span class="control-label-text">
								${myServiceInfo.genderName} </span>
						</div>
					</div>
				</div>
				<!-- <div class="form-group">
				</div> -->
				<div class="form-group">
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 手机号： </label>
						<div class="col-xs-5-sm">
							<span class="control-label-text">
								${myServiceInfo.relaPerMobilePhone}
							</span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 服务名称： </label>
						<div class="col-xs-5-sm">
							<span class="control-label-text">
								${myServiceInfo.goodsName} </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 购买服务日期： </label>
						<div class="col-xs-5-sm">
							<span class="control-label-text">
								${myServiceInfo.serviceStartTime} </span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 服务截止时间： </label>
						<div class="col-xs-5-sm">
							<span class="control-label-text">
								${myServiceInfo.serviceEndTime} </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 周期： </label>
						<div class="col-xs-5-sm">
							<span class="control-label-text">${myServiceInfo.serviceCycle}
								 </span>
						</div>
					</div>
					
				</div>

				
				<div class="form-group">
					<div class="col-xs-4">
						<label class="col-xs-5 control-label"> 服务介绍： </label>
						<div class="col-xs-6-sm">
							<span class="control-label-text">
								${myServiceInfo.goodsTitle} </span>
						</div>
					</div>
				</div>
				
				<!-- <div class="panel-heading-blue">
			        <div class="panel-title-blue">
			           健康档案
			        </div>
			    </div>
			    <a style="margin-left: 50px;">  点击查看全部档案信息︾</a>
			    <div class="panel-heading-blue">
			        <div class="panel-title-blue">
			          日常行为统计
			        </div>
			    </div>
			    <img  src="../../images/t.jpg"> -->
			    <div class="panel-heading-blue">
			        <div class="panel-title-blue">
			          日常监测
			        </div>
			    </div>
			    <img  src="../../images/p.jpg">
			      <div class="panel-heading-blue">
			        <div class="panel-title-blue">
			          服务报告
			        </div>
			    </div>
			    <img  src="../../images/q.jpg">
			</div>
		</form>
		<hr>
		<div class="col-xs-12 text-center">
			<button class="btn btn-default" id="btnClose">关闭</button>
		</div>
	</div>
</div>
<script	src="<%=request.getContextPath()%>/modules/service/service/myServiceInfoDetail.js"></script>

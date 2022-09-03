<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="row">
	<div class="col-xs-12">
	<input type="hidden" id="ctxPath"  value="${ctx}"> 
		<form id="memberBasicInfoForm2" name="memberBasicInfoForm2">
		
			<div class="panel-body form-horizontal">
			<input type="hidden" id="code" name="code" value="${memberBasicItemResult.code}"/>
			<input type="hidden" id="key" name="key" value=""/>
				<div class="form-group">
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 姓名： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.relaPerName} </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 性别： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.relaPerGenderName} </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 出生日期： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.relaPerBirthday} </span>
						</div>
					</div>
				</div>
				<!-- <div class="form-group">
				</div> -->
			    <div class="form-group">
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 证件类型： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.relaPerCertificateName}
							</span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 证件号码： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.relaPerCertificateNo} </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 手机号： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.relaPerMobilePhone} </span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 会员等级： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.memberLevelName} </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 注册时间： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text"><fmt:formatDate value="${memberBasicItemResult.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								 </span>
						</div>
					</div>
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 消费金额： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.monetary}元 </span>
						</div>
					</div>
				</div>
				<%-- <div class="form-group">
					<div class="col-xs-6">
						<label class="col-xs-4 control-label"> 职业： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.memberBasicInfo.occupationName} </span>
						</div>
					</div>
					<div class="col-xs-6">
						<label class="col-xs-4 control-label"> 所属机构： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.orgName} </span>
						</div>
					</div>
				</div> --%>

				
				<div class="form-group">
					<div class="col-xs-4">
						<label class="col-xs-4 control-label"> 备注： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.remark} </span>
						</div>
					</div>
				</div>
				<%-- <div class="form-group">
					<div class="col-xs-6">
						<label class="col-xs-4 control-label"> 患有慢性病： </label>
						<div class="col-xs-8-sm">
							<span class="control-label-text">
								${memberBasicItemResult.memberBasicInfo.chronicDiseaseContent} </span>
						</div>
					</div>
				</div> --%>
				<div class="panel-heading-blue">
			        <div class="panel-title-blue">
			            收货信息
			        </div>
			    </div>
			     <div class=" panel-body">
			     	<table id="memberAddressGrid" class="table table-hover">
			            <thead class="bg-default" id="thead">
			            <tr>
			                <th data-header-align="center" data-align="center" data-column-id="memberName" data-identifier="true">用户名</th>
			                <th data-header-align="center" data-align="center" data-column-id="personName">收货人</th>
			                <th data-header-align="center" data-align="center" data-column-id="mobilePhone" data-formatter="mobilePhone">收货电话</th>
			                <th data-header-align="center" data-align="center" data-column-id="address" data-formatter="address">收货地址</th>
			            </tr>
			            </thead>
			            <tbody id="tbody"></tbody>
			        </table>
			     </div>			
			   	<div class="panel-heading-blue">
			        <div class="panel-title-blue">
			           会员关系人信息
			        </div>
			    </div>
			     <div class=" panel-body">
			     	<table id="relationshipInfo" class="table table-hover">
			            <thead class="bg-default" id="thead2">
			            <tr>
			                <th data-header-align="center" data-align="center" data-column-id="relaPerName" data-identifier="true">关系人姓名</th>
			                <th data-header-align="center" data-align="center" data-column-id="relaPerGenderName">关系人性别</th>
			                <th data-header-align="center" data-align="center" data-column-id="relaTypeName" data-formatter="relaTypeName">关系类型</th>
			                <th data-header-align="center" data-align="center" data-column-id="relaPerBirthday" data-formatter="relaPerBirthday">关系人出生年月日</th>
			                <th data-header-align="center" data-align="center" data-column-id="relaPerMobilePhone" data-formatter="relaPerMobilePhone">关系人手机号</th>
			                
			            </tr>
			            </thead>
			            <tbody id="tbody"></tbody>
			        </table>
			     </div>	
			</div>
		</form>
		<hr>
		<div class="col-xs-12 text-center">
			<button class="btn btn-default" id="btnClose">关闭</button>
		</div>
	</div>
</div>
<script
	src="<%=request.getContextPath()%>/modules/member/memberquery/memberBasicInfoDetail.js"></script>

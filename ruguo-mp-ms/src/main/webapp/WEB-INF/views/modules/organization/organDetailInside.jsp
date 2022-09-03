<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">

        <form id="organForm" name="organForm">
            <input type="hidden" name="id" value="${result.id}">
            <div class="panel-body form-horizontal selectid">
            	
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       机构名称：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="sname" name="name" value="${result.name}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        所属机构：
                    </label>

                    <div class="col-xs-4-sm">
                        <select class="selectpicker form-control" id="editOrgType" name="orgType" data-actionsBox="true"
                                title="请选择"  disabled="true">
                            <c:forEach items="${fns:getDictList('DICT_ORG_TYPE_1')}" var="DICT_ORG_TYPE_1">
                                <option value="${DICT_ORG_TYPE_1.value}"
                                <c:if test="${result.orgType eq DICT_ORG_TYPE_1.value}">selected</c:if>
                                >${DICT_ORG_TYPE_1.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       机构负责人：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="scontactPerson" name="contactPerson" value="${result.contactPerson}" readonly="readonly">
                    </div>
                </div>
        		<div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       手机号：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="scontactPhone" name="contactPhone" value="${result.contactPhone}" readonly="readonly">
                    </div>
                </div>
                 <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        所在地区：
                    </label>

                   <div class="col-xs-4-sm">
                    <input type="text" class="form-control" id="saddress" name="address" value="${result.province} ${result.city} ${result.county} ${result.town}" readonly="readonly">
                            <%-- <select class="selectpicker selectArea2 codechange" name="provinceCode" id="editprovinceCode" data-area-id="province"
                                    title="请选择" data-style="btn-sm btn-default" data-live-search="true" data-size="15" 
                                    data-area-value="${result.provinceCode}">
                            </select> --%>
                     </div>
                </div>
                 
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       详细地址：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="saddress" name="address" value="${result.address}" readonly="readonly">
                    </div>
                </div>
                
            </div>
        </form>
        <hr>
        <div class="col-xs-12 text-center">
        	<!-- <button type="button" data-formatter="commands"
                    id="btnSave" class="btn btn-primary">
                保存
            </button> -->
           <!--  <button type="button" data-formatter="commands"
                    id="btnReset" class="btn btn-primary">
                重置
            </button> -->
            <button class="btn btn-default" id="btnCancle">
                关闭
            </button>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/organization/organDetailInside.js"></script>
        
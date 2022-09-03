<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">

        <form id="organForm" name="organForm">
            <input type="hidden" name="id" value="${result.id}">
      <%--       <input type="hidden" name="zoneCode" id="zoneCodehid" value="${result.zoneCode}">
            <input type="hidden" id="isEnable_edit" value="${result.isEnable}"> --%>

            <div class="panel-body form-horizontal selectid">
            	<div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       机构编码：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="scode" name="code" value="${result.code}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       机构名称：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="sname" name="name" value="${result.name}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        机构类型：
                    </label>

                    <div class="col-xs-4-sm">
                        <select class="selectpicker form-control" id="editOrgType" name="orgType" data-actionsBox="true"
                                title="请选择">
                            <c:forEach items="${fns:getDictList('DICT_ORG_TYPE')}" var="DICT_ORG_TYPE">
                                <option value="${DICT_ORG_TYPE.value}"
                                <c:if test="${result.orgType eq DICT_ORG_TYPE.value}">selected</c:if>
                                >${DICT_ORG_TYPE.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
               
                 <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        省：
                    </label>

                   <div class="col-xs-4-sm">
                            <select class="selectpicker selectArea2 codechange" name="provinceCode" id="editprovinceCode" data-area-id="province"
                                    title="请选择" data-style="btn-sm btn-default" data-live-search="true" data-size="15" 
                                    data-area-value="${result.provinceCode}">
                            </select>
                     </div>
                </div>
                 <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                        市：
                    </label>

                     <div class="col-xs-4-sm">
                            <select class="selectpicker selectArea2 codechange" name="cityCode" id="editcityCode" data-area-id="city"
                                    title="请选择" data-style="btn-sm btn-default" data-area-value="${result.cityCode}">
                            </select>
                    </div>
                </div>
                 <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       县：
                    </label>

                    <div class="col-xs-4-sm">
                            <select class="selectpicker selectArea2 codechange" name="countyCode" id="editcountyCode" data-area-id="county"
                                    title="请选择" data-style="btn-sm btn-default" data-area-value="${result.countyCode}">
                            </select>
                    </div>
                </div>
                 <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                      乡：
                    </label>

                    <div class="col-xs-4-sm">
                            <select class="selectpicker selectArea2 codechange" name="townCode" id="edittownCode" data-area-id="town"
                                    title="请选择" data-style="btn-sm btn-default" data-area-value="${result.townCode}">
                            </select>
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       详细地址：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="saddress" name="address" value="${result.address}">
                    </div>
                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       联系人姓名：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="scontactPerson" name="contactPerson" value="${result.contactPerson}">
                    </div>
                </div>
        		<div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label required">
                       联系电话：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="scontactPhone" name="contactPhone" value="${result.contactPhone}">
                    </div>
                </div>
                
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                       邮编：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="sorgPostCode" name="orgPostCode" value="${result.orgPostCode}">
                    </div>
                </div>
                
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                       传真：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="sorgFax" name="orgFax" value="${result.orgFax}">
                    </div>
                </div>
                
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                       机构网址：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="">
                        <input type="text" class="form-control" id="sorgUrl" name="orgUrl" value="${result.orgUrl}">
                    </div>
                </div>
                
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4 control-label">
                        机构简介：
                    </label>

                    <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
                         data-content="最大可输入500个汉字" data-placement="right">
                                            <textarea name="remark" class="form-control"
                                                      onpropertychange="if(value.length>500) value=value.substr(0,500)"
                                                      rows="3">${result.remark}</textarea>
                    </div>
                </div>
                
            </div>
        </form>
        <hr>
        <div class="col-xs-12 text-center">
        	<button type="button" data-formatter="commands"
                    id="btnSave" class="btn btn-primary">
                保存
            </button>
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
<script src="<%=request.getContextPath()%>/modules/organization/organEdit.js"></script>
        
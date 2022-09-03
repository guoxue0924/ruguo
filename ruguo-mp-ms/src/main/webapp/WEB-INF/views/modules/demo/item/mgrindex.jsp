<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">

    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            机构管理
        </div>
    </div>
	
	<div class="row">
		<div class="col-xs-12">
	    <div class="col-xs-4" style="max-height: 450px; overflow-y: auto;min-width:259px">
	    	<div id="orgTree" class="ztree" style="margin-top:10px;float:left;"></div>
	    </div>
	    <div class="col-xs-8">
	    	<form id="userForm" name="userForm">
		        <div class="panel-body form-horizontal">
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
		                    姓名：
		                </label>
		
		                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
		                     data-content="请输入20位有效字符">
		                    <input type="text" class="form-control" name="name">
		                </div>
		            </div>
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
		                    性别：
		                </label>
		
		                <div class="col-xs-4-sm">
		                    <select class="selectpicker" id="gender" name="gender"
		                            data-title="请选择">
		                        <c:forEach items="${fns:getDictList('DICT_GENDER_ITEM')}"
									var="DICT_GENDER_ITEM">
									<option value="${DICT_GENDER_ITEM.value}">${DICT_GENDER_ITEM.label}</option>
								</c:forEach>
		                    </select>
		                </div>
		            </div>
		            
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
		                    邮箱：
		                </label>
		
		                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
		                     data-content="请输入20位有效字符">
		                    <input type="text" class="form-control" name="email" value="">
		                </div>
		            </div>
		            
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label">
		                    电话：
		                </label>
		
		                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
		                     data-content="请输入20位有效字符">
		                    <input type="text" class="form-control" name="phone">
		                </div>
		            </div>
		            
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
		                    手机：
		                </label>
		
		                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
		                     data-content="请输入20位有效字符">
		                    <input type="text" class="form-control" name="mobile">
		                </div>
		            </div>
		            
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
		                    出生日期：
		                </label>
		
		                <div class="col-xs-4-sm">
		                    <div class="input-group date form_datetime" >
		                        <input class="form-control" size="10" type="text" value=""
		                               name="birthday">
		                        <span class="input-group-addon"><span
		                                class="glyphicon glyphicon-calendar"></span></span>
		                    </div>
		                </div>
		            </div>
		            
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
		                    用户类型：
		                </label>
		
		                <div class="col-xs-4-sm">
		                    <select class="selectpicker form-control" id="userType" name="userType" data-actionsBox="true" 
		                            title="请选择">
		                        <c:forEach items="${fns:getDictListNoBlank('DICT_EDUCATE_GRADE')}" var="DICT_EDUCATE_GRADE">
									<option value="${DICT_EDUCATE_GRADE.value}" >${DICT_EDUCATE_GRADE.label}</option>
								</c:forEach>
		                    </select>
		                </div>
		            </div>
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
		                    是否可登录：
		                </label>
		
		                <div class="col-xs-4-sm">
		                    <c:forEach items="${fns:getDictListNoBlank('DICT_IDENTITY_FLAG')}" var="DICT_IDENTITY_FLAG">
									<label class="radio-inline">
				                        <input type="radio" name="loginFlag" value="${DICT_IDENTITY_FLAG.value}" > ${DICT_IDENTITY_FLAG.label}
				                    </label>
							</c:forEach>
		                </div>
		            </div>
		
		            <div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label">
		                    备注：
		                </label>
		
		                <div class="col-xs-4-sm" data-toggle="popover" data-trigger="focus"
		                     data-content="最大可输入500个汉字" data-placement="right">
		                                            <textarea name="remark" class="form-control"
		                                                      onpropertychange="if(value.length>500) value=value.substr(0,500)"
		                                                      rows="3"></textarea>
		                </div>
		            </div>
		        </div>
		    </form>
		    <div class="panel-footer text-center">
		<%--         <shiro:hasRole name="guojiaguanliyuan"> --%>
		        <button type="button" data-formatter="commands"
		                id="btnReset" class="btn btn-default">
		            重置
		        </button>
		<%--         </shiro:hasRole> --%>
		        
		<%--         <shiro:hasPermission name="DemoUser:save"> --%>
		        <button type="button" data-formatter="commands"
		                id="btnSavePost" class="btn btn-success">
		            保存(post)
		        </button>
		<%--         </shiro:hasPermission> --%>
		        
		        <button type="button" data-formatter="commands"
		                id="btnSavePostJson" class="btn btn-primary">
		            保存(Json)
		        </button>
		        <button type="button" data-formatter="commands"
		                id="btnSaveSubmit" class="btn btn-primary">
		            保存(submit)
		        </button>
		    </div>
	    </div>
	    </div>
    </div>
    


</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/item/mgrindex"></script>
		

		
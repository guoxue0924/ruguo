<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">

    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            新用户注册
        </div>
    </div>

    <form id="userForm" name="userForm">
    <div class="panel-body form-horizontal">
    	<div class="col-xs-10-sm">
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 col-xs-offset-2 control-label required">
	                    姓名左：
	                </label>
	                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top"
	                     data-content="请输入20位有效字符">
	                    <input type="text" class="form-control" name="name1">
	                </div>
	            </div>
    		</div>
    		
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 control-label required">
	                    姓名右：
	                </label>
	
	                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top"
	                     data-content="请输入20位有效字符">
	                    <input type="text" class="form-control" name="name2">
	                </div>
	            </div>
    		</div>
    	
    	
    	</div>
    	<div class="col-xs-10-sm">
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 col-xs-offset-2 control-label required">
	                   下拉框左：
	                </label>
	                <div class="col-xs-5-sm">
		                <select class="selectpicker" id="gender1" name="gender1" 
	                            title="请选择">
								<option value="1">1</option>
								<option value="1">1</option>
	                    </select>
	                </div>
	            </div>
    		</div>
    		
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 control-label required">
	                    下拉框右：
	                </label>
	
	                <div class="col-xs-5-sm">
		                <select class="selectpicker" id="gender2" name="gender2" 
	                            title="请选择">
								<option value="1">1</option>
								<option value="1">1</option>
	                    </select>
	                </div>
	            </div>
    		</div>
    	</div>
    	
    	<div class="col-xs-10-sm">
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 col-xs-offset-2 control-label required">
	                    日期选择左：
	                </label>
	                <div class="col-xs-5-sm">
	                    <div class="input-group date form_datetime"
	                         data-date-format="dd MM yyyy">
	                        <input class="form-control" size="10" type="text" value=""
	                               name="birthday1">
	                        <span class="input-group-addon"><span
	                                class="glyphicon glyphicon-calendar"></span></span>
	                    </div>
	                </div>
	            </div>
    		</div>
    		
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 control-label required">
	                    日期选择右：
	                </label>
	
	                <div class="col-xs-5-sm">
	                    <div class="input-group date form_datetime"
	                         data-date-format="dd MM yyyy">
	                        <input class="form-control" size="10" type="text" value=""
	                               name="birthday2">
	                        <span class="input-group-addon"><span
	                                class="glyphicon glyphicon-calendar"></span></span>
	                    </div>
	                </div>
	            </div>
    		</div>
    	
    	</div>
    	
    	<div class="col-xs-10-sm">
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 col-xs-offset-2 control-label required">
	                    单选左：
	                </label>
	                <div class="col-xs-4-sm">
	                    <c:forEach items="${fns:getDictListNoBlank('yes_no')}" var="yes_no">
								<label class="radio-inline">
			                        <input type="radio" name="loginFlag1" value="${yes_no.value}" > ${yes_no.label}
			                    </label>
						</c:forEach>
	                </div>
	            </div>
    		</div>
    		
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 control-label required">
	                    单选右：
	                </label>
	
	                <div class="col-xs-4-sm">
	                    <c:forEach items="${fns:getDictListNoBlank('yes_no')}" var="yes_no">
								<label class="radio-inline">
			                        <input type="radio" name="loginFlag2" value="${yes_no.value}" > ${yes_no.label}
			                    </label>
						</c:forEach>
	                </div>
	            </div>
    		</div>
    	
    	</div>
    	
    	<div class="col-xs-10-sm">
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 col-xs-offset-2 control-label required">
	                    多选左：
	                </label>
	                <div class="col-xs-5-sm">
						<label class="checkbox-inline">
	                        <input type="checkbox" name="hobby1" value="1" > AA
	                    </label>
	                    <label class="checkbox-inline">
	                        <input type="checkbox" name="hobby1" value="2" > BB
	                    </label>
	                    <label class="checkbox-inline">
	                        <input type="checkbox" name="hobby1" value="3" > CC
	                    </label>
	                </div>
	            </div>
    		</div>
    		
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 control-label required">
	                    多选右：
	                </label>
	
	                <div class="col-xs-5-sm">
						<label class="checkbox-inline">
	                        <input type="checkbox" name="hobby2" value="1" > AA
	                    </label>
	                    <label class="checkbox-inline">
	                        <input type="checkbox" name="hobby2" value="2" > BB
	                    </label>
	                    <label class="checkbox-inline">
	                        <input type="checkbox" name="hobby2" value="3" > CC
	                    </label>
	                </div>
	            </div>
    		</div>
    	
    	</div>
    	<div class="col-xs-10-sm">
    		<div class="col-xs-6">
    			<div class="form-group" data-msg-direction="d">
	                <label class="col-xs-5 col-xs-offset-2 control-label required">
	                    备注：
	                </label>
                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top"
                     data-content="最大可输入500个汉字" data-placement="right">
                                            <textarea name="remark" class="form-control"
                                                      onpropertychange="if(value.length>500) value=value.substr(0,500)"
                                                      rows="3"></textarea>
                </div>	                
	            </div>
    		</div>
    		
    	</div>
    	
        </div>
    </form>

    <div class="panel-footer text-center">
        <button type="button" data-formatter="commands"
                id="btnReset" class="btn btn-default">
            重置
        </button>
        <button type="button" data-formatter="commands"
                id="btnSave" class="btn btn-primary">
            保存
        </button>
    </div>

</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/layout/layout6"></script>
		

		
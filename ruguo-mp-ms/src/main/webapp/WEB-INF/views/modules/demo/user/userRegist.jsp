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
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    姓名：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                    <input type="text" class="form-control" name="name">
                </div>
            </div>
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    性别：
                </label>

                <div class="col-xs-3-sm">
                    <select class="selectpicker" id="gender" name="gender" disabled
                            data-title="请选择1">
                        <c:forEach items="${fns:getDictList('DICT_GENDER_ITEM')}"
							var="DICT_GENDER_ITEM">
							<option value="${DICT_GENDER_ITEM.value}">${DICT_GENDER_ITEM.label}</option>
						</c:forEach>
                    </select>
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    邮箱：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                    <input type="text" class="form-control" name="email" value="">
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">
                    电话：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                    <input type="text" class="form-control" name="phone">
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    手机：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                    <input type="text" class="form-control" name="mobile">
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    出生日期：
                </label>

                <div class="col-xs-3-sm">
                    <div class="input-group date form_datetime" >
                        <input class="form-control" size="10" type="text" value=""
                               name="birthday">
                        <span class="input-group-addon"><span
                                class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    用户类型：
                </label>

                <div class="col-xs-3-sm">
                    <select class="selectpicker form-control" id="userType" name="userType" data-actionsBox="true" 
                            title="请选择">
                        <c:forEach items="${fns:getDictListNoBlank('DICT_EDUCATE_GRADE')}" var="DICT_EDUCATE_GRADE">
							<option value="${DICT_EDUCATE_GRADE.value}" >${DICT_EDUCATE_GRADE.label}</option>
						</c:forEach>
                    </select>
                </div>
            </div>
<!-- <div class="form-inline"> -->
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    是否可登录：
                </label>

                <div class="col-xs-2-sm">
                     <!-- <div class="btn-group btn-group-standard" data-toggle="buttons">
                         <label class="btn btn-option">
                             <input type="radio" name="loginFlag" value="2"
                                    autocomplete="off">是
                             <span class="checked"></span>
                         </label>
                         <label class="btn btn-option ">
                             <input type="radio" name="loginFlag" value="0"
                                    autocomplete="off" >否
                             <span class="checked"></span>
                         </label>
                     </div> -->
                    <c:forEach items="${fns:getDictListNoBlank('DICT_IDENTITY_FLAG')}" var="DICT_IDENTITY_FLAG">
							<label class="radio-inline">
		                        <input type="radio" name="loginFlag" value="${DICT_IDENTITY_FLAG.value}" > ${DICT_IDENTITY_FLAG.label}
		                    </label>
					</c:forEach>
<!-- 					<label class="input-inline"> -->
<!-- 				    <input type="text" class="form-control" id="exampleInputPassword3"> -->
<!--                 </div> -->
                </div>

            </div>
<!--             </div> -->

<!--             <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    分区开户行全称（支行）：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位数字">
                    <div class="input-group">
                        <input type="text" name="fqkhhqc" class="form-control"
                               placeholder="点击按钮选择银行">
                        <span class="input-group-btn">
			                                            <button class="btn btn-default form-control" type="button"
                                                                id="xzqhbtn"><span
                                                                class="glyphicon glyphicon-search"></span></button>
			                                          </span>
                    </div>
                </div>
            </div> -->


<!--           <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">选择日期范围：</label>

                <div class="col-xs-4-sm">
                    <div class="form-inline">
                        <div class="input-group date form_datetime" data-date="2016-10-14"
                             data-date-format="dd MM yyyy">
                            <input class="form-control" size="10" type="text" value=""
                                   name="xzrq1">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        至
                        <div class="input-group date form_datetime" data-date="2016-10-14"
                             data-date-format="dd MM yyyy">
                            <input class="form-control" size="10" type="text" value=""
                                   name="xzrq2">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>

                        </div>
                    </div>
                </div>
            </div> -->


           

            <div class="form-group" data-msg-direction="d">
                <label class="col-xs-4 control-label text-right">兴趣爱好：</label>

                <div class="col-xs-4-sm">
                    <!-- <div class="btn-group btn-group-standard" data-toggle="buttons">
                        <label class="btn btn-option">
                            <input type="checkbox" name="hobby" autocomplete="off" value="1">
                            读书
                            <span class="checked"></span>
                        </label>
                        <label class="btn btn-option">
                            <input type="checkbox" name="hobby" autocomplete="off" value="2">
                            看报
                            <span class="checked"></span>
                        </label>
                        <label class="btn btn-option">
                            <input type="checkbox" name="hobby" autocomplete="off" value="3">
                            写代码
                            <span class="checked"></span>
                        </label>
                        <label class="btn btn-option">
                            <input type="checkbox" name="hobby" autocomplete="off" value="4">
                            思考人生
                            <span class="checked"></span>
                        </label>
                    </div> -->
                    <c:forEach items="${fns:getDictListNoBlank('DICT_BABY_NUMBER')}" var="DICT_BABY_NUMBER">
							<label class="checkbox-inline">
		                        <input type="checkbox" name="hobby" value="${DICT_BABY_NUMBER.value}" > ${DICT_BABY_NUMBER.label}
		                    </label>
					</c:forEach>                    

                </div>
            </div>
            
            
            <div class="form-group" data-msg-direction="d">
                <label class="col-xs-4 control-label text-right">民族：</label>

                <div class="col-xs-6-sm">
                    <c:forEach items="${fns:getDictListNoBlank('DICT_NATION_TYPE')}" var="DICT_NATION_TYPE">
							<label class="checkbox-inline">
		                        <input type="checkbox" name="hobby" value="${DICT_NATION_TYPE.value}" > ${DICT_NATION_TYPE.label}
		                    </label>
					</c:forEach>                    

                </div>
            </div>
<!--             <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label text-right">数字选择器：</label>

                <div class="col-xs-2-sm">
					<input name="spinner" class="spinner" value="12" size="6"/>
                </div>
            </div> -->
 <!--            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">上传附件：</label>

                <div class="col-xs-3-sm">
                    <div class=input-group">
                        <input type="file" class="filestyle" id="file1" name="file" value="33333" multiple>
                    </div>
                </div>
            </div> -->

            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">
                    备注：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="最大可输入500个汉字" data-placement="right">
                                            <textarea name="remark" class="form-control"
                                                      onpropertychange="if(value.length>500) value=value.substr(0,500)"
                                                      rows="3"></textarea>
                </div>
            </div>
        </div>
    </form>

    <div class="panel-footer text-center">
        <button type="button" data-formatter="commands"
                id="btnSelectDisabled" class="btn btn-info">
           禁用用户类型
        </button>
        <button type="button" data-formatter="commands"
                id="btnSelectEnabled" class="btn btn-info">
           启用用户类型
        </button>
        <button type="button" data-formatter="commands"
                id="btnShowValid" class="btn btn-danger">
           自定义校验
        </button>
        <button type="button" data-formatter="commands"
                id="btnRemoveValid" class="btn btn-default">
            清除自定义校验
        </button>
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

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/user/userRegist"></script>
		

		
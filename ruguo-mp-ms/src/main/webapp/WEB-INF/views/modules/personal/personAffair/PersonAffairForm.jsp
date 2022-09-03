<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<style>
<!--
.input-group-addon i {
    width: 12px;
    height: 12px;
}
-->
</style>
<div class="panel panel-default">

    <div class="panel-heading-blue">
        <div class="panel-title-blue">
        	个人信息管理
        </div>
    </div>

    <form id="userForm" name="userForm">
        <input type="hidden" class="form-control" id="id" name="id" value="${userObj.id}" >
    	<input type="hidden" class="form-control" id="isFirst" name="isFirst" value="${isFirst}" >
    	<input type="hidden" class="form-control" id="isServiceAdmin" name="isServiceAdmin" value="${isServiceAdmin}" >
    
	    <div class="panel-body form-horizontal">
	    	<div class="col-xs-10-sm">
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 col-xs-offset-3 control-label required">
		                	 用户名：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                    <input type="text" class="form-control" id="loginName" name="loginName" value="${userObj.loginName}" disabled>
		                </div>
		            </div>
	    		</div>
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
							  真实姓名：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                    <input type="text" class="form-control" id="name" name="name" value="${userObj.name}" placeholder="请输入真实姓名"/>
		                </div>
		            </div>
	    		</div>
	    		
	    	</div>
    	
	    	<div class="col-xs-10-sm">
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 col-xs-offset-3 control-label required">
							  单位名称：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                   	<!-- by liuhuan at 20170513 修改单位名称显示权限 start -->
		                    <input type="text" class="form-control" id="unitName" name="unitName" placeholder="请输入单位名称" value = "${userObj.unitName}"/>
		                     <!-- by liuhuan at 20170513 修改单位名称显示权限 end -->
		                </div>
		            </div>
	    		</div>
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label required">
							 单位地址：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                    <input type="text" class="form-control" id="address" name="address" value="${userObj.address}" placeholder="请输入单位地址"/>
		                </div>
		            </div>
	    		</div>
	    	</div>
	    	<%-- <div class="col-xs-10-sm">
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 col-xs-offset-3 control-label required">
		                   	 出生日期：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                	<div class="input-group date form_datetime col-xs-12-sm birthdayDatetime">
								<input class="form-control" id="birthday" name="birthday" size="8" type="text" 
								value="<fmt:formatDate value="${userObj.birthday}" pattern="yyyy-MM-dd"/>" placeholder="请选择出生日期"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
		                </div>
		            </div>
	    		</div>
	    		
	    	</div> --%>
    	
	    	<div class="col-xs-10-sm">
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 col-xs-offset-3 control-label">
							  联系方式：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                    <div class="input-group">
	                            <span class="input-group-addon"><i class="fa fa-mobile-phone fa-lg"></i></span>
	                            <input type="text" class="form-control" id="mobilePhone" name="mobilePhone" value="${userObj.mobilePhone}" placeholder="请输入手机号">
	                        </div>
		                </div>
		            </div>
	    		</div>
	    	</div>
    	
	    	<div class="col-xs-10-sm">
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 col-xs-offset-3 control-label"></label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                     <div class="input-group">
	                            <span class="input-group-addon"><i class="fa fa-phone fa-lg"></i></span>
	                            <input type="text" class="form-control" id="telephone" name="telephone" value="${userObj.telephone}" placeholder="请输入座机号"/>
	                        </div>
		                </div>
		            </div>
	    		</div>
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label">
							 上次登录IP：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                	<!-- By liuhuan at 20170515 修改上次登陆IP start -->
		                    <input type="text" class="form-control" id="loginIp" name="loginIp" disabled value="${userObj.loginIp}"/>
		               		<!-- By liuhuan at 20170515 修改上次登陆IP end -->
		                </div>
		            </div>
	    		</div>
	    	</div>
	    	
	    	<div class="col-xs-10-sm">
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 col-xs-offset-3 control-label required"></label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                     <div class="input-group">
	                            <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
	                            <input type="text" class="form-control" id="email" name="email" value="${userObj.email}"  placeholder="请输入邮箱"/>
	                        </div>
		                    
		                </div>
		            </div>
	    		</div>
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label">
							  上次登录时间：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                	<!-- By liuhuan at 20170515 修改上次登陆时间 start -->
		                    <input type="text" class="form-control" id="loginDate" name="loginDate" 
		                    value="<fmt:formatDate value="${userObj.loginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled/>
		                    <!-- By liuhuan at 20170515 修改上次登陆时间 end -->
		                </div>
		            </div>
	    		</div>
	    	</div>
    	
	    	<div class="col-xs-10-sm">
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 col-xs-offset-3 control-label"></label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                     <div class="input-group">
	                            <span class="input-group-addon"><i class="fa fa-weixin"></i></span>
	                            <input type="text" class="form-control" id="wechat" name="wechat" value="${userObj.wechat}" placeholder="请输入微信号"/>
	                        </div>
		                </div>
		            </div>
	    		</div>
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label">
							  总登录次数：
		                </label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                    <input type="text" class="form-control" id="loginNum" name="loginNum" disabled value="${userObj.loginNum}"/>
		                </div>
		            </div>
	    		</div>
	    	</div>
    	
	    	<div class="col-xs-10-sm">
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 col-xs-offset-3 control-label"></label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top">
		                     <div class="input-group">
	                            <span class="input-group-addon"><i class="fa fa-qq"></i></span>
	                            <input type="text" class="form-control" id="qq" name="qq" value="${userObj.qq}" placeholder="请输入QQ号"/>
	                        </div>
		                </div>
		            </div>
	    		</div>
	    		<div class="col-xs-6">
	    			<div class="form-group" data-msg-direction="d">
		                <label class="col-xs-4 control-label"></label>
		                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus" data-placement="top"></div>
		            </div>
	    		</div>
	    	</div>
        </div>
    </form>
    
    <!-- *************************************** -->

    <div class="panel-footer text-center">
        <button type="button" data-formatter="commands" id="btnSave" class="btn btn-primary btn-wide">保存 </button>
        <button type="button" data-formatter="commands" id="btnReset" class="btn btn-default btn-wide">重置</button>
    </div>
    
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/personal/personAffair/personAffairForm"></script>
		
		
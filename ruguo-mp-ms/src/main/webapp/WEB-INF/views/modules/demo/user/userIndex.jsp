<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<!-- 		<ol class="breadcrumb">
			<li>
				您的位置：
				<a href="#" id="home-link" class="home-link">开发样例</a>
			</li>
			<li>
				<a href="#" id="sub-link" class="personInfo-link">个人信息 </a>
			</li>
			<li class="active">
				基本查询
			</li>
		</ol> -->

<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            用户信息查询
        </div>
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery">查询</button>
        <button class="btn btn-default btn-xs btn-wide pull-right" id="resetbtn">重置</button>
    </div>
    <form id="searchForm" name="searchForm">
        <div class="panel-body form-horizontal search-body">

            <div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">姓名：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="64" value="" name="name" id="name"
                                   class="form-control" placeholder="请输入姓名">
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">邮箱：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="64" value="" name="email" id="email"
                                   class="form-control" placeholder="请输入用户邮箱">
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">用户类型：</label>

                        <div class="col-xs-7-sm">
                            <select class="selectpicker " name="userType" id="userType"
                                    title="请选择" >
                                <c:forEach items="${fns:getDictList('DICT_EDUCATE_GRADE')}"
                                           var="DICT_EDUCATE_GRADE">
                                    <option value="${DICT_EDUCATE_GRADE.value}">${DICT_EDUCATE_GRADE.label}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">学历（多选）：</label>

                        <div class="col-xs-7-sm">
                            <select class="selectpicker " name="education" id="education" data-actions-box="true"
                                    title="请选择" multiple>
                                <c:forEach items="${fns:getDictListNoBlank('DICT_EDUCATE_GRADE')}"
                                           var="DICT_EDUCATE_GRADE">
                                    <option value="${DICT_EDUCATE_GRADE.value}">${DICT_EDUCATE_GRADE.label}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">是否可登录：</label>

                        <div class="col-xs-7-sm">
                            <c:forEach items="${fns:getDictListNoBlank('DICT_IDENTITY_FLAG')}" var="DICT_IDENTITY_FLAG">
                                <label class="radio-inline">
                                    <input type="radio" name="loginFlag" value="${DICT_IDENTITY_FLAG.value}"> ${DICT_IDENTITY_FLAG.label}
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">出生日期：</label>

                        <div class="col-xs-7-sm">
                            <div class="input-group input-group-sm date form_datetime">
                                <input class="form-control" id="birthday" name="birthday" size="8"
                                       type="text">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
			<div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">省：</label>

                        <div class="col-xs-7-sm">
                            <select class="selectpicker selectArea" name="province" id="province" data-area-id="province"
                                    title="请选择" data-live-search="true" data-size="15">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">市：</label>

                        <div class="col-xs-7-sm">
                            <select class="selectpicker selectArea" name="city" id="city" data-area-id="city"
                                    title="请选择" >
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">区/县：</label>

                        <div class="col-xs-7-sm">
                            <select class="selectpicker selectArea" name="county" id="county" data-area-id="county"
                                    title="请选择">
                            </select>
                        </div>
                    </div>
                </div>
            </div>            
			<div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">乡/街道：</label>

                        <div class="col-xs-7-sm">
                            <select class="selectpicker selectArea" name="town" id="town" data-area-id="town"
                                    title="请选择">
                            </select>
                        </div>
                    </div>
                </div>
                
            </div>
            <div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">组织机构：</label>

                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" id="s_zzjg" name="" placeholder="点击按钮选择组织机构"
                                   autocomplete="off">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" id="zzjgbtn"><span
                                        class="glyphicon glyphicon-search"></span></button>
                            </span>

                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">人员选择：</label>

                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" id="s_user" name="" placeholder="点击按钮选择人员"
                                   autocomplete="off">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" id="userbtn"><span
                                        class="glyphicon glyphicon-search"></span></button>
                            </span>

                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">人员选择：</label>

                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" id="s_user1" name="" placeholder="点击按钮选择人员"
                                   autocomplete="off">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" id="user1btn"><span
                                        class="glyphicon glyphicon-search"></span></button>
                            </span>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!--                 </div> -->
    <div class="panel-heading-gray text-right">
        <button class="btn btn-primary btn-xs" id="btn6">显示列</button>
        <button class="btn btn-primary btn-xs" id="btn5">隐藏列</button>
        <button class="btn btn-primary btn-xs" id="btn4">获取当前行记录getCurrentRows</button>
        <button class="btn btn-primary btn-xs" id="btn3">获取当前行obj</button>
        <button class="btn btn-primary btn-xs" id="btn2">新建用户（弹窗）</button>
        <button class="btn btn-primary btn-xs" id="btn1">新建用户（跳转）</button>
    </div>
    <div class=" panel-body">
        <table id="userGrid" class="table table-hover">
            <thead class="bg-default" id="thead">
            <tr>
                <th data-width="58px" data-header-align="left" data-align="left" data-column-id="id"
                    data-identifier="true" data-visible="false" data-visibleinselection="false">序号
                </th>
                <th data-width="" data-align="center" data-column-id="name" data-visibletitle="true" data-sortable="true" data-sortable-column="a.name">姓名
                </th>
                <th data-width="" data-column-id="email" data-align="left" data-visibletitle="true">邮箱</th>
                <th data-width="" data-align="left" data-column-id="phone" data-cssclass="text-red amount">电话</th>
                <th data-width="" data-align="left" data-column-id="mobile" data-visibletitle="true">手机</th>
                <th data-align="center" data-column-id="userType" data-formatter="userType"
                    data-sortable="true">用户类型
                </th>
                <th data-width="50px" data-align="left" data-column-id="education" data-formatter="education"
                    data-sortable="true" data-visibletitle="true">学历
                </th>
                <th data-width="" data-column-id="loginFlag" data-align="center" data-formatter="loginFlag">是否可登录</th>
                <th data-width="" data-align="center" data-column-id="birthday" data-visibletitle="true"
                    data-sortable="false">出生日期
                </th>
                <th data-width="100px" data-align="center" data-formatter="operation" data-sortable="false">操作</th> 
            </tr>
            </thead>
            <tbody id="tbody">
            <!--                           <tr> -->
            <!--                           <td colspan="8">正在查询</td> -->
            <!--                           </tr> -->
            </tbody>
        </table>
        
        <!-- <div class="table-fixed">
        <table id="flex" class="table table-hover">
        <thead class="bg-default">
            <tr>
                <th>header</th><th>header</th><th>header</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="left">cell</td><td>cell</td><td class="right">cell</td>
            </tr>
            <tr>
                <td class="left">cell</td><td>cell</td><td class="right">cell</td>
            </tr>
            <tr>
                <td class="left bottom">cell</td><td class="bottom">cell</td><td class="bottom right">cell</td>
            </tr>
            </tbody>																	
        </table>
        </div> -->
        
    </div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/user/userIndex"></script>
		

		
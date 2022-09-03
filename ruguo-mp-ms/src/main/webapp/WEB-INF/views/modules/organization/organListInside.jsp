<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
                        机构管理
        </div>
        <button class="btn btn-default btn-xs btn-wide pull-right" id="resetBtn">重置</button>
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnSubmit">查询</button>
    </div>
   
    <!-- 	<div class="panel panel-default panel-search">	 -->
    <!-- <div class="panel-heading-search">

        <div class="panel-heading-search-group tab2">
            <a href="#" id="tab2query1" class="active">全部</a>
            <a href="#" id="tab2query2" class="">委托</a>
            <a href="#" id="tab2query3" class="">已受理</a>
            <button type="button" class="btn btn-primary btn-xs">历史项目1</button>
        </div>

    <span class="panel-heading-pull-right">
        <button type="button" class="btn btn-primary btn-xs"><span
                class="glyphicon glyphicon-plus"></span> 添加项目
        </button>
        <button type="button" class="btn btn-primary btn-xs"><i class="fa fa-icon-plus"></i>+ 新建项目
        </button>
        <a href="#" id="extend-link-tab2"><span class="glyphicon glyphicon-search"></span> <span
                class="caret"></span></a>
    </span>
    </div> -->
    <form id="searchFormOrgan">
    <div class="panel-body form-horizontal search-body selectid">
        <!-- start row1-->
        <div class="col-xs-12-sm">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">机构名称：</label>

                    <div class="col-xs-7-sm">
                        <input type="text" maxlength="64" value="" name="name" id="name"
                               class="form-control" placeholder="请输入机构名称">
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">机构负责人：</label>

                    <div class="col-xs-7-sm">
                        <input type="text" maxlength="64" value="" name="contactPerson" id="contactPerson"
                               class="form-control" placeholder="请输入机构负责人">
                    </div>
                </div>
            </div>
           <%--  <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">机构类型：</label>

                    <div class="col-xs-7-sm">
                        <select class="selectpicker" name="orgType" id="orgType"
                                data-title="请选择" data-style="btn-sm btn-default">
                            <c:forEach items="${fns:getDictList('DICT_ORG_TYPE')}"
                                       var="DICT_ORG_TYPE">
                                <option value="${DICT_ORG_TYPE.value}">${DICT_ORG_TYPE.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div> --%>
        </div>
       
        <div class="col-xs-12-sm">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">省：</label>

                     <div class="col-xs-7-sm">
                            <select class="selectpicker selectArea" name="provinceCode" id="provinceCode" data-area-id="province"
                                    title="请选择" data-style="btn-sm btn-default" data-live-search="true" data-size="15">
                            </select>
                     </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">市：</label>

                    <div class="col-xs-7-sm">
                            <select class="selectpicker selectArea" name="cityCode" id="cityCode" data-area-id="city"
                                    title="请选择" data-style="btn-sm btn-default">
                            </select>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">县：</label>

                    <div class="col-xs-7-sm">
                            <select class="selectpicker selectArea" name="countyCode" id="countyCode" data-area-id="county"
                                    title="请选择" data-style="btn-sm btn-default">
                            </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12-sm">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">乡：</label>

                    <div class="col-xs-7-sm">
                            <select class="selectpicker selectArea" name="townCode" id="townCode" data-area-id="town"
                                    title="请选择" data-style="btn-sm btn-default">
                            </select>
                    </div>
                </div>
            </div>
            <!-- <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">启用开始时间：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime">
                            <input class="form-control" id="startEnableDate" name="startEnableDate" size="8"
                                   type="text">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">启用结束时间：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime">
                            <input class="form-control" id="endEnableDate" name="endEnableDate" size="8"
                                   type="text">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
            </div> -->
            <!-- <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">提交方式：</label>

                    <div class="col-xs-7-sm">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="POST"> POST
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="GET"> GET
                        </label>
                    </div>
                </div>
            </div> -->
        </div>
    </div>
   
    <!--                 </div> -->
	</form>
	 <div class="panel-heading-gray text-right">
        <button class="btn btn-primary btn-xs" id="createBtn">添加机构</button>
        <!-- <button class="btn btn-primary btn-xs" id="deleteBtn">批量刪除</button> -->
		<!-- <button class="btn btn-primary btn-xs" id="btnEdit">修改</button> -->
        <!-- <button class="btn btn-primary btn-xs" id="openBtn">启用</button>
 		<button class="btn btn-primary btn-xs" id="stopBtn">停用</button> -->
	 </div>
    <div class=" panel-body">
     
        <table id="itemGrid" class="table table-hover">
            <thead class="bg-default" id="thead">
            <tr>
                <th data-header-Align="left" data-align="left" data-column-id="id"
                    data-identifier="true" data-visible="false">序号
                </th>
                <th data-align="center" data-column-id="name"
                	data-formatter="name">机构名称</th>
                <th data-align="center" data-column-id="orgType" data-formatter="orgType">机构所属</th>
                <th data-align="left" data-column-id="contactPerson" >机构负责人</th>
                <th data-align="left" data-column-id="contactPhone">手机号</th>
                <th data-align="left" data-column-id="province" >所在地区</th>
                <th data-align="center" data-formatter="operation" data-sortable="false">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<script data-main="<%=request.getContextPath()%>/modules/organization/organListInside"
        src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"></script>
		
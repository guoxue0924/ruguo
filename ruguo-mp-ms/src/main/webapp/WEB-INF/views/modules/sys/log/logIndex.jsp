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
            日志查询
        </div>
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery">查询</button>
        <button class="btn btn-default btn-xs btn-wide pull-right" id="btnReset">重置</button>
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
    <form id="searchForm">
    <div class="panel-body form-horizontal search-body">
        <!-- start row1-->
        <div class="col-xs-12-sm">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">操作菜单：</label>

                    <div class="col-xs-7-sm">
                        <input type="text" maxlength="64" value="" name="title" id="title"
                               class="form-control" placeholder="请输入操作菜单">
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">用户ID：</label>

                    <div class="col-xs-7-sm">
                        <input type="text" maxlength="64" value="" name="createbyid" id="createbyid"
                               class="form-control" placeholder="请输入用户ID">
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">日志类型：</label>

                    <div class="col-xs-7-sm">
                        <select class="selectpicker" name="type" id="type"
                                data-title="请选择" data-style="btn-sm btn-default">
                            <c:forEach items="${fns:getDictList('DICT_LOG_TYPE')}"
                                       var="DICT_LOG_TYPE">
                                <option value="${DICT_LOG_TYPE.value}">${DICT_LOG_TYPE.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <!-- start row 1-->
        <div class="col-xs-12-sm">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">开始日期：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime">
                            <input class="form-control" id="beginDate" name="beginDate" size="8"
                                   type="text">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">结束时间：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime">
                            <input class="form-control" id="endDate" name="endDate" size="8"
                                   type="text">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
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
            </div>
        </div>


    </div>
    <!--                 </div> -->
	</form>
    <div class=" panel-body">
        <table id="itemGrid" class="table table-hover">
            <thead class="bg-default" id="thead">
            <tr>
                <th data-width="58px" data-header-Align="left" data-align="left" data-column-id="id"
                    data-identifier="true" data-visible="false">序号
                </th>
                <th data-width="240px" data-align="left" data-column-id="title" data-sortable="false"
                    data-formatter="itemCode" data-visibletitle="true">操作菜单
                </th>
                <th data-width="200px" data-align="left" data-column-id="requestUri" data-visibletitle="true">URI</th>
                <th data-width="100px"data-column-id="createBy" data-formatter="createBy.name" data-visibletitle="true">操作用户</th>
                <th data-width="80px"data-column-id="createBy" data-formatter="createBy.id" data-visibletitle="true">操作用户ID</th>
                <th data-width="100px" data-align="center" data-column-id="type" data-formatter="type"
                    data-cssclass="text-red amount">日志类型
                </th>
                <th data-width="110px" data-align="center" data-column-id="method" data-formatter="purchase"
                    data-sortable="true">提交方式
                </th>
                <th data-width="90px" data-align="center" data-column-id="remoteAddr" data-formatter="itemState"
                    data-sortable="false">操作者IP
                </th>
                <th data-width="140px" data-align="center" data-column-id="createTime" data-visibletitle="true"
                    data-sortable="false">操作时间
                </th>
                <th data-width="90px" data-align="center" data-formatter="operation" data-sortable="false">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<script data-main="<%=request.getContextPath()%>/modules/sys/log/logIndex"
        src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"></script>
		
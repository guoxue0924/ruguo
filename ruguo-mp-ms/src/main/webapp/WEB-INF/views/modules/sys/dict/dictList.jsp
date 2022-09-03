<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">

	<div class="panel-heading-blue">
	    <div class="panel-title-blue">
	        字典管理
	    </div>
	    <button class="btn btn-default btn-xs btn-wide pull-right" id="btnQueryReset">重置</button>
	    <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery">查询</button>
	</div>
	
	<form id="searchForm" name="searchForm">
        <div class="panel-body form-horizontal search-body">
	            <div class="col-xs-12-sm">
	                <div class="col-xs-4">
	                    <div class="form-group form-group-sm">
	                        <label class="col-xs-5-sm control-label">类型：</label>
	
	                        <div class="col-xs-7-sm">
	                            <select class="selectpicker" name="type" data-live-search="true"
	                                    data-title="请选择" data-style="btn-sm btn-default">
	                                <c:forEach items="${fns:getDictAllType()}"
	                                           var="typeList">
	                                    <option value="${typeList.type}">${typeList.type}</option>
	                                </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-xs-4">
	                    <div class="form-group form-group-sm">
	                        <label class="col-xs-5-sm control-label">标签：</label>
	
	                        <div class="col-xs-7-sm">
	                            <input type="text" value="" name="label" class="form-control">
	                        </div>
	                    </div>
	                </div>
	                <div class="col-xs-4">
	                    <div class="form-group form-group-sm">
	                        <label class="col-xs-5-sm control-label">描述：</label>
	
	                        <div class="col-xs-7-sm">
	                            <input type="text" value="" name="description" class="form-control">
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	</form>
	
	<div class="panel-heading-gray text-right">
	    <button class="btn btn-primary btn-xs" id="btnAdd">字典添加</button>
	</div>
	
	<div class="panel-body">
            <table id="dictGrid" class="table table-hover">
                <thead class="bg-default" id="thead">
                <tr>
                    <th data-width="" data-header-align="left" data-align="left" data-column-id="id"
                        data-identifier="true" data-visible="false" data-visibleinselection="false">序号
                    </th>
                    <th data-width="" data-column-id="value" data-align="center">键值</th>
                    <th data-width="" data-column-id="label" data-align="center">标签</th>
                    <th data-width="" data-column-id="type" data-align="center">类型</th>
                    <th data-width="" data-column-id="description" data-align="center">描述</th>
                    <th data-width="" data-column-id="sort" data-align="center">排序</th>

                    <th data-width="100px" data-align="center"
                        data-formatter="operation" data-sortable="false">操作</th>
                </tr>
                </thead>
            </table>
        </div>
</div>


<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/sys/dict/dictList"></script>
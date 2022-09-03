<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
           生成方案配置
        </div>
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery">查询</button>
        <button class="btn btn-default btn-xs btn-wide pull-right" id="resetbtn">重置</button>
    </div>
    <form id="searchForm" name="searchForm">
        <div class="panel-body form-horizontal search-body">

            <div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">方案名称：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="64" value="" name="name" id="name"
                                   class="form-control" placeholder="请输入方案名称">
                        </div>
                    </div>
                </div>
                <!-- <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">说明：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="64" value="" name="comments" id="comments"
								class="form-control" placeholder="请输入说明">
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">父表表名：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="64" value="" name="parentTable" id="parentTable"
                                   class="form-control" placeholder="请输入父表表名">
                        </div>
                    </div>
                </div> -->
            </div>
            
        </div>
    </form>
    <div class="panel-heading-gray text-right">
        <button class="btn btn-primary btn-xs" id="btnAdd">添加生成方案</button>
    </div>
    <div class=" panel-body">
        <table id="genSchemeGrid" class="table table-hover">
            <thead class="bg-default" id="thead">
            <tr>
                <th data-width="58px" data-column-id="id"
                    data-identifier="true" data-visible="false" data-visibleinselection="false">
                </th>
                <th data-width="" data-align="left" data-headerAlign="left" data-column-id="name" data-sortable="true" data-sortable-column="a.name">方案名称
                </th>
                <th data-width="" data-align="left" data-headerAlign="left" data-column-id="packageName">生成模块</th>
                <th data-width="" data-align="left" data-headerAlign="left" data-column-id="moduleName">模块名</th>
                <th data-width="" data-align="left" data-headerAlign="left" data-column-id="functionName">功能名</th>
                <th data-width="" data-align="left" data-headerAlign="left" data-column-id="functionAuthor">功能作者</th>
                <th data-width="100px" data-align="center" data-formatter="operation" data-sortable="false">操作</th> 
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        
    </div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/gen/genSchemeList"></script>
		

		
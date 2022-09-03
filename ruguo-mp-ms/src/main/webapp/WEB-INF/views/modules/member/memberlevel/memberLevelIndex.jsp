<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            会员等级管理
        </div>
		<button class="btn btn-primary btn-xs btn-wide pull-right" id="btnAdd">添加等级</button>
	    <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery" style="display:none;"></button>
    </div>
    <div class=" panel-body">
        <table id="memberLevelGrid" class="table table-hover">
            <thead class="bg-default" id="thead">
	            <tr>
	                <th data-header-align="left" data-align="left" data-column-id="id"
	                    data-identifier="false" data-visible="false" data-visibleinselection="false">
	                </th>
	                <th data-align="center" data-column-id="memberLevelName">会员等级名称
	                </th>
	                <th data-column-id="memberLevelDiscount" data-align="center" data-formatter="memberLevelDiscount">会员折扣</th>
	                <th data-column-id="memberLevelDesc" data-align="center">会员等级描述</th>
	                <th data-align="center" data-column-id="memberLevelMin" data-formatter="memberLevelMin">等级下限</th>
	                <th data-align="center" data-column-id="memberLevelMax" data-formatter="memberLevelMax">等级上限</th>
	                <th data-align="center" data-formatter="operation" data-sortable="false">操作</th> 
	            </tr>
            </thead>
        </table>  
    </div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
    data-main="<%=request.getContextPath()%>/modules/member/memberlevel/memberLevelIndex"></script>
		

		
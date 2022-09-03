<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12" style="height:625px;">
        <div class="form-group" data-msg-direction="r">
            <div class="panel panel-default panel-search">
                <div class="panel-heading-blue">
                    <div class="panel-title-blue">
                        用户信息查询
                    </div>
                    <button class="btn btn-primary btn-xs btn-wide pull-right" id="selectUserBtnReset">重置</button>
                    <button class="btn btn-primary btn-xs btn-wide pull-right" id="selectUserBtnQuery">查询</button>
                </div>
                <form id="selectUserSearchForm" name="searchForm">
                    <div class="panel-body form-horizontal search-body">

                        <div class="col-xs-12-sm">
                            <div class="col-xs-4">
                                <div class="form-group form-group-sm">
                                    <label class="col-xs-5-sm control-label">姓名：</label>
                                    <div class="col-xs-7-sm">
                                        <input type="text" maxlength="64" value="" name="name" id="nameFilter"
                                               class="form-control" placeholder="请输入姓名" >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <div class=" panel-body">
                    <div class="">
                        <table id="selectUserUserGrid" class="table table-hover">
                            <thead class="bg-default" id="thead">
                            <tr>
                                <th data-header-align="left" data-align="left" data-column-id="id"
                                    data-identifier="true" data-visible="false" data-visibleinselection="false">序号
                                </th>
                                <th data-align="center" data-column-id="name" data-sortable="false">姓名
                                </th>
                                <%--<th data-width="auto" data-align="center" data-column-id="userRoleName" data-sortable="false"--%>
                                    <%--data-visibletitle="true">角色名称--%>
                                <%--</th>--%>
                                <th data-align="center" data-column-id="sysOrgName" data-sortable="false"
                                    data-visibletitle="true">机构名称
                                </th>
                            </tr>
                            </thead>
                            <tbody id="tbody">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="col-xs-12 text-center">
            <button type="button" data-formatter="commands"
                    id="btnSelectUserDialogSave" class="btn btn-primary">
                确定
            </button>
            <button class="btn btn-primary" id="btnSelectUserDialogCancle">
                关闭
            </button>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/sys/selectUserDialog.js"></script>

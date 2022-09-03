<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
	<div class="col-xs-12">
   		<span style="color:#456798;"><i class="fa fa-user fa-1" aria-hidden="true" style="color:#31b0d5;"></i> 管理机构</span>&nbsp;&nbsp;
   		<span style="color:#456798;"><i class="fa fa-user fa-1" aria-hidden="true" style="color:#f0ad4e;"></i> 服务机构</span>
   	</div>
   	<div class="col-xs-12">
   	<hr>
   	</div>
    <div class="col-xs-12">
        <div style="height:350px;overflow: auto;">
            <div id="ztree" class="ztree"></div>
        </div>
        <hr>
        <div class="col-xs-12 text-center">
            <button type="button" 
                    id="btnTreeboxDialogSave" class="btn btn-primary">
                确定
            </button>
            <button type="button" 
                    id="btnTreeboxDialogReset" class="btn btn-default">
                清空
            </button>
            <button class="btn btn-default" id="btnTreeboxDialogCancle">
                关闭
            </button>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/tree/orgTreeselect.js"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">

    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            附件管理
        </div>
    </div>

    <form id="fileForm" name="fileForm" enctype="multipart/form-data">
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
                    邮箱：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                    <input type="text" class="form-control" name="email" value="">
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">上传附件1：</label>

                <div class="col-xs-3-sm">
                	<input type="file" class="file-input" id="file1" name="files" value="" tabindex="-1" data-maxfiles="3" data-filesize="2097152" data-filetype="txt|doc">
                	<div class="input-group">
                         <input type="text" class="form-control file-input-label" placeholder="请选择文件" readOnly
                                aria-describedby="sizing-addon3" autocomplete="off">
                         <span class="input-group-btn" tabindex="0">
                         	<label for="file1" class="btn btn-default form-control file-input-label-for">
                 				<span class="glyphicon glyphicon-folder-open"></span>
                 			</label>
                 		</span>
                     </div>
                </div>
                <div class="col-xs-1-sm">
                <a class="btn btn-link file-append"><i class="fa fa-plus-circle fa-lg" aria-hidden="true"></i></a>
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">上传附件2：</label>

                <div class="col-xs-3-sm">
                	<input type="file" class="file-input" id="file2" name="files" value="" tabindex="-1" data-maxfiles="2" data-filesize="2097152" data-filetype="jpg|jpeg|bmp|gif">
                	<div class="input-group">
                         <input type="text" class="form-control file-input-label" placeholder="请选择文件" readOnly
                                aria-describedby="sizing-addon3" autocomplete="off">
                         <span class="input-group-btn" tabindex="0">
                         	<label for="file2" class="btn btn-default form-control file-input-label-for">
                 				<span class="glyphicon glyphicon-folder-open"></span>
                 			</label>
                 		</span>
                     </div>
                </div>
                <div class="col-xs-1-sm">
                <a class="btn btn-link file-append"><i class="fa fa-plus-circle fa-lg" aria-hidden="true"></i></a>
<!--                 <a class="btn btn-link-red file-remove"><i class="fa fa-times-circle fa-lg" aria-hidden="true"></i></a> -->
                </div>
            </div>
        </div>
    </form>

    <div class="panel-footer text-center">
        <button type="button" data-formatter="commands"
                id="btnReset" class="btn btn-default">
            重置
        </button>
<!--         <button type="button" data-formatter="commands" -->
<!--                 id="btnSavePost" class="btn btn-primary"> -->
<!--             开始上传 -->
<!--         </button> -->
<!--         <button type="button" data-formatter="commands" -->
<!--                 id="btnSavePostJson" class="btn btn-primary"> -->
<!--             保存(Json) -->
<!--         </button> -->
        <button type="button" data-formatter="commands"
                id="btnSaveSubmit" class="btn btn-primary">
            保存(submit)
        </button>
    </div>

	<div class=" panel-body">
        <table id="fileGrid" class="table table-hover">
            <thead class="bg-default">
            <tr>
                <th data-width="58px" data-header-align="left" data-align="left" data-column-id="id"
                    data-identifier="true" data-visible="false" data-visibleinselection="false">序号
                </th>
                <th data-width="" data-align="center" data-column-id="demoUser" data-formatter="demoUser.name" data-sortable="false"
                    data-visibletitle="true">姓名
                </th>
                <th data-width="" data-column-id="email" data-align="left" data-formatter="demoUser.email" data-visibletitle="true">邮箱</th>
                <th data-width="" data-align="left" data-column-id="fileName" data-cssclass="text-red amount">附件名称</th>
                <th data-width="" data-align="left" data-column-id="type" data-visibletitle="true">文件类型</th>
                <th data-width="" data-align="center" data-column-id="createTime" data-visibletitle="true"
                    data-sortable="false">上传日期
                </th>
                <th data-width="100px" data-align="center" data-formatter="operation" data-sortable="false">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
    </div>
</div>
	
<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true" data-main="<%=request.getContextPath()%>/modules/demo/upload/fileuploadIndex"></script>
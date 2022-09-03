<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            导入Excel功能
        </div>
    </div>
    <form id="importForm" name="importForm" enctype="multipart/form-data">
        <div class="panel-body form-horizontal">
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">导入Excel：</label>
                <div class="col-xs-3-sm">
                	<input type="file" class="file-input" id="file1" name="files" value="" tabindex="-1" data-maxfiles="3" data-filesize="2097152" data-filetype="xlsx">
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
             </div>
        </div>
	</form>
</div>       
 <div class="panel-body form-horizontal">
    <div class="col-xs-12 text-center">
    	  <button type="button" data-formatter="commands"
                   id="btnSave" class="btn btn-primary">
               导入
           </button>
           <button type="button" data-formatter="commands"
                   id="btnReset" class="btn btn-default">
               重置
           </button>
           <button class="btn btn-default" id="btnCancle">
               取消
           </button>
       </div>
 </div> 
<script src="<%=request.getContextPath()%>/modules/goods/goods/importGoods.js"></script>	
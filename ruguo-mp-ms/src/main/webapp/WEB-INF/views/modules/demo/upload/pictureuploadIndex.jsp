<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default" style="min-height:400px">
	<div class="panel-heading-blue">
              <div class="panel-title-blue">
                  <i class="fa fa-envelope" aria-hidden="true"></i>
                  图片上传
              </div>
          </div>
<div class="panel-body form-horizontal">
                <div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>上传图片预览</strong></h4>
                        </div>
                    </div>
                </div>
                <!--<div class="form-group">
                    <label class="col-xs-4 control-label">
                        姓名：
                    </label>

                    <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus" data-content="请输入20位有效字符" data-original-title="" title="">
                        <input type="text" class="form-control" name="jgdm">
                    </div>
                    <div class="col-xs-1-sm control-label text-red pull-left">(必填)</div>
                </div>
                -->
<!--		        <div class="form-group">-->
<!--		            <label for="photo_id" class="col-xs-4 control-label">照片：</label>-->
<!--		            <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus" data-content="可以上传2048KB以内的doc、docx、xls、xlsx、txt、pdf类型的文件!"> -->
<!--		            <input type="file" name="upload" id="upload" class="form-control" value="" multiple="" tabindex="-1" style="position: absolute; clip: rect(0px 0px 0px 0px);"><div class="bootstrap-filestyle input-group"><input type="text" class="form-control " placeholder="请选择文件" disabled=""> <span class="group-span-filestyle input-group-btn" tabindex="0"><label for="upload" class="btn btn-default form-control "><span class="icon-span-filestyle glyphicon glyphicon-folder-open"></span> <span class="buttonText"></span></label></span></div>-->
<!--		            </div> -->
<!--		        </div>-->
                
                <div class="form-group">
<!--                    <label for="" class="col-xs-1 control-label">照片：</label>-->

                    <div class="col-xs-12" data-toggle="popover" data-trigger="focus"
                         data-content="可以上传2048KB以内的doc、docx、xls、xlsx、txt、pdf类型的文件!">
                        <div id="wrapper">
                            <div id="container">
                                <!--头部，相册选择和格式选择-->
                                <div id="uploader">
                                    <div class="queueList">
                                        <div id="dndArea" class="placeholder">
                                            <div id="filePicker"></div>
                                            <p>或将照片拖到这里，单次最多可选10张</p>
                                        </div>
                                    </div>
                                    <div class="statusBar" style="display:none;">
                                        <div class="progress">
                                            <span class="text">0%</span>
                                            <span class="percentage"></span>
                                        </div>
                                        <p class="info"></p>

                                        <div class="btns">
                                            <div id="filePicker2"></div>
                                            <div class="uploadBtn">开始上传</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>上传图片列表</strong></h4>
                        </div>
                    </div>
                </div>
                <table class="table table-hover" id="filegrid">
                     <thead class="bg-default">
                         <tr>
<!--                             <th data-width="40px" data-header-Align="left" data-align="left" data-column-id="rownum" data-identifier="false" data-visible="true">序号</th>-->
                             <th data-width="100px" data-column-id="fileId" data-sortable="false" data-identifier="true" data-visible="false">FILE_ID</th>
                             <th data-width="" data-align="left" data-column-id="fileCode" data-formatter="fileCode"  data-sortable="false" title="111">附件编码</th>
                             <th data-width="" data-align="left" data-column-id="fileName" data-formatter="fileName"  data-sortable="false" data-visibletitle="true">附件名称</th>
                             <th data-width="" data-align="left" data-column-id="contentType" data-formatter="contentType"  data-sortable="false">附件信息</th>
                             <th data-width="" data-align="left" data-column-id="fileExt" data-formatter="fileExt"  data-sortable="false">附件类型</th>
<!--                             <th data-width="100%" data-align="left" data-column-id="fileDescription" data-formatter="file_name"  data-sortable="false">附件描述</th>-->
                             <th data-width="" data-align="left" data-column-id="fileSize" data-formatter="fileRename"  data-sortable="false">文件大小</th>
                             <th data-width="" data-align="left" data-column-id="fileBusiType" data-formatter="fileBusiType"  data-sortable="false">业务类型</th>
                             <th data-width="140px" data-align="left" data-column-id="operatorDate" data-formatter="operatorDate"  data-sortable="false">上传时间</th>
                             <th data-width="120px" data-align="center" data-formatter="operation"  data-sortable="false">操作</th>
                         </tr>
                     </thead>
                     <tbody>
                     </tbody>
                 </table>
            </div>
</div>
<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true" data-main="<%=request.getContextPath()%>/modules/demo/upload/pictureuploadIndex"></script>
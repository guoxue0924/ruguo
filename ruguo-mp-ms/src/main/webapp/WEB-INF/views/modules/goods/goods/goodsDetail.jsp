<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="panel panel-default">

    <form id="goodsForm" name="goodsForm" enctype="multipart/form-data">
        <div class="panel-body form-horizontal">
        <input type="hidden" name="id" value="${goods.id}">
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    商品编码：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入10位有效字符">
                    <input type="text" class="form-control" name="goodsCode" value="${goods.goodsCode}"  readonly="readonly">
                </div>
            </div>
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    商品名称：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入50位有效字符">
                    <input type="text" class="form-control" name="goodsName" value="${goods.goodsName}" readonly="readonly">
                </div>
            </div>
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                    商品副标题：
                </label>

                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus"
                    data-content="请输入50位有效字符">
                    <input type="text" class="form-control" name="goodsTitle" value="${goods.goodsTitle}" readonly="readonly">
                </div>
            </div>
             <div class="form-group" data-msg-direction="r">
					
						<label class="col-xs-4 control-label">商品分类：</label>
						<div class="col-xs-3-sm">
							<div class="input-group input-group-sm">
							<input type="hidden" id="goodsClass" name="goodsClassCode" value="${goods.goodsClassCode}">
						        <input type="text" class="form-control" id="gclass" name="gclass" placeholder="点击按钮选择商品分类"
						               autocomplete="off" value="${goods.goodsClassName}" readonly="readonly"> <!-- 文本框 -->
						        <span class="input-group-btn">
						            <button class="btn btn-default" type="button" id="editjgbtn">
						            <span class="glyphicon glyphicon-search"></span></button> <!-- 按钮 -->
						        </span>
						  	</div>
						</div>
					
				</div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                	     商品封面图：
                </label>

               <!--  <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                     <button class="btn btn-primary btn-xs" id="btn6">+上传图片</button>
                </div> -->
                <div class="col-xs-3-sm">
                <div class="b_headImg" style="width:60px;height:60px;line-height:60px;text-align:center;">
                <c:choose>
               	 	<c:when test="${goodsLogoFileInfo.id == null}">
                		<img src="../../images/timg.jpg" id="headImg" class="headImg" style="width:100%;height:100%;line-height:60px;text-align:center" >
                	</c:when>
                	<c:otherwise >
            			<img src="${ctx}/common/file/show?fileId=${goodsLogoFileInfo.id}" id="headImg" class="headImg" style="width:100%;height:100%;line-height:60px;text-align:center" ><!--  style="background-image: url(http://localhost/common/file/show?fileId=${result.data.picId});" --> 
           	   		</c:otherwise>
                </c:choose>
                
           	 </div>
                	<input type="file" class="file-input" id="file1" name="logofiles" value="" tabindex="-1" data-maxfiles="" data-filesize="2097152" data-filetype="jpg|jpeg|bmp|gif">
                	<div class="input-group" style="display: none;">
                         <input type="text" class="form-control file-input-label" placeholder="请选择文件" readOnly
                                aria-describedby="sizing-addon3" autocomplete="off">
                         <span class="input-group-btn" tabindex="0">
                         	<label for="file2" class="btn btn-default form-control file-input-label-for">
                 				<span class="glyphicon glyphicon-folder-open"></span>
                 			</label>
                 		</span>
                     </div>
                </div>
                <!-- <div class="col-xs-1-sm">
                <a class="btn btn-link file-append"><i class="fa fa-plus-circle fa-lg" aria-hidden="true"></i></a>
                </div> -->
                
            </div>
            
             
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">
                   	 商品有效期：
                </label>

                <div class="col-xs-1-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入整数">
                    <input type="text" class="form-control" name="goodsValidityNum" value="${goods.goodsValidityNum}" readonly="readonly">
                </div>
                <div class="col-xs-1-sm" style="margin-left: 30px;">
                            <select class="selectpicker " name="goodsValidityUnit" id="goodsValidityUnit"
                                    title="请选择" readonly="readonly">
                                <c:forEach items="${fns:getDictListNoBlank('DICT_GOODS_VALIDITY_UNIT')}"
                                           var="DICT_GOODS_VALIDITY_UNIT">
                                    <option value="${DICT_GOODS_VALIDITY_UNIT.value}"
                                     <c:if test="${goods.goodsValidityUnit eq DICT_GOODS_VALIDITY_UNIT.value}">selected</c:if>
                                    >${DICT_GOODS_VALIDITY_UNIT.label}</option>
                                </c:forEach>
                            </select>
                        </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                    	商品定价：
                </label>

                <div class="col-xs-1-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入数字">
                    <input type="text" class="form-control" name="goodsPrice" value="${goods.goodsPrice}" readonly="readonly">
                </div>
                 <div class="col-xs-1-sm" style="margin-left: 10px;margin-top: 10px;">
                 <div >元</div>
                 </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                    	库存：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入整数">
                    <input type="text" class="form-control" name="goodsStock" value="${goods.goodsStock}" readonly="readonly">
                </div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                    	状态：
                </label>

                <div class="col-xs-2-sm">
                    <c:forEach items="${fns:getDictListNoBlank('DICT_GOODS_STATUS')}" var="DICT_GOODS_STATUS">
							<label class="radio-inline">
		                        <input type="radio" name="goodsStatus" value="${DICT_GOODS_STATUS.value}" 
		                       <c:if test="${goods.goodsStatus eq DICT_GOODS_STATUS.value}">checked="checked"</c:if> 
		                        > ${DICT_GOODS_STATUS.label}
		                    </label>
					</c:forEach>
                </div>

            </div>
             <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                	   商品图片：
                </label>
 	<c:forEach items="${fileInfoList}" var="fileInfoList"  varStatus="status">
                 
                <div class="col-xs-1-sm" >
                 <div class="b_headImg" style="width:60px;height:60px;line-height:60px;text-align:center;">
            <img src="${ctx}/common/file/show?fileId=${fileInfoList.id}" id="goodsImg" class="file-goodsImg" style="width:100%;height:100%;line-height:60px;text-align:center" ><!--  style="background-image: url(http://localhost/common/file/show?fileId=${result.data.picId});" --> 
            
            </div>
                	<input type="file" style="display: none;" class="file-input" id="file2" name="files"  value="" tabindex="-1" data-maxfiles="" data-filesize="2097152" data-filetype="jpg|jpeg|bmp|gif">
                	 <div class="input-group" style="display: none;">
                         <input type="text" class="form-control file-input-label" placeholder="请选择文件" readOnly
                                aria-describedby="sizing-addon3" autocomplete="off">
                         <span class="input-group-btn" tabindex="0">
                         	<label for="file2" class="btn btn-default form-control file-input-label-for">
                 				<span class="glyphicon glyphicon-folder-open"></span>
                 			</label>
                 		</span>
                     </div> 
                </div>
                 
                
                 
                </c:forEach>
            </div>
           
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    商品详情介绍：
                </label>

                 <div style="width: 600px;float: left;" data-toggle="popover" data-trigger="focus"
                     >
                  <div id="summernote2"></div> 
        </div>
            </div>
            <input type="hidden" id="goodsDetails2" name="goodsDetails2" value="${goods.goodsDetails}" >
        </div>
    </form>

    <div class="panel-footer text-center">
       
		<button type="button" data-formatter="commands"
                id="btnCancel2" class="btn btn-default">
            取消
        </button>
</div>
</div>
		
<script src="<%=request.getContextPath()%>/modules/goods/goods/goodsDetail.js"></script>
		
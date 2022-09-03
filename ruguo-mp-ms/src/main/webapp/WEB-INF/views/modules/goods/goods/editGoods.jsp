<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="panel panel-default">

    <form id="goodsForm" name="goodsForm" enctype="multipart/form-data">
        <div class="panel-body form-horizontal">
        <input type="hidden" name="id" value="${goods.id}">
            <%-- <div class="form-group" data-msg-direction="r" style="display: none;">
                <label class="col-xs-4 control-label required">
                    商品编码：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入10位有效字符">
                    <input type="text" class="form-control" name="goodsCode" value="${goods.goodsCode}"  readonly="readonly">
                </div>
            </div> --%>
            <input type="hidden" class="goodsCode"  name="goodsCode" value="${goods.goodsCode }">
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    产品名称：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入50位有效字符">
                    <input type="text" class="form-control" name="goodsName" value="${goods.goodsName}">
                </div>
            </div>
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                  产品副标题：
                </label>

                <div class="col-xs-5-sm" data-toggle="popover" data-trigger="focus"
                    data-content="请输入50位有效字符">
                    <input type="text" class="form-control" name="goodsTitle" value="${goods.goodsTitle}">
                </div>
            </div>
            
				<div class="form-group" data-msg-direction="r">
						<label class="col-xs-4 control-label required">产品类别：</label>
						<div class="col-xs-3-sm">
							<div class="input-group input-group-sm">
							<input type="hidden" id="goodsClass" name="goodsClassCode" value="${goods.goodsClassCode}">
						        <input type="text" class="form-control" id="gclass" name="gclass" value="${goods.goodsClassName}" placeholder="点击按钮选择商品分类"
						               autocomplete="off"  readonly="readonly"> <!-- 文本框 -->
						        <span class="input-group-btn">
						            <button class="btn btn-default" type="button" id="editjgbtn">
						            <span class="glyphicon glyphicon-search"></span></button> <!-- 按钮 -->
						        </span>
						  	</div>
						</div>
					
				</div>
				
					<div class="docKind" style="display: none;">
			<div class="form-group" data-msg-direction="r">
						<label class="col-xs-4 control-label required">服务机构级别：</label>
						<div class="col-xs-3-sm">
							<select class="selectpicker " name="serviceOrgLevelCode" id="serviceOrgLevelCode" 
                                    title="请选择" >
                                <c:forEach items="${fns:getDictList('DICT_HEALTH_ORG_LEVEL')}"
                                           var="DICT_HEALTH_ORG_LEVEL">
                                    <option value="${DICT_HEALTH_ORG_LEVEL.value}" <c:if test="${goods.serviceOrgLevelCode eq DICT_HEALTH_ORG_LEVEL.value}">selected</c:if>>${DICT_HEALTH_ORG_LEVEL.label}</option>
                                </c:forEach>
                            </select>
						</div>
					
				</div>
				<div class="form-group" data-msg-direction="r">
						<label class="col-xs-4 control-label required">服务机构等级：</label>
						<div class="col-xs-3-sm">
							<select class="selectpicker " name="serviceOrgRankCode" id="serviceOrgRankCode" 
                                    title="请选择" >
                                <c:forEach items="${fns:getDictList('DICT_HOS_GRADE')}"
                                           var="DICT_HOS_GRADE">
                                    <option value="${DICT_HOS_GRADE.value}" <c:if test="${goods.serviceOrgLevelCode eq DICT_HOS_GRADE.value}">selected</c:if>>${DICT_HOS_GRADE.label}</option>
                                </c:forEach>
                            </select>
						</div>
					
				</div>
				
				<div class="form-group" data-msg-direction="r">
						<label class="col-xs-4 control-label required">服务人员类型：</label>
						<div class="col-xs-3-sm">
							<select class="selectpicker " name="servicePersonTypeCode" id="servicePersonTypeCode"
                                    title="请选择" >
                                <c:forEach items="${fns:getDictList('DICT_SERVICE_PERSON_TYPE')}"
                                           var="DICT_SERVICE_PERSON_TYPE">
                                    <option value="${DICT_SERVICE_PERSON_TYPE.value}" <c:if test="${goods.servicePersonTypeCode eq DICT_SERVICE_PERSON_TYPE.value}">selected</c:if>>${DICT_SERVICE_PERSON_TYPE.label}</option>
                                </c:forEach>
                            </select>
						</div>
					
				</div>
				
				<div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    	服务人员级别：
                </label>

                <div class="col-xs-5-sm">
                    <c:forEach items="${fns:getDictListNoBlank('DICT_SERVICE_PERSON_LEVEL')}" var="DICT_SERVICE_PERSON_LEVEL" >
							<label class="radio-inline">
		                        <input type="radio" id="servicePersonLevelCode" name="servicePersonLevelCode" value="${DICT_SERVICE_PERSON_LEVEL.value}"  <c:if test="${goods.servicePersonLevelCode eq DICT_SERVICE_PERSON_LEVEL.value}">checked</c:if>> ${DICT_SERVICE_PERSON_LEVEL.label}
		                    </label>
					</c:forEach>
                </div>

            </div>
            <div class="form-group" data-msg-direction="r">
						<label class="col-xs-4 control-label required">服务人员专业：</label>
						<div class="col-xs-3-sm">
							<select class="selectpicker " name="servicePersonMajorCode" id="servicePersonMajorCode"
                                    title="请选择" >
                                <c:forEach items="${fns:getDictList('DICT_DOC_PRACTICE_SCOPE')}"
                                           var="DICT_DOC_PRACTICE_SCOPE">
                                    <option value="${DICT_DOC_PRACTICE_SCOPE.value}" <c:if test="${goods.servicePersonMajorCode eq DICT_DOC_PRACTICE_SCOPE.value}">selected</c:if>>${DICT_DOC_PRACTICE_SCOPE.label}</option>
                                </c:forEach>
                            </select>
						</div>
					
				</div>
			</div>	
            <div class="form-group" data-msg-direction="r" id="aa">
                <label class="col-xs-4 control-label required" >
                	 定价：
                </label>
            </div>
            
                <c:forEach items="${goodsPriceList}" var="GOODS_PRICE_List">
                 <div class="form-group" data-msg-direction="r" id="aa">
                 <label class="col-xs-4 control-label" >
                	 
                </label>
                <div class="col-xs-5-sm">
                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入整数">
                     <input type="hidden" class="form-control" name="goodsPriceCode" value="${GOODS_PRICE_List.goodsPriceCode}">
                    <input type="text" class="form-control" name="goodsPrice" value="${GOODS_PRICE_List.goodsPrice}">
                </div>
                <div class="col-xs-1-sm" style="margin-left: 10px;margin-top: 10px;width:20px;">
                <div>/</div>
                </div>
                
                   <div class="col-xs-2-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入整数">
                    <input type="text" class="form-control" name="goodsValidityNum" value="${GOODS_PRICE_List.goodsValidityNum}">
                   </div>
                   <div class="col-xs-1-sm" style="margin-left: 10px;margin-top: 10px;">
                <div>月</div>
                </div>
                <!-- <div class="col-xs-2-sm" data-toggle="popover" data-trigger="focus" >
                     <button type="button" class="btn btn-primary btn-xs" id="addGoodsButton">添加+</button> 
                </div> -->
                 </div>
                 </div>
                 </c:forEach>
                 <!-- <div class="col-xs-3-sm" style="margin-left: 8px;margin-top: 8px;" id="cc">
                 <div >点击一次添加，选择一个商品</div>
            	</div> -->
            	
           
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                    	试用设置：
                </label>

                <div class="col-xs-1-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入数字">
                    <input type="text" class="form-control" name="probationPeriod" value="${goods.probationPeriod}">
                </div>
                 <div class="col-xs-1-sm" style="margin-left: 10px;margin-top: 10px;">
                 <div >天</div>
                 </div>
            </div>
             <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    配置服务包：
                </label>

                <div class="col-xs-3-sm">
							<select class="selectpicker " name="servicePackageCodeArr" id="servicePackageCodeArr"
                                    title="请选择" multiple="multiple" >
                                <c:forEach items="${ServicePackageName}"
                                           var="SERVICE_PACKAGE">
                                    <option value="${SERVICE_PACKAGE.servicePackageCode}"  
                                    <c:forEach items="${SelectPackageName}" var="SERVICE_PACKAGE_CODE" >
                                    <c:if test="${SERVICE_PACKAGE_CODE  == SERVICE_PACKAGE.servicePackageCode }">selected</c:if> 
                                    </c:forEach>
                                    >${SERVICE_PACKAGE.servicePackageName}</option>
                                </c:forEach>
                            </select>
                           
				</div>
            </div>
            
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                	    商品图标：
                </label>

               <!--  <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                     <button class="btn btn-primary btn-xs" id="btn6">+上传图片</button>
                </div> -->
                <div class="col-xs-3-sm">
                <div class="b_headImg" style="width:60px;height:60px;line-height:60px;text-align:center;">
                <c:choose>
               	 	<c:when test="${goods.goodsIcon == null}">
                		<img src="../../images/timg.jpg" id="headImg" class="headImg" style="width:100%;height:100%;line-height:60px;text-align:center" >
                	</c:when>
                	<c:otherwise >
            			 <img src="${goods.goodsIcon}" id="headImg" class="headImg" style="width:100%;height:100%;line-height:60px;text-align:center" >
           	   		</c:otherwise>
                </c:choose>
                
           	 </div>
                	<input type="file" class="file-input" id="file1" name="logopic" value="" tabindex="-1" data-maxfiles="" data-filesize="2097152" data-filetype="jpg|jpeg|bmp|gif">
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
            
             
            
           
            
            
<%--             
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                    	库存：
                </label>

                <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入整数">
                    <input type="text" class="form-control" name="goodsStock" value="${goods.goodsStock}">
                </div>
            </div> --%>
            
            <%-- <div class="form-group" data-msg-direction="r">
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

            </div> --%>
             <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                	  商品详情轮播图：
                </label>

                <!-- <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                     <button class="btn btn-primary btn-xs" id="btn6">+上传图片</button>
                </div> -->
                
                 <div class="col-xs-1-sm">
                 <div class="b_goodsImg" style="width:60px;height:60px;line-height:60px;text-align:center;">
            <img src="../../images/timg.jpg" id="goodsImg" class="file-goodsImg" style="width:100%;height:100%;line-height:60px;text-align:center" ><!--  style="background-image: url(http://localhost/common/file/show?fileId=${result.data.picId});" --> 
            
            </div>
                	<input type="file" class="file-input" id="file2" name="files" value="" tabindex="-1" data-maxfiles="" data-filesize="2097152" data-filetype="jpg|jpeg|bmp|gif">
                	<!-- <div class="input-group" style="display: none;">
                         <input type="text" class="form-control file-input-label" placeholder="请选择文件" readOnly
                                aria-describedby="sizing-addon3" autocomplete="off">
                         <span class="input-group-btn" tabindex="0">
                         	<label for="file2" class="btn btn-default form-control file-input-label-for">
                 				<span class="glyphicon glyphicon-folder-open"></span>
                 			</label>
                 		</span>
                     </div> -->
                </div>
                <div class="col-xs-1-sm">
                <a class="btn btn-link file-append" ><i class="fa fa-plus-circle fa-lg" aria-hidden="true"></i></a>
                </div>
            </div>
            <c:forEach items="${fileInfoList}" var="fileInfoList"  varStatus="status">
                 <div class="form-group" data-msg-direction="r" id="${fileInfoList.id}">
                
                 <label class="col-xs-4 control-label ">
                </label>
                <div class="col-xs-1-sm" >
                 <div class="b_fgoodsImg" style="width:60px;height:60px;line-height:60px;text-align:center;">
            <img src="${fileInfoList.goodsPicUrl}" id="fgoodsImg" class="file-fgoodsImg" style="width:100%;height:100%;line-height:60px;text-align:center" ><!--  style="background-image: url(http://localhost/common/file/show?fileId=${result.data.picId});" --> 
            
            </div>
                	<!-- <input type="file" style="display: none;" class="file-input" id="fileGoodsImg" name="files"  value="" tabindex="-1" data-maxfiles="" data-filesize="2097152" data-filetype="jpg|jpeg|bmp|gif">
                	 <div class="input-group" style="display: none;">
                         <input type="text" class="form-control file-input-label" placeholder="请选择文件" readOnly
                                aria-describedby="sizing-addon3" autocomplete="off">
                         <span class="input-group-btn" tabindex="0">
                         	<label for="file2" class="btn btn-default form-control file-input-label-for">
                 				<span class="glyphicon glyphicon-folder-open"></span>
                 			</label>
                 		</span>
                     </div>  -->
                </div>
                 <div class="col-xs-1-sm">
                 <a class="btn btn-link-red  file-remove"><i class="fa fa-times-circle fa-lg" aria-hidden="true"></i></a> 
                </div> 
                
                 </div>
                </c:forEach>
                
                <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                    	是否推荐：
                </label>

                <div class="col-xs-2-sm">
                    <c:forEach items="${fns:getDictListNoBlank('DICT_IDENTITY_FLAG')}" var="DICT_IDENTITY_FLAG">
							<label class="radio-inline">
		                        <input type="radio" name="isRecommend" value="${DICT_IDENTITY_FLAG.value}" <c:if test="${goods.isRecommend eq DICT_IDENTITY_FLAG.value}">checked</c:if>> ${DICT_IDENTITY_FLAG.label}
		                    </label>
					</c:forEach>
                </div>

            </div>
                
                <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label ">
                	    推荐轮播图片：
                </label>

               <!--  <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入20位有效字符">
                     <button class="btn btn-primary btn-xs" id="btn6">+上传图片</button>
                </div> -->
                <div class="col-xs-3-sm">
                <div class="b_headImg" style="width:60px;height:60px;line-height:60px;text-align:center;">
                <c:choose>
               	 	<c:when test="${goods.recommendPicUrl == null}">
                		<img src="../../images/timg.jpg" id="recommendImg" class="recommendImg" style="width:100%;height:100%;line-height:60px;text-align:center" >
                	</c:when>
                	<c:otherwise >
            			 <img src="${goods.recommendPicUrl}" id="recommendImg" class="recommendImg" style="width:100%;height:100%;line-height:60px;text-align:center" >
           	   		</c:otherwise>
                </c:choose>
                
           	 </div>
                	<input type="file" class="file-input" id="file3" name="recommendPic" value="" tabindex="-1" data-maxfiles="" data-filesize="2097152" data-filetype="jpg|jpeg|bmp|gif">
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
             <div class="form-group" data-msg-direction="r" id="aa">
                <label class="col-xs-4 control-label " >
                	 标签：
                </label>
 				<input type="hidden" class="form-control" id="goodsTags" name="goodsTags" value="">
                <div class="col-xs-2-sm" data-toggle="popover" data-trigger="focus" >
                     <button type="button" class="btn btn-primary btn-xs" id="editTagsButton">修改</button> 
                </div>
                 
                 <!-- <div class="col-xs-3-sm" style="margin-left: 8px;margin-top: 8px;" id="cc">
                 <div >点击一次添加，选择一个商品</div>
            	</div> -->
            	
            </div>
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label">
                    产品标签匹配个数：
                </label>

                <div class="col-xs-1-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入50位有效字符">
                    <input type="text" class="form-control" id="tagMatchedNum" name="tagMatchedNum" value="${goods.tagMatchedNum }">
                    <input type="hidden"  id="tagMatchedNumCount" name="tagMatchedNumCount"  value="${tagMatchedNumCount }">
                </div>
            </div>    
                
                
                
                
            <div class="form-group" data-msg-direction="r">
                <label class="col-xs-4 control-label required">
                    商品详情介绍：
                </label>

                 <div style="width: 600px;float: left;" data-toggle="popover" data-trigger="focus"
                     >
                 <div id="summernote" ></div>
        </div>
            </div>
            <input type="hidden" id="goodsDetails" name="goodsDetails" value="${goods.goodsDetails}">
        </div>
    </form>

    <div class="panel-footer text-center">
       
        <button type="button" data-formatter="commands"
                id="btnSaveSubmit" class="btn btn-primary">
            保存
        </button>
		<button type="button" data-formatter="commands"
                id="btnCancel" class="btn btn-default">
            取消
        </button>
</div>
</div>
		
<script src="<%=request.getContextPath()%>/modules/goods/goods/editGoods.js"></script>
		
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">
	<div class="panel-heading-blue">
        <div class="panel-title-blue">
            分类管理
        </div>
    </div>
    
    <div class="col-xs-12 panel panel-default panel-search">
	    <div class="col-xs-4" style="max-height: 450px; overflow-y: auto;min-width:259px">
	    	<div id="classTree" class="ztree" style="margin-top:10px;float:left;"></div>
	    </div>
<div  class="col-xs-8">
<ul class="nav nav-tabs" id="tabpage">
	<li role="presentation" class="active"><a href="#tab1content"
		id="tab1" role="tab" data-toggle="tab">分类详情</a></li>
	<li role="presentation"><a href="#tab2content" id="tab2"
		role="tab" data-toggle="tab">子分类新增</a></li>
	<li role="presentation"><a href="#tab3content" id="tab3"
		role="tab" data-toggle="tab">子分类排序</a></li>
</ul>

<div class="panel panel-default panel-search">
	
	<div class="tab-content">
		<form id="tab1content" class="tab-pane active">
			<input type="hidden" id="id" name="id" value="">
			<input type="hidden" id="goodsClassCode" name="goodsClassCode" value="${GoodsClassInfo.goodsClassCode }">
			<input type="hidden" id="classSeqNum" name="classSeqNum" value="${GoodsClassInfo.classSeqNum }">
			<div class="panel-body form-horizontal search-body" id="searchPanel1">

				<!-- start row 1-->
				<div class="form-group" data-msg-direction="r">
					<label class="col-xs-4 control-label required"> 节点名称： </label>

					<div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
						data-content="请输入50位有效字符">
						<input type="text" class="form-control" id="goodsClassName" name="goodsClassName" value="">
					</div>
				</div>

				<div class="form-group" data-msg-direction="r">
					<label class="col-xs-4 control-label "> 节点描述： </label>

					<div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
						>
						<input type="text" class="form-control" name="goodsClassDesc" value="">
					</div>
				</div>

				<div class="form-group" data-msg-direction="r">
					<label class="col-xs-4 control-label required"> 是否是最小分类： </label>

					<div class="col-xs-2-sm">
						<c:forEach items="${fns:getDictListNoBlank('DICT_IDENTITY_FLAG')}"
							var="DICT_IDENTITY_FLAG">
							<label class="radio-inline"> <input type="radio"
								name="isMinClass" value="${DICT_IDENTITY_FLAG.value}"  
								 >
								${DICT_IDENTITY_FLAG.label}
							</label>
						</c:forEach>
					</div>

				</div>

				<div class="panel-footer text-center">

					<button type="button" data-formatter="commands" id="btnClassDetailSaveSubmit"
						class="btn btn-primary">修改</button>

					<button type="button" data-formatter="commands" id="btnClassDetailDelete"
						class="btn btn-default">删除</button>
				</div>

			</div>
		</form>
		
			<div id="tab2content" class="tab-pane">
		<form id="tab2Form" class="tab-pane active">
			<div class="panel-body form-horizontal search-body" id="searchPanel1">
			<input type="hidden" id="id" name="id" value="">
			<input type="hidden" id="goodsClassCode" name="goodsClassCode" value="">
				<!-- start row 1-->

				<div class="form-group" data-msg-direction="r">
					<label class="col-xs-4 control-label required"> 节点名称： </label>

					<div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
						data-content="请输入20位有效字符">
						<input type="text" class="form-control" name="goodsClassName">
					</div>
				</div>

				<div class="form-group" data-msg-direction="r">
					<label class="col-xs-4 control-label "> 节点描述： </label>

					<div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
						data-content="请输入20位有效字符">
						<input type="text" class="form-control" name="goodsClassDesc">
					</div>
				</div>

				<div class="form-group" data-msg-direction="r">
					<label class="col-xs-4 control-label required"> 是否是最小分类： </label>

					<div class="col-xs-2-sm">
						<c:forEach items="${fns:getDictListNoBlank('DICT_IDENTITY_FLAG')}"
							var="DICT_IDENTITY_FLAG">
							<label class="radio-inline"> <input type="radio"
								name="isMinClass" value="${DICT_IDENTITY_FLAG.value}">
								${DICT_IDENTITY_FLAG.label}
							</label>
						</c:forEach>
					</div>

				</div>

				<div class="panel-footer text-center">

					<button type="button" data-formatter="commands" id="btnChildClassSaveSubmit"
						class="btn btn-primary">添加</button>

					<button type="button" data-formatter="commands" id="btnChildClassCancel"
						class="btn btn-default">清空</button>
				</div>

			</div>
			</form>
		</div>
		
		<div id="tab3content" class="tab-pane">
		<form id="tab3Form" class="tab-pane active">
			<div class="panel-body form-horizontal search-body" id="searchPanel1">
			<input type="hidden" id="id" name="id" value="${GoodsClassInfo.id }">
			<input type="hidden" id="goodsClassCode" name="goodsClassCode" value="${GoodsClassInfo.goodsClassCode }">
				<div class="form-group" data-msg-direction="r">
				<label class="col-xs-12-sm control-label ">  </label>
				<label class="col-xs-12-sm control-label ">  </label>
				<label class="col-xs-12-sm control-label ">  </label>
				<label class="col-xs-12-sm control-label ">  </label>
									<label class="col-xs-1-sm control-label ">  </label>
				
					<div class="col-xs-2-sm" data-toggle="popover" data-trigger="focus"
						data-content="">
						<button type="button" data-formatter="commands" id="btnUp"
						class="btn btn-primary" >上移</button>
					</div>
				
									<label class="col-xs-1-sm control-label ">  </label>
				
					<div class="col-xs-2-sm" data-toggle="popover" data-trigger="focus"
						data-content="">
						<button type="button" data-formatter="commands" id="btnDown"
						class="btn btn-primary" >下移</button>
					</div>
				
									<label class="col-xs-1-sm control-label">  </label>
				
					<div class="col-xs-2-sm" data-toggle="popover" data-trigger="focus"
						data-content="">
						<button type="button" data-formatter="commands" id="btnTop"
						class="btn btn-primary" >置顶</button>
					</div>
				
									<label class="col-xs-1-sm control-label ">  </label>
				
					<div class="col-xs-2-sm" data-toggle="popover" data-trigger="focus"
						data-content="">
						<button type="button" data-formatter="commands" id="btnBottom"
						class="btn btn-primary" >置底</button>
					</div>
					<label class="col-xs-12-sm control-label ">  </label>
					<label class="col-xs-12-sm control-label ">  </label>
					<label class="col-xs-12-sm control-label ">  </label>
				</div>
					
				</div>
				</form>
		</div>
					
	</div>


</div>
 </div>
 </div>
 </div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js"	defer async="true"
	data-main="<%=request.getContextPath()%>/modules/goods/goodsclass/goodsClassIndex"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="panel panel-default panel-search">
<input type="hidden" id="ctxPath"  value="${ctx}"> 
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            添加商品
        </div>
        <button class="btn btn-default btn-xs btn-wide pull-right"
				id="resetBtn2">重置</button>
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery2">查询</button>
    </div>
    <form id="searchForm2" name="searchForm2">
        <div class="panel-body form-horizontal search-body">
 			<input type="hidden"  value="emptyConditon" name="condition" id="condition">
            <div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">商品编码：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="64" value="" name="goodsCode" id="goodsCode"
                                   class="form-control" placeholder="请输入商品编码">
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">商品名称：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="64" value="" name="goodsName" id="goodsName"
                                   class="form-control" placeholder="请输入商品名称">
                        </div>
                    </div>
                </div>
                 <div class="col-xs-4">
					<div class="form-group form-group-sm">
						<label class="col-xs-5-sm control-label">商品分类：</label>
						<div class="col-xs-7-sm">
							<div class="input-group input-group-sm">
							<input type="hidden" id="goodsClassgoods2" name="goodsClassCode">
						        <input type="text" class="form-control" id="gclassg2" name="gclassg2" placeholder="点击按钮选择商品分类"
						               autocomplete="off" > <!-- 文本框 -->
						        <span class="input-group-btn">
						            <button class="btn btn-default" type="button" id="setjgbtn2">
						            <span class="glyphicon glyphicon-search"></span></button> <!-- 按钮 -->
						        </span>
						  	</div>
						</div>
					</div>
				</div>
            </div>
        </div>
    </form>
    <div class="panel-heading-gray text-right">
        <button class="btn btn-primary btn-xs" id="batchAddGoods">批量添加商品</button>
    </div>
    <div class=" panel-body">
        <table id="goodsGrid2" class="table table-hover">
            <thead class="bg-default" id="thead">
            <tr>
                <th data-header-align="left" data-align="left" data-column-id="id"
                    data-identifier="true" data-visible="false" data-visibleinselection="false" >序号
                </th>
                <th data-align="center" data-column-id="goodsCode" data-sortable="true" data-sortable-column="a.code">编码
                </th>
                <th data-column-id="goodsName" data-align="left" >名称</th>
                <th data-align="center" data-column-id="goodsClassName" >分类</th>
                <th data-align="center" data-column-id="goodsType" data-formatter="goodsType">类型</th>
                <th data-align="center" data-column-id="goodsStatus" data-formatter="goodsStatus"
                    data-sortable="true">状态
                </th>
                <th data-align="center" data-column-id="createTime" data-formatter="createTime"
                    data-sortable="true">录入时间
                </th>
                <th data-align="center" data-formatter="operation" data-sortable="false">操作</th> 
            </tr>
            </thead>
            <tbody id="tbody">
            <!--                           <tr> -->
            <!--                           <td colspan="8">正在查询</td> -->
            <!--                           </tr> -->
            </tbody>
        </table>
        
    </div>
</div>	
   
<%-- <script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/goods/goods/goods"></script> --%>
        
        <script src="<%=request.getContextPath()%>/modules/goods/goods/goodsPackageAddGoods.js"></script>
		

		
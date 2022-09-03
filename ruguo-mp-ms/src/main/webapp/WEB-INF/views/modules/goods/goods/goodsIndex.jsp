<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            商品管理
        </div>
        <button class="btn btn-default btn-xs btn-wide pull-right"
				id="resetBtn">重置</button>
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery">查询</button>
    </div>
    <form id="searchForm" name="searchForm">
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
							<input type="hidden" id="goodsClassgoods" name="goodsClassCode">
						        <input type="text" class="form-control" id="gclassg" name="gclassg" placeholder="点击按钮选择商品分类"
						               autocomplete="off" readonly="readonly"> <!-- 文本框 -->
						        <span class="input-group-btn">
						            <button class="btn btn-default" type="button" id="setjgbtn">
						            <span class="glyphicon glyphicon-search"></span></button> <!-- 按钮 -->
						        </span>
						  	</div>
						</div>
					</div>
				</div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">状态：</label>

                        <div class="col-xs-7-sm">
                            <select class="selectpicker " name="goodsStatus" id="goodsStatus"
                                    title="请选择" >
                                <c:forEach items="${fns:getDictList('DICT_GOODS_STATUS')}"
                                           var="DICT_GOODS_STATUS">
                                    <option value="${DICT_GOODS_STATUS.value}">${DICT_GOODS_STATUS.label}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
            </div>
        </div>
    </form>
    
    <div class="panel-heading-gray text-right">
        <button class="btn btn-primary btn-xs" id="addGoods">添加商品</button>
<!--         <button class="btn btn-primary btn-xs" id="addGoodsPackage">添加产品包</button> -->
        <button class="btn btn-primary btn-xs" id="onShelves" >上架</button>
        <button class="btn btn-primary btn-xs" id="offShelves">下架</button>
        <button class="btn btn-primary btn-xs" id="deleteGoods">批量删除</button>
       <!--  <button class="btn btn-primary btn-xs" id="btnImport">商品导入</button>
        <button class="btn btn-primary btn-xs" id="btnExport">商品导出</button> -->
    </div>
    <div class=" panel-body">
        <table id="goodsGrid" class="table table-hover">
            <thead class="bg-default" id="thead">
            <tr>
                <th data-header-align="left" data-align="left" data-column-id="id"
                    data-identifier="true" data-visible="false" data-visibleinselection="false" >序号
                </th>
                <!-- <th data-align="center" data-column-id="goodsCode" data-sortable="true" data-sortable-column="a.code">编码
                </th> -->
                <th data-column-id="goodsName" data-align="left" >产品名称</th>
                <th data-align="center" data-column-id="goodsClassName" >产品类别</th>
                <th data-align="center" data-column-id="serviceOrgLevelName" data-formatter="serviceOrgLevelName">服务机构级别</th>
                <th data-align="center" data-column-id="servicePersonTypeName" data-formatter="servicePersonTypeName">服务人员级别</th>
                <th data-align="center" data-column-id="goodsValidityNum" data-formatter="goodsValidityNum">服务周期</th>
                <th data-align="center" data-column-id="goodsPrice" data-formatter="goodsPrice" >服务价格</th>
                <th data-align="center" data-column-id="servicePackageName" data-formatter="servicePackageName">包含服务包</th>
                <th data-align="center" data-column-id="goodsStatus" data-formatter="goodsStatus"
                    data-sortable="true">状态
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
   
<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/goods/goods/goods"></script>
		

		
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            订单管理
        </div>
        <button class="btn btn-default btn-xs btn-wide pull-right"
				id="resetBtn">重置</button>
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btnQuery">查询</button>
    </div>
    <form id="searchForm" name="searchForm">
        <div class="panel-body form-horizontal search-body">
 			<input type="hidden" value="9" name="orderStatus" id="orderStatus">
            <div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">订单编号：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="18" value="" name="orderCode" id="orderCode"
                                   class="form-control" placeholder="请输入订单编号">
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">下单人姓名：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="50" value="" name="payPerName" id="payPerName"
                                   class="form-control" placeholder="请输入下单人姓名">
                        </div>
                    </div>
                </div>
                 <div class="col-xs-4">
                     <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">用户编码：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="32" value="" name="payPerCode" id="payPerCode"
                                   class="form-control" placeholder="请输入用户编码">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12-sm">
                <div class="col-xs-4">
                     <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">商品名称：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="32" value="" name="goodsName" id="goodsName"
                                   class="form-control" placeholder="请输商品名称">
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                     <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">支付方式：</label>

                        <div class="col-xs-7-sm">
                            <select class="selectpicker " name="orderPayWay" id="orderPayWay"
                                    title="请选择" >
                                <c:forEach items="${fns:getDictList('DICT_ORDER_PAY_WAY')}"
                                           var="DICT_ORDER_PAY_WAY">
                                    <option value="${DICT_ORDER_PAY_WAY.value}">${DICT_ORDER_PAY_WAY.label}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <!--  
                <div class="col-xs-4">
	                <div class="form-group form-group-sm">
	                    <label class="col-xs-5-sm control-label">来源渠道：</label>
	
	                    <div class="col-xs-7-sm">
	                        <select class="selectpicker" name="orgCode" id="orgCode" 
	                                    title="请选择" >
	                              <option></option>
	                             <c:forEach items="${organizationPullList}"
	                                        var="organizationPullList">
	                                 <option value="${organizationPullList.code}">${organizationPullList.name}</option>
	                             </c:forEach>
	                         </select>
	                    </div>
	                </div>
                </div>
                -->
                <div class="col-xs-4">
	                <div class="form-group form-group-sm">
	                    <label class="col-xs-5-sm control-label">下单开始日期：</label>
						<div class="col-xs-7-sm">
	                        <div class="input-group input-group-sm date form_datetime"
	                              data-date-format="yyyy-mm-dd">
	                            <input class="form-control" id="startTime" name="startTime"
	                                   type="text" autocomplete="off" placeholder="请选择下单开始日期" readonly="readonly">
	                            <span class="input-group-addon"><span
	                                    class="glyphicon glyphicon-calendar"></span></span>
	                        </div>
	                    </div>
	                 </div>
               </div>
            </div>
           
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">下单结束日期：</label>
					<div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime"
                              data-date-format="yyyy-mm-dd">
                            <input class="form-control" id="endTime" name="endTime"
                                   type="text" autocomplete="off" placeholder="请选择下单择结束日期" readonly="readonly">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                 </div>
            </div>
            
        </div>
    </form>
    <div class="panel-heading-gray">
	    <div class="tab-pane">
	        <div class="panel-heading-search">
	            <div class="panel-heading-search-group tab2">
	                <a href="#" id="tabAllQuery" class="active" data-toggle="tab">全部订单</a>
	                <a href="#" id="tabPaidQuery" data-toggle="tab">已付款</a>
	                <a href="#" id="tabUnpaidQuery" data-toggle="tab">未付款</a>
	                <a href="#" id="tabCanceledQuery" data-toggle="tab">已取消</a>
	                <a href="#" id="tabApplyForRefundQuery" data-toggle="tab">申请退款</a>
	                <a href="#" id="tabRefundQuery" data-toggle="tab">退款中</a>
	                <a href="#" id="tabRefundedQuery" data-toggle="tab">已退款</a>
	            </div>
	     	</div>
	     </div>
	 </div>
     <div class="panel-body">
          <div class="tab-pane active">
	        <table id="orderGrid" name="orderGrid" class="table table-hover">
	            <thead class="bg-default" id="thead">
	            <tr>
	            <th data-header-align="left" data-align="left" data-column-id="approvalStatus"
                    data-identifier="true" data-visible="false" data-visibleinselection="false" >审核状态
                </th>
	                <th data-header-align="center" data-align="center" data-column-id="orderCode" data-column-status="orderStatus" data-column-code="orderCode" data-identifier="true">订单编号</th>
	                <th data-header-align="center" data-align="center" data-column-id="goodsName">商品名称</th>
	                <th data-header-align="center" data-align="center" data-column-id="payPerName">下单人姓名</th>
	                <th data-header-align="center" data-align="center" data-column-id="orderPayWay" data-formatter="orderPayWay">支付方式</th>
	                <th data-header-align="center" data-align="center" data-column-id="orderStatus" data-formatter="orderStatus">支付状态</th>
	                <th data-header-align="center" data-align="center" data-column-id="createTime">下单时间</th>
	                <th data-header-align="center" data-align="center" data-column-id="goodsPrice" data-formatter="goodsPrice">金额</th>
	                <th data-header-align="center" data-align="center" data-column-id="goodsDiscountPrice" data-formatter="goodsDiscountPrice">优惠金额</th>
	                <th data-header-align="center" data-align="center" data-formatter="operation">操作</th> 
	            </tr>
	            </thead>
	            <tbody id="tbody"></tbody>
	        </table>
	    </div>
   	</div>
</div>
<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/order/orderquery/orderQueryOperation"></script>
		

		
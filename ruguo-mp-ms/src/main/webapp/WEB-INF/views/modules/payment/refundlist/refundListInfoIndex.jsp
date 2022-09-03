<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            退款列表
        </div>
        <button class="btn btn-default btn-xs btn-wide pull-right"
				id="resetBtn">重置</button>
        <button type="button" class="btn btn-primary btn-xs btn-wide pull-right search-button" id="btnQuery">查询
        </button>
    </div>
    <form id="refundListInfoForm" name="refundListInfoForm">
        <div class="panel-body form-horizontal search-body" id="searchPanel3">

            <!-- start row 1-->
            <div class="col-xs-6">
                <div class="form-group form-group-sm">
                    <label class="search-label-left control-label">单号：</label>
                    <div class="search-input-right">
                        <input type="text" maxlength="64" value="" name="refundId"
                               class="form-control" placeholder="请输入退款单号">
                    </div>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group form-group-sm">
                    <label class="search-label-right control-label">退款方式：</label>
                    <div class="search-input-right">
                        <select class="selectpicker" name="refundWay" id="refundWay" title="请选择" data-style="btn-sm btn-default">
                             <c:forEach items="${fns:getDictList('DICT_ORDER_PAY_WAY')}"
                                       var="DICT_ORDER_PAY_WAY">
                                <option value="${DICT_ORDER_PAY_WAY.value}">${DICT_ORDER_PAY_WAY.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <!-- end row1-->
            <!-- start row 2-->
            <div class="col-xs-6">
                <div class="form-group form-group-sm">
                    <label class="search-label-left control-label">退款状态：</label>
                    <div class="search-input-left">
                        <select class="selectpicker" name="refundStatus" id="refundStatus" title="请选择" data-style="btn-sm btn-default">
                            <c:forEach items="${fns:getDictList('DICT_SUCCESS_OR_FAILURE')}"
                                       var="DICT_SUCCESS_OR_FAILURE">
                                <option value="${DICT_SUCCESS_OR_FAILURE.value}">${DICT_SUCCESS_OR_FAILURE.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group form-group-sm">
                    <label class="search-label-right control-label">交易时间：</label>

                    <div class="search-input-right form-inline" data-msg-direction="r">
                        <div class="input-group input-group-sm date form_datetime" data-date-format="yyyy-mm-dd" data-toggle="popover" data-trigger="focus">
                            <input class="form-control" id="startRefundTime" name="startRefundTime"
                                   type="text" autocomplete="off" placeholder="开始日期" readonly="readonly">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span> </span>
                        </div>
                        &nbsp;-&nbsp;
                        <div class="input-group input-group-sm date form_datetime" data-date-format="yyyy-mm-dd" data-toggle="popover" data-trigger="focus">
                            <input class="form-control" data-trigger="focus" id="endRefundTime" name="endRefundTime"
                                   type="text" autocomplete="off" placeholder="结束日期" readonly="readonly">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
	<div class="panel panel-default panel-search">
	    <div class=" panel-body">
	        <table id="refundListInfoGrid" class="table table-hover">
	            <thead class="bg-default" id="thead">
	            <tr>
	                <th data-header-align="left" data-align="left" data-column-id="id" data-formatter="refundListInfo.id"
	                    data-identifier="false" data-visible="false" data-visibleinselection="false">
	                </th>
	                <th data-align="center" data-column-id="refundId" data-formatter="refundListInfo.refundId">退款单号
	                </th>
	                <th data-align="center" data-column-id="paymentId" data-formatter="refundListInfo.paymentId">支付单号
	                </th>
	                <th data-align="center" data-column-id="goodsPaymentId" data-formatter="goodsPaymentId">商品订单号
	                </th>
	                <th data-column-id="refundWay" data-formatter="refundListInfo.refundWayCode" data-align="center">退款方式</th>
	                <th data-align="center" data-column-id="refundAmount" data-formatter="refundListInfo.refundAmount">退款金额</th>
	                <th data-align="center" data-column-id="refundStatus" data-formatter="refundListInfo.refundStatus">退款状态</th>
	                <th data-align="center" data-column-id="refundTime" data-formatter="refundListInfo.refundTime">交易时间</th>
	                <th data-align="center" data-formatter="operation" data-sortable="false">操作</th> 
	            </tr>
	        </table>   
	    </div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/payment/refundlist/refundListInfoIndex"></script>
		

		
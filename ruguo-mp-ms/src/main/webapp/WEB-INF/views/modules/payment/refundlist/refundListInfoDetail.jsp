<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">  
        <form id="tab1content" class="tab-pane active">         
            <div class="panel-body form-horizontal search-body" id="searchPanel1">
                <!-- start row 1-->
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">商家号：</label>
                        <span class="control-label-text">
	                         ${refundListInfoResult.refundListInfo.merchantId}
	                    </span>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label">服务号：</label>
                        <span class="control-label-text">
	                         ${refundListInfoResult.refundListInfo.publicAccountId}
	                    </span>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">退款单号：</label>
                        <span class="control-label-text">
	                         ${refundListInfoResult.refundListInfo.refundId}
	                    </span>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label">支付单号：</label>
                        <span class="control-label-text">
	                         ${refundListInfoResult.refundListInfo.paymentId}
	                    </span>
                    </div>
                </div>
                <!-- end row1-->
                <!-- start row 2-->
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label ">商品订单号：</label>
                        <span class="control-label-text">
	                         ${refundListInfoResult.refundListInfo.orderCode}
	                    </span>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label">退款金额：</label>                  
                        <span class="control-label-text">
	                         ${refundListInfoResult.refundListInfo.refundAmount}
	                    </span>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label ">交易时间：</label>
                            <span class="control-label-text">
	                            <fmt:formatDate value="${refundListInfoResult.refundListInfo.refundTime}" pattern="yyyy-MM-dd HH:mm:ss"/>   
	                        </span>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label">退款方式：</label>
                        <span class="control-label-text">
	                         ${fns:getDictLabel(refundListInfoResult.refundListInfo.refundWayCode,'DICT_ORDER_PAY_WAY','')}
	                    </span>
                    </div>
                </div>
                <div class="col-xs-7">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">退款状态：</label>                         
                        <span class="control-label-text">
	                        ${fns:getDictLabel(refundListInfoResult.refundListInfo.refundStatus,'DICT_SUCCESS_OR_FAILURE','')}
	                    </span>
                    </div>
                </div>
                <div class="col-xs-7">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">退款返回码：</label>
                        <span class="control-label-text">
	                         ${refundListInfoResult.refundListInfo.refundReturnCode}
	                    </span>
                    </div>
                </div>
                <div class="col-xs-8">
                    <div class="form-group">
                        <label class="search-label-left control-label">退款返回描述：</label>    
                        <span class="control-label-text">
	                         ${refundListInfoResult.refundListInfo.refundReturnMessage}
	                    </span>         
                    </div>
                </div>
            </div>
        </form>
        <hr>
        <div class="col-xs-12 text-center">
            <button class="btn btn-default" id="btnClose">
               关闭
            </button>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/modules/payment/paymentlist/payMentListInfoDetail.js"></script>
            
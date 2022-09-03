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
			              ${payMentListInfoResult.payMentListInfo.merchantId}
			         </span>
			        </div>
			    </div>
			    <div class="col-xs-6">
			        <div class="form-group form-group-sm">
			            <label class="search-label-right control-label">服务号：</label>
			            <span class="control-label-text">
			              ${payMentListInfoResult.payMentListInfo.publicAccountId}
			         </span>
			        </div>
			    </div>
			    <!-- end row1-->
			    <!-- start row 2-->
			    <div class="col-xs-6">
			        <div class="form-group form-group-sm">
			            <label class="search-label-left control-label ">支付单号：</label>
			            <span class="control-label-text">
			              ${payMentListInfoResult.payMentListInfo.paymentId}
			         </span>
			        </div>
			    </div>
			    <div class="col-xs-6">
			        <div class="form-group form-group-sm">
			            <label class="search-label-right control-label ">商品订单号：</label>
			            <span class="control-label-text">
			              ${payMentListInfoResult.payMentListInfo.orderCode}
			         </span>
			        </div>
			    </div>
			    <div class="col-xs-6">
			        <div class="form-group form-group-sm">
			            <label class="search-label-left control-label">支付金额：</label>                  
			            <span class="control-label-text">
			              ${payMentListInfoResult.payMentListInfo.payAmount}
			         </span>
			        </div>
			    </div>
			    <div class="col-xs-6">
			        <div class="form-group form-group-sm">
			            <label class="search-label-right control-label ">交易时间：</label>
			                <span class="control-label-text">
			                 <fmt:formatDate value="${payMentListInfoResult.payMentListInfo.transactionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>   
			             </span>
			        </div>
			    </div>
			    <div class="col-xs-6">
			        <div class="form-group form-group-sm">
			            <label class="search-label-left control-label">支付状态：</label>
			            <span class="control-label-text">
			              ${fns:getDictLabel(payMentListInfoResult.payMentListInfo.payStatus,'DICT_SUCCESS_OR_FAILURE','')}
			         </span>
			        </div>
			    </div>
			    <div class="col-xs-6">
			        <div class="form-group form-group-sm">
			            <label class="search-label-right control-label">支付方式：</label>
			            <span class="control-label-text">
			              ${fns:getDictLabel(payMentListInfoResult.payMentListInfo.payWayCode,'DICT_ORDER_PAY_WAY','')}
			            </span>
			        </div>
			    </div>
			    <div class="col-xs-7">
			        <div class="form-group form-group-sm">
			            <label class="search-label-left control-label">支付返回码：</label>
			            <span class="control-label-text">
			              ${payMentListInfoResult.payMentListInfo.payReturnCode}
			         </span>
			        </div>
			    </div>
			    <div class="col-xs-8">
			        <div class="form-group">
			            <label class="search-label-left control-label">支付返回描述：</label>    
			            <span class="control-label-text">
			              ${payMentListInfoResult.payMentListInfo.payReturnMessage}
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
		      
<script src="<%=request.getContextPath()%>/modules/payment/refundlist/refundListInfoDetail.js"></script>
            
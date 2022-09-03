<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
    <div class="col-xs-12">
    	<div class="panel-body form-horizontal">
        	<form id="orderBasicInfoForm" name="orderBasicInfoForm">
            	<input type="hidden" id="orderId" name="orderId" value="${order.id}">
            	<input type="hidden" id="orderCode" name="orderCode" value="${order.orderCode}">
                 <div class="form-group">
                    <label class="col-xs-3 control-label">
                       订单编号：
                    </label>

                    <div class="col-xs-2-sm">
                        <span class="control-label-text">
                            ${order.orderCode}
                        </span>
                    </div>
                    <label class="col-xs-3 control-label">
		下单时间：
                    </label>

                    <div class="col-xs-3-sm">
                        <span class="control-label-text"><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                             
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                       支付状态：
                    </label>

                    <div class="col-xs-2-sm">
                        <span class="control-label-text">
                            ${fns:getDictLabel(order.orderStatus,'DICT_ORDER_STATUS','')}
                        </span>
                    </div>
                    <label class="col-xs-3 control-label">
		付款方式：
                    </label>

                    <div class="col-xs-3-sm">
                        <span class="control-label-text">
                            ${fns:getDictLabel(order.orderPayWay,'DICT_ORDER_PAY_WAY','')}
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                      支付单号：
                    </label>

                    <div class="col-xs-2-sm">
                        <span class="control-label-text">
                            ${order.outTradeNo}
                        </span>
                    </div>
                    <label class="col-xs-3 control-label">
		支付时间：
                    </label>

                    <div class="col-xs-3-sm">
                        <span class="control-label-text"><fmt:formatDate value="${order.transactionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                       付款人：
                    </label>

                    <div class="col-xs-2-sm">
                        <span class="control-label-text">
                            ${order.payPerName}
                        </span>
                    </div>
                    <label class="col-xs-3 control-label">
		联系电话：
                    </label>

                    <div class="col-xs-3-sm">
                        <span class="control-label-text">
                            ${order.payPerMoblie}
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                     服务对象姓名：
                    </label>

                    <div class="col-xs-2-sm">
                        <span class="control-label-text">
                            ${order.relaPerName}
                        </span>
                    </div>
                    <label class="col-xs-3 control-label">
		与购买人关系：
                    </label>

                    <div class="col-xs-3-sm">
                        <span class="control-label-text">
                            ${order.relaName}
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                       服务对象证件：
                    </label>

                    <div class="col-xs-2-sm">
                        <span class="control-label-text">
                            ${order.relaCertificateTypeName}
                        </span>
                    </div>
                    <label class="col-xs-3 control-label">
		服务对象证件号：
                    </label>

                    <div class="col-xs-3-sm">
                        <span class="control-label-text">
                            ${order.relaCertificateNo}
                        </span>
                    </div>
                </div>
                <div class="form-group">
		            <label class="col-xs-3 control-label">
		                收件地址：
		            </label>
		            <div class="col-xs-8-sm">
			          <span class="control-label-text">
			               ${order.addrCode}
			          </span>
		            </div>
		        </div>
                <div class="form-group">
		            <label class="col-xs-3 control-label">
		                备注：
		            </label>
		            <div class="col-xs-8-sm">
			          <span class="control-label-text">
			               ${order.remark}
			          </span>
		            </div>
		        </div>
        	</form>
        	<div class="panel-heading-blue">
		        <div class="panel-title-blue">
		            商品列表
		        </div>
		    </div>
	     <div class=" panel-body form-horizontal">
	     	<table id="orderGoodsGrid" class="table table-hover">
	            <thead class="bg-default" id="thead">
	            <tr>
	                <th data-header-align="center" data-align="center" data-column-id="goodsCode" data-identifier="true">商品编码</th>
	                <th data-header-align="center" data-align="center" data-column-id="goodsName">商品名称</th>
	                <th data-header-align="center" data-align="center" data-column-id="goodsType" data-formatter="goodsType">商品类型</th>
	                <th data-header-align="center" data-align="center" data-column-id="goodsPrice" data-formatter="goodsPrice">金额</th>
		            <th data-header-align="center" data-align="center" data-column-id="goodsDiscountPrice" data-formatter="goodsDiscountPrice">优惠金额
	            </tr>
	            </thead>
	            <tbody id="tbody"></tbody>
	        </table>
	     </div>
	     <div class="panel-heading-blue">
		        <div class="panel-title-blue">
		           物流公司
		        </div>
		    </div>
		    <div class="form-group"></div>
		    <table style="margin-left: 200px;">
		    <tr style="width:100px;height:50px;margin-left:50px;border: 1px solid #777; ">
		    <td style="width: 200px;height: 50px;border: 1px solid #777;" align="center">
		    订单阶段
		    </td>
		    <td style="width: 200px;height: 50px;border: 1px solid #777;" align="center">
		    物流公司
		    </td>
		    <td style="width: 200px;height: 50px;border: 1px solid #777;" align="center">
		    物流单号
		    </td>
		    </tr>
		     <tr style="width:100px;height:50px;margin-left:20px;border: 1px solid #777; ">
		    <td style="width: 200px;height: 50px;border: 1px solid #777;" align="center">
		    集散中心发送至用户采样盒
		    </td>
		    <td style="width: 200px;height: 50px;border: 1px solid #777;" align="center">
		    顺风快递
		    </td>
		    <td style="width: 200px;height: 50px;border: 1px solid #777;" align="center">
		    123456789
		    </td>
		    </tr>
		    
		    </table>
	     
	     <c:if test='${not empty refundPersonName}'>
	      <div class="panel-heading-blue">
			        <div class="panel-title-blue">
			          退单申请
			        </div>
			    </div>
			    <div class="form-group"></div>
			    <div class="form-group">
                    <label class="col-xs-3 control-label">
                      申请发起人：
                    </label>

                    <div class="col-xs-2-sm">
                    <input type="text" class="form-control" name="refundPerson" value="${refundPersonName}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group">
		            <label class="col-xs-3 control-label">
		                发起时间：
		            </label>
			          <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
		                          data-placement="right">
		                 <input type="text" class="form-control" name="refundTime"  value="<fmt:formatDate value="${orderRefund.refundTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly">
		             </div>
		        </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                      退款金额：
                    </label>
                    
                    <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                     data-content="请输入有效数字">
                    <input type="text" class="form-control" name="refundAmount"  value="${orderRefund.refundAmount}" readonly="readonly">
                </div>
                </div>
			     <div class="form-group">
		            <label class="col-xs-3 control-label">
		                退款原因：
		            </label>
			          <div class="col-xs-7-sm" data-toggle="popover" data-trigger="focus"
		                         data-content="最大可输入500个汉字" data-placement="right">
		                  <textarea id="refundReason" name="refundReason" class="form-control"
		                            onpropertychange="if(value.length>500) value=value.substr(0,500)"
		                            rows="3" readonly="readonly">${orderRefund.refundReason}</textarea>
		             </div>
		        </div>
			     </c:if>
			     
			     <c:if test='${not empty operationExaminer}'>
			     <hr>
			     <div class="form-group">
                    <label class="col-xs-3 control-label">
                      运营审核人：
                    </label>

                   <div class="col-xs-2-sm">
                     <input type="text" class="form-control" name="refundPerson" value="${operationExaminer}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group">
		            <label class="col-xs-3 control-label">
		                运营审核时间：
		            </label>
			          <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
		                          data-placement="right">
		                 <input type="text" class="form-control" name="operationExamineTime"  value="<fmt:formatDate value="${orderRefund.operationExamineTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly">
		             </div>
		        </div>
		        <div class="form-group">
		        	<label class="col-xs-3 control-label ">
		                退款审核：
		            </label>
		            <div class="col-xs-2-sm">
	                    <c:forEach items="${fns:getDictListNoBlank('DICT_AGREE_OR_NOT')}" var="DICT_AGREE_OR_NOT">
							<label class="radio-inline">
		                        <input type="radio" name="approvalStatus" value="${DICT_AGREE_OR_NOT.value}" 
		                        <c:if test='${orderRefund.approvalStatus eq DICT_AGREE_OR_NOT.value}'> checked="checked"  </c:if> disabled="disabled"> ${DICT_AGREE_OR_NOT.label}
		                    </label>
						</c:forEach>
	                </div>
		        </div>
		       <div class="form-group">
		            <label class="col-xs-3 control-label ">
		                审核意见：
		            </label>
			          <div class="col-xs-7-sm" data-toggle="popover" data-trigger="focus"
		                         data-content="最大可输入500个汉字" data-placement="right">
	                     <textarea id="approvalOpinion" name="approvalOpinion" class="form-control"
	                               onpropertychange="if(value.length>500) value=value.substr(0,500)"
	                               rows="3"  readonly="readonly">${orderRefund.approvalOpinion}</textarea>
		             </div>
		        </div>
	     </c:if>
        </div>
        
        
        
        
        <div class="form-group"></div>
        <div class="col-xs-12 text-center">
            <button class="btn btn-default" id="btnClose">
               关闭
            </button>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/modules/order/orderquery/orderBasicInfoDetail.js"></script>	

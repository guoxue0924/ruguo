<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">
<table class="table table-bordered">
                <tr>
                    <td>
                        <div class="col-xs-14">

                           <div class="col-xs-14">
                            <div class="panel-body form-horizontal">

                                <div class="page-header">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <h4><strong>订单统计</strong></h4>
                                        </div>
                                        
                                    </div>
                                </div>

                              <!--第一行  -->
                                           <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                       订单：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        ${home.orderCount }个
                                    </span>
                    </div>
                    
                    <label class="col-xs-2-sm control-label">
                        总金额：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">

                                        ${home.orderSum }元
                                    </span>
                    </div>
                </div>
                
                <!--第二行  -->
                <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                       已付款：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.payCount }个
                                    </span>
                    </div>
                    
                    <label class="col-xs-2-sm control-label">
                        已付款金额：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">

                                        ${home.paySum }元
                                    </span>
                    </div>
                </div>
                 
                 <!--第三行  -->
                           <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                        待付款：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                         ${home.noPayCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                        待付款金额：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                    ${home.noPaySum }元
                                    </span>
                    </div>
                    
                </div>
                
                  <!--第四行  -->
                           <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                        已取消：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.cancelCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                        已取消金额：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        ${home.cancelSum }元
                                    </span>
                    </div>
                    
                </div>
                 <!--第五行  -->
                           <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                        申请退款：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                         ${home.refundApplicationCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                       申请退款金额：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.refundApplicationSum }元
                                    </span>
                    </div>
                    
                </div>
                
                <!--第六行  -->
                           <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                        退款中：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.refundingCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                       退款中金额：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.refundingSum }元
                                    </span>
                    </div>
                    
                </div>
                
                <!--第七行  -->
                           <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                        已退款：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.refundedCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                       已退款金额：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.refundedSum }元
                                    </span>
                    </div>
                    
                </div>
                <!--第八行  -->
                  <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                        已关闭：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.closedCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                       已关闭金额：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.closedSum }元
                                    </span>
                    </div>
                    
                </div>
                               


                            </div>

                        </div>
                        </div>
                    </td>
                </tr>
</table>
</div>

<div class="panel panel-default">
<table class="table table-bordered">
                <tr>
                    <td>
                        <div class="col-xs-14">

                           <div class="col-xs-14">
                            <div class="panel-body form-horizontal">

                                <div class="page-header">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <h4><strong>商品统计</strong></h4>
                                        </div>
                                        <!--<div class="col-xs-8 text-right">
                                            <button type="button" data-formatter="commands"
                                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                                            </button>
                                        </div>-->
                                    </div>
                                </div>

                                           <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                        商品总数：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        ${home.goodsCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                       上架总数：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        ${home.goodsOnShelvesCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                        下架总数：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">

                                        ${home.goodsOffShelvesCount }个
                                    </span>
                    </div>
                </div>

                               


                            </div>

                        </div>
                        </div>
                    </td>
                </tr>
</table>
</div>

<div class="panel panel-default">
<table class="table table-bordered">
                <tr>
                    <td>
                        <div class="col-xs-14">

                           <div class="col-xs-14">
                            <div class="panel-body form-horizontal">

                                <div class="page-header">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <h4><strong>注册用户统计</strong></h4>
                                        </div>
                                    </div>
                                </div>

                            
                                           <div class="form-group">
                    <label class="col-xs-2-sm control-label">
                        注册用户总数：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                       ${home.userCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                        身份认证数量：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                      ${home.idConfirmUserCount }个
                                    </span>
                    </div>
                    <label class="col-xs-2-sm control-label">
                        未认证数量：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">

                                     ${home.idUnconfirmUserCount }个
                                    </span>
                    </div>
                </div>
                               


                            </div>

                        </div>
                        </div>
                    </td>
                </tr>
</table>
</div>
<script src="<%=request.getContextPath()%>/plugins/require/require.js"
	defer async="true"
	data-main="<%=request.getContextPath()%>/modules/goods/goodsclass/goodsClassIndex"></script>

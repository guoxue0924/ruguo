<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            服务管理
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
                        <label class="col-xs-5-sm control-label">用户名：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="18" value="" name="memberName" id="memberName"
                                   class="form-control" placeholder="请输入用户名">
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">姓名：</label>

                        <div class="col-xs-7-sm">
                            <input type="text" maxlength="50" value="" name="relaPerName" id="relaPerName"
                                   class="form-control" placeholder="请输入姓名">
                        </div>
                    </div>
                </div>
                 <div class="col-xs-4">
                     <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">距离服务到期时间：</label>

                       <!--  <div class="col-xs-7-sm">
                            <input type="text" maxlength="32" value="" name="disServiceEndTime" id="disServiceEndTime"
                                   class="form-control" placeholder="请输入用户编码">
                        </div> -->
                        
                         <div class="col-xs-7-sm">
                            <select class="selectpicker " name="disServiceEndTime" id="disServiceEndTime"
                                    title="请选择" >
                                <c:forEach items="${fns:getDictList('DICT_DISTANCE_DATE')}"
                                           var="DICT_DISTANCE_DATE">
                                    <option value="${DICT_DISTANCE_DATE.value}">${DICT_DISTANCE_DATE.label}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
           
            <div class="col-xs-4">
               <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">购买服务起止时间：</label>
					<div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime"
                              data-date-format="yyyy-mm-dd">
                            <input class="form-control" id="startTime" name="startTime"
                                   type="text" autocomplete="off" placeholder="请选择服务开始日期" readonly="readonly">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                 </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-1-sm control-label">-----</label>
					<div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime"
                              data-date-format="yyyy-mm-dd">
                            <input class="form-control" id="endTime" name="endTime"
                                   type="text" autocomplete="off" placeholder="请选择服务择结束日期" readonly="readonly">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                 </div>
            </div>
            
        </div>
    </form>
   
     <div class="panel-body">
          <div class="tab-pane active">
	        <table id="serviceGrid" name="serviceGrid" class="table table-hover">
	            <thead class="bg-default" id="thead">
	            <tr>
	            <th data-header-align="center" data-align="center" data-column-id="serviceCode"  data-column-code="serviceCode" data-identifier="true">服务编号</th>
	            <th data-header-align="center" data-align="center" data-column-id="memberName"
                    data-identifier="true" >用户名
                </th>
	                <th data-header-align="center" data-align="center" data-column-id="relaPerName" >姓名</th>
	                <th data-header-align="center" data-align="center" data-column-id="relaPerMobilePhone">手机号</th>
	                <th data-header-align="center" data-align="center" data-column-id="goodsName">服务名称</th>
	                <th data-header-align="center" data-align="center" data-column-id="serviceStartTime" >购买服务日期</th>
	                <th data-header-align="center" data-align="center" data-column-id="serviceCycle">服务周期</th>
	                <th data-header-align="center" data-align="center" data-column-id="serviceEndTime" >服务截止日期</th>
	                <th data-header-align="center" data-align="center" data-formatter="operation">操作</th> 
	            </tr>
	            </thead>
	            <tbody id="tbody"></tbody>
	        </table>
	    </div>
   	</div>
</div>
<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/service/service/myServiceIndex"></script>
		

		
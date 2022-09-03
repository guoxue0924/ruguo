<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default">

    <form id="searchForm" name="searchForm">
    	<div class="panel-body form-horizontal search-body">
            <div class="col-xs-12-sm">
                <div class="col-xs-4">
                    <div class="form-group form-group-sm">
                        <label class="col-xs-5-sm control-label">售价：</label>

                        <div class="col-xs-7-sm">
                         	<label class="col-xs-7-sm control-label" id="goodsPrice">${goods.goodsPrice} 元</label>
                        </div>
                    </div>
                </div>
             </div>
             <input type="hidden" id="code" value="${goods.goodsCode}">
    	</div>
    </form>   
    
     <table id="contentTable1" class="table table-striped table-condensed">
		<thead>
		<tr></tr>
			<tr>
			    <th style="display:none;">id</th>
				<th style="display:none;">会员等级编码</th>
				<th>会员等级</th>
				<th>会员折扣</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${memberLevelInfoPullList}" var="memberLevelInfoPullList">
			<tr>	
			<td style="display:none;">
					${memberLevelInfoPullList.id}
				</td>
				<td style="display:none;">
					${memberLevelInfoPullList.memberLevelCode}
				</td>
				<td align="center">
					${memberLevelInfoPullList.memberLevelName}
				</td>
				<td align="center">
					<input type="text" id="memberRate" value="${memberLevelInfoPullList.memberRate}">
				</td>
				
			</tr>
			</c:forEach>
		</tbody>
     </table>         
    
    <div class="panel-footer text-center">
       
        <button type="button" data-formatter="commands"
                id="btnSavePost" class="btn btn-primary">
            保存
        </button>
		<button type="button" data-formatter="commands"
                id="btnCancel" class="btn btn-default">
            取消
        </button>
</div>
</div>
        
        <script src="<%=request.getContextPath()%>/modules/goods/goods/editGoodsPriceManage.js"></script>
		

		
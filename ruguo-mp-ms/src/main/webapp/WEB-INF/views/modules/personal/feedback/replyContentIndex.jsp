<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<div class="panel-footer text-center">
		
            <form action="" id="replyContentForm" name="replyContentForm">
			<div class="form-group form-group-sm">
				<textarea  cols="" maxlength="600"  style="width:500px;height:200px;"
				 name="replyContent"  id="inputReply"  placeholder="请输入处理内容。。。"  data-content="请输入500位有效字符">${replyContent }</textarea>
			</div>
            </form>
            
            <input id="hiddenInput" value="${replyContent }" type="hidden">
            <input id="hiddenInputId" value="${id }" type="hidden">
            <br>
			<button type="button" data-formatter="commands" id="btnSaveSubmit"
				class="btn btn-primary">回复</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" data-formatter="commands" id="btnClose"
				class="btn btn-default">取消</button>
				</div>
		</div>
	</div>
</div>

<script
	src="<%=request.getContextPath()%>/modules/personal/feedback/replyContentIndex.js"></script>

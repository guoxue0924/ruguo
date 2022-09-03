<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<style>
  .ue-container {
	   width: 60%;
	   margin: 0 auto;
	   margin-top: 3%;
	   padding: 20px 40px;
	   border: 1px solid #ddd;
	   background: #fff;
   }
	</style>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="panel panel-default panel-search">
<input type="hidden" id="ctxPath"  value="${ctx}"> 
    <form id="tagsForm" name="tagsForm">
    <input type="hidden"  class="goodsCodeTag"  value="${goodsCode }" >
        <div class="ue-container">
	    <select multiple="multiple" size="10" name="doublebox" class="demo">
        </select>
	</div>
    </form>
    <div class="panel-footer text-center">
       
        <button type="button" data-formatter="commands"
                id="btnSavetagsSubmit" class="btn btn-primary">
            保存
        </button>
        
		
 </div>	   
</div>	
   
<%-- <script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/goods/goods/goods"></script> --%>
        
        <script src="<%=request.getContextPath()%>/modules/goods/goods/editTags.js"></script>
		

		
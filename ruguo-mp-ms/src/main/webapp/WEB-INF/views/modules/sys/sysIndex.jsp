<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<div class="panel panel-default" style="min-height:400px;">
	<div class="panel-heading-blue">
              <div class="panel-title-blue">
                  <i class="fa fa-envelope" aria-hidden="true"></i>
                  我的通知（<span class="" id="msgcount">0</span>）
              </div>
          </div>
	<div class="panel-body">
		<!-- <table id="workhome" class="table table-hover table-condensed">
			<thead class="hide" id="workhomethead">
				<tr>
					<th data-column-id="msgid" data-identifier="true" data-visible="false" data-visibleinselection="false">msgid</th>
					<th data-width="180px" data-column-id="msgtypename" data-formatter="msgtypename" data-visibletitle="true" data-align="left">类型</th>
					<th data-column-id="msgbody" data-align="left">内容</th>
					<th data-width="100px" data-column-id="sendtime" data-formatter="sendtime"  data-visibletitle="true" data-align="right">提醒时间</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<tr>
					<td class="text-center">正在查询...</td>
				</tr>
			</tbody>
		</table> -->
		<div class="col-xs-5 col-xs-offset-4"><h3 class="text-primary">系统登录成功，欢迎使用！</h3></div>
<!-- 		<div class="col-xs-4 col-xs-offset-5"><h3 class="text-danger"><button type="button" id="to_sys_old" class="btn btn-success">旧版入口</button></div> -->
		
	</div>
</div>
<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true" data-main="<%=request.getContextPath()%>/modules/sys/sysIndex"></script>
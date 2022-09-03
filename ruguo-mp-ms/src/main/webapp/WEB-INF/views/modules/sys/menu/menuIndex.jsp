<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            菜单管理
        </div>
    </div>
    <div class=" panel-body form-horizontal">
    	<div class="form-group">
			<!--                        <label class="col-xs-2 control-label">-->
			<!--                        目录名称：-->
			<!--                        </label>-->
			<div class="col-xs-5">
				<div class="input-group ">
					<input type="text" id="serachvalue" class="form-control"
						placeholder="请输入查询条件">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" id="serach">
							查询
						</button>
						<button class="btn btn-default" type="button" id="reset">
							重置
						</button> 
					</span>
				</div>
			</div>
            <div class="col-xs-7 text-right">    
	            <shiro:hasPermission name="sys:menu:edit">
					<button type="button" class="btn btn-primary" id="addMenu"
						title="添加菜单">添加菜单
					</button>
					<button type="button" class="btn btn-primary" id="updateSort"
						title="添加菜单">保存排序
					</button>
				</shiro:hasPermission>
				<button type="button" class="btn btn-default" id="expendAll"
					title="全部展开">
					<i class="fa fa-angle-double-down" aria-hidden="true"></i>
				</button>
				<button type="button" class="btn btn-default" id="collapseAll"
					title="全部关闭">
					<i class="fa fa-angle-double-up" aria-hidden="true"></i>
				</button>
			</div>
		</div>
        <form id="listForm" method="post">
	        <table class="table table-hover bootgrid-table" id="menulist">
				<thead class="bg-default">
					<tr>
						<th width="190px">
							菜单名称
						</th>
						<th width="">
							链接
						</th>
						<th width="60px">
							可见
						</th>
						<th width="">
							权限标识
						</th>
						<th width="80px">
							排序
						</th>
						<th width="150px">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="menu">
						<tr data-id="${menu.id}" data-pid="${menu.parent.id ne '1'? menu.parent.id:'root'}">
							<td >${menu.name}</td>
							<td title="${menu.href}">${menu.href}</td>
							<td class="text-center">${menu.isShow eq '1'?'<span class="text-success">显示</span>':'<span class="text-danger">隐藏</span>'}</td>
							<td title="${menu.permission}">${menu.permission }</td>
							<td class="text-center">
								<shiro:hasPermission name="sys:menu:edit">
									<input type="hidden" name="ids" value="${menu.id}"/>
									<input type="text" name="sorts" value="${menu.sort}" style="width:50px;margin:0;padding:0;text-align:center;"autocomplete="off">
								</shiro:hasPermission>
								<shiro:lacksPermission name="sys:menu:edit">
									${menu.sort}
								</shiro:lacksPermission>
							</td>
							<shiro:hasPermission name="sys:menu:edit">
								<td nowrap>
									<a href="#" class="modify">修改</a>
									<a href="#" class="delete">删除</a>
									<a href="#" class="addNext">添加下级菜单</a> 
								</td>
							</shiro:hasPermission>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</form>
	</div>
        
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/sys/menu/menuIndex"></script>
		

		
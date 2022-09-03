<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div class="panel panel-default panel-search">
	<div class="panel-heading-blue">
		<div class="panel-title-blue">
			业务表添加
		</div>
	</div>
	<div class="panel-body form-horizontal">
		<form id="tableForm" name="tableForm">
			<input type="hidden" name="id" id="id" value="${genTable.id}" />
			<div class="page-header-sm">
				<div class="row">
					<div class="col-xs-4">
						<h4><strong>基本信息</strong></h4>
					</div>
				</div>
			</div>
			<br>
			<div class="form-group" data-msg-direction="r">
				<label class="col-xs-4-sm control-label required">
                        数据源：
                </label>

				<div class="col-xs-4 col-lg-3">
					<input type="text" class="form-control" name="datasource" value="${genTable.datasource}" readOnly>
				</div>
			</div>
			<div class="form-group" data-msg-direction="r">
				<label class="col-xs-4-sm control-label required">
                        表名：
                </label>

				<div class="col-xs-4 col-lg-3">
					<input type="text" class="form-control" name="name" value="${genTable.name}" readOnly>
				</div>
			</div>
			<div class="form-group" data-msg-direction="r">
				<label class="col-xs-4-sm control-label required">
                       说明：
                    </label>

				<div class="col-xs-4 col-lg-3" data-toggle="popover" data-trigger="focus" data-content="请输入20位有效字符">
					<input type="text" class="form-control" name="comments" value="${genTable.comments}" maxlength="20">
				</div>
			</div>
			<div class="form-group" data-msg-direction="r">
				<label class="col-xs-4-sm control-label required">
                        类名：
                    </label>

				<div class="col-xs-4 col-lg-3" data-toggle="popover" data-trigger="focus" data-content="请输入32位有效字符">
					<input type="text" class="form-control" name="className" value="${genTable.className}" maxlength="32">
				</div>
			</div>
			<!-- <div class="form-group" data-msg-direction="d">
				<label class="col-xs-4-sm control-label">
                        父表表名：
                    </label>

				<div class="col-xs-3">
					<select class="selectpicker" name="legal_type2" id="legal_type2" title="请选择">
						<option value="01" disabled="">
							企业
						</option>
						<option value="02">
							机关法人
						</option>
						<option value="03">
							事业单位
						</option>
						<option value="04">
							机关组织
						</option>
						<option value="05">
							其他
						</option>
					</select>
				</div>
			</div> -->

			<div class="page-header-sm">
				<div class="row">
					<div class="col-xs-4">
						<h4><strong>字段列表</strong></h4>
					</div>
				</div>
			</div>
			<div class="table-fixed">
			<table id="genTableGrid" class="table table-hover">
				<thead class="bg-default" id="thead">
					<tr>
						<th title="数据库字段名">列名</th>
						<th title="默认读取数据库字段备注">说明</th>
						<th title="数据库中设置的字段类型及长度">物理类型</th>
						<th title="实体对象的属性字段类型">Java类型</th>
						<th title="实体对象的属性字段（对象名.属性名|属性名2|属性名3，例如：用户user.id|name|loginName，属性名2和属性名3为Join时关联查询的字段）">Java属性名称 <i class="icon-question-sign"></i></th>
						<th title="是否是数据库主键">主键</th>
						<th title="字段是否可为空值，不可为空字段自动进行空值验证">可空</th>
						<th title="选中后该字段被加入到insert语句里">插入</th>
						<th title="选中后该字段被加入到update语句里">编辑</th>
						<th title="选中后该字段被加入到查询列表里">列表</th>
						<th title="选中后该字段被加入到查询条件里">查询</th>
						<th title="该字段为查询字段时的查询匹配放式">查询匹配方式</th>
<!-- 						<th title="字段在表单中显示的类型">显示表单类型</th> -->
<!-- 						<th title="显示表单类型设置为“下拉框、复选框、点选框”时，需设置字典的类型">字典类型</th> -->
						<th>排序</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${genTable.columnList}" var="column" varStatus="vs">
						<tr${column.delFlag eq '1'? ' class="error" title="已删除的列，保存之后消失！"': ''}>
							<td nowrap>
								<input type="hidden" name="columnList[${vs.index}].id" value="${column.id}" />
								<input type="hidden" name="columnList[${vs.index}].delFlag" value="${column.delFlag}" />
								<input type="hidden" name="columnList[${vs.index}].genTable.id" value="${column.genTable.id}" />
								<input type="hidden" name="columnList[${vs.index}].name" value="${column.name}" />${column.name}
							</td>
							<td>
								<input type="text" name="columnList[${vs.index}].comments" value="${column.comments}" maxlength="200" class="form-control input-sm" style="width:100px;" />
							</td>
							<td nowrap>
								<input type="hidden" name="columnList[${vs.index}].jdbcType" value="${column.jdbcType}" />${column.jdbcType}
							</td>
							<td>
								<select name="columnList[${vs.index}].javaType" class="selectpicker" data-style="btn-sm btn-default">
									<c:forEach items="${config.javaTypeList}" var="dict">
										<option value="${dict.value}" ${dict.value==column.javaType? 'selected': ''}>${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input type="text" name="columnList[${vs.index}].javaField" value="${column.javaField}" maxlength="200" class="form-control input-sm" style="width:200px" />
							</td>
							<td>
								<input type="checkbox" name="columnList[${vs.index}].isPk" value="1" ${column.isPk eq '1' ? 'checked' : ''} disabled/>
							</td>
							<td>
								<input type="checkbox" name="columnList[${vs.index}].isNull" value="1" ${column.isNull eq '1' ? 'checked' : ''} disabled/>
							</td>
							<td>
								<input type="checkbox" name="columnList[${vs.index}].isInsert" value="1" ${column.isInsert eq '1' ? 'checked' : ''}/>
							</td>
							<td>
								<input type="checkbox" name="columnList[${vs.index}].isEdit" value="1" ${column.isEdit eq '1' ? 'checked' : ''}/>
							</td>
							<td>
								<input type="checkbox" name="columnList[${vs.index}].isList" value="1" ${column.isList eq '1' ? 'checked' : ''}/>
							</td>
							<td>
								<input type="checkbox" name="columnList[${vs.index}].isQuery" value="1" ${column.isQuery eq '1' ? 'checked' : ''}/>
							</td>
							<td>
								<select name="columnList[${vs.index}].queryType" class="selectpicker" data-style="btn-sm btn-default">
									<c:forEach items="${config.queryTypeList}" var="dict">
										<option value="${fns:escapeHtml(dict.value)}" ${fns:escapeHtml(dict.value)==column.queryType? 'selected': ''}>${fns:escapeHtml(dict.label)}</option>
									</c:forEach>
								</select>
							</td>
							<%-- <td>
								<select name="columnList[${vs.index}].showType" class="selectpicker" data-style="btn-sm btn-default">
									<c:forEach items="${config.showTypeList}" var="dict">
										<option value="${dict.value}" ${dict.value==column.showType? 'selected': ''}>${dict.label}</option>
									</c:forEach>
								</select>
							</td> --%>
							<%-- <td>
								<input type="text" name="columnList[${vs.index}].dictType" value="${column.dictType}" maxlength="200" class="form-control input-sm" />
							</td> --%>
							<td>
								<input type="text" name="columnList[${vs.index}].sort" value="${column.sort}" maxlength="3" class="form-control input-sm" style="width:50px"/>
							</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<div class="panel-footer text-center">
				<button type="button" id="btnCancel" class="btn btn-default btn-bottom btn-wide" onclick="history.go(-1)">返回</button>
				<button type="button" id="btnSave" class="btn btn-primary btn-bottom btn-wide">保存</button>
			</div>
		</form>
	</div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true" data-main="<%=request.getContextPath()%>/modules/gen/genTableEdit"></script>
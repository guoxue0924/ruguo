/**
 * Created by hl on 2017/4/4.
 * 
 */

require(
		[ configPath ],
		function(c) {
			// 自定义模块，模块定义对照require-config.js,按需配置
			require(
					[ 'init', 'common', 'dialog', 'select', 'grid',
							'area' ],
					function(init, common, BootstrapDialog) {
						$(function() {
							var grid = "";
							// 初始化下拉控件
							$('.selectpicker').selectpicker();
							// 初始化行政区划
							$('.selectArea').SelectArea({
								auth : true,
								select : true
							});
							// 初始化CodeList数据
							common.CodeListDataSet.init([ 'DICT_ORG_TYPE_1',
									'DICT_PLATFORM_TYPE' ]);
							// 初始化日期控件
							/*$('.form_datetime').datetimepicker({
								format : "yyyy-mm-dd",
								pickerPosition : "bottom-left",
								// weekStart: 1,
								todayBtn : 1,
								autoclose : 1,
								startView : 2,
								todayHighlight : 1,
								// forceParse: 1,
								// showMeridian: 1,
								minView : 2
							// minuteStep: 5
							});*/

							/*$('#openBtn')
									.click(
											function() {
												var is_Enable = common.CommonConstant["DictStartStatus"]["start"];// common.CommonConstant["DictStartStatus"]["start"]
												if ("" == grid) {
													BootstrapDialog
															.alert("请选择需要启用的信息!");
													return;
												}
												var obj = grid
														.bootgrid("getSelectedRowsObj");
												if (obj.length == 0) {
													BootstrapDialog
															.alert("请选择需要启用的信息!");
													return;
												}
												// 业务判断
												for (var i = 0; i < obj.length; i++) {
													var isenable = obj[i].isEnable;
													if (common.CommonConstant["DictStartStatus"]["start"] == obj[i].isEnable) {
														BootstrapDialog
																.alert("请选择未启用的记录");
														return;
													}
													if ("国家中心" == obj[i].name) {
														BootstrapDialog
																.alert("国家中心记录默认启用，不可停用");
														return;
													}
												}
												BootstrapDialog
														.confirm({
															message : '确认进行启用操作？',
															closable : true,
															draggable : true,
															btnOKClass : 'btn-primary',
															callback : function(
																	result) {
																if (result) {
																	var jsonArray = [];
																	for (var i = 0; i < obj.length; i++) {
																		jsonArray
																				.push({
																					"id" : obj[i].id
																				});
																	}
																	var jsonObj = {
																		"id" : jsonArray,
																		"isEnable" : is_Enable
																	};
																	var uri = "organization/organ/enableRecord";

																	var defer1 = common
																			.ajaxJson(
																					uri,
																					jsonObj);
																	$
																			.when(
																					defer1)
																			.done(
																					function(
																							result) {
																						BootstrapDialog
																								.closeLoading();// 关闭加载效果
																						if (result.result == common.CommonConstant["RESULT_SUCCESS"]) {
																							// $('#btnCancle').click();
																							$(
																									'#btnSubmit')
																									.click();// 刷新父页面查询列表
																							BootstrapDialog
																									.alert("操作成功");
																						} else {
																							BootstrapDialog
																									.alert(result.content);
																						}
																					});
																}
															}
														});
											});
							$('#stopBtn')
									.click(
											function() {
												var is_Enable = common.CommonConstant["DictStartStatus"]["stop"];// CommonConstant["DictStartStatus"]["stop"]
												if ("" == grid) {
													BootstrapDialog
															.alert("请选择需要停用的信息!");
													return;
												}
												var obj = grid
														.bootgrid("getSelectedRowsObj");
												if (obj.length == 0) {
													BootstrapDialog
															.alert("请选择需要停用的信息!");
													return;
												}
												// 业务判断
												for (var i = 0; i < obj.length; i++) {
													var isenable = obj[i].isEnable;
													if (common.CommonConstant["DictStartStatus"]["start"] != obj[i].isEnable) {
														BootstrapDialog
																.alert("请选择已启用的记录");
														return;
													}
													// if("国家中心"==obj[i].name){
													// BootstrapDialog.alert("国家中心记录默认启用，不可停用");
													// return;
													// }
												}
												BootstrapDialog
														.confirm({
															message : '确认进行停用操作？',
															closable : true,
															draggable : true,
															btnOKClass : 'btn-primary',
															callback : function(
																	result) {
																if (result) {
																	var jsonArray = [];
																	for (var i = 0; i < obj.length; i++) {
																		jsonArray
																				.push({
																					"id" : obj[i].id
																				});
																	}
																	var jsonObj = {
																		"id" : jsonArray,
																		"isEnable" : is_Enable
																	};
																	var uri = "organization/organ/enableRecord";

																	var defer1 = common
																			.ajaxJson(
																					uri,
																					jsonObj);
																	$
																			.when(
																					defer1)
																			.done(
																					function(
																							result) {
																						BootstrapDialog
																								.closeLoading();// 关闭加载效果
																						if (result.result == common.CommonConstant["RESULT_SUCCESS"]) {
																							// $('#btnCancle').click();
																							$(
																									'#btnSubmit')
																									.click();// 刷新父页面查询列表
																							BootstrapDialog
																									.alert("操作成功");
																						} else {
																							BootstrapDialog
																									.alert(result.content);
																						}
																					});
																}
															}
														});
											});*/
							$('#deleteBtn')
									.click(
											function() {
												//var is_Enable = common.CommonConstant["DictStartStatus"]["stop"];// CommonConstant["DictStartStatus"]["stop"]
												if ("" == grid) {
													BootstrapDialog
															.alert("请选择需要删除的信息!");
													return;
												}
												var obj = grid
														.bootgrid("getSelectedRowsObj");
												if (obj.length == 0) {
													BootstrapDialog
															.alert("请选择需要删除的信息!");
													return;
												}
												// 业务判断
												/*for (var i = 0; i < obj.length; i++) {
													var isenable = obj[i].isEnable;
													if (common.CommonConstant["DictStartStatus"]["start"] == obj[i].isEnable) {
														BootstrapDialog
																.alert("请选择未启用的记录");
														return;
													}
												}*/
												BootstrapDialog
														.confirm({
															message : '确认进行删除操作？',
															closable : true,
															draggable : true,
															btnOKClass : 'btn-primary',
															callback : function(
																	result) {
																if (result) {
																	var jsonArray = [];
																	for (var i = 0; i < obj.length; i++) {
																		jsonArray
																				.push({
																					"id" : obj[i].id
																				});
																	}
																	var jsonObj = {
																		"id" : jsonArray
																	};
																	var uri = "organization/organ/deleteRecordList";

																	var defer1 = common
																			.ajaxJson(
																					uri,
																					jsonObj);
																	$
																			.when(
																					defer1)
																			.done(
																					function(
																							result) {
																						BootstrapDialog
																								.closeLoading();// 关闭加载效果
																						if (result.result == common.CommonConstant["RESULT_SUCCESS"]) {
																							// $('#btnCancle').click();
																							$(
																									'#btnSubmit')
																									.click();// 刷新父页面查询列表
																							BootstrapDialog
																									.alert("操作成功");
																						} else {
																							BootstrapDialog
																									.alert(result.content);
																						}
																					});
																}
															}
														});
											});
							/** by liuhuan at 20170509 增加编辑按钮 begin */
							$('#btnEdit')
									.click(
											function() {
												//var is_Enable = common.CommonConstant["DictStartStatus"]["stop"];// CommonConstant["DictStartStatus"]["stop"]
												if ("" == grid) {
													BootstrapDialog
															.alert("请选择需要修改的信息!");
													return;
												}
												var obj = grid
														.bootgrid("getSelectedRowsObj");
												if (obj.length == 0) {
													BootstrapDialog
															.alert("请选择需要修改的信息!");
													return;
												}
												if (obj.length > 1) {
													BootstrapDialog
															.alert("请选择一条需要修改的信息!");
													return;
												}
												/*for (var i = 0; i < obj.length; i++) {
													if (obj[i].orgType == common.CommonConstant["DictOrgStyle"]["manager"]) {
														BootstrapDialog
																.alert('该机构为管理机构,不允许修改.');
														return;
													}
												}*/
												var id = obj[0].id; // 获取当前行用户ID
												BootstrapDialog
														.show({
															title : "编辑机构信息",
															size : BootstrapDialog.SIZE_NORMAL, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
															closable : false,
															message : function() {
																var $message = $('<div></div>');
																return $message
																		.load(webAppPath
																				+ '/organization/organ/update?id='
																				+ id);
															}
														});
											});
							/** by liuhuan at 20170509 增加编辑按钮 end */
							$('#createBtn')
									.click(
											function() {
												BootstrapDialog
														.show({
															title : "添加机构信息",
															size : BootstrapDialog.SIZE_NORMAL, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
															closable : false,
															message : function() {
																var $message = $('<div></div>');
																return $message
																		.load(webAppPath
																				+ '/organization/organ/addInside');
															}
														});
											});
							$('#resetBtn').click(function() {
								// $('#searchFormOrgan')[0].reset();
								$('#searchFormOrgan').resetFormData();
								$(".selectid").find("select").each(function() {
									$(this).selectpicker('val', '');
								})
								// $('#type').selectpicker('val','');
								// common.resetCheckBox('methods');
								// catalogids.splice(0, catalogids.length);
								// grid.bootgrid("reload");
							});
							$('#btnSubmit')
									.click(
											function() {
//												var param = $(
//														'#searchFormOrgan')
//														.serializeObject();
//												if ($.isEmptyObject(param)) {
//													BootstrapDialog
//															.alert('至少输入一项查询条件');
//												} else {
													if (""==grid) {
														// BootstrapDialog.showLoading('正在查询，请稍等...');
														initGrid();
													} else {
														
														grid.bootgrid("reload");
													}
												//}
											});
							function initGrid() {
								grid = $("#itemGrid")
										.bootgrid(
												{
													ajax : true,
													post : function() {
														var param = $(
																'#searchFormOrgan')
																.serializeObject();
														return param;
													},
													url : webAppPath
															+ '/organization/organ/listInside',
													//rowSelect : true,
													emptyPost:true,
													selection : true,
													multiSelect : true,
													rowCount : [5,10,20,50],
													formatters : {
														"orgType": function (column, row) {
								            				// 解析codelist值，付款方式
								            				return common.CodeListDataSet.getLabel('DICT_ORG_TYPE_1', row.orgType);
								            			},
														"operation" : function(
																column, row) {
															var detail = "<a href='#' class=\"detail\" data-id='"
																+ row.id
																+ "' data-isenable='"
																+ row.isEnable
																+ "' data-orgtype='"
																+ row.orgType
																+ "'>详情</a>";
															var edit_html = " <a href='#' class=\"edit_link\" data-id='"
																	+ row.id
																	+ "' data-isenable='"
																	+ row.isEnable
																	+ "' data-orgtype='"
																	+ row.orgType
																	+ "'>修改</a>";
															var del_html = " <a href='#' class=\"del_link\" data-id='"
																	+ row.id
																	+ "' data-isenable='"
																	+ row.isEnable
																	+ "' data-orgtype='"
																	+ row.orgType
																	+ "'>删除</a>";
															return detail+edit_html
																	+ del_html;
														}
													// "<a href='#'
													// class=\"operation\"
													// data-logid='" + row.id +
													// "'>详细</a>";
													}
												})
										.on(
												"loaded.rs.jquery.bootgrid",
												function() {
													// BootstrapDialog.closeLoading();
													grid
													.find(".detail")
													.on(
															"click",
															function(e) {
																e
																		.preventDefault();
																var id = $(
																		this)
																		.data(
																				"id");// 获取当前行用户ID
																
																BootstrapDialog
																		.show({
																			title : "机构信息详情",
																			size : BootstrapDialog.SIZE_NORMAL, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
																			closable : false,
																			message : function() {
																				var $message = $('<div></div>');
																				return $message
																						.load(webAppPath
																								+ '/organization/organ/detailInside?id='
																								+ id);
																			}
																		});
															});
													
													grid
															.find(".edit_link")
															.on(
																	"click",
																	function(e) {
																		e
																				.preventDefault();
																		var id = $(
																				this)
																				.data(
																						"id");// 获取当前行用户ID
																		var orgType = $(
																				this)
																				.data(
																						"orgtype");
																		// 管理机构
																		/*if (orgType == common.CommonConstant["DictOrgStyle"]["manager"]) {
																			BootstrapDialog
																					.alert('该机构为管理机构,不允许修改.');
																			return;
																		}*/
																		BootstrapDialog
																				.show({
																					title : "编辑机构信息",
																					size : BootstrapDialog.SIZE_NORMAL, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
																					closable : false,
																					message : function() {
																						var $message = $('<div></div>');
																						return $message
																								.load(webAppPath
																										+ '/organization/organ/updateInside?id='
																										+ id);
																					}
																				});
																	});
													grid
															.find(".del_link")
															.on(
																	"click",
																	function(e) {
																		e
																				.preventDefault();
																		var id = $(
																				this)
																				.data(
																						"id");// 获取当前行用户ID

																		/**
																		 * mod
																		 * by
																		 * jiyingming
																		 * at
																		 * 2017-04-25
																		 * 删除时，提示校验
																		 * begin
																		 */
																		/*var isEnable = $(
																				this)
																				.data(
																						"isenable");
																		if (isEnable == common.CommonConstant["DictStartStatus"]["start"]) {
																			BootstrapDialog
																					.alert('对不起,该服务机构已启用,不能删除!');
																			return;
																		}*/
																		/**
																		 * mod
																		 * by
																		 * jiyingming
																		 * at
																		 * 2017-04-25
																		 * 删除时，提示校验
																		 * end
																		 */

																		BootstrapDialog
																				.confirm({
																					message : '确认删除机构信息？',
																					closable : true,
																					draggable : true,
																					btnOKClass : 'btn-primary',
																					callback : function(
																							result) {
																						if (result) {

																							BootstrapDialog
																									.showLoading();// 加载效果
																							var uri = "/organization/organ/deleteRecord";
																							var param = 'id='
																									+ id;
																							var defer1 = common
																									.ajaxPost(
																											uri,
																											param);
																							$
																									.when(
																											defer1)
																									.done(
																											function(
																													result) {
																												// BootstrapDialog.closeLoading();//关闭加载效果
																												if (result.status == common.CommonConstant.SUCCESS) {
																													// $('#btnCancle').click();
																													$(
																															'#btnSubmit')
																															.click();// 刷新父页面查询列表
																													BootstrapDialog
																															.alert(result.msg);
																												} else {
																													BootstrapDialog
																															.alert(result.msg);
																												}
																											});
																						}
																					}
																				});
																	});
												});
							}
						});
					});
		});
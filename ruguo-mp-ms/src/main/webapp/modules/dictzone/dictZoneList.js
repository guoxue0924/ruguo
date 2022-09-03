/**
 * Created by yjun on 2017/4/15.
 */

require(
		[ configPath ],
		function(c) {
			// 自定义模块，模块定义对照require-config.js,按需配置
			require(
					[ 'init', 'common', 'dialog', 'expexcel', 'file', 'date', 'select', 'grid',
							'area' ],
					function(init, common, BootstrapDialog, ExportUtil, files) {
						$(function() {

							var grid = "";
//							$('#btnStop').hide();

							$('.selectArea').SelectArea({
								auth:true,
								select:true
							});
							// 初始化CodeList数据
							common.CodeListDataSet.init([ 'DICT_IDENTITY_FLAG',
									'DICT_ORG_LEVEL', 'DICT_START_STATUS',
									'DICT_ZONE_QUBS' ]);

							$('.form_datetime').datetimepicker({
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
							});
							$('.selectpicker').selectpicker();

							// 批量删除
							$('#btnDelete')
									.click(
											function() {
												/** 增加选择判断 by liuhuan at 20170517 start */
												if (common.isEmpty(grid))  {
								            		BootstrapDialog.alert("请选择需要删除的信息!");
								                    return;
								            	}
												/** 增加选择判断 by liuhuan at 20170517 end */
												var obj = grid
														.bootgrid("getSelectedRowsObj");
												if (obj.length == 0) {
													BootstrapDialog
															.alert("请选择需要删除的信息!");
													return;

												}

												for (var i = 0; i < obj.length; i++) {

													var isenable = obj[i].isEnable;
													var name = obj[i].fullName;
													if (isenable == common.CommonConstant["DictStartStatus"]["start"]) {
														BootstrapDialog
																.alert(name
																		+ "已经启用，不允许进行删除！");
														return;

													}

													if (isenable == common.CommonConstant["DictStartStatus"]["stop"]) {
														BootstrapDialog
																.alert(name
																		+ "已经停用，不允许删除！");
														return;

													}

												}

												BootstrapDialog
														.confirm({
															message : '确认进行删除选中区划信息吗？',
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

																	var uri = "area/areamanager/deleteZoneDictList";

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
																						if (result.status == "success") {
																							// $('#btnCancle').click();
																							$(
																									'#btnQuery')
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

							// 批量启用
							$('#btnEnable')
									.click(
											function() {
												/** 增加选择判断 by liuhuan at 20170517 start */
												if (common.isEmpty(grid))  {
								            		BootstrapDialog.alert("请选择需要启用的信息!");
								                    return;
								            	}
												/** 增加选择判断 by liuhuan at 20170517 end */
												var obj = grid
														.bootgrid("getSelectedRowsObj");
												if (obj.length == 0) {
													BootstrapDialog
															.alert("请选择需要启用的信息!");
													return;

												}

												for (var i = 0; i < obj.length; i++) {

													var isenable = obj[i].isEnable;
													var name = obj[i].fullName;
													if (isenable == common.CommonConstant["DictStartStatus"]["start"]) {
														BootstrapDialog
																.alert(name
																		+ "已经启用，请确认");
														return;

													}

													if (isenable == common.CommonConstant["DictStartStatus"]["stop"]) {
														BootstrapDialog
																.alert(name
																		+ "已经停用，不允许再次启用");
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
																		"id" : jsonArray
																	};

																	var uri = "area/areamanager/startUseDistrictList";

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
																						if (result.status == "success") {
																							// $('#btnCancle').click();
																							$(
																									'#btnQuery')
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

							// 停用
							$('#btnStop')
									.click(
											function() {
												/** 增加选择判断 by liuhuan at 20170517 start */
												if (common.isEmpty(grid))  {
								            		BootstrapDialog.alert("请选择需要停用的信息!");
								                    return;
								            	}
												/** 增加选择判断 by liuhuan at 20170517 end */

												var obj = grid
														.bootgrid("getSelectedRowsObj");
												if (obj.length == 0) {
													BootstrapDialog
															.alert("请选择需要停用的信息!");
													return;

												}
												for (var i = 0; i < obj.length; i++) {

													var isenable = obj[i].isEnable;
													var name = obj[i].fullName;
													var code = obj[i].code;
													if (code == common.CommonConstant["DICT_ZONE_STATECENTER"]) {

														BootstrapDialog
																.alert(name
																		+ "国家中心不允许停用，请确认！");
														return;

													}

													if (isenable == common.CommonConstant["DictStartStatus"]["notstart"]) {
														BootstrapDialog
																.alert(name
																		+ "未启用，不允许停用，请确认");
														return;

													}

													if (isenable == common.CommonConstant["DictStartStatus"]["stop"]) {
														BootstrapDialog
																.alert(name
																		+ "已经停用，请确认");
														return;

													}

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
																		"id" : jsonArray
																	};

																	var uri = "area/areamanager/stopUseDistrictList";

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
																						if (result.status == "success") {
																							// $('#btnCancle').click();
																							$(
																									'#btnQuery')
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
							// 增加编辑按钮 by liuhuan at 20170509 start ------------
							$('#btnEdit').click(function() {
								/** 增加选择判断 by liuhuan at 20170517 start */
								if (common.isEmpty(grid))  {
				            		BootstrapDialog.alert("请选择需要编辑的信息!");
				                    return;
				            	}
								/** 增加选择判断 by liuhuan at 20170517 end */
								var obj = grid.bootgrid("getSelectedRowsObj");
								if (obj.length == 0) {
									BootstrapDialog.alert("请选择需要编辑的信息!");
									return;
								}
								if (obj.length > 1) {
									BootstrapDialog.alert("请选择一条编辑的信息!");
									return;
								}
								BootstrapDialog.show({
									title : "编辑区划信息",
									size : BootstrapDialog.SIZE_WIDE, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
									closable : false, message : function() {
										var $message = $('<div></div>');
										return $message.load(webAppPath + '/area/areamanager/modifyZoneDictInfo?id=' + obj[0].id);
									}
								});
							});
							// 增加添加下级区划按钮 by liuhuan at 20170509 ------------
							$('#btnAdd').click(function() {
								/** 增加选择判断 by liuhuan at 20170517 start */
								if (common.isEmpty(grid))  {
				            		BootstrapDialog.alert("请选择一条上级区划信息!");
				                    return;
				            	}
								/** 增加选择判断 by liuhuan at 20170517 end */
								var obj = grid.bootgrid("getSelectedRowsObj");
								if (obj.length == 0) {
									BootstrapDialog.alert("请选择一条上级区划信息!");
									return;
								}
								if (obj.length > 1) {
									BootstrapDialog.alert("请选择一条上级区划信息!");
									return;
								}
								var userid = obj[0].id;// 获取当前行用户ID
								var level = obj[0].level;
								var isenable = obj[0].isEnable;
								if (isenable == common.CommonConstant["DictStartStatus"]["notstart"]) {
									BootstrapDialog.alert("未启用区划不能添加下级区划信息，请确认！");
									return;
								}
								if (level == common.CommonConstant["DictZoneLevel"]["town"]) {
									BootstrapDialog.alert("乡级区划不能添加下级区划，请确认！");
									return;
								}
								BootstrapDialog.show({
									title : "添加区划信息",
									size : BootstrapDialog.SIZE_WIDE,
									closable : false,
									message : function() {
										var $message = $('<div></div>');
										return $message.load(webAppPath + '/area/areamanager/addZoneDictInfo?id=' + userid);
									}
								});
							});
							// 增加编辑、添加下级区划按钮 by liuhuan at 20170509 end ------------
							// 查询
							$('#btnQuery').click(function() {
								var param = $('#searchForm').serializeObject();
				            	if($.isEmptyObject(param)){
				            		BootstrapDialog.alert('请至少录入一项查询条件！');
				                	}else{
				                		
				                		if (grid.length > 0) {
											grid.bootgrid("reload");
										} else {
											initGrid();
										}
				                		
				                		
				                	}

								
							});

							// 重置

							$('#btnReset').click(function() {

								$('#searchForm').resetFormData();
							});

							function initGrid() {
								grid = $("#userGrid")
										.bootgrid(
												{
													ajax : true,
													post : function() {
														var param = $(
																'#searchForm')
																.serializeObject();
														return param;
													},
													url : webAppPath
															+ '/area/areamanager/getZoneInfoList',
													rowSelect : true,
													selection : true,
													multiSelect : true,
													rowCount : [ 10, 20, 50 ],

													formatters : {

														"level" : function(
																column, row) {
															// 解析codelist值
															return common.CodeListDataSet
																	.getLabel(
																			'DICT_ORG_LEVEL',
																			row.level);
														},
														"zoneTag" : function(
																column, row) {
															// 解析codelist值
															return common.CodeListDataSet
																	.getLabel(
																			'DICT_ZONE_QUBS',
																			row.zoneTag);
														},
														"isPoorCounty" : function(
																column, row) {
															// 解析codelist值
															return common.CodeListDataSet
																	.getLabel(
																			'DICT_IDENTITY_FLAG',
																			row.isPoorCounty);
														},
														"isAllCover" : function(
																column, row) {
															// 解析codelist值
															return common.CodeListDataSet
																	.getLabel(
																			'DICT_IDENTITY_FLAG',
																			row.isAllCover);
														},
														"isEnable" : function(
																column, row) {
															// 解析codelist值
															return common.CodeListDataSet
																	.getLabel(
																			'DICT_START_STATUS',
																			row.isEnable);
														},
														"operation" : function(
																column, row) {

															var edit_html = " <a href='#' class=\"edit_link\" data-id='"
																	+ row.id
																	+ "' data-isenable='"
																	+ row.isEnable
																	+ "'>编辑</a>";

															var del_html = " <a href='#' class=\"del_link\" data-id='"
																	+ row.id
																	+ "' data-isenable='"
																	+ row.isEnable
																	+ "'>删除</a>";

															var detail_html = " <a href='#' class=\"add_link\" data-id='"
																	+ row.id
																	+ "' data-level='"
																	+ row.level+ "' data-isenable='"
																	+ row.isEnable
																	+ "'>添加下级区划</a>";
															return del_html
																	+ edit_html
																	+ detail_html;

														}
													}

												})
										.on(
												"loaded.rs.jquery.bootgrid",
												function() {
													grid
															.find(".edit_link")
															.on(
																	"click",
																	function(e) {
																		e
																				.preventDefault();
																		var userid = $(
																				this)
																				.data(
																						"id");// 获取当前行用户ID

																		var val = $(
																				this)
																				.data(
																						"isenable");
																																	

//																		if (val == common.CommonConstant["DictStartStatus"]["start"]) {
//
//																			BootstrapDialog
//																					.alert("已经启用，不能进行编辑操作！");
//																			return;
//
//																		}

																		BootstrapDialog
																				.show({
																					title : "编辑区划信息",
																					size : BootstrapDialog.SIZE_WIDE, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
																					closable : false,
																					message : function() {
																						var $message = $('<div></div>');
																						return $message
																								.load(webAppPath
																										+ '/area/areamanager/modifyZoneDictInfo?id='
																										+ userid);
																					}
																				});
																	});

													// 删除区划信息
													grid
															.find(".del_link")
															.on(
																	"click",
																	function(e) {
																		e
																				.preventDefault();
																		var bulletinid = $(
																				this)
																				.data(
																						"id");// 获取当前行用户ID

																		var val = $(
																				this)
																				.data(
																						"isenable");
																		//													

																		if (val == common.CommonConstant["DictStartStatus"]["start"]) {

																			BootstrapDialog
																					.alert("已经启用，不能进行删除！");
																			return;

																		}

																		if (val == common.CommonConstant["DictStartStatus"]["stop"]) {
																			BootstrapDialog
																					.alert("已经停用，不允许删除！");
																			return;

																		}

																		BootstrapDialog
																				.confirm({
																					message : '确认进行删除此区划信息吗？',
																					closable : true,
																					draggable : true,
																					btnOKClass : 'btn-primary',
																					callback : function(
																							result) {
																						if (result) {

																							BootstrapDialog
																									.showLoading();// 加载效果
																							var uri = "area/areamanager/deleteZoneDictInfo";
																							var param = 'id='
																									+ bulletinid;
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
																												BootstrapDialog
																														.closeLoading();// 关闭加载效果
																												if (result.status == "success") {
																													// $('#btnCancle').click();
																													$(
																															'#btnQuery')
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

													grid
															.find(".add_link")
															.on(
																	"click",
																	function(e) {
																		e
																				.preventDefault();
																		var userid = $(
																				this)
																				.data(
																						"id");// 获取当前行用户ID
																		var val = $(
																				this)
																				.data(
																						"level");
																		
																		var isEnable = $(
																				this)
																				.data(
																						"isenable");

																		
																		if (isEnable == common.CommonConstant["DictStartStatus"]["notstart"]) {
																			BootstrapDialog
																					.alert("未启用区划不能添加下级区划信息，请确认！");
																			return;

																		}

																		
																		

																		if (val == common.CommonConstant["DictZoneLevel"]["town"]) {
																			BootstrapDialog
																					.alert("乡级区划不能添加下级区划，请确认！");
																			return;

																		}

																		BootstrapDialog
																				.show({
																					title : "添加区划信息",
																					size : BootstrapDialog.SIZE_WIDE, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
																					closable : false,
																					message : function() {
																						var $message = $('<div></div>');
																						return $message
																								.load(webAppPath
																										+ '/area/areamanager/addZoneDictInfo?id='
																										+ userid);
																					}
																				});
																	});

												});// end grid

							}

							files.init();

							// 导入
							$('#btnImportExcel').click(function () {
								var file = $("#file1").val();
								if(common.isEmpty(file)){
									BootstrapDialog.alert("请选择Excel文件进行导入！");
									return;
								}
								BootstrapDialog.confirm({
									message: '确认导入？',
									closable: true,
									draggable: true,
									btnOKClass: 'btn-primary',
									callback: function (result) {
										if (result) {
											BootstrapDialog.showLoading(); // 加载效果
											var uri = "import/importExcel";
											var defer1 = common.ajaxSubmit(uri, 'importForm');
											$.when(defer1).done(function (result) {
												BootstrapDialog.closeLoading(); // 关闭加载效果
												if (result.status === common.CommonConstant.SUCCESS) {
													BootstrapDialog.alert(result.msg);
												} else {
													BootstrapDialog.closeLoading(); // 加载效果
													BootstrapDialog.alert(result.msg);
												}
											});
										}
									}
								});
							});

							// 全部启用
							$('#btnStartAll').click(function () {
								BootstrapDialog.confirm({
									message: '确认全部启用？',
									closable: true,
									draggable: true,
									btnOKClass: 'btn-primary',
									callback: function (result) {
										if (result) {
											BootstrapDialog.showLoading(); // 加载效果

											var uri = "area/areamanager/startAllOrg";

											var defer1 = common.ajaxJson(uri,{});

											$.when(defer1).done(function (result) {
												BootstrapDialog.closeLoading(); // 关闭加载效果
												if (result.status === common.CommonConstant.SUCCESS) {
													$('#btnQuery').click();// 刷新父页面查询列表
													BootstrapDialog.alert(result.msg);
												} else {
													BootstrapDialog.alert(result.msg);
												}
											});
										}
									}
								});
							});

							// 导出  By liuhuan 增加行为空判断 at 20170517
							$('#btnExportExcel').click(function () {
								var param = $('#searchForm').serializeObject();
								if($.isEmptyObject(param)){
									BootstrapDialog.alert('请先进行查询，再导出！');
									return;
								} else {
									if(!grid){
										BootstrapDialog.alert('请先进行查询，再导出！');
										return;
									}
									if (grid.bootgrid('getTotalRowCount') == 0) {
										BootstrapDialog.alert('请先进行查询，再导出！');
										return;
									}
									BootstrapDialog.confirm({
										message: '确认导出？',
										closable: true,
										draggable: true,
										btnOKClass: 'btn-primary',
										callback: function (result) {
											if (result) {
												ExportUtil.exportExcel('/export/exportDictZoneExcel',param);
											}
										}
									});
								}
							});

							// 导出本页  By liuhuan 增加行为空判断 at 20170517
							$('#btnExportExcelPage').click(function () {
								var param = $('#searchForm').serializeObject();
								if($.isEmptyObject(param)){
									BootstrapDialog.alert('请先进行查询，再导出！');
									return;
								} else {
									if(!grid){
										BootstrapDialog.alert('请先进行查询，再导出！');
										return;
									}
									if (grid.bootgrid('getTotalRowCount') == 0) {
										BootstrapDialog.alert('请先进行查询，再导出！');
										return;
									}
									var rowCount = $("#userGrid").bootgrid("getRowCount");
									var current = $("#userGrid").bootgrid("getCurrentPage");
									param.rowCount = rowCount;
									param.current = current;
									param.isPage = true;
									BootstrapDialog.confirm({
										message: '确认导出？',
										closable: true,
										draggable: true,
										btnOKClass: 'btn-primary',
										callback: function (result) {
											if (result) {
												ExportUtil.exportExcel('/export/exportDictZoneExcel',param);
											}
										}
									});
								}
							});
						});
					});
		});

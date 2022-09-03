/**
 * Created by hl on 2017/8/28.
 */
require([configPath], function(c) {
	//自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'common', 'dialog', 'select', 'grid'], function(init, common, BootstrapDialog) {
		$(function() {

			$('#btnAdd').click(function() {
				window.location.href = webAppPath + '/gen/genScheme/index?type=edit';
			});

			$('#resetbtn').click(function() {
				$('#searchForm').resetFormData();
			});
			//查询按钮点击事件
			$('#btnQuery').click(function() {
				grid.bootgrid("reload");

			});
			var grid = $("#genSchemeGrid").bootgrid({
				ajax: true,
				post: function() {
					var param = $('#searchForm').serializeObject();
					return param;
				},
				url: webAppPath + '/gen/genScheme/list',
				rowSelect: true,
				selection: true,
				multiSelect: false,
				emptyPost: true,
				//                refreshButton: false,
				//                keepSelection: true,
				//                navigation: 0,
				//                colResizable: true,	
				rowCount: [5, 10, 20, -1],
				formatters: {
					"operation": function(column, row) {
						var edit_html = "<a href='#' class=\"edit_link\" data-id='" + row.id + "'>修改</a>";
						var del_html = " <a href='#' class=\"del_link\" data-id='" + row.id + "'>删除</a>";
						return edit_html + del_html;
					}

				}
			}).on("loaded.rs.jquery.bootgrid", function() {
				grid.find(".edit_link").on("click", function(e) {
					e.preventDefault();
					var tid = $(this).data("id"); //获取当前行用户ID
					window.location.href = webAppPath + '/gen/genScheme/index?type=edit&id=' + tid;
				});
				grid.find(".del_link").on("click", function(e) {
					e.preventDefault();
					var tid = $(this).data("id"); //获取当前行用户ID
					BootstrapDialog.confirm({
						message: '确认删除方案？',
						closable: true,
						draggable: true,
						callback: function(result) {
							if(result) {

								BootstrapDialog.showLoading(); //加载效果
								var uri = "gen/genScheme/delete";
								var param = 'id=' + tid;
								var defer1 = common.ajaxPost(uri, param);
								$.when(defer1).done(function(result) {
									if(result.status == common.CommonConstant.SUCCESS) {
										grid.bootgrid("reload"); //刷新查询列表
										BootstrapDialog.alert(result.msg);
									} else {
										BootstrapDialog.alert(result.msg);
									}
								});

							}
						}
					});
				});

			}); //end grid

		});
	});
});
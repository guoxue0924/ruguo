/**
 * Created by hl on 2017/8/28.
 */

require([configPath], function(c) {

	//自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'dialog', 'common', 'valid', 'select'], function(init, BootstrapDialog, common) {
		$(function() {
			//初始化下拉控件
			$('.selectpicker').selectpicker();
			//关闭窗口
			$('#btnCancle').click(function() {
				//            	$.addUserDialog.close();
				BootstrapDialog.closeAll();

			});
			var valid = $.bootValid('#tableForm', {
				rules: {
					name: ['not_empty'],
					comments: ['not_empty'],
					className: ['not_empty']
				}
			});

			//保存按钮
			$('#btnSave').click(function() {
				if(valid.validate()) {
					BootstrapDialog.confirm({
						message: '确认保存业务表？',
						closable: true,
						draggable: true,
						btnOKClass: 'btn-primary',
						callback: function(result) {
							if(result) {

								BootstrapDialog.showLoading(); //加载效果
								var uri = "gen/genTable/save";
								var param = $('#tableForm').serializeData();
								var defer = common.ajaxPost(uri, param);

								$.when(defer).done(function(result) {
									if(result.status === common.CommonConstant.SUCCESS) {
										$('#id').val(result.data);
										BootstrapDialog.confirm({
											title: '消息确认',
											message: result.msg,
											type: BootstrapDialog.TYPE_PRIMARY,
											closable: true,
											draggable: false,
											btnCancelLabel: '继续修改',
											btnOKLabel: '返回列表',
											btnOKClass: 'btn-primary',
											callback: function(result) {
												if(result) {
													window.location.href = webAppPath + '/gen/genTable/index';
												}
											}
										});

									} else {
										BootstrapDialog.alert(result.msg);
									}
								});

							}
						}
					});

				}
			});

		});
	});
});
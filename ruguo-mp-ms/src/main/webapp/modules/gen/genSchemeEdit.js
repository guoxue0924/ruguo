/**
 * Created by hl on 2017/8/28.
 */

require([configPath], function(c) {

	//自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'dialog', 'common', 'valid', 'select'], function(init, BootstrapDialog, common) {
		$(function() {
			//初始化下拉控件
			$('.selectpicker').selectpicker();
			
			$('#gentableid').on('changed.bs.select', function() {
				var subtext = $(this).children('option:checked').data('subtext');
				console.log();
				$('#fname').val(subtext);
				$('#functionName').val(subtext);
			});
			//关闭窗口
			$('#btnCancle').click(function() {
				BootstrapDialog.closeAll();
			});

			var valid = $.bootValid('#tableForm', {
				rules: {
					name: ['not_empty'],
					category: ['not_empty'],
					packageName: ['not_empty'],
					moduleName: ['not_empty'],
					functionName: ['not_empty'],
//					functionNameSimple: ['not_empty'],
					functionAuthor: ['not_empty']
				}
			});

			//保存按钮
			$('.btnSave').click(function() {
				$('#flag').val($(this).val());

				if(valid.validate()) {
					BootstrapDialog.confirm({
						message: '确认保存生成方案？',
						closable: true,
						draggable: true,
						btnOKClass: 'btn-primary',
						callback: function(result) {
							if(result) {

								BootstrapDialog.showLoading(); //加载效果
								var uri = "gen/genScheme/save";
								var param = $('#tableForm').serializeData(true);
								var defer = common.ajaxPost(uri, param);

								$.when(defer).done(function(result) {
									if(result.status === common.CommonConstant.SUCCESS) {
										BootstrapDialog.alert({
											message: result.msg,
											size: BootstrapDialog.SIZE_NORMAL,
											callback: function(result) {
												window.location.href = webAppPath + '/gen/genScheme/index';
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
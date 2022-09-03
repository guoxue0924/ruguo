/**
 * Created by yjun on 2017/4/15.
 */

require([ configPath ], function(c) {
	// 自定义模块，模块定义对照require-config.js,按需配置
	require([ 'dialog', 'common', 'select', 'date', 'valid'/*
															 * , 'files',
															 * 'spinner'
															 */], function(BootstrapDialog, common) {

		common.CodeListDataSet.init([ 'DICT_IDENTITY_FLAG', 'DICT_ORG_LEVEL',
				'DICT_START_STATUS', 'DICT_ZONE_QUBS' ]);

		$(function() {
			// 关闭loading
			BootstrapDialog.closeLoading();
			
			
			// 初始化下拉控件
			$('.selectpicker').selectpicker();
			// 初始化日期控件
			$('.form_datetime').datetimepicker({
				format : "yyyy-mm-dd",
				pickerPosition : "bottom-left",
				todayBtn : 1,
				autoclose : 1,
				startView : 2,
				todayHighlight : 1,
				minView : 2
			});

			$('#levels').selectpicker("disabled");
			// 根据区划级别disabled控制
			var level = $('#levels').selectpicker('val');
			if (level != common.CommonConstant["DictZoneLevel"]["county"]) {
				$('#zoneTags').selectpicker("disabled");
				$('#isPoorCountys').selectpicker("disabled");
				$('#isAllCovers').selectpicker("disabled");

			}
			
			var isEnable = $('#isEnables').val();
			if (isEnable == common.CommonConstant["DictStartStatus"]["start"]) {
				
				$('#zoneTags').selectpicker("disabled");
				$('#isPoorCountys').selectpicker("disabled");
				$('#isAllCovers').selectpicker("disabled");
				
				
				$('#levels').attr("disabled", true);
				$('#code').attr("disabled", true);

			}
			
			
			
			
			
			

			// 关闭窗口
			$('#btnCancle').click(function() {
				BootstrapDialog.closeAll();
			});

			var valid = $.bootValid('#userForm', {
				rules : {
					name : [ 'not_empty' ],
					code : [ 'not_empty', 'numeric', {
						'exact_length' : 9
					} ]
				}

			});

			// 保存按钮
			$('#btnSave').click(function() {
				
		
				if (valid.validate()) {

					BootstrapDialog.showLoading();// 加载效果
					var uri = "area/areamanager/modifyDistrict";
					var param = $('#userForm').serializeData();
					var defer1 = common.ajaxPost(uri, param);
					$.when(defer1).done(function(result) {
						BootstrapDialog.closeLoading();// 关闭加载效果
						if (result.status == "success") {
							$('#btnCancle').click();
							$('#btnQuery').click();// 刷新父页面查询列表
							BootstrapDialog.alert(result.msg);
						} else {
							BootstrapDialog.closeLoading();// 加载效果
							BootstrapDialog.alert(result.msg);
						}
					});

					// BootstrapDialog.confirm({
					// message : '确认保存区划信息？',
					// closable : true,
					// draggable : true,
					// btnOKClass : 'btn-primary',
					// callback : function(result) {
					// if (result) {
					// BootstrapDialog.showLoading();// 加载效果
					// var uri = "area/areamanager/modifyDistrict";
					// var param = $('#userForm').serializeData();
					// var defer1 = common.ajaxPost(uri, param);
					// $.when(defer1).done(function(result) {
					// BootstrapDialog.closeLoading();// 关闭加载效果
					// if (result.status == "success") {
					// $('#btnCancle').click();
					// $('#btnQuery').click();// 刷新父页面查询列表
					// BootstrapDialog.alert(result.msg);
					// } else {
					// BootstrapDialog.closeLoading();// 加载效果
					// BootstrapDialog.alert(result.msg);
					// }
					// });
					//
					// }
					// }
					// });

				}
			});

		});
	});
});

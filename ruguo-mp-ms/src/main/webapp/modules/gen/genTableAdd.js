/**
 * Created by hl on 2017/8/28.
 */

require([configPath], function(c) {

	//自定义模块，模块定义对照require-config.js,按需配置
	require(['dialog', 'common'], function(BootstrapDialog, common) {
		$(function() {
			$('.selectpicker').selectpicker();
			//关闭窗口
			$('#btnCancle').click(function() {
				BootstrapDialog.closeAll();
			});

			$('#datasource').on('changed.bs.select', function() {
				$("#tablename").empty();
				var uri = "gen/genTable/findTableList";
				var param = {};
				param.datasource = this.value;
				var defer = common.ajaxPost(uri, param);
				$.when(defer).done(function(result) {
					if($.isEmptyObject(result)) {
						return;
					}
					$.each(result, function(k, p) {
						var option = "<option value=" + p.name + " data-subtext=" + p.comments + ">" + p.name + "</option>";
						$("#tablename").append(option);
					});
					$("#tablename").selectpicker('refresh');
				});
			});
			//保存按钮
			$('#btnNext').click(function() {
				BootstrapDialog.showLoading(); //加载效果
				var uri = "gen/genTable/addCheck";
				var param = {};
				param.name = $('#tablename').val();
				param.datasource = $('#datasource').val();
				var defer = common.ajaxPost(uri, param);

				$.when(defer).done(function(result) {
					if(result.status === common.CommonConstant.SUCCESS) {
						window.location.href = webAppPath + '/gen/genTable/index?type=edit&name=' + $('#tablename').val() + '&datasource=' + $('#datasource').val();
					} else {
						BootstrapDialog.alert(result.msg);
					}
				});
			});

		});
	});
});
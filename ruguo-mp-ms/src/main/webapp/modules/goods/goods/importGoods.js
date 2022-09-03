/**
 * Created by yuelingyun on 2017/6/12.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common', 'dialog', 'expexcel', 'file'], function (init, common, BootstrapDialog, ExportUtil, files) {
        $(function () {
        	//关闭loading
            BootstrapDialog.closeLoading();
            
            //重置按钮
            $('#btnReset').click(function () {
                $('#importForm').resetFormData();
            });
            //关闭窗口
            $('#btnCancle').click(function () {
                BootstrapDialog.closeAll();
                
            });
            // 初始化上传附件操作
        	files.init();
			// 导入
			$('#btnSave').click(function () {
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
							var uri = "goods/goods/importexcel";
							var defer1 = common.ajaxSubmit(uri, 'importForm');
							$.when(defer1).done(function (result) {
								if (result.status === common.CommonConstant.SUCCESS) {
									BootstrapDialog.alert({
									    message: result.msg,
									    callback: function (result) {
									    	BootstrapDialog.closeAll();
									    }
									});
								} else {
									BootstrapDialog.alert(result.msg);
								}
							});
						}
					}
				});
			});
			
        });
    });
});

/**
 * Created by wanggang on 2017/6/19.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
	require(['dialog'], function (BootstrapDialog) {
        $(function () {
        	 //关闭窗口
            $('#btnClose').click(function () {
                BootstrapDialog.closeAll();
            }); 
        });
    });
});

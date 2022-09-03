/**
 * Created by guoxue on 2017/6/5.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog','file', 'select', 'date', 'valid','editor', 'treebox'], function (init, common, BootstrapDialog,files) {
        $(function () {
        	
        	 //关闭窗口
            $('#btnClose').click(function () {
                BootstrapDialog.closeAll();
            });
            
            
        });
    });
});

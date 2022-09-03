/**
 * Created by hl on 2017/4/4.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['dialog'], function (BootstrapDialog) {
        $(function () {
            BootstrapDialog.closeLoading();
            $('#btnCancle').click(function () {
                BootstrapDialog.closeAll();
            });
        });
    });

});

/**
 * Created by hl on 2017/4/4.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照config.js,按需配置
    require(['init', 'uploadp'], function (init, WebUploader) {

        

        /** 实例化uploader end **/

        $('#to_sys_old').click(function () {
            window.open(webAppPath + "/a");
        })

    });
});

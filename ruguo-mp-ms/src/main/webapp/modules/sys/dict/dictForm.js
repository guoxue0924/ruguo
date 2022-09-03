/**
 * Created by jiyingming on 2017/4/17.
 */
require([configPath], function (c) {
    // 自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog', 'select', 'valid'], function (init, common, BootstrapDialog) {
        $(function () {

            // 校验
            var valid = $.bootValid('#dictForm', {
                rules: {
                    value : ['not_empty', {'max_length': 100}],
                    label: ['not_empty', {'max_length': 50}],
                    type: ['not_empty', {'max_length': 50}],
                    description: ['not_empty', {'max_length': 50}],
                    sort: ['not_empty', 'digit', {'max_length': 11}],
                }
            });

            // 触发自定义提示语
            $('#btnShowValid').click(function () {

            });

            // 清楚自定义提示语
            $('#btnRemoveValid').click(function () {

            });

            // 重置
            $('#btnReset').click(function () {
                $('#dictForm')[0].reset();;
            });

            // 关闭
            $('#btnClose').click(function () {
                BootstrapDialog.closeAll();
            });

            // 保存
            $('#btnSave').click(function () {

                if (valid.validate()) {

                    BootstrapDialog.confirm({
                        message: '确认保存？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "sys/dict/doSave";
                                var jsonData = $('#dictForm').serializeObject();

                                var defer1 = common.ajaxJson(uri, jsonData);

                                $.when(defer1).done(function(result){

                                    BootstrapDialog.closeLoading();//关闭加载效果

                                    if (result.status === common.CommonConstant.SUCCESS) {

                                        $('#btnClose').click();
                                        $('#btnQuery').click();

                                        BootstrapDialog.alert(result.msg);

                                    } else {
                                        BootstrapDialog.closeLoading();//加载效果
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

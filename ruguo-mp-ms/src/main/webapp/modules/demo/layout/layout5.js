/**
 * Created by hl on 2017/4/4.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common', 'dialog', 'date', 'select', 'grid'], function (init, common,BootstapDialog) {
        $(function () {

            //初始化日期控件
            $('.form_datetime').datetimepicker({
                format: "yyyy-mm-dd",
                pickerPosition: "bottom-left",
                //weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                startView: 2,
                todayHighlight: 1,
                //forceParse: 1,
                //showMeridian: 1,
                minView: 2
                //minuteStep: 5
            });
            //初始化下拉控件
            $('.selectpicker').selectpicker();

            $('#addAddress').click(function () {
                BootstrapDialog.alert({
                    //title: 'WARNING',
                    message: '保存成功!',
                    //type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                    closable: true, // <-- Default value is false
                    //draggable: true, // <-- Default value is false
                    btnOKClass: 'btn-warning',
                    //buttonLabel: 'Roar! Why!', // <-- Default value is 'OK',
                    callback: function (result) {
                        // result will be true if button was click, while it will be false if users close the dialog directly.
                        alert('Result is: ' + result);
                    }
                });
            });

            $('#resetbtn').click(function () {
            	$('#searchForm')[0].reset();
//                $(":text").val('');
                $('#type').selectpicker('val', '');
            });
            $('#querybtn').click(function () {
                BootstrapDialog.showLoading('正在查询，请稍等...');
                grid.bootgrid("reload");
            });

        });
    });
});

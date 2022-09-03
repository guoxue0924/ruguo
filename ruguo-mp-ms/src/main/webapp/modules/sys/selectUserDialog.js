/**
 * Created by liuqing on 2017/4/21.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','dialog', 'common','grid'], function (init,BootstrapDialog, common) {
        $(function () {
            //关闭loading
            //BootstrapDialog.closeLoading();

            var parameters = $.selectUserDialogParameter;
            if (common.isEmpty(parameters)) {
                return;
            }

            var browserName = getCurrenBrowserName();
            if (browserName == "IE") {
                $("#nameFilter").bind("keyup",function () {
                    setTimeout(function () {
                        queryInfo();
                    }, 1000);
                });
                // $("#nameFilter").bind("DOMAttrModified", function() {
                //     alert(browserName);
                //     setTimeout(function () {
                //         queryInfo();
                //     }, 1000);
                // });
                // document.getElementById("nameFilter").addEventListener("DOMAttrModified", function() {
                //     alert(browserName);
                //     setTimeout(function () {
                //         queryInfo();
                //     }, 1000);
                // }, false);
                // $("#nameFilter").addEventListener("DOMAttrModified", function() {
                //     alert(browserName);
                //     setTimeout(function () {
                //         queryInfo();
                //     }, 1000);
                // }, false);
                // document.getElementById("nameFilter").onPropertyChange = function() {
                //         alert(browserName);
                //         setTimeout(function () {
                //             queryInfo();
                //         }, 1000);
                //     };
            } else {
                $("#nameFilter").bind("input",function () {
                    setTimeout(function () {
                        queryInfo();
                    }, 1000);
                });
            }


            // 查询
            $('#selectUserBtnQuery').click(function () {
                queryInfo();
            });

            // 重置
            $('#selectUserBtnReset').click(function () {
                $("#nameFilter").val("");
            });

            var treeName = common.isEmpty(parameters["treeName"]) ? false : parameters["treeName"];

            // 关闭窗口
            $('#btnSelectUserDialogCancle').click(function () {
                $.selectUserDialog.close();
            });

            // 保存按钮
            $('#btnSelectUserDialogSave').click(function () {

                var obj = grid.bootgrid("getSelectedRowsObj");
                var param = $('#selectUserSearchForm').serializeObject();
                parameters._callback(obj, param);
                $.selectUserDialog.close();
            });


            var grid = $("#selectUserUserGrid").bootgrid({
                ajax: true,
                post: function () {
                    var param = $('#selectUserSearchForm').serializeObject();
                    return param;
                },
                url: webAppPath + '/common/selectTree/getSelectUserList',
                rowSelect: true,
                selection: true,
                multiSelect: false,
                // columnSelection : false,
                // refreshButton: false,
                rowCount: [10]
            }).on("loaded.rs.jquery.bootgrid", function () {
                // $("#nameFilter").focus();
            });

            function queryInfo() {
                grid.bootgrid("reload");
            }

            function getCurrenBrowserName() {
                var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
                var isOpera = userAgent.indexOf("Opera") > -1;
                if (isOpera) {
                    return "Opera"
                }; // 判断是否Opera浏览器
                if (userAgent.indexOf("Firefox") > -1) {
                    return "FF";
                }; // 判断是否Firefox浏览器
                if (userAgent.indexOf("Chrome") > -1){
                    return "Chrome";
                }; // 判断是否Chrome浏览器
                if (userAgent.indexOf("Safari") > -1) {
                    return "Safari";
                }; // 判断是否Safari浏览器
                if (!!window.ActiveXObject || "ActiveXObject" in window) {
                    return "IE";
                }; // 判断是否IE浏览器

            }
        });
    });
});

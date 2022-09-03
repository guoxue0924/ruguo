/**
 * Created by jiyingming on 2017/4/18.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common', 'dialog', 'select', 'valid', 'grid'], function (init, common, BootstrapDialog) {
        $(function () {

            // 初始化grid
            var grid = "";

            $('.selectpicker').selectpicker();

            // 查询重置
            $('#btnQueryReset').click(function () {
                $('#searchForm').resetFormData();
            });
            initGrid();
            // 查询
            $('#btnQuery').click(function () {

                // 校验通过
                if (grid.length > 0) {

                    grid.bootgrid("reload");
                } else {

                    initGrid();
                }
            });

            // 字典添加
            $('#btnAdd').click(function () {
                BootstrapDialog.show({
                    title: "字典添加",
                    size: BootstrapDialog.SIZE_NORMAL,
                    // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + '/sys/dict/add');
                    }
                });
            });

            // 初始化grid
            function initGrid() {
                grid = $("#dictGrid").bootgrid({
                    emptyPost: true,
                    ajax: true,
                    post: function () {

                        var param = $("#searchForm").serializeObject();

                        return param;
                    },
                    url: webAppPath + '/sys/dict/doQuery',
                    rowSelect: true,
                    selection: false,
                    multiSelect: true,
                    rowCount: [10, 20, 50],
                    formatters: {
                        "operation": function (column, row) {

                            var edit_html = "<a href='#' class=\"edit_link\" data-id='" + row.id + "'>修改</a> ";

                            var del_html = "<a href='#' class=\"del_link\" data-id='" + row.id + "'>删除</a> ";

                            var add_html = "<a href='#' class=\"add_link\" data-id='" + row.id + "'>添加键值</a>";

                            return edit_html + del_html + add_html;

                        }
                    }

                }).on("loaded.rs.jquery.bootgrid", function () {
                    // 修改字典
                    grid.find(".edit_link").on("click", function (e) {
                        e.preventDefault();
                        var id = $(this).data("id");
                        // 获取当前行ID

                        BootstrapDialog.show({
                            title: "字典修改",
                            size: BootstrapDialog.SIZE_NORMAL,
                            // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                            closable: false,
                            message: function () {
                                var $message = $('<div></div>');
                                return $message.load(webAppPath + '/sys/dict/update?id=' + id);
                            }
                        });
                    });

                    // 删除字典
                    grid.find(".del_link").on("click", function (e) {
                        e.preventDefault();
                        var id = $(this).data("id");
                        // 获取当前行用户ID

                        BootstrapDialog.confirm({
                            message: '确认进行删除此字典信息吗？',
                            closable: true,
                            draggable: true,
                            btnOKClass: 'btn-primary',
                            callback: function (result) {
                                if (result) {

                                    BootstrapDialog.showLoading();
                                    // 加载效果
                                    var uri = "/sys/dict/doDelete";
                                    var param = 'id=' + id;
                                    var defer1 = common.ajaxPost(uri, param);
                                    $.when(defer1).done(function (result) {
                                        BootstrapDialog.closeLoading();
                                        // 关闭加载效果
                                        if (result.status === common.CommonConstant.SUCCESS) {
                                            // $('#btnCancle').click();
                                            $('#btnQuery').click();
                                            // 刷新父页面查询列表
                                            BootstrapDialog.alert(result.msg);
                                        } else {
                                            BootstrapDialog.alert(result.msg);
                                        }
                                    });

                                }
                            }
                        });
                    });
                    // 添加键值
                    grid.find(".add_link").on("click", function (e) {
                        e.preventDefault();
                        var id = $(this).data("id");
                        // 类型
                        var type = $(this).data("type");
                        // 描述
                        var description = $(this).data("description");

                        BootstrapDialog.show({
                            title: "添加键值",
                            size: BootstrapDialog.SIZE_NORMAL,
                            // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                            closable: false,
                            message: function () {
                                var $message = $('<div></div>');
                                return $message.load(webAppPath + '/sys/dict/add?id=' + id);
                            }
                        });
                    });

                });
            }
        });
    });
});

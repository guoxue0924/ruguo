/**
 * Created by liuqing on 2017/6/7.
 */

require([configPath],
    function (c) {
        // 自定义模块，模块定义对照require-config.js,按需配置
        require(['init', 'common', 'dialog', 'jtree', /*'date', 'select',*/ 'grid'],
            function (init, common, BootstrapDialog, jtree) {
                $(function () {
                    // grid全局变量
                    var grid = "";
                    // 初始化CodeList数据
                    //common.CodeListDataSet.init(['DICT_DATA_SCOPE', 'DICT_ORG_LEVEL', 'DICT_ACCOUNT_STYLE']);

                    // 查询
                    $('#btnQuery').click(function () {
                        queryRoleList();
                    });

                    queryRoleList();

                    // 查询角色列表
                    function queryRoleList() {
                        if (grid.length > 0) {
                            grid.bootgrid("reload");
                        } else {
                            initGrid();
                        }
                    }

                    // 重置
                    $('#btnReset').click(function () {
                        $('#searchForm').resetFormData();
                    });

                    $("#btnAdd").click(function () {
                        $.paramEditRoleId = "";
                        BootstrapDialog.show({
                            title: "添加角色信息",
                            size: BootstrapDialog.SIZE_WIDE, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                            closable: false,
                            message: function () {
                                var $message = $('<div></div>');
                                return $message
                                    .load(webAppPath
                                        + '/sys/role/roleForm');
                            }
                        });
                    });

                    function initGrid() {
                        grid = $("#roleGrid").bootgrid({
                            ajax: true,
                            post: function () {
                                return {};
                            },
                            emptyPost: true,
                            navigation:0,
                            url: webAppPath
                            + '/sys/role/getAllRoleList',
                            rowSelect: true,
                            // selection: true,
                            // multiSelect: true,
                            rowCount: [-1],
                            formatters: {
                                /*"roleType": function (column, row) {
                                    // 解析codelist值
                                    return common.CodeListDataSet.getLabel('DICT_ACCOUNT_STYLE', row.roleType);
                                },
                                "roleLevel": function (column, row) {
                                    // 解析codelist值
                                    return common.CodeListDataSet.getLabel('DICT_ORG_LEVEL', row.roleLevel);
                                },
                                "dataScope": function (column, row) {
                                    // 解析codelist值
                                    return common.CodeListDataSet.getLabel('DICT_DATA_SCOPE', row.dataScope);
                                },*/
                                "operation": function (column, row) {
                                    var edit_html = " <a href='#' class=\"edit_link\" data-id='"
                                        + row.id
                                        + "' data-isenable='"
                                        + row.isEnable
                                        + "'>修改</a>";

                                    var del_html = " <a href='#' class=\"del_link\" data-id='"
                                        + row.id
                                        + "' data-isenable='"
                                        + row.isEnable
                                        + "'>删除</a>";

                                    // var detail_html = " <a href='#' class=\"assign_link\" data-id='"
                                    //     + row.id
                                    //     + "' data-level='"
                                    //     + row.level + "' data-isenable='"
                                    //     + row.isEnable
                                    //     + "'>分配</a>";
                                    return edit_html
                                        + del_html;

                                }
                            }
                        }).on("loaded.rs.jquery.bootgrid", function () {
                            grid.find(".edit_link").on("click",function (e) {
                                e.preventDefault();
                                var roleId = $(this).data("id");// 获取当前行用户ID

                                $.paramEditRoleId = roleId;

                                BootstrapDialog.show({
                                    title: "编辑角色信息",
                                    size: BootstrapDialog.SIZE_WIDE, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                                    closable: false,
                                    message: function () {
                                        var $message = $('<div></div>');
                                        return $message.load(webAppPath
                                            + '/sys/role/roleForm');
                                    }
                                });
                            });

                            // 删除角色信息
                            grid.find(".del_link").on("click",function (e) {
                                e.preventDefault();
                                var roleId = $(this).data("id");// 获取当前行用户ID

                                BootstrapDialog.confirm({
                                    message: '确认要删除该角色吗？',
                                    closable: true,
                                    draggable: true,
                                    btnOKClass: 'btn-primary',
                                    callback: function (result) {
                                        if (result) {
                                            BootstrapDialog.showLoading();// 加载效果
                                            var uri = "sys/role/deleteRole";
                                            var param = 'id=' + roleId;
                                            var defer1 = common.ajaxPost(uri, param);
                                            $.when(defer1).done(
                                                function (result) {
                                                    //BootstrapDialog.closeLoading();// 关闭加载效果
                                                    if (result.status == "success") {
                                                        $('#btnQuery').click();// 刷新父页面查询列表
                                                        BootstrapDialog.alert(result.msg);
                                                    } else {
                                                        BootstrapDialog.alert(result.msg);
                                                    }
                                                }
                                            );
                                        }
                                    }
                                });
                            });

                            grid.find(".assign_link").on("click", function (e) {
                                e.preventDefault();
                                var roleId = $(this).data("id");// 获取当前行用户ID
                                $.paramEditRoleId = roleId;
                                BootstrapDialog.show({
                                    title: "分配角色信息",
                                    size: BootstrapDialog.SIZE_WIDE, // SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                                    closable: false,
                                    message: function () {
                                        var $message = $('<div></div>');
                                        return $message
                                            .load(webAppPath
                                                + '/sys/role/roleAssign?id='
                                                + userid);
                                    }
                                });
                            });
                        });// end grid
                    }
                });
            }
        );
    });

/**
 * Created by hl on 2017/4/21.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照config.js,按需配置
    require(['init', 'common', 'dialog', 'file', 'valid', 'grid'], function (init, common, BootstrapDialog, files) {

//    	files.init();
        //重置按钮
        $('#btnReset').click(function () {
            $('#fileForm').resetFormData();
        });

        var valid = $.bootValid('#fileForm', {
            rules: {
                name: ['not_empty', {'min_length': 4}],
                email: ['not_empty']
            },
            validFiles:false//是否校验多附件
            //paddingLeft: 10
            //messages: {...}
        });
        //保存按钮submit提交
        $('#btnSaveSubmit').click(function () {
        	
            if (valid.validate()) {
            	
                BootstrapDialog.confirm({
                    message: '确认上传？',
                    closable: true,
                    draggable: true,
                    btnOKClass: 'btn-primary',
                    callback: function (result) {
                        if (result) {

                            BootstrapDialog.showLoading();//加载效果
                            var uri = "demo/upload/fileupload";
                            var defer1 = common.ajaxSubmit(uri, 'fileForm');
                            $.when(defer1).done(function (result) {
                                BootstrapDialog.closeLoading();//关闭加载效果
                                result = $.parseJSON(result);
                                if (result.status === common.CommonConstant.SUCCESS) {
                                    grid.bootgrid('reload');
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

        var grid = $("#fileGrid").bootgrid({
            ajax: true,
            post: function () {
                var param = $('#searchForm').serializeObject();
                return param;
            },
            url: webAppPath + '/demo/upload/fileupload/list',
            rowSelect: true,
            selection: true,
            multiSelect: true,
//            keepSelection: true,
//            navigation: 0,
            emptyPost: true,
            rowCount: [10, 20, 50],
            formatters: {
                "demoUser.name": function (column, row) {
                    var p = {};
                    p.title = row.demoUser.name;
                    p.a = "<a href='#' class=\"topurchase\" >" + row.demoUser.name + "</a>";
                    return p;
                },
                "demoUser.email": function (column, row) {
                    return row.demoUser.email;
                },
                "operation": function (column, row) {
                    var detail_html = " <a href='#' class=\"detail_link\" data-fileid='" + row.fileId + "'>查看</a>";
                    var download_html = " <a href='#' class=\"download_link\" data-fileid='" + row.fileId + "'>下载</a>";
                    var del_html = " <a href='#' class=\"del_link\" data-id='" + row.id + "'>删除</a>";
                    return detail_html + download_html + del_html;
                }
            }
        }).on("loaded.rs.jquery.bootgrid", function () {
            grid.find(".detail_link").on("click", function (e) {
                e.preventDefault();
                var fileId = $(this).data("fileid");
                files.showImg(fileId);

            });
            grid.find(".download_link").on("click", function (e) {
                e.preventDefault();
                var fileId = $(this).data("fileid");
                files.downFile(fileId);
            });
            //先要删除业务表中附件记录，再删除服务器中附件信息
            grid.find(".del_link").on("click", function (e) {
                e.preventDefault();
                var id = $(this).data("id");//获取当前行用户ID
                BootstrapDialog.confirm({
                    message: '确认删除附件？',
                    closable: true,
                    draggable: true,
                    btnOKClass: 'btn-primary',
                    callback: function (result) {
                        if (result) {

                            BootstrapDialog.showLoading();//加载效果
                            var uri = "demo/upload/fileupload/delete";
                            var param = 'id=' + id;
                            var defer1 = common.ajaxPost(uri, param);
                            $.when(defer1).done(function (result) {
//                                BootstrapDialog.closeLoading();//关闭加载效果
                                if (result.status == common.CommonConstant.SUCCESS) {
                                    grid.bootgrid("reload");//刷新查询列表
                                    BootstrapDialog.alert(result.msg);
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

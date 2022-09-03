/**
 * Created by hl on 2017/4/4.
 */
require.config({
    baseUrl: webAppPath + "/",
    paths: {
        jquery: "plugins/jquery/jquery-1.11.3.min",
        bootstrap: "plugins/bootstrap/js/bootstrap.min",
        dialog: "plugins/bootstrap/js/bs-dialog.min",//提示框，模态框，引用使用
        init: "modules/common/header",//页面基本
        common: "modules/common/common",//公共函数类，引用使用
        date: "plugins/bootstrap/js/bs-datetimepicker.min",//日期控件
        select: "plugins/bootstrap/js/bs-select.min",//下拉控件
        grid: "plugins/bootstrap/js/bs-grid.min",//表格控件
        file: "plugins/bootstrap/js/bs-fileupload.min",//附件上传
//        text: "/require/require-text",
        css: "plugins/require/require-css",
        valid: "plugins/bootstrap/js/bs-valid.min",//表单验证，引用使用
        jqform: "plugins/bootstrap/js/jquery.form.min",
        spinner: "plugins/bootstrap/js/bs-spinner.min",//数字选择器
//        tag: "plugins/bootstrap/js/bs-tag",//快速录入标签组件
//        fixedtable: "plugins/bootstrap/js/bs-fixedheadertable",//table表格固定插件
        upload: "plugins/bootstrap/js/bs-upload",//图片上传
        uploadp: "plugins/bootstrap/js/bs-upload-picture",//图片上传
        area: "plugins/bootstrap/js/bs-area.min",//行政区划
        treebox: "plugins/bootstrap/js/bs-treebox", // 弹出框树控件
        jtree: "plugins/jtree/jquery.ztree.all-3.5.min",
        selectuser: "plugins/bootstrap/js/bs-selectuser", // 选择人员控件
        expexcel: "plugins/bootstrap/js/bs-export", // 导出功能
        calendar: "plugins/bootstrap/js/bs-calendar.min", //  added by liujia - 日历控件
        editor: "plugins/editor/summernote/summernote.min", //  富文本编辑器
        typeahead: "plugins/bootstrap/js/bs-typeahead.min", //  预先输入
        treetable: "plugins/bootstrap/js/bs-treetable.min",//  树型表格
        doublebox: "plugins/bootstrap/js/doublebox-bootstrap"
    },
    shim: {
        init: ['jquery', 'bootstrap'],
        bootstrap: ['jquery'],
        jqform: ['bootstrap'],
        valid: ['bootstrap', 'jqform', 'css!' + webAppPath + '/plugins/bootstrap/css/bs-valid.min'],
        spinner: ['bootstrap', 'css!' + webAppPath + '/plugins/bootstrap/css/bs-spinner.min'],
        upload: ['bootstrap', 'css!' + webAppPath + '/plugins/bootstrap/css/bs-upload'],
//        fixedtable: ['bootstrap'],
        jtree: ['jquery', 'css!' + webAppPath + '/plugins/jtree/css/bs-zTree.min'],
        calendar: ['bootstrap', 'css!' + webAppPath + '/plugins/bootstrap/css/bs-calendar'],//  added by liujia - 日历控件样式
        editor: ['bootstrap', 'css!' + webAppPath + '/plugins/editor/summernote/summernote.min'],
        doublebox: ['bootstrap', 'css!' + webAppPath + '/plugins/bootstrap/css/doublebox-bootstrap']
    }
});
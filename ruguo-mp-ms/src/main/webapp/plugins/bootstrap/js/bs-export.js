/**
 * 附件上传插件
 * Created by hl on 2017/4/22.
 */
define(['jquery', 'dialog', 'common'], function ($, BootstrapDialog, common) {

    var ExportUtils = function () {
    };

    /**
     * 导出excel
     * url 请求路径
     * jsonParam 参数
     */
    ExportUtils.exportExcel = function (url, jsonParam) {
        if ($.isEmptyObject(url)) {
            throw new Error("导出url不能为空");
            return;
        }
        var strURL = webAppPath + url;

        if (ExportUtils.isAllEmptyJson(jsonParam)) {
            throw new Error("导出参数不能为空");
            return;
        }
        // var jsonStr = $.parseJSON(jsonParam);
        var jsonStr = JSON.stringify(jsonParam);
        jsonStr = jsonStr.replace(/\"/g,"\'")
        // excel导出
        jQuery('<form action="' + strURL + '" method="' + ('post') + '">' +  // action请求路径及推送方法
            '<input type="hidden" name="param" value="' + jsonStr + '"/>' + // 参数
            '</form>')
            .appendTo('body').submit().remove();
        // common.ajaxJson(url, jsonParam);
    }

    /**
     * json中空串设置成null
     * @param json
     * @return json
     */
    ExportUtils.isAllEmptyJson = function (json) {
        if ($.isEmptyObject(json) || typeof json != 'object' ) {
            return true;
        }
        for(var k in json){
            if (!$.isEmptyObject(json[k])) {
                return false;
            }
        }
        return true;
    };

    return ExportUtils;
})
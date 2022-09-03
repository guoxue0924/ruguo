/**
 * Created by liuqing on 2017/4/21.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'dialog', 'common', 'typeahead'], function (init,BootstrapDialog, common) {
        $(function () {
            // 关闭loading
            //BootstrapDialog.closeLoading();

            var parameters = $.selectUserDialogParameter;
            if (common.isEmpty(parameters)) {
                return;
            }

            var userListObj = common.ajaxJson('common/selectTree/getSelectUserList',{},false);

            var newData = userListObj.rows;
            var len = newData.length;
            for (var i = 0; i < len; i++) {
                var tempName = newData[i]["name"];
                var roleName = newData[i]["userRoleName"];
                var orgName = newData[i]["sysOrgName"];

                newData[i]["tempName"] = tempName;
                newData[i]["name"] = tempName + "（" + roleName + "-" + orgName + "）";
            }

            var selectValue = null;

            // 初始化typeahead
            var $input = $(".typeahead").typeahead({
                source:newData,
                items:'all',
			    showHintOnFocus:'all',
                autoSelect: true,//自动选择
                // fitToElement: true,
                afterSelect:function(item){//选中后
                    selectValue = item;
                }
            });

            // 关闭窗口
            $('#btnSelectUserDialogCancle').click(function () {
                $.selectUserDialog.close();
            });

            // 保存按钮
            $('#btnSelectUserDialogSave').click(function () {
                var current = $input.typeahead("getActive");
                console.log('getActive：',current);
                console.log('$input.val()：',$input.val());
                var currentName = $input.val();

                if (common.isEmpty(selectValue) && common.isEmpty(currentName)) {
                    selectValue = {};
                    var objArray = [];
                    objArray.push(selectValue);
                    var param = {};
                    parameters._callback(objArray, param);
                    $.selectUserDialog.close();

                } else if (!common.isEmpty(selectValue) && selectValue["name"] == currentName) {
                    var objArray = [];
                    objArray.push(selectValue);
                    selectValue["name"] = selectValue["tempName"];
                    var param = {};
                    parameters._callback(objArray, param);
                    $.selectUserDialog.close();
                } else {
                    var param = {"name": currentName};
                    BootstrapDialog.confirm({
                        message : '此用户不是已存在的用户，是否保存？',
                        closable : true,
                        draggable : true,
                        btnOKClass : 'btn-primary',
                        callback : function(result) {
                            if (result) {
                                BootstrapDialog.showLoading();// 加载效果
                                var uri = "common/selectTree/saveSelectUser";
                                var defer1 = common.ajaxJson(uri, param);
                                $.when(defer1).done(function(result) {

                                    //BootstrapDialog.closeLoading();// 关闭加载效果
                                    if (result.success === "0") {

                                        var objArray = [];
                                        objArray.push(result.data);
                                        var param = {};
                                        parameters._callback(objArray, param);
                                        $.selectUserDialog.close();
                                    } else {
                                        //BootstrapDialog.closeLoading();// 加载效果
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

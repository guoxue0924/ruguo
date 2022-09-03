/**
 * Created by wanggang on 2017/6/5.
 */

require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['dialog', 'common', 'valid'], function (BootstrapDialog, common) {
        $(function () {
            //初始化表单提示语
            $('[data-toggle="popover"]').popover();
            var firstData = $('#memberLevelForm').serializeData();
          //重置按钮 
            $('#btnReset').click(function () {
            	$('#memberLevelForm')[0].reset();
            	/** by liuhuan at 20170512 修改重置列表 begin */    
            	$('.selectpicker').selectpicker('refresh');
            	/** by liuhuan at 20170512 修改重置列表 end */    
            });
            //关闭窗口
            $('#btnCancle').click(function () {
                BootstrapDialog.closeAll();
                
            });
            var regex_isNum=/^(([1-9][0-9]*)|[0]|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
            var regex_Num = /^([1-9]\d?|100)$/;
            var valid = $.bootValid('#memberLevelForm', {
                rules: {
                	memberLevelName: ['not_empty', {'max_length': 50}],
                    memberLevelDesc: ['not_empty', {'max_length': 200}],
                    memberLevelDiscount:['not_empty',{'regex':regex_Num}],
                    memberLevelMin: ['not_empty', {'max_length': 10},{'regex':regex_isNum}],
                    memberLevelMax: ['not_empty', {'max_length': 10},{'regex':regex_isNum}]
                },
                messages: {
                	memberLevelMin: {regex: '请输入10位有效的金额！'},
                	memberLevelMax: {regex: '请输入10位有效的金额！'}
                }
            });

            //保存按钮
            $('#btnSave').click(function () {
                if (valid.validate()) {
                    var min  = parseFloat($("#smemberLevelMin").val());
                    var max = parseFloat($("#smemberLevelMax").val());
                    if (min > max) {
                        valid.show({memberLevelMax: "等级上限必须大于等级下限！"}); 
                    	return; 
                	}

                    BootstrapDialog.confirm({
                        message: '确认保存用户信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {
                                BootstrapDialog.showLoading();//加载效果
                                var uri = "member/memberlevel/save";
                                var editData = $('#memberLevelForm').serializeData();
                                if (firstData != editData) {
                                	var defer1 = common.ajaxPost(uri, editData);
                                    $.when(defer1).done(function (result) {
                                        if (result.status === common.CommonConstant.SUCCESS) {
                                        	common.unSavedConfirmCloseable = true;//取消未保存提示。 
                                        	$('#btnCancle').click();
                                            $('#btnQuery').click();//刷新父页面查询列表
                                            BootstrapDialog.alert(result.msg);
                                        } else {                 
                                            BootstrapDialog.alert(result.msg);
                                        }
                                     });
                                } else {
                                    BootstrapDialog.closeLoading();//加载效果
                                    BootstrapDialog.alert("会员等级信息没有修改,不能保存");
                                } 
                             }
                        }
                    });
                }
            });
        });
    });
});

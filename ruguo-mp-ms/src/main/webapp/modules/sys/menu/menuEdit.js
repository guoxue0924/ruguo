/**
 * Created by hl on 2017/4/4.
 */

require([configPath], function (c) {
	
	
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['dialog', 'common', 'valid', 'treebox'], function (BootstrapDialog, common) {
        $(function () {
        	//关闭loading
            BootstrapDialog.closeLoading();
//            common.showUnSavedConfirm();
            //添加dialog关闭事件,显示'是否关闭'确认框
//            common.showUnSavedConfirm($.addUserDialog);
//            console.log(BootstrapDialog.dialogs);
//            var modal ;
//            $.each(BootstrapDialog.dialogs,function(i,o){
//            	console.log(i,o.$modalContent)
//            	modal = o.$modalContent;
//            })
            
            $('#parentname').SelectTreebox({
                treeName: "menu",
                valueId: "parentid",
                valueName: "parentname",
                labelId: "parentname",
                extId: $('#menuid').length>0 ? $('#menuid').val() :'',
                selectIds: $('#parentid').length>0 ? $('#parentid').val() :'',
                muliSelected: false,
                callback: function (value) {
//                    console.log("结果：");
                    console.log(value);
                }
            });
            
            $('#menuBtn').click(function () {
                // 显示机构选择弹出框
                $('#parentname').SelectTreebox('show');
            });
            
        	$('[data-toggle="popover"]').popover();
//            $('.filestyle').filestyle();
            //关闭窗口
            $('#btnCancle').click(function () {
//            	$.addUserDialog.close();
                BootstrapDialog.closeAll();
                
            });
            /*$('#s_user2').SelectUser({
                valueId: "s_user2value",
                labelId: "s_user2",
                title: "选择用户123",
                callback: function (value) {

                }
            });
            $('#user2btn').click(function () {
                // 显示用户选择弹出框
                $('#s_user2').SelectUser('show');
            });*/
            //表单校验规则
//            messages: {
//                not_empty: '该项为必填项！'
//                , min_length: '请输入至少 :value 位有效值！'
//                , max_length: '请输入少于 :value 位有效值！'
//                , regex: ''
//                , email: '请输入一个有效的邮箱地址！'
//                , url: '请输入一个有效的网址！'
//                , exact_length: '请输入 :value 位有效值！'
//                , equals: ''
//                , ip: '请输入一个有效的IP地址！'
//                , credit_card: '请输入有效的信用卡号码！'
//                , alpha: '请输入字符！'
//                , alpha_numeric: '存在非法字符！'
//                , alpha_number: '请输入合法字符！'
//                , alpha_dash: '存在非法字符！'
//                , digit: '请输入数字！'
//                , numeric: '请输入有效数字！'
//                , decimal: '请输入金额格式数字！'
//                , matches: 'Must match the previous value.'
//                , not_matches: 'Must not match the previous value.'
//                , phone_num: '请输入11位有效的手机号码！'
//                , tel: '请输入有效的电话号码！'
//                , id_num: '请输入有效的身份证号码！'
//                , ch_char: '只能输入汉字！'
//                , date: '日期格式错误，正确格式：2015-12-02'
//                , post: '请输入正确的邮政编码!'
//                , bank_count_no: '请输入正确的银行账户!'
//                , alpha_organ: '请输入合理字符，可以是数字，字母和横杠，最大长度为18位字符！'
//            }
            var valid = $.bootValid('#menuForm', {
                rules: {
                    name: ['not_empty', {'max_length': 20}],
            		sort: ['not_empty','numeric']
                }
                //paddingLeft: 10
                //messages: {...}
            });


            //保存按钮
            $('#btnSave').click(function () {
                if (valid.validate()) {
                	BootstrapDialog.showLoading();//加载效果
                    var uri = "sys/menu/save";
                    var param = $('#menuForm').serializeData(true);//true:收集空值，默认false
                    var defer1 = common.ajaxPost(uri, param);
                    
                    $.when(defer1).done(function (result) {
                        if (result.status === common.CommonConstant.SUCCESS) {
//                        	common.unSavedConfirmCloseable = true;//取消未保存提示。
                            $('#btnCancle').click();
//                            $('#btnQuery').click();//刷新父页面查询列表
                            BootstrapDialog.alert(result.msg,function(){
                            	BootstrapDialog.showLoading('正在查询');//加载效果
                                window.location.href = webAppPath + '/sys/menu/';
                            });
                        } else {
                            BootstrapDialog.alert(result.msg);
                        }
                    });

                }
            });

        });
    });
});

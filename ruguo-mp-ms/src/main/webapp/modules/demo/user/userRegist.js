/**
 * Created by hl on 2017/4/4.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog', 'select', 'date', 'valid'/*, 'files', 'spinner'*/], function (init, common, BootstrapDialog) {
        $(function () {
            //初始化表单提示语
            $('[data-toggle="popover"]').popover();
//            $('.filestyle').filestyle();
            // 初始化日期控件
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

            //重置按钮
            $('#btnReset').click(function () {
            	valid.removeAll();//清除所有校验提示
            	$('#searchForm').resetFormData();
            });
            //禁用下拉框
            $('#btnSelectDisabled').click(function () {
            	$('#userType').selectpicker('disabled');
            });
            //启用下拉框
            $('#btnSelectEnabled').click(function () {
            	$('#userType').selectpicker('enabled');
            });

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
            var regex = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
            var valid = $.bootValid('#userForm', {
                rules: {
                    name: ['not_empty', {'min_length': 4},'strong_pwd'],
//                    email: ['not_empty', 'email', {'min_length': 4},{'regex':regex}]
                	email:[],
                    phone: ['not_empty', 'tel'],
                    mobile: ['not_empty', 'phone_num'],
                    birthday: ['not_empty', 'date'],
                    userType: ['not_empty'],
                    loginFlag: ['not_empty'],
                    hobby : ['not_empty'],
                    fqkhhqc : ['not_empty'],
                    file : ['not_empty'],
                    xzrq1 : ['not_empty'],
                    xzrq2 : ['not_empty'],
                    spinner : ['not_empty'],
                    remark: ['not_empty']
                },
                //paddingLeft: 10
                messages: {
                	email: {regex: 'hey you forgot to enter your username'}
                }
            });

            //触发自定义提示语
            $('#btnShowValid').click(function () {
            	
            	valid.show({email: "自定义校验失败提示语（邮箱）"});
            	valid.show({name: "自定义校验失败语名字",phone: "自定义校验失败语phone"});
            	
            });
            //清除自定义提示语
            $('#btnRemoveValid').click(function () {
            	
            	valid.remove('email');
            	valid.remove(['name','phone']);
            	
            });
            //保存按钮post提交
            $('#btnSavePost').click(function () {
            	
//            	valid.show({email: "自定义校验失败语"});
//            	valid.remove('email');
//            	valid.remove(['name','email']);
                if (valid.validate()) {
                	
                	return;

                    BootstrapDialog.confirm({
                        message: '确认保存用户信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "demo/user/save";
                                var param = $('#userForm').serializeData();
                                if(param.length == 0){
                                	BootstrapDialog.alert('啥也没填');
                                }
                                var defer1 = common.ajaxPost(uri, param);
                                $.when(defer1).done(function (result) {
                                    BootstrapDialog.closeLoading();//关闭加载效果
                                    if (result.status === common.CommonConstant.SUCCESS) {
                                        $('#btnReset').click();
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
            
            //保存按钮submit提交
            $('#btnSaveSubmit').click(function () {
            	if (valid.validate()) {
            		
            		BootstrapDialog.confirm({
            			message: '确认保存用户信息？',
            			closable: true,
            			draggable: true,
            			btnOKClass: 'btn-primary',
            			callback: function (result) {
            				if (result) {
            					
            					BootstrapDialog.showLoading();//加载效果
            					var uri = "demo/user/save";
            					var defer1 = common.ajaxSubmit(uri,'userForm');
            					$.when(defer1).done(function (result) {
            						BootstrapDialog.closeLoading();//关闭加载效果
            						if (result.status === common.CommonConstant.SUCCESS) {
            							$('#btnReset').click();
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
            
            //保存按钮json提交
            $('#btnSavePostJson').click(function () {
            	if (valid.validate()) {
            		
            		BootstrapDialog.confirm({
            			message: '确认保存用户信息？',
            			closable: true,
            			draggable: true,
            			btnOKClass: 'btn-primary',
            			callback: function (result) {
            				if (result) {
            					
            					BootstrapDialog.showLoading();//加载效果
            					 var uri = "demo/user/save2";
//                                 var user ={}
//                                 user.email = $('input[name="email"]').val();
//                                 user.name = $('input[name="name"]').val();
//                                 user.phone = $('input[name="phone"]').val();
//                                 var param = $('#userForm').serialize(); 
                                 var user = $('#userForm').serializeObject();
                                 if($.isEmptyObject(user)){
                                	 BootstrapDialog.alert('啥也没填');
                                 }
                                var defer1 = common.ajaxJson(uri, user);
            					$.when(defer1).done(function (result) {
            						BootstrapDialog.closeLoading();//关闭加载效果
            						if (result.status === common.CommonConstant.SUCCESS) {
            							$('#btnReset').click();
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
            //数字选择器
            /*            $(".spinner").spinner({
             max: 100,
             min: 0,
             numberFormat: "n",
             stop: function (event, ui) {
             var $this = $(this);
             var purchasenum = rmoney($this.val());
             if (!$.isNumeric(purchasenum)) {
             $this.val("0");
             } else if (purchasenum > 1000) {//录入大于1000,则返回1000
             $this.val("1000");
             } else {
             $this.val(purchasenum);
             }
             }
             });*/

        });
    });
});

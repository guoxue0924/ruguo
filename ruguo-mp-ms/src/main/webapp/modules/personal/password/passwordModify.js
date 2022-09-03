/**
 * 
 */
require([configPath], function (c) {
	require(['init','common', 'dialog', 'valid'], function (init, common, dialog) {
		$(function () {
			//初始化表单提示语
            $('[data-toggle="popover"]').popover();

			var valid = $.bootValid('#optForm', {
                rules: {
                	oldPassword: ['not_empty'],
                	newPassword: ['not_empty', 'strong_pwd', {'min_length': 12}, {'max_length': 32}],
                	confirmNewPassword: ['not_empty'],
                },
                messages: {
                	newPassword: {
                		strong_pwd: "新密码不符合密码规则，请重新输入！",
                		min_length: "新密码不符合密码规则，请重新输入！",
                		max_length: "新密码不符合密码规则，请重新输入！"
                	}
                }
            });
			
			//保存按钮
            $('#saveBtn').click(function () {   
                if (valid.validate()) {
                	if($("#confirmNewPassword").val() != $("#newPassword").val()){
                		valid.show({confirmNewPassword: "新密码与确认新密码不一致，请重新输入！"});
                		return false;
                	} else if($("#confirmNewPassword").val() == $("#newPassword").val()){
                		valid.remove('confirmNewPassword');
                	}
                	
                	var param = $('#optForm').serialize();
                	var uriCheckPassword = "personal/password/checkPassword";
                    var doAjaxCheckPassword = common.ajaxPost(uriCheckPassword, param);
                    $.when(doAjaxCheckPassword).done(function (resultCheckPassword) {
                    	 if (resultCheckPassword.data.success == common.CommonConstant["RESULT_SUCCESS"]) {
                    		 dialog.confirm({
                                 message: '确认修改密码？',
                                 closable: true,
                                 draggable: true,
                                 btnOKClass: 'btn-primary',
                                 callback: function (result) {
                                     if (result) {
                                         dialog.showLoading();//加载效果
                                         var uri = "personal/password/modifyPassword";
                                         var doAjax = common.ajaxPost(uri, param);
                                         $.when(doAjax).done(function (result) {
                                             dialog.closeLoading();//关闭加载效果
                                             if (result.data.success == common.CommonConstant["RESULT_SUCCESS"]) {
                                             	 resetForm();
                                                 dialog.alert('修改密码成功');
                                                 if (!common.isEmpty($("#resetPaword").val()) && $("#resetPaword").val() == '1') {
                                                	 if (!common.isEmpty($("#resetUserInfo").val()) && $("#resetUserInfo").val() == '1') {
                                                	     window.location = webAppPath + "/personal/PersonAffair/indexFirst";
                                                	 } else {
                                                		 window.location = webAppPath + "/personal/PersonAffair/index"; 
                                                	 }
                                                 } else {
                                                	 //window.location = webAppPath + "/logout";
                                                	 window.location = webAppPath + "/personal/PersonAffair/index";
                                                	 
                                                 }
                                             } else if(result.data.success == common.CommonConstant["RESULT_FAILURE"]){
                                                 dialog.alert(result.data.errorMsg);
                                             }
                                         });
                                     }
                                 }
                             });                  		 
                         } else if(resultCheckPassword.data.success == common.CommonConstant["RESULT_FAILURE"]){
                             dialog.alert(resultCheckPassword.data.errorMsg);
                         }
                    	
                    });
                }
            });
            
            function resetForm(){
            	$('#optForm')[0].reset();
            }
            
		});
	});
});

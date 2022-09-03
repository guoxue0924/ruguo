6/**
 * Created by zhuming on 2017/9/28.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'common','dialog','expexcel','grid','date','select','valid'], function (init,common,BootstrapDialog,ExportUtil) {
        $(function () {
        	 //关闭窗口
            $('#btnClose').click(function () {
                BootstrapDialog.closeAll();
            }); 
            
            //给文本框赋值
            /*var re = $('#hiddenInput').val();
            $('#inputReply').val(re);*/
            
            var valid = $.bootValid('#replyContentForm', {
                rules: {
                	replyContent:['not_empty',{'max_length': 500}]
                },
                messages: {
                	replyContent: {regex: '请输入500位有效字符！'}
                }
                
            });
            
          //保存按钮submit提交
            $('#btnSaveSubmit').click(function () {
            	 if (valid.validate()) {
            	var msg = "回复失败";
            	var replyContent = $('#inputReply').val();
            	var id = $('#hiddenInputId').val();
            	var param={"replyContent":replyContent,"id":id};
				BootstrapDialog.confirm({
							message : '确认进行回复？',
							closable : true,
							draggable : true,
							btnOKClass : 'btn-primary',
							callback : function(result) {
								if (result) {
									var uri = "/feedback/information/updateMemberFeedback";
									var defer1 = common.ajaxJson(uri,param);
									 $.when(defer1).done(function (result) {
		                                    if (result.status === common.CommonConstant.SUCCESS) {
		                                    	$('#btnClose').click();
		                                        $('#btnQuery').click();//刷新父页面查询列表
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
        
        });
    });
});

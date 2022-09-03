/**
 * Created by hl on 2017/4/4.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog', 'select','editor'/*, 'files', 'spinner'*/], function (init, common, BootstrapDialog) {
        $(function () {
            //初始化表单提示语
//            $('[data-toggle="popover"]').popover();
            //初始化下拉控件
            $('.selectpicker').selectpicker();

            $('#summernote').summernote({
            	height: 200,
            	placeholder: '在此编辑公告内容',
            	toolbar: [
            		['style1', ['style']],
            	    ['style2', ['bold', 'italic', 'underline', 'clear']],
            	    ['fontsize', ['fontsize','color']],
            	    ['para', ['paragraph', 'ul', 'ol', 'table', 'hr']],
            	    ['misc1', ['fullscreen','codeview']],
            	    ['misc2', ['undo','redo']]
            	  ]
            
            });
            
            
            //重置按钮
            $('#btnReset').click(function () {
            	$('#summernote').summernote('reset');
            });
            //禁用
            $('#btnDisabled').click(function () {
            	$('#summernote').summernote('disable');
            });
            //启用
            $('#btnEnabled').click(function () {
            	$('#summernote').summernote('enable');
            });

            //获取值
            $('#btnGetContents').click(function () {
            	var markupStr = $('#summernote').summernote('code');
            	alert(markupStr);
//            	BootstrapDialog.show({
//                    title: "获取源码",
//                    size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
//                    closable: false,
//                    message: function () {
//                    	return markupStr;
////                        var $message = $('<div></div>');
////                        return $message.load(webAppPath + '/demo/user/form?id=' + userid);
//                    }
//                });
            	
            });
            //校验是否为空
            $('#btnEmpty').click(function () {
            	if($('#summernote').summernote('isEmpty')){
            		BootstrapDialog.alert('这是个空文本');
            	}else{
            		BootstrapDialog.alert('这是个非空文本');
            	}
            });
            //赋值
            $('#btnSetVal1').click(function () {
            	$('#summernote').summernote('insertText', '<span style="font-size: 36px;">insertText</span>');
            	
            });
            //code
            $('#btnSetVal2').click(function () {
            	$('#summernote').summernote('code', '<span style="font-size: 36px;color:red">insertText</span>');
            	
            });
            //insertNode
            $('#btnSetVal3').click(function () {
            	$('#summernote').summernote('insertNode', '<span style="font-size: 36px;">aosdijfoaisdjfoasijdofiasjdofijasodjfaosijfdo</span>');
            	
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

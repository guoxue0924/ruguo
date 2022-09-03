/**
 * Created by hl on 2017-05-02.

 */
require.config({
	    baseUrl: webAppPath,
	    paths: {
	        jquery: "plugins/jquery/jquery-1.11.3.min",
	        bootstrap: "plugins/bootstrap/js/bootstrap.min",
	        dialog: "plugins/bootstrap/js/bs-dialog.min",//提示框，模态框，引用使用
	        init: "modules/common/header",//页面基本
	        common: "modules/common/common",//公共函数类，引用使用
	    },
	    shim: {
	    	init: ['jquery', 'bootstrap'],
	        bootstrap: ['jquery']
	    }
	}
);

require(['jquery', 'bootstrap', 'dialog', 'init', 'common'], function ($, bs, BootstrapDialog,init, common) {
    $(function () {
    	
    	$("#forgetPassword").on('click', function () {
		    	 BootstrapDialog.show({
		            title: "提示",
		            size: BootstrapDialog.SIZE_SMALL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
		            closable: false,
		            message: function () {
		                var $message = $('<div></div>');
		               return $message.load(webAppPath + '/forgetpassword');
		            }
		        });
		    });
    	
    	 $('#loginBtn').on('click', function () {
		    var $btn = $(this).button('loading')
		    // business logic...
//		    $btn.button('reset')
		  })
		  
		  
		  
		  
    })
    
  
});


        

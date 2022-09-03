/**
 * Created by hl on 2017-05-02.

 */
require.config({
	    baseUrl: webAppPath,
	    paths: {
	        jquery: "plugins/jquery/jquery-1.11.3.min",
	        bootstrap: "plugins/bootstrap/js/bootstrap.min",
	        dialog: "plugins/bootstrap/js/bs-dialog.min",//提示框，模态框，引用使用
	    },
	    shim: {
	        bootstrap: ['jquery']
	    }
	}
);

require(['jquery', 'bootstrap', 'dialog'], function ($, bs, BootstrapDialog) {
    $(function () {
    	$('#btnCancel').click(function () {
        	BootstrapDialog.closeAll();
        	
        });
    	
    })
    
  
});


        

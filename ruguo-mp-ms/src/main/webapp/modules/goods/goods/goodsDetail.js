/**
 * Created by guoxue on 2017/6/5.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog','file', 'select', 'date', 'valid','editor', 'treebox'], function (init, common, BootstrapDialog,files) {
        $(function () {
        	var flag = true;
        	
        	files.init();
            //初始化表单提示语
            $('[data-toggle="popover"]').popover();
            
            common.showUnSavedConfirm($.detailDialog);
            
            //初始化富文本编辑器
            $('#summernote2').summernote({
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
            
            $('#gclass').SelectTreebox({
        		treeName: "goods",//固定
        		valueId: "goodsClass",//页面保存节点ID用的
        		labelId: "gclass",//页面显示节点名用的
        		title: "商品分类选择",//弹出框标题
        		muliSelected: false,//单选/多选
        		callback: function (value) {
        		//                    console.log("结果：");
        		//                    console.log(value);
        		}
    		});
    		$('#editjgbtn').click(function () {
    		    // 显示机构选择弹出框
    		    $('#gclass').SelectTreebox('show');
    		});
          //初始化CodeList数据
            common.CodeListDataSet.init(['DICT_GOODS_VALIDITY_UNIT','DICT_GOODS_TYPE', 'DICT_GOODS_STATUS']);
            //初始化下拉控件
            $('.selectpicker').selectpicker();

            
          //取消按钮
            $('#btnCancel2').click(function () {
            	$.detailDialog2.close();
            	
            	
            });
            
            $('#summernote2').summernote('code', $('#goodsDetails2').val());
            
            
            
        });
    });
});

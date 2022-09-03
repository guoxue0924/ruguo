/**
 * Created by guoxue on 2017/6/5.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog', 'doublebox'], function (init, common, BootstrapDialog,doublebox) {
        $(function () {
        	 var uri = "goods/goods/gettags";
        	 var param = {
        			 "goodsCode":$('.goodsCodeTag').val()
        	 };
             var defer1 = common.ajaxPost(uri, param);
             $.when(defer1).done(function (result) {
                   if (result !== null) {
                   	common.unSavedConfirmCloseable = true;//取消未保存提示。
                   	var demo2 = $('.demo').doublebox({
                        nonSelectedListLabel: '备选标签',
                        selectedListLabel: '已选标签',
                        preserveSelectionOnMove: 'moved',
                        moveOnSelect: false,
                        nonSelectedList:result.resultL,
                        selectedList:result.resultR,
                        optionValue:"id",
                        optionText:"name",
                        doubleMove:true,
                      });
                   } else {
                   	 
                   }
            	 
           		  
               });
        	
       
           
            

           
            //保存按钮submit提交
            $('#btnSavetagsSubmit').click(function () {
            	
                    BootstrapDialog.confirm({
                        message: '确认保存标签信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {
                                var arr = new Array(); //数组定义标准形式，不要写成Array arr = new Array();
                                var tagsVal = ""; //定义变量全部保存
                                var tagsText = ""; //定义变量全部保存
                                var i=0;
                                $("#bootstrap-duallistbox-selected-list_doublebox option").each(function () {
                                  var txt = $(this).text(); //获取单个text
                                  var val = $(this).val(); //获取单个value
                                  tagsVal = tagsVal +val+",";
                                  tagsText = tagsText +txt+",";
                                  i++;
                                  
                                });
                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goods/goods/savetags";
                                var param = {
                                		"goodsCode":$('.goodsCodeTag').val(),
                                		"goodsTags":tagsVal
                           	 };
                                var defer1 = common.ajaxPost(uri, param);
                                $.when(defer1).done(function (result) {
                                    if (result.status === common.CommonConstant.SUCCESS) {
                                    	common.unSavedConfirmCloseable = true;//取消未保存提示。
                                    	$.addTagsDialog.close();
                                    	$('#tagMatchedNumCount').val(i);
                                    } else {
                                        BootstrapDialog.closeLoading();//加载效果
                                        BootstrapDialog.alert(result.msg);
                                    }
                                });
                                
                                /*$("#goodsTags").val(tagsVal);*/
                                

                            }
                        }
                    });

            });
            
           
          


            
            
        });
    });
});



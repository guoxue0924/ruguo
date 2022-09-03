/**
 * Created by guoxue on 2017/6/5.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common', 'dialog', 'date'], function (init, common, BootstrapDialog) {
        $(function () {
        		
            $('#btnCancel').click(function () {
            	BootstrapDialog.closeAll();
                
            });
            $('#btnSavePost').click(function () {
            	var goodsCode = $('#code').val();
            	var jsonArray = [];
            	var table =document.getElementById("contentTable1");
            	var rows = table.rows.length;
            	var  reg = /^(-?\d+)(\.\d+)?$/;   
            	   for(var i=2;i<rows;i++){
            		   var t1=$("#contentTable1 tr:eq("+i+") td:eq(0)").text();
            		   var t2=$("#contentTable1 tr:eq("+i+") td:eq(1)").text();
            		   var t3=$("#contentTable1 tr:eq("+i+") td:eq(2)").text();
            		   var t4=$("#contentTable1 tr:eq("+i+")").find("td:eq(3) input").val();
            		   if(!reg.test(t4) || t4.length > 3){
            			   BootstrapDialog.alert('请输入小于3位的正整数！');
            			   return;
            		   }
            		   jsonArray
						.push({
							"goodsCode" : goodsCode,
							 "id" : t1,
							 "memberLevelCode" : t2,
							 "memberLevelName" : t3,
							 "memberRate" : t4,
						});
            	   }
				BootstrapDialog
						.confirm({
							message : '确认进行保存？',
							closable : true,
							draggable : true,
							btnOKClass : 'btn-primary',
							callback : function(result) {
								if (result) {
									var param = {
											"goodsRateList" : jsonArray,
									};
									var uri = "/goods/goods/savegoodsratelist";

									var defer1 = common.ajaxJson(uri,param);
									 $.when(defer1).done(function (result) {
		                                    if (result.status === common.CommonConstant.SUCCESS) {
		                                        BootstrapDialog.closeAll();
		                                    } else {
		                                    BootstrapDialog.closeLoading();//加载效果
		                                        BootstrapDialog.alert(result.msg);
		                                    }
		                                });

								}
							}
						});
            });
            
           

        });
    });
});

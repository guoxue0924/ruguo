/**
 * Created by yuelingyun on 2017/6/19.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'common', 'dialog', 'grid', 'valid'], function (init, common, BootstrapDialog) {
        $(function () {
        	// 初始化查询grid列表
        	initGrid();
        	// 点关闭按钮时，提示未进行保存操作
        	common.showUnSavedConfirm($.audit);
        	// 校验条件，审核状态不能为空，审核意见不能为空，并且不能超过500个字符
            var valid = $.bootValid('#orderBasicInfoForm', {
                rules: {
                	approvalStatus: ['not_empty'],
                	approvalOpinion:['not_empty', {'max_length': 500}]
                }
            });
            // 将退款原因置为不可编辑
            $("#refundReason").attr("disabled",true); 
            //关闭窗口
            $('#btnCancel').click(function () {
                BootstrapDialog.closeAll();
            });
            // -----------------grid赋值 start-------------------
            var grid;
            function initGrid(){
            	grid = $("#orderGoodsGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#orderBasicInfoForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/order/orderquery/getordergoodslist',
            		rowSelect: false,
            		selection: false,
            		multiSelect: false,
            		navigation: 0,
            		rowCount: [-1],
            		formatters: {
            			"goodsPrice": function (column, row) {
	           				return common.fmoney(row.goodsPrice, 2);
	           			},
	           			"goodsDiscountPrice": function (column, row) {
	           				return common.fmoney(row.goodsDiscountPrice, 2);
	           			},
	           			"goodsType": function (column, row) {
            				// 解析codelist值，付款方式
            				return common.CodeListDataSet.getLabel('DICT_GOODS_TYPE', row.goodsType);
            			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		
            	});
            }
            // -----------------grid赋值 end-------------------
            // -----------------提交审核信息 start-------------------
            $('#btnSubmit').click(function () {
                if (valid.validate()) {
                    BootstrapDialog.confirm({
                        message: '确认提交审核信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {
                                BootstrapDialog.showLoading();//加载效果
                                var uri = "order/orderquery/saveoperationexaminer";
                                var defer1 = common.ajaxSubmit(uri, 'orderBasicInfoForm');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                      	 common.unSavedConfirmCloseable = true;//取消未保存提示。
                                          BootstrapDialog.alert({
      									    message: result.msg,
      									    callback: function (result) {
      									    	BootstrapDialog.closeAll();
      									    	$('#btnQuery').click();//刷新父页面查询列表
      									    }
      									});
                                      } else {
                                          BootstrapDialog.alert(result.msg);
                                      }
                                  });
                            }
                        }
                    });

                }
            });
            // -----------------提交审核信息 end-------------------
        });
    });
});

/**
 * Created by yuelingyun on 2017/6/19.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'common', 'dialog', 'grid'], function (init, common, BootstrapDialog) {
        $(function () {
        	// 初始化grid查询方法
        	initGrid();
        	 //关闭窗口
            $('#btnClose').click(function () {
                BootstrapDialog.closeAll();
            });
            // 对grid进行赋值操作
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
            			"goodsType": function (column, row) {
            				// 解析codelist值，付款方式
            				return common.CodeListDataSet.getLabel('DICT_GOODS_TYPE', row.goodsType);
            			},
            			"goodsPrice": function (column, row) {
	           				return common.fmoney(row.goodsPrice, 2);
	           			},
	           			"goodsDiscountPrice": function (column, row) {
	           				return common.fmoney(row.goodsDiscountPrice, 2);
	           			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		
            	});
            }

        });
    });
});

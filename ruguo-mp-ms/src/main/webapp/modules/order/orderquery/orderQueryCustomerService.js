/**
 * Created by yuelingyun on 2017/6/19.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'common', 'dialog', 'date', 'select', 'grid', 'valid'], function (init, common, BootstrapDialog) {
        $(function () {
        	// 初始化日期控件
            $('.form_datetime').datetimepicker({
                format: "yyyy-mm-dd",
                pickerPosition: "bottom-left",
                todayBtn: 1,
                autoclose: 1,
                startView: 2,
                todayHighlight: 1,
                minView: 2
            });
            //初始化CodeList数据
            common.CodeListDataSet.init(['DICT_ORDER_PAY_WAY', 'DICT_ORDER_STATUS', 'DICT_PLATFORM_TYPE','DICT_GOODS_TYPE']);
            // 初始化下拉框选择器
            $('.selectpicker').selectpicker();
           
            
            // 点全部tab将订单状态赋值为9
            $('#tabAllQuery').click(function () {
            	$("#orderStatus").val(common.CommonConstant["OrderStatus"]["all"]);// 全部
            	$('#tabAllQuery').addClass("active");
         		$('#tabPaidQuery').removeClass("active");
         		$('#tabUnpaidQuery').removeClass("active");
         		$('#tabCanceledQuery').removeClass("active");
         		$('#tabApplyForRefundQuery').removeClass("active");
         		$('#tabRefundQuery').removeClass("active");
         		$('#tabRefundedQuery').removeClass("active");
         		grid.bootgrid("reload");
            });
            // 点已付款tab将订单状态赋值为1
            $('#tabPaidQuery').click(function () {
            	$("#orderStatus").val(common.CommonConstant["OrderStatus"]["paid"]);// 已付款
            	$('#tabPaidQuery').addClass("active");
        		$('#tabAllQuery').removeClass("active");
        		$('#tabUnpaidQuery').removeClass("active");
        		$('#tabCanceledQuery').removeClass("active");
        		$('#tabApplyForRefundQuery').removeClass("active");
        		$('#tabRefundQuery').removeClass("active");
        		$('#tabRefundedQuery').removeClass("active");
        		grid.bootgrid("reload");
            });
            // 点未付款tab将订单状态赋值为0
            $('#tabUnpaidQuery').click(function () {
            	$("#orderStatus").val(common.CommonConstant["OrderStatus"]["unpaid"]);// 未付款
            	$('#tabUnpaidQuery').addClass("active");
        		$('#tabAllQuery').removeClass("active");// 添加当前tab页的样式
        		$('#tabPaidQuery').removeClass("active");// 移除当前tab页的样式
        		$('#tabCanceledQuery').removeClass("active");
        		$('#tabApplyForRefundQuery').removeClass("active");
        		$('#tabRefundQuery').removeClass("active");
        		$('#tabRefundedQuery').removeClass("active");
        		grid.bootgrid("reload");
            });
            // 点已取消tab将订单状态赋值为5
            $('#tabCanceledQuery').click(function () {
            	$("#orderStatus").val(common.CommonConstant["OrderStatus"]["canceled"]);// 已取消
            	$('#tabCanceledQuery').addClass("active");
        		$('#tabAllQuery').removeClass("active");
        		$('#tabPaidQuery').removeClass("active");
        		$('#tabUnpaidQuery').removeClass("active");
        		$('#tabApplyForRefundQuery').removeClass("active");
        		$('#tabRefundQuery').removeClass("active");
        		$('#tabRefundedQuery').removeClass("active");
        		grid.bootgrid("reload");
            });
            // 点申请退款tab将订单状态赋值为2
            $('#tabApplyForRefundQuery').click(function () {
            	$("#orderStatus").val(common.CommonConstant["OrderStatus"]["applyForRefund"]);// 申请退款
            	$('#tabApplyForRefundQuery').addClass("active");
        		$('#tabAllQuery').removeClass("active");
        		$('#tabPaidQuery').removeClass("active");
        		$('#tabUnpaidQuery').removeClass("active");
        		$('#tabCanceledQuery').removeClass("active");
        		$('#tabRefundQuery').removeClass("active");
        		$('#tabRefundedQuery').removeClass("active");
        		grid.bootgrid("reload");
            });
            // 点退款中tab将订单状态赋值为3
            $('#tabRefundQuery').click(function () {
            	$("#orderStatus").val(common.CommonConstant["OrderStatus"]["refund"]);// 退款中
            	$('#tabRefundQuery').addClass("active");
        		$('#tabAllQuery').removeClass("active");
        		$('#tabPaidQuery').removeClass("active");
        		$('#tabUnpaidQuery').removeClass("active");
        		$('#tabCanceledQuery').removeClass("active");
        		$('#tabApplyForRefundQuery').removeClass("active");
        		$('#tabRefundedQuery').removeClass("active");
        		grid.bootgrid("reload");
            });
            // 点已退款tab将订单状态赋值为4
            $('#tabRefundedQuery').click(function () {
            	$("#orderStatus").val(common.CommonConstant["OrderStatus"]["refunded"]);// 已退款
            	$('#tabRefundedQuery').addClass("active");
        		$('#tabAllQuery').removeClass("active");
        		$('#tabPaidQuery').removeClass("active");
        		$('#tabUnpaidQuery').removeClass("active");
        		$('#tabCanceledQuery').removeClass("active");
        		$('#tabApplyForRefundQuery').removeClass("active");
        		$('#tabRefundQuery').removeClass("active");
        		grid.bootgrid("reload");
            });
            var valid = $.bootValid('#searchForm', {
				rules: {
					endTime: []
			    }    
            });
            // 重置
            $('#resetBtn').click(function () {
                $('#searchForm').resetFormData();
            });
            //查询按钮点击事件
            $('#btnQuery').click(function () {
            	if (valid.validate()) {
        			var startDate = Date.parse($('#startTime').val());
        			var endDate = Date.parse($('#endTime').val());
        			if (startDate > endDate) {
        				valid.show({endTime: "下单结束日期输入错误"});
        			} else {
        				if(grid){
                            grid.bootgrid("reload");
                        }else{
                        	initGrid();
                        }
        			}
            	}  
            });
            // 初始化grid
            var grid;
            function initGrid(){
            	grid = $("#orderGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#searchForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/order/orderquery/list',
            		rowSelect: false,
            		selection: false,
            		multiSelect: false,
            		emptyPost: true,
            		rowCount: [5,10,20,50],
            		formatters: {
            			"orderPayWay": function (column, row) {
            				// 解析codelist值，付款方式
            				return common.CodeListDataSet.getLabel('DICT_ORDER_PAY_WAY', row.orderPayWay);
            			},
            			"orderStatus": function (column, row) {
            				// 解析codelist值，订单状态
            				return common.CodeListDataSet.getLabel('DICT_ORDER_STATUS', row.orderStatus);
            			},
            			"goodsPrice": function (column, row) {
	           				return common.fmoney(row.goodsPrice, 2);
	           			},
	           			"goodsDiscountPrice": function (column, row) {
	           				return common.fmoney(row.goodsDiscountPrice, 2);
	           			},
            			"operation": function (column, row) {
            				// 添加查看链接
            				var detail_html = " <a href='#' class=\"detail\" data-code='" + row.orderCode + "'>详细</a>";
            				var audit_html = "";
            				// 如果是申请退款状态，添加审核链接
            				if(row.orderStatus === common.CommonConstant["OrderStatus"]["paid"]){
            					audit_html = " <a href='#' class=\"audit\" data-code='" + row.orderCode + "'>申请退款</a>";
            				}
	           				return detail_html + audit_html;
	           			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".detail").on("click", function (e) {
            			var orderCode = $(this).data("code");//获取当前行用户ID
            			BootstrapDialog.show({
            				title: "订单信息",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/order/orderquery/form?orderCode=' + orderCode);
            				}
            			});
            		});
            		grid.find(".audit").on("click", function (e) {
            			var orderCode = $(this).data("code");//获取当前行用户ID
            			$.audit = BootstrapDialog.show({
            				title: "退款申请",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/order/orderquery/applyrefund?orderCode=' + orderCode);
            				}
            			});
            		});
            	});
            }

        });
    });
});

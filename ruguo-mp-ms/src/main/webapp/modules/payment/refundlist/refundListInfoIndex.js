/**
 * Created by wanggang on 2017/6/15.

 */
require([configPath], function (c) {
	
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common','dialog','grid','select','date','valid'], function (init,common,BootstrapDialog) {
        $(function () {
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
            common.CodeListDataSet.init(['DICT_SUCCESS_OR_FAILURE', 'DICT_ORDER_PAY_WAY','DICT_GOODS_TYPE']);
            $('.selectpicker').selectpicker();
            //启用下拉框
            $('#btnSelectEnabled').click(function () {
            	$('#orderPayWay').selectpicker('enabled');
            	$('#refundStatus').selectpicker('enabled');
            });
            var valid = $.bootValid('#refundListInfoForm', {
				rules: {
					endRefundTime: []
			    }    
            });
            // 重置
            $('#resetBtn').click(function () {
                $('#refundListInfoForm').resetFormData();
            });
            $('#btnQuery').click(function () {
                var param = $('#refundListInfoForm').serializeObject();
                if (valid.validate()) {
        			var startDate = Date.parse($('#startRefundTime').val());
        			var endDate = Date.parse($('#endRefundTime').val());
        			if (startDate > endDate) {
        			    valid.show({endRefundTime: "结束时间输入错误"});
        			} else {
        				if(grid){
                            grid.bootgrid("reload");
                        }else{
                        	initGrid();
                        }
        			}
            	}  
            });
            //查询按钮点击事件
            var grid;
            function initGrid(){
                grid = $("#refundListInfoGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#refundListInfoForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/payment/refundlist/list',
            		rowSelect: true,
            		selection: true,
            		multiSelect: true,
            		emptyPost: true,	
            		rowCount: [5,10,20,50],
            		formatters: {         			
            			"refundListInfo.refundId": function (column, row) {
                            return row.refundListInfo.refundId;
                        },
            			"refundListInfo.paymentId": function (column, row) {
                            return row.refundListInfo.paymentId;
                        },
            			"goodsPaymentId": function (column, row) {
           				    var detail_order_html = " <a href='#' class=\"detail_order_link\" data-id='" + row.refundListInfo.orderCode + "'>"+row.refundListInfo.orderCode+"</a>";
          				    return detail_order_html;
                        },
            			"refundListInfo.refundStatus": function (column, row) {
                            return common.CodeListDataSet.getLabel('DICT_SUCCESS_OR_FAILURE', row.refundListInfo.refundStatus);
                        },
            			"refundListInfo.refundTime": function (column, row) {
                            return row.refundListInfo.refundTime;
                        },
            			"refundListInfo.refundAmount": function (column, row) {
            				var refundAmount = common.fmoney(row.refundListInfo.refundAmount,2);    				
                            return refundAmount;
                        },
            			"refundListInfo.refundWayCode": function (column, row) {
                            return common.CodeListDataSet.getLabel('DICT_ORDER_PAY_WAY', row.refundListInfo.refundWayCode);
                        },
            			"operation": function (column, row) {
            			     var detail_html = " <a href='#' class=\"detail_link\" data-id='" + row.refundListInfo.id + "'>详细</a>";
            				return detail_html;
            			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".detail_link").on("click", function (e) {
            			e.preventDefault();
            			var userCode = $(this).data("id");//获取当前行用户ID
            			BootstrapDialog.show({
            				title: "退款详细信息",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/payment/refundlist/detail?id=' + userCode);
            				}
            			});	
            		});	
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".detail_order_link").on("click", function (e) {
            			e.preventDefault();
            			var orderCode = $(this).data("id");//获取当前行用户ID
            			BootstrapDialog.show({
            				title: "订单详细信息",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/order/orderquery/form?orderCode=' + orderCode);
            				}
            			});	
            		});	
            	});
            }	
        });
    });
});

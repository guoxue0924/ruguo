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
            	$('#payStatus').selectpicker('enabled');
            });
            var valid = $.bootValid('#payMentListInfoForm', {
				rules: {
					endTransactionTime: []
			    }    
            });
            // 重置
            $('#resetBtn').click(function () {
                $('#payMentListInfoForm').resetFormData();
            });
            $('#btnQuery').click(function () {
                var param = $('#payMentListInfoForm').serializeObject();
                if (valid.validate()) {
        			var startDate = Date.parse($('#startTransactionTime').val());
        			var endDate = Date.parse($('#endTransactionTime').val());
        			if (startDate > endDate) {
        				valid.show({endTransactionTime: "结束时间输入错误"});
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
              grid = $("#payMentListInfoGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#payMentListInfoForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/payment/paymentlist/list',
            		rowSelect: true,
            		selection: true,
            		multiSelect: true,
            		emptyPost: true,	
            		rowCount: [5,10,20,50],
            		formatters: {         			
            			"payMentListInfo.paymentId": function (column, row) {            				
             				return row.payMentListInfo.paymentId;
                        },
            			"goodsPaymentId": function (column, row) { 
           				    var detail_order_html = " <a href='#' class=\"detail_order_link\" data-id='" + row.payMentListInfo.orderCode + "'>"+row.payMentListInfo.orderCode+"</a>";
          				    return detail_order_html;
                        },
            			"payMentListInfo.payStatus": function (column, row) {
                            return common.CodeListDataSet.getLabel('DICT_SUCCESS_OR_FAILURE', row.payMentListInfo.payStatus);
                        },
            			"payMentListInfo.transactionTime": function (column, row) {
                            return row.payMentListInfo.transactionTime;
                        },
            			"payMentListInfo.payAmount": function (column, row) {
            				var payAmount = common.fmoney(row.payMentListInfo.payAmount,2);    				
                            return payAmount;
                        },
            			"orderPayWay": function (column, row) {
                            return common.CodeListDataSet.getLabel('DICT_ORDER_PAY_WAY', row.payMentListInfo.payWayCode);
                        },
            			"operation": function (column, row) {
            			     var detail_html = " <a href='#' class=\"detail_link\" data-id='" + row.payMentListInfo.id + "'>详细</a>";
            				return detail_html;
            			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".detail_link").on("click", function (e) {
            			e.preventDefault();
            			var userCode = $(this).data("id");//获取当前行用户ID
            			BootstrapDialog.show({
            				title: "支付详细信息",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/payment/paymentlist/detail?id=' + userCode);
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

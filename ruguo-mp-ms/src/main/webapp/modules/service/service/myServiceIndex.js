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
            common.CodeListDataSet.init(['DICT_DISTANCE_DATE']);
            // 初始化下拉框选择器
            $('.selectpicker').selectpicker();
           
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
            	grid = $("#serviceGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#searchForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/myservices/myservices/list',
            		rowSelect: false,
            		selection: false,
            		multiSelect: false,
            		emptyPost: true,
            		rowCount: [5,10,20,50],
            		formatters: {
            			"operation": function (column, row) {
            				// 添加查看链接
            				var detail_html = " <a href='#' class=\"detail\" data-code='" + row.serviceCode + "'>查看详情</a>";
	           				return detail_html ;
	           			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".detail").on("click", function (e) {
            			var serviceCode = $(this).data("code");//获取当前行用户ID
            			BootstrapDialog.show({
            				title: "服务信息",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/myservices/myservices/detail?serviceCode=' + serviceCode);
            				}
            			});
            		});
            		
            	});
            }

        });
    });
});

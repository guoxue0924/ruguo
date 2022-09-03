/**
 * Created by zhuming on 2017/9/27.
 */
require([configPath], function (c) {
	
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common','dialog','expexcel','grid','date','select'], function (init,common,BootstrapDialog,ExportUtil) {
        $(function () {
        	//初始化CodeList数据
            common.CodeListDataSet.init(['DICT_AGREE_REPLY']);
        	$('.form_datetime').datetimepicker({
                format: "yyyy-mm-dd",
                pickerPosition: "bottom-left",
                //weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                startView: 2,                                                     
                todayHighlight: 1,
                //forceParse: 1,
                //showMeridian: 1,
                minView: 2
                //minuteStep: 5
            });
        	/*initMemberFeedbackGrid();*/
            $('.selectpicker').selectpicker();
           /* //启用下拉框
            $('#btnSelectEnabled').click(function () {
            	$('#operationState').selectpicker('enabled');
            });*/
            
            //查询按钮点击事件
            $('#btnQuery').click(function () {
            	if(grid){
            		grid.bootgrid("reload");
            	}else{
            		initGrid();
            	}
            });
            
         // 重置
            $('#resetBtn').click(function () {
                $('#feedbackForm').resetFormData();
            });
            
            var grid;
            function initGrid(){
            	var operationState;
              grid = $("#memberFeedbackGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#feedbackForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/feedback/information/list',
            		rowSelect: true,
            		selection: true,
            		multiSelect: true,
            		emptyPost: true,	
            		colResizable:false,
            		rowCount: [5,10,20,50],
            		formatters: {         			
            			"operationState": function (column, row) {
            				operationState = row.operationState;
            				return common.CodeListDataSet.getLabel('DICT_AGREE_REPLY', row.operationState);
                        },
 
            			"operation": function (column, row) {
            				if(operationState === undefined){
            					var detail_html = " <a href='#' class=\"detail_link\" data-id='" + row.id +"'>回复</a>";
                				return detail_html;
            					
            				}else{
            					return ""; 
            				}
            			},
            			"replyContent" :function (column, row) {
            				if(row.replyContent === undefined){
            					return ""; 
            				}else{
            					return "<span title='" + row.replyContent + "'>" + row.replyContent + "</span>";
            				}
            				  
            			},
            			"document":function (column, row) {
            				return "<span title='" + row.document + "'>" + row.document + "</span>";  
            			},
            			"updateTime" :function (column, row) {
            				return "<span title='" + row.updateTime + "'>" + row.updateTime + "</span>";  
            			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".detail_link").on("click", function (e) {
            			e.preventDefault();
            			var id = $(this).data("id");
            			BootstrapDialog.show({
            				title: "回复",
            				size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/feedback/information/queryReplyContent?id='+id);
            				}
            			});	
            		});	
            	});
            }
            /*//操作状态下拉
            function initMemberFeedbackGrid() {
            	var uri = "feedback/information/queryOperationStateList";
            	var param = $('#feedbackForm').serializeData();
            	var defer = common.ajaxPost(uri, param);

            	$.when(defer).done(function (result) {
        			var option = "";
                	$("#operationState").empty();
        			$("#operationState").prepend("<option value=''></option>");
                	for(var i=0;i<result.length;i++){
                		option = $("<option>");
                		option.val(result[i].operationState).text(result[i].operationState);
                		$("#operationState").append(option);
                	}
              		$('#operationState').selectpicker('refresh');
            	});
            }*/
  
        });
    });
});

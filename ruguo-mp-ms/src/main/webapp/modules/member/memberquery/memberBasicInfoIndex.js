/**
 * Created by wanggang on 2017/6/7.

 */
require([configPath], function (c) {
	
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common','dialog','expexcel','grid','date','select'], function (init,common,BootstrapDialog,ExportUtil) {
        $(function () {
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
        	initMemberPullDownGrid();
        	initChannelPullDownGrid();
            $('.selectpicker').selectpicker();
            //启用下拉框
            $('#btnSelectEnabled').click(function () {
            	$('#memberLevelCode').selectpicker('enabled');
            });
            // 重置
            $('#resetBtn').click(function () {
                $('#memberBasicInfoForm').resetFormData();
            });
            //查询按钮点击事件
            $('#btnQuery').click(function () {
            	if(grid){            	
            		grid.bootgrid("reload");
            	}else{
            		initGrid();
            	}
            });
            
            var grid;
            function initGrid(){
              grid = $("#memberBasicInfoGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#memberBasicInfoForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/member/memberquery/list',
            		rowSelect: true,
            		selection: true,
            		multiSelect: true,
            		emptyPost: true,	
            		rowCount: [5,10,20,50],
            		formatters: {         			
//            			"rela_per_name": function (column, row) {
//                        	var checkText = $("#orgCode option[value='" + row.memberBasicInfo.orgCode + "']").text();
//                            row.orgName = checkText;
//                        	return row.orgName;
//                        },
            			"relaPerName": function (column, row) {
                            return row.relaPerName;
                        },
                        "relaPerGenderName": function (column, row) {
                            return row.relaPerGenderName;
                        },
                        "relaPerBirthday": function (column, row) {
                            return row.relaPerBirthday;
                        },
            			"relaPerMobilePhone": function (column, row) {
                            return row.relaPerMobilePhone;
                        },
                        "createTime": function (column, row) {
                            return row.createTime;
                        },
            			"relaPerEmail": function (column, row) {
                            return row.relaPerEmail;
                        },
 
            			"operation": function (column, row) {
            			    var detail_html = " <a href='#' class=\"detail_link\" data-code='" + row.memberCode + "' data-org='" + row.relaPerName + "'>查看详情</a>";
            				return detail_html;
            			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".detail_link").on("click", function (e) {
            			e.preventDefault();
            			var code = $(this).data("code");//获取当前行用户code
            			var orgName = $(this).data("org");//获取当前行用户code
            			//解决中文乱码问题
            			orgName = encodeURI(encodeURI(orgName));
            			BootstrapDialog.show({
            				title: "会员详细信息",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/member/memberquery/detail?orgName=' + orgName + '&code=' + code);
            				}
            			});	
            		});	
            	});//end grid
            }
            // 导出  By zhuming
			$('#btnExport').click(function () {
				var param = $('#memberBasicInfoForm').serializeObject();
				if($.isEmptyObject(param)){
					BootstrapDialog.alert('请先进行查询，再导出！');
					return;
				} else {	
				if(!grid){
						BootstrapDialog.alert('请先进行查询，再导出！');
						return;
					}
					if (grid.bootgrid('getTotalRowCount') == 0) {
						BootstrapDialog.alert('请先进行查询，再导出！');
						return;
					}
					BootstrapDialog.confirm({
						message: '确认导出？',
						closable: true,
						draggable: true,
						btnOKClass: 'btn-primary',
						callback: function (result) {
							if (result) {
								ExportUtil.exportExcel('/member/memberquery/exportexcel',param);
							}
						}
					});
			   }
			});

            
            function initMemberPullDownGrid() {
            	var uri = "member/memberquery/memberlevelinfopulllist";
            	var param = $('#memberBasicInfoForm').serializeData();
            	var defer = common.ajaxPost(uri, param);

            	$.when(defer).done(function (result) {
        			var option = "";
                	$("#memberLevelCode").empty();
        			$("#memberLevelCode").prepend("<option value=''></option>");
                	for(var i=0;i<result.length;i++){
                		option = $("<option>");
                		option.val(result[i].memberLevelCode).text(result[i].memberLevelName);
                		$("#memberLevelCode").append(option);
                	}
              		$('#memberLevelCode').selectpicker('refresh');
            	});
            }
            function initChannelPullDownGrid() {
            	var uri = "member/memberquery/organizationpulllist";
            	var param = $('#memberBasicInfoForm').serializeData();
            	var defer = common.ajaxPost(uri, param);

            	$.when(defer).done(function (result) {
        			var option = "";
                	$("#orgCode").empty();
        			$("#orgCode").prepend("<option value=''></option>");
                	for(var i=0;i<result.length;i++){
                		option = $("<option>");
                		option.val(result[i].code).text(result[i].name);
                		$("#orgCode").append(option);
                	}
              		$('#orgCode').selectpicker('refresh');
            	});
            }
        });
    });
});

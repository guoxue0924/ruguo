/**
 * Created by hl on 2017/4/4.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common', 'dialog', 'treebox', 'date', 'select', 'grid', 'area', 'selectuser'], function (init, common, BootstrapDialog) {
        $(function () {

            $('.selectArea').SelectArea({
//        		auth:false,//权限过滤,默认：false,权限过滤
//        		isEnable: 1//是否启用，默认：1,（0禁用，1启用，2全部）
//        		select:true//是否显示空选项，默认：false(true显示，false不显示)
            });

            $('#userType').on('changed.bs.select', function () {
//        		$(this).children('option:checked').text()
//                console.log($(this).children('option:checked').text(), this.value);
            });
            //初始化CodeList数据
            common.CodeListDataSet.init(['DICT_EDUCATE_GRADE', 'DICT_AGEUNIT', 'DICT_IDENTITY_FLAG','DICT_NATION_TYPE']);
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
            $('.selectpicker').selectpicker();
            
            $('#btn1').click(function () {
                window.location.href = webAppPath + "/demo/user/regist"
            });
            $('#btn2').click(function () {
            	$.addUserDialog = BootstrapDialog.show({
                    title: "添加用户信息",
                    size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + '/demo/user/regist2');
                    }
                });
            });
            $('#btn3').click(function () {
                var obj = grid.bootgrid("getSelectedRowsObj");
//                console.log(obj);
            });
            $('#btn4').click(function () {
            	var obj = grid.bootgrid("getCurrentRows");
//            	console.log(obj);
            });
            //隐藏列
            $('#btn5').click(function () {
            	if(grid){
            		grid.bootgrid("visibleColumns",['email','name','phone'], false);
            	}
            });
            //显示列
            $('#btn6').click(function () {
            	if(grid){
            		grid.bootgrid("visibleColumns",['email','name','phone'], true);
            	}
            });

            $('#resetbtn').click(function () {
                $('#searchForm').resetFormData();
            });
            //查询按钮点击事件
            $('#btnQuery').click(function () {
                var param = $('#searchForm').serializeObject();
                if ($.isEmptyObject(param)) {
                    BootstrapDialog.alert('至少输入一项查询条件');
                } else {
                	if(grid){
//                		grid.bootgrid("visibleColumns",['email','name','phone'], true);
                		grid.bootgrid("reload");
                	}else{
                		initGrid();
//                		grid.bootgrid("visibleColumns",['email','name','phone'], false);
                	}
                    
                }
            });
            
            var grid;
            function initGrid(){
            	
            	grid = $("#userGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#searchForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/demo/user/list',
            		rowSelect: true,
            		selection: true,
            		multiSelect: true,
//                refreshButton: false,
//                keepSelection: true,
//                navigation: 0,
//                colResizable: true,	
            		rowCount: [5,10,20,50],
            		formatters: {
            			"operation": function (column, row) {
            				var edit_html = common.hasPermission('DemoUser:edit') ? "<a href='#' class=\"edit_link\" data-id='" + row.id + "'>编辑</a>" : '';
            				var del_html = " <a href='#' class=\"del_link\" data-id='" + row.id + "'>删除</a>";
            				var detail_html = " <a href='#' class=\"detail_link\" data-id='" + row.id + "'>详细</a>";
            				return edit_html + del_html + detail_html;
            			},
            			"userType": function (column, row) {
            				//解析codelist值
            				return common.CodeListDataSet.getLabel('DICT_EDUCATE_GRADE', row.userType);
            			},
            			"education": function (column, row) {
            				//解析codelist值
            				return common.CodeListDataSet.getLabel('DICT_NATION_TYPE', row.education);
            			},
            			"loginFlag": function (column, row) {
            				//解析codelist值
            				return common.CodeListDataSet.getLabel('DICT_IDENTITY_FLAG', row.loginFlag);
            			}
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".edit_link").on("click", function (e) {
            			e.preventDefault();
            			var userid = $(this).data("id");//获取当前行用户ID
            			$.addUserDialog = BootstrapDialog.show({
            				title: "编辑用户信息",
            				size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
	                        message: function () {
	                            var $message = $('<div></div>');
	                            return $message.load(webAppPath + '/demo/user/form?id=' + userid);
	                        }
//            				message: common.dialogLoad('demo/user/form?id=' + userid)
            			});
            		});
            		grid.find(".del_link").on("click", function (e) {
            			e.preventDefault();
            			var userid = $(this).data("id");//获取当前行用户ID
            			BootstrapDialog.confirm({
            				message: '确认删除用户信息？',
            				closable: true,
            				draggable: true,
            				callback: function (result) {
            					if (result) {
            						
            						BootstrapDialog.showLoading();//加载效果
            						var uri = "demo/user/delete";
            						var param = 'id=' + userid;
            						var defer1 = common.ajaxPost(uri, param);
            						$.when(defer1).done(function (result) {
            							if (result.status == common.CommonConstant.SUCCESS) {
            								grid.bootgrid("reload");//刷新查询列表
            								BootstrapDialog.alert(result.msg);
            							} else {
            								BootstrapDialog.alert(result.msg);
            							}
            						});
            						
            					}
            				}
            			});
            		});
            		grid.find(".detail_link").on("click", function (e) {
            			e.preventDefault();
            			var userid = $(this).data("id");//获取当前行用户ID
            			
            			BootstrapDialog.show({
            				title: "查看用户信息",
            				size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: function () {
            					var $message = $('<div></div>');
            					return $message.load(webAppPath + '/demo/user/detail?id=' + userid);
            				}
            			});
            			
            			
            		});
            		
            	});//end grid
            }

//            console.log(common.getCurrentUser()); // 获取当前登录人信息

            $('#s_zzjg').SelectTreebox({
                treeName: "organization",
                valueId: "s_zzjgvalue",
                valueName: "s_zzjgnamevalue",
                labelId: "s_zzjg",
                title: "组织机构",
                muliSelected: true,
                onlyChild: false,
                onlySelf: false,
                callback: function (value) {
//                    console.log("结果：");
//                    console.log(value);
                }
            });
            $('#s_zzjg').SelectTreebox('setValue', "ccc");
            $('#s_zzjg').SelectTreebox('setLabel', "ddd");
            var zzjgValue = $('#s_zzjg').SelectTreebox('getValue');
            var zzjgLabel = $('#s_zzjg').SelectTreebox('getLabel');
            $('#zzjgbtn').click(function () {
                // 显示机构选择弹出框
                $('#s_zzjg').SelectTreebox('show');
            });

            $('#s_user').SelectTreebox({
                treeName: "goods",
                valueId: "s_uservalue",
                labelId: "s_user",
                title: "选择用户",
                muliSelected: false,
                callback: function (value) {
//                    console.log("结果：");
//                    console.log(value);
                }
            });
            $('#s_user').SelectTreebox('setValue', "aaa");
            $('#s_user').SelectTreebox('setLabel', "bbb");
            $('#userbtn').click(function () {
                // 显示用户选择弹出框
                $('#s_user').SelectTreebox('show');
            });

            $('#s_user1').SelectUser({
                valueId: "s_user1value",
                labelId: "s_user1",
                title: "选择用户",
                callback: function (value) {

                }
            });
            $('#s_user1').SelectUser('setValue', "eee");
            $('#s_user1').SelectUser('setLabel', "fff");
            $('#user1btn').click(function () {
                // 显示用户选择弹出框
                $('#s_user1').SelectUser('show');
            });

            $('#doPrint').click(function() {
                window.open(webAppPath + '/demo/user/detail?id=' + '111');
            });
//            console.log(common.CodeListDataSet.getCodelist(['sys_user_type']));
//            console.log(common.CodeListDataSet.getCode('sys_user_type','1'));
//            console.log(common.CodeListDataSet.getLabel('sys_user_type','1'));
            /**
             * 获取codelist
             * @param codeType
             * @return {string}
             */
            /*
             function getCodelist(codeTypeNames) {
             var codeTypeList = [];
             $.each(codeTypeNames, function (i) {
             codeTypeList.push(codeTypeNames[i]);
             });

             if(codeTypeList.length > 0){
             var param = {};
             param.codeTypeList = codeTypeList;
             // 发送请求	 common.ajaxPost
             var result = common.ajaxPost("a/common/getCodelist2",param,false);
             //            	$.doAjax("/common/getCodelist", jsonObj, function(result){
             //	            		codelistResult = result;
             //            	},{async:false});

             //	            	console.log(result);
             return result;
             }
             }
             console.log(getCodelistLabelForGird('DICT_EDUCATE_GRADE','1'));
             function getCodelistLabelForGird(codeType, codeValue) {

             var colistTypeList = $.parseJSON(codelistResult);
             var label =colistTypeList[codeType][codeValue];
             if ($.isEmptyObject(label)) {
             return "";
             }
             return label;
             }*/


        });
    });
});

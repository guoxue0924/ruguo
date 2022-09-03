/**
 * Created by zhouhao on 2017/4/18.
 */
//定义了两个常量
require([ configPath ],function(c) {
		// 自定义模块，模块定义对照require-config.js,按需配置
		require([ 'init', 'common', 'dialog', 'select', 'grid'],function(init, common, BootstrapDialog) {
			$(function(){
	            //初始化CodeList数据
//	            $('.form_datetime').datetimepicker({
//	                format: "yyyy-mm-dd",
//	                pickerPosition: "bottom-left",
//	                todayBtn: 1,
//	                autoclose: 1,
//	                startView: 2,
//	                todayHighlight: 1,
//	                minView: 2
//	            });
	            $('.selectpicker').selectpicker();
	        	 // 重置
	            $('#resetBtn').click(function () {
	                $('#userForm').resetFormData();
	            });
	            //启用
	            $('#openBtn').click(function() {
	            	if (common.isEmpty(grid))  {
	            		BootstrapDialog.alert("请选择一条记录!");
	                    return;
	            	}
	            	var obj = grid.bootgrid("getSelectedRowsObj");
	            	if (obj.length === 0)  {
	            		BootstrapDialog.alert("请选择一条记录!");
	                    return;
	            	}
	            	for(var i = 0; i < obj.length; i++){
//	            		if(obj[i].loginFlag == common.CommonConstant["DictStartStatus"]["stop"]){
//	            			BootstrapDialog.alert("已停用的账号，不能再次启用！");
//		                    return;
//	            		}
	            		if(obj[i].loginFlag == common.CommonConstant["DictStartStatus"]["start"]){
	            			BootstrapDialog.alert("请选择未启用的账号信息！");
		                    return;
	            		}
	            	}
	            	BootstrapDialog.confirm({
						message : '确认进行启用选中账号信息吗？',
						closable : true,
						draggable : true,
						btnOKClass : 'btn-primary',
						callback : function(result) {
							if (result) {
								var jsonArray = [];
								for (var i = 0; i < obj.length; i++) {
									jsonArray.push({
										"id" : obj[i].id
									 });
								}
								var jsonObj = {
									"id" : jsonArray
								};
								var uri = "sys/user/openUser";
								var defer1 = common.ajaxJson(uri,jsonObj);
								$.when(defer1).done(
										function(result) {
											if (result.status == "success") {
												$('#queryBtn').click();// 刷新父页面查询列表
													BootstrapDialog.alert(result.msg);
												} else {
													BootstrapDialog.alert(result.msg);
												}
										});
							}
						}
					});
	            });
	            //停用
	            $('#stopBtn').click(function() {
	            	if (common.isEmpty(grid))  {
	            		BootstrapDialog.alert("请选择一条记录!");
	                    return;
	            	}
	            	var obj = grid.bootgrid("getSelectedRowsObj");
	            	if (obj.length === 0)  {
	            		BootstrapDialog.alert("请选择一条记录!");
	                    return;
	            	}
	            	for(var i = 0; i < obj.length; i++){
	            		if(obj[i].loginFlag != common.CommonConstant["DictStartStatus"]["start"]){
	            			BootstrapDialog.alert("请选择已启用的账号信息！");
		                    return;
	            		}
	            	}
	            	BootstrapDialog.confirm({
						message : '确认进行停用选中账号信息吗？',
						closable : true,
						draggable : true,
						btnOKClass : 'btn-primary',
						callback : function(result) {
							if (result) {
								var jsonArray = [];
								for (var i = 0; i < obj.length; i++) {
									jsonArray.push({
										"id" : obj[i].id
									 });
								}
								var jsonObj = {
									"id" : jsonArray
								};
								var uri = "sys/user/stopUser";
								var defer1 = common.ajaxJson(uri,jsonObj);
								$.when(defer1).done(
										function(result) {
											if (result.status == "success") {
												$('#queryBtn').click();// 刷新父页面查询列表
													BootstrapDialog.alert(result.msg);
												} else {
													BootstrapDialog.alert(result.msg);
												}
										});
							}
						}
					});
	            });
	            //查询
	            $('#queryBtn').click(function () {
//	            	var obj = $("#userForm").serializeObject();
//	            	if (common.isAllEmptyJson(obj)) {
//	            		BootstrapDialog.alert("请至少录入一项查询条件！");
//	                	return;
//	                }
	            	if(grid){
	            		grid.bootgrid("reload");
	            	}else{
	            		initGrid();
	            	}
	            });
	            //账号类型与角色级联
	            /*$('#userType').change(function(){
	            	var userType = $('#userType').val();
	            	var uri = "sys/role/getRoleListByType";
	            	var param = {"roleType": userType};
	            	var doAjax = common.ajaxJson(uri,param);
	            	$.when(doAjax).done(function (result) {
	                	var jsondata = result.data;
//	        			console.log(jsondata);
	        			var options = "";
	                	$("#userRole").empty();
	                	for(var i=0;i<jsondata.length;i++){
	                		var option = $("<option>");
	                		option.val(jsondata[i].id).text(jsondata[i].name);
	                		$("#userRole").append(option);
	                	}
	        			$("#userRole").selectpicker('refresh');
	                });
	            });*/
	            //新增管理机构
	            /*$('#createManageBtn').click(function () {
	            	var userType = "1";
	                BootstrapDialog.show({
	                    title: "新建管理机构账号",
	                    size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
	                    closable: false,
	                    message: function () {
	                        var $message = $('<div></div>');
	                        return $message.load(webAppPath + '/sys/user/addServiceUser?userType=' + userType);
	                    }
	                });
	            });*/
	            $('#createBtn').click(function () {
	                BootstrapDialog.show({
	                    title: "添加账号",
	                    size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
	                    closable: false,
	                    message: function () {
	                        var $message = $('<div></div>');
	                        return $message.load(webAppPath + '/sys/user/addServiceUser');
	                    }
	                });
	            });
	            
	    		/*$('#setOrganizationNameSearch').SelectTreebox({
	    		    // tree类型，根据此属性判断是否是机构树
	    		    treeName:"organization",
	    		    // 存放选中机构id的隐藏控件id，没有此控件会根据此属性生成一个隐藏的文本控件，控件的id和name为此属性值。
	    		    valueId:"setOrganizationIdSearch",
	    		    valueName:"sysOrgId",
	    		    // 显示机构名称的控件id。
	    		    labelId:"setOrganizationNameSearch",
	    		    // 标题
	    		    title:"所属机构",
	    		    // 是否多选
	    		    muliSelected:false,
	    		    // 是否只显示根节点和子节点
	    		    onlyChild:false,
	    		    // 是否只显示根节点
	    		    onlySelf:false,
	    		    // 回调方法，value为选中的节点的json数组。
	    		    callback:function(value) {
	    		        
	    		    }
	    		});*/
	    		/*$('#setjgbtn').click(function () {
	    		    // 显示机构选择弹出框
	    		    $('#setOrganizationNameSearch').SelectTreebox('show');
	    		});*/
	            var grid;
	            //初始化grid
				function initGrid(){
					 common.CodeListDataSet.init(["DICT_START_STATUS"]);
		             grid = $("#userGridList").bootgrid({
		                ajax: true,
		                post: function () {
		                    var param = $('#userForm').serializeObject();
		                    return param;
		                },
		                url: webAppPath + '/sys/user/getUserList',
		                //rowSelect: true,
		                emptyPost:true,
		                selection: true,
		                multiSelect: true,
		                rowCount: [5,10,20,50],
		                formatters: {
		                    "operation": function (column, row) {
		                    	
//		                        var edit_html = " <a href='#' class=\"edit_link\" data-id='" + row.id + "' data-sysloginflag='" + row.loginFlag + "'>编辑</a>";
		                        var del_html = " <a href='#' class=\"del_link\" data-id='" + row.id + "' data-loginflag='" + row.loginFlag + "'>删除</a>";
		                        var reset_html = " <a href='#' class=\"reset_link\" data-id='" + row.id + "' data-loginflag='" + row.loginFlag + "'>重置密码</a>";
		                        
		                        if(common.CommonConstant["DictStartStatus"]["notstart"] == row.loginFlag) {
		                        	return reset_html + del_html ;
		                        } else {
		                        	return reset_html ;
		                        }
		                    },
		              
		                    "loginFlag": function (column, row) {
		                        //解析codelist值--账号状态
		                        return common.CodeListDataSet.getLabel('DICT_START_STATUS', row.loginFlag);
		                    }
		                }
		            }).on("loaded.rs.jquery.bootgrid", function () {
		                grid.find(".del_link").on("click", function (e) {
		                	var id = $(this).data("id");	// 获取当前行用户ID
		                	var loginFlag = $(this).data("loginflag");	// 获取当前行用户账号状态
		                	if(loginFlag != common.CommonConstant["DictStartStatus"]["notstart"]){
		                		BootstrapDialog.alert("只有未启用的账号，才可以被删除！");
								return;
		                	}
		                    e.preventDefault();
//		                    BootstrapDialog.show({
//		                        title: "编辑",
//		                        size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE  ?id=' + id
//		                        closable: false,
//		                        message: function () {
//		                            var $message = $('<div></div>');
//		                            return $message.load(webAppPath + '/sys/user/updateServiceUser?param=' + id);
//		                        }
//		                    });
		                    BootstrapDialog.confirm({
								message : '确认删除此记录？',
								closable : true,
								draggable : true,
								btnOKClass : 'btn-primary',
								callback : function(result) {
									if (result) {
										BootstrapDialog.showLoading();// 加载效果
										var uri = "sys/user/deleteUser";
										var param = {
											"id" : id
										};
										var defer1 = common.ajaxJson(uri,param);
										$.when(defer1).done(function(result) {
											if (result.status == "success") {
												$('#queryBtn').click();	// 刷新父页面查询列表
												BootstrapDialog.alert(result.msg);
											} else {
												BootstrapDialog.alert(result.msg);
											}
										});
									}
								}
		                    });
		                });
		                grid.find(".reset_link").on("click", function (e) {
		                    e.preventDefault();
		                    var userid = $(this).data("id");//获取当前行用户ID
//		                	var loginFlag = $(this).data("sysloginflag");//获取当前行用户账号状态
//		                	if(loginFlag != common.CommonConstant["DictStartStatus"]["notstart"]){
//		                		BootstrapDialog.alert("请选择未启用的记录");
//								return;
//		                	}
		                    BootstrapDialog.confirm({
		                        message: '确认重置账号密码？',
		                        closable: true,
		                        draggable: true,
		                        btnOKClass: 'btn-primary',
		                        callback: function (result) {
		                            if (result) {
		                                BootstrapDialog.showLoading();//加载效果
		                                var uri = "sys/user/resetKey";
		                                var jsonObj = {
		        								"id" : userid
		        							};
		                                var defer1 = common.ajaxJson(uri, jsonObj);
		                                $.when(defer1).done(function (result) {
		                                    if (result.status == common.CommonConstant.SUCCESS) {
//		                                        $('#btnCancle').click();
		                                        $('#queryBtn').click();//刷新父页面查询列表
		                                        BootstrapDialog.alert(result.msg);
		                                    } else {
		                                        BootstrapDialog.alert(result.msg);
		                                    }
		                                });
		                            }
		                        }
		                    });
		                });
		            });//end grid
				}
			})
		});
});
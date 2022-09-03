/**
 * Created by zhouhao on 2017/4/18.
 */
require([ configPath ],function(c) {
		// 自定义模块，模块定义对照require-config.js,按需配置
		require([ 'dialog', 'common', 'select', 'valid'/*
		 * , 'files',
		 * 'spinner'
		 */], function(BootstrapDialog, common)  {
			$(function(){
	            //初始化CodeList数据
	            common.CodeListDataSet.init(["DICT_START_STATUS"]);
				// 关闭loading
				//BootstrapDialog.closeLoading();
				// 初始化下拉控件
				$('.selectpicker').selectpicker();
				// 关闭窗口
				$('#btnCancle').click(function() {
					BootstrapDialog.closeAll();
				});
				
	            var grid;
	            //账号信息保存
	            // 保存
	            $('#saveBtn').click(function() {
	            	saveUser();
	            });
	            //获取角色
	            function getRoles(){
	            	var uri = "sys/role/getRoleList";
	            	var doAjax = common.ajaxJson(uri);
	            	$.when(doAjax).done(function (result) {
	                	var jsondata = result.data;
//	        			console.log(jsondata);
	        			var options = "";
	                	$("#userRole1").empty();
	                	for(var i=0;i<jsondata.length;i++){
	                		var option = $("<option>");
	                		if (jsondata[i].id + '' === $('#userrole').val()) {
	                			option.val(jsondata[i].id).text(jsondata[i].name).attr('selected', true);
	                        }else{
	                        	option.val(jsondata[i].id).text(jsondata[i].name).attr('id',jsondata[i].roleLevel);
	                        	// 新增角色等级隐藏域 by liuhuan at 20170508
	                        	$("#userRole1").after('<input id=\'' + 'roleLevel' + jsondata[i].id + '\' value=\'' + jsondata[i].roleLevel + '\' type=\'hidden\' >');
	                        }
	                		$("#userRole1").append(option);
	                	}
	        			$("#userRole1").selectpicker('refresh');
	                });
	            }
				getRoles();
	            //表单验证
				var chineseAndEnglishRegex = /^[a-zA-Z\u4e00-\u9fa5]{2,16}$/;
                var englishRegex = /^[a-zA-Z]{3,16}$/;
		        var valid = $.bootValid('#userFormEdit', {
		            rules: {
		            	name: [{'regex':chineseAndEnglishRegex}],
		            	loginName: [{'regex':englishRegex}],
		            	userRole:['not_empty']
		            },
                    messages: {
                        name: {regex: '只能输入2至16位汉字和英文字母'},
                        loginName: {regex: '只能输入3至16位英文字母'}
                    }
		        });
		        
		   		 // 保存按钮
		   		function saveUser() {
		   			 if (valid.validate()) {
		   				// 新增角色等级和机构等级判断 by liuhuan at 20170508 strat
		   				var roleinput = $('#userRole1').val();
		   				/*if($('#zoneCode').val() == common.CommonConstant["DICT_ZONE_STATECENTER"]){
		   					if(!($('#roleLevel'+roleinput).val() == $('#'+orginput).val())){
		   	            		BootstrapDialog.alert("添加的角色级别和所属机构级别不同，请重新选择！");
		   	            		return;
							}
		   				}else{
		   	            	if(!($('#roleLevel'+roleinput).val() == $('#orgLevel').val())){
		   	            		BootstrapDialog.alert("添加的角色级别和所属机构级别不同，请重新选择！");
		   	            		return;
							}
		   			 	}*/
		   				// 新增角色等级和机构等级判断 by liuhuan at 20170508 end
		   				BootstrapDialog.confirm({
		   					message : '确认保存信息？',
		   					closable : true,
		   					draggable : true,
		   					btnOKClass : 'btn-primary',
		   					callback : function(result) {
		   						if (result) {
		   							BootstrapDialog.showLoading();// 加载效果
		   							var uri = "sys/user/saveUser";
		   							var param = $('#userFormEdit').serializeData();
		   							var defer1 = common.ajaxPost(uri, param);
		   							$.when(defer1).done(function(result) {
		   								//BootstrapDialog.closeLoading();// 关闭加载效果
		   								if (result.status == "success") {
		   									$('#btnCancle').click();
		   									var obj = $("#userForm").serializeObject();
		   					            	//if (!common.isAllEmptyJson(obj)) { 
		   					            		$('#queryBtn').click();// 刷新父页面查询列表
		   					                //}
		   									$('#userFromId').val(result.data); 
		   									BootstrapDialog.alert(result.msg);
		   								} else {
		   									//BootstrapDialog.closeLoading();// 加载效果
		   									BootstrapDialog.alert(result.msg);
		   								}
		   							});
		   						}
		   					}
		   				});
		   			}
		   		}
			})
		});
});
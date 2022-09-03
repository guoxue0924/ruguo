/**
 * Created by wanggang on 2017/6/6.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common','dialog','grid'], function (init,common,BootstrapDialog) {
        $(function () {
        	initGrid();
            $('#btnAdd').click(function () {
            	BootstrapDialog.show({
                    title: "添加会员等级信息",
                    size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + '/member/memberlevel/regist2');
                    }
                });
            });
            $('#btnQuery').click(function () {
             	if(grid){
            		grid.bootgrid("reload");
            	}else{
            		initGrid();
            	}
            });
            var grid;
            function initGrid(){
            	grid = $("#memberLevelGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = {};
            			return param;
            		},
            		url: webAppPath + '/member/memberlevel/list',
            		rowSelect: true,
            		selection: true,
            		multiSelect: true,
            		emptyPost: true,
            		navigation: 0,
            		rowCount: [-1],
            		formatters: {
            			"memberLevelMin": function (column, row) {
            				var memberLevelMin = common.fmoney(row.memberLevelMin,2);    				
                            return memberLevelMin;
                        },
            			"memberLevelMax": function (column, row) {
            				var memberLevelMax = common.fmoney(row.memberLevelMax,2);
                            return memberLevelMax;
                        },
                        "memberLevelDiscount": function (column, row) {
            				var memberLevelDiscount = row.memberLevelDiscount + "%";    				
                            return memberLevelDiscount;
                        },
            			"operation": function (column, row) {
            				 var edit_html = "<a href='#' class=\"edit_link\" data-id='" + row.id + "'>修改 </a>";
            				 var del_html = "<a href='#' class=\"del_link\" data-id='" + row.id + "'>删除</a>";
            				 return edit_html + del_html;	
            			},
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".edit_link").on("click", function (e) {
            			e.preventDefault();
            			var memberLevelId = $(this).data("id");//获取当前行用户ID
            			BootstrapDialog.show({
            				title: "修改用户信息",
            				size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            				message: common.dialogLoad('member/memberlevel/edit?id=' + memberLevelId)
            			});
            		});
            		grid.find(".del_link").on("click", function (e) {
            		   e.preventDefault();
            		   var memberLevelId = $(this).data("id");//获取当前行用户ID
	        		   BootstrapDialog.confirm({
	    				   message : '确认进行删除操作？',
	    				   closable : true,
    					   draggable : true,
    					   btnOKClass : 'btn-primary',
	    				   callback : function(result) {
	    					   if (result) {
	    						   var jsonArray = [];	   							   
	   							   jsonArray.push({"id" : memberLevelId});
	    						   var jsonObj = {
	    							   "id" : jsonArray
	    						   };
	    						   var uri = "member/memberlevel/delete";
	
	    						   var defer1 = common.ajaxJson(uri,jsonObj);
	    					       $.when(defer1).done(function(result) {
	    								$('#btnQuery').click();// 刷新父页面查询列表
	    								if (result.error) {
	    								    BootstrapDialog.alert(result.error);
	    								} else {
	    								    BootstrapDialog.alert(result.content);	
	    								}							
	    							});
	    						}
	    					}
	    				});
            		});
            	});//end grid
            }   
        });
    });
});

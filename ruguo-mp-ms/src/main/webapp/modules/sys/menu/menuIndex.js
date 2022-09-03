/**
 * Created by hl on 2017/4/4.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common', 'dialog','treetable'], function (init, common, dialog) {
    	dialog.showLoading();
        $(function () {
        	//初始化
            var treetable = $('#menulist').BootstrapTreeTable({
                column: 0,//指定排序列号
                expandlevel: 2,//默认展开级次
                expandAll: false,//是否全部展开
                collapseAll: false,//是否全部关闭
                maxResult: '20'//搜索最大结果集，超过将停止返回结果
            }).on("initialized.bs.treetable", function (e) {
            	dialog.closeLoading();
            });
            
            $('#serach').click(function () {
                var value = $('#serachvalue').val();
                if (value == '')return false;
                var result = $('#menulist').BootstrapTreeTable('searchNodeName', value);
                $(this).text('查询(' + result + ')');
            });

            $('#serachvalue').keypress(function (e) {
                // 回车键事件  
                if (e.which === 13) {
                    $('#serach').trigger('click');
                }
            });
            $('#reset').click(function () {
                $('#serachvalue').val('');
                $('#serach').text('查询');
            });

            $('#expendAll').click(function () {
                $('#menulist').BootstrapTreeTable('expendAll');
            });

            $('#collapseAll').click(function () {
                $('#menulist').BootstrapTreeTable('collapseAll');
            });
            //添加菜单
            $('#addMenu').click(function(){
            	dialog.show({
    				title: "添加菜单",
    				size: dialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
    				closable: false,
    				message: function () {
                      var $message = $('<div></div>');
                      return $message.load(webAppPath + '/sys/menu/form');
                  }
    			});
            })
            //保存排序
            $('#updateSort').click(function(){
            	dialog.showLoading();//加载效果
            	var param = $('#listForm').serializeData();//表单序列化
            	var uri = "sys/menu/updateSort";
            	var defer = common.ajaxPost(uri, param);
            	$.when(defer).done(function (result) {
					if (result.status == common.CommonConstant.SUCCESS) {
						dialog.alert(result.msg,function(){
							dialog.showLoading('正在查询');//加载效果
                            window.location.href = webAppPath + '/sys/menu/';
                        });
					} else {
						dialog.alert(result.msg);
					}
				});
            })
            //修改
            $('.modify').on('click', function (e) {
                e.preventDefault();
                var $this = $(this);
                var menuid = $this.parents('tr').data('id');
                dialog.show({
    				title: "编辑菜单",
    				size: dialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
    				closable: false,
    				message: function () {
                      var $message = $('<div></div>');
                      return $message.load(webAppPath + '/sys/menu/form?id=' + menuid);
                  }
    			});

            });
            //删除
            $('.delete').on('click', function (e) {
            	e.preventDefault();
            	var $this = $(this);
            	var menuid = $this.parents('tr').data('id');
            	dialog.confirm({
    				message: '要删除该菜单及所有子菜单项吗？',
    				closable: true,
    				draggable: true,
    				callback: function (result) {
    					if (result) {
    						dialog.showLoading();//加载效果
    						var uri = "sys/menu/delete";
    						var param = 'id=' + menuid;
    						var defer = common.ajaxPost(uri, param);
    						$.when(defer).done(function (result) {
    							if (result.status == common.CommonConstant.SUCCESS) {
    								dialog.alert(result.msg,function(){
    									dialog.showLoading('正在查询');//加载效果
    	                                window.location.href = webAppPath + '/sys/menu/';
    	                            });
    							} else {
    								dialog.alert(result.msg);
    							}
    						});
    						
    					}
    				}
    			});
            	
            });
            
            //添加下级菜单
            $('.addNext').on('click', function (e) {
                e.preventDefault();
                var $this = $(this);
                var menuid = $this.parents('tr').data('id');
                dialog.show({
    				title: "添加下级菜单",
    				size: dialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
    				closable: false,
    				message: function () {
                      var $message = $('<div></div>');
                      return $message.load(webAppPath + '/sys/menu/form?parent.id=' + menuid);
                  }
    			});

            });
            
        });
    });
});

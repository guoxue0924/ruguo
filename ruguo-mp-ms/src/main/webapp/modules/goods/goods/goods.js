/**
 * Created by guoxue on 2017/6/5.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'common', 'dialog', 'expexcel', 'treebox', 'date', 'select', 'grid', 'selectuser'], function (init, common, BootstrapDialog, ExportUtil) {
        $(function () {
        	
        	$('#gclassg').SelectTreebox({
        		treeName: "goods",//固定
        		valueId: "goodsClassgoods",//页面保存节点ID用的
        		labelId: "gclassg",//页面显示节点名用的
        		title: "商品分类选择",//弹出框标题
        		muliSelected: false,//单选/多选
        		callback: function (value) {
        		//                    console.log("结果：");
        		//                    console.log(value);
        		}
    		});
    		$('#setjgbtn').click(function () {
    		    // 显示机构选择弹出框
    		    $('#gclassg').SelectTreebox('show');
    		});

            $('#goodsStatus').on('changed.bs.select', function () {
            	
            });
            //初始化CodeList数据
            common.CodeListDataSet.init(['DICT_GENDER_ITEM', 'DICT_GOODS_STATUS','DICT_GOODS_TYPE','DICT_GOODS_VALIDITY_UNIT']);
            
            $('.selectpicker').selectpicker();
            // 重置
            $('#resetBtn').click(function () {
                $('#searchForm').resetFormData();
            });
            // 执行导入操作，弹出上传导入商品Excel的弹出页
            $('#btnImport').click(function () {
            	BootstrapDialog.show({
                        title: "导入商品信息",
                        size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                        closable: false,
                        message: function () {
                            var $message = $('<div></div>');
                            return $message.load(webAppPath + '/goods/goods/importgoods');
                        }
                    });
                });
            // 导出  By yuelingyun
			$('#btnExport').click(function () {
				var param = $('#searchForm').serializeObject();
				if($.isEmptyObject(param)){
					BootstrapDialog.alert('请先进行查询，再导出！');
					return;
				} else {
					if(!grid){
						BootstrapDialog.alert('请先进行查询，再导出！');
						return;
					}
					if (grid.bootgrid('getTotalRowCount') === 0) {
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
								ExportUtil.exportExcel('/goods/goods/exportexcel',param);
							}
						}
					});
				}
			});

            $('#deleteGoods').click(function () {
            	var obj = grid.bootgrid("getSelectedRowsObj");
            	if (obj.length === 0) {
					BootstrapDialog.alert("请选择需要删除的商品!");
					return;
				}
				BootstrapDialog
						.confirm({
							message : '确认进行删除？',
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

									var uri = "/goods/goods/deletegoods";
									var defer1 = common.ajaxJson(uri,jsonObj);
									 $.when(defer1).done(function (result) {
		                                    if (result.status === common.CommonConstant.SUCCESS) {
		                                        $('#btnQuery').click();
		                                        BootstrapDialog.alert(result.msg);
		                                    } else {
		                                    BootstrapDialog.closeLoading();//加载效果
		                                        BootstrapDialog.alert(result.msg);
		                                    }
		                                });

								}
							}
						});
            });
            //下架
            $('#offShelves').click(function () {
            	var obj = grid.bootgrid("getSelectedRowsObj");
            	if (obj.length === 0) {
					BootstrapDialog.alert("请选择需要下架的商品!");
					return;

				}
            	
            	for(var i = 0 ; i < obj.length ; i++){
            		if(obj[i].goodsStatus === '0'){
            			BootstrapDialog
						.alert("商品必须都是上架商品!");
				return;
            		}
            	}
            	
				BootstrapDialog.confirm({
							message : '确认进行下架？',
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

									var uri = "/goods/goods/modifygoodsstatusoff";
									var defer1 = common.ajaxJson(uri,jsonObj);
									 $.when(defer1).done(function (result) {
		                                    if (result.status === common.CommonConstant.SUCCESS) {
		                                        $('#btnQuery').click();
		                                        BootstrapDialog.alert(result.msg);
		                                    } else {
		                                    BootstrapDialog.closeLoading();//加载效果
		                                        BootstrapDialog.alert(result.msg);
		                                    }
		                                });

								}
							}
						});
            });
            //上架
            $('#onShelves').click(function () {
            	var obj = grid.bootgrid("getSelectedRowsObj");
            	if (obj.length === 0) {
					BootstrapDialog.alert("请选择需要上架的商品!");
					return;

				}
            	
            	for(var i = 0 ; i < obj.length ; i++){
            		if(obj[i].goodsStatus === '1'){
            			BootstrapDialog.alert("商品必须都是下架商品!");
				return;
            		}
            	}
				BootstrapDialog
						.confirm({
							message : '确认进行上架？',
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

									var uri = "/goods/goods/modifygoodsstatuson";
									var defer1 = common.ajaxJson(uri,jsonObj);
									 $.when(defer1).done(function (result) {
		                                    if (result.status === common.CommonConstant.SUCCESS) {
		                                        $('#btnQuery').click();
		                                        BootstrapDialog.alert(result.msg);
		                                    } else {
		                                    BootstrapDialog.closeLoading();//加载效果
		                                        BootstrapDialog.alert(result.msg);
		                                    }
		                                });

								}
							}
						});

            });
            //添加产品包按钮
            $('#addGoodsPackage').click(function () {
            	$.addGoodsPackageDialog = BootstrapDialog.show({
                    title: "添加产品包",
                    size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + '/goods/goods/addgoodspackage');
                    }
                });
            });
            
            //添加产品按钮
            $('#addGoods').click(function () {
            	$.addGoodsDialog = BootstrapDialog.show({
                    title: "添加商品",
                    size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + '/goods/goods/addgoods');
                    }
                });
            });

           
            //查询按钮点击事件
            $('#btnQuery').click(function () {
            	var GoodsStatue = $("#goodsStatus").find("option:selected").text();
            	if(GoodsStatue === "上架"){
            		$("#onShelves").attr("disabled","disabled");
            		$("#offShelves").removeAttr("disabled");
            	}else if(GoodsStatue === "下架"){
            		$("#offShelves").attr("disabled","disabled");
            		$("#onShelves").removeAttr("disabled");
            		
            	}else{
            		$("#offShelves").removeAttr("disabled");
            		$("#onShelves").removeAttr("disabled");
            	}
            	//清空分类选项
            	var gClass = $("#gclassg").val();
            	if(gClass === ""){
            		BootstrapDialog.alert('商品分类为必选条件');
            		$("#goodsClassgoods").removeAttr("value");
            	}else{
            		if(grid){
            				
            			if(gClass ==="医生服务" || gClass ==="基因类"){
            				grid.bootgrid("visibleColumns",['serviceOrgLevelName','servicePersonTypeName'], true);
            			}else{
            				grid.bootgrid("visibleColumns",['serviceOrgLevelName','servicePersonTypeName'], false);
            			}
                 		grid.bootgrid("reload");
                 	}else{
                 		
                 		initGrid();
                 	}
            	}
            	
            });
            
            var grid;
            function initGrid(){
            	
            	grid = $("#goodsGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#searchForm').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/goods/goods/list',
            		rowSelect: false,
            		selection: true,
            		emptyPost: true,
            		multiSelect: true,
            		rowCount: [5,10,20,50],
            		formatters: {
            			"operation": function (column, row) {
            				/*var pic_manage_html = "<a href='#' class=\"pric_manage\" data-id='" + row.id + "'>价格管理</a>" ;*/
            				var detail_html = " <a href='#' class=\"detail\" data-id='" + row.id + "'>修改</a>";
            				return  detail_html;
            			},
            			"goodsStatus": function (column, row) {
            				//解析codelist值
            				return common.CodeListDataSet.getLabel('DICT_GOODS_STATUS', row.goodsStatus);
            			},
            			"goodsType": function (column, row) {
            				//解析codelist值
            				return common.CodeListDataSet.getLabel('DICT_GOODS_TYPE', row.goodsType);
            			},
            			"goodsValidityNum": function (column, row) {
            				 var goodsPriceList = row.goodsPriceList;
            				 var html = "";
            				 for(var i =0;i<goodsPriceList.length;i++){
            					var goodsValidityNum = goodsPriceList[i].goodsValidityNum;
            					html = html + "<div>"+goodsValidityNum+"月</div>";
            				 }
            	           return html;
            			},
            			"goodsPrice": function (column, row) {
           				 var goodsPriceList = row.goodsPriceList;
           				 var html = "";
           				 for(var i =0;i<goodsPriceList.length;i++){
           					var goodsPrice = goodsPriceList[i].goodsPrice;
           					html = html + "<div>"+goodsPrice+"元</div>" ;
           				 }
           	           return html;
           			},
            			
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid.find(".detail").on("click", function (e) {
            			e.preventDefault();
            			var id = $(this).data("id");//获取当前行用户ID
            			$.detailDialog = BootstrapDialog.show({
            				title: "商品修改",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            			message: common.dialogLoad('goods/goods/form?id=' + id)
            			});
            			
            			/*window.location.href = webAppPath + "/goods/goods/form?id=" + id*/
            		});
            		/*grid.find(".pric_manage").on("click", function (e) {
            			e.preventDefault();
            			var id = $(this).data("id");//获取当前行用户ID
            			$.priceManagerDialog = BootstrapDialog.show({
            				title: "价格管理",
            				size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            			message: common.dialogLoad('goods/goods/pricemanage?id=' + id)
            			});
            		});*/
            		
            	});//end grid
            }

        });
    });
});

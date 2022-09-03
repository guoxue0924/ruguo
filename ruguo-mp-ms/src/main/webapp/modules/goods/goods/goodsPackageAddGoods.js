/**
 * Created by guoxue on 2017/6/5.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'common', 'dialog', 'expexcel', 'treebox', 'date', 'select', 'grid', 'selectuser'], function (init, common, BootstrapDialog, ExportUtil) {
        $(function () {
        	
        	$('#gclassg2').SelectTreebox({
        		treeName: "goods",//固定
        		valueId: "goodsClassgoods2",//页面保存节点ID用的
        		labelId: "gclassg2",//页面显示节点名用的
        		title: "商品分类选择",//弹出框标题
        		muliSelected: false,//单选/多选
        		callback: function (value) {
        		//                    console.log("结果：");
        		//                    console.log(value);
        		}
    		});
    		$('#setjgbtn2').click(function () {
    		    // 显示机构选择弹出框
    		    $('#gclassg2').SelectTreebox('show');
    		});

            $('#goodsStatus').on('changed.bs.select', function () {
            	
            });
            //初始化CodeList数据
            common.CodeListDataSet.init(['DICT_GENDER_ITEM', 'DICT_GOODS_STATUS','DICT_GOODS_TYPE','DICT_GOODS_VALIDITY_UNIT']);
            
            $('.selectpicker').selectpicker();
            // 重置
            $('#resetBtn2').click(function () {
                $('#searchForm2').resetFormData();
            });
            
            
            $('#batchAddGoods').click(function () {
            	
            	var obj = grid2.bootgrid("getSelectedRowsObj");
            	var ctx = $('#ctxPath').val();
            	if (obj.length === 0) {
					BootstrapDialog.alert("请选择需要添加的商品!");
					return;
				}
            	var jsonArray = [];
				for (var i = 0; i < obj.length; i++) {
					jsonArray.push({
								"id" : obj[i].id
							});
				}
				var jsonObj = {
					"id" : jsonArray
				};
				var uri = "goods/goods/addgoodspackagegoods";
				var defer2 = common.ajaxJson(uri,jsonObj);
				$.when(defer2).done(function (result) {
                    if (result != null) {
                    	
                    		$("#oldPackageGoods").remove();
                    	
                    	var before = "<div class='form-group' data-msg-direction='r'>"
			                +"<label class='col-xs-4 control-label '></label>";
                    	var after = "</div></div>";
                    	var centers = "";
        				for (var i = 0; i < result.length; i++) {
        					var center ="<div class='col-xs-1-sm'>"
        						+"<input type='hidden'  id='goodsPackageList' name='packGoodsCode' value='"+result[i].goodsCode+"'>"
    			                +"<div class='b_headImg' style='width:60px;height:60px;line-height:60px;text-align:center;'>"
    			                +"<img src='"+ctx+"/common/file/show?fileId="+result[i].goodsPicId+"' id='packageGoodsImg' class='packageGoodsImg' style='width:100%;height:100%;line-height:60px;text-align:center;float:left;'>"
    			               
    			                +"</div>"
    			                +"<label style='width:60px;height:60px;'>"+result[i].goodsName+"</label>"
    			                +"</div>"
    			                centers = centers +center;
        					
        				}
                    	$('#goodsPackageGoods').html(before+centers+after);
                    	$.addGoodsDialog.close();
                    } else {
                    	
                    }
                });
				
            	
            	
            });
            //查询按钮点击事件
            $('#btnQuery2').click(function () {
            	
                 	if(grid2){
                 		grid2.bootgrid("reload");
                 	}else{
                 		initGrid2();
                 	}
                     
       
                
            });
            
            var grid2;
            function initGrid2(){
            	
            	grid2 = $("#goodsGrid2").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#searchForm2').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/goods/goods/getgoodspackagegoods',
            		rowSelect: false,
            		selection: true,
            		emptyPost: true,
            		multiSelect: true,
            		rowCount: [5,10,20,50],
            		formatters: {
            			"operation": function (column, row) {
            				/*var pic_manage_html = "<a href='#' class=\"pric_manage\" data-id='" + row.id + "'>价格管理</a>" ;*/
            				var detail_html = " <a href='#' class=\"detail\" data-id='" + row.id + "'>详情</a>";
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
            			
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		grid2.find(".detail").on("click", function (e) {
            			e.preventDefault();
            			var id = $(this).data("id");//获取当前行用户ID
            			$.detailDialog2 = BootstrapDialog.show({
            				title: "详情",
            				size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            				closable: false,
            			message: common.dialogLoad('goods/goods/goodsdetail?id=' + id)
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

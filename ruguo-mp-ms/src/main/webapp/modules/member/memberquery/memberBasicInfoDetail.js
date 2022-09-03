/**
 * Created by wanggang on 2017/6/19.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
	require(['init', 'common', 'dialog', 'grid'], function (init, common,BootstrapDialog) {
        $(function () {
        	var ctx = $('#ctxPath').val();
        	 
        	initGrid();
        	 //关闭窗口
            $('#btnClose').click(function () {
                BootstrapDialog.closeAll();
            }); 
            
            
            
            var grid;
            function initGrid(){
            	grid = $("#memberAddressGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#memberBasicInfoForm2').serializeObject();
            			debugger;
            			return param;
            		},
            		url: webAppPath + '/member/memberquery/address',
            		rowSelect: false,
            		selection: false,
            		multiSelect: false,
            		navigation: 0,
            		rowCount: [-1],
            		formatters: {
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		initGrid2();
            	});
            }
           
            var grid2;
            function initGrid2(){
            	grid2 = $("#relationshipInfo").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#memberBasicInfoForm2').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/member/memberquery/relationshipInfo',
            		rowSelect: false,
            		selection: false,
            		multiSelect: false,
            		navigation: 0,
            		rowCount: [-1],
            		formatters: {
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		initGrid3();
            	});
            }
            
            
           
            
//            var grid2;
//            function initGrid2(){
//            	grid2 = $("#relationshipInfo").bootgrid({
//            		ajax: true,
//            		post: function () {
//            			var param = $('#memberBasicInfoForm2').serializeObject();
//            			return param;
//            		},
//            		url: webAppPath + '/member/memberquery/myconsultation',
//            		rowSelect: false,
//            		selection: false,
//            		multiSelect: false,
//            		navigation: 0,
//            		rowCount: [-1],
//            		formatters: {
//            			"operation2": function (column, row) {
//            				var detail_html = " <a href='#' class=\"detail\" data-id='" + row.id + "'>详情</a>";
//            				return  detail_html;
//            			},
//            			"picId": function (column, row) {
//            				var pic_html = "<div class='b_headImg' style='width:60px;height:60px;line-height:60px;text-align:center;'>"
//            	            +"<img src='"+ctx+"/common/file/show?fileId="+row.picId+"' id='headImg' class='headImg' style='width:100%;height:100%;line-height:60px;text-align:center' > </div>"
//            				return  pic_html;
//            			},
//            		}
//            	}).on("loaded.rs.jquery.bootgrid", function () {
//            		initGrid3();
//            	});
//            }
            
            
            var grid3;
            function initGrid3(){
            	grid3 = $("#orderGrid").bootgrid({
            		ajax: true,
            		post: function () {
            			var param = $('#memberBasicInfoForm2').serializeObject();
            			return param;
            		},
            		url: webAppPath + '/member/memberquery/myorder',
            		rowSelect: false,
            		selection: false,
            		multiSelect: false,
            		navigation: 0,
            		rowCount: [-1],
            		formatters: {
            			
            		}
            	}).on("loaded.rs.jquery.bootgrid", function () {
            		
            	});
            }
            
            
        });
    });
});

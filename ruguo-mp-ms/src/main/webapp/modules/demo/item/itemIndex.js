/**
 * Created by hl on 2017/4/4.

 */

require([configPath], function (c) {
	//自定义模块，模块定义对照config.js,按需配置
    require(['date', 'select', 'grid'], function () {
        $(function () {
            // console.log($('.selectpicker').length);
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
            
            $('#addAddress').click(function () {
                BootstrapDialog.alert({
                    //title: 'WARNING',
                    message: '保存成功!',
                    //type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                    closable: true, // <-- Default value is false
                    //draggable: true, // <-- Default value is false
                    btnOKClass: 'btn-warning',
                    //buttonLabel: 'Roar! Why!', // <-- Default value is 'OK',
                    callback: function (result) {
                        // result will be true if button was click, while it will be false if users close the dialog directly.
                        alert('Result is: ' + result);
                    }
                });
            });
            
            $('#resetbtn').click(function () {
                $(":text").val('');
                $('#type').selectpicker('val','');
                resetCheckBox('methods');
//                catalogids.splice(0, catalogids.length);
//                grid.bootgrid("reload");
            });
            function resetCheckBox(name) {
                $("input[name='" + name + "']").prop("checked", false);
                $("input[name='" + name + "']").parent('.active').removeClass('active');
            }
            $('#querybtn').click(function () {
            	Showloading.open('正在查询，请稍等...');
                grid.bootgrid("reload");
            });

            var grid = $("#itemGrid").bootgrid({
                ajax: true,
                post: function () {
                    var param = {};
//                    param.catalog_id = $('#catalog_id').val();
//                    param.catalog_ids = catalogids;
                    param.beginDate = $('#beginDate').val();
                    param.endDate = $('#endDate').val();
                    var createBy={};
                    createBy.id = $('#createbyid').val();
                    param.createBy = createBy;
                    var ids = [];
                    $("input[name='methods']:checked").each(function () {
                        ids.push($(this).val());
                    })
                    
                    param.method = ids;
                    param.title =$('#title').val();
                    param.type = $('#type').val();
                    return param;
                },
                url:webAppPath + '/demo/log/list',
                rowSelect: true,
                selection: true,
                //multiSelect: true,
                rowCount: [15, 20, 50],
                formatters: {
                    "operation": function (column, row) {
                        return "<a href='#' class=\"operation\" data-logid='" + row.id + "'>详细</a>";
                    },
                    "createBy.name": function (column, row){
                    	var p ={};
                    	p.title = row.createBy.name;
                    	p.a = "<a href='#' class=\"topurchase\" >"+row.createBy.name+"</a>";
                        return p;

                    },
                    "createBy.id": function (column, row){
                    	return row.createBy.id;
                    },
                    "type": function (column, row){
                    	if(row.type === '1'){
                    		return '接入日志';
                    	}else{
                    		return '错误日志';
                    	}
                    }
                }
            }).on("loaded.rs.jquery.bootgrid", function () {
            	Showloading.close();
                grid.find(".operation").on("click", function (e) {
                    e.preventDefault();
                    Showloading.open('正在跳转，请稍等...');
//                    var merchandiseids = new Array();
//                    var merchandiseid = encodeURI($(this).data("merchandiseid"));
//                    merchandiseids.push(merchandiseid);
//                    window.location.href = getURI(webAppPath + '/emallsys/directpurchase.do?method=goDirectPurchase' + "&merchandiseids=" + merchandiseids);

                });
            });

        });
    });
});

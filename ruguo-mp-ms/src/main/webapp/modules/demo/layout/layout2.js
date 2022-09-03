/**
 * Created by hl on 2017/4/4.

 */

require([configPath], function (c) {
	//自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'date', 'select', 'grid', 'files', 'spinner', 'tag'], function () {
        $(function () {
        	
        	$("[data-toggle='plustag']").plustag();
        	
        	//初始化下拉控件
            $('.selectpicker').selectpicker();
            //初始化日期控件
        	$('.form_datetime').datetimepicker({
        	    format: "yyyy-mm-dd hh:ii:ss",
        	    pickerPosition: "bottom-left",
        	    //weekStart: 1,
        	    todayBtn: 1,
        	    autoclose: 1,
        	    startView: 2,
        	    todayHighlight: 1,
        	    //forceParse: 1,
        	    showMeridian: true,
        	    minView: 0,

        	    maxView: 4

        	    //minuteStep: 5
        	});
        	
        	$('#input12').filestyle({
        	    //buttonText : '',
        	    //'placeholder' : '请选择文件'
        	});
        	
        	$(".spinner").spinner({
        	    max: 1000,
        	    min: 0,
        	    numberFormat: "n"

        	});
        	
        	//获取多选择值
        	$('#getcheckbox').click(function (e) {
        	    e.preventDefault();
        	    var ids = [];
        	    $("input[name='ck']:checked").each(function () {
        	        ids.push($(this).val());
        	    })
        	    alert(ids);
        	});
        	//获取单选值
        	$('#getradio').click(function (e) {
        	    e.preventDefault();
        	    alert($("input[name='ra']:checked").val());

        	});
        	//重置多选
        	$('#checkboxreset').click(function (e) {
			    e.preventDefault();
			    resetCheckBox('ck');
			});
			//重置单选
			$('#radioreset').click(function (e) {
			    e.preventDefault();
			    resetCheckBox('ra');
			});
			$("input[type='checkbox']").change(function () {
			    alert($(this).val());
//			    $('.labelthis').trigger('click');
			});
			
			$("input[name='ra']").change(function () {
			    alert($(this).val());
			});
        	//表格删除行取消效果
        	$('.cancel').click(function (e) {
        	    e.preventDefault();
        	    $(this).parents('tr').children('td:not(:last)').each(function () {
        	            //变灰色字体效果
        	            //$(this).wrapInner($("<span class='text-muted'/>")).wrapInner($('<s />'));
        	            //变斜效果
        	            $(this).wrapInner($("<span />")).wrapInner($('<s />')).wrapInner($('<em />'));
        	        }
        	    )
        	});
        	//表格恢复删除行效果
        	$('.recovery').click(function (e) {
        	    e.preventDefault();
        	    $(this).parents('tr').children('td').each(function () {
        	            //取消变灰色字体效果
        	            //$(this).find('span').removeClass().unwrap('s');
        	            //取消变斜效果
        	            $(this).find('span').unwrap('s').unwrap('em');
        	        }
        	    )
        	});
        	
        	//合并单元格
        	var mergeCells = function(){
        		var $rowspantd;
        		var tdtext;
        		var rowspan;
        		$('#rowtable>tbody>tr').each(function (index, domEle) {
        			
        			var $thistd = $(domEle).children().first();
        			if (tdtext !== $thistd.text()) {
        				tdtext = $thistd.text();
        				rowspan = 1;
        				$rowspantd = $thistd;
        			} else {
        				rowspan += 1;
        				$rowspantd.attr('rowspan', rowspan);
        				$thistd.remove();
        			}
        		})
        	}
        	mergeCells();
        	
        	$('#addcondition').click(function () {
        	    //删除事件
        	    var delhandle = function (e) {
        	        e.preventDefault();
        	        $(this).parents('.conditionbase').remove();
        	    }

        	    $(".spinner").spinner("destroy");

        	    //复制元素
        	    var clonediv = $('.conditionbase').first().clone();
        	    clonediv.children('label').text('');
        	    var $delbtn = $("<a class='btn btn-link'><i class='fa fa-times-circle fa-lg' aria-hidden='true'></i></a>").on('click', delhandle);
        	    var $delcol = $('<div class="col-xs-1-sm"></div>').append($delbtn);
        	    clonediv.append($delcol);
        	    clonediv.appendTo('#condition');
        	    $(".spinner").spinner({
        	        max: 100,
        	        min: 0,
        	        numberFormat: "n"

        	    });

        	})

        });
    });
});

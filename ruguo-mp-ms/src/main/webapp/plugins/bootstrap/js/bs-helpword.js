/**
 * jQuery Bootgrid v1.3.1 - 09/11/2015
 * 辅助词管理
 * huang-lin@neusoft.com
 */
;
(function ($, window, undefined) {
    "use strict";
    //选择辅助词事件
    var selecthelpwordhandler = function (e) {
        e.preventDefault();
        var $this = $(this);
        var $helpwordinput = $this.closest('.helpword-group').find('.helpword-input');
        $helpwordinput.val($this.text());

        var hworddetail_id = $this.attr('hworddetail_id');
        var $helpwordmultiple = $this.closest('.helpword-group').find('.helpword-multiple');
        $helpwordmultiple.each(function () {
            var $value = $(this);
            if (hworddetail_id == $value.attr("data-phwordid")) {
                $("input[hwordcode='" + $value.attr("data-hwordcode") + "'][workname='" + $value.attr("data-workname") + "']")
                    .val($value.attr("data-hwordvalue")).attr('hworddetail_id', $value.attr("data-hworddetailid"));
            }
        });
    };

    $.fn.helpword = function () {
        //选择常用词
        $('.helpword-group li a').bind('click', selecthelpwordhandler);

        //保存常用词
        $('.helpword-btn').click(function (e) {
            var $this = $(this);
            var $helpwordinput = $this.closest('.helpword-group').find('.helpword-input');

            var hwordcode = $helpwordinput.attr('hwordcode');//常用词代码
            var workname = $helpwordinput.attr('workname');//既是代表一组也代表主表的代码code
            var hmultiple = $helpwordinput.attr('hmultiple');//是否选择多值
            var hwordvalue = $helpwordinput.val();//常用词内容
            var hworddetail_id = 'helpword';//常用词明细编号
            var $helpwordmenu = $this.closest('.helpword-group').find('.helpword-menu');

            if ($.trim(hwordvalue) == '') {
                BootstrapDialog.alert('常用词不能为空，请重新输入！');
                return;
            }
            //遍历重复项
            $helpwordmenu.children().each(function (i) {
                var str = $(this).find('a').text();
                var hworddetailid = $(this).find('a').attr('hworddetail_id');
                if (str.indexOf(hwordvalue) != -1) {
                    hworddetail_id = hworddetailid;
                }
            });
//            if (hmultiple == 'false') {
            if (hworddetail_id != 'helpword') {
                BootstrapDialog.alert('常用词已存在，请重新输入！');
                return;
            }
//			}
            //console.log(hwordvalue + ' ' + hworddetail_id);
            Showloading.open();//加载效果
            var para = "hwordcode=" + hwordcode + "&" + "workname=" + workname + "&" + "hwordvalue=" + $.trim(hwordvalue) + "&" + "hworddetail_id=" + hworddetail_id;
            var result = executeRequestAjax("helpword", "addHelpWord", para);
            $.when(result).done(function (data) {
                Showloading.close();
                if (data == '0') {
                    BootstrapDialog.alert('保存失败！');
                } else {
                    var $li = $('<li></li>');
                    var $a = $('<a href="#"></a>');
                    $a.text(hwordvalue).attr('hworddetail_id', data).bind('click', selecthelpwordhandler);
                    $li.append($a);
                    $helpwordmenu.append($li);

                    if (hmultiple == 'true') {
                        saveMultiple(data);
                    }
                    BootstrapDialog.alert('保存成功');
                }
            });

            function saveMultiple(hworddetail_id) {
                $("input[workname='" + workname + "'][hwordcode!='" + hwordcode + "']").each(function () {
                    //alert($(this).attr('hwordcode') + ' ' + $(this).attr('workname') + ' ' + $(this).val() + ' ' + $(this).attr('hworddetail_id'));

                    var para = "hwordcode=" + $(this).attr('hwordcode') + "&" + "workname=" + $(this).attr('workname')
                        + "&" + "hwordvalue=" + $.trim($(this).val()) + "&" + "hworddetail_id=" + hworddetail_id;

                    var result = executeRequestAjax("helpword", "addHelpWord", para, false);
                    var $input = $('<input type="hidden" class="helpword-multiple"/>');
                    $input.attr('data-hworddetailid', result);
                    //$input.attr('data-hwordid',);
                    $input.attr('data-hwordcode', $(this).attr('hwordcode'));
                    $input.attr('data-hwordvalue', $.trim($(this).val()));
                    $input.attr('data-phwordid', hworddetail_id);
                    $input.attr('data-workname', $(this).attr('workname'));
                    $input.insertBefore($helpwordinput);
                });


            }
        })
    }

    $('.helpword-removeall').click(function (e) {
        var $this = $(this);
        BootstrapDialog.confirm({
            message: '确认清空记录吗?',
            type: BootstrapDialog.TYPE_PRIMARY, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
            closable: false, // <-- Default value is false
            draggable: false, // <-- Default value is false
            btnOKClass: 'btn-primary', // <-- If you didn't specify it, dialog type will be used,
            callback: function (result) {
                if (result) {
                    var $helpwordinput = $this.closest('.helpword-group').find('.helpword-input');//常用词输入框
                    var hwordcode = $helpwordinput.attr('hwordcode');//常用词代码
                    var workname = $helpwordinput.attr('workname');//既是代表一组也代表主表的代码code
                    Showloading.open();//加载效果
                    var para = "hwordcode=" + hwordcode + "&workname=" + workname;
                    var result = executeRequestAjax("helpword", "delHelpWords", para);
                    $.when(result).done(function (data) {
                        Showloading.close();

                        if (data == '0') {
                            BootstrapDialog.alert('清除失败');
                        } else if (data == '1') {
                            $this.parent().siblings().remove();//清空
                            BootstrapDialog.alert('清除成功');
                        }
                    })
                } else {

                }
            }
        });


    })

})(jQuery, window);
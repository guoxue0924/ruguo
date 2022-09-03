/**
 * jQuery bootplustags - 09/14/2016
 * bootplustags 标签组件
 * huanglin
 */
(function (root, factory) {
    // CommonJS module is defined
    if (typeof module !== 'undefined' && module.exports) {
        module.exports = factory(require('jquery'), require('bootstrap'));
    }
    // AMD module is defined
    else if (typeof define === "function" && define.amd) {
        define("tag", ["jquery", "bootstrap"], function ($) {
            return factory($);
        });
    } else {
        // planted over the root!
        root.bootplustags = factory(root.jQuery);
    }
}(this, function ($) {
    'use strict';
    $.fn.plustag = function (e) {
        //var toggle = '[data-toggle="plustag"]';
        if (e && e.which === 3) return;

        $(this).each(function () {
            var $this = $(this)
            var target = $this.attr('data-target');
            var a = $('#' + target);
            var $b = $('button', $this);
            var $i = $('input', $this);
            $i.keyup(function (e) {
                if (e.keyCode == 13) {
                    $b.click();
                }
            });
            $b.click(function () {
                var name = $i.val();
                if (name != '') setTips(a, name, -1);
                $i.val('');
                $i.select();
            });
        });

        var searchAjax = function () {
        };

        var hasTips = function (a, b) {
            var d = $("a", a), c = false;
            d.each(function () {
                if ($(this).attr("title") == b) {
                    c = true;
                    return false
                }
            });
            return c
        };

        var isMaxTips = function (a) {
            //console.log(a.attr('data-maxTips')===undefined?1:a.attr('data-maxTips'));
            return $("a", a).length >= (a.attr('data-maxTips') === undefined ? 1 : a.attr('data-maxTips'));//G_tocard_maxTips;
        };

        var setTips = function (a, c, d) {
            if (hasTips(a, c)) {
                return false
            }
            if (isMaxTips(a)) {
                BootstrapDialog.alert("最多添加" + (a.attr('data-maxTips') === undefined ? 1 : a.attr('data-maxTips')) + "个标签！");
                return false
            }
            var b = d ? 'value="' + d + '"' : "";
            var $em = $("<em class='remove'>&times;</em>");
            //a.append($("<a " + b + ' title="' + c + '" href="javascript:void(0);" ><span>' + c + "</span><em class='remove'>&times;</em></a>"));

            $($em).on("click", function () {
                var c = $(this).parents("a"), b = c.attr("title"), d = c.attr("value");
                delTips(a, b, d)
            });
            a.append($("<a " + b + ' title="' + c + '" href="javascript:void(0);" ><span>' + c + "</span></a>").append($em));
            searchAjax(c, d, true);

            return true
        };

        var delTips = function (a, b, c) {
            if (!hasTips(a, b)) {
                return false
            }
            $("a", a).each(function () {
                var d = $(this);
                if (d.attr("title") == b) {
                    d.remove();
                    return false
                }
            });
            searchAjax(b, c, false);
            return true
        };

        var getTips = function () {
            var b = [];
            $("a", a).each(function () {
                b.push($(this).attr("title"))
            });
            return b
        };

        var getTipsId = function () {
            var b = [];
            $("a", a).each(function () {
                b.push($(this).attr("value"))
            });
            return b
        };

        var getTipsIdAndTag = function () {
            var b = [];
            $("a", a).each(function () {
                b.push($(this).attr("value") + "##" + $(this).attr("title"))
            });
            return b
        }

    }

}));

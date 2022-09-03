/*!
 * jquery-lazyload v1.0.5
 *
 * Copyright 2017-1-12 by huang-lin@neusoft.com
 * https://git.oschina.net/harris992/JQuery-Lazyload-Plugin
 */
(function ($) {
    'use strict';
    var lazyload = function (element, options) {
        this.$element = $(element);
        this.$window = $(window);
        this.scrolltop;
        this.windowheight;
        this.offsetbottom;
        this.options = options;
        this.init();
    };
    lazyload.VERSION = '1.0.5';

    lazyload.prototype = {
        init: function () {
            this.resize();
            this.lazyloadHandler();
            this.scrollistener();
        },
        resize: function () {

            var offsetbottom = this.options.bottom !== '' ? this.options.bottom : 0;
            this.scrolltop = this.$window.scrollTop();
            this.windowheight = this.$window.height();
            this.offsetbottom = offsetbottom;
        },
        scrollistener: function () {
            var that = this;
            that.$window.scroll(function () {
                that.lazyloadHandler();
            })

        },
        lazyloadHandler: function () {
            var that = this;
            this.$element.each(function () {
                var $this = $(this);
                if (typeof $this.data('loaded') === 'undefined') {

                    var offsetTop = $this.offset().top;
                    var currentTop = offsetTop - that.$window.scrollTop();

                    if (currentTop <= that.windowheight - that.offsetbottom) {

                        $this.data('loaded', 1);

                        if ($.isFunction(that.options.lazyHandler)) {
                            that.options.lazyHandler.call(that, $this);
                        }
                    }
                }
            })
        }

    }
    lazyload.DEFAULTS = {
        bottom: 20,//底部偏移量 offsetButtom
        lazyHandler: {},//延迟处理 lazyHandler
    };

    function Plugin(option, event) {
        var args = arguments;
        var $this = $(this), _option = option, _event = event;
        [].shift.apply(args);

        var value;

        var data = $this.data('lazyload');
        var options = typeof _option === 'object' && _option;

        if (!data) {
            var config = $.extend(true, lazyload.DEFAULTS, $this.data(), options);
            $this.data('lazyload', (data = new lazyload(this, config, _event)));
        } else if (options) {
            for (var i in options) {
                if (options.hasOwnProperty(i)) {
                    data.options[i] = options[i];
                }
            }
        }
        if (typeof _option === 'string') {

            if (data[_option] instanceof Function) {
                value = data[_option].apply(data, args);

            } else {
                value = data.options[_option];
            }
        }

        if (typeof value !== 'undefined') {
            return value;
        } else {
            return this;
        }
    }
    var old = $.fn.lazyload;
    $.fn.lazyload = Plugin;
    $.fn.lazyload.Constructor = lazyload;
    $.fn.lazyload.noConflict = function () {
        $.fn.lazyload = old;
        return this;
    };
})(jQuery);
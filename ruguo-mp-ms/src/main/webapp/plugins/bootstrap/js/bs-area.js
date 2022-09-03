/**
 * 行政区划组件
 * class="selectpicker selectArea" data-area-id="province" data-area-value="210000000"
 * huanglin 2017-04-17 22:47:23
 * option1:auth:false,//权限过滤,默认：false,权限过滤
 * option2:isEnable: 1//是否启用，默认：1,（0禁用，1启用，2全部）
 * option3:select:true//是否显示空选项，默认：false(true显示，false不显示)
 * option4:onInitialized//组件初始化完成后执行函数。
 * reload:重新加载，参数省市区县
 * reset:重置默认值
 */
(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module unless amdModuleId is set
        define(["jquery", 'common', 'select'], function (a0, a1, a2) {
            return (factory(a0, a1, a2));
        });
    } else if (typeof module === 'object' && module.exports) {
        // Node. Does not work with strict CommonJS, but
        // only CommonJS-like environments that support module.exports,
        // like Node.
        module.exports = factory(require("jquery"));
    } else {
        factory(root["jQuery"]);
    }
}(this, function (jQuery, common, select) {

    (function ($) {
        'use strict';

        var namespace = ".bs.select.area";

        var SelectArea = function (element, options, e) {
            var that = this;
            this.$element = $(element);
            this.options = options;
//            this.origin = this.$element.clone();
            this.$element.each(function () {
                var $this = $(this);
                if ($this.data('areaId') === 'province') {
                    that.province = this;
                } else if ($this.data('areaId') === 'city') {
                    that.city = this;
                } else if ($this.data('areaId') === 'county') {
                    that.county = this;
                } else if ($this.data('areaId') === 'town') {
                    that.town = this;
                }

            });
            this.postUrl = this.options.auth ? "common/getDictZoneListForAuth" : "common/getDictZoneList";
            //Expose public methods
            this.reset = SelectArea.prototype.init;
            this.reload = SelectArea.prototype.reload;
            this.init();
        };

        SelectArea.VERSION = '2.0.1';

        SelectArea.prototype = {
            constructor: SelectArea,
            reload: function (defaultParam) {
                var param = {};
                param.rootCode = this.options.rootCode;
                param.provinceCode = defaultParam.provinceCode;
                param.cityCode = defaultParam.cityCode;
                param.countyCode = defaultParam.countyCode;
                param.isEnable = this.options.isEnable == "2" ? "" : this.options.isEnable;
                this.loadData(param);
            },
            init: function () {
                var provinceCode = $(this.province).data('areaValue');
                var cityCode = $(this.city).data('areaValue');
                var countyCode = $(this.county).data('areaValue');
                var townCode = $(this.town).data('areaValue');
                var param = {};
                param.rootCode = this.options.rootCode;
                param.provinceCode = provinceCode;
                param.cityCode = cityCode;
                param.countyCode = countyCode;
                param.townCode = townCode;
                param.isEnable = this.options.isEnable == "2" ? "" : this.options.isEnable;
                this.loadData(param);
            },
            loadData: function (param) {
                var that = this;
                var defer = common.ajaxJson(that.postUrl, param);
                $.when(defer).done(function (result) {
                    if ($.isEmptyObject(result)) {
                        return;
                    }
                    if (result.provincelist) {
                        that.loadList(that.province, result.provincelist, param.provinceCode);
                    }
                    if (result.citylist) {
                        that.loadList(that.city, result.citylist, param.cityCode);
                    }
                    if (result.countylist) {
                        that.loadList(that.county, result.countylist, param.countyCode);
                    }
                    if (result.townlist) {
                        that.loadList(that.town, result.townlist, param.townCode);
                    }

                    //组件初始化后执行
                    if (typeof that.options.onInitialized === 'function') {
                        that.options.onInitialized(that);
                    }

                });
            },
            loadList: function (element, arealist, selectCode) {
                var that = this;
                var areaId = $(element).data('areaId');
                $(element).empty();
                $(element).off('changed.bs.select');

                if (arealist.length > 0 && that.options.emptySelect) {
                    var option = $("<option>");
                    option.val('').text('');
                    $(element).append(option);
                }

                $.each(arealist, function (k, p) {
                    var option = $("<option>");
                    if (selectCode + '' === p.code) {
                        option.val(p.code).text(p.name).attr('selected', true);
                    } else {
                        option.val(p.code).text(p.name);
                    }
                    $(element).append(option);
                });
                $(element).selectpicker('refresh');

                if (areaId !== 'town') {
                    $(element).on('changed.bs.select', function () {
                        that.changeOption(this, this.value);
                    });
                }

            },
            changeOption: function (element, parentCode) {

                var areaId = $(element).data('areaId');
                var param = {};
                param.isEnable = this.options.isEnable == "2" ? "" : this.options.isEnable;

                if (areaId === 'province') {
                    this.resetOption([this.city, this.county, this.town]);
                    param.provinceCode = parentCode;
                    this.loadData(param);
                } else if (areaId === 'city') {
                    this.resetOption([this.county, this.town]);
                    param.cityCode = parentCode;
                    this.loadData(param);
//                    this.resetOption([this.town]);
                } else if (areaId === 'county') {
                    this.resetOption([this.town]);
                    param.countyCode = parentCode;
                    this.loadData(param);
                }
            },
            resetOption: function (elementArray) {
                $.each(elementArray, function () {
                    $(this).empty();
                    $(this).off('changed.bs.select');
                    $(this).selectpicker('refresh');
                });

            }
        };

        function Plugin(option, event) {
            var args = arguments;
            var $this = this.filter('select'), _option = option, _event = event;
            [].shift.apply(args);
            var defaults = {
//                provinceCode: '000000000',//默认市区
                rootCode: '000000000',
                auth: false,//是否权限过滤
                isEnable: "1",//0禁用，1启用，2全部
                isSelectOne: true, // 为true时下拉值域只有一个，选中；false不选择
                emptySelect: true //是否显示空白项目
            };

            var value;
            if ($this) {
                var data = $this.data('SelectArea');
                var options = typeof _option == 'object' && _option;
                if (!data) {

                    var config = $.extend(true, defaults, options);
                    $this.data('SelectArea', (data = new SelectArea($this, config, _event)));

                } else if (options) {
                    for (var i in options) {
                        if (options.hasOwnProperty(i)) {
                            data.options[i] = options[i];
                        }
                    }
                }
                if (typeof _option == 'string') {

                    if (data[_option] instanceof Function) {
                        value = data[_option].apply(data, args);

                    } else {
                        value = data.options[_option];
                    }
                }
            }
            if (typeof value !== 'undefined') {

                return value;
            } else {
                return this;
            }
        }

        $.fn.SelectArea = Plugin;
        $.fn.SelectArea.Constructor = SelectArea;

    })(jQuery);
}));

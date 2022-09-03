/**
 * 弹出框树组件
 * liuqing 2017-04-21 15:00:00
 */
(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module unless amdModuleId is set
        define(["jquery", 'common', 'select', 'dialog'], function (a0, a1, a2, a3) {
            return (factory(a0, a1, a2, a3));
        });
    } else if (typeof module === 'object' && module.exports) {
        module.exports = factory(require("jquery"));
    } else {
        factory(root["jQuery"]);
    }
}(this, function (jQuery, common, select, dialog) {

    (function ($) {
        'use strict';

        var namespace = ".bs.select.selectuser";

        var SelectUser = function (element, options, e) {
            var that = this;
            this.$element = $(element);
            this.options = options;
            this.origin = this.$element.clone();
            this.show = SelectUser.prototype.show;
            this.setLabel = SelectUser.prototype.setLabel;
            this.setValue = SelectUser.prototype.setValue;
            this.init();
        };

        SelectUser.VERSION = '1.0.1';

        SelectUser.prototype = {
            constructor: SelectUser,
            init: function () {
                var options = this.options;

                // 回调函数
                if (typeof options.callback == "function") {
                    options._callback = function (value, parameters) {
                        setValue(value, options.valueId, options.labelId, parameters);
                        options.callback(value);
                    }
                } else {
                    options._callback = function (value) {
                        setValue(value, options.valueId, options.labelId);
                    }
                }

                // 生成控件
                var valueId = options.valueId;
                var valueName = options.valueName;
                if (!common.isEmpty(valueId)) {
                    if ($("#" + valueId).length > 0) {

                    } else {
                        if (common.isEmpty(valueName)) {
                            valueName = valueId;
                        }
                        this.$element.after('<input id=\'' + valueId + '\' name=\'' + valueName + '\' type=\'hidden\' >');
                        var value = options.value;
                        if (!common.isEmpty(value)) {
                            $("#" + valueId).val(value);
                        }
                    }
                }

                // 赋值
                var labelId = options.labelId;
                var label = options.label;
                if (!common.isEmpty(labelId)) {
                    $("#" + labelId).attr("disabled","disabled");
                    if (!common.isEmpty(label)) {
                        $("#" + labelId).val(label);
                    }
                }

            },
            setLabel: function(label) {
                var labelId = this.options.labelId;
                if (!common.isEmpty(labelId)) {
                    $("#" + labelId).val(label);
                }
            },
            setValue: function(value) {
                var valueId = this.options.valueId;
                if (!common.isEmpty(valueId)) {
                    $("#" + valueId).val(value);
                }
            },
            getLabel: function() {
                var labelId = this.options.labelId;
                var result = null;
                if (!common.isEmpty(labelId)) {
                    result = $("#" + labelId).val();
                }
                return result;
            },
            getValue: function() {
                var valueId = this.options.valueId;
                var result = null;
                if (!common.isEmpty(valueId)) {
                    result = $("#" + valueId).val();
                }
                return result;
            },
            show: function() {
                $.selectUserDialogParameter = this.options;
                var _tilte = this.options.title;
                var _dialogUrl = this.options.dialogUrl;
                $.selectUserDialog = dialog.show({
                    title: _tilte,
                    size: dialog.SIZE_SMALL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + _dialogUrl);
                    }
                });
            },
            inithidden :function(){

            }
        };
        SelectUser.DEFAULTS = {
            valueId:"",
            labelId:"",
            title: '选择人员',
        };
        function setValue(value, valueId, labelId, parameters) {
            if (!common.isEmpty(value) && value.length > 0) {
                var ids = [], names = [], codes = [], nodes = value;
                for(var i=0; i<nodes.length; i++) {
                    ids.push(nodes[i].id);
                    codes.push(nodes[i].code);
                    names.push(nodes[i].name);
                }
                var idsString = ids.join(",").replace(/u_/ig,"");
                var codesString = codes.join(",").replace(/u_/ig,"");
                var namesString = names.join(",");

                if (!common.isEmpty(labelId)) {
                    $("#" + labelId).val(namesString);
                }

                if (!common.isEmpty(valueId)) {
                    $("#" + valueId).val(idsString);
                }
            } else {
                var name = parameters.name;

                if (common.isEmpty(name)) {
                    name = '';
                }

                if (!common.isEmpty(labelId)) {
                    $("#" + labelId).val(name);
                }

                if (!common.isEmpty(valueId)) {
                    $("#" + valueId).val(name);
                }
            }
        }

        function Plugin(option, event) {
            var args = arguments;

            var $this = $(this), _option = option, _event = event;
            [].shift.apply(args);
            var value;
            var options = typeof _option == 'object' && _option;

            var defaults = {
                valueId:"",
                labelId:"",
                title: '选择人员',
                dialogUrl: '/common/selectTree/showSelectUserDialog',
            };

            var config = null;
            if ($this.is('input')) {
                var data = $this.data('SelectUser');
                if (!data) {
                    config = $.extend(true, defaults, options);
                    $this.data('SelectUser', (data = new SelectUser(this, config, _event)));
                } else if (options) {
                    for (var i in options) {
                        if (options.hasOwnProperty(i)) {
                            data.options[i] = options[i];
                        }
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
            if (typeof value !== 'undefined') {
                return value;
            } else {
                return this;
            }
        }

        var old = $.fn.SelectUser;
        $.fn.SelectUser = Plugin;
        $.fn.SelectUser.Constructor = SelectUser;
        $.fn.SelectUser.noConflict = function () {
            $.fn.selectUser = old;
            return this;
        };

    })(jQuery);
}));

/**
 * 弹出框树组件
 * liuqing 2017-04-19 09:00:00
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

        var namespace = ".bs.select.treebox";

        var SelectTreebox = function (element, options, e) {
            var that = this;
            this.$element = $(element);
            this.options = options;
            this.origin = this.$element.clone();
            this.show = SelectTreebox.prototype.show;
            this.setLabel = SelectTreebox.prototype.setLabel;
            this.setValue = SelectTreebox.prototype.setValue;
            this.init();
        };

        SelectTreebox.VERSION = '1.0.1';

        SelectTreebox.prototype = {
            constructor: SelectTreebox,
            init: function () {
                var options = this.options;

                // 回调函数
                if (typeof options.callback == "function") {
                    options._callback = function (value) {
                        setValue(value, options.valueId, options.labelId);
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
                   /* $("#" + labelId).attr("disabled", "disabled");*/
                    if (!common.isEmpty(label)) {
                        $("#" + labelId).val(label);
                    }
                }

            },
            setLabel: function (label) {
                var labelId = this.options.labelId;
                if (!common.isEmpty(labelId)) {
                    $("#" + labelId).val(label);
                }
            },
            setValue: function (value) {
                var valueId = this.options.valueId;
                if (!common.isEmpty(valueId)) {
                    $("#" + valueId).val(value);
                }
            },
            getLabel: function () {
                var labelId = this.options.labelId;
                var result = null;
                if (!common.isEmpty(labelId)) {
                    result = $("#" + labelId).val();
                }
                return result;
            },
            getValue: function () {
                var valueId = this.options.valueId;
                var result = null;
                if (!common.isEmpty(valueId)) {
                    result = $("#" + valueId).val();
                }
                return result;
            },
            show: function () {
                $.dialogParameter = this.options;
                var _tilte = this.options.title;
                var _dialogUrl = this.options.dialogUrl;
                $.treeboxDialog = dialog.show({
                    title: _tilte,
                    size: dialog.SIZE_SMALL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + _dialogUrl);
                    }
                });
            },
            inithidden: function () {

            }
        };

        function setValue(value, valueId, labelId) {
            if (!common.isEmpty(value)) {
                var ids = [], names = [], codes = [], nodes = value;
                for (var i = 0; i < nodes.length; i++) {
                    ids.push(nodes[i].id);
                    codes.push(nodes[i].code);
                    names.push(nodes[i].name);
                }
                var idsString = ids.join(",").replace(/u_/ig, "");
                var codesString = codes.join(",").replace(/u_/ig, "");
                var namesString = names.join(",");

                if (!common.isEmpty(labelId)) {
                    $("#" + labelId).val(namesString);
                }

                if (!common.isEmpty(valueId)) {
                    $("#" + valueId).val(idsString);
                }
            }
        }

        function Plugin(option, event) {
            var args = arguments;

            var $this = $(this), _option = option, _event = event;
            [].shift.apply(args);
            var value;
            var options = typeof _option == 'object' && _option;

            var ORG_DEFAULTS = {
                valueId: "",
                valueName: "",
                labelId: "",
                value: "",
                label: "",
                treeName: 'organization',
                title: '选择机构',
                rootUrl: 'common/selectTree/getOrgById',
                nodeUrl: 'common/selectTree/getOrgByParentId',
                dialogUrl: '/common/selectTree/showOrgDialog',
                onlyChild: false,
                onlySelf: false,
                onlyManageOrg: false,
                onlyTown: false,
                muliSelected: false,
                rootNodeDisabled: false,
                rootId: '000000000'
            };
            var USER_DEFAULTS = {
                valueId: "",
                labelId: "",
                treeName: 'user',
                title: '选择用户',
                rootUrl: 'common/selectTree/getOrgByIdForUser',
                nodeUrl: 'common/selectTree/getListForUserTreeByOrgId',
                dialogUrl: '/common/selectTree/showUserDialog',
                onlyChild: false,
                onlySelf: false,
                muliSelected: false,
                rootId: '000000000'
            };
            var MENU_DEFAULTS = {
                valueId: "",
                valueName: "",
                labelId: "",
                value: "",
                label: "",
                treeName: 'menu',
                title: '选择菜单',
                rootUrl: 'sys/menu/treeData',
                dialogUrl: '/common/selectTree/showMenuDialog',
                muliSelected: false,
                rootNodeDisabled: false,
                rootId: '1',
                selectIds: '',
                extId: ''
            };
            var GOODS_DEFAULTS = {
        		valueId: "",
        		valueName: "",
        		labelId: "",
        		value: "",
        		label: "",
        		treeName: 'goods',
        		title: '选择菜单',
        		rootUrl: 'goodsclass/goodsclass/getgoodsclassbyid',
        		nodeUrl: 'goodsclass/goodsclass/getgoodsclassbyparentcode',
        		dialogUrl: '/common/selectTree/showGoodsDialog',
        		muliSelected: false,
        		rootNodeDisabled: false,
        		rootId: '0'
            };
            var config = null;
            if ($this.is('input')) {
                var data = $this.data('SelectTreebox');
                if (!data) {
                    if (options.treeName == "user") {
                        config = $.extend(true, USER_DEFAULTS, options);
                    }
                    if (options.treeName == "organization") {
                        config = $.extend(true, ORG_DEFAULTS, options);
                    }
                    if (options.treeName == "menu") {
                        config = $.extend(true, MENU_DEFAULTS, options);
                    }
                    if (options.treeName == "goods") {
                    	config = $.extend(true, GOODS_DEFAULTS, options);
                    }

                    $this.data('SelectTreebox', (data = new SelectTreebox(this, config, _event)));

                } else if (options) {
                    for (var i in options) {
                        if (options.hasOwnProperty(i)) {
                            data.options[i] = options[i];
                        }
                    }
                }
            }
            // new SelectTreebox(this, config, _event);

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

        var old = $.fn.SelectTreebox;
        $.fn.SelectTreebox = Plugin;
        $.fn.SelectTreebox.Constructor = SelectTreebox;
        $.fn.SelectTreebox.noConflict = function () {
            $.fn.selectTreebox = old;
            return this;
        };

    })(jQuery);
}));

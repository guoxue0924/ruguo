/* ==========================================================
 * bs-valid.js
 * modify: hl 2017-04-25
 * ========================================================== */
/*jshint laxcomma:true, sub:true, browser:true, jquery:true, smarttabs:true */

(function ($, undefined) {
    "use strict";

    var pluginName = 'bootValid';

    function Valid($form, options) {
        this.options = $.extend({}, $.fn[pluginName].defaults, options);
        this.$form = $form;
        this.allowed_rules = [];
        this.fieldNames = [];
        this.errors = {};
        var self = this;
        $.each(this.methods, function (k, v) {
            self.allowed_rules.push(k);
        });
    }

    $.extend(Valid.prototype, {
        // this is the main function - it returns either true if the validation passed or false
        validate: function () {
            var self = this
                , form_fields = this.$form.serializeArray()
                , all_fields = this.$form.find(':input[name]').map(function () {
                return this.name;
            }).get();
            this.fieldNames = all_fields;

            //2017-04-21 09:43:02
            $.each(all_fields, function (k, v) {
                self.clearValidByName(v);
            });
            this.errors = {};

            $.each(self.options.rules, function (field_name, rules) {
                var field = null
                    , normalized_rules = {}
                ;
                // find the field in the form
                $.each(form_fields, function (k, v) {
                    if (v.name === field_name) {
                        field = v;
                        return false;
                    }
                });

                // if field was not found, it could mean 2 things: mispelled field name in the rules or the field is not a successful control
                // even if it's not successful we have to validate it
                if (field === null) {
                    if ($.inArray(field_name, all_fields) !== -1) {
                        field = {name: field_name, value: self.get_field_value(field_name)};
                    }
                }

                // if it's still null then it's either mispelled or disabled. We don't care either way
                if (field !== null) {
                    $.each(rules, function (rule_idx, rule_value) {
                        // determine the method to call and its args
                        // only string and objects are allowed
                        if ($.type(rule_value) === 'string') {
                            // make sure the requested method actually exists.
                            if ($.inArray(rule_value, self.allowed_rules) !== -1) {
                                normalized_rules[rule_value] = null;
                            }
                        } else {
                            // if not string then we assume it's a {key: val} object. Only 1 key is allowed
                            $.each(rule_value, function (k, v) {
                                // make sure the requested method actually exists.
                                if ($.inArray(k, self.allowed_rules) !== -1) {
                                    normalized_rules[k] = v;
                                    return false;
                                }
                            });
                        }
                    });

                    $.each(normalized_rules, function (fn_name, fn_args) {
                        // call the method with the requested args
                        if (self.methods[fn_name].call(self, field.name, field.value, fn_args, normalized_rules) !== true) {
                            self.errors[field.name] = self.format.call(self, field.name, fn_name, fn_args);
                        }
                    });
                }
            });

            if (!$.isEmptyObject(this.errors)) {
                this.show(this.errors);

                if (self.options.validFiles) {
                    self.validFiles();
                }
                return false;
            } else if (self.options.validFiles) {
                return self.validFiles();
            } else {
                return true;
            }

        },

        appendMessage: function ($input, v) {
            var self = this;
            var direction = $input.closest(self.options.wrapper).attr('data-msg-direction');
            var $span = $('<span/>').addClass('pull-left control-label text-left-imp valid-message');
            if (typeof direction === 'undefined') {
                direction = self.options.direction;
            }

            if (direction === 'r') {
                var $span2 = $input.closest(self.options.wrapper).children('span.valid-message').addClass('control-label');//right
                if ($span2.length === 0) {
                    $span.css('padding-left', self.options.paddingLeft + 'px');
                    $span.html(v);
                    $input.closest(self.options.wrapper).append($span);
                } else {
                    $span2.css('padding-left', self.options.paddingLeft + 'px');
                    $span2.html(v);
                }
            } else if (direction === 'd') {
                var $span3 = $input.siblings('span.valid-message').addClass('control-label');//down
                if ($input.parent('.input-group').length == 1) {
                    $input.parent('.input-group').parent().append($span);
                    $span.html(v);
                } else if ($span3.length === 0) {
                    if ($input.attr("type") === 'radio' || $input.attr("type") === 'checkbox' || $input.hasClass('spinner')) {
                        $input.parent().parent().append($span);
                    } else {
                        $input.parent().append($span);
                    }
                    $span.html(v);
                } else {
                    $span3.html(v);
                }
            }

            if (self.options.wrapper !== null) {
                $input.closest(self.options.wrapper).addClass('has-error');
                // var $span = $input.closest(self.options.wrapper).children('span.valid-message');

            }
        },
        /**
         * ??????????????????????????? hl 2017-04-25 00:07:01
         */
        validFiles: function () {
            var self = this;
            var $files = this.$form.find(":file");
            var validflag = true;
            $.each($files, function (k, o) {
                if (!o.value) {
                    var $input = self.$form.find('#' + o.id);
                    self.appendMessage($input, self.messages['not_empty']);
                    validflag = false;
                } else {
                    self.clearValidById(o.id);
                }
            });
            return validflag;
        },
        show: function (errors) {
            var self = this;
            $.each(errors, function (k, v) {
                var $input = self.$form.find('[name="' + k + '"]');
                self.appendMessage($input, v);
            });
        },
        remove: function (errors) {
            var self = this;
            if (typeof errors === 'string') {
                self.clearValidByName(errors);
            } else {
                $.each(errors, function (i, field_name) {
                    self.clearValidByName(field_name);
                });
            }
        },
        removeAll: function () {
            var self = this;
            $.each(self.fieldNames, function (i, fieldName) {
                self.clearValidByName(fieldName);
            });
        },
        clearValidByName: function (field_name) {
            var $input = this.$form.find('[name="' + field_name + '"]');
            this.clearValid($input);
        },
        clearValidById: function (field_id) {
            var $input = this.$form.find('#' + field_id);
            this.clearValid($input);
        },
        clearValid: function ($input) {
            if (!$input) {
                return;
            }
            var self = this;
            $input.closest(self.options.wrapper).find('span.valid-message').html('').removeClass('control-label');
            if (self.options.wrapper !== null) {
                $input.closest(self.options.wrapper).removeClass('has-error');
            }
        },
        methods: {
            not_empty: function (field, value) {
                return value !== null && $.trim(value).length > 0;
            },

            min_length: function (field, value, min_len, all_rules) {
                var length = $.trim(value).length
                    , result = (length >= min_len);
                if (!all_rules['not_empty']) {
                    result = result || length === 0;
                }
                return result;
            },

            max_length: function (field, value, max_len) {
                return $.trim(value).length <= max_len;
            },

            regex: function (field, value, regexp) {
                return regexp.test(value);
            },

            email: function (field, value) {
                if (value !== null && $.trim(value).length > 0) {
                    // by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
                    var regex = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
                    return regex.test($.trim(value));
                }
                return true;
            },

            url: function (field, value) {
                if (value !== null && $.trim(value).length > 0) {
                    // by Scott Gonzalez: http://projects.scottsplayground.com/iri/
                    var regex = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
                    return regex.test(value);
                }
                return true;
            },

            exact_length: function (field, value, exact_length, all_rules) {
                var length = $.trim(value).length
                    , result = (length === exact_length);
                if (!all_rules['not_empty']) {
                    result = result || length === 0;
                }
                return result;
            },

            equals: function (field, value, target) {
                return value === target;
            },

            ip: function (field, value) {
                if (value !== null && $.trim(value).length > 0) {
                    var regex = /^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/i;
                    return regex.test($.trim(value));
                }
                return true;
            },

            credit_card: function (field, value) {
                // accept only spaces, digits and dashes
                if (/[^0-9 \-]+/.test(value)) {
                    return false;
                }
                var nCheck = 0
                    , nDigit = 0
                    , bEven = false;

                value = value.replace(/\D/g, "");

                for (var n = value.length - 1; n >= 0; n--) {
                    var cDigit = value.charAt(n);
                    nDigit = parseInt(cDigit, 10);
                    if (bEven) {
                        if ((nDigit *= 2) > 9) {
                            nDigit -= 9;
                        }
                    }
                    nCheck += nDigit;
                    bEven = !bEven;
                }

                return (nCheck % 10) === 0;
            },

            alpha: function (field, value) {
                var regex = /^[a-z]*$/i;
                return regex.test(value);
            },

            alpha_numeric: function (field, value) {
                var regex = /^[a-z0-9]*$/i;
                return regex.test(value);
            },
            alpha_number: function (field, value) {
                var regex = /^[a-zA-z]\w{3,15}$/;
                return regex.test(value);
            },
            alpha_dash: function (field, value) {
                var regex = /^[a-z0-9_\-]*$/i;
                return regex.test(value);
            },
            alpha_organ: function (field, value) {
                var regex = /^[a-zA-Z0-9\-]{1,18}$/;
                return regex.test(value);
            },
            digit: function (field, value) {
                var regex = /^\d*$/;
                return regex.test(value);
            },

            numeric: function (field, value) {
                var regex = /^([\+\-]?[0-9]+(\.[0-9]+)?)?$/;
                return regex.test(value);
            },

            // same as numeric
            decimal: function (field, value) {
                var regex = /^([\+\-]?[0-9]+(\.[0-9]+)?)?$/;
                return regex.test(value);
            },

            matches: function (field, value, param) {
                return value === this.$form.find('[name="' + param + '"]').val();
            },

            not_matches: function (field, value, param) {
                return (value !== this.$form.find('[name="' + param + '"]').val());
            },

            phone_num: function (field, value) {
                if (value !== null && $.trim(value).length > 0) {
                    var regex = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
                    return regex.test(value);
                }
                return true;
            },

            tel: function (field, value) {
                if (value !== null && $.trim(value).length > 0) {
                    var regex = /^0\d{2,3}-?\d{7,8}$/;
                    return regex.test(value);
                }
                return true;
            },

            id_num: function (field, code) {
                if (code !== null && $.trim(code).length > 0) {
                    var city = {
                        11: "??????",
                        12: "??????",
                        13: "??????",
                        14: "??????",
                        15: "?????????",
                        21: "??????",
                        22: "??????",
                        23: "????????? ",
                        31: "??????",
                        32: "??????",
                        33: "??????",
                        34: "??????",
                        35: "??????",
                        36: "??????",
                        37: "??????",
                        41: "??????",
                        42: "?????? ",
                        43: "??????",
                        44: "??????",
                        45: "??????",
                        46: "??????",
                        50: "??????",
                        51: "??????",
                        52: "??????",
                        53: "??????",
                        54: "?????? ",
                        61: "??????",
                        62: "??????",
                        63: "??????",
                        64: "??????",
                        65: "??????",
                        71: "??????",
                        81: "??????",
                        82: "??????",
                        91: "?????? "
                    };
                    var tip = "";
                    var pass = true;

                    if (!code || !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/i.test(code)) {
                        tip = "????????????????????????";
                        pass = false;
                    }

                    else if (!city[code.substr(0, 2)]) {
                        tip = "??????????????????";
                        pass = false;
                    }
                    else {
                        //18?????????????????????????????????????????????
                        if (code.length == 18) {
                            code = code.split('');
                            //???(ai??Wi)(mod 11)
                            //????????????
                            var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
                            //?????????
                            var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
                            var sum = 0;
                            var ai = 0;
                            var wi = 0;
                            for (var i = 0; i < 17; i++) {
                                ai = code[i];
                                wi = factor[i];
                                sum += ai * wi;
                            }
                            var last = parity[sum % 11];
                            if (parity[sum % 11] != code[17]) {
                                tip = "???????????????";
                                pass = false;
                            }
                        }
                    }
                    return pass;
                }
            },

            ch_char: function (field, value) {
                if (value !== null && $.trim(value).length > 0) {
                    var regex = /^[\u4e00-\u9fa5]+$/;
                    return regex.test(value);
                }
                return true;
            },

            date: function (field, value) {
                if (value !== null && $.trim(value).length > 0) {
                    var regex = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
                    return regex.test(value);
                }
                return true;
            },

            post: function (field, value) {
                if (value !== null && $.trim(value).length > 0) {
                    var regex = /^[1-9]\d{5}$/;
                    return regex.test(value);
                }
                return true;
            },
            bank_count_no: function (field, value) {//???????????????????????????16?????????19???
                if (value !== null && $.trim(value).length > 0) {
                    var regex = /^(\d{16}|\d{19})$/;
                    return regex.test(value);
                }
                return true;
            },
            strong_pwd: function (field, value) {//?????????????????????
                if (value !== null && $.trim(value).length > 0) {
                    var regex = /(?=[-\da-zA-Z`=\\;',./~!@#$%^&*()_+|{}:"<>?]*((\d+[a-zA-Z]+[-`=\\;',./~!@#$%^&*()_+|{}:"<>?]+)|(\d+[-`=\\;',./~!@#$%^&*()_+|{}:"<>?]+[a-zA-Z]+)|([a-zA-Z]+\d+[-`=\\;',./~!@#$%^&*()_+|{}:"<>?]+)|([a-zA-Z]+[-`=\\;',./~!@#$%^&*()_+|{}:"<>?]+\d+)|([-`=\\;',./~!@#$%^&*()_+|{}:"<>?]+\d+[a-zA-Z]+)|([-`=\\;',./~!@#$%^&*()_+|{}:"<>?]+[a-zA-Z]+\d+))[-\da-zA-Z`=\\;',./~!@#$%^&*()_+|{}:"<>?]*)((?=.*[A-Z])(?=.*[a-z]))/;
                    return regex.test(value);
                }
                return true;
            }
        },


        messages: {
            not_empty: '?????????????????????'
            , min_length: '??????????????? :value ???????????????'
            , max_length: '?????????????????? :value ???????????????'
            , regex: ''
            , email: '???????????????????????????????????????'
            , url: '?????????????????????????????????'
            , exact_length: '????????? :value ???????????????'
            , equals: ''
            , ip: '????????????????????????IP?????????'
            , credit_card: '????????????????????????????????????'
            , alpha: '??????????????????'
            , alpha_numeric: '?????????????????????'
            , alpha_number: '????????????????????????'
            , alpha_dash: '?????????????????????'
            , digit: '??????????????????'
            , numeric: '????????????????????????'
            , decimal: '??????????????????????????????'
            , matches: 'Must match the previous value.'
            , not_matches: 'Must not match the previous value.'
            , phone_num: '?????????11????????????????????????'
            , tel: '?????????????????????????????????0920-29392929'
            , id_num: '?????????????????????????????????'
            , ch_char: '?????????????????????'
            , date: '????????????????????????????????????2015-12-02'
            , post: '??????????????????????????????!'
            , bank_count_no: '??????????????????????????????!'
            , alpha_organ: '???????????????????????????????????????????????????????????????????????????18????????????'
            , strong_pwd: '???????????????????????????????????????????????????????????????'

        },


        /**
         * finds the most specific error message string and replaces any ":value" substring with the actual value
         */
        format: function (field_name, rule, params) {
            var message;
            if (typeof this.options.messages[field_name] !== 'undefined' && typeof this.options.messages[field_name][rule] !== 'undefined') {
                message = this.options.messages[field_name][rule];
            } else {
                message = this.messages[rule];
            }

            if ($.type(params) !== 'undefined' && params !== null) {
                if ($.type(params) === 'boolean' || $.type(params) === 'string' || $.type(params) === 'number') {
                    params = {value: params};
                }
                $.each(params, function (k, v) {
                    message = message.replace(new RegExp(':' + k, 'ig'), v);
                });
            }
            return message;
        },


        /**
         * get a normalized value for a form field.
         */
        get_field_value: function (field_name) {
            var $input = this.$form.find('[name="' + field_name + '"]');
            if ($input.is('[type="checkbox"], [type="radio"]')) {
                return $input.is(':checked') ? $input.val() : null;
            } else {
                return $input.val();
            }
        }
    });


    /**
     * main function to use on a form (like $('#form).scojs_valid({...})). Performs validation of the form, sets the error messages on form inputs and returns
     * true/false depending on whether the form passed validation or not
     *
     * @param {hash|string} options the hash of rules and messages to validate the form against (and messages to show if failed validation) or the string "option"
     * @param {string} key the option key to retrieve or set. If the third param of the function is available then act as a setter, otherwise as a getter.
     * @param {mixed} value the value to set on the key
     */
    $.fn[pluginName] = function (options, key, value) {
        return this.each(function (idx, form) {
            var $form = $(form)
                , validator = $form.data(pluginName);

            if ($.type(options) === 'object') {
                if (!validator) {
                    var opts = $.extend({}, $.fn[pluginName].defaults, options, $form.data());
                    validator = new Valid($form, opts);
                    $form.data(pluginName, validator).attr('novalidate', 'novalidate');
                }
                $form.ajaxForm({
                    beforeSerialize: function () {
                        if (typeof validator.options.onBeforeValidate === 'function') {
                            validator.options.onBeforeValidate.call(validator);
                        }
                    }
                    , beforeSubmit: function () {
                        return validator.validate();
                    }
                    , dataType: 'json'
                    , success: function (response, status, xhr, $form) {
                        if (response.status === 'fail') {
                            if (typeof options.onFail !== 'function' || options.onFail.call(this, response, validator, $form) !== false) {
                                validator.show(response.data.errors);
                            }
                        } else if (response.status === 'error') {
                            if (typeof options.onError !== 'function' || options.onError.call(this, response, validator, $form) !== false) {
                                $.scojs_message(response.message, $.scojs_message.TYPE_ERROR);
                            }
                        } else if (response.status === 'success') {
                            if (typeof options.onSuccess !== 'function' || options.onSuccess.call(this, response, validator, $form) !== false) {
                                if (response.data.next) {
                                    if (response.data.next === '.') {			// refresh current page
                                        window.location.href = window.location.href.replace(/#.*$/, '');
                                    } else if (response.data.next === 'x') {	// close the parent modal
                                        $form.closest('.modal').trigger('close');
                                    } else {
                                        window.location.href = response.data.next;
                                    }
                                }
                                if (response.data.message) {
                                    $.scojs_message(response.data.message, $.scojs_message.TYPE_OK);
                                }
                            }
                        }
                    }
                });
                // allow chaining
                return this;
            } else if (options === 'option') {
                if ($.type(value) === 'undefined') {
                    return validator.options[key];
                } else {
                    validator.options[key] = value;
                    return validator;
                }
            } else {
                return validator ? validator : this;
            }
        });
    };

    $[pluginName] = function (form, options) {
        if (typeof form === 'string') {
            form = $(form);
        }
        return new Valid(form, options);
    };

    $.fn[pluginName].defaults = {
        wrapper: '.form-group'	// the html tag that wraps the field and which defines a "row" of the form
        , direction: 'r'   // message direction rigth(r) or down(d)
        , rules: {}			// array of rules to check the form against. Each value should be either a string with the name of the method to use as rule or a hash like {method: <method params>}
        , messages: {}		// custom error messages like {username: {not_empty: 'hey you forgot to enter your username', min_length: 'come on, more than 2 chars, ok?'}, password: {....}}
        , paddingLeft: 10
        , validFiles: false
    };
})(jQuery);

define(['jquery', 'dialog'], function ($, BootstrapDialog) {

    var CommonUtils = function () {

    };

    /**
     * Ajax请求入口
     * @param uri
     * @param postParameter
     * @param isAsynchronism
     * @returns {*}
     * hl 2017-04-13
     */
    CommonUtils.ajaxPost = function (uri, postParameter, isAsynchronism, json) {
        if ($.isEmptyObject(uri)) {
            throw new Error("请求地址不能为空");
            return;
        }
        var strURL = webAppPath + "/" + uri;
        //增加变化的参数（时间+随机数），用于防止代理服务器缓存
        var r = new Date().getTime() + "" + parseInt(Math.random() * 1000);
        strURL += ("?r_t=" + r);
        if (postParameter == null || typeof postParameter == "undefined") postParameter = "";

        if (isAsynchronism == null || typeof isAsynchronism == "undefined") isAsynchronism = true;

        var defer, result;
        if (isAsynchronism) {
            defer = $.Deferred();
        }
        $.ajax({
            type: "post",
            url: strURL,
            async: isAsynchronism,
            data: postParameter,
            beforeSend: function (XMLHttpRequest) {
            },
            success: function (data, textStatus) {
                if (isAsynchronism) {
                    defer.resolve(data);
                } else {
                    result = data;
                }
            },
        });
        if (isAsynchronism) {
            return defer.promise();
        } else {
            return result;
        }

    }

    /**
     * Ajax请求入口Json
     * @param uri
     * @param Json
     * @param isAsynchronism
     * @returns {*}
     * hl 2017-04-15
     */
    CommonUtils.ajaxJson = function (uri, obj, isAsynchronism) {
        if ($.isEmptyObject(uri)) {
            throw new Error("请求地址不能为空");
            return;
        }
        var strURL = webAppPath + "/" + uri;
        //增加变化的参数（时间+随机数），用于防止代理服务器缓存
//        var r = new Date().getTime() + "" + parseInt(Math.random() * 1000);
//    	if (postParameter == null || typeof postParameter == "undefined") postParameter = "";
//    	strURL += ("?r_t=" + r +'&'+postParameter);
        if (isAsynchronism == null || typeof isAsynchronism == "undefined") isAsynchronism = true;
        var jsonStr = ''
        try {
            jsonStr = JSON.stringify(obj)//将对象序列化成JSON字符串
        } catch (err) {
            $.error('[' + obj + ']类型转换错误');
        }
        var defer, result;
        if (isAsynchronism) {
            defer = $.Deferred();
        }
        $.ajax({
            type: "post",
            url: strURL,
            async: isAsynchronism,
            data: jsonStr,//将对象序列化成JSON字符串
            dataType: "json",
            contentType: 'application/json;charset=utf-8', //设置请求头信息
            beforeSend: function (XMLHttpRequest) {
            },
            success: function (data, textStatus) {
                if (isAsynchronism) {
                    defer.resolve(data);
                } else {
                    result = data;
                }
            }
        });
        if (isAsynchronism) {
            return defer.promise();
        } else {
            return result;
        }


    }

    /**
     * AjaxSubmit提交表单
     * @param uri
     * @param formid
     * @param postParameter
     * @param function
     * @param isAsynchronism
     * @returns {*}
     * hl 2017-04-13
     */
    CommonUtils.ajaxSubmit = function (uri, formid, postParameter, fun, isAsynchronism) {


        var strURL = webAppPath + "/" + uri;
        if (isAsynchronism == null || typeof isAsynchronism == "undefined") isAsynchronism = true;
        if (postParameter == null || typeof postParameter == "undefined") postParameter = "";
        var result, defer = $.Deferred();//加定义
        $("#" + formid).ajaxSubmit({
            url: strURL + "?" + postParameter,
            type: "POST",
            async: isAsynchronism,
            success: function (data, textStatus) {
                if (typeof fun === 'function') {
                    fun();
                }
                if (isAsynchronism) {
                    defer.resolve(data);
                } else {
                    result = data;
                }

            }
        });
        // 增加是否是异步的判断
        if (isAsynchronism) {

            return defer.promise();
        } else {

            return result;
        }
    }

    /**
     * BootstrapDialog.loadMessage方法
     * hl 2017-05-09
     */
    CommonUtils.dialogLoad = function (uri, postParameter, fun) {

        if (postParameter == null || typeof postParameter == "undefined") postParameter = "";
        var strURL = webAppPath + "/" + uri;
        var content = $('<div></div>');
        var message = content.load(strURL, postParameter, function (text, status, xhr) {
        });

        return message;
    }

    /**
     * 捕获错误异常
     * hl 2017-04-13
     */
    $.ajaxSetup({
        cache: false,
        complete: function (XMLHttpRequest, textStatus) {
            BootstrapDialog.closeLoading();
        },
        error: function (XMLHttpRequest, textStatus) {
//        	BootstrapDialog.closeAll();
            var errorStatus = XMLHttpRequest.status;
            var errortitle = CommonUtils.ConstantMsg.ExceptionTitle + errorStatus;
            var errortype = BootstrapDialog.TYPE_DANGER;

            var errorInfo;
            if ("430" == errorStatus) {
                errortitle = CommonUtils.ConstantMsg.Exception430Title;
                errorInfo = XMLHttpRequest.responseText;
            } else if ("500" == errorStatus) {
                errorInfo = CommonUtils.ConstantMsg.Exception500Message;
            } else if ("404" == errorStatus) {
                errorInfo = CommonUtils.ConstantMsg.Exception404Message;
            } else if ("403" == errorStatus) {
            	errorInfo = CommonUtils.ConstantMsg.Exception403Message;
            } else if ("4081" == errorStatus) {
                BootstrapDialog.alert({
                    message: CommonUtils.ConstantMsg.ExceptionLoginTimeout,
                    callback: function (result) {
                        window.location.href = webAppPath + "/login";
                    }
                });
                return;
            } else {
                errorInfo = CommonUtils.ConstantMsg.Exception500Message;
            }

            BootstrapDialog.alert({
                title: errortitle,
                message: errorInfo,
                type: errortype
            });
        }
    })

    /**
     * CodeListUtils
     * hl 2017-04-10
     */
    var CodeListDataSet = {
        //数据集
        data: {},
        /**
         * 页面加载初始化CodeList
         * @param:codelistTypeNames[] 例:common.CodeListDataSet.init(['sys_user_type', 'yes_no']);
         * @returns:codelist[]
         */
        init: function (codeTypeNames) {
            var codeTypeList = [];
            $.each(codeTypeNames, function (i) {
                codeTypeList.push(codeTypeNames[i]);
            });

            if (codeTypeList.length > 0) {
                var uri = "common/getCodelist";
                var param = {};
                param.codeTypeList = codeTypeList;

                var result = CommonUtils.ajaxPost(uri, param, false);
//	            $.when(defer).done(function (result) {
                this.data = $.parseJSON(result);
                return this.data;
//	            });

            }
        },
        /**
         * 获取指定CodeList
         * @param:codeType String
         * @returns:codelist []
         */
        getCodelist: function (codeType) {
            if (!this.checkDataSet()) {
                return false;
            }
            if (typeof codeType !== 'undefined') {
                var codelist = this.data[codeType];
                if ($.isEmptyObject(codelist)) {
                    $.error('CodeList[' + codeType + ']数据未初始化');
                    return "";
                }
                return codelist;
            }
        },
        /**
         * 获取指定Code
         * @param:codeType String, codeValue String
         * @returns:code {}
         */
        getCode: function (codeType, codeValue) {
            if (!this.checkDataSet()) {
                return false;
            }
            if (typeof codeType !== 'undefined' && typeof codeValue !== 'undefined') {

                var codelist = this.data[codeType];
                var code;
                if ($.isEmptyObject(codelist)) {
                    $.error('CodeList[' + codeType + ']数据未初始化');
                    return "";
                }
                $.each(codelist, function (i, obj) {
                    if (obj.value === codeValue) {
                        code = obj;
                        return;
                    }
                });

                if ($.isEmptyObject(code)) {
                    return "";
                }
                return code;
            }
        },
        /**
         * 获取指定CodeLabel,多选时，用“,”号拼接
         * @param:codeType String,codeValue String
         * @returns:String codeLabel String
         */
        getLabel: function (codeType, codeValue, separator) {
            var that = this;
            if (!this.checkDataSet()) {
                return false;
            }

            if (codeValue == null && typeof codeValue === 'undefined') {
                return '';
            }
            var splitstr = (separator != null && typeof separator !== 'undefined') ? separator : ",";
            var codelist = codeValue.split(splitstr);
            var labels = '';
            $.each(codelist, function (i, value) {
                var code = that.getCode(codeType, value);
                if (code !== '' && typeof code !== 'undefined') {
                    labels += ((i === 0) ? '' : '，') + code.label;
                }
            })
            return labels;
        },
        checkDataSet: function () {
            if ($.isEmptyObject(this.data)) {
                $.error('CodeList数据未初始化');
                return false;
            }
            return true;
        }
    }

    CommonUtils.CodeListDataSet = CodeListDataSet;

//    $.CodeListDataSet.init(['sys_user_type', 'DICT_AGEUNIT', 'yes_no']);
//    console.log($.CodeListDataSet.getCodelist(['sys_user_type']));
//    console.log($.CodeListDataSet.getCode('sys_user_type', '1'));
//    console.log($.CodeListDataSet.getLabel('sys_user_type', '1'));
//    console.log($.CodeListDataSet.data);
    /**
     * 重置单选/多选（新版style）
     * @param name
     * @returns
     */
    CommonUtils.resetCheckBox = function (name) {
        $("input[name='" + name + "']").prop("checked", false);
        $("input[name='" + name + "']").parent('.active').removeClass('active');
    }


    /**
     * 将form表单转化成Javascript object
     * 例:$('#searchForm').serializeObject();
     * hl 2017-04-12
     */
    $.fn.serializeObject = function () {
        var o = [];
        var param = '';
        var inputRadio = this.find(':radio');
        this.find('input').each(function () {
            if (!$.trim(this.value)) {
                return true;
            }
            if (this.type === 'text' || this.type === 'hidden') {
                var obj = {};
                obj.name = this.name;
                obj.value = $.trim(this.value);
                o.push(obj);
            } else if (this.type === 'radio' || this.type === 'checkbox') {
                if (this.checked === true) {
                    var obj = {};
                    obj.name = this.name;
                    obj.value = $.trim(this.value);
                    o.push(obj);
                }
            }
        });
        this.find('select option:selected').each(function () {
            if (!$.trim(this.value)) {
                return true;
            }
            var obj = {};
            obj.name = $(this).parent().attr('name');
            obj.value = $.trim(this.value);
            o.push(obj);
        });
        this.find('textarea').each(function () {
            if (!$.trim(this.value)) {
                return true;
            }
            var obj = {};
            obj.name = this.name;
            obj.value = $.trim(this.value);
            o.push(obj);
        });
        var sobj = {};
        $.each(o, function () {
            if (sobj[this.name] !== undefined) {
                if (!sobj[this.name].push) {
                    sobj[this.name] = [sobj[this.name]];
                }
                sobj[this.name].push(this.value);
            } else {
                sobj[this.name] = this.value;
            }
        });
        return sobj;
    };

    /**
     * 序列化Form表单，包含disabled readOnly值
     * 例:$('#searchForm').serializeData();
     * 参数 true,收集空值的属性,如:userType=''
     * hl 2017-04-14
     */
    $.fn.serializeData = function (collectionEmpty) {
        if (!collectionEmpty) {
            collectionEmpty = false;
        }
        var o = [];
        var param = '';
        var inputRadio = this.find(':radio');
        this.find('input').each(function () {
            if (!collectionEmpty && !$.trim(this.value)) {
                return true;
            }
            if (this.type === 'text' || this.type === 'hidden') {
                var obj = {};
                obj.name = this.name;
                obj.value = $.trim(this.value);
                o.push(obj);
            } else if (this.type === 'radio' || this.type === 'checkbox') {
                if (this.checked === true) {
                    var obj = {};
                    obj.name = this.name;
                    obj.value = $.trim(this.value);
                    o.push(obj);
                }
            }
        });
        this.find('select').each(function () {
            var options = $(this).find('option:selected');
            var sname = $(this).attr('name');
            if (collectionEmpty && options.length === 0) {
                var obj = {};
                obj.name = sname;
                obj.value = '';
                o.push(obj);
                return true;
            }

            options.each(function () {
                var obj = {};
                obj.name = sname;
                obj.value = this.value;
                o.push(obj);
            });

        });
//        this.find('select option:selected').each(function () {
//            if (!collectionEmpty && !$.trim(this.value)) {
//                return true;
//            }
//            var obj = {};
//            obj.name = $(this).parent().attr('name');
//            obj.value = $.trim(this.value);
//            o.push(obj);
//        });
        this.find('textarea').each(function () {
            if (!collectionEmpty && !$.trim(this.value)) {
                return true;
            }
            var obj = {};
            obj.name = this.name;
            obj.value = $.trim(this.value);
            o.push(obj);
        });
        $.each(o, function () {
            param += '&' + this.name + '=' + encodeURIComponent(this.value);
        })
        return param;
    }
    /**
     * 重置Form表单
     * 例:$('#searchForm').resetFormData();
     * hl 2017-04-14
     */
    $.fn.resetFormData = function () {
        this.find('input:text').val('');//重置text
        this.find('input[type="hidden"]').val('');//重置hidden
        this.find('input:radio').attr('checked', false);//重置radio
        this.find('input:checkbox').attr('checked', false);//重置checkbox
        this.find('textarea').val('');//重置textarea
        if (this.find('select').length > 0) {
            this.find('select').selectpicker('val', '');//重置下拉框
            this.find('select[data-area-id="city"]').empty().selectpicker('refresh');
            this.find('select[data-area-id="county"]').empty().selectpicker('refresh');
            this.find('select[data-area-id="town"]').empty().selectpicker('refresh');
        }
        this.find('input:file').val('');//重置text
    }
    /*!
     * hl 2015-11-10
     * fmoney(20000, 2) -> 20,000.00
     * rmoney(2,000.10) -> 2000.1
     * formatFloat((numA + numB),3) 0.1 + 0.2 = 0.3
     */
    CommonUtils.fmoney = function (s, n) {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r;
    }

    CommonUtils.rmoney = function (s) {
        return parseFloat(s.replace(/[^\d\.-]/g, ""));
    }

    CommonUtils.formatFloat = function (n, f) {
        var m = Math.pow(10, f);
        var fixnm = parseFloat(n * m).toFixed(2);
        return parseInt(fixnm, 10) / m;
    }

    /**
     * 查询区域'更多'按钮事件
     */
    $('.panel-more-query').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var sinput = $this.parent().siblings('.search-body').children();
        if (sinput.length === 0) {
            sinput = $this.parent().siblings('form').children('.search-body').children();
        }
        if (typeof $this.data('isOpen') === 'undefined' || $this.data('isOpen') !== true) {
            sinput.each(function (i) {
                if ($(this).hasClass("hidden")) {
                    $(this).removeClass('hidden');
                    $(this).attr('hideflag', true);
                }
                ;
            })
            $this.data('isOpen', true);
            $this.find('span.glyphicon-triangle-bottom').removeClass('glyphicon-triangle-bottom').addClass('glyphicon-triangle-top');
        } else {
            sinput.each(function (i) {
                if ($(this).attr("hideflag")) {
                    $(this).addClass('hidden');
                }
            })
            $this.data('isOpen', false);
            $this.find('span.glyphicon-triangle-top').removeClass('glyphicon-triangle-top').addClass('glyphicon-triangle-bottom');
        }
    })

    /**
     * ConstantMessage
     */
    CommonUtils.ConstantMsg = {
        ExceptionTitle: '操作过程中出错',
        Exception500Message: '无法连接服务器或者服务器出现异常。\n如果您在操作时，重复出现此错误，\n请与系统开发人员联系。',
        Exception401Title: "身份认证失败",
        Exception401Message: "身份认证失败，请重新登录",
        Exception999Title: "数据重复提交",
        Exception999Message: "数据已提交，请勿重复提交，请求操作失败",
        ExceptionLoginTimeout: "登陆超时，请重新登录",
        Exception430Title: "文件操作过程中出错",
        Exception404Message: "请求错误或页面不存在",
        Exception403Message: "操作权限不足"

    }

    var CommonConstant = {};

    // 返回结果key名称
    CommonConstant["SUCCESS"] = "success"; // 请求操作结果状态
    CommonConstant["FAIL"] = "fail"; // 请求操作结果状态
    CommonConstant["ERROR_MSG"] = "errorMsg"; // 请求操作错误信息

    // 操作结果状态值常量
    CommonConstant["RESULT_SUCCESS"] = "0"; // 成功
    CommonConstant["RESULT_FAILURE"] = "1"; // 失败


    // 操作结果状态值常量
    CommonConstant["COMMON_YES"] = "1"; // 是
    CommonConstant["COMMON_NO"] = "0"; // 否

    // 区划级别
    CommonConstant["DictZoneLevel"] = {};
    CommonConstant["DictZoneLevel"]["state"] = "1"; // 国家
    CommonConstant["DictZoneLevel"]["provice"] = "2"; // 省
    CommonConstant["DictZoneLevel"]["city"] = "3"; // 市
    CommonConstant["DictZoneLevel"]["county"] = "4"; // 区
    CommonConstant["DictZoneLevel"]["town"] = "5"; // 乡

    // 国家中心
    CommonConstant["DICT_ZONE_STATECENTER"] = "000000000";

    // 启用、未启用状态
    CommonConstant["DictStartStatus"] = {};
    CommonConstant["DictStartStatus"]["notstart"] = "0"; // 未启用
    CommonConstant["DictStartStatus"]["start"] = "1"; // 启用
    CommonConstant["DictStartStatus"]["stop"] = "2"; // 停用

    // parentIds分割符号
    CommonConstant["DICT_PARENTIDS_SEPARATOR"] = ",";

    // 性别
    CommonConstant["DictGender"] = {};
    CommonConstant["DictGender"]["unknow"] = "0"; // 未知的性别
    CommonConstant["DictGender"]["male"] = "1"; // 男性
    CommonConstant["DictGender"]["female"] = "2"; // 女性
    CommonConstant["DictGender"]["unstated"] = "9"; // 未说明的性别

    // 胎婴儿性别
    CommonConstant["DictBabyGender"] = {};
    CommonConstant["DictBabyGender"]["male"] = "1"; // 男
    CommonConstant["DictBabyGender"]["female"] = "2"; // 女
    CommonConstant["DictBabyGender"]["unknow"] = "0"; // 不详
    CommonConstant["DictBabyGender"]["unnormal"] = "3"; // 两性畸形

    // 性别-项目用
    CommonConstant["DictGenderItem"] = {};
    CommonConstant["DictGenderItem"]["currency"] = "0"; // 通用
    CommonConstant["DictGenderItem"]["male"] = "1"; // 男
    CommonConstant["DictGenderItem"]["female"] = "2"; // 女

    // 检验检查项目分类
    CommonConstant["DictExamType"] = {};
    CommonConstant["DictExamType"]["custom"] = "1"; // 自定义
    CommonConstant["DictExamType"]["routine"] = "2"; // 常规

    // 删除标识
    CommonConstant["DelFlag"] = {};
    CommonConstant["DelFlag"]["normal"] = "0"; // 正常
    CommonConstant["DelFlag"]["delete"] = "1"; // 删除

    // 机构类型
    CommonConstant["DictOrgStyle"] = {};
    CommonConstant["DictOrgStyle"]["manager"] = "1"; // 管理机构
    CommonConstant["DictOrgStyle"]["service"] = "2"; // 服务机构

    // 血细胞分类
    CommonConstant["DictHaemocytesType"] = {};
    CommonConstant["DictHaemocytesType"]["three"] = "1"; // 三分类
    CommonConstant["DictHaemocytesType"]["five"] = "2"; // 五分类


    // 签署情况
    CommonConstant["DictInformedConsentSigned"] = {};
    CommonConstant["DictInformedConsentSigned"]["female"] = "2"; // 女方签署
    CommonConstant["DictInformedConsentSigned"]["male"] = "1"; // 男方签署
    CommonConstant["DictInformedConsentSigned"]["all"] = "0"; // 双方签署

    // 完成状态
    CommonConstant["DictCompleteStatus"] = {};
    CommonConstant["DictCompleteStatus"]["notfill"] = "0"; // 未填写
    CommonConstant["DictCompleteStatus"]["notfinish"] = "1"; // 未完成
    CommonConstant["DictCompleteStatus"]["finish"] = "2"; // 完成

    // 项目值区间范围
    CommonConstant["DictValueScope"] = {};
    CommonConstant["DictValueScope"]["containAll"] = "1"; // 不包括最小值和最大值
    CommonConstant["DictValueScope"]["containMin"] = "2"; // 包括最小值不包括最大值
    CommonConstant["DictValueScope"]["containMax"] = "3"; // 包括最大值不包括最小值
    CommonConstant["DictValueScope"]["notContainAll"] = "4"; // 包括最小值和最大值

    // 早孕随访结果
    CommonConstant["DictEarlyFollowUp"] = {};
    CommonConstant["DictEarlyFollowUp"]["pregnant"] = "1"; // 已孕
    CommonConstant["DictEarlyFollowUp"]["notpregnant"] = "2"; // 未孕
    CommonConstant["DictEarlyFollowUp"]["lost"] = "3"; // 失访

    // 审核状态
    CommonConstant["DictAuditStatus"] = {};
    CommonConstant["DictAuditStatus"]["unaudit"] = "0"; // 未审核
    CommonConstant["DictAuditStatus"]["pass"] = "1"; // 通过
    CommonConstant["DictAuditStatus"]["reject"] = "2"; // 驳回

    // 胸透或者B超检查
    CommonConstant["DictChestOrB"] = {};
    CommonConstant["DictChestOrB"]["normal"] = "1"; // 正常
    CommonConstant["DictChestOrB"]["notnormal"] = "0"; // 异常
    CommonConstant["DictChestOrB"]["notsure"] = "2"; // 不能确定
    CommonConstant["DictChestOrB"]["reject"] = "3"; // 拒绝检查

    // 时限类型
    CommonConstant["DictTableType"] = {};
    CommonConstant["DictTableType"]["busi_archive"] = "1"; // 基本信息
    CommonConstant["DictTableType"]["busi_wife_general_situation"] = "2"; // 妻子一般情况表
    CommonConstant["DictTableType"]["busi_husband_general_situation"] = "3"; // 丈夫一般情况表
    CommonConstant["DictTableType"]["busi_wife_physical_exam"] = "4"; // 妻子一般体格检查表
    CommonConstant["DictTableType"]["busi_husband_physical_exam"] = "5"; // 丈夫一般体格检查表
    CommonConstant["DictTableType"]["busi_wife_genital_exam"] = "6"; // 妻子生殖系统表
    CommonConstant["DictTableType"]["busi_husband_genital_exam"] = "7"; // 丈夫生殖系统检查表
    CommonConstant["DictTableType"]["busi_exam_value_w"] = "8"; // 妻子临床检验表
    CommonConstant["DictTableType"]["busi_exam_value_b"] = "9"; // 丈夫临床检验表
    CommonConstant["DictTableType"]["busi_hemoglobin_wife"] = "10"; // 妻子血红蛋白分析表
    CommonConstant["DictTableType"]["busi_hemoglobin_husband"] = "11"; // 丈夫血红蛋白分析表
    CommonConstant["DictTableType"]["busi_gene_wife"] = "12"; // 妻子基因检测表
    CommonConstant["DictTableType"]["busi_gene_husband"] = "13"; // 丈夫基因检测表
    CommonConstant["DictTableType"]["busi_wife_b_value"] = "14"; // 妻子妇科B超检查表
    CommonConstant["DictTableType"]["busi_evaluation_advice"] = "15"; // 评估建议告知书
    CommonConstant["DictTableType"]["busi_early_visit"] = "16"; // 早孕随访
    CommonConstant["DictTableType"]["busi_pregnancy_visit"] = "17"; // 妊娠结局随访
    CommonConstant["DictTableType"]["busi_wife_physical_exam_follow"] = "18"; // 妻子随诊检验表
    CommonConstant["DictTableType"]["busi_husband_physical_exam_follow"] = "19"; // 丈夫随诊检验表

    // 确诊早孕机构
    CommonConstant["DictEarlyPregnancyDiagOrg"] = {};
    CommonConstant["DictEarlyPregnancyDiagOrg"]["org"] = "1"; // 本机构
    CommonConstant["DictEarlyPregnancyDiagOrg"]["other_org"] = "2"; // 转录其他机构确诊结果
    CommonConstant["DictEarlyPregnancyDiagOrg"]["ohter"] = "3"; // 其他情况

    // servicr url
    CommonConstant["ServiceUrl"] = {};
    CommonConstant["ServiceUrl"]["dict_exam_range"] = "DictExamRangeService"; // 参数区间设置
    CommonConstant["ServiceUrl"]["busi_plan_pregnan"] = "BusiPlanPregnanService"; // 计划怀孕夫妇
    CommonConstant["ServiceUrl"]["stat_pregnancy_month_report_report"] = "StatPregnancyMonthReportReportService"; // 统计-妊娠月报相关统计（上报）
    CommonConstant["ServiceUrl"]["stat_service_month_report_report"] = "StatServiceMonthReportReportService"; // 技术服务月报相关统计（上报）

    // 三分类五分类标识
    CommonConstant["DictThreeFiveFlag"] = {};
    CommonConstant["DictThreeFiveFlag"]["all"] = "0"; // 通用
    CommonConstant["DictThreeFiveFlag"]["three"] = "1"; // 三分类
    CommonConstant["DictThreeFiveFlag"]["five"] = "2"; // 五分类

    // 阴性阳性可疑
    CommonConstant["DictNegativePositiveSuspicious"] = {};
    CommonConstant["DictNegativePositiveSuspicious"]["positive"] = "1"; // 阳性
    CommonConstant["DictNegativePositiveSuspicious"]["negative"] = "2"; // 阴性
    CommonConstant["DictNegativePositiveSuspicious"]["suspicious"] = "3"; // 可疑

    // 时限类型
    CommonConstant["DictRole"] = {};
    CommonConstant["DictRole"]["city_service_mgr"] = "10"; // 市级服务机构管理员
    CommonConstant["DictRole"]["county_service_mgr"] = "11"; // 县级服务机构管理员
    CommonConstant["DictRole"]["statemanager"] = "1111"; // 国家级管理机构管理员（国家级管理员）
    CommonConstant["DictRole"]["town_service_mgr"] = "12"; // 乡级服务机构管理员
    CommonConstant["DictRole"]["pro_service_clinic_doc"] = "13"; // 省级服务机构临床医生
    CommonConstant["DictRole"]["pro_service_check_doc"] = "14"; // 省级服务机构检验医生
    CommonConstant["DictRole"]["pro_service_image_doc"] = "15"; // 省级服务机构影像医生
    CommonConstant["DictRole"]["pro_service_visit_doc"] = "16"; // 省级服务机构随访员
    CommonConstant["DictRole"]["pro_service_input_doc"] = "17"; // 省级服务机构录入员
    CommonConstant["DictRole"]["city_service_clinic_doc"] = "18"; // 市级服务机构临床医生
    CommonConstant["DictRole"]["city_service_check_doc"] = "19"; // 市级服务机构检验医生
    CommonConstant["DictRole"]["provincemanager"] = "2"; // 省级管理机构管理员（省级管理员）
    CommonConstant["DictRole"]["city_service_image_doc"] = "20"; // 市级服务机构影像医生
    CommonConstant["DictRole"]["city_service_visit_doc"] = "21"; // 市级服务机构随访员
    CommonConstant["DictRole"]["city_service_input_doc"] = "22"; // 市级服务机构录入员
    CommonConstant["DictRole"]["county_service_clinic_doc"] = "23"; // 县级服务机构临床医生
    CommonConstant["DictRole"]["county_service_check_doc"] = "24"; // 县级服务机构检验医生
    CommonConstant["DictRole"]["county_service_image_doc"] = "25"; // 县级服务机构影像医生
    CommonConstant["DictRole"]["county_service_visit_doc"] = "26"; // 县级服务机构随访员
    CommonConstant["DictRole"]["county_service_input_doc"] = "27"; // 县级服务机构录入员
    CommonConstant["DictRole"]["town_service_clinic_doc"] = "28"; // 乡级服务机构临床医生
    CommonConstant["DictRole"]["town_service_check_doc"] = "29"; // 乡级服务机构检验医生
    CommonConstant["DictRole"]["supermanager"] = "3"; // 国家级信息员
    CommonConstant["DictRole"]["town_service_image_doc"] = "30"; // 乡级服务机构影像医生
    CommonConstant["DictRole"]["town_service_visit_doc"] = "31"; // 乡级服务机构随访员
    CommonConstant["DictRole"]["town_service_input_doc"] = "32"; // 乡级服务机构录入员
    CommonConstant["DictRole"]["pro_info_doc"] = "4"; // 省级信息员
    CommonConstant["DictRole"]["city_info_doc"] = "5"; // 市级信息员
    CommonConstant["DictRole"]["county_info_doc"] = "6"; // 县级信息员
    CommonConstant["DictRole"]["state_check_center"] = "7"; // 国家级临检中心
    CommonConstant["DictRole"]["pro_check_center"] = "8"; // 省级临检中心
    CommonConstant["DictRole"]["pro_service_mgr"] = "9"; // 省级服务机构管理员
    CommonConstant["DictRole"]["super_mgr"] = "99999999999"; // 超级管理员

    // 审核状态
    CommonConstant["DictReportAuditStatus"] = {};
    CommonConstant["DictReportAuditStatus"]["unaudit"] = "0"; // 未审核
    CommonConstant["DictReportAuditStatus"]["pass"] = "1"; // 审核通过
    CommonConstant["DictReportAuditStatus"]["reject"] = "2"; // 驳回
    CommonConstant["DictReportAuditStatus"]["revoke"] = "3"; // 撤销审核
    CommonConstant["DictReportAuditStatus"]["abandon"] = "4"; // 放弃审核

    // 角色权限范围
    CommonConstant["RoleDataScope"] = {};
    CommonConstant["RoleDataScope"]["DATA_SCOPE_ALL"] = "0"; // 全部
    CommonConstant["RoleDataScope"]["DATA_SCOPE_SELF"] = "1"; // 本服务机构
    CommonConstant["RoleDataScope"]["DATA_SCOPE_SELF_AND_ASSIS"] = "2"; // 本服务机构与协办检查机构
    CommonConstant["RoleDataScope"]["DATA_SCOPE_SELF_AND_FOLLOW"] = "3"; // 本服务机构与预期随访机构
    CommonConstant["RoleDataScope"]["DATA_SCOPE_SELF_AND_ASSIS_AND_FOLLOW"] = "4"; // 本服务机构、协办检查机构与预期随访机构
    CommonConstant["RoleDataScope"]["DATA_SCOPE_MANAGER"] = "5"; // 本管理机构
    CommonConstant["RoleDataScope"]["DATA_SCOPE_MANAGER_AND_CHILD"] = "6"; // 本管理机构及下属管理机构

    // 上报状态
    CommonConstant["DictReportStatus"] = {};
    CommonConstant["DictReportStatus"]["notfill"] = "1"; // 未上报
    CommonConstant["DictReportStatus"]["notfinish"] = "2"; // 正常上报
    CommonConstant["DictReportStatus"]["finish"] = "3"; // 逾期上报

    // 职称
    CommonConstant["DictProfRank"] = {};
    CommonConstant["DictProfRank"]["physician"] = "1"; // 医师
    CommonConstant["DictProfRank"]["attending_doctor"] = "2"; // 主治医师
    CommonConstant["DictProfRank"]["deputy_chief_physician"] = "3"; // 副主任医师
    CommonConstant["DictProfRank"]["chief_physician"] = "4"; // 主任医师
    CommonConstant["DictProfRank"]["inspection_technician"] = "5"; // 检验技师
    CommonConstant["DictProfRank"]["chief_inspector"] = "6"; // 主管检验师
    CommonConstant["DictProfRank"]["deputy_chief_inspecto"] = "7"; // 副主任检验师
    CommonConstant["DictProfRank"]["chief_inspector"] = "8"; // 主任检验师
    CommonConstant["DictProfRank"]["nurse"] = "9"; // 护士
    CommonConstant["DictProfRank"]["senior_nurse"] = "10"; // 护师
    CommonConstant["DictProfRank"]["intermediate_charge_nurse"] = "11"; // 中级主管护师
    CommonConstant["DictProfRank"]["deputy_director_nurse"] = "12"; // 副主任护师
    CommonConstant["DictProfRank"]["director_nurse"] = "13"; // 主任护师
    CommonConstant["DictProfRank"]["other"] = "99"; // 其他

    // 科室类型
    CommonConstant["DictDeptStyle"] = {};
    CommonConstant["DictDeptStyle"]["administrative_department"] = "1"; // 行政科室
    CommonConstant["DictDeptStyle"]["clinical_departments"] = "2"; // 临床科室
    CommonConstant["DictDeptStyle"]["auxiliary_diagnosis_departments"] = "3"; // 辅诊科室
    CommonConstant["DictDeptStyle"]["auxiliary_departments"] = "4"; // 辅助科室
    CommonConstant["DictDeptStyle"]["other"] = "99"; // 其他

    // 基因类型
    CommonConstant["DictGeneType"] = {};
    CommonConstant["DictGeneType"]["no_deletion"] = "0"; // 无缺失
    CommonConstant["DictGeneType"]["heterozygous_mutation"] = "1"; // 杂合型突变
    CommonConstant["DictGeneType"]["homozygous_mutation"] = "2"; // 纯合型突变

    // 正常异常状态
    CommonConstant["DictNormalAbnormal"] = {};
    CommonConstant["DictNormalAbnormal"]["normal"] = "1"; // 正常
    CommonConstant["DictNormalAbnormal"]["abnormal"] = "0"; // 异常ss

    // 异常未见异常
    CommonConstant["DictAbnormalStatus"] = {};
    CommonConstant["DictAbnormalStatus"]["normal"] = "1"; // 未见异常
    CommonConstant["DictAbnormalStatus"]["abnormal"] = "0"; // 异常

    // RH血型
    CommonConstant["DictRhBloodGroup"] = {};
    CommonConstant["DictRhBloodGroup"]["positive"] = "1"; // 阳性
    CommonConstant["DictRhBloodGroup"]["negative"] = "2"; // 阴性

    // 清洁度
    CommonConstant["DictDegreeCleanliness"] = {};
    CommonConstant["DictDegreeCleanliness"]["one"] = "0"; // I
    CommonConstant["DictDegreeCleanliness"]["two"] = "1"; // II
    CommonConstant["DictDegreeCleanliness"]["three"] = "2"; // III
    CommonConstant["DictDegreeCleanliness"]["four"] = "3"; // IV

    // RH血型
    CommonConstant["DictPhValue"] = {};
    CommonConstant["DictPhValue"]["high"] = "1"; // >4.5
    CommonConstant["DictPhValue"]["low"] = "0"; // ≤4.5

    // 无效有效
    CommonConstant["DictInvalidValid"] = {};
    CommonConstant["DictInvalidValid"]["effective"] = "1"; // 有效
    CommonConstant["DictInvalidValid"]["invalid"] = "0"; // 无效

    // 是否
    CommonConstant["DictIdentityFlag"] = {};
    CommonConstant["DictIdentityFlag"]["yes"] = "1"; // 是
    CommonConstant["DictIdentityFlag"]["no"] = "0"; // 否

    // 有害因素
    CommonConstant["DictHarmfulFactor"] = {};
    CommonConstant["DictHarmfulFactor"]["cat_dog"] = "1"; // 猫狗
    CommonConstant["DictHarmfulFactor"]["pesticides"] = "2"; // 农药
    CommonConstant["DictHarmfulFactor"]["radiation"] = "3"; // 放射线
    CommonConstant["DictHarmfulFactor"]["smoke"] = "4"; // 被动吸烟
    CommonConstant["DictHarmfulFactor"]["other"] = "5"; // 其他

    // 停经后疾病类型
    CommonConstant["DictPostmenopausalDisease"] = {};
    CommonConstant["DictPostmenopausalDisease"]["blood"] = "1"; // 阴道流血
    CommonConstant["DictPostmenopausalDisease"]["fever"] = "2"; // 发热38.5度以上
    CommonConstant["DictPostmenopausalDisease"]["diarrhea"] = "3"; // 腹泻
    CommonConstant["DictPostmenopausalDisease"]["abdominal_pain"] = "4"; // 腹痛
    CommonConstant["DictPostmenopausalDisease"]["cold"] = "5"; // 流行性感冒
    CommonConstant["DictPostmenopausalDisease"]["hepatitis"] = "6"; // 病毒性肝炎
    CommonConstant["DictPostmenopausalDisease"]["other"] = "7"; // 其他

    // 妊娠服务机构类型
    CommonConstant["DictPregnancyOrgStyle"] = {};
    CommonConstant["DictPregnancyOrgStyle"]["county_health"] = "1"; // 县级以上医疗保健机构
    CommonConstant["DictPregnancyOrgStyle"]["county_family_plan"] = "2"; // 县级以上计划生育服务机构
    CommonConstant["DictPregnancyOrgStyle"]["town_health"] = "3"; // 乡镇卫生院
    CommonConstant["DictPregnancyOrgStyle"]["town_family_plan"] = "4"; // 乡级计划生育服务机构
    CommonConstant["DictPregnancyOrgStyle"]["other"] = "5"; // 其他机构

    // 是否被动吸烟/喝酒
    CommonConstant["DictDrinkFre"] = {};
    CommonConstant["DictDrinkFre"]["none"] = "0"; // 否
    CommonConstant["DictDrinkFre"]["occasionally"] = "1"; // 偶尔
    CommonConstant["DictDrinkFre"]["often"] = "2"; // 经常

    // 服用叶酸及开始时间
    CommonConstant["DictFolviteTime"] = {};
    CommonConstant["DictFolviteTime"]["none"] = "0"; // 未服用
    CommonConstant["DictFolviteTime"]["three"] = "1"; // 停经前至少3个月
    CommonConstant["DictFolviteTime"]["one"] = "2"; // 停经前1-2个月
    CommonConstant["DictFolviteTime"]["stop"] = "3"; // 停经后

    // 妊娠结局随访结果
    CommonConstant["DictPregnancyFollowupResults"] = {};
    CommonConstant["DictPregnancyFollowupResults"]["brith"] = "1"; // 已分娩
    CommonConstant["DictPregnancyFollowupResults"]["lost"] = "2"; // 失访
    CommonConstant["DictPregnancyFollowupResults"]["not"] = "3"; // 未分娩

    // 城镇农村标识
    CommonConstant["DictTownVillage"] = {};
    CommonConstant["DictTownVillage"]["town"] = "1"; // 城镇
    CommonConstant["DictTownVillage"]["village"] = "2"; // 农村

    // 证件类型
    CommonConstant["DictCardType"] = {};
    CommonConstant["DictCardType"]["resident_identity_card"] = "1"; // 居民身份证
    CommonConstant["DictCardType"]["identity_card"] = "2"; // 港澳居民身份证
    CommonConstant["DictCardType"]["residence_booklet"] = "3"; // 居民户口薄
    CommonConstant["DictCardType"]["passport"] = "4"; // 护照
    CommonConstant["DictCardType"]["officers_certificate"] = "5"; // 军官证
    CommonConstant["DictCardType"]["driver_license"] = "6"; // 驾驶证
    CommonConstant["DictCardType"]["other"] = "99"; // 其他证件

    // 职业分类
    CommonConstant["DictOccupationType"] = {};
    CommonConstant["DictOccupationType"]["farmer"] = "1"; // 农民
    CommonConstant["DictOccupationType"]["worker"] = "2"; // 工人
    CommonConstant["DictOccupationType"]["service_industry"] = "3"; // 服务业
    CommonConstant["DictOccupationType"]["business"] = "4"; // 经商
    CommonConstant["DictOccupationType"]["housework"] = "5"; // 家务
    CommonConstant["DictOccupationType"]["teacher"] = "6"; // 教师/公务员/职员等
    CommonConstant["DictOccupationType"]["other"] = "7"; // 其他

    // 页面控件类型
    CommonConstant["DictActiveObjStyle"] = {};
    CommonConstant["DictActiveObjStyle"]["text"] = "text"; // 文本型
    CommonConstant["DictActiveObjStyle"]["numbertext"] = "numbertext"; // 数值型
    CommonConstant["DictActiveObjStyle"]["select"] = "select"; // 下拉选择
    CommonConstant["DictActiveObjStyle"]["select2"] = "select2"; // 搜索下拉选择
    CommonConstant["DictActiveObjStyle"]["checkbox"] = "checkbox"; // 多选框
    CommonConstant["DictActiveObjStyle"]["radio"] = "radio"; // 单选框
    CommonConstant["DictActiveObjStyle"]["textarea"] = "textarea"; // 文本域
    CommonConstant["DictActiveObjStyle"]["date"] = "date"; // 日期型
    CommonConstant["DictActiveObjStyle"]["multiselect"] = "multiselect"; // 下拉多选

    // 逾期时限类型 by liuhuan at 20170519 start **
    CommonConstant["OverDue"] = {};
    CommonConstant["OverDue"]["OVERDUE"] = "overdue";	// 逾期时限
    CommonConstant["OverDue"]["OVERDUEREMIND"] = "overdueRemind";	// 逾期提醒时限
    CommonConstant["OverDue"]["OVERDUEREPORT"] = "overdueReport";	// 报表逾期时限
    CommonConstant["OverDue"]["OVERDUEINSERT"] = "overdueInsert";	// 录入时限
    CommonConstant["OverDue"]["OVERDUEUPDATE"] = "overdueUpdate";	// 修改时限
    CommonConstant["OverDue"]["OVERDUEDELETE"] = "overdueDelete";	// 删除时限
    // 逾期时限类型 by liuhuan at 20170519 end **

    //  caihe at 20170520 start **
    //申请状态
    CommonConstant["DictApplyStatus"] = {};
    CommonConstant["DictApplyStatus"]["unapply"] = "0"; // 未审核
    CommonConstant["DictApplyStatus"]["pass"] = "1"; // 审核通过
    CommonConstant["DictApplyStatus"]["back"] = "2"; // 驳回
    CommonConstant["DictApplyStatus"]["noapply"] = "3"; // 未申请
    //caihe at 20170520 end **
    
    // yuelingyun at 20170628 start **
    // 订单状态
    CommonConstant["OrderStatus"] = {};
    CommonConstant["OrderStatus"]["all"] = "9"; // 全部
    CommonConstant["OrderStatus"]["unpaid"] = "0"; // 未付款
    CommonConstant["OrderStatus"]["paid"] = "1"; // 已付款
    CommonConstant["OrderStatus"]["applyForRefund"] = "2"; // 申请退款
    CommonConstant["OrderStatus"]["refund"] = "3"; // 退款中
    CommonConstant["OrderStatus"]["refunded"] = "4"; // 已退款
    CommonConstant["OrderStatus"]["canceled"] = "5"; // 已取消
    CommonConstant["OrderStatus"]["closeed"] = "6"; // 已关闭
    // yuelingyun at 20170628 end **

    CommonUtils.CommonConstant = CommonConstant;

    /**
     * 合并两个json对象属性为一个对象
     * @param json1
     * @param json2
     * @returns json1
     */
    CommonUtils.mergeJsonObject = function (json1, json2) {
        for (var attr in json1) {
            if (json2[attr]) {
                json1[attr] = json2[attr];
            }
        }
        return json1;
    };

    /**
     * 根据身份证号获得性别
     */
    CommonUtils.getSexForIdcard = function (idcard) {
        var flag = "";
        if (parseInt(idcard.substr(16, 1)) % 2 == 1) { //性别
            flag = "1";
        } else {
            flag = "2";
        }
        return flag;
    };

    /**
     * 根据身份证号获得年龄
     */
    CommonUtils.getAgeForIdcard = function (idcard) {
        //获取年龄
        var myDate = new Date();
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();
        var age = myDate.getFullYear() - idcard.substring(6, 10) - 1;
        if (idcard.substring(10, 12) < month || idcard.substring(10, 12) == month && idcard.substring(12, 14) <= day) {
            age++;
        }
        return age;
    };

    /**
     * 根据身份证号获得出生日期
     */
    CommonUtils.getBirthForIdcard = function (idcard) {
        var birth = idcard.substring(6, 10) + "-" + idcard.substring(10, 12) + "-" + idcard.substring(12, 14);
        return birth;
    };

    /**
     * 是否为正整形
     * @param x 校验值
     */
    CommonUtils.isInteger = function (x) {
        return (typeof x === 'number') && (x % 1 === 0);
    };

    /**
     * 生成uuid
     * @return {string}
     */
    CommonUtils.getUUID = function () {
        return CommonUtils.uuid(32, 16);
    };

    /**
     * 生成uuid
     * @param len 长度
     * @param radix 基数
     * @return {string}
     */
    CommonUtils.uuid = function (len, radix) {
        var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
        var uuid = [], i;
        radix = radix || chars.length;

        if (len) {
            // Compact form
            for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
        } else {
            // rfc4122, version 4 form
            var r;

            // rfc4122 requires these characters
            uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
            uuid[14] = '4';

            // Fill in random data.  At i==19 set the high bits of clock sequence as
            // per rfc4122, sec. 4.1.5
            for (i = 0; i < 36; i++) {
                if (!uuid[i]) {
                    r = 0 | Math.random() * 16;
                    uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                }
            }
        }

        return uuid.join('');
    };

    /**
     * str转json
     * @param str json结构字符串
     * @return json对象
     */
    CommonUtils.strToJson = function (str) {
        var json = eval('(' + str + ')');
        return json;
    };

    /**
     * 超出长度,显示...
     * @param cellvalue
     * @param options
     * @param rowObject
     * @return 结果
     */
    CommonUtils.strLenFormat = function (cellvalue, options, rowObject) {
        return "<div style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap;'>" + cellvalue + "</div>";
    };

    /**
     * json中空串设置成null
     * @param json
     * @return json
     */
    CommonUtils.setEmptyToNull = function (json) {
        if ($.isEmptyObject(json) || typeof json != 'object') {
            return json;
        }
        for (var k in json) {
            if ($.isEmptyObject(json[k])) {
                json[k] = null;
            }
        }
        return json;
    };

    /**
     * json中空串设置成null
     * @param json
     * @return json
     */
    CommonUtils.isAllEmptyJson = function (json) {
        if ($.isEmptyObject(json) || typeof json != 'object') {
            return true;
        }
        for (var k in json) {
            if (!$.isEmptyObject(json[k])) {
                return false;
            }
        }
        return true;
    };

    /**
     * 判断时间大小
     * @param startDate 起始时间
     * @param endTime 结束时间
     * @return json
     */
    CommonUtils.compareTime = function (startDate, endTime) {

        if (CommonUtils.isEmpty(startDate) || CommonUtils.isEmpty(startDate)) {
            return true;
        }
        if (startDate.getTime() > endTime.getTime()) {
            return false;
        }
        return true;
    };

    /**
     * 判断为空
     * @return str
     */
    CommonUtils.isEmpty = function (str) {
        return (typeof str === 'undefined') || (str == null) || (str === "");
    };

    /**
     * 绑定表单数据
     * json 绑定表单的json数据
     * by sunpeng
     * 2017-1-18
     */
    $.fn.setFormData = function (json) {
        var formId = $(this).attr('id');
        // $("#"+formId).append('<input type="hidden" class="form-control" id="'+formId+'Data"/>');  //每个表单都有一个隐藏域负责存储数据，为重置表单使用
        var jsonObj = json;
        if (typeof json === 'string') {
            $("#" + formId + "Data").val(json);
            jsonObj = $.parseJSON(json); //转化为json对象
        } else {
            $("#" + formId + "Data").val(JSON.stringify(json));
        }

        var key, value, tagName, type, arr;
        for (x in jsonObj) {
            key = x;
            value = jsonObj[x];

            if (x == "id") {
                key = "key_id";
                value = jsonObj[x];
            }

            $(this).find("[name='" + key + "'],[name='" + key + "[]']").each(function () {
                tagName = $(this)[0].tagName;
                type = $(this).attr('type');
                if (tagName == 'INPUT') {
                    if (type == 'radio') { //单选按钮
                        console.log($(this).val(), $(this).val() == value);
                        $(this).attr('checked', $(this).val() == value);
                        $(this).prop('checked', $(this).val() == value);
                    } else if (type == 'checkbox') { //多选按钮
                        arr = value.split(',');
                        for (var i = 0; i < arr.length; i++) {
                            if ($(this).val() == arr[i]) {
                                $(this).attr('checked', true);
                                break;
                            }
                        }
                    } else {  //正常input标签
                        $(this).val(value);
                    }
                } else if (tagName == 'SELECT' || tagName == 'TEXTAREA') { //下拉框、文本域
                    $(this).val(value);
                }

            });
        }
    }

    CommonUtils.sys_currentUser = null;

    /**
     * 获取当前登录人信息
     * @return json
     */
    CommonUtils.getCurrentUser = function () {
        if (CommonUtils.sys_currentUser == null) {
            CommonUtils.sys_currentUser = CommonUtils.ajaxJson('common/getCurrentUser', {}, false);
        }
        return CommonUtils.sys_currentUser;
    }

    /**
     * 获取时间加月份方法
     * @param tmpDate
     * @param month
     * @return date
     */
    CommonUtils.addMonth = function (tmpDate, limitMonth) {
        if (CommonUtils.isEmpty(tmpDate) || CommonUtils.isEmpty(limitMonth)) {
            return null;
        }
        var resultDate = null;
        if (typeof(tmpDate) == 'string') {
            var year = tmpDate.substring(0, 4);
            var month = tmpDate.substring(5, 7);
            var date = tmpDate.substring(8, 10);
            resultDate = new Date(parseInt(year), parseInt(month) - 1, parseInt(date), 0, 0, 0);
        } else {
            resultDate = tmpDate;
        }
        if (resultDate != null && resultDate != '') {
            resultDate.setMonth(resultDate.getMonth() + parseInt(limitMonth));
        }
        return resultDate;
    }

    /**
     * 获取时间加月份方法
     * @param currentDate date类型
     * @param lastMenstruationDate date类型
     * @return date
     */
    CommonUtils.compareLastMenstruationDate = function (currentDate, lastMenstruationDate) {
        if (CommonUtils.isEmpty(currentDate) || CommonUtils.isEmpty(lastMenstruationDate)) {
            return null;
        }

        currentDate.setDate(currentDate.getDate() - 7);
        currentDate.setMonth(currentDate.getMonth() - 9);

        if (currentDate.getTime() > new Date(lastMenstruationDate.replace(/-/g, "/")).getTime()) {
            return true;
        } else {
            return false;
        }

        return false;
    }

    /**
     * 获取当前时间
     * @return date
     */
    CommonUtils.getCurrentDate = function () {
        //var currentDateJson = CommonUtils.ajaxJson('common/getCurrentDate',{},false);
        var tempDate = new Date();
        //tempDate.setTime(currentDateJson["currentDate"])
        return tempDate;

    }

    /**
     * 获取当前时间
     * @param tmpDate
     * @param month
     * @return long 毫秒数 或 格式化后的日期字符串
     */
    CommonUtils.getCurrentDateString = function (format) {
        //var currentDateJson = CommonUtils.ajaxJson('common/getCurrentDate',{},false);
        //if (CommonUtils.isEmpty(format)) {
        //   return currentDateJson["currentDate"];
        //}
        var tmpDate = new Date();
        //tmpDate.setTime(currentDateJson["currentDate"]);
        if (CommonUtils.isEmpty(format)) {
            return tmpDate.getTime();
        } else {
            return CommonUtils.formatDate(tmpDate, format);
        }

    }

    /**
     * 日期格式化
     * @param tmpDate
     * @param formatString 'yyyy-MM-dd'
     * @return string
     */
    CommonUtils.formatDate = function (tmpDate, formatString) {

        if (tmpDate == null || typeof(tmpDate) == "undefined" || isNaN(tmpDate.getTime())) {
            return "";
        }

        var o = {
            "M+": tmpDate.getMonth() + 1,                 //月份
            "d+": tmpDate.getDate(),                    //日
            "h+": tmpDate.getHours(),                   //小时
            "m+": tmpDate.getMinutes(),                 //分
            "s+": tmpDate.getSeconds(),                 //秒
            "q+": Math.floor((tmpDate.getMonth() + 3) / 3), //季度
            "S": tmpDate.getMilliseconds()             //毫秒
        };

        if (/(y+)/.test(formatString)) {
            formatString = formatString.replace(RegExp.$1, (tmpDate.getFullYear() + "").substr(4 - RegExp.$1.length));
        }

        for (var k in o) {
            if (new RegExp("(" + k + ")").test(formatString)) {
                formatString = formatString.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }

        return formatString;
    }

    /**
     * 时限判断
     * @param tmpDate
     * @param month
     * @return boolean
     */
    CommonUtils.isLimit = function (tmpDate, limitMonth) {
        if (CommonUtils.isEmpty(tmpDate) || CommonUtils.isEmpty(limitMonth)) {
            return false;
        }
        var currentDate = CommonUtils.getCurrentDate();
        var limitDate = CommonUtils.addMonth(tmpDate, limitMonth);
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);
        if (currentDate.getTime() > limitDate.getTime()) {
            return false;
        }
        return true;

    }


    CommonUtils.Permission = [];
    /**
     * 校验按钮权限标识
     * @return json
     * hl 2017-04-27 11:25:16
     */
    CommonUtils.hasPermission = function (permission) {
        if (permission === null || typeof permission === 'undefined') {
            return false;
        }
        if (CommonUtils.Permission.length === 0) {
            CommonUtils.Permission = CommonUtils.ajaxJson('common/getAuth', {}, false);
        }

        if ($.inArray(permission, CommonUtils.Permission) !== -1) {
            return true;
        }
        return false;
    }

    CommonUtils.getCheckBoxValue = function (checkBoxEvent, descId) {
        if ($(checkBoxEvent).is(':checked')) {
            $('#' + descId).attr("disabled", false);
        } else {
            $('#' + descId).val("");
            $('#' + descId).attr("disabled", true);
        }
    }

    /**
     * 弹出页面添加关闭页面监听事件，提示'信息尚未保存，确认关闭？'。
     * @param dialog, message
     * hl 2017-04-28 16:00:44
     */
    CommonUtils.showUnSavedConfirm = function (dialog, message) {
        CommonUtils.unSavedConfirmCloseable = false;
        message = (message === null || typeof message === 'undefined') ? '信息尚未保存，确认关闭？' : message;
        try {
            dialog.options.onhide = function () {
                if (!CommonUtils.unSavedConfirmCloseable) {
                    BootstrapDialog.confirm(message, function (result) {
                            if (result) {
                                CommonUtils.unSavedConfirmCloseable = true;
                                dialog.close();
                            }
                        }
                    );
                }
                return CommonUtils.unSavedConfirmCloseable;
            }
        } catch (e) {
            if (dialog) {
                try {
                    dialog.close();
                } catch (e) {

                }
            }
            return true;
        }
    }

    /**
     * 校验身份证
     * @param code 身份证
     * @return {boolean} true校验通过，false校验不通过
     */
    CommonUtils.identityCodeValid = function (code) {
        var city = {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江 ",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北 ",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏 ",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门",
            91: "国外 "
        };
        var tip = "";
        var pass = true;

        if (!code || !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/i.test(code)) {
            tip = "身份证号格式错误";
            pass = false;
        }

        else if (!city[code.substr(0, 2)]) {
            tip = "地址编码错误";
            pass = false;
        }
        else {
            //18位身份证需要验证最后一位校验位
            if (code.length == 18) {
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
                //校验位
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
                    tip = "校验位错误";
                    pass = false;
                }
            }
        }
        if (!pass) alert(tip);
        return pass;
    };

    CommonUtils.printShow = function (url) {
        var name = '打印预览';                            //网页名称，可为空; 
        var iWidth = 1000;                          //弹出窗口的宽度; 
        var iHeight = 800;                         //弹出窗口的高度; 
        //获得窗口的垂直位置 
        var iTop = (window.screen.availHeight - 20 - iHeight) / 2;
        //获得窗口的水平位置 
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
        window.open(webAppPath + url, name, 'height=' + iHeight + ',width=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
    }
    return CommonUtils;
});

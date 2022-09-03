<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>
<style>
    <!--
    .photo-img {
        position: absolute;
        max-height: 280px;
        max-height: 225px;
    }

    -->
</style>
<div class="" style="min-height: 400px">
    <div class="panel panel-default">
        <div class="panel-heading-blue">
            <div class="panel-title-blue">
                <span class="glyphicon glyphicon-list-alt"></span>
                专家基础信息
            </div>
            <button class="btn btn-primary btn-xs btn-wide pull-right">提交</button>
            <button class="btn btn-primary btn-xs btn-wide pull-right">提交</button>
        </div>

        <form id="institutionForm2" name="institutionForm2">
            <div class="panel-body form-horizontal">
                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label required">
                        机构代码：
                    </label>

                    <div class="col-xs-3" data-toggle="popover" data-trigger="focus"
                         data-content="请输入20位有效字符">

                        <input type="text" class="form-control" name="jgdm">
                    </div>
                    <div class="col-xs-1-sm control-label text-red pull-left"></div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label required">
                        分区开户行全称（支行）：
                    </label>

                    <div class="col-xs-3" data-toggle="popover" data-trigger="focus"
                         data-content="请输入20位数字">
                        <div class="input-group">
                            <input type="text" name="fqkhhqc" class="form-control"
                                   placeholder="点击按钮选择银行">
                            <span class="input-group-btn">
			                                            <button class="btn btn-default form-control" type="button"
                                                                id="xzqhbtn"><span
                                                                class="glyphicon glyphicon-search"></span></button>
			                                          </span>
                        </div>
                    </div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label required">
                        选择日期：
                    </label>

                    <div class="col-xs-3">
                        <div class="input-group date form_datetime"
                             data-date-format="dd MM yyyy">
                            <input class="form-control" size="10" type="text" value=""
                                   readonly name="xzrq">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>


                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">选择日期范围：</label>

                    <div class="col-xs-8">
                        <div class="form-inline">
                            <div class="input-group date form_datetime" data-date="2016-10-14"
                                 data-date-format="dd MM yyyy">
                                <input class="form-control" size="10" type="text" value=""
                                       name="xzrq1">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            <span class="control-label valid-message"></span>
                            至
                            <div class="input-group date form_datetime" data-date="2016-10-14"
                                 data-date-format="dd MM yyyy">
                                <input class="form-control" size="10" type="text" value=""
                                       name="xzrq2">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>

                            </div>
                        </div>
                    </div>
                </div>


                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">
                        机构性质：
                    </label>

                    <div class="col-xs-3" name="legal_type">
                        <select class="selectpicker" name="legal_type2"
                                id="legal_type2" title="请选择">
                            <option value="01" disabled>
                                企业
                            </option>
                            <option value="02">
                                机关法人
                            </option>
                            <option value="03">
                                事业单位
                            </option>
                            <option value="04">
                                机关组织
                            </option>
                            <option value="05">
                                其他
                            </option>
                        </select>
                        <span class="control-label valid-message"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        多选：
                    </label>

                    <div class="col-xs-6">
                        <div class="btn-group btn-group-standard" data-toggle="buttons">
                            <label class="btn btn-option active">
                                <input type="checkbox" name="ck" autocomplete="off" value="checkbox1"
                                       checked> Checkbox 1
                                <span class="checked"></span>
                            </label>
                            <label class="btn btn-option disabled">
                                <input type="checkbox" name="ck" autocomplete="off" value="checkbox2"
                                       disabled>
                                Checkbox 2
                                <span class="checked"></span>
                            </label>
                            <label class="btn btn-option">
                                <input type="checkbox" name="ck" autocomplete="off" value="checkbox3">
                                Checkbox 3
                                <span class="checked"></span>
                            </label>
                            <label class="btn btn-option">
                                <input type="checkbox" name="ck" autocomplete="off" value="checkbox4">
                                Checkbox 4
                                <span class="checked"></span>
                            </label>
                        </div>
                    </div>

                    <div class="col-xs-2-sm">
                        <a href="#" class="btn btn-link" id="getcheckbox">获取多选择值</a>
                        <a href="#" class="btn btn-link" id="checkboxreset">重置</a>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        单选：
                    </label>

                    <div class="col-xs-5">
                        <div class="btn-group btn-group-standard" data-toggle="buttons">
                            <label class="btn btn-option labelthis">
                                <input type="radio" name="ra" id="option1" value="radio1"
                                       autocomplete="off"> Radio 1
                                <span class="checked"></span>
                            </label>
                            <label class="btn btn-option disabled">
                                <input type="radio" name="ra" id="option2" value="radio2"
                                       autocomplete="off" disabled>
                                Radio 2
                                <span class="checked"></span>
                            </label>
                            <label class="btn btn-option active text-muted">
                                <input type="radio" name="ra" id="option3" value="radio3"
                                       autocomplete="off" checked>
                                <span class="checked"></span>
                                Radio 3
                            </label>
                            <label class="btn btn-option">
                                <input type="radio" name="ra" id="option4" value="radio4"
                                       autocomplete="off">
                                <span class="checked"></span>
                                Radio 4
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-3-sm">
                        <a href="#" class="btn btn-link" id="getradio">获取单选值</a>
                        <a href="#" class="btn btn-link" id="radioreset">重置</a>
                    </div>

                </div>
                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4-sm control-label text-right">多选：</label>

                    <div class="col-xs-5">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="checkbox2"> 乘用车1
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="checkbox2"> 乘用车2
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="checkbox2" disabled> 乘用车3
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="checkbox2" disabled> 乘用车4
                        </label>

                    </div>
                </div>

                <div class="form-group" data-msg-direction="r">
                    <label class="col-xs-4-sm control-label">
                        是否停用：
                    </label>

                    <div class="col-xs-2" name="is_valid" id="is_valid">
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio1"
                                   value="option1"> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio2"
                                   value="option2"> 否
                        </label>
                        <span class="control-label valid-message"></span>
                    </div>

                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">上传附件：</label>

                    <div class="col-xs-3">
                        <div class=input-group">
                            <input type="file" id="input12" name="file12" value="33333" multiple>
                            <span class="control-label valid-message"></span>
                        </div>
                    </div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">
                        备注：
                    </label>

                    <div class="col-xs-4" data-toggle="popover" data-trigger="focus"
                         data-content="最大可输入500个汉字" data-placement="top">
                                            <textarea name="remark" class="form-control"
                                                      onpropertychange="if(value.length>500) value=value.substr(0,500)"
                                                      name="note" rows="3"></textarea>

                        <p></p>

                        <p class="text-muted">
                            注：专家的工作单位、近三年内兼职和有利害关系的单位、近三年内配偶和直系亲属任兼职单位情况说明含有此信息的，将不予以抽取。</p>
                    </div>
                </div>

                <div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>抽取条件信息</strong></h4>
                        </div>
                        <div class="col-xs-8 text-right">
                            <button type="button" data-formatter="commands"
                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                            </button>
                        </div>
                    </div>
                </div>

                <div class="form-group conditionbase">
                    <label class="col-xs-4-sm control-label">选择单位：</label>

                    <div class="col-xs-3">
                        <div class="input-group">
                            <input type="text" class="form-control" id="extract_major_name1">

                            <div class="input-group-btn">
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">请选择 <span class="caret"></span></button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><a href="#">计算机</a></li>
                                    <li><a href="#">化学工程与工艺</a></li>
                                    <li><a href="#">量子力学</a></li>
                                    <li><a href="#">生物工程</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!--<div class="col-xs-2">
                        <div class="input-group" style="width: inherit">
                            <span class="input-group-btn">
                                <button class="btn btn-default"><span
                                        class="glyphicon glyphicon-minus"></span></button>
                                &lt;!&ndash;<input class="btn" type="button" value="-" onclick="minuschoosenum(1)"&ndash;&gt;
                                       &lt;!&ndash;ID="minuschoosenum1"&ndash;&gt;
                                       &lt;!&ndash;NAME="minuschoosenum1">&ndash;&gt;
                            </span>
                            <input type="text" class="form-control"
                                   aria-label="Amount (to the nearest dollar)" name="choosenum"
                                   id="choosenum1" value="1" style="width:50px">
                           <span class="input-group-btn">
                               <button class="btn btn-default"><span
                                       class="glyphicon glyphicon-plus"></span></button>
                              &lt;!&ndash; <input class="btn" type="button" value="+" onclick="addchoosenum(1)"
                                   ID="addchoosenum1" NAME="addchoosenum1">&ndash;&gt;
                          </span>
                        </div>

                    </div>-->
                    <div class="col-xs-1">
                        <input name="spinner" class="spinner" value="12" size="5"/>
                    </div>
                </div>

                <div id="condition">

                </div>


                <div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>屏蔽条件信息</strong></h4>
                        </div>
                    </div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">选择单位：</label>

                    <div class="col-xs-4" data-toggle="popover" data-trigger="focus"
                         data-content="输入完成后按回车">
                        <div class="input-group " data-toggle="plustag" data-target="angencyTags">
                            <input type="text" name="fqkhhqc" class="form-control"
                                   placeholder="点击回车有惊喜">
                            <span class="input-group-btn">
			                                            <button class="btn btn-default form-control"
                                                                type="button"
                                                        ><i class="fa fa-level-down fa-lg fa-rotate-90"
                                                            aria-hidden="true"></i></button>
			                                          </span>
                        </div>

                    </div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">
                    </label>

                    <div class="col-xs-4">
                        <div class="plus-tag tagbtn clearfix" data-maxTips="10"
                             id="angencyTags"></div>
                        <p class="text-muted">
                            注：专家的工作单位、近三年内兼职和有利害关系的单位、近三年内配偶和直系亲属任兼职单位情况说明含有此信息的，将不予以抽取。</p>
                    </div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">专家姓名：</label>

                    <div class="col-xs-4" data-toggle="popover" data-trigger="focus"
                         data-content="输入完成后按回车">
                        <div class="input-group " data-toggle="plustag" data-target="nameTags">
                            <input type="text" name="fqkhhqc" class="form-control"
                                   placeholder="点击回车有惊喜">
                            <span class="input-group-btn">
			                                            <button class="btn btn-default form-control"
                                                                type="button"
                                                        ><i class="fa fa-level-down fa-lg fa-rotate-90"
                                                            aria-hidden="true"></i></button>
			                                          </span>
                        </div>

                    </div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">
                    </label>

                    <div class="col-xs-4">
                        <div class="plus-tag tagbtn clearfix" data-maxTips="10"
                             id="nameTags"></div>
                        <p class="text-muted">注：系统将自动屏蔽您所录入的专家姓名。</p>
                    </div>
                </div>
            </div>
        </form>

    </div>

    <div class="panel panel-default">

        <div class="panel-heading-blue">
            <div class="panel-title-blue">
                <span class="glyphicon glyphicon-export"></span>
                抽取需求
            </div>
        </div>

        <form id="institutionForm" name="institutionForm">
            <div class="panel-body form-horizontal">

                <div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>抽取条件信息1</strong></h4>
                        </div>
                        <!--<div class="col-xs-8 text-right">
                            <button type="button" data-formatter="commands"
                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                            </button>
                        </div>-->
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        机构代码：
                    </label>

                    <div class="col-xs-7">
                                    <span class="control-label-text">
                                        SSDFSDF
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        机构名称：
                    </label>

                    <div class="col-xs-7">
                                    <span class="control-label-text">
                                        沈阳市财政局
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        统一社会信用代码：
                    </label>

                    <div class="col-xs-7">
                                    <span class="control-label-text">
                                        1111122223333445679
                                    </span>
                    </div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-4-sm control-label">
                        机构性质：
                    </label>

                    <div class="col-xs-7">
                                    <span class="control-label-text">
                                        企业
                                    </span>
                    </div>
                </div>

                <div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>抽取条件信息2</strong></h4>
                        </div>
                        <!--<div class="col-xs-8 text-right">
                            <button type="button" data-formatter="commands"
                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                            </button>
                        </div>-->
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        机构类别：
                    </label>

                    <div class="col-xs-7">
                                    <span class="control-label-text">
                                        采购监督部门
                                    </span>
                    </div>

                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        区划：
                    </label>

                    <div class="col-xs-7">
                                    <span class="control-label-text">
                                        沈阳市
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        行业部门分类：
                    </label>

                    <div class="col-xs-7">
                                    <span class="control-label-text">
                                        业务处室
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        是否停用：
                    </label>

                    <div class="col-xs-4">
                                    <span class="control-label-text">
                                        是
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4-sm control-label">
                        备注：
                    </label>

                    <div class="col-xs-4">
                                    <span class="control-label-text">
                                    出于性能的考虑，所有图标都需要一个基类和对应每个图标的类。把下面的代码放在任何地方都可以正常使用。注意，为了设置正确的内补（padding），务必在图标和文本之间添加一个空格。
                                    </span>
                    </div>

                </div>

                <!--<hr>-->
                <div class="panel-footer text-center">

                    <button type="button" data-formatter="commands"
                            id="institutionReset" class="btn btn-default btn-sm btn-bottom btn-wide">
                        取&nbsp;&nbsp;消
                    </button>
                    <button type="button" data-formatter="commands"
                            id="institutionFresh" class="btn btn-default btn-sm btn-bottom btn-wide">
                        上一步
                    </button>
                    <button type="button" data-formatter="commands"
                            id="institutionSave" class="btn btn-primary btn-lg btn-bottom btn-wide">
                        提&nbsp;&nbsp;交
                    </button>

                </div>


            </div>
        </form>
    </div>

    <div class="panel panel-default">

        <div class="panel-heading-blue">
            <div class="panel-title-blue">
                <span class="glyphicon glyphicon-export"></span>
                抽取需求(二列)
            </div>
        </div>

        <form id="institutionForm5" name="institutionForm">
            <div class="panel-body form-horizontal">

                <div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>抽取条件信息1</strong></h4>
                        </div>
                        <!--<div class="col-xs-8 text-right">
                            <button type="button" data-formatter="commands"
                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                            </button>
                        </div>-->
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        机构代码：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        SSDFSDF
                                    </span>
                    </div>
                    <label class="col-xs-3 control-label">
                        机构代码：
                    </label>

                    <div class="col-xs-3-sm">
                                    <span class="control-label-text">
                                        SSDFSDF
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        机构名称：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        沈阳市财政局
                                    </span>
                    </div>
                    <label class="col-xs-3 control-label">
                        机构名称：
                    </label>

                    <div class="col-xs-3-sm">
                                    <span class="control-label-text">
                                        沈阳市财政局
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        统一社会信用代码：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        1111122223333445679
                                    </span>
                    </div>
                    <label class="col-xs-3 control-label">
                        统一社会信用代码：
                    </label>

                    <div class="col-xs-3-sm">
                                    <span class="control-label-text">
                                        1111122223333445679
                                    </span>
                    </div>
                </div>

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-3 control-label">
                        机构性质：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        企业
                                    </span>
                    </div>
                    <label class="col-xs-3 control-label">
                        机构性质：
                    </label>

                    <div class="col-xs-3-sm">
                                    <span class="control-label-text">
                                        企业
                                    </span>
                    </div>
                </div>

                <div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>抽取条件信息2</strong></h4>
                        </div>
                        <!--<div class="col-xs-8 text-right">
                            <button type="button" data-formatter="commands"
                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                            </button>
                        </div>-->
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        机构类别：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        采购监督部门
                                    </span>
                    </div>
                    <label class="col-xs-3 control-label">
                        机构类别：
                    </label>

                    <div class="col-xs-3-sm">
                                    <span class="control-label-text">
                                        采购监督部门
                                    </span>
                    </div>

                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        区划：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        沈阳市
                                    </span>
                    </div>
                    <label class="col-xs-3 control-label">
                        区划：
                    </label>

                    <div class="col-xs-3-sm">
                                    <span class="control-label-text">
                                        沈阳市
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        行业部门分类：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        业务处室
                                    </span>
                    </div>
                    <label class="col-xs-3 control-label">
                        行业部门分类：
                    </label>

                    <div class="col-xs-3-sm">
                                    <span class="control-label-text">
                                        业务处室
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        是否停用：
                    </label>

                    <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        是
                                    </span>
                    </div>
                    <label class="col-xs-3 control-label">
                        是否停用：
                    </label>

                    <div class="col-xs-3-sm">
                                    <span class="control-label-text">
                                        是
                                    </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        备注：
                    </label>

                    <div class="col-xs-8-sm">
                                    <span class="control-label-text">
                                    出于性能的考虑，所有图标都需要一个基类和对应每个图标的类。把下面的代码放在任何地方都可以正常使用。注意，为了设置正确的内补（padding），务必在图标和文本之间添加一个空格。
                                    </span>
                    </div>

                </div>

                <!--<hr>-->
                <div class="panel-footer text-center">

                    <button type="button" data-formatter="commands"
                            id="institutionReset" class="btn btn-default btn-sm btn-bottom btn-wide">
                        取&nbsp;&nbsp;消
                    </button>
                    <button type="button" data-formatter="commands"
                            id="institutionFresh" class="btn btn-default btn-sm btn-bottom btn-wide">
                        上一步
                    </button>
                    <button type="button" data-formatter="commands"
                            id="institutionSave" class="btn btn-primary btn-lg btn-bottom btn-wide">
                        提&nbsp;&nbsp;交
                    </button>

                </div>


            </div>
        </form>
    </div>

    <div class="panel panel-default">

        <div class="panel-heading-blue">
            <div class="panel-title-blue">
                <span class="glyphicon glyphicon-user"></span>
                专家信息
            </div>
        </div>

        <form id="institutionForm4" name="institutionForm">
            <div class="panel-body form-horizontal">

                <!--<div class="page-header">
                    <div class="row">
                        <div class="col-xs-4">
                            <h4><strong>抽取条件信息1</strong></h4>
                        </div>
                        &lt;!&ndash;<div class="col-xs-8 text-right">
                            <button type="button" data-formatter="commands"
                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                            </button>
                        </div>&ndash;&gt;
                    </div>
                </div>-->

                <div class="form-group" data-msg-direction="d">
                    <label class="col-xs-2 control-label">
                        机构代码：
                    </label>

                    <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入20位有效字符">
                        <input type="text" class="form-control" name="jgdm">
                    </div>
                    <!--<div class="col-xs-1-sm control-label text-red pull-left">(必填)</div>-->
                    <label class="col-xs-2 control-label">
                        证件类别及号码：
                    </label>

                    <div class="col-xs-3-sm" data-toggle="popover" data-trigger="focus"
                         data-content="请输入20位有效字符">
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">请选择 <span class="caret"></span></button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><a href="#">身份证</a></li>
                                    <li><a href="#">护照</a></li>
                                    <li><a href="#">其他</a></li>
                                </ul>
                            </div>
                            <input type="text" class="form-control" id="">


                        </div>
                    </div>

                    <div class="col-xs-2">
                        <button type="button" class="btn btn-primary btn-wide pull-right">确定
                        </button>
                    </div>
                </div>


                <table class="table  table-hover " id="tabel1">
                    <thead class="bg-default">
                    <tr class="tr">
                        <th width="25" height="42px">
                            <input type="checkbox" id="checkTop">
                        </th>
                        <th data-column-id="biditem_id" data-sortable="false">
                            项目编号
                        </th>
                        <th data-column-id="biditemname" data-sortable="false">
                            项目名称
                        </th>
                        <th data-column-id="purchasemode" data-sortable="false">
                            采购方式
                        </th>
                        <th data-column-id="item_type" data-sortable="false">
                            委托机构
                        </th>
                        <th data-column-id="auditstatedesc">
                            项目状态
                        </th>
                        <th data-column-id="auditstatedesc">
                            操作
                        </th>

                    </tr>
                    </thead>

                    <tbody>
                    <!--<tr>-->
                    <!--<td colspan="9" class="text-primary">-->
                    <!--<div class="checkbox">-->
                    <!--<label>&nbsp;-->
                    <!--<input type="checkbox"><strong>打印机</strong>-->
                    <!--</label>-->
                    <!--</div>-->
                    <!--</td>-->
                    <!--</tr>-->
                    <tr>
                        <td>
                            <input type="checkbox"/>
                        </td>
                        <td class="text-center"><s><em><a href="#">CN2016726105345</a></em></s></td>
                        <td><s><em>沈阳市财政局采购软件</em></s></td>
                        <td class="text-center"><s><em>公开招标</em></s></td>
                        <td><s><em>沈阳市公共资源交易中心</em></s></td>
                        <td class="text-center"><s><em>已受理</em></s></td>
                        <td class="text-center td-nopadding">

                            <div class="btn-group table-chose-btn">
                                <button type="button" class="btn btn-default btn-xs dropdown-toggle"
                                        data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    &nbsp;选择&nbsp;<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><a href="#">委托受理机构</a></li>
                                    <li><a href="#">编辑</a></li>
                                    <li><a href="#">删除</a></li>
                                </ul>
                            </div>

                        </td>
                    </tr>
                    <tr>

                        <td>
                            <input type="checkbox"/>
                        </td>
                        <td class="text-center"><a href="#">CN2016726105345</a></td>
                        <td>沈阳市财政局采购软件111</td>
                        <td class="text-center">公开招标</td>
                        <td>沈阳市公共资源交易中心</td>
                        <td class="text-center">已受理</td>
                        <td class="text-center td-nopadding">
                            <div class="btn-group table-chose-btn">
                                <button type="button" class="btn btn-default btn-xs dropdown-toggle"
                                        data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    &nbsp;选择&nbsp;<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><a href="#">委托</a></li>
                                    <li><a href="#">编辑</a></li>
                                    <li><a href="#">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox"/>
                        </td>
                        <td class="text-center"><a href="#">CN2016726105345</a></td>
                        <td>沈阳市财政局采购软件</td>
                        <td class="text-center">公开招标</td>
                        <td>沈阳市公共资源交易中心</td>
                        <td class="text-center">已受理</td>
                        <td class="text-center td-nopadding">
                            <div class="btn-group table-chose-btn">
                                <button type="button" class="btn btn-default btn-xs dropdown-toggle"
                                        data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    &nbsp;选择&nbsp;<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><a href="#">委托受理机构</a></li>
                                    <li><a href="#">编辑</a></li>
                                    <li><a href="#">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox"/>
                        </td>
                        <td class="text-center"><a href="#">CN2016726105345</a></td>
                        <td>沈阳市财政局采购软件</td>
                        <td class="text-center">公开招标</td>
                        <td>沈阳市公共资源交易中心</td>
                        <td class="text-center">已受理</td>
                        <td class="text-center"><a href="#">编辑</a> <a href="#">删除</a></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox"/>
                        </td>
                        <td class="text-center"><a href="#">CN2016726105345</a></td>
                        <td>沈阳市财政局采购软件</td>
                        <td class="text-center">公开招标</td>
                        <td>沈阳市公共资源交易中心</td>
                        <td class="text-center">已受理</td>
                        <td class="text-center"><a href="#" class="cancel">取消</a> <a href="#"
                                                                                     class="recovery">恢复</a>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <!--<hr>-->
                <div class="panel-footer text-center">

                    <button type="button" data-formatter="commands"
                            id="" class="btn btn-default btn-sm btn-bottom btn-wide">
                        取&nbsp;&nbsp;消
                    </button>
                    <button type="button" data-formatter="commands"
                            id="" class="btn btn-default btn-sm btn-bottom btn-wide">
                        上一步
                    </button>
                    <button type="button" data-formatter="commands"
                            id="" class="btn btn-primary btn-lg btn-bottom btn-wide">
                        提&nbsp;&nbsp;交
                    </button>

                </div>


            </div>
        </form>
    </div>

    <div class="title-line">
        <div class="title-bar">
            <span class="title-text">专家注册</span>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel-heading-blue">
            <div class="panel-title-blue">
                <span class="glyphicon glyphicon-ok"></span>
                提交成功
            </div>
        </div>

        <form id="institutionForm3" name="institutionForm3">
            <div class="panel-body form-horizontal">
                <br>
                <br>

                <div class="row">
                    <div class="col-xs-7 col-xs-offset-3">

                        <div class="media">
                            <a class="media-left" href="#">
                                <img src="<%=request.getContextPath() %>/images/regsuccess.jpg" alt="...">
                            </a>

                            <div class="media-body media-middle">
                                <h5 class="media-heading">您的专家注册信息已经提交成功，请等待审核，并进行专家公示。</h5>
                                <h5>如果审核及公示没有问题，系统将以短信的形式通知您的账号和密码。</h5>
                                <h5>谢谢您对沈阳市政府采购的支持。</h5>
                            </div>
                        </div>

                    </div>
                </div>
                <br>
                <br>

                <div class="row">
                    <div class="col-xs-7 col-xs-offset-3">


                        <p>请您将沈阳市政府采购评审专家的申报纸质材料及时提交沈阳市财政局采购监督管理处。</p>

                        <p>地址：沈阳市沈河区北一经街84-2号</p>

                        <p>联系电话：024-22828056</p>

                        <p>联系人：黄有为</p>

                        <br>

                        <p>申报纸质材料包括如下内容：</p>

                        <p>1、单位推荐信函；必须加盖单位公章。</p>

                        <p>2、单位推荐信函；必须加盖单位公章。</p>

                        <p>3、单位推荐信函；必须加盖单位公章。</p>

                    </div>
                </div>

                <br>

                <div class="panel-footer text-center">


                    <button type="button" data-formatter="commands"
                            id="institutionSave1" class="btn btn-primary btn-lg btn-bottom btn-wide">
                        完&nbsp;&nbsp;成
                    </button>

                </div>
            </div>
        </form>
    </div>

    <div class="panel panel-default">

        <div class="panel-heading-blue">
            <div class="panel-title-blue">
                <span class="glyphicon glyphicon-list-alt"></span>
                提交内容
            </div>
        </div>

        <div class="panel-body">

            <table class="table table-bordered">
                <tr>
                    <td class="td-quote active">
                        <div class="text-vertical">
                            证件一
                        </div>
                    </td>
                    <td>
                        <div class="col-xs-10 col-xs-offset-2">

                            <div class="media">
                                <a class="media-left" href="#">
                                    <img src="<%=request.getContextPath() %>/images/regsuccess.jpg" alt="...">
                                </a>

                                <div class="media-body media-middle">
                                    <h5 class="media-heading">您的专家注册信息已经提交成功，请等待审核，并进行专家公示。</h5>
                                    <h5>如果审核及公示没有问题，系统将以短信的形式通知您的账号和密码。</h5>
                                    <h5>谢谢您对沈阳市政府采购的支持。</h5>
                                </div>
                            </div>

                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="td-quote active">
                        <div class="text-vertical">
                            证件二
                        </div>
                    </td>
                    <td>
                        <div class="col-xs-10 col-xs-offset-2">
                            <p>请您将沈阳市政府采购评审专家的申报纸质材料及时提交沈阳市财政局采购监督管理处。</p>

                            <p>地址：沈阳市沈河区北一经街84-2号</p>

                            <p>联系电话：024-22828056</p>

                            <p>联系人：黄有为</p>

                            <br>

                            <p>申报纸质材料包括如下内容：</p>

                            <p>1、单位推荐信函；必须加盖单位公章。</p>

                            <p>2、单位推荐信函；必须加盖单位公章。</p>

                            <p>3、单位推荐信函；必须加盖单位公章。</p>

                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="td-quote active">
                        <div class="text-vertical">
                            证件三
                        </div>
                    </td>
                    <td>
                        <div class="col-xs-12">
                            <div class="panel-body form-horizontal">

                                <div class="page-header">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <h4><strong>抽取条件信息1</strong></h4>
                                        </div>
                                        <!--<div class="col-xs-8 text-right">
                                            <button type="button" data-formatter="commands"
                                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                                            </button>
                                        </div>-->
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 control-label">
                                        机构代码：
                                    </label>

                                    <div class="col-xs-7-sm">
                                    <span class="control-label-text">
                                        SSDFSDF
                                    </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 control-label">
                                        机构名称：
                                    </label>

                                    <div class="col-xs-7-sm">
                                    <span class="control-label-text">
                                        沈阳市财政局
                                    </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 control-label">
                                        统一社会信用代码：
                                    </label>

                                    <div class="col-xs-7-sm">
                                    <span class="control-label-text">
                                        1111122223333445679
                                    </span>
                                    </div>
                                </div>

                                <div class="form-group" data-msg-direction="d">
                                    <label class="col-xs-4 control-label">
                                        机构性质：
                                    </label>

                                    <div class="col-xs-7-sm">
                                    <span class="control-label-text">
                                        企业
                                    </span>
                                    </div>
                                </div>

                                <div class="page-header">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <h4><strong>抽取条件信息2</strong></h4>
                                        </div>
                                        <!--<div class="col-xs-8 text-right">
                                            <button type="button" data-formatter="commands"
                                                    class="btn btn-primary btn-sm" id="addcondition">添加抽取条件
                                            </button>
                                        </div>-->
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 control-label">
                                        机构类别：
                                    </label>

                                    <div class="col-xs-7-sm">
                                    <span class="control-label-text">
                                        采购监督部门
                                    </span>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 control-label">
                                        区划：
                                    </label>

                                    <div class="col-xs-7-sm">
                                    <span class="control-label-text">
                                        沈阳市
                                    </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 control-label">
                                        行业部门分类：
                                    </label>

                                    <div class="col-xs-7-sm">
                                    <span class="control-label-text">
                                        业务处室
                                    </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 control-label">
                                        是否停用：
                                    </label>

                                    <div class="col-xs-4-sm">
                                    <span class="control-label-text">
                                        是
                                    </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 control-label">
                                        备注：
                                    </label>

                                    <div class="col-xs-4-sm">
                                    <span class="control-label-text">
                                    出于性能的考虑，所有图标都需要一个基类和对应每个图标的类。把下面的代码放在任何地方都可以正常使用。注意，为了设置正确的内补（padding），务必在图标和文本之间添加一个空格。
                                    </span>
                                    </div>

                                </div>


                            </div>

                        </div>
                    </td>
                </tr>
            </table>


        </div>
    </div>

    <div class="panel panel-default">

        <div class="panel-heading-blue">
            <div class="panel-title-blue">
                <span class="glyphicon glyphicon-user"></span>
                个人基础信息
            </div>
        </div>

        <div class="panel-body form-horizontal">

            <div class="form-group">
                <label class="col-xs-3 control-label">
                    姓名：
                </label>

                <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        熊本
                                    </span>
                </div>
                <label class="col-xs-3 control-label">
                    照片：
                </label>

                <div class="col-xs-3-sm">
                    <img class="thumbnail photo-img" src="<%=request.getContextPath() %>/images/photo.jpeg" alt="...">
                </div>

            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label">
                    证件类别：
                </label>

                <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        身份证
                                    </span>
                </div>

            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label">
                    证件号码：
                </label>

                <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        123123123123123123123123
                                    </span>
                </div>

            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label">
                    统一社会信用代码：
                </label>

                <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        1111122223333445679
                                    </span>
                </div>

            </div>

            <div class="form-group" data-msg-direction="d">
                <label class="col-xs-3 control-label">
                    性别：
                </label>

                <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        男
                                    </span>
                </div>

            </div>
            <div class="form-group" data-msg-direction="d">
                <label class="col-xs-3 control-label">
                    出生日期：
                </label>

                <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        2016-12-13
                                    </span>
                </div>

            </div>
            <div class="form-group" data-msg-direction="d">
                <label class="col-xs-3 control-label">
                    政治面貌：
                </label>

                <div class="col-xs-2-sm">
                                    <span class="control-label-text">
                                        党员
                                    </span>
                </div>

            </div>
        </div>
    </div>

    <div class="panel panel-default">

        <div class="panel-heading-blue">
            <div class="panel-title-blue">
                <span class="glyphicon glyphicon-user"></span>
                个人基础信息
            </div>
        </div>

        <div class="panel-body form-horizontal">
            <table class="table table-bordered" id="rowtable">
                <thead class="bg-default">
                <tr>
                    <th>title1</th>
                    <th>title2</th>
                    <th>title3</th>
                </tr>
                </thead>
                <body>
                <tr>
                    <td>1</td>
                    <td>21</td>
                    <td>31</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>21</td>
                    <td>31</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>22</td>
                    <td>32</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>23</td>
                    <td>33</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>21</td>
                    <td>31</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>22</td>
                    <td>32</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>23</td>
                    <td>33</td>
                </tr>

                </body>
            </table>

        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/layout/layout2"></script>
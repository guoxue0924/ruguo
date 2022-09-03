<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="nav nav-tabs" id="tabpage">
    <li role="presentation" class="active"><a href="#tab1content" id="tab1" role="tab" data-toggle="tab">项目查询一</a></li>
    <li role="presentation"><a href="#tab2content" id="tab2" role="tab" data-toggle="tab">项目查询二</a></li>
    <li role="presentation"><a href="#tab3content" id="tab3" role="tab" data-toggle="tab">项目查询三</a></li>
</ul>
<div class="panel panel-default panel-search">
    <div class="tab-content">
        <form id="tab1content" class="tab-pane active">
             <div class="panel-heading-line text-right">
                 <button type="button" class="btn btn-primary btn-xs btn-wide additem">按钮1</button>
                 <button type="button" class="btn btn-primary btn-xs btn-wide additem">按钮2</button>
             </div>
             
            <div class="panel-heading-search">

                <div class="panel-heading-search-group search-group-tab1">
                    <a href="#" id="query1" class="active">全部</a>
                    <a href="#" id="query2" class="">委托</a>
                    <a href="#" id="query3" class="">已受理</a>
                    <a href="#" id="query4" class="">采购中</a>
                    <a href="#" id="query5" class="">待生成合同</a>
                    <button type="button" class="btn btn-primary btn-xs additem">历史项目1</button>
                    <button type="button" class="btn btn-primary btn-xs additem">历史项目2</button>
                </div>

                <span class="panel-heading-pull-right">
                        <button type="button" class="btn btn-primary btn-xs additem"><span
                                class="glyphicon glyphicon-plus"></span> 添加项目
                        </button>
                        <button type="button" class="btn btn-primary btn-xs additem"><i class="fa fa-icon-plus"></i>+ 新建项目
                        </button>
                        <a href="#" id="extend-link-tab1"><span class="glyphicon glyphicon-search"></span> <span
                                class="caret"></span></a>
                    </span>
            </div>
            <div class="panel-body form-horizontal search-body" id="searchPanel1">

                <!-- start row 1-->
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">行政区划：</label>

                        <div class="search-input-left">
                            <div class="input-group input-group-sm">
                                <input type="text" class="form-control" id="s_xzqh" name="" placeholder="点击按钮选择行政区划"
                                       autocomplete="off">
                                <span class="input-group-btn">
                                            <button class="btn btn-default" type="button" id="xzqhbtn"><span
                                                    class="glyphicon glyphicon-search"></span></button>
                                          </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label">项目名称：</label>

                        <div class="search-input-right">
                            <input type="text" maxlength="64" value="" name="biditem_id"
                                   class="form-control">
                        </div>
                    </div>
                </div>
                <!-- end row1-->
                <!-- start row 2-->
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label ">项目类型：</label>

                        <div class="search-input-left">
                            <div class="input-group input-group-sm">
                                <input type="text" class="form-control" placeholder="选择项"
                                       aria-describedby="sizing-addon3" autocomplete="off">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-plus"></span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label ">采购方式1：</label>

                        <div class="search-input-right">
                            <input type="text" maxlength="64" value="" name="biditem_id"
                                   class="form-control">
                        </div>
                    </div>
                </div>

                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">项目状态：</label>

                        <div class="search-input-left">
                            <select class="selectpicker" name="once_triennium_type" id="xmzt"
                                    title="请选择" data-style="btn-sm btn-default">
                                <option value="1">请选择</option>
                                <option value="1">首年</option>
                                <option value="2">续签</option>
                                <option value="0">非一招三年</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label ">采购单位名称：</label>

                        <div class="search-input-right">
                            <input type="text" maxlength="64" value="" name="biditem_id"
                                   class="form-control">
                        </div>
                    </div>
                </div>


                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">策划日期：</label>

                        <div class="search-input-left form-inline">
                            <div class="input-group input-group-sm date form_datetime"
                                 data-date="2016-08-13" data-date-format="yyyy-mm-dd">
                                <input class="form-control" id="startTime3" name="startTime"
                                       type="text" value="2016-08-13" autocomplete="off">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span> </span>
                            </div>
                            &nbsp;-&nbsp;
                            <div class="input-group input-group-sm date form_datetime"
                                 data-date="2014-10-14" data-date-format="yyyy-mm-dd">
                                <input class="form-control" id="endTime3" name="endTime"
                                       type="text" autocomplete="off">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label">策划日期上诉：</label>

                        <div class="search-input-right form-inline">
                            <div class="input-group input-group-sm date form_datetime"
                                 data-date="2014-10-14" data-date-format="yyyy-mm-dd">
                                <input class="form-control" id="startTime" name="startTime"
                                       type="text" autocomplete="off">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span> </span>
                            </div>
                            &nbsp;-&nbsp;
                            <div class="input-group input-group-sm date form_datetime"
                                 data-date="2014-10-14" data-date-format="yyyy-mm-dd">
                                <input class="form-control" id="endTime" name="endTime"
                                       type="text" autocomplete="off">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">评审状态(单选)：</label>

                        <div class="search-input-left search-input-group pszt">
                            <div class="btn-group btn-group-sm btn-group-standard" data-toggle="buttons">
                                <label class="btn btn-option">
                                    <input type="radio" name="ra" id="option1" value="radio1"
                                           autocomplete="off">已审核
                                    <span class="checked"></span>
                                </label>
                                <label class="btn btn-option">
                                    <input type="radio" name="ra" id="option2" value="radio2"
                                           autocomplete="off">
                                    已受理
                                    <span class="checked"></span>
                                </label>
                                <label class="btn btn-option active">
                                    <input type="radio" name="ra" id="option3" value="radio3"
                                           autocomplete="off" checked>
                                    <span class="checked"></span>
                                    采购中
                                </label>
                                <label class="btn btn-option">
                                    <input type="radio" name="ra" id="option4" value="radio4"
                                           autocomplete="off">
                                    <span class="checked"></span>
                                    待生成合同
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label class="search-label-right control-label"></label>

                        <div class="search-input-right text-right">

                            <button type="button" class="btn btn-default btn-sm search-button" id="resetbtn1">重置
                            </button>
                            <button type="button" class="btn btn-primary btn-sm search-button" id="querybtn1">查询
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </form>
        <div id="tab2content" class="tab-pane">
            <div class="panel-heading-search">

                <div class="panel-heading-search-group tab2">
                    <a href="#" id="tab2query1" class="active">全部</a>
                    <a href="#" id="tab2query2" class="">委托</a>
                    <a href="#" id="tab2query3" class="">已受理</a>
                    <button type="button" class="btn btn-primary btn-xs">历史项目1</button>
                </div>

                <span class="panel-heading-pull-right">
                        <button type="button" class="btn btn-primary btn-xs"><span
                                class="glyphicon glyphicon-plus"></span> 添加项目
                        </button>
                        <button type="button" class="btn btn-primary btn-xs"><i class="fa fa-icon-plus"></i>+ 新建项目
                        </button>
                        <a href="#" id="extend-link-tab2"><span class="glyphicon glyphicon-search"></span> <span
                                class="caret"></span></a>
                    </span>
            </div>
            <div class="panel-body form-horizontal search-body" id="searchPanel2">

                <!-- start row 1-->
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">项目编号：</label>

                        <div class="search-input-left">
                            <div class="input-group input-group-sm">
                                <input type="text" class="form-control" placeholder="Search for...">
                                <span class="input-group-btn">
                                            <button class="btn btn-default" type="button"><span
                                                    class="glyphicon glyphicon-search"></span></button>
                                          </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label">项目名称：</label>

                        <div class="search-input-right">
                            <input type="text" maxlength="64" value="" name="biditem_id"
                                   class="form-control">
                        </div>
                    </div>
                </div>

                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">项目状态：</label>

                        <div class="search-input-left">
                            <select class="selectpicker" name="once_triennium_type" id="once_triennium_type"
                                    title="请选择" data-style="btn-sm btn-default">
                                <option value="1">首年</option>
                                <option value="2">续签</option>
                                <option value="0">非一招三年</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label ">采购单位名称：</label>

                        <div class="search-input-right">
                            <input type="text" maxlength="64" value="" name="biditem_id"
                                   class="form-control">
                        </div>
                    </div>
                </div>

                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">评审状态(多选)：</label>

                        <div class="search-input-left search-input-group">
                            <div class="btn-group btn-group-sm btn-group-standard" data-toggle="buttons">
                                <label class="btn btn-option active">
                                    <input type="checkbox" name="ck" autocomplete="off" value="checkbox1"
                                           checked>已审核
                                    <span class="checked"></span>
                                </label>
                                <label class="btn btn-option">
                                    <input type="checkbox" name="ck" autocomplete="off" value="checkbox2">
                                    已受理
                                    <span class="checked"></span>
                                </label>
                                <label class="btn btn-option">
                                    <input type="checkbox" name="ck" autocomplete="off" value="checkbox3">
                                    采购中
                                    <span class="checked"></span>
                                </label>
                                <label class="btn btn-option">
                                    <input type="checkbox" name="ck" autocomplete="off" value="checkbox4">
                                    待生成合同
                                    <span class="checked"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label class="search-label-right control-label"></label>

                        <div class="search-input-right text-right">

                            <button type="button" class="btn btn-default btn-sm search-button" id="resetbtn2">重置
                            </button>
                            <button type="button" class="btn btn-primary btn-sm search-button" id="querybtn2">查询
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div id="tab3content" class="tab-pane">
            <div class="panel-heading-search">

                <div class="panel-heading-search-group tab3">
                    <a href="#" id="tab3query1" class="active">全部</a>
                    <a href="#" id="tab3query2" class="">委托</a>
                    <a href="#" id="tab3query3" class="">已受理</a>
                    <a href="#" id="tab3query4" class="">采购中</a>
                    <a href="#" id="tab3query5" class="">待生成合同</a>

                </div>

                <span class="panel-heading-pull-right">
                        <button type="button" class="btn btn-primary btn-xs"><span
                                class="glyphicon glyphicon-plus"></span> 添加项目
                        </button>
                        <button type="button" class="btn btn-primary btn-xs"><i class="fa fa-icon-plus"></i>+ 新建项目
                        </button>
                        <a href="#" id="extend-link-tab3"><span class="glyphicon glyphicon-search"></span> <span
                                class="caret"></span></a>
                    </span>
            </div>
            <div class="panel-body form-horizontal search-body" id="searchPanel3">

                <!-- start row 1-->
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">项目编号：</label>

                        <div class="search-input-left">
                            <div class="input-group input-group-sm">
                                <input type="text" class="form-control" placeholder="Search for...">
                                <span class="input-group-btn">
                                            <button class="btn btn-default" type="button"><span
                                                    class="glyphicon glyphicon-search"></span></button>
                                          </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label">项目名称：</label>

                        <div class="search-input-right">
                            <input type="text" maxlength="64" value="" name="biditem_id"
                                   class="form-control">
                        </div>
                    </div>
                </div>
                <!-- end row1-->
                <!-- start row 2-->
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label ">项目类型：</label>

                        <div class="search-input-left">
                            <div class="input-group input-group-sm">
                                <input type="text" class="form-control" placeholder="选择项"
                                       aria-describedby="sizing-addon3">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-plus"></span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label ">采购方式1：</label>

                        <div class="search-input-right">
                            <input type="text" maxlength="64" value="" name="biditem_id"
                                   class="form-control">
                        </div>
                    </div>
                </div>

                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-left control-label">项目状态：</label>

                        <div class="search-input-left">
                            <select class="selectpicker" name="once_triennium_type"
                                    title="请选择" data-style="btn-sm btn-default">
                                <option value="1">首年</option>
                                <option value="2">续签</option>
                                <option value="0">非一招三年</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label ">采购单位名称：</label>

                        <div class="search-input-right">
                            <input type="text" maxlength="64" value="" name="biditem_id"
                                   class="form-control">
                        </div>
                    </div>
                </div>
                <!-- 重置、查询按钮 单独占据一行的情况下 使用如下代码-->
                <div class="col-xs-offset-6 col-xs-6">
                    <div class="form-group form-group-sm">
                        <label class="search-label-right control-label"></label>

                        <div class="search-input-right text-right">
                            <button type="button" class="btn btn-default btn-sm search-button">重置
                            </button>
                            <button type="button" class="btn btn-primary btn-sm search-button">查询
                            </button>
                        </div>
                    </div>
                </div>
                <!-- end row 2 -->
            </div>
        </div>
    </div>
    <div class=" panel-body">
        <table id="itemgrid" class="table table-hover">
            <thead class="bg-default" id="thead">
            <tr>
                <th data-width="58px" data-header-Align="left" data-align="left" data-column-id="itemId"
                    data-identifier="true" data-visible="true">序号
                </th>
                <th data-width="140px" data-align="center" data-column-id="itemCode" data-sortable="false"
                    data-formatter="itemCode" data-visibletitle="true">项目编号
                </th>
                <th data-column-id="itemName" data-visibletitle="true">项目名称</th>
                <th data-width="100px" data-align="right" data-column-id="amount" data-formatter="amount"
                    data-cssclass="text-red amount">项目金额
                </th>
                <th data-width="90px" data-align="center" data-column-id="createTime" data-visibletitle="true">创建日期</th>
                <th data-width="110px" data-align="center" data-column-id="purchase" data-formatter="purchase"
                    data-sortable="false">采购方式
                </th>
                <th data-width="90px" data-align="center" data-column-id="itemState" data-formatter="itemState"
                    data-sortable="false">启用状态
                </th>
                <th data-width="90px" data-align="center" data-formatter="operation" data-sortable="false">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <tr>
                <td colspan="8">正在查询</td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--<hr>-->
    <div class="panel-heading">
        手动添加项目
    </div>
    <div class=" panel-body">
        <table d="" class="table table-hover ">
            <thead class="bg-default">
            <tr class="tr">
                <th width="25" height="42px">
                    <input type="checkbox" id="checkTop">
                </th>
                <th width="58px" data-header-Align="left" data-align="left" data-column-id="itemId"
                    data-identifier="true" data-visible="true">序号
                </th>
                <th width="140px" data-align="center" data-column-id="itemCode" data-sortable="false">项目编号</th>
                <th data-column-id="itemName">项目名称</th>
                <th width="81px" data-align="center" data-column-id="purchase">采购方式</th>
                <!--                              <th width="50px" data-column-id="associate">委托机构</th>-->
                <th width="81px" data-align="right" data-column-id="amount">项目金额</th>
                <th width="90px" data-align="center" data-column-id="createTime">创建日期</th>
                <th width="81px" data-align="center" data-column-id="itemState">项目状态</th>
                <th width="100px" data-align="center" data-formatter="operation">操作</th>
            </tr>
            </thead>

            <tbody id="itemtbody">
            <tr>
                <td>
                    <input type="checkbox"/>
                </td>
                <td class="text-center">1</td>
                <td class="text-center"><a href="#">CN2016726105345</a></td>
                <td>沈阳市财政局采购软件</td>
                <td class="text-center">公开招标</td>
                <td>100000</td>
                <td class="text-center">2016-09-05</td>
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

            </tbody>
        </table>
    </div>
</div>

<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/layout/layout1"></script>
            
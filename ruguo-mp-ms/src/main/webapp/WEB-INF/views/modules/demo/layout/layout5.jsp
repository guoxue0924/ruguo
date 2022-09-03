<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            档案管理
        </div>
        <a href="#" class="panel-more-query pull-right" title="展开更多查询"><span
                class="glyphicon glyphicon-search"></span> <span class="glyphicon glyphicon-triangle-bottom"></span></a>
        <button class="btn btn-primary btn-xs btn-wide pull-right">查询</button>
        <button class="btn btn-default btn-xs btn-wide pull-right">重置</button>
    </div>
<!--     <form> -->
    <div class="panel-body form-horizontal search-body" id="searchPanel2">
		
        <!-- start row1-->
        <div class="col-xs-12-sm">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">档案编号：</label>

                    <div class="col-xs-7-sm">
                        <input type="text" maxlength="64" value="" name="biditem_id1" id="biditem_id1"
                               class="form-control" placeholder="请输入档案编号">
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">男方姓名：</label>

                    <div class="col-xs-7-sm">
                        <input type="text" maxlength="64" value="" name="biditem_id2"
                               class="form-control" placeholder="请输入男方姓名">
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">女方姓名：</label>

                    <div class="col-xs-7-sm">
                        <input type="text" maxlength="64" value="" name="biditem_id3" id="biditem_id3"
                               class="form-control" placeholder="请输入女方姓名">
                    </div>
                </div>
            </div>
        </div>
        <!-- start row2-->
        <div class="col-xs-12-sm">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">档案编号：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" placeholder="请选择档案编号">
                            <span class="input-group-btn">
                                            <button class="btn btn-default" type="button"><span
                                                    class="glyphicon glyphicon-search"></span></button>
                                          </span>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">档案编号：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" placeholder="请选择档案编号">
                            <span class="input-group-btn">
                                            <button class="btn btn-default" type="button"><span
                                                    class="glyphicon glyphicon-search"></span></button>
                                          </span>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">档案编号：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" placeholder="请选择档案编号">
                            <span class="input-group-btn">
                                            <button class="btn btn-default" type="button"><span
                                                    class="glyphicon glyphicon-search"></span></button>
                                          </span>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- start row3 -->
        <div class="col-xs-12-sm">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">项目类型：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" placeholder="Username">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-yen"></span></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">项目类型：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" placeholder="Username">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-music"></span></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">项目类型：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm">
                            <input type="text" class="form-control" placeholder="Username">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-ok"></span></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- start row4      -->
        <div class="col-xs-12-sm hidden">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">建档机构1：</label>

                    <div class="col-xs-7-sm">
                        <select class="selectpicker" name="once_triennium_type"
                                title="请选择" data-style="btn-sm btn-default">
                            <option value="1">男性</option>
                            <option value="2">女性</option>
                            <option value="0">未说明</option>
                        </select>

                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">建档机构2：</label>

                    <div class="col-xs-7-sm">
                        <select class="selectpicker" name="once_triennium_type"
                                title="请选择" data-style="btn-sm btn-default">
                            <option value="1">男性</option>
                            <option value="2">女性</option>
                            <option value="0">未说明</option>
                        </select>

                    </div>
                </div>
            </div>

            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">建档机构3：</label>

                    <div class="col-xs-7-sm">
                        <select class="selectpicker" name="once_triennium_type"
                                title="请选择" data-style="btn-sm btn-default">
                            <option value="1">男性</option>
                            <option value="2">女性</option>
                            <option value="0">未说明</option>
                        </select>

                    </div>
                </div>
            </div>
        </div>
        <!-- start row5 -->
        <div class="col-xs-12-sm hidden">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">建档时间：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime">
                            <input class="form-control" id="startTime1" name="startTime1" size="8"
                                   type="text">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">建档时间：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime">
                            <input class="form-control" id="startTime2" name="startTime2" size="8"
                                   type="text">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">建档时间：</label>

                    <div class="col-xs-7-sm">
                        <div class="input-group input-group-sm date form_datetime">
                            <input class="form-control" id="startTime3" name="startTime3" size="8"
                                   type="text">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- start row 6 -->
        <div class="col-xs-12-sm hidden">
			<div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">性别：</label>

                    <div class="col-xs-7-sm">
	                    <label class="radio-inline">
	                        <input type="radio" name="loginFlag" value="1"> 男
	                    </label>
	                    <label class="radio-inline">
	                        <input type="radio" name="loginFlag" value="2"> 女
	                    </label>
                    </div>
                </div>
            </div>            
			<div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">性别：</label>

                    <div class="col-xs-7-sm">
	                    <label class="radio-inline">
	                        <input type="radio" name="loginFlag" value="1"> 男
	                    </label>
	                    <label class="radio-inline">
	                        <input type="radio" name="loginFlag" value="2"> 女
	                    </label>
                    </div>
                </div>
            </div>            
			<div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">性别：</label>

                    <div class="col-xs-7-sm">
	                    <label class="radio-inline">
	                        <input type="radio" name="loginFlag" value="1"> 男
	                    </label>
	                    <label class="radio-inline">
	                        <input type="radio" name="loginFlag" value="2"> 女
	                    </label>
                    </div>
                </div>
            </div>            
            
        </div>
        <!-- start row7 -->
        <div class="col-xs-12-sm hidden">
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">血型：</label>

                    <div class="col-xs-7-sm">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="1"> A
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="2"> B
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="3"> C
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="4"> D
                        </label>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">血型：</label>

                    <div class="col-xs-7-sm">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="1"> A
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="2"> B
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="3"> C
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="4"> D
                        </label>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group form-group-sm">
                    <label class="col-xs-5-sm control-label">血型：</label>

                    <div class="col-xs-7-sm">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="1"> A
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="2"> B
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="3"> C
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="methods" value="4"> D
                        </label>
                    </div>
                </div>
            </div>

<!--             <div class="col-xs-4-sm text-right"> -->
<!--                 <button type="button" id="query" data-formatter="commands" -->
<!--                         class="btn btn-default btn-sm">重置 -->
<!--                 </button> -->
<!--                 <button type="button" id="query" data-formatter="commands" -->
<!--                         class="btn btn-primary btn-sm">查询 -->
<!--                 </button> -->
<!--             </div> -->
        </div>
	    
    </div>
<!--     </form> -->
    <div class="panel-heading-gray text-right">
        <button class="btn btn-primary btn-xs" id="btn1">新建档案</button>
        <button class="btn btn-primary btn-xs" id="btn2">完善档案</button>
        <button class="btn btn-primary btn-xs" id="btn2">查看详情</button>
        <button class="btn btn-primary btn-xs" id="btn2">重复参检信息查询</button>
        <button class="btn btn-primary btn-xs" id="btn2">申请修改</button>
        <button class="btn btn-primary btn-xs" id="btn2">申请删除</button>
    </div>
    <div class="panel-body">
        <table d="" class="table table-hover ">
            <thead class="bg-default">
            <tr class="tr">
                <th width="25" height="42px">
                    <input type="checkbox" id="checkTop">
                </th>
                <th width="58px" data-header-align="left" data-align="left" data-column-id="itemId"
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
                    <input type="checkbox">
                </td>
                <td class="text-center">1</td>
                <td class="text-center"><a href="#">CN2016726105345</a></td>
                <td>健康管理平台（NFPC）系统</td>
                <td class="text-center">公开招标</td>
                <td>10,000,000</td>
                <td class="text-center">2016-09-05</td>
                <td class="text-center">已受理</td>
                <td class="text-center td-nopadding">
                    <div class="btn-group table-chose-btn">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"
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
<!-- end demo 1-->
<!-- start demo 2-->
<div class="panel panel-default panel-search">
    <div class="panel-heading-blue">
        <div class="panel-title-blue">
            档案管理
        </div>
        <!--<button class="btn btn-primary btn-xs btn-wide pull-right">提交</button>-->
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btn1">新建档案</button>
        <button class="btn btn-primary btn-xs btn-wide pull-right" id="btn2">完善档案</button>
    </div>

    <div class="panel-body form-horizontal search-body" id="searchPanel2">
        <!-- start row 1-->
        <div class="col-xs-4">
            <div class="form-group form-group-sm">
                <label class="col-xs-5-sm control-label">档案编号：</label>

                <div class="col-xs-7-sm">
                    <input type="text" maxlength="64" value="" name="biditem_id1" id="biditem_id1"
                           class="form-control" placeholder="请选择档案编号">
                </div>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="form-group form-group-sm">
                <label class="col-xs-5-sm control-label">男方姓名：</label>

                <div class="col-xs-7-sm">
                    <input type="text" maxlength="64" value="" name="biditem_id2"
                           class="form-control" placeholder="请输入男方姓名">
                </div>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="form-group form-group-sm">
                <label class="col-xs-5-sm control-label">女方姓名：</label>

                <div class="col-xs-7-sm">
                    <input type="text" maxlength="64" value="" name="biditem_id3" id="biditem_id3"
                           class="form-control" placeholder="请输入女方姓名">
                </div>
            </div>
        </div>
        <!-- end row1-->
        <div class="col-xs-12">
            <div class="form-group form-group-sm text-right">
                <button type="button" id="query" data-formatter="commands"
                        class="btn btn-default btn-sm">重置
                </button>
                <button type="button" id="query" data-formatter="commands"
                        class="btn btn-primary btn-sm">查询
                </button>
            </div>
        </div>
    </div>
    <div class=" panel-body">
        <table d="" class="table table-hover ">
            <thead class="bg-default">
            <tr class="tr">
                <th width="25" height="42px">
                    <input type="checkbox" id="checkTop">
                </th>
                <th width="58px" data-header-align="left" data-align="left" data-column-id="itemId"
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
                    <input type="checkbox">
                </td>
                <td class="text-center">1</td>
                <td class="text-center"><a href="#">CN2016726105345</a></td>
                <td>健康管理平台（NFPC）系统</td>
                <td class="text-center">公开招标</td>
                <td>10,000,000</td>
                <td class="text-center">2016-09-05</td>
                <td class="text-center">已受理</td>
                <td class="text-center td-nopadding">
                    <div class="btn-group table-chose-btn">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"
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
<!-- end demo 2-->


<script src="<%=request.getContextPath()%>/plugins/require/require.js" defer async="true"
        data-main="<%=request.getContextPath()%>/modules/demo/layout/layout5"></script>

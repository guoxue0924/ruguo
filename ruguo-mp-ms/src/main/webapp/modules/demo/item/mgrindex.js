/**
 * Created by hl on 2017/4/4.

 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init', 'common', 'dialog', 'treebox', 'date', 'select', 'jtree'], function (init, common, BootstrapDialog) {
        $(function () {

            BootstrapDialog.showLoading();
            $('#userType').on('changed.bs.select', function () {
//        		$(this).children('option:checked').text()
//                console.log($(this).children('option:checked').text(), this.value);
            });
            $('.form_datetime').datetimepicker({
                format: "yyyy-mm-dd",
                pickerPosition: "bottom-left",
                //weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                startView: 2,
                todayHighlight: 1,
                //forceParse: 1,
                //showMeridian: 1,
                minView: 2
                //minuteStep: 5
            });
            $('.selectpicker').selectpicker();

            $('#btn1').click(function () {
                window.location.href = webAppPath + "/demo/user/regist"
            });
            $('#btn2').click(function () {
                $.addUserDialog = BootstrapDialog.show({
                    title: "添加用户信息",
                    size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + '/demo/user/regist2');
                    }
                });
            });
            $('#btn3').click(function () {
                var obj = grid.bootgrid("getSelectedRowsObj");
//                console.log(obj);
            });
            $('#btn4').click(function () {
                var obj = grid.bootgrid("getCurrentRows");
//            	console.log(obj);
            });
            //隐藏列
            $('#btn5').click(function () {
                BootstrapDialog.showLoading();//加载效果
                return;
                if (grid) {
                    grid.bootgrid("visibleColumns", ['email', 'name', 'phone'], false);
                }
            });
            //显示列
            $('#btn6').click(function () {
                if (grid) {
                    grid.bootgrid("visibleColumns", ['email', 'name', 'phone'], true);
                }
            });

            $('#resetbtn').click(function () {
                $('#searchForm').resetFormData();
            });
            initTree()
            var zTreeObj = null;

            function initTree(result) {

                var rootUrl = 'common/selectTree/getOrgById';
                var nodeUrl = 'common/selectTree/getOrgByParentId';
                var muliSelected = false;
                var rootId = '000000000';

                function onExpand(e, treeId, treeNode) {
//                	if (!onlySelf && !onlyChild) {
                    ztree.pNode = treeNode;
                    ztree.loadNodeByPNode();
//                    }
                }

                //单选时，取消节点文本选中颜色。
                function resetCheckedNodeClass() {
                    var nodes = zTreeObj.getCheckedNodes(true);
                    if (nodes.length) {
                        $.each(nodes, function (i, o) {
                            zTreeObj.checkNode(o, false, false);//取消选中
                            $('#' + o.tId + "_a").removeClass('curSelectedNode2');
                        })
                    }
                }

                function showIconForTree(treeId, treeNode) {
                    return !treeNode.isParent;
                };

                var zTreeObj = null;
                var ztree = {
                    setting: {
                        view: {
                            dblClickExpand: false,
                            showIcon: false
                        },
                        data: {simpleData: {enable: true, idKey: "id", pIdKey: "parentId", rootPId: '000000000'}},
                        callback: {
                            onClick: function (event, treeId, treeNode) {
//                                if (rootNodeDisabled) {
                                // var nodes = zTreeObj.getNodes();
                                if (treeNode.id == '000000000') {
                                    zTreeObj.cancelSelectedNode(false);
                                }
//                                }
                                if (!treeNode.checked) {
                                    zTreeObj.checkNode(treeNode, true, true, true);
                                } else if (muliSelected) {
                                    zTreeObj.checkNode(treeNode, false, true, true);
                                }

                                //单击节点，展开目录
//                                zTreeObj.expandNode(treeNode);
//                                onExpand(event, treeId, treeNode);
                            },
                            onExpand: function (event, treeId, treeNode) {
                                onExpand(event, treeId, treeNode);

                            },
                            onDblClick: function () {
                                // top.$.jBox.getBox().find("button[value='ok']").trigger("click");
                            },
                            onCheck: function (event, treeId, treeNode) {
                                if (treeNode.checked) {
                                    $('#' + treeNode.tId + "_a").addClass('curSelectedNode2');
                                    doCheckHandler(treeNode);
                                } else {
                                    $('#' + treeNode.tId + "_a").removeClass('curSelectedNode2');
                                }
                            },
                            beforeCheck: function (event, treeId, treeNode) {
                                if (!muliSelected) {
                                    resetCheckedNodeClass();
                                }
                            }
                        }
                    },
                    /**
                     * 一般情况下，如果一段代码中要用到一个变量
                     * 而这个变量的值是在回调函数中赋值的
                     * 这个时候要保证使用这个变量的时候回调函数已经执行了
                     */
                    loadRootNode: function () { // 加载根节点,id=000000000
                        var _this = this;

                        var defer1 = common.ajaxJson(rootUrl, {id: rootId});
                        $.when(defer1).done(function (result) {
//                        	$.each(result, function (i, o) {
//                        	    if (o.orgType === '1') {
//                        	        o.iconSkin = 'u1';
//                        	    } else if (o.orgType === '2') {
//                        	        o.iconSkin = 'u2';
//                        	    }
//                        	});
                            $.fn.zTree.init($("#orgTree"), _this.setting, result);
                            _this.zTree = $.fn.zTree.getZTreeObj("orgTree");
                            zTreeObj = _this.zTree;
                            var nodes = zTreeObj.getNodes();
                            if (nodes.length > 0) {
                                zTreeObj.expandNode(nodes[0], true, false, false, true);
                            }

                            var disabledNode = zTreeObj.getNodeByParam("level", 0);
                            if (disabledNode != null) {
                                zTreeObj.setChkDisabled(disabledNode, true, false, false);
                            }
                        });

                    },
                    /**
                     * 该方法是点击父节点的+号后执行的，意味着执行该方法的时候树已经生成了
                     * 所以才能用tree.zTree
                     */
                    loadNodeByPNode: function () {
                        var _this = this;
                        var folder = false;
                        var parameter = {
                            parentId: _this.pNode.id, //子节点的parentId是父节点的id
                            onlyManageOrg: false,
                            onlyTown: false
                        };

                        var parentNode = _this.pNode;
                        if (parentNode["children"] == null || parentNode["children"].length == 0) {
                            var defer1 = common.ajaxJson(nodeUrl, parameter);
                            $.when(defer1).done(function (result) {
//                            	$.each(result, function (i, o) {
//                            	    if (!o.orgType) {
//                            	        o.nocheck = true;
//                            	    } else if (o.orgType === '1') {
//                            	        o.iconSkin = 'u1';
//                            	    } else if (o.orgType === '2') {
//                            	        o.iconSkin = 'u2';
//                            	    }
//                            	});
                                _this.zTree.addNodes(_this.pNode, result, folder);
                            });

                        }
                    }
                };

                if (muliSelected) {
                    ztree.setting.check = {enable: true, autoCheckTrigger: false, chkboxType: {"Y": "", "N": ""}};
                } else {
                    ztree.setting.check = {enable: true, autoCheckTrigger: true, chkStyle: "radio", radioType: "all"}
                }

                function refreshTree() {
                    ztree.loadRootNode();
                }

                refreshTree();
            }

            //节点选中事件处理
            function doCheckHandler(treeNode) {
                //TODO
                alert(treeNode.id + " " + treeNode.code + " " + treeNode.name);
            }
        });
    });
});

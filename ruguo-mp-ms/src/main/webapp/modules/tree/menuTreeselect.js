/**
 * Created by liuqing on 2017/4/18.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['dialog', 'common', 'jtree'], function (BootstrapDialog, common, jtree) {
        $(function () {
            //关闭loading
            BootstrapDialog.closeLoading();

            var parameters = $.dialogParameter;
            if (common.isEmpty(parameters)) {
                return;
            }

            var extId = common.isEmpty(parameters["extId"]) ? "" : parameters["extId"];
            var selectIds = common.isEmpty(parameters["selectIds"]) ? "" : parameters["selectIds"];
            var rootUrl = common.isEmpty(parameters["rootUrl"]) ? "" : parameters["rootUrl"];
            var muliSelected = common.isEmpty(parameters["muliSelected"]) ? false : parameters["muliSelected"];
            var rootNodeDisabled = common.isEmpty(parameters["rootNodeDisabled"]) ? false : parameters["rootNodeDisabled"];

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
                    data: {simpleData: {enable: true, idKey: "id", pIdKey: "pId", rootPId: '0'}},
                    callback: {
                        onClick: function (event, treeId, treeNode) {
                            if (rootNodeDisabled) {
                                // var nodes = zTreeObj.getNodes();
//                                if (treeNode.id == '000000000') {
//                                    zTreeObj.cancelSelectedNode(false);
//                                }
                            }
                            //单击节点，展开目录
                            zTreeObj.expandNode(treeNode);
                        },
                        onExpand: function (event, treeId, treeNode) {

                        },
                        onDblClick: function () {

                        },
                        onCheck: function (event, treeId, treeNode) {
                            if (treeNode.checked) {
                                $(event.target.nextSibling).addClass('curSelectedNode2');
                            } else {
                                $(event.target.nextSibling).removeClass('curSelectedNode2');
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
                loadRootNode: function () {
                    var _this = this;
                    var defer1 = common.ajaxJson(rootUrl, {isShowHide: 0, extId: extId});
                    $.when(defer1).done(function (result) {
                        //展开选中节点的全部父级节点
                        function openParentNode(id) {

                            $.each(result, function (i, o) {
                                if (o.id === id) {
                                    o.open = true;
                                    if (o.pId !== '0') {
                                        openParentNode(o.pId);
                                    }
                                    return false;
                                }
                            });

                        }

                        $.each(result, function (i, o) {
                            if (selectIds !== '' && o.id === selectIds) {
                                o.checked = true;
                                o.open = true;
                                openParentNode(o.pId);
                            }
                        });

                        $.fn.zTree.init($("#ztree"), _this.setting, result);
                        _this.zTree = $.fn.zTree.getZTreeObj("ztree");
                        zTreeObj = _this.zTree;
                        var nodes = zTreeObj.getNodes();
                        //默认选中一级节点
                        if (nodes.length > 0 && selectIds === '') {
                            zTreeObj.expandNode(nodes[0], true, false, false, true);
                            zTreeObj.checkNode(nodes[0], true, true);
                        }

                        if (rootNodeDisabled) {
                            var disabledNode = zTreeObj.getNodeByParam("level", 0);
                            if (disabledNode != null) {
                                zTreeObj.setChkDisabled(disabledNode, true, false, false);
                            }
                        }
                    });

                },
            };

            if (muliSelected) {
                ztree.setting.check = {enable: true, autoCheckTrigger: false, chkboxType: {"Y": "", "N": ""}};
            } else {
                ztree.setting.check = {enable: true, chkStyle: "radio", radioType: "all"}
            }

            function refreshTree() {
                ztree.loadRootNode();
            }

            refreshTree();

            // 关闭窗口
            $('#btnTreeboxDialogCancle').click(function () {
                $.treeboxDialog.close();
            });

            // 保存按钮
            $('#btnTreeboxDialogSave').click(function () {
                var nodes = zTreeObj.getCheckedNodes(true);

                if (nodes.length === 0) {
                    BootstrapDialog.alert("请选择至少一个菜单！");
                    return;
                }

                parameters._callback(nodes);
                $.treeboxDialog.close();
            });

        });
    });
});

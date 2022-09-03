/**
 * Created by liuqing on 2017/4/18.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['dialog', 'common','jtree'], function (BootstrapDialog, common,jtree) {
        $(function () {
            //关闭loading
            BootstrapDialog.closeLoading();

            var parameters = $.dialogParameter;
            if (common.isEmpty(parameters)) {
                return;
            }

            var treeName = common.isEmpty(parameters["treeName"]) ? false : parameters["treeName"];
            var onlyChild = common.isEmpty(parameters["onlyChild"]) ? false : parameters["onlyChild"];
            var onlySelf = common.isEmpty(parameters["onlySelf"]) ? false : parameters["onlySelf"];
            var rootId = common.isEmpty(parameters["rootId"]) ? "" : parameters["rootId"];
            var rootUrl = common.isEmpty(parameters["rootUrl"]) ? "" : parameters["rootUrl"];
            var nodeUrl = common.isEmpty(parameters["nodeUrl"]) ? "" : parameters["nodeUrl"];
            var muliSelected = common.isEmpty(parameters["muliSelected"]) ? false : parameters["muliSelected"];

            function onExpand(e,treeId, treeNode) {
            	if (!onlySelf && !onlyChild) {
                    ztree.pNode = treeNode;
                    ztree.loadNodeByPNode();
                }
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
                    view:{
                    	dblClickExpand:false,
                    	showIcon: showIconForTree},
                    data: {simpleData: {enable: true, idKey: "id", pIdKey: "parentId", rootPId: '000000000',nocheck:true}},
                    callback: {
                        onClick: function (event, treeId, treeNode) {
                        	//单击节点，展开目录
                        	if(treeNode.isUser){
//                        		zTreeObj.checkNode(treeNode, true, false,true);//取消选中
                        	}else{
                        		zTreeObj.expandNode(treeNode);
                        		onExpand(event, treeId, treeNode);
                        	}
                        	
                        	
                        },
                        onExpand: function (event, treeId, treeNode) {
                        	onExpand(event, treeId, treeNode);

                        },onDblClick: function(){
                            // top.$.jBox.getBox().find("button[value='ok']").trigger("click");
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
                loadRootNode: function () { // 加载根节点,id=000000000
                    var _this = this;
                    if (common.isEmpty(rootId)) {
                        rootId = '000000000';
                    }
                    var defer1 = common.ajaxJson(rootUrl,{id: rootId,onlyChild:onlyChild,onlySelf:onlySelf});
                    $.when(defer1).done(function (result) {
                        $.fn.zTree.init($("#ztree"), _this.setting, result).expandAll(false);
                        _this.zTree = $.fn.zTree.getZTreeObj("ztree");
                        zTreeObj = _this.zTree;

                        var nodes = zTreeObj.getNodes();
                        if (nodes.length > 0) {
                        	nodes[0].nocheck = true;
                        	zTreeObj.updateNode(nodes[0]);
                            zTreeObj.expandNode(nodes[0], true, false, false, true);
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
                        parentId: _this.pNode.id //子节点的parentId是父节点的id
                    };

                    var parentNode = _this.pNode;
                    if (parentNode["children"] == null || parentNode["children"].length == 0) {
                        var defer1 = common.ajaxJson(nodeUrl,parameter);
                        $.when(defer1).done(function (result) {
                        	$.each(result, function (i, o) {
                        	    if (!o.isUser) {
                        	        o.nocheck = true;
                        	    } else if (o.isUser === '1') {
                        	        o.iconSkin = 'u1';
                        	    } else if (o.isUser === '2') {
                        	        o.iconSkin = 'u2';
                        	    }
                        	});
                            _this.zTree.addNodes(_this.pNode, result, folder);
                        });

                    }
                }
            };

            if (muliSelected) {
                ztree.setting.check = {enable:true , autoCheckTrigger: false, chkboxType: { "Y": "", "N": "" }};
            }else{
            	ztree.setting.check = {enable: true, chkStyle: "radio", radioType: "all"}
            }

            function refreshTree() {
                ztree.loadRootNode();
            }

            refreshTree();

            // 重置按钮
            $('#btnTreeboxDialogReset').click(function () {
                zTreeObj.cancelSelectedNode(false);
//                if (muliSelected) {
                	resetCheckedNodeClass();
                    zTreeObj.checkAllNodes(false);
//                }
            });

            // 关闭窗口
            $('#btnTreeboxDialogCancle').click(function () {
                $.treeboxDialog.close();
            });

            // 保存按钮
            $('#btnTreeboxDialogSave').click(function () {
                var ids = [], names = [], codes = [], nodes = [];
                var nodes = null;
//                if (muliSelected) {
                    nodes = zTreeObj.getCheckedNodes(true);
//                } else {
//                    nodes = zTreeObj.getSelectedNodes();
//                }

                if (treeName == "user") {
                    for(var i=0; i<nodes.length; i++) {
                        if (!nodes[i].isUser) {
                            BootstrapDialog.alert("只能选择用户！");
                            return;
                        }
                    }
                }

                parameters._callback(nodes);
                $.treeboxDialog.close();
            });

        });
    });
});

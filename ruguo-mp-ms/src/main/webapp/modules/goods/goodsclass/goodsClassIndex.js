/**
 * Created by guoxue on 2017/4/4.

 */

require([configPath], function (c) {
	//自定义模块，模块定义对照config.js,按需配置
    require(['init', 'common', 'dialog', 'treebox', 'date', 'select', 'selectuser', 'valid', 'jtree'], function (init, common, BootstrapDialog) {
        $(function () {
            
            //初始化CodeList数据
            common.CodeListDataSet.init(['DICT_IDENTITY_FLAG']);
            
            var valid = $.bootValid('#tab1content', {
                rules: {
                	goodsClassName: ['not_empty','ch_char', {'max_length': 50}],
                	goodsClassDesc: [{'max_length': 200}],
                	isMinClass:['not_empty']
                },
            });
            
            var valid2 = $.bootValid('#tab2Form', {
                rules: {
                	goodsClassName: ['not_empty','ch_char', {'max_length': 50}],
                	goodsClassDesc: [{'max_length': 200}],
                	isMinClass:['not_empty']
                },
               
            });
            
            //tab1content的保存
            $('#btnClassDetailSaveSubmit').click(function () {
            	
            	var goodsClassName = $("#tab1content input[name='goodsClassName']").val();
            	if(goodsClassName === ""){
            		BootstrapDialog.alert("请选择一个分类！");
            		return;
            	}
                	if(valid.validate()){
                    BootstrapDialog.confirm({
                        message: '确认保存分类信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goodsclass/goodsclass/edit";
                                var defer1 = common.ajaxSubmit(uri, 'tab1content');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                      		 var id = result.data
                                             initTree(id);
                                      		BootstrapDialog.alert(result.msg);
                                      	
                                      } else {
                                    	  BootstrapDialog.closeLoading();//加载效果
                                          BootstrapDialog.alert(result.msg);
                                      }
                                     
                                  });

                            }
                        }
                    });

                }
            });
            
            //tab1content的删除
            $('#btnClassDetailDelete').click(function () {
            	var goodsClassName = $("#tab1content input[name='goodsClassName']").val();
            	if(goodsClassName === ""){
            		BootstrapDialog.alert("请选择一个分类！");
            		return;
            	}
                	
                    BootstrapDialog.confirm({
                        message: '确认删除分类信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goodsclass/goodsclass/delete";
                                var defer1 = common.ajaxSubmit(uri, 'tab1content');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                      	 common.unSavedConfirmCloseable = true;//取消未保存提示。
                                      	$("#tab1content :input[type=text]").each(function () {  
                                	        $(this).val(""); 
                                	        });
                                      	$("#tab1content :input[type=radio]").each(function () {  
                                	        $(this).prop("checked",false)
                                	        });
                                      	  initTree();
                                          BootstrapDialog.alert(result.msg);
                                      } else {
                                          BootstrapDialog.closeLoading();//加载效果
                                          BootstrapDialog.alert(result.msg);
                                      }
                                  });

                            }
                        }
                    });

            });
            
            
            
          //tab2的保存
            $('#btnChildClassSaveSubmit').click(function () {
            	var goodsClassName = $("#tab1content input[name='goodsClassName']").val();
            	if(goodsClassName === ""){
            		BootstrapDialog.alert("请选择一个分类！");
            		return;
            	}
                if (valid2.validate()) {
                	
                    BootstrapDialog.confirm({
                        message: '确认保存分类信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goodsclass/goodsclass/save";
                                var defer1 = common.ajaxSubmit(uri, 'tab2Form');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                      	 common.unSavedConfirmCloseable = true;//取消未保存提示。
                                      	/*$("#tab1content :input[type=text]").each(function () {  
                                	        $(this).val(""); 
                                	        });
                                      	$("#tab1content :input[type=radio]").each(function () {  
                                	        $(this).prop("checked",false)
                                	        });*/
                                      	  var id = result.data
                                          initTree(id);
                                          BootstrapDialog.alert(result.msg);
                                         
                                      } else {
                                          BootstrapDialog.closeLoading();//加载效果
                                          BootstrapDialog.alert(result.msg);
                                      }
                                  });

                            }
                        }
                    });

                }
            });
            
            $('#btnChildClassCancel').click(function () {
            	$("#tab2content :input[type=text]").each(function () {  
        	        $(this).val(""); 
        	        });
              	$("#tab2content :input[type=radio]").each(function () {  
        	        $(this).prop("checked",false)
        	        });
//            	 $("input:radio[name='isMinClass']").attr("checked",false);
            });
            
            //上移
            $('#btnUp').click(function () {
            	var goodsClassName = $("#tab1content input[name='goodsClassName']").val();
            	if(goodsClassName === ""){
            		BootstrapDialog.alert("请选择一个分类！");
            		return;
            	}
                	
                    BootstrapDialog.confirm({
                        message: '确认上移分类信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goodsclass/goodsclass/moveup";
                                var defer1 = common.ajaxSubmit(uri, 'tab3Form');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                      	 common.unSavedConfirmCloseable = true;//取消未保存提示。
                                      	/*$("#tab1content :input[type=text]").each(function () {  
                                	        $(this).val(""); 
                                	        });
                                      	$("#tab1content :input[type=radio]").each(function () {  
                                	        $(this).prop("checked",false)
                                	        });*/
                                      	BootstrapDialog.alert(result.msg);
                                      	var id = result.data
                                     	 initTree(id);
                                          
                                      } else {
                                          BootstrapDialog.closeLoading();//加载效果
                                          BootstrapDialog.alert(result.msg);
                                      }
                                  });

                            }
                        }
                    });

           });

            //下移
            $('#btnDown').click(function () {
            	var goodsClassName = $("#tab1content input[name='goodsClassName']").val();
            	if(goodsClassName === ""){
            		BootstrapDialog.alert("请选择一个分类！");
            		return;
            	}
                	
                    BootstrapDialog.confirm({
                        message: '确认下移分类信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goodsclass/goodsclass/movedown";
                                var defer1 = common.ajaxSubmit(uri, 'tab3Form');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                      	 common.unSavedConfirmCloseable = true;//取消未保存提示。
                                      	/*$("#tab1content :input[type=text]").each(function () {  
                                	        $(this).val(""); 
                                	        });
                                      	$("#tab1content :input[type=radio]").each(function () {  
                                	        $(this).prop("checked",false)
                                	        });*/
                                          BootstrapDialog.alert(result.msg);
                                          var id = result.data
                                      	 initTree(id);
                                      } else {
                                          BootstrapDialog.closeLoading();//加载效果
                                          BootstrapDialog.alert(result.msg);
                                      }
                                  });

                            }
                        }
                    });

           });
            
            
            //置顶
            $('#btnTop').click(function () {
            	
            	var goodsClassName = $("#tab1content input[name='goodsClassName']").val();
            	if(goodsClassName === ""){
            		BootstrapDialog.alert("请选择一个分类！");
            		return;
            	}
                	
                    BootstrapDialog.confirm({
                        message: '确认置顶分类信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goodsclass/goodsclass/movetop";
                                var defer1 = common.ajaxSubmit(uri, 'tab3Form');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                      	 common.unSavedConfirmCloseable = true;//取消未保存提示。
                                      	/*$("#tab1content :input[type=text]").each(function () {  
                                	        $(this).val(""); 
                                	        });
                                      	$("#tab1content :input[type=radio]").each(function () {  
                                	        $(this).prop("checked",false)
                                	        });*/
                                          BootstrapDialog.alert(result.msg);
                                          var id = result.data
                                      	 initTree(id);
                                      } else {
                                          BootstrapDialog.closeLoading();//加载效果
                                          BootstrapDialog.alert(result.msg);
                                      }
                                  });

                            }
                        }
                    });

           });
            
            
            //置底
            $('#btnBottom').click(function () {
            	var goodsClassName = $("#tab1content input[name='goodsClassName']").val();
            	if(goodsClassName === ""){
            		BootstrapDialog.alert("请选择一个分类！");
            		return;
            	}
                	
                    BootstrapDialog.confirm({
                        message: '确认置底分类信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goodsclass/goodsclass/movebottom";
                                var defer1 = common.ajaxSubmit(uri, 'tab3Form');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                      	 common.unSavedConfirmCloseable = true;//取消未保存提示。
                                      	/*$("#tab1content :input[type=text]").each(function () {  
                                	        $(this).val(""); 
                                	        });
                                      	$("#tab1content :input[type=radio]").each(function () {  
                                	        $(this).prop("checked",false)
                                	        });*/
                                          BootstrapDialog.alert(result.msg);
                                          var id = result.data
                                      	 initTree(id);
                                      } else {
                                          BootstrapDialog.closeLoading();//加载效果
                                          BootstrapDialog.alert(result.msg);
                                      }
                                  });

                            }
                        }
                    });

           });
            
            
            initTree()
            var zTreeObj = null;

            function initTree(result) {

                var rootUrl = 'goodsclass/goodsclass/getgoodsclassbyid';
                var nodeUrl = 'goodsclass/goodsclass/getgoodsclassbyparentcode';
                var muliSelected = false;
                var rootId = '0';

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
                        data: {simpleData: {enable: true, idKey: "code", pIdKey: "parentClassCode", rootPId: '0'}},
                        callback: {
                        	onClick: function (event, treeId, treeNode) {
//                                if (rootNodeDisabled) {
                                // var nodes = zTreeObj.getNodes();
                                if (treeNode.id == '0') {
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
                            $.fn.zTree.init($("#classTree"), _this.setting, result);
                            _this.zTree = $.fn.zTree.getZTreeObj("classTree");
                            zTreeObj = _this.zTree;
                            var nodes = zTreeObj.getNodes();
                            if (nodes.length > 0) {
                                zTreeObj.expandNode(nodes[0], true, false, false, true);
                            }

                           /* var disabledNode = zTreeObj.getNodeByParam("level", 0);
                            if (disabledNode != null) {
                                zTreeObj.setChkDisabled(disabledNode, true, false, false);
                            }*/
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
                        if (parentNode["children"] === null || parentNode["children"].length === 0) {
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
                    },
                    
                    loadAllNode: function (id) { // 加载根节点,id=000000000
                        var _this = this;
                        var defer1 = common.ajaxJson('goodsclass/goodsclass/getgoodsclassallbyrootid',{id: id});
                        $.when(defer1).done(function (result) {
                            $.fn.zTree.init($("#classTree"), _this.setting, result);
                            _this.zTree = $.fn.zTree.getZTreeObj("classTree");
                            zTreeObj = _this.zTree;
                            var nodes = zTreeObj.getNodes();
                            for(var i=0;i<nodes.length;i++){
                                zTreeObj.expandNode(nodes[i],true,true,true,true);
                            }
                        	
                            
                        });

                    }
                    
                };

                if (muliSelected) {
                    ztree.setting.check = {enable: true, autoCheckTrigger: false, chkboxType: {"Y": "", "N": ""}};
                } else {
                    ztree.setting.check = {enable: true, autoCheckTrigger: true, chkStyle: "radio", radioType: "all"}
                }

                function refreshTree(id) {
                	
                    ztree.loadAllNode(id);
                }
                
                refreshTree(result);
            }

            //节点选中事件处理
            function doCheckHandler(treeNode) {
//                alert(treeNode.id + " " + treeNode.code + " " + treeNode.name);
                
//                BootstrapDialog.showLoading();//加载效果
				var uri = "goodsclass/goodsclass/indexwithcondation";
				 var parameter = {
                         "id":treeNode.id //子节点的parentId是父节点的id
                     };
				var defer1 = common.ajaxJson(uri, parameter);
				$.when(defer1).done(function (result) {
					if (result !== null) {
						$('#tab1content input[name="id"]').attr("value",result.id);
						$('#tab1content input[name="goodsClassCode"]').val(result.code);
						$('#tab1content input[name="goodsClassName"]').val(result.name);
						$('#tab1content input[name="goodsClassDesc"]').val(result.goodsClassDesc);
						$('#tab1content input[name="classSeqNum"]').val(result.classSeqNum);
						if(result.isMinClass === '0'){
							$('#tab1content input[name="isMinClass"][value="0"]').prop("checked",true);
							$('#tab1content input[name="isMinClass"][value="1"]').prop("checked",false);
						}else if(result.isMinClass === '1'){
							$('#tab1content input[name="isMinClass"][value="1"]').prop("checked",true);
							$('#tab1content input[name="isMinClass"][value="0"]').prop("checked",false);
						}
						
						
						$('#tab2Form input[name="id"]').attr("value",result.id);
						$('#tab2Form input[name="goodsClassCode"]').attr("value",result.code);
						
						$('#tab2Form input[name="goodsClassName"]').val("");
						$('#tab2Form input[name="goodsClassDesc"]').val("");
						$('#tab2Form input[name="isMinClass"][value="0"]').prop("checked",false);
						$('#tab2Form input[name="isMinClass"][value="1"]').prop("checked",false);
						
						$('#tab3Form input[name="id"]').attr("value",result.id);
						$('#tab3Form input[name="goodsClassCode"]').attr("value",result.code);
						
					} else {
//						alert("bb");
					}
				});
                
                
                
            }

        });
    });
});

/**
 * Created by yjun on 2017/4/15.
 */

require([ configPath ],
    function(c) {
        // 自定义模块，模块定义对照require-config.js,按需配置
        require([ 'dialog', 'common', 'valid'],
            function(BootstrapDialog, common) {
                $(function() {
                    // 关闭loading
                   // BootstrapDialog.closeLoading();
                    // 初始化下拉控件
                    //$('.selectpicker').selectpicker();

                    // 关闭窗口
                    $('#btnCancle').click(function() {
                        BootstrapDialog.closeAll();
                    });

                    /* 验证 */
                    var chineseAndEnglishRegex = /^[a-zA-Z\u4e00-\u9fa5]{3,16}$/;
                    var englishRegex = /^[a-zA-Z]{3,16}$/;
                    var valid = $.bootValid('#roleForm', {
                        rules : {
                            name : [ 'not_empty', {'regex': chineseAndEnglishRegex}],
                            enname : [ 'not_empty', {'regex': englishRegex}],
                            remark : [ {'max_length':500}]
                        },
                        messages: {
                            name: {regex: '只能输入3至16位汉字和英文字母'},
                            enname: {regex: '只能输入3至16位英文字母'}
                        }
                    });

                    $('#id').val($.paramEditRoleId);

                    $.paramEditRoleId = "";

                    initPage();
                    var tree = null;
                    function initPage() {
                        var uri = "sys/role/getRoleInfo";
                        var param = {"id" : $('#id').val()};
                        var defer1 = common.ajaxJson(uri, param);
                        $.when(defer1).done(
                            function(result) {
                                if (result.status == "success") {
                                    initTree(result);
                                    var roleJson = result.data.role;
                                    if (!common.isEmpty(roleJson)) {
                                        $("#id").val(roleJson.id);
                                        $("#name").val(roleJson.name);
                                        $("#oldName").val(roleJson.name);
                                        $("#oldEnname").val(roleJson.enname);
                                        $("#enname").val(roleJson.enname);
                                        $("#remark").val(roleJson.remark);
                                    }
                                }
                            }
                        );
                    }

                    function initTree(result) {
                        // 用户-菜单
                        var zNodes=[];
                        if ((result.data.menuList != null)) {
                            var menuList =  result.data.menuList;
                            var len = menuList.length;
                            for (var i=0; i<len; i++) {
                                var menuJson = menuList[i];
                                zNodes.push({
                                    id:menuJson.id,
                                    pId:common.isEmpty(menuJson.parentId) ? 0 : menuJson.parentId,
                                    name:common.isEmpty(menuJson.parentId) ? '权限列表' : menuJson.name
                                });
                            }
                        }
                        var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false,showIcon: false},
                            data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
                                tree.checkNode(node, !node.checked, true, true);
                                return false;
                            }}};

                        // 初始化树结构
                        tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
                        // 不选择父节点
                        tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
                        // 默认选择节点
                        var menuIds = result.data.role.menuIds;
                        if (!common.isEmpty(menuIds)) {
                            var ids = menuIds.split(",");
                            for (var i=0; i<ids.length; i++) {
                                var node = tree.getNodeByParam("id", ids[i]);
                                try {
                                    tree.checkNode(node, true, false);
                                } catch(e) {

                                }
                            }
                        }

                        // 默认展开全部节点
                        tree.expandAll(true);
                    }

                    // 保存按钮
                    $('#btnSave').click(function() {
                        if (valid.validate()) {
                            
                            var ids = [], nodes = tree.getCheckedNodes(true);
                            for(var i=0; i<nodes.length; i++) {
                                ids.push(nodes[i].id);
                            }
                            if (ids != null && ids.length > 0) {
                                $("#menuIds").val(ids.join(","));
                            }

                            BootstrapDialog.showLoading();// 加载效果
                            var uri = "sys/role/saveRole";
                            var param = $('#roleForm').serializeData();
                            var defer1 = common.ajaxPost(uri, param);
                            $.when(defer1).done(
                                function(result) {
                                    //BootstrapDialog.closeLoading();// 关闭加载效果
                                    if (result.status == "success") {
                                        $('#btnCancle').click();
                                        $('#btnQuery').click();// 刷新父页面查询列表
                                        BootstrapDialog.alert(result.msg);
                                    } else {
                                       // BootstrapDialog.closeLoading();// 加载效果
                                        BootstrapDialog.alert(result.msg);
                                    }
                                }
                            );
                        }
                    });
                });
            }
        );
    });

/**
 * Created by guoxue on 2017/6/6.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog', 'file', 'select', 'date', 'valid','editor'], function (init, common, BootstrapDialog,files) {
        $(function () {
        	var i = 1;
        	
        	files.init();
            //初始化表单提示语
            $('[data-toggle="popover"]').popover();
            
            common.showUnSavedConfirm($.addGoodsPackageDialog);
            
            //初始化富文本编辑器
            $('#summernote').summernote({
            	height: 200,
            	placeholder: '在此编辑公告内容',
            	toolbar: [
            		['style1', ['style']],
            	    ['style2', ['bold', 'italic', 'underline', 'clear']],
            	    ['fontsize', ['fontsize','color']],
            	    ['para', ['paragraph', 'ul', 'ol', 'table', 'hr']],
            	    ['misc1', ['fullscreen','codeview']],
            	    ['misc2', ['undo','redo']]
            	  ]
            
            });
            
            $('#gclass').SelectTreebox({
        		treeName: "goods",//固定
        		valueId: "goodsClass",//页面保存节点ID用的
        		labelId: "gclass",//页面显示节点名用的
        		title: "商品分类选择",//弹出框标题
        		muliSelected: false,//单选/多选
        		callback: function (value) {
        		//                    console.log("结果：");
        		//                    console.log(value);
        		}
    		});
    		$('#addPackagejgbtn').click(function () {
    		    // 显示机构选择弹出框
    		    $('#gclass').SelectTreebox('show');
    		});
            //初始化CodeList数据
            common.CodeListDataSet.init(['DICT_GOODS_VALIDITY_UNIT','DICT_GOODS_TYPE', 'DICT_GOODS_STATUS']);
            
            $('.selectpicker').selectpicker();
            
          //取消按钮
            $('#btnCancel').click(function () {
            	BootstrapDialog.closeAll();
            });

          //添加产品包商品按钮
           /* $('#addGoodsButton').click(function (e) {
            	var gouzhaoId = "goodsPackageList"+(i+1);
            	var goujianClass1 = "selectpicker2" + (i+1)+ " cc";
            	var goujianClass2 = "selectpicker2" + (i+1);
            	
            	var clonediv = $('#bb').clone();
            	var aa = clonediv.html();
            	var fileid = clonediv.find('.cc').attr('id',gouzhaoId);
//            	var fileid2 = clonediv.find('.cc').attr('name',gouzhaoId);
            	var fileid3 = clonediv.find('.cc').attr('class',goujianClass1);
            	
            	var bb = clonediv.html();
            	
            	$('#aa').after(clonediv.html());
            	
            	$('.'+goujianClass2).selectpicker({
                	liveSearch:true
                });
            	i++;
            });*/
            
            
           
            
            $('#addGoodsButton').click(function (e) {
            	$.addGoodsDialog = BootstrapDialog.show({
                    title: "添加商品",
                    size: BootstrapDialog.SIZE_WIDE, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
                    closable: false,
                    message: function () {
                        var $message = $('<div></div>');
                        return $message.load(webAppPath + '/goods/goods/goodspackageaddgoods');
                    }
                });
            });
            var regex_isNum=/^(([1-9][0-9]*)|[0]|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
            var valid = $.bootValid('#goodsForm', {
                rules: {
                	goodsName:['not_empty','ch_char', {'max_length': 50}],
                	goodsTitle: ['ch_char', {'max_length': 50}],
                	goodsClassCode: [],
                	goodsValidityNum: ['not_empty','digit',{'max_length': 2}],
                	goodsValidityUnit: ['not_empty'],
                	goodsPrice: ['not_empty','decimal',{'max_length': 10},{'regex':regex_isNum}],
                	goodsStock : ['digit',{'max_length': 8}],
            		goodsDetails:['not_empty']
                },
                messages: {
                	goodsPrice: {regex: '请输入10位有效的金额！'}
                }
            });

            //保存按钮post提交
            $('#btnSavePost').click(function () {
            	var arr = new Array();
                if (valid.validate()) {
                	
                	/*for(var j = i;j>=2;j--){
                		arr[j] =$("#goodsPackageList"+j).find("option:selected").val(); 
                		if(arr[j] == null || arr[j] == undefined || arr[j] == ''){
                			BootstrapDialog.alert("包含商品不能为空！");
                			return;
                		}
                	}
                	for(var k = i;k>=2;k--){
                		for(var z = i-1 ;z>=2;z--){
                			if(arr[k] == arr[z]){
                    			BootstrapDialog.alert("包含商品不能相同！");
                    			return;
                    		}
                		}
            			
            		}*/
                	
                	$('input[name="goodsPackageList"]').each(function(){   
                	    result=result+ $(this).val();   
                	    alert("aa");
                	}); 
                	
                    BootstrapDialog.confirm({
                        message: '确认保存商品信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goods/goods/savepackage";
                                var jsonArray = [];
            					for (var j = i;j>=2;j--) {
            						jsonArray.push({
            									"id" : $("#packageId"+j).val() ,
            									"packGoodsCode" : $("#goodsPackageList"+j).find("option:selected").val()
            								});
            					}

            					var jsonObj = {
            						"goodsPackageList" : jsonArray ,
            						"goods" : $('#goodsForm').serializeObject(),
            						"goodsDetails" : $('#summernote').summernote('code'),
            						"countNumber" : i+""
            					};
                                /*if(param.length == 0){
                                	BootstrapDialog.alert('啥也没填');
                                }*/
                                var defer1 = common.ajaxJson(uri, jsonObj);
                                $.when(defer1).done(function (result) {
                                    if (result.status === common.CommonConstant.SUCCESS) {
                                        $('#btnReset').click();
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
            
            
            
            //保存按钮submit提交
            $('#btnSaveSubmit').click(function () {
            	
            	if($('#summernote').summernote('isEmpty')){
            		BootstrapDialog.alert("商品详细不能为空！");
        			return;
            	}
            	
            	var goodsDetails = $('#summernote').summernote('code');
            	if(goodsDetails.length > 65535){
            		BootstrapDialog.alert("商品详细内容超出最大范围！");
        			return;
            	}
            	$("#goodsDetails").val(goodsDetails);
            	var arr = new Array();
            	
            	//清空分类选项
            	var gClass = $("#gclass").val();
            	if(gClass === ""){
            		$("#goodsClass").removeAttr("value");
            	}
            	if(!document.getElementById("goodsPackageList")){
            		BootstrapDialog.alert("包含商品不能为空！");
            		return;
            	}
                if (valid.validate()) {
                	/*if(!$("#goodsPackageList2").length>0){
                		BootstrapDialog.alert("包含商品不能为空！");
            			return;
                	}*/
                	
                	/*for(var j = i;j>=2;j--){
                		arr[j] =$("#goodsPackageList"+j).find("option:selected").val(); 
                		if(arr[j] === null || arr[j] === undefined || arr[j] === ''){
                			BootstrapDialog.alert("包含商品不能为空！");
                			return;
                		}
                	}
                	
                	for(var k = i;k>=2;k--){
                		for(var z = k-1 ;z>=2;z--){
                			if(arr[k] === arr[z]){
                    			BootstrapDialog.alert("包含商品不能相同！");
                    			return;
                    		}
                		}
            			
            		}*/
                	
                	
                	/*$('input[name="packGoodsCode"]').each(function(){   
                		var result;
                	    result=result+ $(this).val();   
                	    alert($(this).val());
                	    alert("aa");
                	});*/
                	
                    BootstrapDialog.confirm({
                        message: '确认保存商品信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "goods/goods/savepackage";
                                var defer1 = common.ajaxSubmit(uri, 'goodsForm');
                                $.when(defer1).done(function (result) {
                                      if (result.status === common.CommonConstant.SUCCESS) {
                                     	 common.unSavedConfirmCloseable = true;//取消未保存提示。
                                          $('#btnCancel').click();
                                          $('#btnQuery').click();//刷新父页面查询列表
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
            
            $('.headImg').click(function (e) {
              	 $("#file1").click();
             	  });
              
              $("#file1").on("change",function(){
              	var objUrl = getObjectURL(this.files[0]) ;
              	if (objUrl) {
              		$("#headImg").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
              		}

              });
              $('#goodsImg').click(function (e) {
                	 $("#file2").click();
               	  });
                
                $("#file2").on("change",function(){
                	var objUrl = getObjectURL(this.files[0]) ;
                	if (objUrl) {
                		$("#goodsImg").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                		}

                });
                
                
                $("body").on("click", "#goodsImg2", function(){
               	 $("#file22").click();
             	  }); 
                
                
                $("body").on("change","#file22",function(){
                	var objUrl = getObjectURL(this.files[0]) ;
                	if (objUrl) {
                		$("#goodsImg2").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                		}

                });
                $("body").on("click", "#goodsImg3", function(){
                  	 $("#file23").click();
                	  }); 
                   
                   
                   $("body").on("change","#file23",function(){
                   	var objUrl = getObjectURL(this.files[0]) ;
                   	if (objUrl) {
                   		$("#goodsImg3").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                   		}

                   });
                   $("body").on("click", "#goodsImg4", function(){
                    	 $("#file24").click();
                  	  }); 
                     
                     
                     $("body").on("change","#file24",function(){
                     	var objUrl = getObjectURL(this.files[0]) ;
                     	if (objUrl) {
                     		$("#goodsImg4").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                     		}

                     });
                     $("body").on("click", "#goodsImg5", function(){
                    	 $("#file25").click();
                  	  }); 
                     
                     
                     $("body").on("change","#file25",function(){
                     	var objUrl = getObjectURL(this.files[0]) ;
                     	if (objUrl) {
                     		$("#goodsImg5").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                     		}

                     });
                     $("body").on("click", "#goodsImg6", function(){
                    	 $("#file26").click();
                  	  }); 
                     
                     
                     $("body").on("change","#file26",function(){
                     	var objUrl = getObjectURL(this.files[0]) ;
                     	if (objUrl) {
                     		$("#goodsImg6").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                     		}

                     });
                     $("body").on("click", "#goodsImg7", function(){
                    	 $("#file27").click();
                  	  }); 
                     
                     
                     $("body").on("change","#file27",function(){
                     	var objUrl = getObjectURL(this.files[0]) ;
                     	if (objUrl) {
                     		$("#goodsImg7").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                     		}

                     });
            
            function getObjectURL(file) {
            	var url = null ;
            	if (window.createObjectURL!=undefined) { // basic
            	url = window.createObjectURL(file) ;
            	} else if (window.URL!=undefined) { // mozilla(firefox)
            	url = window.URL.createObjectURL(file) ;
            	} else if (window.webkitURL!=undefined) { // webkit or chrome
            	url = window.webkitURL.createObjectURL(file) ;
            	}
            	return url ;
            	}
            
            
        });
    });
});

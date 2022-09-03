/**
 * Created by liujia on 2017/4/4.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog', /*'select', */'date', 'valid'/*, 'files', 'spinner'*/], function (init, common, BootstrapDialog) {
        $(function () {
            //初始化表单提示语
            $('[data-toggle="popover"]').popover();
            // 初始化日期控件
            $('.form_datetime').datetimepicker({
                format: "yyyy-mm-dd",
                pickerPosition: "bottom-left",
                //weekStart: 1,
                //todayBtn: 1,
                autoclose: 1,
                startView: 2,
                todayHighlight: 1,
                //forceParse: 1,
                //showMeridian: 1,
                minView: 2
                //minuteStep: 5
            });
            //初始化下拉控件
            //$('.selectpicker').selectpicker();
            
            // 初始化其他职称隐藏	By:liuhuan;2017年4月27日
            if($('#positionTitle').val() == common.CommonConstant["DictProfRank"]["other"]){
        		$('#otherProf').removeAttr("disabled");
        	}else{
        		$('#otherProf').attr("disabled", "true");
        	}
            // 初始化其他部门/科室隐藏	By:liuhuan;2017年4月27日
            if($('#department').val() == common.CommonConstant["DictDeptStyle"]["other"]){
        		$('#otherDept').removeAttr("disabled");
        	}else{
        		$('#otherDept').attr("disabled", "true");
        	}
            /** by liuhuan at 20170512 修改出生日期不可选择今天和未来日期 begin */     
            var nowTime = common.getCurrentDate();
			var yestoday = Date.parse(nowTime) - 24*60*60*1000;
			$('.birthdayDatetime').datetimepicker('setEndDate',new Date(yestoday));//设置开始日期
			/** by liuhuan at 20170512 修改出生日期不可选择今天和未来日期 end */  
          //重置按钮 
            $('#btnReset').click(function () {
            	$('#userForm')[0].reset();
            	/** by liuhuan at 20170512 修改重置列表 begin */    
            	$('.selectpicker').selectpicker('refresh');
            	/** by liuhuan at 20170512 修改重置列表 end */    
            });
            
            //表单校验规则
//            messages: {
//                not_empty: '该项为必填项！'
//                , min_length: '请输入至少 :value 位有效值！'
//                , max_length: '请输入少于 :value 位有效值！'
//                , regex: ''
//                , email: '请输入一个有效的邮箱地址！'
//                , url: '请输入一个有效的网址！'
//                , exact_length: '请输入 :value 位有效值！'
//                , equals: ''
//                , ip: '请输入一个有效的IP地址！'
//                , credit_card: '请输入有效的信用卡号码！'
//                , alpha: '请输入字符！'
//                , alpha_numeric: '存在非法字符！'
//                , alpha_number: '请输入合法字符！'
//                , alpha_dash: '存在非法字符！'
//                , digit: '请输入数字！'
//                , numeric: '请输入有效数字！'
//                , decimal: '请输入金额格式数字！'
//                , matches: 'Must match the previous value.'
//                , not_matches: 'Must not match the previous value.'
//                , phone_num: '请输入11位有效的手机号码！'
//                , tel: '请输入有效的电话号码！'
//                , id_num: '请输入有效的身份证号码！'
//                , ch_char: '只能输入汉字！'
//                , date: '日期格式错误，正确格式：2015-12-02'
//                , post: '请输入正确的邮政编码!'
//                , bank_count_no: '请输入正确的银行账户!'
//                , alpha_organ: '请输入合理字符，可以是数字，字母和横杠，最大长度为18位字符！'
//            }
            
            var valid = $.bootValid('#userForm', {
                rules: {
                	  name: ['not_empty',{'max_length':200}],
                      department: ['not_empty'],
                      positionTitle: ['not_empty'],
                      address: ['not_empty',{'max_length':2000}],
                      mobilePhone: ['phone_num'],
                      educateGrade: ['not_empty'],
                      email: ['not_empty','email',{'max_length':32}],
                      telephone: ['tel',{'max_length':50}],
                      qq: ['numeric',{'max_length':50}],
                      wechat:[{'max_length':50}],
                      deptName:[{'max_length':200}],	// By:liuhuan at 20170427
                      otherProf:[{'max_length':50}],	// By:liuhuan at 20170427
                      otherDept:[{'max_length':50}],	// By:liuhuan at 20170427
                      unitName:['not_empty',{'max_length':50}],	// By:liuhuan at 20170513
                      birthday:['not_empty','date']		// By:liuhuan at 20170505
//                    hobby1: ['not_empty'],
//                    hobby2: ['not_empty']
//                    hobby : ['not_empty'],
//                    fqkhhqc : ['not_empty'],
//                    file : ['not_empty'],
//                    xzrq1 : ['not_empty'],
//                    xzrq2 : ['not_empty'],
//                    spinner : ['not_empty'],
//                    remark: ['not_empty']
                }
                //paddingLeft: 10
                //messages: {...}
            });
            
            // 其他职称显示	By:liuhuan;2017年4月27日
            $('#positionTitle').change(function(){
            	if($('#positionTitle').val() == common.CommonConstant["DictProfRank"]["other"]){
            		$('#otherProf').removeAttr("disabled");
            	}else{
            		$('#otherProf').val("");
            		$('#otherProf').attr("disabled", "true");
            	}
            }); 
            
            // 其他部门/科室显示	By:liuhuan;2017年4月27日
            $('#department').change(function(){
            	if($('#department').val() == common.CommonConstant["DictDeptStyle"]["other"]){
            		$('#otherDept').removeAttr("disabled");
            	}else{
            		$('#otherDept').val("");
            		$('#otherDept').attr("disabled", "true");
            	}
            });

          //保存按钮
            $('#btnSave').click(function () {
                if (valid.validate()) {
                	// 验证部门/科室	By:liuhuan;2017年4月27日
                	if($('#department').val() == common.CommonConstant["DictDeptStyle"]["other"]){
                		if($('#otherDept').val()==""){
                    		valid.show({otherDept: "该项为必填项!"});
                    		return;
                    	}
                	}
                	// 验证职称	By:liuhuan;2017年4月27日
                	if($('#positionTitle').val() == common.CommonConstant["DictProfRank"]["other"]){
                		if($('#otherProf').val()==""){
                    		valid.show({otherProf: "该项为必填项!"});
                    		return;
                    	}
                	}
                	// 验证联系方式	By:liuhuan;2017年4月27日
                	if($('#mobilePhone').val()==""&&$('#telephone').val()==""){
                		valid.show({mobilePhone: "手机和座机请至少填写一个！"});
                		valid.show({telephone: "手机和座机请至少填写一个！"});
                		return;
                	}
            		BootstrapDialog.confirm({
                        message: '确认保存用户信息？',
                        closable: true,
                        draggable: true,
                        btnOKClass: 'btn-primary',
                        callback: function (result) {
                            if (result) {

                                BootstrapDialog.showLoading();//加载效果
                                var uri = "personal/PersonAffair/save";
                                var param = $('#userForm').serializeData();
                                var defer1 = common.ajaxPost(uri, param);
                                
                                $.when(defer1).done(function (result) {
                                    //BootstrapDialog.closeLoading();//关闭加载效果
                                    if (result.status === common.CommonConstant.SUCCESS) {
                                        $('#btnCancle').click();
                                        BootstrapDialog.alert(result.msg,function(result){
                                        	// By liuhuan at 20170518 修改判断
//                                            if ((!common.isEmpty($("#isServiceAdmin").val())) && $("#isServiceAdmin").val() == '0') {
                                        	    window.location.href = webAppPath + '/personal/PersonAffair/index';	// By:liuhuan;2017年4月27日
//                                            } else {
//                                            	window.location.href = webAppPath + '/exam/examTemplate/index';
//                                            }
                                        });
                                    } else {
                                        //BootstrapDialog.closeLoading();//加载效果
                                        BootstrapDialog.alert(result.msg);
                                    }
                                });
                                
//                                var $message = $('<div></div>');
//                                return $message.load(webAppPath + '/personal/PersonAffair/index');
                               

                            }
                        }
                    });
                }
            });

            //数字选择器
            /*            $(".spinner").spinner({
             max: 100,
             min: 0,
             numberFormat: "n",
             stop: function (event, ui) {
             var $this = $(this);
             var purchasenum = rmoney($this.val());
             if (!$.isNumeric(purchasenum)) {
             $this.val("0");
             } else if (purchasenum > 1000) {//录入大于1000,则返回1000
             $this.val("1000");
             } else {
             $this.val(purchasenum);
             }
             }
             });*/

        });
    });
});

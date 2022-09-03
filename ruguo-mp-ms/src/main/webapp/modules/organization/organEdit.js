/**
 * Created by hl on 2017/4/4.
 */

require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['dialog', 'common', 'select', 'valid','area'/*, 'files', 'spinner'*/], function (BootstrapDialog, common,select,data,valid) {
        $(function () {
        	var valid;
        	//关闭loading
            //BootstrapDialog.closeLoading();
            
            //初始化CodeList数据
        	common.CodeListDataSet.init(['DICT_ORG_TYPE','DICT_PLATFORM_TYPE']);

            //初始化行政区划
        	$('.selectArea2').SelectArea({
            	auth:true,
				select:true
            });

        	//判断服务机构或者管理机构
        	/*var isEnable = $("#isEnable_edit").val();
        	if(common.CommonConstant["DictStartStatus"]["start"]==isEnable){
        		$("#editLevel").attr("disabled",true);
        		$("#editServiceOrgType").attr("disabled",true);
        		$("#editprovinceCode").attr("disabled",true);
        		$("#editcityCode").attr("disabled",true);
        		$("#editcountyCode").attr("disabled",true);
        		$("#edittownCode").attr("disabled",true);
        		$("#editHospitalLevel").attr("disabled",true);
        		$("#editHospitalType").attr("disabled",true);
        	}*/
        	//初始化表单提示语
        	$('[data-toggle="popover"]').popover();
//            $('.filestyle').filestyle();
            // 初始化日期控件
            /*$('.form_datetime').datetimepicker({
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
            });*/
            //初始化下拉控件
            $('.selectpicker').selectpicker();

            //重置按钮
            $('#btnReset').click(function () {
                $('#organForm')[0].reset();
                valid.removeAll();//清除所有校验提示
//                $('#userForm input').val('');//重置text
//                $('#userForm input:radio').attr('checked', false);//重置radio
//                $('#userForm input:checkbox').attr('checked', false);//重置checkbox
                $(".selectid").find("select").each(function () {
        			$(this).selectpicker('val','');
        		})
            });
            //关闭窗口
            $('#btnCancle').click(function () {
                BootstrapDialog.closeAll();
            });
            valid = $.bootValid('#organForm', {
                rules: {
                	code: ['not_empty',{'max_length':24}],
                	name: ['not_empty',{'max_length':50}],
                	orgType: ['not_empty'],
                	orgPlatformType: ['not_empty'],
                    provinceCode: ['not_empty'],
                    cityCode: ['not_empty'],
                    countyCode: ['not_empty'],
                    address: ['not_empty',{'max_length':255}],
                    contactPerson: ['not_empty'],
                    contactPhone: ['not_empty','tel'],
                    orgPostCode: ['post'],
                    orgFax: ['tel'],
                    orgUrl: ['url'],
                    remark:  [{'max_length':500}]
                },
                messages: {
                	contactPhone: {tel: '请输入有效的电话号码！'},
                	orgFax: {tel: '请输入有效的传真号码！'}
	            }
            });
            
            //下拉修改zonecode
            $(".codechange").on('changed.bs.select', function () {
	           	 //所属区划逻辑
	           	var provinceCode = $("#editprovinceCode").val();
	           	var cityCode = $("#editcityCode").val();
	           	var countyCode = $("#editcountyCode").val();
	           	var townCode = $("#edittownCode").val();
		        //对应区划赋值
	           	/*if(level=="5"){
	           		$("#zoneCodehid").val(townCode);
	           	}else{
	           		$("#zoneCodehid").val(countyCode);
	           	}*/
	            //所属区划逻辑
            	var provinceCode = $("#editprovinceCode").val();
            	var cityCode = $("#editcityCode").val();
            	var countyCode = $("#editcountyCode").val();
            	var townCode = $("#edittownCode").val();
            	/*if(level=="1"){
	        		 $("#editownZoneCode").val("000000000");
	        	 }else if(level=="2"){
	        		 $("#editownZoneCode").val(provinceCode);
	        	 }else if(level=="3"){
	        		 $("#editownZoneCode").val(cityCode);
	        	 }else if(level=="4"){
	        		 $("#editownZoneCode").val(countyCode);
	        	 }else if(level=="5"){
	        		 $("#editownZoneCode").val(townCode);
	        	 }else{
	        		 $("#editownZoneCode").val("");
	        	 }*/
            });
        	//页面初始化校验
            
            /*//等级
            $("#editLevel").on('changed.bs.select', function () {
            	var level = $(this).children('option:selected').val();
            	 //所属区划逻辑
            	var provinceCode = $("#editprovinceCode").val();
            	var cityCode = $("#editcityCode").val();
            	var countyCode = $("#editcountyCode").val();
            	var townCode = $("#edittownCode").val();
            	 //对应区划赋值
            	 if(level=="5"){
            		 $("#zoneCodehid").val(townCode);
            	 }else{
            		 $("#zoneCodehid").val(countyCode);
            	 }
            	 var serviceOrgType = $('#editServiceOrgType').val();
	        	 if(level=="1"){
	        		 if(serviceOrgType=="2"){
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
			        			 name: ['not_empty',{'max_length':24}],
		    					 level: ['not_empty'],
		    					 serviceOrgType: ['not_empty'],
		    					 provinceCode: ['not_empty'],
		    					 cityCode: ['not_empty'],
		    					 countyCode: ['not_empty'],
		    					 hospitalType:['not_empty'],
		        				 hospitalLevel:['not_empty']
	        				 }
	        			 });
	        		 }else{
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
	        					 level: ['not_empty'],
	        					 serviceOrgType: ['not_empty'],
	        					 provinceCode: ['not_empty'],
	        					 cityCode: ['not_empty'],
	        					 countyCode: ['not_empty']
	        				 }
	        			 });
	        		 }
	        		 $("#editownZoneCode").val("000000000");
	        	 }else if(level=="2"){
	        		 if(serviceOrgType=="2"){
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
		    					 level: ['not_empty'],
		    					 serviceOrgType: ['not_empty'],
		    					 provinceCode: ['not_empty'],
		    					 cityCode: ['not_empty'],
		    					 countyCode: ['not_empty'],
		    					 hospitalType:['not_empty'],
		        				 hospitalLevel:['not_empty']
	        				 }
	        			 });
	        		 }else{
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
	        					 level: ['not_empty'],
	        					 serviceOrgType: ['not_empty'],
	        					 provinceCode: ['not_empty'],
	        					 cityCode: ['not_empty'],
	        					 countyCode: ['not_empty']
	        				 }
	        			 });
	        		 }
	        		 $("#editownZoneCode").val(provinceCode);
	        	 }else if(level=="3"){
	        		 if(serviceOrgType=="2"){
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
		    					 level: ['not_empty'],
		    					 serviceOrgType: ['not_empty'],
		    					 provinceCode: ['not_empty'],
		    					 cityCode: ['not_empty'],
		    					 countyCode: ['not_empty'],
		    					 hospitalType:['not_empty'],
		        				 hospitalLevel:['not_empty']
	        				 }
	        			 });
	        		 }else{
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
	        					 level: ['not_empty'],
	        					 serviceOrgType: ['not_empty'],
	        					 provinceCode: ['not_empty'],
	        					 cityCode: ['not_empty'],
	        					 countyCode: ['not_empty']
	        				 }
	        			 });
	        		 }
	        		 $("#editownZoneCode").val(cityCode);
	        	 }else if(level=="4"){
	        		 if(serviceOrgType=="2"){
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
		    					 level: ['not_empty'],
		    					 serviceOrgType: ['not_empty'],
		    					 provinceCode: ['not_empty'],
		    					 cityCode: ['not_empty'],
		    					 countyCode: ['not_empty'],
		    					 hospitalType:['not_empty'],
		        				 hospitalLevel:['not_empty']
	        				 }
	        			 });
	        		 }else{
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
	        					 level: ['not_empty'],
	        					 serviceOrgType: ['not_empty'],
	        					 provinceCode: ['not_empty'],
	        					 cityCode: ['not_empty'],
	        					 countyCode: ['not_empty']
	        				 }
	        			 });
	        		 }
	        		 $("#editownZoneCode").val(countyCode);
	        	 }else if(level=="5"){
	        		 if(serviceOrgType=="2"){
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
		    					 level: ['not_empty'],
		    					 serviceOrgType: ['not_empty'],
		    					 provinceCode: ['not_empty'],
		    					 cityCode: ['not_empty'],
		    					 countyCode: ['not_empty'],
		    					 hospitalType:['not_empty'],
		        				 hospitalLevel:['not_empty'],
		                         townCode: ['not_empty']
	        				 }
	        			 });
	        		 }else{
	        			 valid = $.bootValid('#organForm', {
	        				 rules: {
	        					 name: ['not_empty',{'max_length':24}],
	        					 level: ['not_empty'],
	        					 serviceOrgType: ['not_empty'],
	        					 provinceCode: ['not_empty'],
	        					 cityCode: ['not_empty'],
	        					 countyCode: ['not_empty'],
	        					 townCode: ['not_empty']
	        				 }
	        			 });
	        		 }
	        		 $("#editownZoneCode").val(townCode);
	        	 }else{
	        		 $("#editownZoneCode").val("");
	        		 valid = $.bootValid('#organForm', {
	                     rules: {
	                     	name: ['not_empty',{'max_length':24}],
	                         level: ['not_empty'],
	                         serviceOrgType: ['not_empty'],
	                         provinceCode: ['not_empty'],
	                         cityCode: ['not_empty'],
	                         countyCode: ['not_empty']
	                     }
	                 });
	        	 }
            });*/
          
            /*$("#editServiceOrgType").on('changed.bs.select', function () {
            	 var serviceOrgType = $(this).children('option:selected').val();
	             if("2"==serviceOrgType){
            		$('#editHospitalType').selectpicker('enabled');
            		$('#editHospitalLevel').selectpicker('enabled');
            		var level = $('#editLevel').val();
            		if(level=="5"){
            			valid = $.bootValid('#organForm', {
            				rules: {
            					name: ['not_empty',{'max_length':24}],
            					level: ['not_empty'],
            					serviceOrgType: ['not_empty'],
            					provinceCode: ['not_empty'],
            					cityCode: ['not_empty'],
            					countyCode: ['not_empty'],
            					townCode: ['not_empty'],
            					hospitalType:['not_empty'],
            					hospitalLevel:['not_empty']
            				}
            			});
            		}else{
            			valid = $.bootValid('#organForm', {
            				rules: {
            					name: ['not_empty',{'max_length':24}],
            					level: ['not_empty'],
            					serviceOrgType: ['not_empty'],
            					provinceCode: ['not_empty'],
            					cityCode: ['not_empty'],
            					countyCode: ['not_empty'],
            					hospitalType:['not_empty'],
            					hospitalLevel:['not_empty']
            				}
            			});
            		}
		       	 }else{
		       		var level = $('#editLevel').val();
		       		if(level=="5"){
		       			valid = $.bootValid('#organForm', {
            				rules: {
            					name: ['not_empty',{'max_length':24}],
            					level: ['not_empty'],
            					serviceOrgType: ['not_empty'],
            					provinceCode: ['not_empty'],
            					cityCode: ['not_empty'],
            					countyCode: ['not_empty'],
            					townCode: ['not_empty']
            				}
            			});
		       		}else{
		       			valid = $.bootValid('#organForm', {
            				rules: {
            					name: ['not_empty',{'max_length':24}],
            					level: ['not_empty'],
            					serviceOrgType: ['not_empty'],
            					provinceCode: ['not_empty'],
            					cityCode: ['not_empty'],
            					countyCode: ['not_empty']
            				}
            			});
		       		}
		       		$('#editHospitalType').selectpicker('val','');
		       		$('#editHospitalLevel').selectpicker('val','');
		       		$('#editHospitalType').selectpicker('disabled');
		       		$('#editHospitalLevel').selectpicker('disabled');
		       	 }
            });*/
            
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
            

          //保存按钮
            $('#btnSave').click(function () {
                if (valid.validate()) {
//                    BootstrapDialog.confirm({
//                        message: '确认保存机构信息？',
//                        closable: true,
//                        draggable: true,
//                        btnOKClass: 'btn-primary',
//                        callback: function (result) {
//                            if (result) {
                                BootstrapDialog.showLoading();//加载效果
                                var uri = "organization/organ/save";
                                var param = $('#organForm').serializeObject();
                               
//                                param.zoneCode = $("#zoneCodehid").val();
                                param.province = $('#editprovinceCode').children('option:selected').text();
                                param.city = $('#editcityCode').children('option:selected').text();
                                param.county = $('#editcountyCode').children('option:selected').text();
                                var edittownCode = $('#edittownCode').children('option:selected').val();
                                if(edittownCode==""){
                                	param.town = "";
                                }else{
                                	param.town = $('#edittownCode').children('option:selected').text();
                                }
                                var defer1 = common.ajaxPost(uri, param);
                                $.when(defer1).done(function (result) {
                                    //BootstrapDialog.closeLoading();//关闭加载效果
                                    if (result.status === common.CommonConstant.SUCCESS) {
                                        $('#btnCancle').click();
										/** mod by jiyingming at 2017-04-25 添加机构后,服务机构区划自动写入查询文本框里 begin */
										var param = $('#searchFormOrgan').serializeObject();
										//if(!common.isAllEmptyJson(param)) {
											$('#btnSubmit').click();//刷新父页面查询列表
										//}
										/** mod by jiyingming at 2017-04-25 添加机构后,服务机构区划自动写入查询文本框里 end */
                                        BootstrapDialog.alert(result.msg);
                                    } else {
//                                       // BootstrapDialog.closeLoading();//加载效果
                                        BootstrapDialog.alert(result.msg);
                                    }
                                });

//                            }
//                        }
//                    });

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

/**
 * Created by hl on 2017/4/4.
 */
require([configPath], function (c) {
    //自定义模块，模块定义对照require-config.js,按需配置
    require(['init','common', 'dialog','typeahead'/*, 'files', 'spinner'*/], function (init, common, BootstrapDialog) {
        $(function () {
        	
        	common.CodeListDataSet.init(['DICT_EDUCATE_GRADE']);
        	var data = common.CodeListDataSet.getCodelist(['DICT_EDUCATE_GRADE']);
//        	console.log(JSON.stringify(data))
        	var newData = [];
        	$.each(data, function (i, obj) {
        		var code = {};
        		code.name = obj.label;
        		code.id = obj.value;
        		newData.push(code);
            });
        	$('#source').text(JSON.stringify(newData));
            //初始化typeahead
        	var $input = $(".typeahead").typeahead({
//			  source:[{id: "1", name: "王五1",age:'10'},{id: "2", name: "王五2",age:'20'}],
			  source:newData,
			  items:'all',
//			  showHintOnFocus:'all',
			  autoSelect: true,//自动选择
			  fitToElement: true,
			  afterSelect:function(item){//选中后
				  console.log('选择了：',item)
			  }
//        	,
//			  displayText: function(item){//显示内容
//				  return "name:" + item.name+" age:"+item.id;
//			  }
			  
			});	
            //重置按钮
            $('#btnReset').click(function () {
            	$input.val('');
            	$input.typeahead("reset")
            });

            //获取对象
            $('#btnGetContents').click(function () {
            	 var current = $input.typeahead("getActive");
            	 console.log('getActive：',current)
				 console.log('$input.val()：',$input.val())
            });
        });
    });
});

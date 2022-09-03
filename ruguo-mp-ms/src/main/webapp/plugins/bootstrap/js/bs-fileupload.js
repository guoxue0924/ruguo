/**
 * 附件上传插件
 * Created by hl on 2017/4/22.
 */
define(['jquery', 'dialog', 'jqform'], function ($, BootstrapDialog) {

    var browserCfg = {};
    var ua = window.navigator.userAgent;
    if (ua.indexOf("MSIE 8.0") > 0) {
        browserCfg.ie = true;
    } else if (ua.indexOf("Firefox") >= 1) {
        browserCfg.firefox = true;
    } else if (ua.indexOf("Chrome") >= 1) {
        browserCfg.chrome = true;
    }

    var FileUtils = function () {
    };

    /**
     * 清除事件处理
     */
    FileUtils.removeHandler = function (e) {
        e.preventDefault();
        var fileid = $(this).data('filegroup');
        var filecount = $('#' + fileid).data('filecount');
        filecount -= 1;
        $('#' + fileid).data('filecount', filecount);
        $(this).closest('.form-group').remove();
    };

    /**
     * 选择附件事件处理
     */
    FileUtils.chageHandler = function () {

        if (!FileUtils.checkFileSize(this)) {
            FileUtils.resetFile(this);
            return;
        }

        if (!FileUtils.checkFileType(this)) {
            FileUtils.resetFile(this);
            return;
        }

        FileUtils.setFileName(this);
    };

    /**
     * 设置附件名称
     */
    FileUtils.setFileName = function (self) {
        var _self = $(self);
        var fileName = '';
        if (browserCfg.ie) {
            if (self.value === '') {
                BootstrapDialog.alert('上传文件不能为空！');
                return false;
            }
            fileName = self.value;
            fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);

        } else {
            var file = typeof _self[0].files !== 'undefined' ? _self[0].files[0] : '';
            if (file === '') {
                BootstrapDialog.alert('上传文件不能为空！');
                return false;
            }
            fileName = file.name;
        }
        _self.closest('.form-group').find('.file-input-label').val(fileName.replace(/\, $/g, ''));
    };

    /**
     * 重置附件名称
     */
    FileUtils.resetFile = function (self) {
        var _self = $(self);
        _self.val('');
        _self.closest('.form-group').find('.file-input-label').val('');
    };

    /**
     * 校验附件大小
     * 默认大小：2M
     * data-filesize="2097152"
     */
    FileUtils.checkFileSize = function (self) {
        var _self = $(self);
        var maxsize = _self.data('filesize') ? _self.data('filesize') : 1024 * 1024 * 2;//2M
        if (browserCfg.ie) {
//        	debugger;
//        	var fileobject = new ActiveXObject ("Scripting.FileSystemObject");//获取上传文件的对象
//        	var file = fileobject.GetFile (self.value);//获取上传的文件
//        	var filesize12 = file.Size;//文件大小

            if (self.value === '') {
                BootstrapDialog.alert('上传文件不能为空！');
                return false;
            }

//        	var img = new Image();
//            img.dynsrc = self.value;
//            if (img.fileSize > 0) {
//                if (img.fileSize >maxsize) {
//                	BootstrapDialog.alert('上传文件大小不能超过' + maxsize + '');
//                    return false;
//                }
//            }

//            if (self.size > maxsize) {
//                BootstrapDialog.alert('上传文件大小不能超过' + maxsize + '');
//                return false;
//            }
            return true;
        } else {
            var file = typeof _self[0].files !== 'undefined' ? _self[0].files[0] : '';

            if (file === '') {
                BootstrapDialog.alert('上传文件不能为空！');
                return false;
            }
            if (file.size > maxsize) {
                var maxsizeparse = parseInt(maxsize) / 1024;
                BootstrapDialog.alert('上传文件大小不能超过' + maxsizeparse.toFixed(0) + 'KB,请重新选择!');
                return false;
            }
            return true;
        }


    };

    /**
     * 校验附件类型
     * 默认：无
     * data-filetype="txt|doc"
     */
    FileUtils.checkFileType = function (self) {
        var _self = $(self);
        //获取限定文件格式参数
        var filetype = _self.data('filetype') ? _self.data('filetype').split('|') : '';
        if (filetype === '') {
            return true;
        }
        var filename = '';
        if (browserCfg.ie) {
            if (self.value === '') {
                BootstrapDialog.alert('上传文件不能为空！');
                return false;
            }

            filename = self.value;

        } else {

            var file = typeof _self[0].files !== 'undefined' ? _self[0].files[0] : '';
            if (file === '') {
                BootstrapDialog.alert('上传文件不能为空！');
                return false;
            }

            filename = file.name;

        }
        var extStart = filename.lastIndexOf(".") + 1;
        var ext = filename.substring(extStart, filename.length).toLowerCase();

        if ($.inArray(ext, filetype) === -1) {
            BootstrapDialog.alert("上传文件限于 " + filetype.join(",") + " 格式,请重新选择!");
            return false;
        }
        return true;
    };

    /**
     * 下载附件
     * fileId,fileName
     */
    FileUtils.downFile = function (fileId, fileName) {
        if ($.isEmptyObject(fileId)) {
            throw new Error("附件id不能为空");
        }
        var strURL = webAppPath + "/common/file/down";
        if (fileName == null || typeof fileName === "undefined") fileName = "";
        // 文件下载
        jQuery('<form action="' + strURL + '" method="' + ('post') + '">' +  // action请求路径及推送方法
            '<input type="hidden" name="fileId" value="' + fileId + '"/>' + // 文件ID
            '<input type="hidden" name="fileName" value="' + fileName + '"/>' + // 文件名称
            '</form>')
            .appendTo('body').submit().remove();

    };
    /**
     * 预览图片
     * fileId
     */
    FileUtils.showImg = function (fileId) {
        var strURL = webAppPath + "/common/file/show?fileId=" + fileId;

        var $img = $('<img style="max-width:578px"/>').attr("src", strURL);
        var $message = $('<div></div>').append($img);

        var pictureDialog = BootstrapDialog.show({
            title: "图片预览",
            size: BootstrapDialog.SIZE_NORMAL, //SIZE_NORMAL,SIZE_SMALL,SIZE_WIDE,SIZE_LARGE
            closable: false,
//            marginTop: 10,
            message: $message,
            buttons: [{
                label: '关闭',
                cssClass: 'btn-default',
                autospin: true,
                action: function (dialogRef) {
                    dialogRef.close();
                }
            }]
        });
        $('#dialog-load').remove();
        return;

    };

    FileUtils.init = function () {

        /**
         * 初始化绑定change事件
         */
        $('.file-input').each(function () {
            var _self = $(this);
            _self.off('change');
            _self.on('change', FileUtils.chageHandler);
        });

        /**
         * 初始化添加附件事件
         * 默认：上传附件最大数：5
         * data-maxfiles="3"
         */
        $('.file-append').off('click');
        $('.file-append').click(function (e) {
            e.preventDefault();
            var fileInput = $(this).closest('.form-group').find('.file-input');
            var dataMaxfiles = fileInput.data('maxfiles');//获取上传附件最大数
            var maxfiles = dataMaxfiles ? parseInt(dataMaxfiles) : FileUtils.MaxFileCount;
            var dataFileCount = fileInput.data('filecount');//获取已打开上传附件控件数
            var fileCount = dataFileCount ? parseInt(dataFileCount) : 1;

            if (fileCount >= maxfiles) {
                BootstrapDialog.alert('最多允许上传' + maxfiles + '个附件。');
                return;
            }
            fileCount += 1;
            FileUtils.AddCount += 1;
            fileInput.data('filecount', fileCount);
            var clonediv = $(this).closest('.form-group').clone();
            var fileid = clonediv.find('.file-input').attr('id');
            clonediv.find('.file-input').attr('id', fileid + FileUtils.AddCount).val('').on('change', FileUtils.chageHandler);
            clonediv.children('label').text('');
            clonediv.find('.file-input-label').val('');
            clonediv.find('.file-input-label-for').attr('for', fileid + FileUtils.AddCount);

            var $delbtn = $("<a class='btn btn-link-red file-remove'><i class='fa fa-times-circle fa-lg' aria-hidden='true'></i></a>").on('click', FileUtils.removeHandler);
            $delbtn.data('filegroup', fileid);
            var fileAppend = clonediv.find('.file-append');
            fileAppend.after($delbtn);
            fileAppend.remove();
            $(this).closest('.form-group').after(clonediv);

        });
    };

    //设置附件最大上传个数
    FileUtils.setMaxfiles = function (fileId, value) {
        $('#' + fileId).data('maxfiles', value);
        FileUtils.AddCount = 1;
    };

    //重置附件控件
    FileUtils.reset = function (fileId) {

        $("input[id^=" + fileId + "]").each(function (i, v) {
            if ($(this).attr('id') !== fileId) {
                $(this).closest('.form-group').remove();
            }
        });
        $('#' + fileId).val('');
        $('#' + fileId).closest('.form-group').find('.file-input-label').val('');
        $('#' + fileId).data('filecount', 1);
        FileUtils.AddCount = 1;

    };
    FileUtils.MaxFileCount = 5;//上传附件最大数,默认值
    FileUtils.AddCount = 1;//附件添加计数器
    FileUtils.init();

    return FileUtils;
});

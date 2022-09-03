package com.foundation.common.utils.upload;

/**
 * 上传附件异常类
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-24
 */
public class UploadException extends Exception {

	private static final long serialVersionUID = 1L;

	public UploadException() {
		super();
	}

	public UploadException(String message) {
		super(message);
	}

	public UploadException(Throwable cause) {
		super(cause);
	}

	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}

}

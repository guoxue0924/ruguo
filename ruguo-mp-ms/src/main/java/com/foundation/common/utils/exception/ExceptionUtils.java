package com.foundation.common.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * Created on 2017年3月31日
 * <p>
 * Description 异常工具类
 * <p>
 * Copyright tuling (c) 2016 .
 *
 * @author xiaohui
 */
public class ExceptionUtils {

	/**
	 * 
	 * Created on 2017年3月31日 .
	 * <p>
	 * Description 获取异常全栈详细信息
	 *
	 * @author xiaohui
	 * @param e
	 * @return String
	 */
	public static String getAllExp(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		e.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

}

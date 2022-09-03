package com.foundation.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;




/**
 * ID生成器 
 */
public class IDHelper {
	
	
	/**
	 * 生成UUID
	 */
	public static synchronized String genUuid(){
		UUID uuid =UUID.randomUUID();   
        String uuidStr = uuid.toString();  
		uuidStr = StringUtils.remove(uuidStr, '-');
        return uuidStr;
	}
	

	/**
	 * 生成32位长ID
	 */
	public static synchronized String genTimeseqId(){
		StringBuffer buf = new StringBuffer();  
		//取得时间戳 
        buf.append(getTimeStamp());            
        Random r = new Random(); 
        //增加一个随机数   
        for(int i=0;i<7;i++){  
            buf.append(r.nextInt(10));             
        }  
        return buf.toString();
	}
	private static String getTimeStamp(){  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
        return sdf.format(new Date());  
    }
	
	
	
	
	
}

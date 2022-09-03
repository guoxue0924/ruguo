package com.tuling.modules.demo.service;

import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.foundation.common.utils.JedisUtils;

//@Service
//@Lazy(false)
public class DemoScheduleTask {

	
	/**
	 * 每天凌晨1点触发（进行磁盘检测）
	 */
//	@Scheduled(cron="0/10 * * * * ?")	
	@Scheduled(fixedRate = 5000)
	public void run() {
		
		JedisUtils.set("name", "123");
		System.out.println(JedisUtils.get("name"));
		
        System.out.println(new Date());
		
	}
	
}

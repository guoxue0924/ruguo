package com.foundation.modules.sys.entity;

import com.foundation.common.persistence.DataEntity;


/**
 * Created on 2017年3月6日
 * Description {个人信息管理查询Entity}
 * Copyright tuling (c) 2017
 * @author jiyingming
 */
public class UserFilter extends DataEntity<UserFilter> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id; // 主键

    private String loginName; // 账号

    private String name; // 真实姓名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
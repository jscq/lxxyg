package com.chengqing.model.sys;

import com.chengqing.model.BaseModel;

/**
 * 用户信息
 * @author ChengQing
 *
 */
public class User extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String username;
	
	/** 用户密码 */
	private String password;
	
	/** 用户类型：1.系统用户 2.教师  4.学生 */
	private String userType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}

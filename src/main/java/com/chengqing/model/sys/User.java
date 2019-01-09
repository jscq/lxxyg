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
	private String userName;
	
	/** 用户密码 */
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	
	
	
	
}

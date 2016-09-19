package com.wsx.friends.user.login.model;

import com.wsx.friends.user.UserBaseBean;

public class LoginUser extends UserBaseBean {

	private static final long serialVersionUID = -4010677806181012831L;
	
	private String checkCode;

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}

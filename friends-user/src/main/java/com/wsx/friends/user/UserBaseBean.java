package com.wsx.friends.user;

import com.wsx.friends.core.model.BaseBean;

public class UserBaseBean extends BaseBean {

	private static final long serialVersionUID = 3590142830504197822L;
	
	private String userCode;
	private String password;
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

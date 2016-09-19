package com.wsx.friends.user.signup.model;

import java.util.List;

import com.wsx.friends.core.model.OutputData;

public class WebUserOutputData extends OutputData {

	private static final long serialVersionUID = -4409188367346019847L;
	
	public WebUserOutputData() {
		super();
	}
	
	public WebUserOutputData(int code, int status) {
		super(code, status);
	}
	
	private List<WebUser> users;

	public List<WebUser> getUsers() {
		return users;
	}

	public void setUsers(List<WebUser> users) {
		this.users = users;
	}
}

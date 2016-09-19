package com.wsx.friends.es.search.user.model;


import com.wsx.friends.core.model.OutputData;

public class UserOutputData extends OutputData {

	private static final long serialVersionUID = 8265128532231909957L;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

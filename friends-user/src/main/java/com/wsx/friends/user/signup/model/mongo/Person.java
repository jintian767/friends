package com.wsx.friends.user.signup.model.mongo;


import org.springframework.data.mongodb.core.mapping.Document;

import com.wsx.friends.core.model.BaseBean;

@Document(collection="person")
public class Person extends BaseBean {
	
	private static final long serialVersionUID = -7692455343977556936L;
	private String name;
	private String home;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	
}

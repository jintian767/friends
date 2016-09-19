package com.wsx.friends.core.token;

import java.io.Serializable;

/**
 * 
 * @author wangshuaixin
 *
 */
public class UserToken implements Serializable {

	private static final long serialVersionUID = 1601095349832377707L;
	
	private String token;
	private long startTime;
	
	public UserToken(String token,long startTime) {
		this.token = token;
		this.startTime = startTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}

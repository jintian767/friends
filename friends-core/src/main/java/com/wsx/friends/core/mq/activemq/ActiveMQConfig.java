package com.wsx.friends.core.mq.activemq;

public class ActiveMQConfig {

	private static String activeUrl = "nio://127.0.0.1:61616";
	private static String username = "system";
	private static String password = "manager";
	
	public static String getActiveUrl() {
		return activeUrl;
	}
	public static void setActiveUrl(String activeUrl) {
		ActiveMQConfig.activeUrl = activeUrl;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		ActiveMQConfig.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		ActiveMQConfig.password = password;
	}
}

package com.wsx.friends.core.mq.activemq;

public class JMSUtil {

	private JMSUtil(){
	}
	
	private static JMSHelper jmsHelper; 
	
	public static JMSHelper getInstance() {
		if (null == jmsHelper) {
			jmsHelper = new JMSHelper(ActiveMQConfig.getActiveUrl(), 
					ActiveMQConfig.getUsername(), ActiveMQConfig.getPassword());
		}
		return jmsHelper;
	}
	
	/**
	 * 
	 * @param quene
	 * @param message
	 */
	public static void sendQueneMessage(String quene, String message) {
		getInstance().send(quene, message);
	}
	
	
	
}

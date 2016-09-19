package com.wsx.friends.core.mq.activemq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;


/**
 * 
 * @author wangshuaixin
 *
 */
public class JMSHelper implements ExceptionListener {
	
	// 设置连接的最大连接数
	public final static int DEFAULT_MAX_CONNECTIONS = 50;
	private int maxConnections = DEFAULT_MAX_CONNECTIONS;
	// 设置每个连接中使用的最大活动会话数
	private int maximumActiveSessionPerConnection = DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION;
	public final static int DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION = 300;
	// 线程池数量
	private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;
	public final static int DEFAULT_THREAD_POOL_SIZE = 500;
	// 强制使用同步返回数据的格式
	private boolean useAsyncSendForJMS = DEFAULT_USE_ASYNC_SEND_FOR_JMS;
	public final static boolean DEFAULT_USE_ASYNC_SEND_FOR_JMS = true;
	// 是否持久化消息
	private boolean isPersistent = DEFAULT_IS_PERSISTENT;
	public final static boolean DEFAULT_IS_PERSISTENT = true;
	
	public final static boolean ISTRANSACTION = false;
	
	
	private String activeUrl;
	private String username;
	private String password;
	
	private ExecutorService activeThreadpool;
	
	private PooledConnectionFactory pooledConnectionFactory;
	
	public JMSHelper(String activeUrl, String username, String password) {
		this(activeUrl, username, password, DEFAULT_MAX_CONNECTIONS,
				DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION,
				DEFAULT_THREAD_POOL_SIZE, DEFAULT_USE_ASYNC_SEND_FOR_JMS,
				DEFAULT_IS_PERSISTENT);
	}
	

	public JMSHelper(String activeUrl, String username, String password, int maxConnections,
			int maximumActiveSessionPerConnection, int threadPoolSize, boolean useAsyncSendForJms,
			boolean isPersistent) {
		this.activeUrl = activeUrl;
		this.username = username;
		this.password = password;
		this.maxConnections = maxConnections;
		this.maximumActiveSessionPerConnection = maximumActiveSessionPerConnection;
		this.threadPoolSize = threadPoolSize;
		this.useAsyncSendForJMS = useAsyncSendForJms;
		this.isPersistent = isPersistent;
		
		//
		init();
	}


	private void init() {
		this.activeThreadpool = Executors.newFixedThreadPool(this.threadPoolSize);
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(this.username, this.password, this.activeUrl);
		activeMQConnectionFactory.setUseAsyncSend(this.useAsyncSendForJMS);
		
		this.pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
		this.pooledConnectionFactory.setCreateConnectionOnStartup(true);
		this.pooledConnectionFactory.setMaxConnections(this.maxConnections);
		this.pooledConnectionFactory.setMaximumActiveSessionPerConnection(this.maximumActiveSessionPerConnection);
	}
	
	public void send(final String quene, final String jsonValue) {
		this.send(quene, jsonValue, ISTRANSACTION);
	}
	
	public void send(final String quene, final String jsonValue, final boolean isTransaction) {
		this.activeThreadpool.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					sendQueneMessage(quene, jsonValue, isTransaction);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}
	
	private void sendQueneMessage(String quene, String jsonValue, boolean isTransaction) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Session session = null;
		try {
			connection = this.pooledConnectionFactory.createConnection();
			session = connection.createSession(isTransaction, Session.AUTO_ACKNOWLEDGE);
			//
			Destination destination = session.createQueue(quene);
			MessageProducer messageProducer = session.createProducer(destination);
			messageProducer.setDeliveryMode(this.isPersistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
			
			messageProducer.send(session.createTextMessage(jsonValue));
			
			if (isTransaction) {
				session.commit();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (null != session) {
				closeSession(session);
			}
			if (null != connection) {
				closeConnection(connection);
			}
		}
	}



	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	private void closeSession(Session session) {
		try {
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	public void onException(JMSException ex) {
		ex.printStackTrace();
	}

}

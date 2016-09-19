package com.wsx.friends.core.redis.model;

import java.io.Closeable;

/**
 * 
 * @author wangshuaixin
 *
 */
public abstract class SerializeTranscoder {

	public abstract byte[] serialize(Object value);
	
	public abstract Object deserialize(byte[] bytes);
	
	public void close(Closeable closeable) {
		if (null != closeable) {
			try {
				closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

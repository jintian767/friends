package com.wsx.friends.core.redis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Repository;

import com.wsx.friends.core.util.CommonFinalUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository("redisSequenceUtil")
public class RedisSequenceUtil {
	
	@Autowired
	private static JedisConnectionFactory jedisConnectionFactory;
	
	public static final String SEQUENCE_USER = "SEQUENCE_USER";
	public static final String SEQUENCE_BASE = "SEQUENCE_BASE";

	private static Map<String, ConcurrentLinkedQueue<String>> sequenceMap = new ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>();
	
	private final static int redisSequenceSize = 100;
	
	private static JedisPool  sequenceJedisPool;
	
	
	public synchronized static String getSequenceValue(String sequenceName) {
		String result = null;
		
		ConcurrentLinkedQueue<String> quene = sequenceMap.get(sequenceName);
		if (null != quene) {
			if (quene.isEmpty()) {
				getAndSetSequenceFromRedis(jedisConnectionFactory, quene, sequenceName);
			}
		} else {
			quene = new ConcurrentLinkedQueue<String>();
			sequenceMap.put(sequenceName, quene);
			getAndSetSequenceFromRedis(jedisConnectionFactory, quene, sequenceName);
		}
		
		result = quene.poll();
		return result;
	}


	private static void getAndSetSequenceFromRedis(JedisConnectionFactory factory,
			ConcurrentLinkedQueue<String> quene, String sequenceName) {
		
		Jedis jedis = getRedisPool(factory);
		try {
			String luaScript = "local firstNum=redis.call('INCR',KEYS[1]);local endNum=firstNum+("+String.valueOf(redisSequenceSize)+"-1);redis.call('SET',KEYS[1],endNum);return firstNum..'-'..endNum;";
			//获得的值格式为 1-100
			Object sequeceNumber = jedis.eval(luaScript.toString(), 1, sequenceName);
			String [] sequeces = String.valueOf(sequeceNumber).split(CommonFinalUtil.ENGLISH_SPIT);
			for (long i = Long.valueOf(sequeces[0]); i <= Long.valueOf(sequeces[1]); i++) {
				quene.add(String.valueOf(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			releaseRedis(jedis);
		}
	}


	
	private static void releaseRedis(Jedis jedis) {
		sequenceJedisPool.returnResourceObject(jedis);
	}


	private static Jedis getRedisPool(JedisConnectionFactory factory) {
		Jedis jedis = null;
		try {
			if (null == sequenceJedisPool) {
				sequenceJedisPool = new JedisPool(factory.getPoolConfig(), factory.getHostName(), factory.getPort());
			}
			jedis = sequenceJedisPool.getResource();
			jedis.select(factory.getDatabase());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jedis;
	}
}

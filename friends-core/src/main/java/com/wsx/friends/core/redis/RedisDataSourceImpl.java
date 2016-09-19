package com.wsx.friends.core.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wsx.friends.core.redis.RedisDataSource;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis客户端的获取和释放
 * @author wangshuaixin
 *
 */
@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {
	
	private static Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	/**
	 * 获得redis客户端
	 */
	@Override
	public ShardedJedis getRedisClient() {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return shardedJedis;
		} catch (Exception e) {
			log.error("redis", e);
			if (null != shardedJedis) {
				shardedJedis.close();
			}
			return null;
		}
	}

	/**
	 * 关闭操作的客户端，根据redis的版本不同选择不同的关闭方法
	 */
	@Override
	public void closeRedisClient(ShardedJedis shardedJedis) {
		shardedJedis.disconnect();
	}

	/**
	 * 出现异常的关闭情况
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void closeRedisClient(ShardedJedis shardedJedis, boolean broken) {
		if (broken) {
			shardedJedisPool.returnBrokenResource(shardedJedis);
		} else {
			shardedJedisPool.returnResourceObject(shardedJedis);
		}
	}

}

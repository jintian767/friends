package com.wsx.friends.core.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * redis的公用操作，获得redis和关闭redis的接口
 * @author wangshuaixin
 *
 */
public interface RedisDataSource {

	public abstract ShardedJedis getRedisClient();
	
	public void closeRedisClient(ShardedJedis shardedJedis);
	
	public void closeRedisClient(ShardedJedis shardedJedis, boolean broken);
}

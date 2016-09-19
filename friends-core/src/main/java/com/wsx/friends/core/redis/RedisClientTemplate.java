package com.wsx.friends.core.redis;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wsx.friends.core.redis.RedisDataSource;
import com.wsx.friends.core.util.RedisFinalUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
/**
 * redis客户端的操作
 * @author wangshuaixin
 *
 */
@Repository("redisClientTemplate")
public class RedisClientTemplate {

	@Autowired
	private RedisDataSource redisDataSource;
	
	/**
	 * 关闭连接的操作
	 */
	public void disconnect() {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		shardedJedis.disconnect();
	}
	
	/**
	 * 把相应的数据放入redis中，对应的是key和value，系统默认操作的是0的redis的db
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {
		return set(key, value, RedisFinalUtil.DB_DEFAULT);
	}

	/**
	 * 放入相应的数据，key，value，db位置
	 * @param key
	 * @param value
	 * @param dbIndex
	 * @return
	 */
	public String set(String key, String value, int dbIndex) {
		String result = null;
		
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		if (dbIndex >= 0) {
			selelctDB(shardedJedis, dbIndex);
		}
		
		boolean broken = false;
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			broken = true;
		} finally {
			redisDataSource.closeRedisClient(shardedJedis, broken);
		}
		return result;
	}
	
	/**
	 * 获得对应的值
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return get(key, RedisFinalUtil.DB_DEFAULT);
	}
	
	/**
	 * 获得对应的值，指定db位置和key
	 * @param key
	 * @param dbIndex
	 * @return
	 */
	public String get(String key, int dbIndex) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		if (dbIndex >= 0) {
			selelctDB(shardedJedis, dbIndex);
		}
		boolean broken = false;
		try {
			result = shardedJedis.get(key);
		} catch (Exception e) {
			broken = true;
		} finally {
			redisDataSource.closeRedisClient(shardedJedis, broken);
		}
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param dbIndex
	 * @param seconds
	 * @return
	 */
	public String set(String key, String value, int dbIndex, int seconds) {
		String result = null;
		
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		if (dbIndex >= 0) {
			selelctDB(shardedJedis, dbIndex);
		}
		
		boolean broken = false;
		try {
			result = shardedJedis.setex(key, seconds, value);
		} catch (Exception e) {
			broken = true;
		} finally {
			redisDataSource.closeRedisClient(shardedJedis, broken);
		}
		return result;
	}
	
	public boolean exists(String key) {
		return exists(key, RedisFinalUtil.DB_DEFAULT);
	}
	
	public boolean exists(String key, int dbIndex) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return false;
		}
		if (dbIndex >= 0) {
			selelctDB(shardedJedis, dbIndex);
		}
		
		boolean broken = false;
		try {
			shardedJedis.exists(key);
		} catch (Exception e) {
			broken = true;
		} finally {
			redisDataSource.closeRedisClient(shardedJedis, broken);
		}
		return !broken;
	}
	
	public boolean del(String key) {
		return del(key, RedisFinalUtil.DB_DEFAULT);
	}
	public boolean del(String key, int dbIndex) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return false;
		}
		if (dbIndex >= 0) {
			selelctDB(shardedJedis, dbIndex);
		}
		
		boolean broken = false;
		try {
			shardedJedis.del(key);
		} catch (Exception e) {
			broken = true;
		} finally {
			redisDataSource.closeRedisClient(shardedJedis, broken);
		}
		return !broken;
	}

	/**
	 * 选择对应的db位置
	 * @param shardedJedis
	 * @param dbIndex
	 */
	private void selelctDB(ShardedJedis shardedJedis, int dbIndex) {
		Collection<Jedis> collections = shardedJedis.getAllShards();
		Iterator<Jedis> jedis = collections.iterator();
		while (jedis.hasNext()) {
			jedis.next().select(dbIndex);
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(byte[] key, byte[] value) {
		return set(key, value, RedisFinalUtil.DB_DEFAULT);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param dbIndex
	 * @return
	 */
	public String set(byte[] key, byte[] value, int dbIndex) {
		String result = null;
		
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		if (dbIndex >= 0) {
			selelctDB(shardedJedis, dbIndex);
		}
		
		boolean broken = false;
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			broken = true;
		} finally {
			redisDataSource.closeRedisClient(shardedJedis, broken);
		}
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) {
		return get(key, RedisFinalUtil.DB_DEFAULT);
	}

	/**
	 * 
	 * @param key
	 * @param dbDefault
	 * @return
	 */
	public byte[] get(byte[] key, int dbIndex) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		if (dbIndex >= 0) {
			selelctDB(shardedJedis, dbIndex);
		}
		boolean broken = false;
		try {
			result = shardedJedis.get(key);
		} catch (Exception e) {
			broken = true;
		} finally {
			redisDataSource.closeRedisClient(shardedJedis, broken);
		}
		return result;
	}
}

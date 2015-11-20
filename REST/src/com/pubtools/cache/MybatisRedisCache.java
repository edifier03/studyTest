package com.pubtools.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.SerializedCache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MybatisRedisCache implements Cache{
	
	private Jedis redisClient = createRedis();
	
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private String id;
	
	public MybatisRedisCache(final String id)
	{
		if(id == null)
		{
			throw new IllegalArgumentException("cache no id");
		}
		
		this.id = id;
	}
	
	@Override
	public void clear() {
		redisClient.flushDB();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		byte[] bytes = redisClient.get(SerializeUtil.serialize(key.toString()));
		System.out.println(bytes);
		if(bytes==null)
			return null;
		Object value = SerializeUtil.unserialize(bytes);
		return value;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return readWriteLock;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return Integer.valueOf(redisClient.dbSize().toString());
	}

	@Override
	public void putObject(Object key, Object value) {
		// TODO Auto-generated method stub
		redisClient.setex(SerializeUtil.serialize(key.toString()),1000, SerializeUtil.serialize(value));
		redisClient.set(SerializeUtil.serialize(key.toString()),SerializeUtil.serialize(value));
	}

	@Override
	public Object removeObject(Object key) {
		// TODO Auto-generated method stub
		return redisClient.expire(SerializeUtil.serialize(key.toString()), 0);
	}
	
	protected static Jedis createRedis(){
		try{
		   JedisPoolConfig config = new JedisPoolConfig();
		   config =new JedisPoolConfig();
	       // config.setMaxTotal(60000);//设置最大连接数  
		    config.setMaxActive(60000);
	        config.setMaxIdle(1000); //设置最大空闲数 
	        config.setMaxWait(3000);
	        config.setTestOnBorrow(true);
			
			JedisPool pool = new JedisPool(config,"192.168.189.128",6379);
			return pool.getResource();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		throw new RuntimeException("初始化连接池错误。");
	}
}

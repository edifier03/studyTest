package com.pubtools.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.SerializedCache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class MybatisRedisCache4 implements Cache{
	
	/** The ReadWriteLock. */
	  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	  
	  private String id;
	  public MybatisRedisCache4(final String id) {
	    if (id == null) {
	      throw new IllegalArgumentException("±ØÐë´«ÈëID");
	    }
	    this.id=id;
	  }
	  
	  @Override
	  public String getId() {
	    return this.id;
	  }

	  @Override
	  public int getSize() {
	    Jedis jedis = null;
	    JedisPool jedisPool = null;
	    int result = 0;
	    boolean borrowOrOprSuccess = true;
	    try {
	      jedis   = CachePool.getInstance().getJedis();
	      jedisPool = CachePool.getInstance().getJedisPool();
	      result = Integer.valueOf(jedis.dbSize().toString());
	    } catch (JedisConnectionException e) {
	      borrowOrOprSuccess = false;
	      if (jedis != null)
	        jedisPool.returnBrokenResource(jedis);
	    } finally {
	      if (borrowOrOprSuccess)
	        jedisPool.returnResource(jedis);
	    }
	    return result;
	     
	  }

	  @Override
	  public void putObject(Object key, Object value) {
	    Jedis jedis = null;
	    JedisPool jedisPool = null;
	    boolean borrowOrOprSuccess = true;
	    try {
	      jedis   = CachePool.getInstance().getJedis();
	      jedisPool = CachePool.getInstance().getJedisPool();
	      jedis.set(SerializeUtil.serialize(key.hashCode()), SerializeUtil.serialize(value));
	    } catch (JedisConnectionException e) {
	      borrowOrOprSuccess = false;
	      if (jedis != null)
	        jedisPool.returnBrokenResource(jedis);
	    } finally {
	      if (borrowOrOprSuccess)
	        jedisPool.returnResource(jedis);
	    }
	    
	  }

	  @Override
	  public Object getObject(Object key) {
	    Jedis jedis = null;
	    JedisPool jedisPool = null;
	    Object value = null;
	    boolean borrowOrOprSuccess = true;
	    try {
	      jedis   = CachePool.getInstance().getJedis();
	      jedisPool = CachePool.getInstance().getJedisPool();
	      value  = SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(key.hashCode())));
	    } catch (JedisConnectionException e) {
	      borrowOrOprSuccess = false;
	      if (jedis != null)
	        jedisPool.returnBrokenResource(jedis);
	    } finally {
	      if (borrowOrOprSuccess)
	        jedisPool.returnResource(jedis);
	    }
	    return value;
	  }

	  @Override
	  public Object removeObject(Object key) {
	    Jedis jedis = null;
	    JedisPool jedisPool = null;
	    Object value = null;
	    boolean borrowOrOprSuccess = true;
	    try {
	      jedis   = CachePool.getInstance().getJedis();
	      jedisPool = CachePool.getInstance().getJedisPool();
	      value  = jedis.expire(SerializeUtil.serialize(key.hashCode()), 0);
	    } catch (JedisConnectionException e) {
	      borrowOrOprSuccess = false;
	      if (jedis != null)
	        jedisPool.returnBrokenResource(jedis);
	    } finally {
	      if (borrowOrOprSuccess)
	        jedisPool.returnResource(jedis);
	    }
	    return value;
	  }

	  @Override
	  public void clear() {
	    Jedis jedis = null;
	    JedisPool jedisPool = null;
	    boolean borrowOrOprSuccess = true;
	    try {
	      jedis   = CachePool.getInstance().getJedis();
	      jedisPool = CachePool.getInstance().getJedisPool();
	      jedis.flushDB();
	      jedis.flushAll();
	    } catch (JedisConnectionException e) {
	      borrowOrOprSuccess = false;
	      if (jedis != null)
	        jedisPool.returnBrokenResource(jedis);
	    } finally {
	      if (borrowOrOprSuccess)
	        jedisPool.returnResource(jedis);
	    }
	  }

	  @Override
	  public ReadWriteLock getReadWriteLock() {
	    return readWriteLock;
	  }
	  
	  public static class CachePool {
		    JedisPool pool;
		    private static final CachePool cachePool = new CachePool();
		    
		    public static CachePool getInstance(){
		      return cachePool;
		    }
		    private CachePool() {
		      JedisPoolConfig config = new JedisPoolConfig();
		      config.setMaxIdle(100);
		      config.setMaxWait(1000l);
		      pool = new JedisPool(config,"192.168.160.134",6379);
		    }
		    public  Jedis getJedis(){
		      Jedis jedis = null;
		      boolean borrowOrOprSuccess = true;
		      try {
		        jedis = pool.getResource();
		      } catch (JedisConnectionException e) {
		        borrowOrOprSuccess = false;
		        if (jedis != null)
		          pool.returnBrokenResource(jedis);
		      } finally {
		        if (borrowOrOprSuccess)
		          pool.returnResource(jedis);
		      }
		      jedis = pool.getResource();
		      return jedis;
		    }
		    
		    public JedisPool getJedisPool(){
		      return this.pool;
		    }
		  }
	  
}

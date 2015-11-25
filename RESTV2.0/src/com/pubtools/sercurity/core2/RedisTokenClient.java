package com.pubtools.sercurity.core2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTokenClient implements InitializingBean, DisposableBean{
	private final Log logger = LogFactory.getLog( getClass() );
	private String redisurl;
	
	private int redisport;
	private JedisPool jedisPool;//·ÇÇÐÆ¬Á¬½Ó³Ø
	@Override
	public void destroy() throws Exception {
		if ( this.jedisPool != null ) {
			this.jedisPool.destroy();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxActive(20); 
        config.setMaxIdle(5); 
        config.setMaxWait(1000l); 
        config.setTestOnBorrow(false); 
//        jedisPool = new JedisPool(config,"192.168.160.133",6379);
        jedisPool = new JedisPool(config,redisurl,redisport);
	}
	
	public Object get(String sessionToken)
	{
		return this.jedisPool.getResource().get(sessionToken);
	}
	public String getRedisurl() {
		return redisurl;
	}

	public void setRedisurl(String redisurl) {
		this.redisurl = redisurl;
	}

	public int getRedisport() {
		return redisport;
	}

	public void setRedisport(int redisport) {
		this.redisport = redisport;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

}

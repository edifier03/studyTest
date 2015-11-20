package com.pubtools.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisFactory {
	public static JedisPool pool = null;
	public static Jedis createRedis(){
		try{
			if(pool!=null)
			{
				return  pool.getResource();
			}
			
		   JedisPoolConfig config = new JedisPoolConfig();
		   config =new JedisPoolConfig();
	       // config.setMaxTotal(60000);//�������������  
		    config.setMaxActive(60000);
	        config.setMaxIdle(1000); //������������ 
	        config.setMaxWait(3000);
	        config.setTestOnBorrow(true);
			pool = new JedisPool(config,"192.168.160.134",6379);
			return pool.getResource();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		throw new RuntimeException("��ʼ�����ӳش���");
	}
}

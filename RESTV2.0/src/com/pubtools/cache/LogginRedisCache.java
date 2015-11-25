package com.pubtools.cache;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;

public class LogginRedisCache extends LoggingCache{

	public LogginRedisCache(String id) {
		super(new MybatisRedisCache(id));
	}

}

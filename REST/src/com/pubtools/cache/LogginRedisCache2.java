package com.pubtools.cache;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;

public class LogginRedisCache2 extends LoggingCache{

	public LogginRedisCache2(String id) {
		super(new MybatisRedisCache(id));
	}

}

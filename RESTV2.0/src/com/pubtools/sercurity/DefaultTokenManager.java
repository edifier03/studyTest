package com.pubtools.sercurity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jackson.map.JsonSerializer;

import com.pubtools.sercurity.core2.RedisTokenClient;
import com.redis.RedisClient;
import com.zhoutao.mvc.auth.bean.AuthBean;

public class DefaultTokenManager implements TokenManager {
	private static Map<String,AuthBean> tokenMap = new ConcurrentHashMap();
	private RedisTokenClient redisClient;
	private int sessionTimeout;

	public int getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public RedisTokenClient getRedisClient() {
		return redisClient;
	}

	public void setRedisClient(RedisTokenClient redisClient) {
		this.redisClient = redisClient;
	}

	@Override
	public String createToken(AuthBean authBean) {
		String token = UUID.randomUUID().toString();
		tokenMap.put(token, authBean);
		redisClient.getJedisPool().getResource().set(token, authBean.getUserName());
		return token;
	}

	@Override
	public boolean checkToken(String token) {
		boolean result = token!=null&&!token.trim().equals("")&&tokenMap.containsKey(token);
		return result;
	}
	
}

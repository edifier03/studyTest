package com.pubtools.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultTokenManager implements TokenManager {
	private static Map<String,String> tokenMap = new ConcurrentHashMap();

	@Override
	public String createToken(String username) {
		String token = "111";
		tokenMap.put(token, username);
		return null;
	}

	@Override
	public boolean checkToken(String token) {
		boolean result = token!=null&&!token.trim().equals("")&&tokenMap.containsKey(token);
		return result;
	}
	
}

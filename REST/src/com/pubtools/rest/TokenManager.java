package com.pubtools.rest;

public interface TokenManager {
	String createToken(String username);
	boolean checkToken(String token);
}	

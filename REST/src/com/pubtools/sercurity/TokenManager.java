package com.pubtools.sercurity;

public interface TokenManager {
	
	String createToken(String username);  
	  
    boolean checkToken(String token); 
}

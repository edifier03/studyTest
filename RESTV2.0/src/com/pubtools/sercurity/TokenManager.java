package com.pubtools.sercurity;

import com.zhoutao.mvc.auth.bean.AuthBean;

public interface TokenManager {
	
	String createToken(AuthBean authBean);  
	  
    boolean checkToken(String token); 
}

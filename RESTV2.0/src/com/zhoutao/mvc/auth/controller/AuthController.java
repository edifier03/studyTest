package com.zhoutao.mvc.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pubtools.rest.Response;
import com.pubtools.sercurity.IgnoreSecurity;
import com.pubtools.sercurity.TokenManager;
import com.zhoutao.mvc.auth.bean.AuthBean;

@RestController  
public class AuthController {
	@Autowired 
	private TokenManager tokenManager;  
    public TokenManager getTokenManager() {
		return tokenManager;
	}
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}
	/*
	 * ×Ô¶¨Òåvalidate 
	 */
	@RequestMapping(value="/auth/login",method=RequestMethod.POST)
	@IgnoreSecurity
	public 
		Response authLogin(@RequestBody @Valid AuthBean authBean) {
		System.out.println("userName:"+authBean.getUserName());
		System.out.println("passWord:"+authBean.getPassWord());
		if("zhoutao".equals(authBean.getUserName())
				&&"123".equals(authBean.getPassWord()))
		{
			String tokenid = tokenManager.createToken(authBean);
			authBean.setTokenid(tokenid);

			return new Response().success(authBean);
		}else
		{
			return new Response().failure(authBean);
		}
		
		
	}
}

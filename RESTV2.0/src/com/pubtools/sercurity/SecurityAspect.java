package com.pubtools.sercurity;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SecurityAspect {  
	  
    private static final String DEFAULT_TOKEN_NAME = "X-Token";  
  
    private TokenManager tokenManager;  
    private String tokenName;  
  
    public void setTokenManager(TokenManager tokenManager) {  
        this.tokenManager = tokenManager;  
    }  
  
    public void setTokenName(String tokenName) {  
        if (tokenName==null||tokenName.trim().equals("")) {  
            tokenName = DEFAULT_TOKEN_NAME;  
        }  
        this.tokenName = tokenName;  
    }  
//    @Autowired  
//    private  HttpServletRequest request; 
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {  
        // 从切点上获取目标方法  
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();  
        
        Method method = methodSignature.getMethod(); 
        System.out.println(method.getName());
        // 若目标方法忽略了安全性检查，则直接调用目标方法  
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {  
            return pjp.proceed();  
        }  
        // 从 request header 中获取当前 token  
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();    
        String token = request.getHeader(tokenName);  
        // 检查 token 有效性  
        if (!tokenManager.checkToken(token)) {  
            String message = String.format("token [%s] is invalid", token);  
            throw new Exception(message);  
        }  
        // 调用目标方法  
        return pjp.proceed();  
    }  
}  

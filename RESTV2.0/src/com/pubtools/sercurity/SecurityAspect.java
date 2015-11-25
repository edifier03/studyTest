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
        // ���е��ϻ�ȡĿ�귽��  
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();  
        
        Method method = methodSignature.getMethod(); 
        System.out.println(method.getName());
        // ��Ŀ�귽�������˰�ȫ�Լ�飬��ֱ�ӵ���Ŀ�귽��  
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {  
            return pjp.proceed();  
        }  
        // �� request header �л�ȡ��ǰ token  
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();    
        String token = request.getHeader(tokenName);  
        // ��� token ��Ч��  
        if (!tokenManager.checkToken(token)) {  
            String message = String.format("token [%s] is invalid", token);  
            throw new Exception(message);  
        }  
        // ����Ŀ�귽��  
        return pjp.proceed();  
    }  
}  

package com.pubtools.sercurity.core2;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 该过滤器对Ams-Session-Token进行验证
 * 
 * @author xiaofei.xu
 * 
 */
public class SessionTokenAuthenticationFilter extends GenericFilterBean {

	private final static String AMS_SESSION_TOKEN = "Ams-Session-Token";

	/** 认证管理器 */
	private AuthenticationManager authenticationManager;

	/** 认证失败处理器 */
	private AuthenticationEntryPoint authenticationEntryPoint;

	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();

		Assert.notNull( this.authenticationManager, "AuthenticationManager is required" );
	}

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException,
			ServletException {

		// 查看是否已认证过
		Authentication auth_result = SecurityContextHolder.getContext().getAuthentication();
		if ( auth_result != null && auth_result.isAuthenticated() ) {
			// 继续处理链
			chain.doFilter( request, response );
			return;
		}

		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;

		String sessionToken = req.getHeader( AMS_SESSION_TOKEN );

		try {

			// TOKEN头必须设置
			if ( sessionToken==null||sessionToken.trim().equals("") ) {
				sessionToken = "123";
//				throw new SessionTokenAuthenticationException( "Error occoured.", new Exception("HTTP Header AMS-SESSION_TOKEN is required" ) );
			}

			// 使用认证管理器进行认证
			SessionTokenAuthentication authentication = new SessionTokenAuthentication( sessionToken );
			Authentication result = authenticationManager.authenticate( authentication );

			// 在上下文中设置认证结果
			SecurityContextHolder.getContext().setAuthentication( result );
		}
		catch ( AuthenticationException authException ) {
			// 清除上下文
			SecurityContextHolder.clearContext();

			this.authenticationEntryPoint.commence( req, res, authException );

			return;
		}

		// 继续处理链
		chain.doFilter( request, response );
	}

	// ============================================================================
	// getter/setter

	public void setAuthenticationManager( AuthenticationManager authenticationManager ) {
		this.authenticationManager = authenticationManager;
	}

	public void setAuthenticationEntryPoint( AuthenticationEntryPoint authenticationEntryPoint ) {
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
}

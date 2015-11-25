package com.pubtools.sercurity.core2;

import javax.security.sasl.AuthenticationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.Assert;

import com.pubtools.sercurity.bean.LoginUserInfo;


/**
 * 该类使用保存在HTTP请求头Token的值进行权限验证
 */
public class SessionTokenAuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private final Log logger = LogFactory.getLog( getClass() );

	/** Redis client */
	private RedisTokenClient client;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull( client, "Redis uri is required" );
	}

	@Override
	public Authentication authenticate( Authentication authentication ) {

		// 获取Ams-Session-Token
		String sessionToken = (String) authentication.getPrincipal();
		Object jsonLoginInfo = client.get( sessionToken );

		if ( jsonLoginInfo == null ) {
			// 根据Ams-Session-Token未获取到登录信息

			if ( logger.isDebugEnabled() )
				logger.debug( "Failed to get user information from couchbase by session token [" + sessionToken + "]" );

			throw new SessionTokenAuthenticationException( "Error occoured.", new Exception(
					"Invalid session token" ) );
		}
		else {
			// 转化JSON值

			ObjectMapper mapper = new ObjectMapper();
			LoginUserInfo loginInfo = null;
			try {
				loginInfo = mapper.readValue( (String) jsonLoginInfo, LoginUserInfo.class );
			}
			catch ( Exception ex ) {
				logger.error( "Failed to convert json to object.", ex );

				throw new SessionTokenAuthenticationException( "Json value can not convert to Object.", ex );
			}

			SessionTokenAuthentication result = new SessionTokenAuthentication(
					AuthorityUtils.createAuthorityList( new String[] { loginInfo.getRole().toString() } ), sessionToken );
			result.setAuthenticated( true ); // 已认证

			// 设置详细信息
			result.setDetails( loginInfo );

			return result;
		}

	}

	@Override
	public boolean supports( Class<?> authentication ) {
		return ( SessionTokenAuthentication.class.isAssignableFrom( authentication ) );
	}

	public void setClient( RedisTokenClient client ) {
		this.client = client;
	}
}

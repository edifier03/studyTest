package com.pubtools.sercurity.core2;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SessionTokenAuthentication extends AbstractAuthenticationToken {

	private final Object principal;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6915327210362470681L;

	public SessionTokenAuthentication( Object principal ) {
		super( null );
		this.principal = principal;
		setAuthenticated( false );
	}

	public SessionTokenAuthentication( Collection<? extends GrantedAuthority> authorities, Object principal ) {
		super( authorities );
		this.principal = principal;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

}

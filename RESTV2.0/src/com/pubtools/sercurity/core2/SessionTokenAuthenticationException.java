package com.pubtools.sercurity.core2;

import org.springframework.security.core.AuthenticationException;

public class SessionTokenAuthenticationException extends AuthenticationException {

	public SessionTokenAuthenticationException( String msg, Throwable t ) {
		super( msg, t );
	}

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5763850734913431450L;

}

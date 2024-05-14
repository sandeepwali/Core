package com.solumesl.aims.saas.adapter.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenNotFoundException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenNotFoundException(String msg) {
		super(msg);
	}

}

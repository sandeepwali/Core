package com.solumesl.aims.saas.adapter.exception;

import org.springframework.security.core.AuthenticationException;

public class UnderPriviliegedUserException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnderPriviliegedUserException(String msg) {
		super(msg);
	}

}

package com.solumesl.aims.saas.adapter.exception;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public class AccessTokenExpiredException extends Exception{
  
	private static final long serialVersionUID = -7654446610478295053L;
 
	 
    public AccessTokenExpiredException() {
        super();
    }

    
    public AccessTokenExpiredException(String s) {
        super(s);
    }

}

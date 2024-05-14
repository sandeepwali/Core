package com.solumesl.aims.saas.adapter.exception;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public class ConstraintViolationException extends Exception{
  
	private static final long serialVersionUID = -7654446610478295053L;
 
	 
    public ConstraintViolationException() {
        super();
    }

    
    public ConstraintViolationException(String s) {
        super(s);
    }

}

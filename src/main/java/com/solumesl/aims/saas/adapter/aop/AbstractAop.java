
package com.solumesl.aims.saas.adapter.aop;


import org.aspectj.lang.annotation.Pointcut;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public abstract class AbstractAop {
	@Pointcut(" execution(* com.solumesl..saas..*(..)) && !within(com.solumesl.aims..filter..*) && !within(com.solumesl.aims..job..*)")
	protected void loggingOperation(){}
	
	@Pointcut(" execution(* com.solumesl..*(..)) ")
	protected void allPackage(){}
	 
	@Pointcut(" !execution(* org.springframework.web..*(..)) && !within(is(FinalType))")
	protected void excludeSpringFramework(){}

	
	@Pointcut("execution(public * *(..))") //this should work for the public pointcut
	protected void anyPublicOperation() {}
	
	@Pointcut("  !execution(void set*(*)) && !execution(!void get*())")
	protected void excludeGetterSetter() {
	}
 
	@Pointcut("  loggingOperation() && anyPublicOperation() && excludeSpringFramework() && excludeGetterSetter()")
	protected void allPublicInService() {}
}

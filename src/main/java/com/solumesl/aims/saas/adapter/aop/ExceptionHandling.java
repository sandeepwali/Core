package com.solumesl.aims.saas.adapter.aop;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.solumesl.aims.saas.adapter.util.SolumSaasUtil;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Aspect
@Component
public class ExceptionHandling extends AbstractAop {
	@AfterThrowing(pointcut = "allPublicInService()", throwing = "e")
	@Order(2)
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) throws ClassNotFoundException
	{
		Logger logger = LoggerFactory.getLogger(Class.forName(joinPoint.getSignature().getDeclaringTypeName()));
		logger.error("An exception has been thrown in {} -> {}",SolumSaasUtil.camelToSnake(joinPoint.getSignature().getName()), ExceptionUtils.getStackTrace(e));
		logger.info(SolumSaasUtil.camelToSnake(joinPoint.getSignature().getName()) + "() ENDS " );
	}
}

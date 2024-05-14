package com.solumesl.aims.saas.adapter.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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
 
public class LogExecutionTimeAspect  extends  AbstractAop{



	@Around("loggingOperation()")
	@Order(1)
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable
	{
		Logger logger = LoggerFactory.getLogger(Class.forName(joinPoint.getSignature().getDeclaringTypeName()));
		logger.info( "{} BEGINS",  SolumSaasUtil.camelToSnake(joinPoint.getSignature().getName()));
		try
		{
			Object result = joinPoint.proceed();
			logger.info( "{} ENDS",  SolumSaasUtil.camelToSnake(joinPoint.getSignature().getName())); 
			return result;
		}
		catch (IllegalArgumentException e)
		{
			throw e;
		}
	}



	

}


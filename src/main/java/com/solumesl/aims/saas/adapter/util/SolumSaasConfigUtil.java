package com.solumesl.aims.saas.adapter.util;

 
import java.util.Map;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Service
public class SolumSaasConfigUtil implements ApplicationContextAware{
	private static ApplicationContext applicationContext;
	
	public static String getBeanPropertyValue( String beanId, String propertyName ){
		Map<String,String> mapObj = getMap(beanId);
		if( mapObj != null ){
			return mapObj.get(propertyName);
		}
		return null;
	}
	
	public static Object getBean( String beanId ){
		if( applicationContext != null ){
			return applicationContext.getBean(beanId);
		}
		return null;
	}
	public static <T> T getBean(Class<T> requiredType ){
		if( applicationContext != null ){
			return applicationContext.getBean(requiredType);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public static <T> Map<T, T> getMap( String beanId ){
		try{
			return (Map<T, T>)getBean(beanId);
		}catch(Exception e){
		}
		return null;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
}

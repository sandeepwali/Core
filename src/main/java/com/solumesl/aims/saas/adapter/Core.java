package com.solumesl.aims.saas.adapter;

import java.util.Locale;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.solumesl.aims.saas.adapter.retry.listener.AuthenticationRetryListener;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@SpringBootApplication 
@ImportResource("classpath:*-Config.xml")
public class Core {

	@Bean  
	public  LocaleResolver localeResolver()  
	{  
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();  
		localeResolver.setDefaultLocale(Locale.US);  
		return localeResolver;  
	}  

	@Bean  
	public ResourceBundleMessageSource bundleMessageSource()  
	{  
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
		messageSource.setBasename("message");  
		return messageSource;  
	} 

	
}

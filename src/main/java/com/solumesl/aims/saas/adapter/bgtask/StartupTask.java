package com.solumesl.aims.saas.adapter.bgtask;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Component
public class StartupTask {


	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {}
}
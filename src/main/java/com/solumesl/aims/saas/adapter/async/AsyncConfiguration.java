package com.solumesl.aims.saas.adapter.async;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
 /**
  * 
  * @author baskarmohanasundaram
  *
  */
@Configuration
public class AsyncConfiguration 
{
	@Value("${solum.saas.async.poolsize:1}")
	private int poolSize;
	
  @Bean(name = "asyncExecutor")
  public Executor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(poolSize);
    executor.setMaxPoolSize(poolSize);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("AsynchThread-");
    executor.initialize();
    return executor;
  }
}
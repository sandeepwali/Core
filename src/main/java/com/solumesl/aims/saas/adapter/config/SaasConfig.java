package com.solumesl.aims.saas.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.solumesl.aims.saas.adapter.store.SaasTokenStore;
import com.solumesl.aims.saas.adapter.store.Store;

@Configuration
public class SaasConfig {
	@Bean
	public Store saasTokenStore() {
		return new SaasTokenStore();
	}

}

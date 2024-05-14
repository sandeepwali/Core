package com.solumesl.aims.saas.adapter.config;

import org.springframework.context.annotation.Bean;

import com.solumesl.aims.saas.adapter.Interceptor.SaasFeignClientInterceptor;
import com.solumesl.aims.saas.adapter.decoder.SaasCustomErrorDecoder;

public class FeignSaasConfig {
	@Bean
	public SaasFeignClientInterceptor feignClientInterceptor() {
		return new SaasFeignClientInterceptor();
	}
	@Bean
	public SaasCustomErrorDecoder saasCustomErrorDecoder() {
		return new SaasCustomErrorDecoder();
	}
	
}

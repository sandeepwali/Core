package com.solumesl.aims.saas.adapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Configuration
@Getter
@Setter
public class SaasServerClientConfig {
	
	@Value("${solum.saas.server.username:}")
	private String username;
	@Value("${solum.saas.server.password:}")
	private String password;
	@Value("${solum.saas.server.accesstoken.url:}")
	private String accessTokenUrl;
	@Value("${solum.saas.server.refreshtoken.url:}")
	private String refreshTokenUrl;

 


}

package com.solumesl.aims.saas.adapter.Interceptor;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.solumesl.aims.saas.adapter.config.SaasServerClientConfig;
import com.solumesl.aims.saas.adapter.store.Store;

import feign.RequestInterceptor;
import feign.RequestTemplate;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public class SaasFeignClientInterceptor implements RequestInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(SaasFeignClientInterceptor.class);
	
	private static final String AUTHORIZATION_HEADER="Authorization";
	private static final String TOKEN_TYPE = "Bearer";
	@Autowired
	private  Store store;
	@Autowired
	private  SaasServerClientConfig saasServerClientConfig;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		if(isAuthorizationRequest(requestTemplate)) {
			logger.info("Auth flow, hence skipping interceptor");
		}else if(store.hasToken()){
			requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, store.getAccessToken()));
		}else {
			logger.info("Token Not Present");//Add flow to get token
		}

	}
	private boolean isAuthorizationRequest(RequestTemplate requestTemplate) {
		Objects.requireNonNull(requestTemplate);
		return requestTemplate.url().contains(saasServerClientConfig.getAccessTokenUrl()) || requestTemplate.url().contains(saasServerClientConfig.getRefreshTokenUrl()) ;
	}
}
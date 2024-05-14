package com.solumesl.aims.saas.adapter.service.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.solumesl.aims.saas.adapter.config.SaasServerClientConfig;
import com.solumesl.aims.saas.adapter.exception.AccessToken401UnauthorizedException;
import com.solumesl.aims.saas.adapter.model.AuthResponse;
import com.solumesl.aims.saas.adapter.model.User;
import com.solumesl.aims.saas.adapter.rest.SaasRestClient;
import com.solumesl.aims.saas.adapter.service.SaasAbstractService;
import com.solumesl.aims.saas.adapter.store.Store;

import feign.FeignException.MethodNotAllowed;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Service
@Primary
public class SaasServiceAccountLoginService extends SaasAbstractService {
	
	@Autowired
	public SaasServiceAccountLoginService(SaasRestClient saasRestClient, Store store,
			SaasServerClientConfig saasServerClientConfig) {
		super(saasRestClient, store, saasServerClientConfig);
	}

	private static Logger logger = LoggerFactory.getLogger(SaasServiceAccountLoginService.class);
	private void loadTokenByServiceAccount() {
		AuthResponse authToken = saasRestClient.getAuthToken(new User(saasServerClientConfig));
		store.assignToken(authToken);
	}

	private void loadAccessTokenByRefreshToken() {
		AuthResponse authToken = saasRestClient.getAuthTokenByRefreshToken(store.getRefreshToken());
		store.assignToken(authToken);
	}

	
	public synchronized boolean loadToken() {
		try {
			if(!store.isRetryAllowed())return false;
			
			if(  store.hasToken()) {
				loadAccessTokenByRefreshToken();
				return true;
			}else {
				loadTokenByServiceAccount();
				return true;
			}
		} catch (Exception e) {//assumes refresh token is expired and retry
			if(e.getCause() instanceof AccessToken401UnauthorizedException) {
				if(store.hasToken())loadTokenByServiceAccount();
			}else if(e.getCause() instanceof MethodNotAllowed) {
				logger.error("Service Acc Credentails Expired ");
				store.stopRetry();
				throw e;
			}
		}
		return false;
	}

}

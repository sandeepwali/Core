package com.solumesl.aims.saas.adapter.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solumesl.aims.saas.adapter.model.AuthResponse;
import com.solumesl.aims.saas.adapter.model.RefreshToken;
import com.solumesl.aims.saas.adapter.util.SolumSaasUtil;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public class SaasTokenStore implements Store{
	private static Logger logger = LoggerFactory.getLogger(SaasTokenStore.class);

	private AuthResponse token;

	private boolean isRetryAllowed = true;
	@Override
	public synchronized  void assignToken(AuthResponse token) {
		this.token = token;
		logger.info("New Token Assigned");
		logger.debug("token-->"+token);

	}

	@Override
	public boolean hasToken() {
		return SolumSaasUtil.isNotEmpty(token) && SolumSaasUtil.isNotEmpty(token.getResponseMessage()) && SolumSaasUtil.isNotEmpty(token.getResponseMessage().getAccessToken());
	}


	@Override
	public String getAccessToken() {
		return hasToken()? token.getResponseMessage().getAccessToken(): null;
	}

	public RefreshToken getRefreshToken() {
		return hasToken() ?  new RefreshToken(token.getResponseMessage().getRefreshToken()): null;
	}

	@Override
	public boolean isRetryAllowed() {
		return isRetryAllowed;
	}

	@Override
	public void stopRetry() {
		this.isRetryAllowed = false;
		
	}
}

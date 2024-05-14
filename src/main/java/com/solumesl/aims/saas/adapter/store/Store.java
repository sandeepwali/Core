package com.solumesl.aims.saas.adapter.store;

import com.solumesl.aims.saas.adapter.model.AuthResponse;
import com.solumesl.aims.saas.adapter.model.RefreshToken;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public interface Store {

	boolean hasToken();
	String getAccessToken();
	RefreshToken getRefreshToken();
	void assignToken(AuthResponse token);
	boolean isRetryAllowed();
	void stopRetry();
}

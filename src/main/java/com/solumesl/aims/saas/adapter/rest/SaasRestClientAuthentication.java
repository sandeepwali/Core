package com.solumesl.aims.saas.adapter.rest;

import org.springframework.web.bind.annotation.PostMapping;

import com.solumesl.aims.saas.adapter.model.AuthResponse;
import com.solumesl.aims.saas.adapter.model.RefreshToken;
import com.solumesl.aims.saas.adapter.model.User;

import feign.Headers;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Headers("Content-Type: application/json")
public interface SaasRestClientAuthentication {
	
	
	@PostMapping(value = "${solum.saas.server.accesstoken.url}")
	AuthResponse getAuthToken(User user);
	
	@PostMapping(value = "${solum.saas.server.refreshtoken.url}")
	AuthResponse getAuthTokenByRefreshToken(RefreshToken refreshToken);
}

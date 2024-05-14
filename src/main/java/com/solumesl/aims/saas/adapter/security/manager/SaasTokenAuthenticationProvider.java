package com.solumesl.aims.saas.adapter.security.manager;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.solumesl.aims.saas.adapter.constants.AzureConstants;
import com.solumesl.aims.saas.adapter.exception.UnderPriviliegedUserException;
import com.solumesl.aims.saas.adapter.security.TokenAuthorizationService;
@Component
public class SaasTokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	@Autowired
	private  TokenAuthorizationService tokenAuthService;
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if(!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("1"))) {
			throw new UnderPriviliegedUserException("Access Denied for this Account, under priviliged user");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		  Object token = authentication.getCredentials();
			
	        return Optional
	                .ofNullable(token)
	                .map(String::valueOf)
	                .flatMap(a->{
	                	 Map<String, Object> data = tokenAuthService.findByToken(a).orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
	                	authentication.setDetails(data);
	                	 return  Optional.ofNullable(new User((String)data.get(AzureConstants.OID),(String) data.get(AzureConstants.CUSTOMER_CODE), 
	                			 true, true, true, true, AuthorityUtils.createAuthorityList((String) data.get(AzureConstants.CUSTOMER_LEVEL))));
	                })
	                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
	    }
	}


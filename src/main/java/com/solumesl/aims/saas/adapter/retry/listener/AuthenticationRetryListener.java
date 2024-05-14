package com.solumesl.aims.saas.adapter.retry.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

import com.solumesl.aims.saas.adapter.exception.AccessToken401UnauthorizedException;
import com.solumesl.aims.saas.adapter.service.authentication.SaasServiceAccountLoginService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AuthenticationRetryListener implements RetryListener {

	@Autowired
	protected  SaasServiceAccountLoginService saasLoginService;
	@Override
	public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
		return true;
	}
	@Override
	public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback,
			Throwable throwable) {

	}
	@Override
	public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
			Throwable throwable) {
		try {
			if(context.getLastThrowable().getCause() instanceof AccessToken401UnauthorizedException)
				saasLoginService.loadToken();
		} catch (Exception e) {
			log.error("Error->{}",e);
		}
	}




}

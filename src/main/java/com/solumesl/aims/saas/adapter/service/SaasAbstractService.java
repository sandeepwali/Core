package com.solumesl.aims.saas.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.solumesl.aims.saas.adapter.config.SaasServerClientConfig;
import com.solumesl.aims.saas.adapter.rest.SaasRestClient;
import com.solumesl.aims.saas.adapter.store.Store;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public abstract class SaasAbstractService extends MessageService {
	
	public SaasAbstractService(SaasRestClient saasRestClient, Store store,
			SaasServerClientConfig saasServerClientConfig) {
		super();
		this.saasRestClient = saasRestClient;
		this.store = store;
		this.saasServerClientConfig = saasServerClientConfig;
	}
	
	 
	protected  SaasRestClient saasRestClient;
	 
	protected  Store store;
	 
	protected  SaasServerClientConfig saasServerClientConfig;
	
}

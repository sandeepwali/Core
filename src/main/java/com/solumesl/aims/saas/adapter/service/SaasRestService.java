
package com.solumesl.aims.saas.adapter.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.solumesl.aims.saas.adapter.rest.SaasRestClient;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Service
@Retryable(maxAttempts = 2)
@Async
@Slf4j
public class SaasRestService  {
	@Autowired
	protected  SaasRestClient saasRestClient;

	private static Logger logger = LoggerFactory.getLogger(SaasRestService.class);


	public CompletableFuture<?> uploadArticles(String company, String store, List<?> metaList) {
		Object  result = null;
		logger.info("Triggering uploadArticles with Payload "+ metaList.size()  );
		result =  this.saasRestClient.uploadArticles(metaList,company,store );
		return CompletableFuture.completedFuture(result);
	}
	public CompletableFuture<?> getCompany() {
		Object result =  this.saasRestClient.getCompanyDetails();
		return CompletableFuture.completedFuture(result);
	}

	public CompletableFuture<?> getStore(String company, Map<String, String> queryMap) {
		 
		Object result =  this.saasRestClient.getStore(company, queryMap);
		return CompletableFuture.completedFuture(result);
	}
	public CompletableFuture<?> getStoreSummary(String company, String store) {
		Object result =  this.saasRestClient.getStoreSummary(company, store);
		return CompletableFuture.completedFuture(result);
	}
	public CompletableFuture<?> getArticles(String company,String store, Map<String, String> queryMap) {
		Object result =  this.saasRestClient.getArticles(company,store, queryMap);
		return CompletableFuture.completedFuture(result);
	}

	public CompletableFuture<?> getArticlesCount(String company,String store, Map<String, String> queryMap) {

		Object	result =  this.saasRestClient.getArticleCount(company,store, queryMap);
		return CompletableFuture.completedFuture(result);
	}

	public CompletableFuture<?> getArticleFormat(Map<String, String> queryMap) {


		Object	result = this.saasRestClient.getArticleFormat(queryMap);

		return CompletableFuture.completedFuture(result);

	}
	
	public CompletableFuture<?> articlePushByZipFile(String company, Object data){
		Object	result = this.saasRestClient.uploadArticlesZip(data,company);
		return CompletableFuture.completedFuture(result);
	}
	 
	
}
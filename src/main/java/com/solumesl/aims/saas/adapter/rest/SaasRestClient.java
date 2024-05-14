package com.solumesl.aims.saas.adapter.rest;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.solumesl.aims.saas.adapter.config.FeignSaasConfig;

/**
 * 
 * @author baskarmohanasundaram
 *
 */
@FeignClient(name="SaasRestClient", url= "${solum.saas.server.url:https://eu.common.solumesl.com}", configuration= FeignSaasConfig.class)
public interface SaasRestClient extends SaasRestClientAuthentication{
	
	@GetMapping("common/api/v2/common/version")
	Object getVersion();
	@GetMapping("common/api/v2/common/company/details")
	Object getCompanyDetails();
	@GetMapping("common/api/v2/common/articles/upload/format")
	Object getArticleFormat(@SpringQueryMap Map<String, String> options);
	@GetMapping("common/api/v2/common/store")
	Object getStore(@RequestParam(value="company") String company , @SpringQueryMap Map<String, String> options);
	@GetMapping("common/api/v2/common/articles")
	Object getArticleCount(@RequestParam(value="company") String company ,@RequestParam(value="store") String store , @SpringQueryMap Map<String, String> options);
	@GetMapping("common/api/v2/common/config/article/info")
	Object getArticles(@RequestParam(value="company") String company ,@RequestParam(value="store") String store , @SpringQueryMap Map<String, String> options);
	@PostMapping("common/api/v2/common/articles")
	Object uploadArticles( List<?> metaList,@RequestParam(value="company") String company,@RequestParam(value="store") String store);
	
	@PostMapping("common/api/v2/common/articles/upload/zip")
	Object uploadArticlesZip( Object articleUpload ,@RequestParam(value="company") String company);
	
	@GetMapping("common/api/v2/common/store/summary")
	Object getStoreSummary(@RequestParam(value="company") String company , @RequestParam(value="store") String store);
}

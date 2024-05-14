package com.solumesl.aims.saas.adapter.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.solumesl.aims.saas.adapter.entity.job.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ClientResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonView(View.Admin.class)
	private String responseCode;
	@JsonView(View.Admin.class)
	private String message;
	@JsonView(View.Admin.class)
	private Object additionalInfo;

	public ClientResponse(String responseCode, String message) {
		super();
		this.responseCode = responseCode;
		this.message = message;
	}


	public ClientResponse(String responseCode, String message, Object additionalInfo) {
		super();
		this.responseCode = responseCode;
		this.message = message;
		this.additionalInfo = additionalInfo;
	}



}

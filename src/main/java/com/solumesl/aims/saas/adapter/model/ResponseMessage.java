package com.solumesl.aims.saas.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseMessage {
	@JsonProperty("access_token")
	public String accessToken;
	@JsonProperty("token_type")
	public String tokenType;
	@JsonProperty("expires_in")
	public int expiresIn;
	@JsonProperty("refresh_token")
	public String refreshToken;
}

package com.solumesl.aims.saas.adapter.model;

import com.solumesl.aims.saas.adapter.config.SaasServerClientConfig;
import com.solumesl.aims.saas.adapter.security.SaasSecurity;

import lombok.Data;

@Data
public class User {
	private String username;
	private String password;
	
	public User(SaasServerClientConfig user) {
		this.username = user.getUsername();
		this.password = SaasSecurity.decrypt(user.getPassword());
	}

}

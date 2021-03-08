package com.networth.pojo;

import lombok.Data;

@Data
public class AuthenticateRequest {

	private String username;
	private String password;

	public AuthenticateRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AuthenticateRequest() {
		super();
	}
}

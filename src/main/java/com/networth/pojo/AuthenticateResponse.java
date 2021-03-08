package com.networth.pojo;

import lombok.Data;

@Data
public class AuthenticateResponse {

	private String token;

	public AuthenticateResponse(String token) {
		super();
		this.token = token;
	}

	public AuthenticateResponse() {
		super();
	}
}

package com.networth.configuration;

import java.util.Base64;
import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class MyAuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

	UserDetails userDetails;

	MyAuthenticationProvider(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	MyAuthenticationProvider() {
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("MyAuthenticationProvider:  "+userDetails);
		String token = (String) authentication.getPrincipal();
		token = getDecodedToken(token);
		if (Objects.equals(token, userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(token, null, null);
		}
		throw new BadCredentialsException("Invalid Token Provided");
	}

	private String getDecodedToken(String token) {
		String decodedToken = null;
		if (Objects.nonNull(token) && token.startsWith("BEARER")) {
			token = token.replace("BEARER", "").trim();
			decodedToken = new String(Base64.getDecoder().decode(token));
		}
		return decodedToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}

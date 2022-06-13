package com.riu.users.infraestructure.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = 1L;
	private final String token;

	public AuthenticationToken(String token) {
		super(null);
		this.token = token;
		setAuthenticated(true);
	}


	@Override
	public Object getCredentials() {
		return getToken();
	}

	@Override
	public Object getPrincipal() {
		return getToken();
	}

	public String getToken() {
		return token;
	}



	
	

}
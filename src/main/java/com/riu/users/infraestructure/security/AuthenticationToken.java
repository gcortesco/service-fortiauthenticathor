package com.riu.users.infraestructure.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = 1L;
	private final String token;
	private final String clientId;

	public AuthenticationToken(String token,String clientId) {
		super(null);
		this.token = token;
		this.clientId = clientId;
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


	public String getClientId() {
		return clientId;
	}
	
	

}
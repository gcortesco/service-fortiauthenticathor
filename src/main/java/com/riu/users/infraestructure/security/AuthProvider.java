package com.riu.users.infraestructure.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


/**
 * This class is responsible for checking the token.
 */
public class AuthProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(AuthProvider.class);

	private String authURL;
	//private String clientID;

	public AuthProvider(String authURL, String clientID) {
		this.authURL = authURL;
		//this.clientID = clientID;
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		final AuthenticationToken tokenContainer = (AuthenticationToken) auth;
		final String token = tokenContainer.getToken();
		final String clientID = tokenContainer.getClientId();
		if (verifyToken(token,clientID))
			return auth;
		return null;
	}

	private Boolean verifyToken(String token,String clientID) {
		RestTemplate restTemplate = (new RestTemplateBuilder()).defaultHeader("Authorization", token)
				.setConnectTimeout(Duration.ofMillis(3000L)).build();
		try {
			LOGGER.debug("DEBUG VALIDATE TOKEN");
			LOGGER.debug(token);
			ResponseEntity<String> response = restTemplate.getForEntity(authURL + clientID, String.class);
			LOGGER.debug(response.toString());
			return response.getStatusCode().is2xxSuccessful();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// this class is only responsible for AuthTokenContainers
		return AuthenticationToken.class.isAssignableFrom(authentication);
	}
}

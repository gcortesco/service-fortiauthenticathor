package com.riu.users.infraestructure.security;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestTemplate;

import org.apache.http.impl.client.HttpClientBuilder;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;


/**
 * This class is responsible for checking the token.
 */
public class AuthProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(AuthProvider.class);

	private String authURL;


	public AuthProvider(String authURL) {
		this.authURL = authURL;

	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		final AuthenticationToken tokenContainer = (AuthenticationToken) auth;
		final String token = tokenContainer.getToken();
		if (verifyToken(token))
			return auth;
		return null;
	}

	private Boolean verifyToken(String token) {
		RestTemplate restTemplate = (new RestTemplateBuilder()).defaultHeader("Authorization", token)
				.setConnectTimeout(Duration.ofMillis(3000L)).build();
		customize(restTemplate);
		try {
			LOGGER.debug("DEBUG VALIDATE TOKEN");
			LOGGER.debug(token);
			ResponseEntity<String> response = restTemplate.getForEntity(authURL, String.class);
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

	private void customize(RestTemplate restTemplate) {

		final SSLContext sslContext;
		try {
			SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
			KeyStore trustStore = keyStore("classpath:fortiautenticator.p12", "password");
			sslContextBuilder.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy());
			sslContextBuilder.loadKeyMaterial(trustStore, "password".toCharArray());
			sslContext = sslContextBuilder.build();

		} catch (Exception e) {
			throw new IllegalStateException(
					"Failed to setup client SSL context", e
			);
		}

		final HttpClient httpClient = HttpClients.custom().setSslcontext(sslContext).build();
		final ClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory(httpClient);
		restTemplate.setRequestFactory(requestFactory);
	}

	private KeyStore keyStore(String file, String password) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		InputStream in = getClass().getResourceAsStream("/fortiautenticator.p12");
		keyStore.load(in, password.toCharArray());
		return keyStore;
	}

}

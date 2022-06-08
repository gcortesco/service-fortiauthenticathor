package com.riu.users.infraestructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class AuthFilter extends AbstractAuthenticationProcessingFilter {

	public static final String TOKEN_HEADER = "Authorization";
	public static final String CLIENT_ID_HEADER = "client_id";

	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
	private static final String clientID="6bMDbv5yVvpn1Y3AbNl4GFrm10ER7U3qX8oFNNLw";


	public AuthFilter(RequestMatcher requestMatcher) {
		super(requestMatcher);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		this.setAuthenticationSuccessHandler((request1, response1, authentication) -> {
			chain.doFilter(request1, response1);
		});

		super.doFilter(request, response, chain);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			  throws AuthenticationException, IOException, ServletException {

		final String tokenValue = getTokenValue(request);
		final String clientId = getClientIdValue(request);


		AuthenticationToken token = new AuthenticationToken(tokenValue,clientId);
		token.setDetails(authenticationDetailsSource.buildDetails(request));

		return this.getAuthenticationManager().authenticate(token);
	}

	private String getTokenValue(HttpServletRequest req) {
		//find the header which contains our token (ignore the header-name case)
		return Collections.list(req.getHeaderNames()).stream()
				  .filter(header -> header.equalsIgnoreCase(TOKEN_HEADER))
				  .map(header -> req.getHeader(header))
				  .findFirst()
				  .orElse(null);
	}
	
	private String getClientIdValue(HttpServletRequest req) {
		//find the header which contains our token (ignore the header-name case)
		return Collections.list(req.getHeaderNames()).stream()
				  .filter(header -> header.equalsIgnoreCase(CLIENT_ID_HEADER))
				  .map(header -> req.getHeader(header))
				  .findFirst()
				  .orElse(clientID);
	}
}
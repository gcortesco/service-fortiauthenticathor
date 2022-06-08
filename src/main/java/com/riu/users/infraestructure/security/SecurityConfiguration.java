package com.riu.users.infraestructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${spring.security.oauth2.resourceserver.opaquetoken.introspection-uri}")
	private String authURL;
	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
	private String clientID;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable()
			.addFilterAfter(createCustomFilter(),AnonymousAuthenticationFilter.class)
		  ;
	}

	protected AbstractAuthenticationProcessingFilter createCustomFilter() throws Exception {
		AuthFilter filter = new AuthFilter(
				new AndRequestMatcher(
						new AntPathRequestMatcher("/secured/**"))
		);
		filter.setAuthenticationManager(authenticationManagerBean());
		return filter;
	}
	
	@Bean
	public AuthenticationProvider createCustomAuthenticationProvider() {
		return new AuthProvider(authURL,clientID);
	}
}
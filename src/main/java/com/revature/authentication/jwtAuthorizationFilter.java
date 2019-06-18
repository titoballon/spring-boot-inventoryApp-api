package com.revature.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class jwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private jwtTokenProvider tokenProvider;
	
	public jwtAuthorizationFilter(AuthenticationManager authenticationManager
			,jwtTokenProvider tokenProvider) {
		super(authenticationManager);
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain)
			throws IOException, ServletException {
		
		Authentication authentication = tokenProvider.getAuthentication(request);
		if(authentication != null && tokenProvider.validateToken(request)) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}
	

}

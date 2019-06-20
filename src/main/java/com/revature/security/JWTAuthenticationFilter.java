package com.revature.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.repositories.UserRepository;

//import com.revature.models.User;
import org.springframework.security.core.userdetails.User;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.revature.security.SecurityConstants.EXPIRATION_TIME;
import static com.revature.security.SecurityConstants.HEADER_STRING;
import static com.revature.security.SecurityConstants.SECRET;
import static com.revature.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	//private UserRepository userRepository;
	
	//@Autowired
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	        //this.userRepository = userRepository;
	    }
	 
	 @Override
	    public Authentication attemptAuthentication(HttpServletRequest req,
	                                                HttpServletResponse res) throws AuthenticationException {
	        try {
	        	
	        	com.revature.models.User creds = new ObjectMapper()
	                    .readValue(req.getInputStream(), com.revature.models.User.class);

	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            creds.getUsername(),
	                            creds.getPassword(),
	                            new ArrayList<>())
	            );
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {
		 //System.out.println(userRepository.findByUsername(((User) auth.getPrincipal()).getUsername()));
		 //System.out.println(((User) auth.getPrincipal()).getUsername());
		 String token = JWT.create()
	                .withSubject(((User) auth.getPrincipal()).getUsername())
	                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .sign(HMAC512(SECRET.getBytes()));
	        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	        res.addHeader("username", ((User) auth.getPrincipal()).getUsername());
	    }
}

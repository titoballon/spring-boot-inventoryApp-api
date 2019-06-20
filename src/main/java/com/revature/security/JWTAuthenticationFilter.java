package com.revature.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

//@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	//@Autowired
	//private UserRepository userRepository;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
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
		 //System.out.println(getUser(((User) auth.getPrincipal()).getUsername()));
		 String username = ((User) auth.getPrincipal()).getUsername();
		 String token = JWT.create()
	                .withSubject(((User) auth.getPrincipal()).getUsername())
	                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .sign(HMAC512(SECRET.getBytes()));
		 
		 PrintWriter out = res.getWriter();
		 res.setContentType("application/json");
		 res.setCharacterEncoding("UTF-8");
		 out.print("{\"Authorization\":\"" + TOKEN_PREFIX + token + "\", \"username\":\"" + username +  "\"}");
		 out.flush();
        //res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        //res.addHeader("username", ((User) auth.getPrincipal()).getUsername());
	    }
}

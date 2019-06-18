package com.revature.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.authentication.jwtTokenProvider;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
public class AuthenticationController {
	
	
	private jwtTokenProvider tokenProvider;
	
	
	private UserService userService;
	
	@Autowired
	public AuthenticationController(UserService userService,jwtTokenProvider tokenProvider ) {
		super();
		this.userService = userService;
		this.tokenProvider = tokenProvider;
	}

	@GetMapping("api/user/login")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getUser(Authentication principal) throws UnsupportedEncodingException{
		
		if(principal == null) {
			return ResponseEntity.ok(principal);
		}
		
		UsernamePasswordAuthenticationToken authenticationToken =
				(UsernamePasswordAuthenticationToken) principal;
		
		String username = authenticationToken.getName();
		
		User user = userService.findByUsername(username).get(0);
		
		user.setToken(tokenProvider.generateToken(authenticationToken));
		
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

}

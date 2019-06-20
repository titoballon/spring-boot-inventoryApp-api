package com.revature.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.authentication.AuthenticationModelUser;
import com.revature.authentication.jwtTokenProvider;
import com.revature.exceptions.ApiException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;

@RestController
public class AuthenticationController {
	
	 
	private jwtTokenProvider tokenProvider;
	
	
	private UserService userService;
	private UserRepository userRepository;
	

	@Autowired
	public AuthenticationController(UserService userService,jwtTokenProvider tokenProvider,UserRepository ur ) {
		super();
		this.userRepository = ur;
		this.userService = userService;
		this.tokenProvider = tokenProvider;
	}

	@GetMapping("/api/user/login")
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
	@PostMapping("register")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<User> registerUser(@Valid @RequestBody AuthenticationModelUser userRaw) {

		
		
		if(		userRaw.getUsername()  == null
			||  userRaw.getPassword()  == null
			||  userRaw.getEmail()     == null 
			||  userRaw.getFirstname() == null
			||  userRaw.getLastname()  == null){
				throw new ApiException(HttpStatus.BAD_REQUEST, "Please fill all the fields");

		}
		Boolean userDataBase = userRepository.findByUsername(userRaw.getUsername()).isEmpty();
		if(!userDataBase){
			throw new ApiException(HttpStatus.BAD_REQUEST, "Username already exist");
		}
		userDataBase = userRepository.findByEmail(userRaw.getEmail()).isEmpty();

		if(!userDataBase){
			throw new ApiException(HttpStatus.BAD_REQUEST, "Email already exist");
		}

		User userCreated = 	new User(userRaw.getUsername(),
		userRaw.getPassword(), 
		userRaw.getEmail(), 
		userRaw.getFirstname(), 
		userRaw.getLastname(),
		new String[] {"ROLE_USER"});
		this.userService.save(userCreated);


		return new ResponseEntity<User>(userCreated, HttpStatus.OK);
	}

}

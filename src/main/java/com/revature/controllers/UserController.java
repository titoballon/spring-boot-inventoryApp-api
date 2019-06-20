package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.ApiException;
import com.revature.models.Area;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.NullPropertiesHandler;

@RestController
@RequestMapping("users")
public class UserController {
	
	private UserService userService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@GetMapping
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("username/{username}")
	public User findByUsername(@PathVariable String username){
		//System.out.println(userService.getByUsername(username));
		return userService.findByUsername(username);
	}
	
	@GetMapping("{id}")
	public User findById(@PathVariable Integer id) {
		return userService.findById(id);
	}
	
	@PostMapping
	public User save(@Valid @RequestBody User user) {
		return userService.save(user);
	}
	
	@PostMapping("/register")
    public void signUp(@RequestBody User user) {
		Optional<User> usr = userService.getByUsername(user.getUsername());
		if(usr.isPresent()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "username already in use");
		}		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		System.out.println(user);
//		System.out.println(userService.save(user));
        userService.save(user);
    }
	
//	@PostMapping("/login")
//    public User login(@RequestBody User user) {
//		System.out.println(user);
//        return userService.save(user);
//    }
	
	@PatchMapping
	public User updateUser(@Valid @RequestBody User user) {
		User existingUser = userService.getOne(user.getId());
		NullPropertiesHandler.myCopyProperties(user, existingUser);
		return userService.save(existingUser);	
	}
	
//	@DeleteMapping
//	public void delete(@Valid @RequestBody User user) {
//		userService.delete(user);
//	}
	
	@DeleteMapping("{userId}")
	public void deleteById(@PathVariable Integer userId) {
		userService.deleteById(userId);
	}
}

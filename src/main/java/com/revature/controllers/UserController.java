package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	private UserService userService;

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("username/{username}")
	public List<User> findByUsername(@PathVariable String username){
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
	
	@PatchMapping
	public User updateUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}
	
	@DeleteMapping("{userId}")
	public void delete(@PathVariable Integer userId) {
		userService.delete(userId);
	}
}

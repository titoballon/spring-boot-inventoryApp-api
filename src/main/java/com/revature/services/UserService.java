package com.revature.services;

import java.util.List;

import com.revature.models.User;

public interface UserService {
	
	public User findByUsername(String username);
	
	public User findById(Integer id);
	
	public User getOne(Integer id);
	
	public List<User> findAll();
	
	public User save(User user);
	
	public void delete(User user);
	
	public void deleteById(Integer id);
}

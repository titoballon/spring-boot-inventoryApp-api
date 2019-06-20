package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findByUsername(String username);
	
	public List<User> findByEmail(String email);
}

package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);
	
	public Optional<User> getByUsername (String login);
	
	//void save(User user);
}

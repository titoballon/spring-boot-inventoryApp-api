package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ApiException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository ur) {
		this.userRepository = ur;
	}

	@Override
	public List<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findByUsername(username);
		if(users.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No users found");
		return users;
	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<User> res = userRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, "user not found");
		}
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> allUsers = userRepository.findAll();
		if(allUsers.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "No users found");
		return allUsers;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void delete(Integer userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}
}

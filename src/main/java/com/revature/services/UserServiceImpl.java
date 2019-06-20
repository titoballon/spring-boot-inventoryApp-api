package com.revature.services;

import static java.util.Collections.emptyList;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.User;

import com.revature.exceptions.ApiException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private UserRepository userRepository;
	
	//private List<User> registeredUsers = new LinkedList<>();
	
	@Autowired
	public UserServiceImpl(UserRepository ur) {
		this.userRepository = ur;
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        //return new User(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstname(), user.getLastname(), emptyList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
    }
	
	public Optional<User> getByUsername(String login) {
        return findAll().stream()
                .filter(user -> user.getUsername().equals(login))
                .findFirst();
    }

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
//		List<User> users = userRepository.findByUsername(username);
//		if(users.isEmpty())
//			throw new ApiException(HttpStatus.NOT_FOUND, "No users found");
//		return users;

		return userRepository.findByUsername(username);
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

//	@Override
//	public void delete(User user) {
//		// TODO Auto-generated method stub
//		userRepository.delete(user);
//	}

	@Override
	public User getOne(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.getOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}
}

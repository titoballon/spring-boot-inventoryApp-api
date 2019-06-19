package com.revature.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.revature.models.User;
import com.revature.repositories.UserRepository;


@Component
public class DatabaseLoader implements ApplicationRunner{

	@Autowired
	private final UserRepository userdao;
	
	public DatabaseLoader(UserRepository userdao) {
		super();
		this.userdao = userdao;
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
			
		//userdao.save(new User("titoballon2","pass","my email","ernesto","ballon",new String[] {"ROLE_USER"}));
		
		System.out.println("this is running");
	}

}

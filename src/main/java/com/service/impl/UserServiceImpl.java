package com.service.impl;

import com.entity.User;
import com.repository.UserRepository;
import com.repository.impl.UserRepositoryImpl;
import com.service.UserService;

public class UserServiceImpl implements UserService{

	private UserRepository userRepository=new UserRepositoryImpl();
	
	
	@Override
	public User login(String username, String password) {
		
		return userRepository.login(username, password);
	}

}

package com.xsx.ncd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.User;
import com.xsx.ncd.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User userLoginService(String account, String password){
		
		User user = null;
		
		if((account == null) || (password == null))
			return null;
		
		user = userRepository.findByAccountAndPassword(account, password);

		return user;
	}
}

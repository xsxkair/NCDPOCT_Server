package com.xsx.ncd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.User;
import com.xsx.ncd.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findUserByAccountAndPasswordService(String account, String password){
		
		User user = null;
		
		if((account == null) || (password == null))
		{
			return null;
		}
		
		user = userRepository.findByAccountAndPassword(account, password);
		if(user == null)
			System.out.println("��");
		else
			System.out.println("�ǿ�");
		return user;
	}
	
	public User saveUserService(User user){
		return userRepository.save(user);
	}
	
	public Boolean deleteUserService(User user){
		if(user.getId() == null)
			return false;
		else{
			userRepository.delete(user.getId());
			
			return true;
		}
	}
	
	public Boolean checkUserIsExistService(String account){
		if(userRepository.readUserIdByAccount(account) == null)
			return false;
		else
			return true;
	}
	
	public List<User> readAllUserButOneUserService(User user){
		return userRepository.readAllUserButOneUser(user.getAccount());
	}
	
	public List<User> readAllUserService(){
		return userRepository.findAll();
	}
}

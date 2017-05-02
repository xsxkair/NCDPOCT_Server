package com.xsx.ncd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.User;
import com.xsx.ncd.service.UserService;

@Controller
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/Login")
	public User readUserByPasswordHandler(@RequestBody User user) {
		return userService.findUserByAccountAndPasswordService(user.getAccount(), user.getPassword());
	}
	
	@ResponseBody
	@RequestMapping(value="/SaveUser")
	public User saveUserHandler(@RequestBody User user) {
		return userService.saveUserService(user);
	}
	
	@ResponseBody
	@RequestMapping(value="/DeleteUser")
	public Boolean deleteUserHandler(@RequestBody User user) {
		return userService.deleteUserService(user);
	}
	
	@ResponseBody
	@RequestMapping(value="/CheckUserIsExist")
	public Boolean checkUserIsExistHandler(@RequestBody User user) {
		return userService.checkUserIsExistService(user.getAccount());
	}
	
	@ResponseBody
	@RequestMapping(value="/ReadAllUser")
	public List<User> readAllUsersHandler(@RequestBody User user) {
		return userService.readAllUserButOneUserService(user);
	}
}
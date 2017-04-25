package com.xsx.ncd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public User userLoginHandler(String account, String password) {
		return userService.userLoginService(account, password);
	}
}
package com.citycup.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citycup.service.UserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dql
 * @since 2023-03-22
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public Map<Boolean, String> register(String id,String name,String password){
		return userService.register(id, password, name);
	}
	
	@PostMapping("/login")
	public Map<Boolean, String> login(String id,String password){
		return userService.login(id, password);
	}
	
	@PostMapping("/logout")
	public Map<Boolean, String> logout(String id){
		return userService.logout(id);
	}
}

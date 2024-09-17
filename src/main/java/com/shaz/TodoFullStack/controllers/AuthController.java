package com.shaz.TodoFullStack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaz.TodoFullStack.dto.LoginRequest;
import com.shaz.TodoFullStack.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String authenticate(@RequestBody LoginRequest loginRequest)
	{
		System.out.println(loginRequest.getUsername());
		System.out.println(loginRequest.getPassword());
		
		return userService.verify(loginRequest);
		
	}

}

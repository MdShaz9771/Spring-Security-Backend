package com.shaz.TodoFullStack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.shaz.TodoFullStack.dto.LoginRequest;

@Service
public class UserService
{
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	public String verify(LoginRequest loginRequest)
	{
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
				loginRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(loginRequest.getUsername());
		}
		return null;
	}

}

package com.shaz.TodoFullStack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
	public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginRequest)
	{
		try
		{
			String token = userService.verify(loginRequest);
			return ResponseEntity.ok(token);
		} catch (BadCredentialsException e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
		} catch (Exception e) {
			return ResponseEntity.
					status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error. Please try again after some time");
		}
		
	}

}

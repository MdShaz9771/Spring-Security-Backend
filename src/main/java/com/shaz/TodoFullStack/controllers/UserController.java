package com.shaz.TodoFullStack.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController
{
	@GetMapping("/home")
	public String home()
	{
		return "This is the home page";
	}

	@GetMapping("/me")
	public UserDetailsResponse getLoggedInUser(Authentication authentication)
	{
		// The authentication object will be injected automatically by Spring Security
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
		{
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			// Return the user details in the response
			return new UserDetailsResponse(userDetails.getUsername(), userDetails.getAuthorities().toString());
		}

		// Return a default response if no user is logged in (optional handling)
		return new UserDetailsResponse("Anonymous", "No roles");
	}

	// DTO class for user details response
	static class UserDetailsResponse
	{
		private String username;
		private String roles;

		public UserDetailsResponse(String username, String roles)
		{
			this.username = username;
			this.roles = roles;
		}

		public String getUsername()
		{
			return username;
		}

		public String getRoles()
		{
			return roles;
		}
	}

}

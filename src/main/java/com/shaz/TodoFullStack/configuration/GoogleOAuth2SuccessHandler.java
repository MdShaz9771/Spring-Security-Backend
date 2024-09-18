package com.shaz.TodoFullStack.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.shaz.TodoFullStack.model.User;
import com.shaz.TodoFullStack.repo.UserRepo;
import com.shaz.TodoFullStack.service.JwtService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GoogleOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{
	private final JwtService jwtService;
	private final UserRepo userRepo;

	public GoogleOAuth2SuccessHandler(JwtService jwtService, UserRepo userRepo)
	{
		this.jwtService = jwtService;
		this.userRepo = userRepo;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, java.io.IOException
	{
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		String email = oAuth2User.getAttribute("email");

		// Check if the user exists in the database
		User user = userRepo.findByUsername(email);
		if (user == null)
		{
			// Register new user if not found
			user = new User();
			user.setUsername(email);
			user.setAuthProvider("google");
			userRepo.save(user);
		}

		// Generate JWT token
		String jwtToken = jwtService.generateGoogleToken(user.getUsername());
		System.out.println(jwtToken);

		// Redirect the user to the frontend with the JWT token
		response.sendRedirect("http://localhost:3000/oauth2-success?token=" + jwtToken);

	}
}

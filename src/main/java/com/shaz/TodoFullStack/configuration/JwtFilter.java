package com.shaz.TodoFullStack.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shaz.TodoFullStack.service.JwtService;
import com.shaz.TodoFullStack.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter
{
	@Autowired
	private JwtService jwtService;
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (authHeader != null && authHeader.startsWith("Bearer "))
		{
			token = authHeader.substring(7);
			
			
			username = jwtService.extractUserName(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
//            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

			if (jwtService.validateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);

	}

}

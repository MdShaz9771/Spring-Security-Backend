package com.shaz.TodoFullStack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shaz.TodoFullStack.model.MyUserDetails;
import com.shaz.TodoFullStack.model.User;
import com.shaz.TodoFullStack.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserRepo userRepo;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepo.findByUsername(username);
		if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
            
        }else if (!"local".equals(user.getAuthProvider())) {
        	throw new BadCredentialsException("Use different login method");
			
		}
        
        return new MyUserDetails(user);
	}
	
	public UserDetails loadUserByGoogleUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepo.findByUsername(username);
		if (user == null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("user not found");
			
		}else if ( !"google".equals(user.getAuthProvider())) {
			throw new BadCredentialsException("User different login method");
			
		}
		
		return new MyUserDetails(user);
	}

	

}

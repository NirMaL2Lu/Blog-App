package com.nirmal.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nirmal.blog.entity.User;
import com.nirmal.blog.exceptions.ResourceNotFoundException;
import com.nirmal.blog.repos.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user from database 
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("Username", "Email" + username, 0));
		System.out.println("user :" + user);
		return user;
	}

}

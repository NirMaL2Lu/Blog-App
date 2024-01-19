package com.nirmal.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirmal.blog.exceptions.ApiException;
import com.nirmal.blog.payloads.JwtAuthRequest;
import com.nirmal.blog.payloads.JwtAuthResponse;
import com.nirmal.blog.payloads.UserDto;
import com.nirmal.blog.security.JwtTokenHelper;
import com.nirmal.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest authRequest) throws Exception{
		
		this.authenticate(authRequest.getUserName(),authRequest.getPassword());
		
		
		//get username and password
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequest.getUserName());
		
		//generate token
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		//send token 
		
		JwtAuthResponse authResponse = new JwtAuthResponse();
		
		authResponse.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK);
	}


	private void authenticate(String userName, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken =
				 new UsernamePasswordAuthenticationToken(userName, password);
		
		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}
		
	}
	
	
	//register new user api
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		
		UserDto registerUser = this.userService.registerUser(userDto);
		
		return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
	}
}

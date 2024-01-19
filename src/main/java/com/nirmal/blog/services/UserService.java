package com.nirmal.blog.services;

import java.util.List;

import com.nirmal.blog.payloads.UserDto;

public interface UserService {

	UserDto registerUser(UserDto userDto);
	
	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, Integer id);

	UserDto getUserById(Integer id);

	List<UserDto> getAllUsers();
 
	void deleteUsers(Integer id);

}

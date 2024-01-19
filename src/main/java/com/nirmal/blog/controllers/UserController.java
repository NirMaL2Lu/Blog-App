package com.nirmal.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirmal.blog.payloads.ApiResponse;
import com.nirmal.blog.payloads.UserDto;
import com.nirmal.blog.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		logger.info("Method : createUser starts");

		UserDto createUserDto = this.userService.createUser(userDto);

		ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

		logger.info("Method : createUser ends");
		return responseEntity;
	}

	// PUT - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		logger.info("Method : updateUser starts");
		UserDto updateUserdto = this.userService.updateUser(userDto, userId);
		ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(updateUserdto, HttpStatus.OK);
		logger.info("Method : updateUser ends");
		return responseEntity;
	}

	// DELETE - delete user

	/*
	 * public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
	 * logger.info("Method : deleteUser starts");
	 * this.userService.deleteUsers(userId); ResponseEntity responseEntity = new
	 * ResponseEntity(Map.of("message","user deleted sucessfully"),HttpStatus.OK);
	 * logger.info("Method : deleteUser ends"); return responseEntity;
	 */
	
	// onlyt admin can delete
	// preauthorize for role based access
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		logger.info("Method : deleteUser starts");
		this.userService.deleteUsers(userId);
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<ApiResponse>(
				new ApiResponse("user deleted sucessfully", true), HttpStatus.OK);
		logger.info("Method : deleteUser ends");
		return responseEntity;

	}

	// GET - get user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		logger.info("Method : getAllUsers starts");
		ResponseEntity<List<UserDto>> responseEntity = ResponseEntity.ok(this.userService.getAllUsers());
		logger.info("Method : getAllUsers ends");
		System.out.println("res :" + responseEntity);
		return responseEntity;

	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		logger.info("Method : getUserById starts");
		
		ResponseEntity<UserDto> responseEntity = ResponseEntity.ok(this.userService.getUserById(userId));
		logger.info("Method : getUserById ends");
		System.out.println("res :" + responseEntity);
		return responseEntity;

	}
}

package com.nirmal.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.nirmal.blog.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 charachters ")
	private String name;
	@Email(message = "Email adress is not valid !!")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 characters and max of 10 characters")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}

package com.nirmal.blog.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirmal.blog.entity.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
}

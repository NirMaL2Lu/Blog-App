package com.nirmal.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirmal.blog.entity.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

}

package com.nirmal.blog.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirmal.blog.entity.Category;
import com.nirmal.blog.entity.Post;
import com.nirmal.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	List<Post> findBycategory(Category category);	
}

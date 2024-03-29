package com.nirmal.blog.services;

import java.util.List;

import com.nirmal.blog.entity.Post;
import com.nirmal.blog.payloads.PostDto;
import com.nirmal.blog.payloads.PostResponse;

public interface PostService {

	//create 
	
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	//delete 
	
	void deletePost(Integer postId);
	
	// get all posts
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);
	
	//get single post 
	PostDto getpostById(Integer postId);
	
	//get posts by category 
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get posts by user
	
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts 
	
	List<PostDto> searchPosts(String Keyword);
	
	
}


package com.nirmal.blog.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nirmal.blog.payloads.ApiResponse;
import com.nirmal.blog.payloads.PostDto;
import com.nirmal.blog.payloads.PostResponse;
import com.nirmal.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		logger.info("Method : createPost starts");

		PostDto createPost = this.postService.createPost(dto, userId, categoryId);
		logger.info("Method : createPost ends");
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	// get all posts by categoryId
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable Integer categoryId) {
		logger.info("Method : getPostsByCategoryId starts");
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		logger.info("Method : getPostsByCategoryId ends");
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// get all posts by userId
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId) {
		logger.info("Method : getPostsByUserId starts");
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		logger.info("Method : getPostsByUserId ends");
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// get all posts

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
			@RequestParam(value = "pageValue",defaultValue = "5",required = false)Integer pageValue,
			@RequestParam(value = "sortBy",defaultValue = "postId",required = false)String sortBy
			) {
		logger.info("Method : getAllPosts starts");
		  PostResponse allPost = this.postService.getAllPost(pageNumber,pageValue);
		logger.info("Method : getAllPosts ends");
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);

	}

	// get single posts by post id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId) {
		logger.info("Method : getPostsById starts");
		PostDto post = this.postService.getpostById(postId);
		logger.info("Method : getPostsById ends");
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	
	//delete post
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("the post is sucesssfully deleted !! ",true);
		
	}
	
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId){
		
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	

}

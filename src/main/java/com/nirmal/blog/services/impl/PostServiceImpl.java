package com.nirmal.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nirmal.blog.entity.Category;
import com.nirmal.blog.entity.Post;
import com.nirmal.blog.entity.User;
import com.nirmal.blog.exceptions.ResourceNotFoundException;
import com.nirmal.blog.payloads.PostDto;
import com.nirmal.blog.payloads.PostResponse;
import com.nirmal.blog.repos.CategoryRepo;
import com.nirmal.blog.repos.PostRepo;
import com.nirmal.blog.repos.UserRepo;
import com.nirmal.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user", "User id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category", "Category id", userId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category); 
		
		
		Post newPost = this.postRepo.save(post);
		
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
		.orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}
	@Override
	public void deletePost(Integer postId) {
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		
		this.postRepo.deleteById(postId);
	}
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		
		Sort sort = null;
				if(sortDirection.equalsIgnoreCase("asc"))
				{
					sort = Sort.by(sortBy).ascending();
				}
				else
				{
					sort = Sort.by(sortBy).descending();
				}
		
		
		// pageable object jere we can sort by acending or decending order
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		
		 Page<Post> pagePost = this.postRepo.findAll(page);
		 
		 List<Post> allPost = pagePost.getContent();
		 
		List<PostDto> postDtos = allPost.stream()
				.map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}
	@Override
	public PostDto getpostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				 .orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category catId = this.categoryRepo.findById(categoryId)
		.orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		List<Post> posts = this.postRepo.findBycategory(catId);
		
		List<PostDto> postDtos = posts.stream()
		.map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream()
				.map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}
	@Override
	public List<PostDto> searchPosts(String Keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(Keyword);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	

	

}

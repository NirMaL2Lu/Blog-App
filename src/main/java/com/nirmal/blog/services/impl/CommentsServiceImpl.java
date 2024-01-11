package com.nirmal.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirmal.blog.entity.Comments;
import com.nirmal.blog.entity.Post;
import com.nirmal.blog.exceptions.ResourceNotFoundException;
import com.nirmal.blog.payloads.CommentsDto;
import com.nirmal.blog.repos.CommentsRepo;
import com.nirmal.blog.repos.PostRepo;
import com.nirmal.blog.services.CommentsService;
@Service
public class CommentsServiceImpl implements CommentsService{
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentsRepo commentsRepo;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentsDto createComment(CommentsDto commentsDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "Post id ", postId));
		
		Comments comments = this.modelMapper.map(commentsDto, Comments.class);
		
		comments.setPost(post);
		
		Comments savedComment = this.commentsRepo.save(comments);
		
		return this.modelMapper.map(savedComment, CommentsDto.class);
	}

	@Override
	public void deleteComment(Integer CommentId) {
		Comments comments = this.commentsRepo.findById(CommentId).orElseThrow(()-> new ResourceNotFoundException("commment", "Comment Id", CommentId));
	this.commentsRepo.delete(comments);
		
	}

}

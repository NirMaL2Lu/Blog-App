package com.nirmal.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirmal.blog.payloads.ApiResponse;
import com.nirmal.blog.payloads.CommentsDto;
import com.nirmal.blog.services.CommentsService;

@RestController
@RequestMapping("/api/commments")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;
	
	// create comments
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentsDto> createComments(@RequestBody CommentsDto commentsDto,@PathVariable Integer postId){
		
		CommentsDto createComment = this.commentsService.createComment(commentsDto, postId);
		
		return new ResponseEntity<CommentsDto>(createComment,HttpStatus.CREATED);
		
	}
	
	// delete comments
		@PostMapping("/comments/{commentId}")
		public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
			
		this.commentsService.deleteComment(commentId); 
			return new ResponseEntity<ApiResponse>(new ApiResponse("Comments deleted sucessfulluy",true),HttpStatus.OK);
			
		}
	
}

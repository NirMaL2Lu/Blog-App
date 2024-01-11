package com.nirmal.blog.services;

import com.nirmal.blog.payloads.CommentsDto;

public interface CommentsService {
	
	CommentsDto createComment(CommentsDto commentsDto, Integer postId);
	void deleteComment(Integer CommentId);
	

}

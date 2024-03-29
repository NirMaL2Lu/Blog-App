package com.nirmal.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.nirmal.blog.entity.Category;
import com.nirmal.blog.entity.Comments;
import com.nirmal.blog.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer  postId;
	
	private String title;

	private String content;

	private String imageName;

	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentsDto> comments = new HashSet<>();

}

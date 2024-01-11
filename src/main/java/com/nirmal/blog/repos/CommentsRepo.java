package com.nirmal.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirmal.blog.entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer>{

}

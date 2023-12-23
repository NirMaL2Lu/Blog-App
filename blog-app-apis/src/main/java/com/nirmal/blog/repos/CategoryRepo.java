package com.nirmal.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirmal.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

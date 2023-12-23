package com.nirmal.blog.services;

import java.util.List;

import com.nirmal.blog.payloads.CategoryDto;

public interface CategoryService {
	// create categories
	CategoryDto createCategory(CategoryDto categoryDto);

	// update categories
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete categories
	public void deleteCategory(Integer categoryId);

	// get single category
	public CategoryDto getCategory(Integer categoryId);

	// get all categories
	List<CategoryDto> getAllCategories();
}

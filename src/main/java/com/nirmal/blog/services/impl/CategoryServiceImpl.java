package com.nirmal.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirmal.blog.entity.Category;
import com.nirmal.blog.exceptions.ResourceNotFoundException;
import com.nirmal.blog.payloads.CategoryDto;
import com.nirmal.blog.payloads.UserDto;
import com.nirmal.blog.repos.CategoryRepo;
import com.nirmal.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(category);
		CategoryDto addedCategoryDto = this.modelMapper.map(addedCategory, CategoryDto.class);
		return addedCategoryDto;

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepo.save(category);
		CategoryDto updatedCategoryDto = this.modelMapper.map(updatedCategory, CategoryDto.class);
		return updatedCategoryDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> allCategories = this.categoryRepo.findAll();
		
		System.out.println("allCategories ;;;;;;;; >>>>.." +allCategories);
		
		List<CategoryDto> allCategoryDto = allCategories.stream()
				.map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		
		
		
		/*
		 * List<UserDto> userDtos = users.stream() .map(user ->
		 * this.userToDto(user)).collect(Collectors.toList());
		 */
		
		
		System.out.println("allCategoryDto ;;;;;;;; >>>>.." +allCategoryDto);
		return allCategoryDto;
	}

}

package com.nirmal.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.nirmal.blog.payloads.ApiResponse;
import com.nirmal.blog.payloads.CategoryDto;
import com.nirmal.blog.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		logger.info("Method : createCategory starts");
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(createCategory, HttpStatus.CREATED);
		System.out.println("Response :" + responseEntity);
		logger.info("Method : createCategory starts");
		return responseEntity;
	}

	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {
		logger.info("Method : updateCategory starts");
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(updatedCategory, HttpStatus.OK);
		logger.info("Method : updateCategory starts");
		return responseEntity;
	}

	// delete

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		logger.info("Method : deleteCategory starts");
		this.categoryService.deleteCategory(categoryId);
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<ApiResponse>(
				new ApiResponse("user deleted sucessfully", true), HttpStatus.OK);
		logger.info("Method : deleteCategory ends");
		return responseEntity;

	}

	// GET - get all categories
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {

		logger.info("Method : getAllCategories starts");
		List<CategoryDto> allCategories = this.categoryService.getAllCategories();
		ResponseEntity<List<CategoryDto>> responseEntity = ResponseEntity.ok(allCategories);
		logger.info("Method : getAllCategories ends");
		System.out.println("res :" + responseEntity);
		return responseEntity;

	}
	// get single categories

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoriesById(@PathVariable Integer categoryId) {
		logger.info("Method : getCategoriesById starts");

		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
		logger.info("Method : getCategoriesById ends");
		System.out.println("res :" + responseEntity);
		return responseEntity;

	}
}

package com.nirmal.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min = 4 , message = "Category name must be min of 4 charachters ")
	private String categoryTitle;
	@NotBlank
	@Size(max = 20 , message = "Category description must be max of 20 charachters ")
	private String categoryDescription;
}

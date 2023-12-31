package com.nirmal.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	String resourcename;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resourcename, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s",resourcename, fieldName, fieldValue));
		this.resourcename = resourcename;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	

}

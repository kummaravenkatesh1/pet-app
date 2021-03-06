package com.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends CoreException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String errorCode)
	{
		super(errorCode);
	}

	public ResourceNotFoundException(String errorCode , String message)
	{
		super(errorCode,message);
	}
}
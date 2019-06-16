package com.revature.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends AbstractApiException{

	public ApiException(HttpStatus status) {
		super(status);
		// TODO Auto-generated constructor stub
	}
	
	public ApiException(HttpStatus status, String message) {
		super(status, message);
		// TODO Auto-generated constructor stub
	}	
}

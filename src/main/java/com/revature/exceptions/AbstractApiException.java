package com.revature.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AbstractApiException extends RuntimeException{
	HttpStatus status;
	String message;
	
	public AbstractApiException(HttpStatus status) {
		this.status = status;
	}
	public AbstractApiException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}	
}

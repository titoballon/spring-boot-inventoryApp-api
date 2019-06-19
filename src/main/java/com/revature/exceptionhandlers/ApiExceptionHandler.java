package com.revature.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.exceptions.ApiException;


@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {ApiException.class})
	public ResponseEntity<Object> inventoryApi(ApiException e){
		return new ResponseEntity<Object>(e.getMessage(), e.getStatus());
	}
	
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	public ResponseEntity<Object> HttpMessageNotReadable(HttpMessageNotReadableException e){
		return new ResponseEntity<Object>("Wrong object type", HttpStatus.BAD_REQUEST);
	}
	
	//this is to catch all exceptions and don't show anything in 
//	@ExceptionHandler(value= {Throwable.class})
//	public ResponseEntity<Object> catchAll(Throwable e){
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
}

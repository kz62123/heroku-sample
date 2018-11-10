package com.example.sample.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
	Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
		logger.error(exception.getMessage());
		exception.printStackTrace();
		return super.handleExceptionInternal(
				exception,
				exception.getMessage(),
				new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

}

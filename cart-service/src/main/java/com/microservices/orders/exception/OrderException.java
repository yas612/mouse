package com.microservices.orders.exception;

import org.springframework.http.HttpStatus;

public class OrderException {
	private final String message;
	private final HttpStatus httpStatus;
	
	public OrderException(String message, HttpStatus httpStatus) {
		
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}

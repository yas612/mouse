package com.microservices.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class OrderExceptionHandler {

	@ExceptionHandler(value= {OrderRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(OrderRequestException e){
		
		HttpStatus badRequest=HttpStatus.BAD_REQUEST;
		OrderException apiException=new OrderException (
				e.getMessage(),
				badRequest
				);
		return new ResponseEntity<>(apiException,badRequest);
	}

}

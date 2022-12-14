package com.retail.ordering.system.orderservice.exception;

import com.retail.ordering.system.orderservice.common.OrderResponse;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;


@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<OrderResponse> conflict(DataIntegrityViolationException ex){
		String message = getMostSpecificMessage(ex);
		return new ResponseEntity<OrderResponse>(new OrderResponse(false, message), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<OrderResponse> validationException(ValidationException ex){
		String message = ex.getMessage();
		return new ResponseEntity<OrderResponse>(new OrderResponse(false, message), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<OrderResponse> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		ex.printStackTrace();
		String message = ex.getMessage();
		return new ResponseEntity<OrderResponse>(new OrderResponse(false, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<OrderResponse> unhandledExceptions(Exception ex){
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		ex.printStackTrace();
		return new ResponseEntity<OrderResponse>(new OrderResponse(false, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private String getMostSpecificMessage(DataIntegrityViolationException ex) {
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		if(message.contains("Detail:")) {
			message = message.substring(message.indexOf("Detail:")+"Detail:".length());
		}
		return message;
	}
}

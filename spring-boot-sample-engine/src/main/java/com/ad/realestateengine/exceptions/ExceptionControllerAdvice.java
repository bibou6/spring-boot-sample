package com.ad.realestateengine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ad.realestatemodel.common.ErrorResponse;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RealEstateEngineException.class)
	public ResponseEntity<ErrorResponse> handleRealEstateEngineException(RealEstateEngineException ex, WebRequest request){
		
		ErrorResponse errorResponse = new ErrorResponse(ex.getCode());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(ex.getCode().getHttpStatus()));
		
	}

}

package com.pravin.util;

import javax.security.sasl.AuthenticationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pravin.model.ErrorDetails;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorDetails processValidationError(Exception e) {
		ErrorDetails error = new ErrorDetails();
		if(e instanceof AuthenticationException){
			error.setErrorCode("401");
			error.setErrorDesc(e.getMessage());
		}
		else if (e.getMessage()!=null) {
			error.setErrorCode("500");
			error.setErrorDesc(e.getMessage());
		} else {
			error.setErrorCode("500");
			error.setErrorDesc("System is currently unavailable, please try after sometime.");
		}
		return error;
	}
}

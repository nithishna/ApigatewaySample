package com.pravin.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.NotFoundException;

@ControllerAdvice
public class MainExceptionHandler {

	@ExceptionHandler(value= NotFoundException.class)
	public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex){
		Map<String, String> map = new HashMap<>();
		map.put("message", ex.getMessage());
		return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
	}
}

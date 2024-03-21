package com.bruno.livraria.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	   @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException erro) {
	        Map<String, String> errors = new HashMap<>();
	        erro.getBindingResult().getFieldErrors().forEach(error -> {
	            errors.put(error.getField(), error.getDefaultMessage());
	        });
	        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
	    }
}
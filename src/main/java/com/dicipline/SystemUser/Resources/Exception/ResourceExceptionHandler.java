package com.dicipline.SystemUser.Resources.Exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dicipline.SystemUser.Services.Exceptions.DataErrorException;
import com.dicipline.SystemUser.Services.Exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
  
	    @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, ServerHttpRequest request) {
	        String erro = "Resource not Found";
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        StandardError stdError = new StandardError(
	            Instant.now(), 
	            status.value(), 
	            e.getMessage(), 
	            erro, 
	            request.getURI().getPath());
	        
	        return ResponseEntity.status(status).body(stdError);
	    }
	    
	    @ExceptionHandler(DataErrorException.class)
	    public ResponseEntity<StandardError> dataError(DataErrorException e, HttpServletRequest request) {
	        String erro = "Data Error";
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        StandardError stdError = new StandardError(
	            Instant.now(), 
	            status.value(), 
	            e.getMessage(), 
	            erro, 
	            request.getRequestURI()
	        );
	        return ResponseEntity.status(status).body(stdError);
	    }
	}

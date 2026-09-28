package com.paul.inventory_management.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paul.inventory_management.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleNotFound(ResourceNotFoundException ex) {
		
		ErrorResponseDTO error = new ErrorResponseDTO(
				HttpStatus.NOT_FOUND.value(), 
				ex.getMessage()
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDTO> handleAlreadyExists(ResourceAlreadyExistsException ex) {
	
		ErrorResponseDTO error = new ErrorResponseDTO(
				HttpStatus.CONFLICT.value(),
				ex.getMessage()		
				);
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		
	}
	
	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<ErrorResponseDTO> handleBusinessRule(BusinessRuleException ex)  {
		
		ErrorResponseDTO error = new ErrorResponseDTO(
				HttpStatus.BAD_REQUEST.value(),
				ex.getMessage()
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	
	
	

}

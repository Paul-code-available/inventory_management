package exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleNotFound(ResourceNotFoundException ex,  HttpServletRequest request) {
		
		ErrorResponseDTO error = new ErrorResponseDTO(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(), 
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDTO> handleAlreadyExists(ResourceAlreadyExistsException ex, HttpServletRequest request) {
	
		ErrorResponseDTO error = new ErrorResponseDTO(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI()				
				);
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		
	}
	
	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<ErrorResponseDTO> handleBusinessRule(BusinessRuleException ex, HttpServletRequest request)  {
		
		ErrorResponseDTO error = new ErrorResponseDTO(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	
	
	

}

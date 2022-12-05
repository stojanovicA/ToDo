package com.example.ToDo.error;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseDTO> handleException(NoSuchElementException exc){
		String message = exc.getMessage();
		int status = HttpStatus.NOT_FOUND.value();
		LocalDate timestamp = LocalDate.now();
		ErrorResponseDTO error = new ErrorResponseDTO(message, status, timestamp);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseDTO> handleException(MethodArgumentTypeMismatchException exc){
		String message = exc.getMessage();
		int status = HttpStatus.BAD_REQUEST.value();
		LocalDate timestamp = LocalDate.now();
		ErrorResponseDTO error = new ErrorResponseDTO(message, status, timestamp);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponseDTO> handleException(Exception exc){
		String message = exc.getMessage();
		int status = HttpStatus.BAD_REQUEST.value();
		LocalDate timestamp = LocalDate.now();
		ErrorResponseDTO error = new ErrorResponseDTO(message, status, timestamp);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}

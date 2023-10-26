package com.cg.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorMessage> invalidArgumentException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error", "invalid date format");
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), errors,
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(NoSuchElementException ex, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error", ex.getMessage());
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), errors,
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ErrorMessage> invalidFormatException(InvalidFormatException ex, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", ex.getMessage());
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), errors,
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
}

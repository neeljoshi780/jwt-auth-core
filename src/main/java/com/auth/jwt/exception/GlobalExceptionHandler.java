package com.auth.jwt.exception;

import com.auth.jwt.common.response.ApiResponse;
import com.auth.jwt.common.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles all application exceptions and returns
 * standardized API responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Common method to build error response.
	 */
	private ResponseEntity<ApiResponse<Object>> buildErrorResponse(String message, HttpStatus status, HttpServletRequest request, Map<String, String> fieldErrors) {
		ErrorResponseDto error = ErrorResponseDto.builder()
			.timestamp(LocalDateTime.now())
			.status(status.value())
			.error(status.name())
			.message(message)
			.path(request.getRequestURI())
			.fieldErrors(fieldErrors)
			.build();
		return ResponseEntity.status(status).body(ApiResponse.error(message, error));
	}

	/**
	 * Validation errors (@Valid)
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
			.forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
		return buildErrorResponse("Validation failed", HttpStatus.BAD_REQUEST, request, fieldErrors);
	}

	/**
	 * Handle invalid login credentials (username/password).
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse<Object>> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
		return buildErrorResponse("Invalid username or password", HttpStatus.UNAUTHORIZED, request, null);
	}

	/**
	 * Handle all other exceptions.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleException(Exception ex, HttpServletRequest request) {
		return buildErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, request, null);
	}

}
package com.auth.jwt.security.jwt;

import com.auth.jwt.common.response.ApiResponse;
import com.auth.jwt.common.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Handles 401 Unauthorized errors when a request is not authenticated.
 * Returns a standardized API error response.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	/**
	 * Builds and sends a custom 401 response.
	 * Triggered by Spring Security when authentication fails.
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, @NonNull AuthenticationException ex) throws IOException {
		ErrorResponseDto error = ErrorResponseDto.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.UNAUTHORIZED.value())
			.error(HttpStatus.UNAUTHORIZED.name())
			.message("Unauthorized")
			.path(request.getRequestURI())
			.build();
		ApiResponse<Object> apiResponse = ApiResponse.error("Unauthorized", error);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		objectMapper.writeValue(response.getOutputStream(), apiResponse);
	}

}
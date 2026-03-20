package com.auth.jwt.security.jwt;

import com.auth.jwt.common.response.ApiResponse;
import com.auth.jwt.common.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Handles 403 Forbidden errors when an authenticated user
 * tries to access a resource without required permissions.
 * Returns a standardized API error response.
 */
@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	private final ObjectMapper objectMapper;

	/**
	 * Builds and sends a custom 403 response.
	 * Triggered by Spring Security when access is denied.
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, @NonNull AccessDeniedException ex) throws IOException {
		ErrorResponseDto error = ErrorResponseDto.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.FORBIDDEN.value())
			.error(HttpStatus.FORBIDDEN.name())
			.message("Access Denied")
			.path(request.getRequestURI())
			.build();
		ApiResponse<Object> apiResponse = ApiResponse.error("Access Denied", error);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json");
		objectMapper.writeValue(response.getOutputStream(), apiResponse);
	}

}
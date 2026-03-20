package com.auth.jwt.common.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Standard error response DTO.
 *
 * Used for validation, business, and system errors.
 * Provides structured error details for client applications.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

	private LocalDateTime timestamp;

	private int status;

	private String error;

	private String message;

	private String path;

	private Map<String, String> fieldErrors;

}
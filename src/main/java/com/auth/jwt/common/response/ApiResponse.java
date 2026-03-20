package com.auth.jwt.common.response;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Standard API response wrapper.
 *
 * Used for all API responses (success + error).
 * Ensures consistent structure across application.
 *
 * @param <T> type of response data
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

	private boolean success;

	private String message;

	private T data;

	private ErrorResponseDto error;

	private LocalDateTime timestamp;

	/**
	 * Builds a success response.
	 *
	 * @param data actual response data
	 * @param message success message
	 * @return ApiResponse object with success=true
	 */
	public static <T> ApiResponse<T> success(T data, String message) {
		return ApiResponse.<T>builder()
			.success(true)
			.message(message)
			.data(data)
			.timestamp(LocalDateTime.now())
			.build();
	}

	/**
	 * Builds an error response.
	 *
	 * @param message error message
	 * @param error error details
	 * @return ApiResponse object with success=false
	 */
	public static <T> ApiResponse<T> error(String message, ErrorResponseDto error) {
		return ApiResponse.<T>builder()
			.success(false)
			.message(message)
			.error(error)
			.timestamp(LocalDateTime.now())
			.build();
	}

}
package com.auth.jwt.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for user login request.
 *
 * Carries user credentials required for authentication.
 * Used in login API to validate username and password input.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {

	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Password is required")
	private String password;

}
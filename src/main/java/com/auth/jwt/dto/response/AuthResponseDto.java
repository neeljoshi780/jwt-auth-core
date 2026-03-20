package com.auth.jwt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for authentication response.
 *
 * Contains JWT token generated after successful login.
 * This token is used for accessing secured APIs.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponseDto {

	private String token;

}
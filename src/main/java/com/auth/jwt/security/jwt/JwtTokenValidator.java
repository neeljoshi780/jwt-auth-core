package com.auth.jwt.security.jwt;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handles JWT validation logic.
 */
@Component
@RequiredArgsConstructor
public class JwtTokenValidator {

	private final JwtTokenProvider tokenProvider;

	/**
	 * Validate token integrity and expiration.
	 *
	 * @param token JWT token
	 * @return true if valid, false otherwise
	 */
	public boolean isValid(String token) {
		try {
			tokenProvider.extractClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

}
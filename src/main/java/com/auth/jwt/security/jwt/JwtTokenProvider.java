package com.auth.jwt.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

/**
 * Central class responsible for:
 * - Generating JWT tokens
 * - Parsing tokens
 * - Extracting claims
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	private final JwtProperties jwtProperties;

	private SecretKey secretKey;

	/**
	 * Initialize secret key once during startup.
	 */
	@PostConstruct
	public void initializeSecretKey() {
		this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Generates JWT access token.
	 *
	 * @param username authenticated username
	 * @return signed JWT token
	 */
	public String generateAccessToken(String username) {
		Instant now = Instant.now();
		Instant expiry = now.plusMillis(jwtProperties.getAccessTokenExpiration());
		return Jwts.builder()
			.subject(username)
			.issuedAt(Date.from(now))
			.expiration(Date.from(expiry))
			.signWith(secretKey)
			.compact();
	}

	/**
	 * Extract username from token.
	 *
	 * @param token JWT token
	 * @return username (subject)
	 */
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	/**
	 * Parse and validate token claims.
	 *
	 * @param token JWT token
	 * @return Claims object
	 */
	public Claims extractClaims(String token) {
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

}
package com.auth.jwt.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for JWT.
 *
 * Loaded from application.yml / properties.
 * This class centralizes all JWT-related configurations.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {

	/**
	 * Secret key used for signing JWT tokens.
	 * Should be at least 256-bit (32+ chars).
	 */
	private String secret;

	/**
	 * Access token expiration in milliseconds.
	 */
	private long accessTokenExpiration;

}
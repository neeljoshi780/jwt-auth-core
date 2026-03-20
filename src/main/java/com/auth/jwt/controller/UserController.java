package com.auth.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APIs accessible by USER and ADMIN roles.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	/**
	 * User profile endpoint.
	 *
	 * Requires ROLE_USER or ROLE_ADMIN.
	 *
	 * @param authentication current authenticated user
	 * @return user profile info
	 */
	@GetMapping("/profile")
	public String profile(Authentication authentication) {
		return "User Profile API - " + authentication.getName();
	}

}
package com.auth.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APIs accessible only by ADMIN role.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	/**
	 * Admin dashboard endpoint.
	 *
	 * Requires ROLE_ADMIN.
	 *
	 * @param authentication current authenticated user
	 * @return admin dashboard info
	 */
	@GetMapping("/dashboard")
	public String dashboard(Authentication authentication) {
		return "Admin Dashboard API - " + authentication.getName();
	}

}
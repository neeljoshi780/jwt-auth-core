package com.auth.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public APIs accessible without authentication.
 */
@RestController
@RequestMapping("/api/public")
public class PublicController {

	/**
	 * Public test endpoint.
	 *
	 * No authentication required.
	 *
	 * @return simple public response
	 */
	@GetMapping("/test")
	public String test() {
		return "Public API";
	}

}
package com.auth.jwt.controller;

import com.auth.jwt.common.response.ApiResponse;
import com.auth.jwt.dto.request.LoginRequestDto;
import com.auth.jwt.dto.response.AuthResponseDto;
import com.auth.jwt.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication APIs
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	/**
	 * Login API
	 */
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequestDto request) {
		String token = authService.login(request.getUsername(), request.getPassword());
		return ResponseEntity.ok(ApiResponse.success(token, "User logged in successfully"));
	}

}
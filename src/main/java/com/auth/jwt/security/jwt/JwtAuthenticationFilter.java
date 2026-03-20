package com.auth.jwt.security.jwt;

import com.auth.jwt.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter responsible for:
 * - Extracting JWT from request
 * - Validating token
 * - Setting authentication context
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider tokenProvider;

	private final JwtTokenValidator tokenValidator;

	private final CustomUserDetailsService userDetailsService;

	/**
	 * Processes JWT authentication for each request.
	 *
	 * Extracts token from Authorization header (Bearer token),
	 * validates it, loads user details, and sets authentication
	 * in SecurityContext if token is valid.
	 *
	 * If token is missing or invalid, request continues without authentication.
	 *
	 * @param request incoming HTTP request
	 * @param response HTTP response
	 * @param filterChain next filter in chain
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			if (tokenValidator.isValid(token)) {
				String username = tokenProvider.extractUsername(token);
				var userDetails = userDetailsService.loadUserByUsername(username);
				var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}

}
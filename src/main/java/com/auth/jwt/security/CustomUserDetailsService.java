package com.auth.jwt.security;

import com.auth.jwt.model.User;
import com.auth.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Loads user details from database for authentication.
 * Used by Spring Security during login and JWT validation.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	/**
	 * Fetches user by username and converts it to UserDetails.
	 * Throws exception if user is not found.
	 */
	@Override
	@NonNull
	public UserDetails loadUserByUsername(@NonNull String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new CustomUserDetails(user);
	}

}
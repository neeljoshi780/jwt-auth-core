package com.auth.jwt.security;

import com.auth.jwt.model.User;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Adapts User entity to Spring Security UserDetails.
 * Provides user credentials and authorities.
 */
public class CustomUserDetails implements UserDetails {

	private final User user;

	public CustomUserDetails(User user) {
		this.user = user;
	}

	/**
	 * Returns user roles as granted authorities.
	 */
	@Override
	@NonNull
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles()
			.stream()
			.map(role -> new SimpleGrantedAuthority(role.getName()))
			.collect(Collectors.toList());
	}

	/**
	 * Returns user password.
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * Returns username.
	 */
	@Override
	@NonNull
	public String getUsername() {
		return user.getUsername();
	}

	/**
	 * Indicates if user account is enabled.
	 */
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	@Override public boolean isAccountNonExpired() {
		return true;
	}

	@Override public boolean isAccountNonLocked() {
		return true;
	}

	@Override public boolean isCredentialsNonExpired() {
		return true;
	}

}
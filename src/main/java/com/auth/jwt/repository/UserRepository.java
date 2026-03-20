package com.auth.jwt.repository;

import com.auth.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find user by username.
	 */
	Optional<User> findByUsername(String username);

	/**
	 * Check if username already exists.
	 */
	Boolean existsByUsername(String username);

}
package com.auth.jwt.repository;

import com.auth.jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Role entity.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Find role by name.
	 */
	Optional<Role> findByName(String name);

	/**
	 * Check if role exists by name.
	 */
	boolean existsByName(String name);

}
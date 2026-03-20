package com.auth.jwt.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing roles in the system.
 *
 * Roles define the level of access (e.g., ADMIN, USER).
 * Used for role-based authorization.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

}
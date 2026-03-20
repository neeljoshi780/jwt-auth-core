package com.auth.jwt.seeder;

import com.auth.jwt.model.Role;
import com.auth.jwt.model.User;
import com.auth.jwt.repository.RoleRepository;
import com.auth.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds initial data into the database at application startup.
 * Creates default roles and users if they do not already exist.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

	private final RoleRepository roleRepository;

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	/**
	 * Runs after application startup.
	 * Initializes roles and default users.
	 */
	@Override
	public void run(String... args) throws Exception {
		createRoleIfNotExists("ROLE_ADMIN");
		createRoleIfNotExists("ROLE_USER");
		createAdminIfNotExists();
		createUserIfNotExists();
	}

	/**
	 * Creates a role if it does not exist in the database.
	 */
	public void createRoleIfNotExists(String roleName){
		if(!roleRepository.existsByName(roleName)){
			Role role = new Role();
			role.setName(roleName);
			roleRepository.save(role);
			System.out.println("Role created: " + roleName);
		}
	}

	/**
	 * Creates default admin user if not present.
	 */
	private void createAdminIfNotExists() {
		if (!userRepository.existsByUsername("neeljoshi780")) {
			User admin = new User();
			admin.setUsername("neeljoshi780");
			admin.setEmail("neeljoshi780@gmail.com");
			admin.setPassword(passwordEncoder.encode("n@123456"));
			roleRepository.findByName("ROLE_ADMIN").ifPresent(role -> admin.getRoles().add(role));
			userRepository.save(admin);
			System.out.println("Admin user created.");
		}
	}

	/**
	 * Creates default normal user if not present.
	 */
	private void createUserIfNotExists() {
		if (!userRepository.existsByUsername("gautamjoshi780")) {
			User user = new User();
			user.setUsername("gautamjoshi780");
			user.setEmail("gautamjoshi780@gmail.com");
			user.setPassword(passwordEncoder.encode("g@123456"));
			roleRepository.findByName("ROLE_USER").ifPresent(role -> user.getRoles().add(role));
			userRepository.save(user);
			System.out.println("User created.");
		}
	}

}
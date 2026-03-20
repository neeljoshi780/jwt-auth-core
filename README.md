# 🔐 Spring Boot JWT Auth Demo

This is a simple Spring Boot project to learn JWT authentication and role-based access control (RBAC).

Users can log in using username and password, and a JWT token is generated after successful authentication. This token is then used to access secured APIs.

The project includes basic role-based access:

* Public APIs (no login required)
* User APIs (accessible by USER and ADMIN)
* Admin APIs (accessible by ADMIN only)

Spring Security is used for authentication and authorization, and passwords are stored using BCrypt encoding.

This project is created for learning and understanding JWT flow in Spring Boot.

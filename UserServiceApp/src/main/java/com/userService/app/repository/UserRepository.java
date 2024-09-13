package com.userService.app.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userService.app.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String username);
	}

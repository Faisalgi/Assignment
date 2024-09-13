package com.userService.journal.repositiry;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userService.journal.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String username);
	}

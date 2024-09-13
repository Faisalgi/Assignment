package com.userService.journal.securityService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.userService.journal.entity.Users;

public interface UserServiceSecurityLocal {
	
	
	public PasswordEncoder passwordEncoder();
	
}

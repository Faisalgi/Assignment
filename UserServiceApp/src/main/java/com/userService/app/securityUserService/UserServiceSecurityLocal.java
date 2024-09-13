package com.userService.app.securityUserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.userService.app.entity.Users;

public interface UserServiceSecurityLocal {
	
	public Integer saveUser(Users user);
	
	public PasswordEncoder passwordEncoder();
	
}

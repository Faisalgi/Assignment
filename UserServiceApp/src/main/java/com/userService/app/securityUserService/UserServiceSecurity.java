package com.userService.app.securityUserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userService.app.entity.Users;
import com.userService.app.repository.UserRepository;

@Service
public class UserServiceSecurity implements UserServiceSecurityLocal, UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	public Integer saveUser(Users user) {
		String passwd = user.getPassword();
		String encodedPasswod = passwordEncoder().encode(passwd);
		user.setPassword(encodedPasswod);
		user = userRepo.save(user);
		return user.getId().intValue();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Users> opt = userRepo.findByUsername(username);
		org.springframework.security.core.userdetails.User springUser = null;

		if (opt.isEmpty())
			throw new UsernameNotFoundException("User with email: " + username + " not found !");
		else {
			Set<GrantedAuthority> ga = new HashSet<>();

			ga.add(new SimpleGrantedAuthority(opt.get().getRole()));

			springUser = new org.springframework.security.core.userdetails.User(opt.get().getUsername(),
					opt.get().getPassword(), ga);
			return springUser;
//			Users user = opt.get();
//			return new org.springframework.security.core.userdetails.User(
//					user.getUsername(),
//					user.getPassword(),
//					user.getRole()
//					.stream()
//					.map(role-> new SimpleGrantedAuthority(role))
//					.collect(Collectors.toSet())
//		    );
		}

	}

}

package com.userService.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userService.app.config.KafkaProducerService;
import com.userService.app.entity.Users;
import com.userService.app.getAllUserResponseVO.GetAllUserResponse;
import com.userService.app.repository.UserRepository;
import com.userService.app.securityUserService.UserServiceSecurityLocal;

@Service
public class UserService implements UserServiceLocal{

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    UserServiceSecurityLocal uSS;
    
    @Autowired
    private KafkaProducerService kafkaProducerService;

    public Users registerUser(Users user) {
        logger.info("Registering user with username: {}", user.getUsername());
		user.setPassword(uSS.passwordEncoder().encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        logger.info("User registered successfully with ID: {}", savedUser.getId());
        kafkaProducerService.publishEvent("User Registered: " + user.getUsername());
        return savedUser;
    }

    public Users getUser(Long id) {
        logger.info("Fetching user with ID: {}", id);
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        logger.info("User fetched successfully: {}", user.getUsername());
        kafkaProducerService.publishEvent("User Fetched: " + id);
        return user;
    }

    public Users updateUser(Long id, Users user) {
        logger.info("Updating user with ID: {}", id);
        Users existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(uSS.passwordEncoder().encode(user.getPassword()));
        existingUser.setRole(user.getRole());
        Users updatedUser = userRepository.save(existingUser);
        logger.info("User updated successfully with ID: {}", updatedUser.getId());
        kafkaProducerService.publishEvent("User updated: " + id);
        return updatedUser;
    }

    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        Users existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(existingUser);
        logger.info("User deleted successfully with ID: {}", id);
        kafkaProducerService.publishEvent("User Deleted: " + id);
    }

	@Override
	public GetAllUserResponse getAllUser() {
		logger.info("Fetching all users ");
		GetAllUserResponse allUsers;
		
		List<Users> all = userRepository.findAll();
		if(all.isEmpty()) {
			allUsers=new GetAllUserResponse("No Data Found","-1",all);
		}else {
			allUsers=new GetAllUserResponse("Data fetched successfuly", "1", all);
		}
		kafkaProducerService.publishEvent("Users fetched: " + all.size());
		return allUsers;
	}
}

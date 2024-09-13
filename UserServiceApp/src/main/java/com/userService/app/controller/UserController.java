package com.userService.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.userService.app.entity.Users;
import com.userService.app.getAllUserResponseVO.GetAllUserResponse;
import com.userService.app.securityUserService.UserServiceSecurity;
import com.userService.app.securityUserService.UserServiceSecurityLocal;
import com.userService.app.service.UserServiceLocal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserServiceLocal userService;
    
    

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        logger.info("Received request to register user: {}", user);
       
        Users registeredUser = userService.registerUser(user);
        System.out.println(user.toString());
        logger.info("User registered: {}", registeredUser);
        return registeredUser;
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable Long id) {
        logger.info("Received request to get user with ID: {}", id);
        Users user = userService.getUser(id);
        logger.info("User retrieved: {}", user);
        return user;
    }
    
    
    @GetMapping("/getAllUser")
    public GetAllUserResponse getAllUser() {
        logger.info("Received request to get all user {}");
        GetAllUserResponse allUsers = userService.getAllUser();
        logger.info("User retrieved: {}", allUsers.toString());
        return allUsers;
    }

    
    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        logger.info("Received request to update user with ID: {}", id);
        Users updatedUser = userService.updateUser(id, user);
        logger.info("User updated: {}", updatedUser);
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        logger.info("Received request to delete user with ID: {}", id);
        userService.deleteUser(id);
        logger.info("User deleted with ID: {}", id);
    }
}

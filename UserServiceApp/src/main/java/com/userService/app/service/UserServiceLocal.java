package com.userService.app.service;

import com.userService.app.entity.Users;
import com.userService.app.getAllUserResponseVO.GetAllUserResponse;

public interface UserServiceLocal {
	
	public Users registerUser(Users user);
	
	 public Users getUser(Long id);
	 
	 public GetAllUserResponse getAllUser();
	 
	 public Users updateUser(Long id, Users user);
	 
	 public void deleteUser(Long id);
}

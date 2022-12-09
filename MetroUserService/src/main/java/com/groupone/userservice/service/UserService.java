package com.groupone.userservice.service;

import com.groupone.userservice.entity.User;

public interface UserService {

	public User searchUserById(int id);
	
	public User addUser(User user);
}

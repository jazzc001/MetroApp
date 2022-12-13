package com.groupone.userservice.service;

import com.groupone.userservice.entity.User;

import java.util.List;

public interface UserService {

	public User searchByUserId(int userId);
	
	public User addUser(User user);

	public User searchByEmail(String email);

}

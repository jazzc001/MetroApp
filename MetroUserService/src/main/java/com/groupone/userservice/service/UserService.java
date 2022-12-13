package com.groupone.userservice.service;

import com.groupone.userservice.entity.User;

import java.util.List;

public interface UserService {

	User searchByUserId(int userId);
	
	boolean addUser(User user);

	User searchByEmail(String email);
	
	User topUpBalance(int userId, double topUpAmount);
	 

}

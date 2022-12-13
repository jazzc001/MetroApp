package com.groupone.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupone.userservice.entity.User;
import com.groupone.userservice.persistence.UserDao;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User searchByUserId(int userId) {
		return userDao.findById(userId).orElse(null);
	}

	@Override
	public boolean addUser(User user) {
		if (userDao.save(user) == null)
			return false;
		else
			return true;
	}

	@Override
	public User searchByEmail(String email) {
		return userDao.searchByEmail(email);
	}

}

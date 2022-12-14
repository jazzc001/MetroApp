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

	@Override
	public User topUpBalance(int userId, double topUpAmount) {
		User user = userDao.findById(userId).get();
		if (user != null) {
			user.setBalance(user.getBalance() + topUpAmount);
			userDao.save(user);
			return user;
		} else {
			return null;

		}

	}

	@Override
	public User loginCheck(String email, String password) {
		try {
			User user = userDao.findByEmailAndPassword(email, password);
			if (user != null)
				return user;
			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public User updateBalance(int userId, double fare) {
		User user = userDao.findById(userId).get();
		if (user != null) {
			user.setBalance(user.getBalance() - fare);
			userDao.save(user);
			return user;
		} else {
			return null;
		}
	}
}

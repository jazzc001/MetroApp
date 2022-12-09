package com.groupone.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupone.userservice.entity.User;
import com.groupone.userservice.persistence.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserDao userDao;

    @Override
    public User searchUserById(int id) {
        return userDao.findById(id).orElse(null);
    }

	@Override
	public User addUser(User user) {
		try {
			int rows=userDao.insertUser(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getBalance(), user.getCardNumber());
			if(rows>0)
				return user;
			else
				return null;
		}
		catch(Exception ex) {
			return null;
		}

	}

}

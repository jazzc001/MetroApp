package com.groupone.userservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupone.userservice.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	User searchByEmail(String email);

	User searchByUserId(int id);
	
	User findByEmailAndPassword(String email, String password);

}

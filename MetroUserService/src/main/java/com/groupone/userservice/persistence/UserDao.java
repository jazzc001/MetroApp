package com.groupone.userservice.persistence;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.groupone.userservice.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User searchByEmail(String email);

	public User searchByUserId(int id);

}

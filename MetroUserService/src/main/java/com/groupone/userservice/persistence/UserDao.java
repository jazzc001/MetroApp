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

	// Native Query
	@Modifying
	@Transactional
	@Query(value = "insert into user values(:uid,:fna,:lna,:email,:password,:bal,:cNo)", nativeQuery = true)
	int insertUser(@Param("uid") int id, @Param("fna") String fname, @Param("lna") String lname,
			@Param("email") String mail, @Param("password") String pwd, @Param("bal") double balance,
			@Param("cNo") int cardNo) throws SQLIntegrityConstraintViolationException;

}

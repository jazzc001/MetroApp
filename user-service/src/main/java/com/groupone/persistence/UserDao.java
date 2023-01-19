package com.groupone.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupone.entity.User;


@Repository
public interface UserDao extends JpaRepository<User,Integer>{

	//Native Query
//	@Modifying
//	@Transactional
//	@Query(value = "insert into user values(:uid,:fna,:lna,:email,:password,:bal,:cNo)", nativeQuery = true)
//	int insertUser(@Param("uid") int id, @Param("fna") String fname, @Param("lna") String lname,
//			@Param("email") String mail,@Param("password") String pwd,@Param("bal") double balance,@Param("cNo") int cardNo)throws SQLIntegrityConstraintViolationException;
//
	
	User searchByEmail(String email);

	User searchByUserId(int id);
	
	User findByEmailAndPassword(String email, String password);
	
}

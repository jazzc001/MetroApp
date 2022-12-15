package com.groupone.userservice;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.groupone.userservice.entity.User;
import com.groupone.userservice.persistence.UserDao;
import com.groupone.userservice.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class MetroUserServiceApplicationTests {

	@InjectMocks
	private UserServiceImpl userServiceImpl;
	@Mock
	private UserDao userDao;
	private AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
		
	}

	@Test
	@DisplayName("Search User By ID - Positive Scenario")
	void testsearchUserByIdPositive() {
		User user = new User(101,"FirstName", "LastName", "test@mail.com", "testPassword", 50);
		when(userDao.searchByUserId(101)).thenReturn(user);
		assertEquals(user, userServiceImpl.searchByUserId(101));
	}

	@Test
	@DisplayName("Search User By ID - Negative Scenario")
	void testsearchUserByIdNegative() {
		when(userDao.searchByUserId(500)).thenReturn(null);
		assertNull("User does not exist", userServiceImpl.searchByUserId(500));
	}
	
	@Test
	@DisplayName("Search User By Email - Positive Scenario")
	void testSearchUserByEmailPositive() {
		User user = new User(101,"FirstName", "LastName", "test@mail.com", "testPassword", 50);
		when(userDao.searchByEmail("test@gmail.com")).thenReturn(user);
		assertEquals(user, userServiceImpl.searchByEmail("test@gmail.com"));
	}

	@Test
	@DisplayName("Search User By Email - Negative Scenario")
	void testSearchUserByEmailNegative() {
		when(userDao.searchByEmail("fail@gmail.com")).thenReturn(null);
		assertNull("User does not exist", userServiceImpl.searchByEmail("fail@gmail.com"));
	}

//	@Test
//	@DisplayName("Add User - Positive Scenario")
//	void testAddUserPositive() throws SQLIntegrityConstraintViolationException {
//		when(userDao.insertUser("FirstName", "LastName", "test@mail.com", "testPassword", 50)).thenReturn(1);
//		assertNotNull(userServiceImpl
//				.addUser(new User(101, "FirstName", "LastName", "test@mail.com", "testPassword", 50)));
//
//	}

//	@Test
//	@DisplayName("Add User - Negative Scenario")
//	void testAddUserNegative() throws SQLIntegrityConstraintViolationException {
//		when(userDao.insertUser("FirstName", "LastName", "test@mail.com", "testPassword", 50)).thenReturn(0);
//		assertNull(userServiceImpl
//				.addUser(new User(101, "FirstName", "LastName", "test@mail.com", "testPassword", 50)));
//	}

}

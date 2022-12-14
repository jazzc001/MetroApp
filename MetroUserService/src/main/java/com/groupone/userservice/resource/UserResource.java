package com.groupone.userservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groupone.userservice.entity.User;
import com.groupone.userservice.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/user/id/{abc}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User searchUserById(@PathVariable("abc") int id) {
		return userService.searchByUserId(id);
	}

	@PostMapping(path = "/newUser", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addUserResource(@RequestBody User newUser) {
		if (userService.addUser(newUser))
			return "User Added";
		else
			return "User Not Added";
	}

	@PutMapping(path = "/user/id/{userId}/{topUp}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User topUpAmount(@PathVariable("userId") int userId, @PathVariable("topUp") double topUpAmount) {
		return userService.topUpBalance(userId, topUpAmount);
	}

	@PutMapping(path = "/user/{userId}/{fare}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateBalance(@PathVariable("userId") int userId, @PathVariable("fare") double fare) {
		return userService.updateBalance(userId, fare);
	}

	@GetMapping(path = "/user/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User searchByEmail(@PathVariable("email") String email) {
		return userService.searchByEmail(email);
	}

	@GetMapping(path = "/user/{email}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User searchByEmailAndPassword(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		return userService.loginCheck(email, password);
	}
}

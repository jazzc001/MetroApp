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

	@PutMapping(path = "/user/id/{userId}/{topUp}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String topUpAmount(@PathVariable("userId") int userId, @PathVariable("topUp") double topUpAmount) {
		if (userService.topUpBalance(userId, topUpAmount) != null)
			return "TopUp Successfull";
		else
			return "TopUp Unsuccessfull";
	}

	@GetMapping(path = "/user/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User searchByEmail(@PathVariable("email") String email) {
		return userService.searchByEmail(email);
	}
}

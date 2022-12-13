package com.groupone.userservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.groupone.userservice.entity.User;
import com.groupone.userservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

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

	@GetMapping(path = "/user/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User searchByEmail(@PathVariable("email") String email) {
		return userService.searchByEmail(email);
	}
}

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

    @GetMapping(path="/user/{abc}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User searchUserById(@PathVariable("abc")int id){
        return userService.searchByUserId(id);
    }
    
    @PostMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUserResource(@RequestBody User user) {
    	return userService.addUser(user);
    }

    @GetMapping(path="/users/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User searchByEmail(@PathVariable("email") String email) {
        return userService.searchByEmail(email);
    }
}

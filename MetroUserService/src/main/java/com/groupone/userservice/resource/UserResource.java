package com.groupone.userservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groupone.userservice.entity.User;
import com.groupone.userservice.service.UserService;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(path="/user/{abc}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User searchUserById(@PathVariable("abc")int id){
        return userService.searchUserById(id);
    }
    
    
    @PostMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUserResource(@RequestBody User user) {
    	return userService.addUser(user);
    }

}

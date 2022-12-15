package com.groupone.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private int userId;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    
    private double balance;
    
    
}

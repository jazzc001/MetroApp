package com.groupone.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.groupone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private double balance;
}

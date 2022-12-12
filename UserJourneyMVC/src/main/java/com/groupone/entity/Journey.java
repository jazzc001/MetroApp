package com.groupone.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Journey {

	@Id
	@GeneratedValue
	private int journeyId;

	private int userId;
	private String swipeInStation;
	private String swipeOutStation;
	private LocalDateTime swipeInDateAndTime;
	private LocalDateTime swipeOutDateAndTime;
	private double journeyFare;

}

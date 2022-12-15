package com.groupone.service;

import java.util.List;

import com.groupone.entity.Journey;
import com.groupone.entity.User;

public interface UserJourneyService {
	
   /* == LOGIN == */

	User login(String email, String password);
	
	/* == CREATE NEW USER == */
	
	User createNewUser(String firstName, String lastName, String Email, String password, double balance);
	
	/* == PAST JOURNEY == */

	List<Journey> searchJourneyByUserID(Integer userId);

	/* == CALCULATE FARE == */

	double calculateFare (int journeyId, String startStationName, String endStationName);
	
	/* == TOP UP == */

	User topUpBalance (int userId, double amount);
	
	/* == UPDATE BALANCE AFTER JOURNEY == */
	
	User updateBalance(int userId, double fare);

	double getBalance(int userId);
	
	/* == SWIPE IN == */
	
	Journey swipeIn(int userId, String startStationName);

	Journey swipeOut(int journeyId, String endStationName);
	
	
}

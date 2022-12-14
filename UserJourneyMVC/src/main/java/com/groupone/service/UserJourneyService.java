package com.groupone.service;

import java.util.List;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;
import com.groupone.entity.User;

public interface UserJourneyService {

	User login(String email, String password);
	
	User createNewUser(String firstName, String lastName, String Email, String password, double balance);

	List<Journey> searchJourneyByUserID(Integer userId);
	
	Journey createNewJourney(int userId, Station startStation, Station endStation);

	double calculateFare (int startStationId, int endStationId);

	User topUpBalance (int userId, double amount);
	
	public boolean updateBalance(int userId, double remainingBalance,int startStationId, int endStationId);

	public double getBalance(int userId);
	
}

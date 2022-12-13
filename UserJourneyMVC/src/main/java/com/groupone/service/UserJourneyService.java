package com.groupone.service;

import java.util.List;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;
import com.groupone.entity.User;

public interface UserJourneyService {

	boolean login(String email, String password);

	List<Journey> searchJourneyByUserID(Integer userId);
	
	Journey createNewJourney(int userId, Station startStation, Station endStation);

	double calculateFare (int startStationId, int endStationId);

	void topUpBalance (int userId, double amount);
	
	public boolean updateBalance(int userId, double remainingBalance,int startStationId, int endStationId);

	public double getBalance(int userId);

}

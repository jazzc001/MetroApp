package com.groupone.service;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;
import com.groupone.entity.User;

public interface UserJourneyService {

<<<<<<< HEAD
	//Serach Journey List from userId
	List<Journey> searchJourneyByUserID(Integer userId);

	//Create a new Journey
	Boolean addJourney(Journey journey);
=======
	public Journey createNewJourney(Station startStation, Station endStation, int userId);

	/*private int journeyId;

	private int stationId;
	private int userId;
	private String swipeInStation;
	private String swipeOutStation;
	private LocalDateTime swipeInDateAndTime;
	private LocalDateTime swipeOutDateAndTime;
	private double journeyFare;

	*/

	// List<Journey> getJourneyByUserID(Integer userId, Integer swipeInStationId,
	// Integer swipeOutStationId);
	public boolean login(String email, String password);

>>>>>>> 6238ccdba717beaf70e996964261bce89a4608ce
}

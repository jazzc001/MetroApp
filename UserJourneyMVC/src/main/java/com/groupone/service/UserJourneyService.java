package com.groupone.service;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;

import java.util.List;

public interface UserJourneyService {

	//Serach Journey List from userId
	List<Journey> searchJourneyByUserID(Integer userId);

//Create a new journey

//	Journey createNewJourney(int userId, Station startStation, Station endStation);

	/*private int journeyId; //auto incremented

private int userId
	private int stationId;

	private String swipeInStation;
	private String swipeOutStation;
	private LocalDateTime swipeInDateAndTime;
	private LocalDateTime swipeOutDateAndTime;
	private double journeyFare;

	*/

	//

}

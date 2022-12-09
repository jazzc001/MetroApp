package com.groupone.service;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;

public interface UserJourneyService {

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

}

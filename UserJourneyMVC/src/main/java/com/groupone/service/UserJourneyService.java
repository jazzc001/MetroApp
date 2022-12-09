package com.groupone.service;

import java.util.List;

import com.groupone.entity.Journey;

public interface UserJourneyService {

	Journey createNewJourney(Integer swipeInStation,Integer swipeOutStation);

	// List<Journey> getJourneyByUserID(Integer userId, Integer swipeInStationId, Integer swipeOutStationId);
	
}

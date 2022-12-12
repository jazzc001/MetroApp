package com.groupone.service;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;


import java.util.List;

public interface UserJourneyService {

	//Serach Journey List from userId
	List<Journey> searchJourneyByUserID(Integer userId);

	//Create a new Journey
	Boolean addJourney(Journey journey);
}

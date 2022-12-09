package com.groupone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.groupone.entity.Journey;
import com.groupone.persistence.JourneyDao;

@Service
public class UserJourneyServiceImpl implements UserJourneyService {

	@Autowired
	private JourneyDao journeyDao;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Journey createNewJourney(Integer swipeInStation, Integer swipeOutStation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Override public List<Journey> getJourneyByUserID(Integer userId, Integer
	 *           swipeInStationId, Integer swipeOutStationId) {
	 * 
	 *           List<Journey> journeyList = new ArrayList<Journey>();
	 * 
	 *           StationList stationList =
	 *           restTemplate.getForObject("http://localhost:8082/stations/" +
	 *           userId, StationList.class);
	 * 
	 *           for (Journey journey : journeyList) { Station swipeInStation =
	 *           restTemplate
	 *           .getForObject("http://localhost:8082/swipe-in-station/" +
	 *           swipeInStationId, Station.class); Station swipeOutStation =
	 *           restTemplate
	 *           .getForObject("http://localhost:8082/swipe-in-station/" +
	 *           swipeOutStationId, Station.class); }
	 * 
	 *           return journeyDao.searchJourneyByUserId(userId); }
	 * 
	 **/

}

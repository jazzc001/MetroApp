package com.groupone.service;

import com.groupone.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;
import com.groupone.persistence.JourneyDao;

@Service
public class UserJourneyServiceImpl implements UserJourneyService {

	@Autowired
	private JourneyDao journeyDao;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Journey createNewJourney(Station startStation, Station endStation, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String email, String password) {
		User user = restTemplate.getForObject("http://localhost:8082/users/{email}", User.class, email);

			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				return true;
		}
		return false;
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

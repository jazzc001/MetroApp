package com.groupone.service;

import com.groupone.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;
import com.groupone.persistence.JourneyDao;

<<<<<<< HEAD

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


=======
>>>>>>> 6238ccdba717beaf70e996964261bce89a4608ce
@Service
public class UserJourneyServiceImpl implements UserJourneyService {

	@Autowired
	private JourneyDao journeyDao;

	@Autowired
	private RestTemplate restTemplate;


	public List<Journey> searchJourneyByUserID(Integer userId) {

		List<Journey> journeyList = journeyDao.searchJourneyByUserId(userId);

		return journeyList;

<<<<<<< HEAD
	@Override
	public Boolean addJourney(Journey journey) {
		try{
			journeyDao.createJourney(journey.getJourneyId(),
					journey.getStationId(),
					journey.getUserId(),
					journey.getSwipeInStation(),
					journey.getSwipeOutStation(),
					journey.getSwipeInDateAndTime(),
					journey.getSwipeOutDateAndTime(), journey.getJourneyFare());
			return true;
		} catch (SQLIntegrityConstraintViolationException ex) {
			return false;
		} catch(Exception ex) {
			return false;
		}

	}

=======
	}
>>>>>>> 6238ccdba717beaf70e996964261bce89a4608ce

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

	@Override
	public boolean login(String email, String password) {
		// find customer object
		User user = restTemplate.getForObject("http://localhost:8080/users/{email}", User.class);
		if (user != null) {
			return true;
		}
		return false;
	}




}

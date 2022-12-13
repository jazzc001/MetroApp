package com.groupone.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;
import com.groupone.entity.User;
import com.groupone.persistence.JourneyDao;

@Service
public class UserJourneyServiceImpl implements UserJourneyService {

	@Autowired
	private JourneyDao journeyDao;

	@Autowired
	private RestTemplate restTemplate;

	/* ======= LOGIN ======== */
	@Override
	public boolean login(String email, String password) {
		// find customer object
		User user = restTemplate.getForObject("http://localhost:8080/users/{email}", User.class);
		if ((user != null)&&(password.equals(user.getPassword()))) {
			return true;
		}
		return false;
	}

	/* ======= SEARCH JOURNEY BY USER ID ======== */

	public List<Journey> searchJourneyByUserID(Integer userId) {
		List<Journey> journeyList = journeyDao.searchJourneyByUserId(userId);
		return journeyList;

	}

	/* ======= CREATE JOURNEY ======== */

	@Override
	public Journey createNewJourney(int userId, Station startStation, Station endStation) {
		User user = restTemplate.getForObject("http://localhost:8080/users/{userId}" + userId, User.class);

		double balance = user.getBalance();

		if (balance > 20) {

			startStation = restTemplate.getForObject("http://localhost:8082/station/name/{startstation}",
					Station.class);
			endStation = restTemplate.getForObject("http://localhost:8082/station/name/{endstation}", Station.class);
			int startStationId = startStation.getStationId();
			int endStationId = endStation.getStationId();

			double totalFare = calculateFare(startStationId, endStationId);
			LocalDateTime swipeInDateTime = LocalDateTime.now();
			LocalDateTime swipeOutDateTime = LocalDateTime.now();

			Journey journey = new Journey();

			journey.setSwipeInStation(startStation.getStationName());
			journey.setSwipeInStation(endStation.getStationName());
			journey.setSwipeInDateAndTime(swipeInDateTime);
			journey.setSwipeOutDateAndTime(swipeOutDateTime);
			journey.setJourneyFare(totalFare);

			return journey;
		} else {
			return null;
		}
	}

	/* ======= CALCULATE FARE ======== */

	public double calculateFare(int startStationId, int endStationId) {

		int noOfStops = Math.abs(startStationId - endStationId);
		double journeyFare = noOfStops * 5;
		return journeyFare;

	}

///* ======= UPDATE BALANCE ======== */

	@Override
	public boolean updateBalance(int userId, double remainingBalance, int startStationId, int endStationId) {
		User user = restTemplate.getForObject("http://localhost:8080/users/{userId}", User.class);

		if (user != null) {
			remainingBalance = user.getBalance() - calculateFare(startStationId, endStationId);
			user.setBalance(remainingBalance);
			return true;
		}
		return false;

	}
	/*===================Get Balance======================*/
	@Override
	public double getBalance(int userId) {
		User user = restTemplate.getForObject("http://localhost:8080/users/{userId}", User.class);
		return user.getBalance();

	}

	/* ======= TOP UP BALANCE ======== */

	public void topUpBalance(int userId, double topUpAmount) {
		User user = restTemplate.getForObject("http://localhost:8080/users/{userId}" + userId, User.class);
		double newBalance;

		if (user != null) {
			newBalance = user.getBalance() + topUpAmount;
			user.setBalance(newBalance);
		}
	}



}

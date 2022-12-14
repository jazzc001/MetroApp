package com.groupone.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
	public User login(String email, String password) {
		// find customer object
		User user = restTemplate.getForObject("http://localhost:8080/user/" + email + "/" + password, User.class);
		if (user != null)
			return user;
		return null;
	}

	/* ======= SEARCH JOURNEY BY USER ID ======== */

	public List<Journey> searchJourneyByUserID(Integer userId) {
		List<Journey> journeyList = journeyDao.searchJourneyByUserId(userId);
		return journeyList;

	}

	/* ======= CREATE JOURNEY ======== */

	@Override
	public Journey createNewJourney(int userId, Station startStation, Station endStation) {
		User user = restTemplate.getForObject("http://localhost:8080/user/id/" + userId, User.class);

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
		User user = restTemplate.getForObject("http://localhost:8080/users/id/" + userId, User.class);

		if (user != null) {
			remainingBalance = user.getBalance() - calculateFare(startStationId, endStationId);
			user.setBalance(remainingBalance);
			return true;
		}
		return false;

	}

	/* ===================Get Balance====================== */
	@Override
	public double getBalance(int userId) {
		User user = restTemplate.getForObject("http://localhost:8080/users/{userId}", User.class);
		return user.getBalance();

	}

	/* ======= TOP UP BALANCE ======== */

	public User topUpBalance(int userId, double topUpAmount) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<User> entity = new HttpEntity<User>(headers);

		User user = restTemplate.exchange("http://localhost:8080/user/id/" + userId + "/" + topUpAmount, HttpMethod.PUT,
				entity, User.class).getBody();
		try {
			if (user != null) {
				return user;
			} else
				return null;

		} catch (Exception ex) {
			return null;
		}

	}

	/* ======= CREATE NEW USER ======== */

	@Override
	public User createNewUser(String firstName, String lastName, String email, String password, double balance) {

		try {
			User newUser = new User();

			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setBalance(100);

			String postNewUser = restTemplate.postForObject("http://localhost:8080/newUser", newUser, String.class);

			if (postNewUser.equals("User Added")) {
				return newUser;
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}

	}

}

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
		user = restTemplate.getForObject("http://localhost:8080/user/id/" + userId, User.class);

		try {
			if (user != null) {
				System.out.println("Service: " + user);
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
	/* ========== SWIPE IN ======= */

	@Override
	public Journey swipeIn(int userId, String startStationName) {

		Journey currentJourney = new Journey();

		Station station = restTemplate.getForObject("http://localhost:8082/station/name/" + startStationName,
				Station.class);
		startStationName = station.getStationName();

		currentJourney.setUserId(userId);
		currentJourney.setSwipeInStation(startStationName);
		currentJourney.setSwipeInDateAndTime(LocalDateTime.now());
		journeyDao.save(currentJourney);
		return currentJourney;
	}

	@Override
	public Journey swipeOut(int journeyId, String endStationName) {

		Station station = restTemplate.getForObject("http://localhost:8082/station/name/" + endStationName,
				Station.class);
		endStationName = station.getStationName();

		Journey currentJourney = journeyDao.searchJourneyByJourneyId(journeyId);
		currentJourney.setSwipeOutStation(endStationName);
		currentJourney.setSwipeOutDateAndTime(LocalDateTime.now());

		journeyDao.save(currentJourney);

		return currentJourney;
	}

	/* ======= CALCULATE FARE ======== */

	public double calculateFare(int journeyId, String startStationName, String endStationName) {

		Station startStation = restTemplate.getForObject("http://localhost:8082/station/name/" + startStationName,
				Station.class);
		Station endStation = restTemplate.getForObject("http://localhost:8082/station/name/" + endStationName,
				Station.class);

		Journey currentJourney = journeyDao.searchJourneyByJourneyId(journeyId);
		int startStationId = startStation.getStationId();
		int endStationId = endStation.getStationId();

		int noOfStops = Math.abs(startStationId - endStationId);
		double journeyFare = noOfStops * 5;

		currentJourney.setJourneyFare(journeyFare);
		journeyDao.save(currentJourney);

		return journeyFare;

	}

	/* ======= UPDATE BALANCE ======== */

	@Override
	public User updateBalance(int userId, double fare) {

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<User> entity = new HttpEntity<User>(headers);

		User user = restTemplate
				.exchange("http://localhost:8080/user/" + userId + "/" + fare, HttpMethod.PUT, entity, User.class)
				.getBody();

		if (user != null) {
			return user;
		} else {
			return null;
		}

	}

}

package com.groupone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;
import com.groupone.entity.User;
import com.groupone.persistence.JourneyDao;

@RunWith(MockitoJUnitRunner.class)
class UserJourneyServiceImplTest {

	@InjectMocks
	private UserJourneyServiceImpl userJourneyServiceImpl;

	@Mock
	private JourneyDao journeyDao;

	@Mock
	private RestTemplate restTemplate;

	private AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void searchJourneyByUserIDPositiveTest() {
		List<Journey> mockJourneyList = new ArrayList<Journey>();
		mockJourneyList.add(new Journey(111, 101, "London", "Exeter", LocalDateTime.now(), LocalDateTime.now(), 10.05));
		mockJourneyList
				.add(new Journey(222, 101, "Colindale", "Glasgow", LocalDateTime.now(), LocalDateTime.now(), 20.05));
		mockJourneyList.add(new Journey(333, 102, "Paris", "Sydney", LocalDateTime.now(), LocalDateTime.now(), 30.15));

		List<Journey> expectedJourneyList = new ArrayList<Journey>();
		mockJourneyList.add(new Journey(111, 101, "London", "Exeter", LocalDateTime.now(), LocalDateTime.now(), 10.05));
		mockJourneyList
				.add(new Journey(222, 101, "Colindale", "Glasgow", LocalDateTime.now(), LocalDateTime.now(), 20.05));

		when(journeyDao.searchJourneyByUserId(101)).thenReturn(expectedJourneyList);

	}

	@Test
	void searchJourneyByUserIDNegativeTest() {
		List<Journey> mockJourneyList = new ArrayList<Journey>();
		mockJourneyList.add(new Journey(111, 101, "London", "Exeter", LocalDateTime.now(), LocalDateTime.now(), 10.05));
		mockJourneyList
				.add(new Journey(222, 101, "Colindale", "Glasgow", LocalDateTime.now(), LocalDateTime.now(), 20.05));
		mockJourneyList.add(new Journey(333, 102, "Paris", "Sydney", LocalDateTime.now(), LocalDateTime.now(), 30.15));

		List<Journey> expectedJourneyList = new ArrayList<Journey>();
		mockJourneyList.add(new Journey(111, 101, "London", "Exeter", LocalDateTime.now(), LocalDateTime.now(), 10.05));
		mockJourneyList
				.add(new Journey(222, 101, "Colindale", "Glasgow", LocalDateTime.now(), LocalDateTime.now(), 20.05));

		when(journeyDao.searchJourneyByUserId(101)).thenReturn(expectedJourneyList);

	}

	@Test
	void calculateFareTest() {

		User mockUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100);
		Station startStation = new Station(1, "Chiswick");
		Station endStation = new Station(1, "London Bridge");

		Journey mockJourney = new Journey(123, 101, "Chiswick", "London Bridge", LocalDateTime.now(),
				LocalDateTime.now(), 30.05);
		Journey mockCurrentJourney = new Journey(123, 101, "Chiswick", "London Bridge", LocalDateTime.now(),
				LocalDateTime.now(), 30.05);

		when(restTemplate.getForObject("http://localhost:8082/station/name/" + startStation.getStationName(),
				Station.class)).thenReturn(startStation);
		when(restTemplate.getForObject("http://localhost:8082/station/name/" + endStation.getStationName(),
				Station.class)).thenReturn(endStation);
		when(journeyDao.searchJourneyByJourneyId(mockJourney.getJourneyId())).thenReturn(mockCurrentJourney);

		double expectedFare = userJourneyServiceImpl.calculateFare(mockJourney.getJourneyId(),
				startStation.getStationName(), endStation.getStationName());

		assertEquals(expectedFare, 0);
	}

	@Test
	void updateBalance() {

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<User> entity = new HttpEntity<User>(headers);

		User mockUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100);
		Journey mockJourney = new Journey(123, 101, "Chiswick", "London Bridge", LocalDateTime.now(),
				LocalDateTime.now(), 5);
		when(restTemplate.exchange(
				"http://localhost:8080/user/" + mockUser.getUserId() + "/" + mockJourney.getJourneyFare(),
				HttpMethod.PUT, entity, User.class))
				.thenReturn(new ResponseEntity<>(new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100),
						HttpStatus.OK));
		User expectedReturnUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100);
		assertEquals(mockUser, expectedReturnUser);
	}

	@Test
	void getBalance() {
		User mockUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100);
		when(restTemplate.getForObject("http://localhost:8080/users/{userId}", User.class)).thenReturn(mockUser);
		double mockBalance = userJourneyServiceImpl.getBalance(mockUser.getUserId());
		assertEquals(mockBalance, 100);
	}

	@Test
	void topUpBalance() {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<User> entity = new HttpEntity<User>(headers);
		User mockUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100);
		double mockTopUpAmount = 10;

		when(restTemplate.exchange("http://localhost:8080/user/id/" + mockUser.getUserId() + "/" + mockTopUpAmount,
				HttpMethod.PUT, entity, User.class))
				.thenReturn(new ResponseEntity<>(new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100),
						HttpStatus.OK));
		User expectedUser = userJourneyServiceImpl.topUpBalance(mockUser.getUserId(), mockTopUpAmount);
		assertEquals(mockUser, expectedUser);
	}

	@Test
	void createNewUserTest() {
		User mockUser = new User(0, "Jasmine", "Chan", "j@gmail.com", "1234", 100);

		when(restTemplate.postForObject("http://localhost:8080/newUser", mockUser, String.class))
				.thenReturn("User Added");
		User expectUser = userJourneyServiceImpl.createNewUser(mockUser.getFirstName(), mockUser.getLastName(),
				mockUser.getEmail(), mockUser.getPassword(), mockUser.getBalance());
		assertEquals(mockUser, expectUser);

		// when(employeedao.insertRecord(new Employee(110, "JJJJ", "Exdecutive",
		// "Sales", 23000, LocalDate.now()))).thenReturn(1);
		//
		// assertTrue(employeeServiceImpl.addEmployee(new Employee(110, "JJJJ",
		// "Exdecutive", "Sales", 23000, LocalDate.now())));
	}
}
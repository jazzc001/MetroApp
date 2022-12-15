package com.groupone.service;

import com.groupone.entity.Journey;
import com.groupone.entity.Station;
import com.groupone.entity.User;
import com.groupone.persistence.JourneyDao;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserJourneyServiceImplTest {

    @InjectMocks
    private UserJourneyServiceImpl userJourneyServiceImpl;

    @Mock
    private JourneyDao journeyDao;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    HttpHeaders headers;

    private AutoCloseable  autoCloseable;

    @BeforeEach
    void setUp() throws Exception {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void searchJourneyByUserID() {
        List<Journey> mockJourneyList = new ArrayList<Journey>();
        mockJourneyList.add(new Journey(111, 101, "London", "Exeter", LocalDateTime.now(), LocalDateTime.now(), 10.05));
        mockJourneyList.add(new Journey(222, 101, "Colindale", "Glasgow", LocalDateTime.now(), LocalDateTime.now(), 20.05));
        mockJourneyList.add(new Journey(333, 102, "Paris", "Sydney", LocalDateTime.now(), LocalDateTime.now(), 30.15));

        List<Journey> expectedJourneyList = new ArrayList<Journey>();
        mockJourneyList.add(new Journey(111, 101, "London", "Exeter", LocalDateTime.now(), LocalDateTime.now(), 10.05));
        mockJourneyList.add(new Journey(222, 101, "Colindale", "Glasgow", LocalDateTime.now(), LocalDateTime.now(), 20.05));

        when(journeyDao.searchJourneyByUserId(101)).thenReturn(expectedJourneyList);

    }

    @Test
    void createNewJourney() {
        User mockUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100);
        Station startStation = new Station(1, "Chiswick");
        Station endStation = new Station(1, "Chiswick");

        Journey mockJourney = new Journey(123,101,"Chiswick", "London Bridge", LocalDateTime.now(), LocalDateTime.now(), 5);

        //mocking behaviour in caluculate fare
        when(restTemplate.getForObject("http://localhost:8082/station/name/" + startStation.getStationName(), Station.class)).thenReturn(startStation);
        when(restTemplate.getForObject("http://localhost:8082/station/name/" + endStation.getStationName(), Station.class)).thenReturn(endStation);
        when(journeyDao.searchJourneyByJourneyId(mockJourney.getJourneyId())).thenReturn(mockJourney);

        when(restTemplate.getForObject("http://localhost:8080/user/id/" + mockUser.getUserId(), User.class)).thenReturn(mockUser);
        when(restTemplate.getForObject("http://localhost:8080/users/{userId}", User.class)).thenReturn(mockUser);


        when(restTemplate.getForObject("http://localhost:8082/station/name/{startstation}", Station.class)).thenReturn(startStation);
        when(restTemplate.getForObject("http://localhost:8082/station/name/{endstation}", Station.class)).thenReturn(endStation);




        Journey expectedJourney = userJourneyServiceImpl.createNewJourney(mockUser.getUserId(), startStation, endStation);

        assertEquals(expectedJourney, mockJourney);
    }

    @Test
    void calculateFareTest() {

        User mockUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100);
        Station startStation = new Station(1, "Chiswick");
        Station endStation = new Station(1, "London Bridge");

        Journey mockJourney = new Journey(123,101,"Chiswick", "London Bridge", LocalDateTime.now(), LocalDateTime.now(), 30.05 );
        Journey mockCurrentJourney = new Journey(123,101,"Chiswick", "London Bridge", LocalDateTime.now(), LocalDateTime.now(), 30.05 );


        when(restTemplate.getForObject("http://localhost:8082/station/name/" + startStation.getStationName(), Station.class)).thenReturn(startStation);
        when(restTemplate.getForObject("http://localhost:8082/station/name/" + endStation.getStationName(), Station.class)).thenReturn(endStation);
        when(journeyDao.searchJourneyByJourneyId(mockJourney.getJourneyId())).thenReturn(mockCurrentJourney);

        double expectedFare = userJourneyServiceImpl.calculateFare(mockJourney.getJourneyId(), startStation.getStationName(), endStation.getStationName());

        assertEquals(expectedFare, 0);
    }

    @Test
    void updateBalance() {

        HttpEntity<User> entity = new HttpEntity<User>(headers);



        User mockUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 100);
        Journey mockJourney = new Journey(123,101,"Chiswick", "London Bridge", LocalDateTime.now(), LocalDateTime.now(), 5 );
        when(restTemplate.exchange("http://localhost:8080/user/" + mockUser.getUserId() + "/" + mockJourney.getJourneyFare(), HttpMethod.PUT,
                entity, User.class).getBody()).thenReturn(mockUser);
        User expectedReturnUser = new User(101, "Jasmine", "Chan", "j@gmail.com", "1234", 95);
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
    }

    @Test
    void createNewUser() {
    }
}
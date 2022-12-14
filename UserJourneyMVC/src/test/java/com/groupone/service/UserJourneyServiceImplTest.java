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

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserJourneyServiceImplTest {

    @InjectMocks
    private UserJourneyServiceImpl userJourneyServiceImpl;

    @Mock
    private JourneyDao journeyDao;

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


//        try {
//            when(journeyDao.createJourney(123,101,101,"Chiswick", "London Bridge", LocalDateTime.now(), LocalDateTime.now(), 30.05 )).thenReturn(1);
//        } catch (SQLIntegrityConstraintViolationException e) {
//            throw new RuntimeException(e);
//        }
            Station start = new Station(1, "Chiswick");
        Station end = new Station(1, "Chiswick");

            Journey mockJourney = new Journey(123,101,"Chiswick", "London Bridge", LocalDateTime.now(), LocalDateTime.now(), 30.05 );
            when(userJourneyServiceImpl.createNewJourney(101, start, end)).thenReturn(mockJourney);
    }

    //@Test
    //	void testAddEmployeeOne() {
    //		//Specifying the behavior of the mock
    //		when(employeedao.insertRecord(new Employee(110, "JJJJ", "Exdecutive", "Sales", 23000, LocalDate.now()))).thenReturn(1);
    //
    //		assertTrue(employeeServiceImpl.addEmployee(new Employee(110, "JJJJ", "Exdecutive", "Sales", 23000, LocalDate.now())));

    @Test
    void calculateFare() {
    }

    @Test
    void updateBalance() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void topUpBalance() {
    }

    @Test
    void createNewUser() {
    }
}
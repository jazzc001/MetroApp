package com.groupOne.stationService;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.groupOne.stationService.entity.Station;
import com.groupOne.stationService.persistence.StationDao;
import com.groupOne.stationService.service.StationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class StationServiceApplicationTests {

	@InjectMocks
	private StationServiceImpl stationServiceImpl;
	@Mock
	private StationDao stationDao;
	private AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	@DisplayName("Search Station By ID - Positive Scenario")
	void testSearchStationByIdPositive() {
		Station station = new Station(4, "Chiswick");
		when(stationDao.searchStationByStationId(4)).thenReturn(station);
		assertEquals(station, stationServiceImpl.searchStationByStationId(4));
	}

	@Test
	@DisplayName("Search Station By ID - Negative Scenario")
	void testSearchStationByIdNegative() {
		when(stationDao.searchStationByStationId(10)).thenReturn(null);
		assertNull("Station ID does not exist", stationServiceImpl.searchStationByStationId(10));

	}

	@Test
	@DisplayName("Search Station By Name - Positive Scenario")
	void testSearchStationByNamePositive() {
		Station station = new Station(1, "Kings-Cross");
		when(stationDao.searchStationByStationName("Kings-Cross")).thenReturn(station);
		assertEquals(station, stationServiceImpl.searchStationByStationName("Kings-Cross"));
	}

	@Test
	@DisplayName("Search Station By Name - Negative Scenario")
	void testSearchStationByNameNegative() {
		when(stationDao.searchStationByStationName("Oxford")).thenReturn(null);
		assertNull("Station does not exist", stationServiceImpl.searchStationByStationName("Oxford"));

	}

}

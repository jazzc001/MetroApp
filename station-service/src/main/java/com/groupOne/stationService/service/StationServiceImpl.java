package com.groupOne.stationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupOne.stationService.entity.Station;
import com.groupOne.stationService.persistence.StationDao;

import lombok.Setter;

@Service
public class StationServiceImpl implements StationService {

	@Setter
	@Autowired
	private StationDao stationDao;

	@Override
	public Station searchStationByStationId(int stationId) {
		return stationDao.searchStationByStationId(stationId);
	}

	@Override
	public Station searchStationByStationName(String stationName) {
		return stationDao.searchStationByStationName(stationName);
	}

}

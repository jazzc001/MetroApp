package com.groupOne.stationService.service;

import com.groupOne.stationService.entity.Station;

public interface StationService {
	
    Station searchStationByStationId(int stationId);

	Station searchStationByStationName(String stationName);

}

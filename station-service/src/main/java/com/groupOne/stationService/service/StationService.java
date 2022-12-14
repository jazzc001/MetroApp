package com.groupOne.stationService.service;

import com.groupOne.stationService.entity.Station;
import com.groupOne.stationService.entity.StationList;

public interface StationService {
	
    Station searchStationByStationId(int stationId);

	Station searchStationByStationName(String stationName);

}

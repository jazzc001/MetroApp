package com.groupOne.stationService.service;

import com.groupOne.stationService.entity.Station;

import java.util.List;

public interface StationService {

    public Station searchStationByStationId(int stationId);

	public Station searchStationByStationName(String stationName);

}

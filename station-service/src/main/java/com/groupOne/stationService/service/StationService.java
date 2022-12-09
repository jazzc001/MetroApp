package com.groupOne.stationService.service;

import com.groupOne.stationService.entity.Station;

import java.util.List;

public interface StationService {

    public List<Station> searchStationByStationId(int stationId);

}

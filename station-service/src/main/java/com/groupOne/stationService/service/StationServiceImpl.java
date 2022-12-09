package com.groupOne.stationService.service;

import com.groupOne.stationService.entity.Station;
import com.groupOne.stationService.persistence.StationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService{

    @Autowired
    private StationDao stationDao;


    @Override
    public List<Station> searchStationByStationId(int stationId) {
        return stationDao.searchStationByStationId(stationId);
    }

}

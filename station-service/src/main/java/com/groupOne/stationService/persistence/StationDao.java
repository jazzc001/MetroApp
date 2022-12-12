package com.groupOne.stationService.persistence;

import com.groupOne.stationService.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationDao extends JpaRepository<Station, Integer> {

    public Station searchStationByStationId(int stationId);
    
    public Station searchStationByStationName(String stationName);

}

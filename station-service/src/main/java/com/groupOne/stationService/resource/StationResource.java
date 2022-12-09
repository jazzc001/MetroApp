package com.groupOne.stationService.resource;

import com.groupOne.stationService.entity.StationList;
import com.groupOne.stationService.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationResource {

    @Autowired
    private StationService stationService;


    @GetMapping(path = "/stations/{statId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StationList searchStationByStationIdResource(@PathVariable("statId") int stationId) {
        return new StationList(stationService.searchStationByStationId(stationId));
    }

}
package com.groupOne.stationService.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.groupOne.stationService.entity.Station;
import com.groupOne.stationService.service.StationService;

@RestController
public class StationResource {

	@Autowired
	private StationService stationService;

	@RequestMapping(path = "/station/{statId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Station searchStationByStationIdResource(@PathVariable("statId") int statId) {
		return stationService.searchStationByStationId(statId);
	}

	@RequestMapping(path = "/station/name/{statName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Station searchStationByStationNameResource(@PathVariable("statName") String stationName) {
		return stationService.searchStationByStationName(stationName);
	}

}
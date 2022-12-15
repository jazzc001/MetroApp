package com.groupone.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.groupone.entity.JourneyList;
import com.groupone.service.UserJourneyService;

@RestController
public class UserJourneyResource {

	@Autowired
	private UserJourneyService userJourneyService;

	@GetMapping(path = "/journeys/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JourneyList searchJourneyByUserIdResource(@PathVariable("userId") Integer userId) {
		return new JourneyList(userJourneyService.searchJourneyByUserID(userId));
	}

}

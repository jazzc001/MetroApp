package com.groupone.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.groupone.entity.JourneyList;
import com.groupone.service.UserJourneyService;

@Controller
public class UserJourneyContoller {
	private UserJourneyService userJourneyService;

	@GetMapping(path = "/journeys/{iId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JourneyList searchJourneyByUserIdResource(@PathVariable("uId") Integer userId) {
		return new JourneyList(userJourneyService.searchJourneyByUserID(userId));
	}

	// ----------------------------------SWIP IN STATION
	// CONTROLLER---------------------------------
	@RequestMapping("/swipein")
	public ModelAndView swipeInController(String station) {
		ModelAndView modelAndView = new ModelAndView();

		return modelAndView;
	}

	// ----------------------------------SWIP OUT STATION
	// CONTROLLER---------------------------------
	@RequestMapping("/swipeout")
	public ModelAndView swipeOutController(String station) {
		ModelAndView modelAndView = new ModelAndView();

		return modelAndView;
	}
	/* ====== FARES CONTROLLER ====== */

	@RequestMapping("/fares")
	public ModelAndView faresPageController() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Fares");
		return modelAndView;
	}

}

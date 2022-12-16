package com.groupone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.groupone.service.UserJourneyService;

@Controller
public class UserJourneyContoller {
	
	@Autowired
	private UserJourneyService userJourneyService;
	
//	/* ====== SWIPE IN CONTROLLER ====== */
//	
//	@RequestMapping("/swipeIn")
//	public ModelAndView swipeInPageController(String station) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("SwipeInStation");
//		return modelAndView;
//	}
//	
//	@RequestMapping("/swipe-in")
//	public ModelAndView swipeInController(String station, HttpSession session) {
//		ModelAndView modelAndView = new ModelAndView();
//		
//		User user = (User)session.getAttribute(user);
//		
//		userJourneyService.swipeIn(0, station);
//		
//		
//		
//		return modelAndView;
//	}

	/* ====== SWIPE OUT CONTROLLER ====== */
//	
//	@RequestMapping("/swipeOut")
//	public ModelAndView swipeOutController(String station) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("SwipeOutStation");
//
//		return modelAndView;
//	}
	/* ====== FARES CONTROLLER ====== */

//	@RequestMapping("/fares")
//	public ModelAndView faresPageController() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("Fares");
//		return modelAndView;
//	}
//
}

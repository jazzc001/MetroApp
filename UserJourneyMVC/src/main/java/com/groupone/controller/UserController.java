package com.groupone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.groupone.service.UserJourneyService;

@Controller
public class UserController {

	@Autowired
	private UserJourneyService userJourneyService;

	// -------------------------------------------------------------------LOGIN
	// CONTROLLER------------------------------------------------------------------------
	@RequestMapping("/")
	public ModelAndView loginPageController() {
		return new ModelAndView("login");
	}

	@RequestMapping("/login")
	public ModelAndView loginController(@RequestParam("email") String email,
			@RequestParam("password") String password) {

		// instantiate empty/blank MAV object
		ModelAndView modelAndView = new ModelAndView();

		// if login is correct
		if (userJourneyService.login(email, password)) {
			modelAndView.setViewName("Dashboard");
		}
		// if login check is failed
		else {
			// display message on selected view
			modelAndView.addObject("message", "Unable to Login, either your password or email is incorrect.");
			// show the Login page on the web app
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

}

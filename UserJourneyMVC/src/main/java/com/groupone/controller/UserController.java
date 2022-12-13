package com.groupone.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.groupone.entity.User;
import com.groupone.service.UserJourneyService;

@Controller
public class UserController {

	@Autowired
	private UserJourneyService userJourneyService;

	@RequestMapping("/")
	public ModelAndView welcomePageController() {
		return new ModelAndView("HomePage");
	}

	/* ====== LOGIN CONTROLLER ====== */

	@RequestMapping("/loginPage")
	public ModelAndView loginPageController() {
		return new ModelAndView("loginPage", "user", new User());
	}

	@RequestMapping("/login")
	public ModelAndView loginController(@ModelAttribute("user") User user, @RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		if (userJourneyService.login(email, password)) {
			modelAndView.addObject("user", user);
			session.setAttribute("user", user);
			modelAndView.setViewName("Dashboard");
		} else {
			modelAndView.addObject("Invalid User Credentials, Please try again");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("loginPage");
		}

		return modelAndView;
	}

	/* ====== REGISTER NEW USER CONTROLLER ====== */

	@RequestMapping("/registerPage")
	public ModelAndView registerPageController() {
		return new ModelAndView("RegisterPage", "user", new User());
	}

	@RequestMapping("/register")
	public ModelAndView registerController(@ModelAttribute("user") User newUser, @RequestParam("email") String email,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("pass") String password, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;

		newUser.setBalance(100);

		if (userJourneyService.createNewUser(email, firstName, lastName, password, newUser.getBalance())) {
			message = "Thanks for Signing Up";

		} else {
			message = "Sign up failed!";
		}

		modelAndView.addObject("message", message);
		modelAndView.setViewName("LoginPage");

		return modelAndView;

	}

	/* ======== DASHBOARD CONTROLLER ======= */

	public ModelAndView dashboardController(@RequestParam("userId") int userId) {
		ModelAndView modelAndView = new ModelAndView("Dashboard");
		modelAndView.addObject("balance", userJourneyService.getBalance(userId));
		return new ModelAndView("Dashboard");
	}

}

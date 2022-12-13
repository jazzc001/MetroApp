package com.groupone.controller;

import javax.servlet.http.HttpServletRequest;
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
			modelAndView.addObject("message", "Invalid User Credentials, Please try again");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("loginPage");
		}

		return modelAndView;
	}

	/* ====== REGISTER NEW USER CONTROLLER ====== */

	@RequestMapping("/registerPage")
	public ModelAndView registerPageController() {
		return new ModelAndView("registerPage", "user", new User());
	}

	@RequestMapping("/register")
	public ModelAndView registerController(@ModelAttribute("user") User newUser, @RequestParam("email") String email,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("password") String password) {
		ModelAndView modelAndView = new ModelAndView();
		String message;

		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setBalance(100);

		if (userJourneyService.createNewUser(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(),
				newUser.getPassword(), newUser.getBalance()) != null) {
			modelAndView.setViewName("loginPage");
			modelAndView.addObject("newUser", newUser);
		} else {
			message = "FAIL";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("HomePage");
		}

		return modelAndView;

	}

	/* ======== DASHBOARD CONTROLLER ======= */

	public ModelAndView dashboardController(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		// getting User from session
		user = (User)session.getAttribute("user");

		user.getBalance();
		user.getFirstName();

		modelAndView.addObject("user", user);
		modelAndView.setViewName("Dashboard");

		return modelAndView;
	}

	/* ======== TOP UP CONTROLLER ======= */
	@RequestMapping("/topUpPage")
	public ModelAndView topUpPageController() {
		return new ModelAndView("TopUpPage");
	}

	@RequestMapping("/topUp")
	public ModelAndView topUpController(@RequestParam("topUpAmount") double topUpAmount, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String message;
		
		int userId = ((User)session.getAttribute("user")).getUserId();

		if (userJourneyService.topUpBalance(userId, topUpAmount) != null) {
			message = "Your account has been increased by " + topUpAmount;
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");

		} else {
			message = topUpAmount + " could not be added to your account, please try again!";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("TopUpPage");

		}

		return modelAndView;

		/*
		 * @RequestMapping("/transferFunds") public ModelAndView
		 * transferFundsController(@RequestParam("accountId")int
		 * recepientAccountId,@RequestParam("amount") double balance,HttpSession
		 * session) { ModelAndView modelAndView=new ModelAndView();
		 * 
		 * int myAccountId=((Customer)session.getAttribute("customer")).getAccountId();
		 * Customer customer=customerService.transferFunds(myAccountId,
		 * recepientAccountId,balance ); if(customer==null) {
		 * modelAndView.addObject("message", "Transaction Failed");
		 * session.setAttribute("customer", customer); }else
		 * modelAndView.addObject("message",
		 * "Your Account has been debited with balance "
		 * +balance+" and credited in Account No"
		 * +recepientAccountId+" and your current Balance is "+customer.
		 * getCustomerBalance());
		 * 
		 * modelAndView.setViewName("Output"); return modelAndView; }
		 * 
		 */

		/*
		 * modelAndView.addObject("balance", "Your current balance is: " +
		 * userJourneyService.getBalance(userId) + ". Top up more below."); // Tops up
		 * balance userJourneyService.topUpBalance(userId, topUpAmount); // Adds a
		 * message saying how much you have topped up by
		 * modelAndView.addObject("message", "You have updated the balance by " +
		 * topUpAmount); return modelAndView;
		 */
	}
}

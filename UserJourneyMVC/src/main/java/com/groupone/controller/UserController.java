package com.groupone.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.groupone.entity.Journey;
import com.groupone.entity.JourneyList;
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
	public ModelAndView loginController(@RequestParam("email") String email, @RequestParam("password") String pass,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		User user = userJourneyService.login(email, pass);

		if (user != null) {
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
		user = (User) session.getAttribute("user");

		user.getBalance();
		user.getFirstName();

		modelAndView.addObject("user", user);
		modelAndView.setViewName("Dashboard");

		return modelAndView;
	}

	/* ======== FARES CONTROLLER ======= */

	@RequestMapping("/fares")
	public ModelAndView faresPageController() {
		return new ModelAndView("Fares");
	}

	/* ======== LOGOUT CONTROLLER ======= */

	@RequestMapping("/logOut")
	public ModelAndView logoutPageController(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		session.removeAttribute("user");

		String message = "Thank you for using MetroApp";
		modelAndView.addObject("message", message);
		modelAndView.setViewName("HomePage");

		return modelAndView;

	}

	/* ======== PAST JOURNEY CONTROLLER ======= */

	@RequestMapping("/pastJourneys")
	public ModelAndView pastJourneysPageController(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		User user = ((User) session.getAttribute("user"));
		List<Journey> journeyList = userJourneyService.searchJourneyByUserID(user.getUserId());
		if (journeyList != null) {
			modelAndView.addObject("journeyList", journeyList);
			modelAndView.setViewName("PastJourney");
		} else {
			modelAndView.addObject("message", "You have made no journeys");
			modelAndView.setViewName("PastJourney");
		}
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

		User user = ((User) session.getAttribute("user"));
		if (userJourneyService.topUpBalance(user.getUserId(), topUpAmount) != null) {
			message = "Your account has been increased by " + topUpAmount;

		} else {
			message = topUpAmount + " could not be added to your account, please try again!";

		}

		session.setAttribute("user", user);
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Dashboard");

		return modelAndView;

	}

	@RequestMapping("/swipeInPage")
	public ModelAndView swipeInPageController(String station) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("SwipeInStation");
		return modelAndView;
	}

	@RequestMapping("/swipeIn")
	public ModelAndView swipeInController(String station, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String message;

		User user = ((User) session.getAttribute("user"));
		if (userJourneyService.swipeIn(user.getUserId(), station) != null) {
			if (user.getBalance() > 20) {
				Journey journey = userJourneyService.swipeIn(user.getUserId(), station);
				session.setAttribute("journey", journey);
				message = "You have successfully swiped in at " + station;
			} else {
				message = "You need to top up your account";
			}
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Dashboard");
		}

		return modelAndView;
	}

	@RequestMapping("/swipeOutPage")
	public ModelAndView swipeOutPageController(String station) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("SwipeOutStation");
		return modelAndView;
	}

	@RequestMapping("/swipeOut")
	public ModelAndView swipeOutController(String station, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String message;

		Journey journey = ((Journey) session.getAttribute("journey"));
		journey.setSwipeOutStation(station);

		if (userJourneyService.swipeOut(journey.getJourneyId(), station) != null) {
			double fare = userJourneyService.calculateFare(journey.getJourneyId(), journey.getSwipeInStation(),
					journey.getSwipeOutStation());
			User updateBalance = userJourneyService.updateBalance(journey.getUserId(), fare);
			message = "You have successfully swiped out at " + station + " and your journey cost is " + fare
					+ " and your remaining balance is  " + updateBalance.getBalance();
		} else {
			message = "You have not swiped out, please try again!";
		}

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Dashboard");
		return modelAndView;

	}
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

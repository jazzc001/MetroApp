package com.groupone.controller;


import com.groupone.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.groupone.entity.JourneyList;
import com.groupone.service.UserJourneyService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserJourneyContoller {
    private UserJourneyService userJourneyService;

    @GetMapping(path = "/journeys/{iId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JourneyList searchJourneyByUserIdResource(@PathVariable("uId") Integer userId) {
        return new JourneyList(userJourneyService.searchJourneyByUserID(userId));
    }

    //----------------------------------TOP UP PAGE CONTROLLER---------------------------------
    @RequestMapping("/topup")
    public ModelAndView topUpController(@RequestParam("userId") int userId, @RequestParam("top up amount")double topUpAmount) {
        //instantiate empty MAV
        ModelAndView modelAndView = new ModelAndView();
        //Provide current balance
        modelAndView.addObject("balance", "Your current balance is: "+userJourneyService.getBalance(userId)+". Top up more below.");
        //Tops up balance
        userJourneyService.topUpBalance(userId, topUpAmount);
        //Adds a message saying how much you have topped up by
        modelAndView.addObject("message", "You have updated the balance by " + topUpAmount);
        return modelAndView;
    }

    //----------------------------------SWIP IN STATION CONTROLLER---------------------------------
    @RequestMapping("/swipein")
    public ModelAndView swipeInController(String station) {
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

    //----------------------------------SWIP OUT STATION CONTROLLER---------------------------------
    @RequestMapping("/swipeout")
    public ModelAndView swipeOutController(String station) {
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

}

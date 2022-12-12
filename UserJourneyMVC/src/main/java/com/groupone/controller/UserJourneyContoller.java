package com.groupone.controller;


import com.groupone.entity.JourneyList;
import com.groupone.service.UserJourneyService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserJourneyContoller {
    private UserJourneyService userJourneyService;

    @GetMapping(path = "/journeys/{iId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JourneyList searchJourneyByUserIdResource(@PathVariable("uId") Integer userId) {
        return new JourneyList(userJourneyService.searchJourneyByUserID(userId));
    }
}

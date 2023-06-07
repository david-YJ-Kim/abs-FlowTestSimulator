package com.dk.abs.flowtestsimulator.controller;

import com.dk.abs.flowtestsimulator.manager.TestManager;
import com.dk.abs.flowtestsimulator.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    TestManager manager;

    @Autowired
    public EventController(TestManager testManager, VerificationService verificationService) {
        this.manager = testManager;
    }


    @RequestMapping(value = "/receive/request", method = RequestMethod.POST)
    public void receiveRequestEvent(@RequestBody String payload){
        this.manager.handleRequestEvent(payload);
    }

    @RequestMapping(value = "/receive/log", method = RequestMethod.POST)
    public void receiveLogEvent(@RequestBody String log){}
}

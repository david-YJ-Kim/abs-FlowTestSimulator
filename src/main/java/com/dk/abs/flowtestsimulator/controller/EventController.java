package com.dk.abs.flowtestsimulator.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {


    @RequestMapping(value = "/receive/request", method = RequestMethod.POST)
    public void receiveRequestEvent(@RequestBody String payload){}

    @RequestMapping(value = "/receive/log", method = RequestMethod.POST)
    public void receiveLogEvent(@RequestBody String log){}
}

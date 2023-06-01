package com.dk.abs.flowtestsimulator.controller;

import com.dk.abs.flowtestsimulator.manager.TestManager;
import com.dk.abs.flowtestsimulator.service.ManageService;
import com.dk.abs.flowtestsimulator.service.MockingService;
import com.dk.abs.flowtestsimulator.service.VerificationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class ManageController {

    ManageService manageService;
    @Autowired
    public ManageController(ManageService manageService) {
        this.manageService = manageService;
    }

    @RequestMapping(value = "/test/start", method = RequestMethod.POST)
    public void startTest(@RequestBody String confJson){
        manageService.prepareTest(new JSONObject(confJson));
    }

}

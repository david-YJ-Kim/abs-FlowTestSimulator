package com.dk.abs.flowtestsimulator.controller;

import com.dk.abs.flowtestsimulator.manager.TestManager;
import com.dk.abs.flowtestsimulator.service.ManageService;
import com.dk.abs.flowtestsimulator.vo.ivo.ManageConfIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class ManageController {

    private final TestManager manager;

    ManageService manageService;
    @Autowired
    public ManageController(TestManager testManager, ManageService manageService) {

        this.manager = testManager;
        this.manageService = manageService;
    }

    @RequestMapping(value = "/test/start", method = RequestMethod.POST)
    public void startTest(@RequestBody String confJson){
        manager.initializeTest(confJson);
    }

}

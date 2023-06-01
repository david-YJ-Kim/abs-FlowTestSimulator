package com.dk.abs.flowtestsimulator.service;

import com.dk.abs.flowtestsimulator.util.MemoryStorage;
import com.dk.abs.flowtestsimulator.vo.ConfigurationVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageService {

    private final MemoryStorage storage;

    @Autowired
    public ManageService(MemoryStorage storage){
        this.storage = storage;
    }

    public String prepareTest(JSONObject jsonObject){

        // 1. generate Configuration VO.
        ConfigurationVO vo = new ConfigurationVO(jsonObject);
        String testRound = vo.getTestRound();

        // 2. save vo in memory storage.

        // 3. get initialize message
        return "Message to init WFS";
    }


}

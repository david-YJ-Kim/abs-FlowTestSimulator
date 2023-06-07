package com.dk.abs.flowtestsimulator.service;

import com.dk.abs.flowtestsimulator.manager.TestManager;
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

    public ConfigurationVO generateConfigurationVO (JSONObject jsonObject, TestManager manager){

        // 1. generate Configuration VO.
        return new ConfigurationVO(jsonObject, manager);

    }




}

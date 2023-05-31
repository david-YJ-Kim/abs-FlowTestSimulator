package com.dk.abs.flowtestsimulator.service;

import com.dk.abs.flowtestsimulator.manager.TestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
    // Service which handle Task Group, Validation

    private final TestManager testManager;
    @Autowired
    public VerificationService(TestManager testManager){
        this.testManager = testManager;
    }

    /**
     * 진입된 메시지의 원천 시스템이 현재 Simulating 해야하는 시스템인지
     * @param requestSystem
     * @return
     */
    public boolean isMockingSystem(String requestSystem){
        return !testManager.isSystemExist(requestSystem);
    }
}

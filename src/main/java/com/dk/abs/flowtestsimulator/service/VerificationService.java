package com.dk.abs.flowtestsimulator.service;

import com.dk.abs.flowtestsimulator.manager.TestManager;
import com.dk.abs.flowtestsimulator.util.CustomCode;
import com.dk.abs.flowtestsimulator.util.MemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
    // Service which handle Task Group, Validation

    private final MemoryStorage storage;

    @Autowired
    public VerificationService(MemoryStorage storage){

        this.storage = storage;
    }


    public boolean isTestMessage(String messageId){
        // Test Message Naming Rule.
        return messageId.startsWith(CustomCode.TST.name());
    }

    public boolean isOperationCorrect(){
        return false;
    }

    /**
     * 진입된 메시지의 원천 시스템이 현재 Simulating 해야하는 시스템인지
     * @param testRound
     * @return
     */
    public boolean isMockingSystem(String testRound, String targetSystem){

        return this.storage.search(testRound).getParticipatedSystemList().contains(targetSystem);
    }
}

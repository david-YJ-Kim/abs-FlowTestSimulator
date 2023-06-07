package com.dk.abs.flowtestsimulator.service;

import org.springframework.stereotype.Service;

@Service
public class RequestService {

    public void sendRequestMessage(String targetName){
        System.out.println("Request Message");
    }
}

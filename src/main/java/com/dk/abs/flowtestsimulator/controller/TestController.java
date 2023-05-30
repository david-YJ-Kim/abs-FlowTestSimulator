package com.dk.abs.flowtestsimulator.controller;

import com.dk.abs.flowtestsimulator.manager.TestManager;
import com.dk.abs.flowtestsimulator.util.CustomCode;
import com.dk.abs.flowtestsimulator.util.EventParsingUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestManager testManager;
    @Autowired
    public TestController(TestManager testManager){
        this.testManager = testManager;
    }

    @PostMapping
    public String receiveHookedEvent(@RequestBody String log){
        JSONObject eventObj = new JSONObject(log);
        boolean isEvent = EventParsingUtil.isTestEvent(eventObj.getJSONObject(CustomCode.head.name()));

        if(!isEvent){
            System.out.println("This is not event we want to parse. " + eventObj.toString());
        }else{
            // 1. 테스트 유효 확인 (Timer Action Time Out 발생 여부).
            // 2. Simulator System 여부 확인.
            // 3. 시나리오 위치 내 Event 위치 확인.
            // 4. 조건) 성공 시, Simulation 진행 == 다음 이벤트 생성 및 송신.
            // 5. Event Lost 업데이트.
            // 6. Action Timer Update.
        }

        return "Received message : " + eventObj.toString();
    }

    @GetMapping("/timer/start")
    public void startTimer(){testManager.startActionTimer();}

    @GetMapping("/timer/stop")
    public void stopTimer(){testManager.stopActionTimer();}

    @GetMapping("/timer/reset")
    public void resetTimer(){testManager.resetActionTimer();;}

}

package com.dk.abs.flowtestsimulator.controller;

import com.dk.abs.flowtestsimulator.manager.TestManager;
import com.dk.abs.flowtestsimulator.service.MockingService;
import com.dk.abs.flowtestsimulator.service.TestService;
import com.dk.abs.flowtestsimulator.service.VerificationService;
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
    VerificationService verificationService;
    MockingService mockingService;

    @Autowired
    public TestController(TestManager testManager, VerificationService verificationService, MockingService mockingService) {
        this.testManager = testManager;
        this.verificationService = verificationService;
        this.mockingService = mockingService;
    }


    @PostMapping("/test/start")
    public void startTest(@RequestBody String configJson){}


    @GetMapping("/test/start/file")
    public void startTest(){

    }

    @PostMapping
    public String receiveHookedEvent(@RequestBody String log) {

        // [Parsing] 파싱
        JSONObject eventObj = new JSONObject(log);
        JSONObject headObj = eventObj.getJSONObject(CustomCode.head.name());
        JSONObject bodyObj = eventObj.getJSONObject(CustomCode.body.name());

        String eventId = headObj.getString(CustomCode.cid.name());
        String requestSystem = headObj.getString(CustomCode.src.name());

        // [Check] 테스트 메시지 확인
        boolean isEvent = EventParsingUtil.isTestEvent(eventId);
        boolean isTestOngoing = testManager.isTestOnGoing();

        if (!isEvent) {
            System.out.println("This is not event we want to parse. " + eventObj.toString());
        } else {

            // 1. 테스트 유효 확인 (Timer Action Time Out 발생 여부).
            if (!isTestOngoing) {
                System.out.println("Test has been over. It's fail.");
            } else {
                // 2. Simulator System 여부 확인.
                if(!verificationService.isMockingSystem(requestSystem)){
                    return null;
                }

                testManager.stopActionTimer();

                String nextEventNm = testManager.getNextEventNm();
                // 3. 시나리오 위치 내 Event 위치 확인.
                // 3-1. 시나리오 맨 처음 진입 시, 기존 ArrayList size = 0; ==> 'INIT' 초기화
                if(!testManager.getLastEvent().equals(CustomCode.INIT.name())){
                    return null;

                }else if (!nextEventNm.equals(eventId)){
                    // 4. 조건) 성공 시, Simulation 진행 == 다음 이벤트 생성 및 송신.
                    System.out.println("Test Event & Before Action Timer ring.");
                    return null;
                }

                mockingService.sendMockingMessage(eventObj);

                // 5. Event Lost 업데이트.
                testManager.updateLastEvent(nextEventNm);

                // 6. Action Timer Update.
                testManager.startActionTimer();
            }
        }

        return "Received message : " + eventObj.toString();
    }

    @GetMapping("/timer/start")
    public void startTimer() {
        testManager.startActionTimer();
    }

    @GetMapping("/timer/stop")
    public void stopTimer() {
        testManager.stopActionTimer();
    }

    @GetMapping("/timer/reset")
    public void resetTimer() {
        testManager.resetActionTimer();
    }
}
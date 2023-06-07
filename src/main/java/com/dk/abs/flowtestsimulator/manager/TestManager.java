package com.dk.abs.flowtestsimulator.manager;

import com.dk.abs.flowtestsimulator.service.ManageService;
import com.dk.abs.flowtestsimulator.service.MockingService;
import com.dk.abs.flowtestsimulator.service.RequestService;
import com.dk.abs.flowtestsimulator.service.VerificationService;
import com.dk.abs.flowtestsimulator.util.EventParsingUtil;
import com.dk.abs.flowtestsimulator.util.MemoryStorage;
import com.dk.abs.flowtestsimulator.vo.ConfigurationVO;
import com.dk.abs.flowtestsimulator.vo.ivo.ManageConfIVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestManager {

    @Autowired
    ManageService manageService;

    @Autowired
    RequestService requestService;

    @Autowired
    MockingService mockingService;

    @Autowired
    VerificationService verificationService;

    @Autowired
    MemoryStorage storage;

    // TODO IVO 에서 JOSNObject으로 변환 하는게 어려움.
    public void initializeTest(ManageConfIVO confJson){
        // Generate Configuration VO.
        ConfigurationVO obj = manageService.generateConfigurationVO(new JSONObject(confJson), this);
        // Save Configuration in VO.
        storage.insert(obj.getTestRound(), obj);
        storage.getSize();
        // Send Initialize Message.
        requestService.sendRequestMessage("Target Info Managed by hostName");
        // Start Action Timer.
        obj.startActionTimer();
    }

    public void initializeTest(String confJson){
        // Generate Configuration VO.
        ConfigurationVO obj = manageService.generateConfigurationVO(new JSONObject(confJson), this);
        // Save Configuration in VO.
        storage.insert(obj.getTestRound(), obj);
        storage.getSize();
        // Send Initialize Message.
        requestService.sendRequestMessage("Target Info Managed by hostName");
        // Start Action Timer.
        obj.startActionTimer();
    }

    public void handleRequestEvent(String payload){
        JSONObject payloadObj = new JSONObject(payload);
        String messageId = EventParsingUtil.getMessageId(payloadObj);
        ConfigurationVO vo;

        // Verify request. (isTest, isCorrectlyOperate)
        if(!verificationService.isTestMessage(messageId)){
            System.out.println("It's not test message.");
            return;
        }else{
            vo = storage.search(messageId);
            if(vo.isTestStartFlag()){
                if(!vo.isActionTimeOutFlag()){
                    vo.stopActionTimer();
                }else{
                    System.out.println("Action Time Out.");
                    return;
                }
            }else{
                System.out.println("Test Event Start. Something is wrong.");
                return;
            }
        }


        // Verify operation.
        if(!verificationService.isOperationCorrect()){
            System.out.println("Operation Verify Fail.");
            return;
        }

        // Check Joined System.
        // Mocking System. (generate request message)
        // Send Request Message.
        if(verificationService.isMockingSystem("","")) {
            System.out.println("It's Mocking System");
            mockingService.sendMockingMessage();
        }else{
            System.out.println("Target System is join.");
            mockingService.bypassRequestMessage();
        }

        // Resume Action Timer.
        vo.startActionTimer();

    }


    /**
     * Features related w/ Common
     * 1. check the test status.
     * 2. Mocking action when Action Timer is ringing.
     * 3. Check whether exist in data. (system list)
     */

    /**
     * Features related w/ Timer.
     * 1. Start Timer.
     * 2. Stop Timer.
     * 3. Re-Set Timer.
     * 4, Call Time-Out method.
     */


    /**
     * Features related w/ Event Managing
     * 1. Memorize the event history
     * 2. Check participated system in test.
     * 3. Provide next event inform.
     */


    /**
     * Event Managing
     * Get the latest event from event history.
     * @return latest event name.
     */
//    public String getLastEvent(){
//        return this.eventList.get(0);
//    }
//
//    /**
//     * Event Managing
//     * Update the latest event in event history
//     * @param eventNm
//     * @return total size of event history.
//     */
//    public int updateLastEvent(String eventNm){
//        eventList.add(eventNm);
//        return eventList.size();
//    }
//
//    /**
//     * Event Managing
//     * Provide next event name in pre-defined scenario.
//     * @return
//     */
//    public String getNextEventNm(){
//        return "";
//    }
//
//
//    /**
//     * Common
//     * Check whether is need to mocking system.
//     * @param system
//     * @return
//     */
//    public boolean isSystemExist(String system){
//        return systemList.contains(system);
//    }
//
//
//    public boolean isTestStartFlag() {
//        return testStartFlag;
//    }
//
//    public void setTestStartFlag(boolean testStartFlag) {
//        this.testStartFlag = testStartFlag;
//    }
//
//    public boolean isActionTimeOutFlag() {
//        return actionTimeOutFlag;
//    }
//
//    public void setActionTimeOutFlag(boolean actionTimeOutFlag) {
//        this.actionTimeOutFlag = actionTimeOutFlag;
//    }
}

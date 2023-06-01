package com.dk.abs.flowtestsimulator.manager;

import com.dk.abs.flowtestsimulator.util.CustomCode;
import com.dk.abs.flowtestsimulator.util.EventParsingUtil;
import com.dk.abs.flowtestsimulator.vo.ConfigurationVO;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TestManager {


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
    public String getLastEvent(){
        return this.eventList.get(0);
    }

    /**
     * Event Managing
     * Update the latest event in event history
     * @param eventNm
     * @return total size of event history.
     */
    public int updateLastEvent(String eventNm){
        eventList.add(eventNm);
        return eventList.size();
    }

    /**
     * Event Managing
     * Provide next event name in pre-defined scenario.
     * @return
     */
    public String getNextEventNm(){
        return "";
    }


    /**
     * Common
     * Check whether is need to mocking system.
     * @param system
     * @return
     */
    public boolean isSystemExist(String system){
        return systemList.contains(system);
    }


    public boolean isTestStartFlag() {
        return testStartFlag;
    }

    public void setTestStartFlag(boolean testStartFlag) {
        this.testStartFlag = testStartFlag;
    }

    public boolean isActionTimeOutFlag() {
        return actionTimeOutFlag;
    }

    public void setActionTimeOutFlag(boolean actionTimeOutFlag) {
        this.actionTimeOutFlag = actionTimeOutFlag;
    }
}

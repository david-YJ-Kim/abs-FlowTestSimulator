package com.dk.abs.flowtestsimulator.manager;

import com.dk.abs.flowtestsimulator.util.CustomCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class TestManager {

    boolean testFlag = true;
    Timer actionTimer;
    int seq = 1;
    int targetTimeSec = 10000;
    static ArrayList<String> eventList = new ArrayList<>();
    static ArrayList<String> systemList = new ArrayList<>();

    static {
        // event data.
        eventList.add(CustomCode.INIT.name());

        // system sample data.
        systemList.add("BRA");
        systemList.add("RTA");
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

    /***
     * Timer
     * Start Action Timer.
     */
    public void startActionTimer(){
        actionTimer = new Timer();
        actionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Action Time is running out of time.");
                actionTimeout();
            }
        }, targetTimeSec, targetTimeSec);
    }

    /**
     * Timer
     * End Action Timer.
     */
    public void stopActionTimer(){
        System.out.println("Timer Stop");
        actionTimer.cancel();
    }

    /**
     * Timer
     * Re-Set Action Timer.
     */
    public void resetActionTimer(){
        System.out.println("Timer reset.");
        this.stopActionTimer();
        this.startActionTimer();
    }


    /**
     * Common
     * Get Test Status.
     * @return
     */
    public boolean isTestOnGoing(){
        return testFlag;
    }

    /**
     * Common
     * Mocking Post Action.
     */
    public void actionTimeout(){
        this.testFlag = false;
        System.out.println("Action Time Out. Test is Failed. Flag gonna be False");
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
}

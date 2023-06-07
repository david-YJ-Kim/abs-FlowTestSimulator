package com.dk.abs.flowtestsimulator.vo;

import com.dk.abs.flowtestsimulator.manager.TestManager;
import com.dk.abs.flowtestsimulator.vo.ivo.ManageConfIVO;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

enum ConfigurationCode {
    ActionTimeOutSec,
    SelectedScenario,
    SystemJoinFlag,
    TestRound
}

/**
 * 필요한 데이터 = Configuration VO
 * 1. Test 참가 시스템
 * 2. 기준정보 (Action TimeOut, etc)
 * 3. Test Flag 정보 (test 시작 flag, action time-out flag)
 * 4. 테스트 시나리오 정보
 * 5. 공통 시나리오 대응 정보
 * 6. Test 흐름 저장
 * 7. Action Timer
 */


/**
 *
 */

@ToString
@Getter
public class ConfigurationVO {

    String testRound; // test round : 여러 개 테스트를 진행 할 때, 식별 가능한 key.

    boolean testStartFlag;
    boolean actionTimeOutFlag;

    Timer actionTimer;

    int actionTimeOutSec;

    ArrayList<String> eventHistoryList;
    ArrayList<String> participatedSystemList;

    String selectedScenario;
    JSONArray scenarioDetails;

    TestManager manager;

    public ConfigurationVO(JSONObject jsonObject){

        testRound = jsonObject.getString(ConfigurationCode.TestRound.name());
        actionTimeOutSec = jsonObject.getInt(ConfigurationCode.ActionTimeOutSec.name());
        selectedScenario = jsonObject.getString(ConfigurationCode.SelectedScenario.name());
        participatedSystemList = setJoinSystemList(jsonObject.getJSONObject(ConfigurationCode.SystemJoinFlag.name()));
        scenarioDetails = jsonObject.getJSONArray(selectedScenario);
        eventHistoryList = new ArrayList<>();

    }

    /**
     *
     * @param jsonObject : Admin으로 부터 수신 받은 테스트 진행서
     */
    public ConfigurationVO(JSONObject jsonObject, TestManager manager){

        testRound = jsonObject.getString(ConfigurationCode.TestRound.name());
        actionTimeOutSec = jsonObject.getInt(ConfigurationCode.ActionTimeOutSec.name());
        selectedScenario = jsonObject.getString(ConfigurationCode.SelectedScenario.name());
        participatedSystemList = setJoinSystemList(jsonObject.getJSONObject(ConfigurationCode.SystemJoinFlag.name()));
        scenarioDetails = jsonObject.getJSONArray(selectedScenario);
        eventHistoryList = new ArrayList<>();
        manager = manager;

    }


    /**
     *
     * @return
     * @param joinDetailObject
     */
    private ArrayList setJoinSystemList(JSONObject joinDetailObject){

        ArrayList<String> result = new ArrayList<>();

        Iterator<String> keyIterator = joinDetailObject.keys();
        while(keyIterator.hasNext()){
            String key = keyIterator.next();
            if(joinDetailObject.getBoolean(key)){
                result.add(key);
            }
        }
        return result;
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
        }, actionTimeOutSec, actionTimeOutSec);
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

    private void actionTimeout(){
        this.actionTimeOutFlag = false;
        System.out.println("Action Time Out. Test is Failed. Flag gonna be False");
//        manager.
    }
}

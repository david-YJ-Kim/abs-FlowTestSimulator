package com.dk.abs.flowtestsimulator.util;

import org.json.JSONObject;

public class EventParsingUtil {

    public static boolean isTestEvent(JSONObject headObj){
        if(headObj.getString(CustomCode.osrc.name()).startsWith(CustomCode.TST.name())){
            return true;
        }else{
            return false;
        }
    }
}

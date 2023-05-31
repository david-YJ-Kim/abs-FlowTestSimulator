package com.dk.abs.flowtestsimulator.util;

import com.dk.abs.flowtestsimulator.vo.ConfigurationVO;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EventParsingUtil {

    public static boolean isTestEvent(JSONObject headObj){
        if(headObj.getString(CustomCode.osrc.name()).startsWith(CustomCode.TST.name())){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isTestEvent(String eventId){
        if(eventId.startsWith(CustomCode.TST.name())){
            return true;
        }else{
            return false;
        }
    }

    public static ConfigurationVO readConfigurationFile(){
        try{
            InputStream inputStream = new ClassPathResource("scenario_sample.json").getInputStream();

            // Create JSONTokener from FileInputStream
            JSONTokener tokener = new JSONTokener(inputStream);

            // Create JSONObject from JSONTokener
            JSONObject jsonObject = new JSONObject(tokener);

            return new ConfigurationVO(jsonObject);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

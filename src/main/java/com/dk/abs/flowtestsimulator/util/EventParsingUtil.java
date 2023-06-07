package com.dk.abs.flowtestsimulator.util;

import com.dk.abs.flowtestsimulator.vo.ConfigurationVO;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class EventParsingUtil {

    public static String getMessageId(JSONObject jsonObject){
        return "";
    }


    /**
     * Read Configuration File and make VO.
     * @return
     */
    public static ConfigurationVO readConfigurationFile(){
        try{
            // Get the resource file as a byte array
            byte[] fileBytes = StreamUtils.copyToByteArray(new ClassPathResource("scenario_sample.json").getInputStream());

            // Convert the byte array to a String
            String jsonString = new String(fileBytes, StandardCharsets.UTF_8);

            // Create JSONObject from JSONTokener
            JSONObject jsonObject = new JSONObject(jsonString);

            return new ConfigurationVO(jsonObject);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

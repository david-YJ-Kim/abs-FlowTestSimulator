package com.dk.abs.flowtestsimulator.util;

import com.dk.abs.flowtestsimulator.vo.ConfigurationVO;
import org.junit.jupiter.api.Test;

public class EventParsingUtilTest {

    @Test
    void readConfigurationFile(){
        ConfigurationVO vo = EventParsingUtil.readConfigurationFile();
        System.out.println(vo.toString());
    }
}

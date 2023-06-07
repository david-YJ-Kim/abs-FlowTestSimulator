package com.dk.abs.flowtestsimulator.util;

import com.dk.abs.flowtestsimulator.vo.ConfigurationVO;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 테스트 라운드 별, 필요한 데이터 적재 및 보관
 * 필요한 데이터 = Configuration VO
 * 1. Test 참가 시스템
 * 2. 기준정보 (Action TimeOut, etc)
 * 3. Test Flag 정보 (test 시작 flag, action time-out flag)
 * 4. 테스트 시나리오 정보
 * 5. 공통 시나리오 대응 정보
 */
@Component
public class MemoryStorage {

    private ConcurrentHashMap<String, ConfigurationVO> memoryMap = new ConcurrentHashMap<>();

    // Method to insert data
    public void insert(String key, ConfigurationVO value) {
        memoryMap.put(key, value);
    }

    // Method to update data
    public void update(String key, ConfigurationVO newValue) {
        if (memoryMap.containsKey(key)) {
            memoryMap.put(key, newValue);
        }
    }

    // Method to delete data
    public void delete(String key) {
        memoryMap.remove(key);
    }

    // Method to search data
    public ConfigurationVO search(String key) {
        ConfigurationVO configurationVO = memoryMap.get(key);
        if (configurationVO == null) {
            throw new IllegalArgumentException("Key does not exist");
        }
        return configurationVO;
    }


    public int getSize(){
        return memoryMap.size();
    }
}

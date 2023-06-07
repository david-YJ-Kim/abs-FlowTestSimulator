package com.dk.abs.flowtestsimulator.util;

import org.springframework.stereotype.Component;

public enum EventPayloadTemplate {
    WFS_PROD_STARTED("{" +
            "    \"head\": {" +
            "        \"cid\": \"WFS_LOAD_REQ\"," +
            "        \"tid\": \"202303030000000\"," +
            "        \"osrc\": \"\"," +
            "        \"otgt\": \"\"," +
            "        \"src\": \"EAP\"," +
            "        \"srcEqp\": \"\"," +
            "         \"tgt\": \"WFS\"," +
            "         \"tgtEqp\": \"\"" +
            "    }," +
            "    \"body\": {" +
            "        \"siteId\": \"SVM\"," +
            "        \"eqpId\": \"AP-SP-01\"," +
            "\"portId\": \"AP-SP-01-00_BP01\"," +
            "\"portState\": \"Enable\"," +
            "\"portType\": \"BP\"," +
            "\"userId\": \"EAP\"" +
            "    }" +
            "}");

    private String template;
    EventPayloadTemplate(String template){
        this.template = template;
    }

    public String getTemplate(){
        return this.template;
    }

}


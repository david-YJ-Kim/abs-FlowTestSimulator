package com.dk.abs.flowtestsimulator.vo.ivo.receive;

import com.dk.abs.flowtestsimulator.vo.ivo.EventHead;
import lombok.Data;

@Data
public class RtdDspWorkReqIVO {

    EventHead head;

    DspWorkReq body;

    @Data
    public static class DspWorkReq {
        String siteId;
        String dspType;
        String lotId;
        String eqpId;
        String portId;
        String carrId;
        String pordDefId;
        String procDefId;
        String evntCm;
        String evntUserId;

    }
}

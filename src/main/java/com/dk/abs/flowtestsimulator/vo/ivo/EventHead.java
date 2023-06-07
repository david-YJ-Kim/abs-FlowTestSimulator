package com.dk.abs.flowtestsimulator.vo.ivo;

import lombok.Data;

import java.util.List;

@Data
public class EventHead {

    String cid;
    String tid;
    String osrc;
    String otgt;
    String src;
    String srcEqp;
    String tgt;
    List<String> tgtEqp;
}

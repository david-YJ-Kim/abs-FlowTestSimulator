package com.dk.abs.flowtestsimulator.vo.ivo;

import com.dk.abs.flowtestsimulator.vo.ivo.conf.ScenarioIVO;
import com.dk.abs.flowtestsimulator.vo.ivo.conf.SystemFlagIVO;
import lombok.Data;

import java.util.List;

@Data
public class ManageConfIVO {

    String TestRound;
    String SelectedScenario;
    int ActionTimeOutSec;
    SystemFlagIVO SystemJoinFlag;
    List<ScenarioIVO> BP;

}

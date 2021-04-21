package com.nju.iot.service.rule_engine.re_humiduty;

import com.nju.iot.entity.Rule;

import java.util.ArrayList;

/**
 * @author: d_xin
 * @date: 2021/4/20
 * @description:
 */
public interface RE_HumidityService {
    boolean checkHumidityThreshold(Long userId, Double humi_data);

    boolean sendHumidityWarningSignal(Long userId, int signal);

    // ArrayList<Rule> showHumidityRules();
}

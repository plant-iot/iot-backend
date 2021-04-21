package com.nju.iot.service.rule_engine.re_temperature;

import com.nju.iot.entity.Rule;

import java.util.ArrayList;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
public interface RE_TemperatureService {
    boolean checkTempThreshold(Long userId, Double temp_data);

    boolean sendTempWarningSignal(Long userId, int signal);

    // ArrayList<Rule> showTempRules();
}

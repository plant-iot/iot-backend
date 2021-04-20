package com.nju.iot.service.rule_engine;

import com.nju.iot.entity.Rule;
import com.nju.iot.entity.RuleType;

import java.util.ArrayList;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
public interface RuleService {
    boolean addRule(RuleType type, Double data, boolean is_enabled);

    boolean deleteRule(String rule_id);

    boolean enableRule(String rule_id);

    boolean disableRule(String rule_id);

    ArrayList<Rule> showTempRules();

    ArrayList<Rule> showHumidityRules();
}

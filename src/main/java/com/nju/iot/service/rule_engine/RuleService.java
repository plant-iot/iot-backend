package com.nju.iot.service.rule_engine;

import com.nju.iot.entity.Rule;
import com.nju.iot.entity.RuleAction;
import com.nju.iot.entity.RuleType;
import com.nju.iot.payloads.RuleInfo;

import java.util.ArrayList;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
public interface RuleService {
    Long addRule(RuleType type, Long user_id, Double data, RuleAction rule_state);

    void deleteRule(Long id);

    boolean enableRule(Long id);

    void disableRule(Long id);

    ArrayList<Rule> showAllRulesByUserId(Long user_id);

    ArrayList<RuleInfo> showAllRulesInfoByUserId(Long user_id);

    ArrayList<Rule> showTempRules(Long user_id);

    ArrayList<Rule> showHumidityRules(Long user_id);
}

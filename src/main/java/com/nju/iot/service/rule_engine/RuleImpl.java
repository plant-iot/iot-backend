package com.nju.iot.service.rule_engine;

import com.nju.iot.entity.Rule;
import com.nju.iot.entity.RuleType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RuleImpl implements RuleService {
    @Override
    public boolean addRule(RuleType type, Double data, boolean is_enabled) {
        return false;
    }

    @Override
    public boolean deleteRule(String rule_id) {
        return false;
    }

    @Override
    public boolean enableRule(String rule_id) {
        return false;
    }

    @Override
    public boolean disableRule(String rule_id) {
        return false;
    }

    @Override
    public ArrayList<Rule> showTempRules() {
        return null;
    }

    @Override
    public ArrayList<Rule> showHumidityRules() {
        return null;
    }
}

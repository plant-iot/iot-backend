package com.nju.iot.service.rule_engine.re_temperature;

import com.nju.iot.entity.Rule;
import com.nju.iot.entity.RuleAction;
import com.nju.iot.service.rule_engine.RuleImpl;
import com.nju.iot.service.rule_engine.RuleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
@Service
public class RE_TemperatureImpl implements RE_TemperatureService {
    @Override
    public boolean checkTempThreshold(Long userId, Double temp_data){
        Rule current_enabled_temp_rule = null;

        RuleService ruleService = new RuleImpl();
        ArrayList<Rule> temp_rules = ruleService.showTempRules(userId);
        for(int i = 0;i < temp_rules.size();i++){
            if(temp_rules.get(i).getState() == RuleAction.ENABLED){
                current_enabled_temp_rule = temp_rules.get(i);
                break;
            }
        }

        boolean is_overThre = false;
        // 这里暂时只考虑 当前温度数据超过规则设置的阈值0.1℃
        if(current_enabled_temp_rule != null &&
                temp_data - current_enabled_temp_rule.getThreshold_data() >= 0.1){
            is_overThre = true;
        }
        return is_overThre;
    }

    @Override
    public boolean sendTempWarningSignal(Long userId, int signal){
        return false;
    }

}

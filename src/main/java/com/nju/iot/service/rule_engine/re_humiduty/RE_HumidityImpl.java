package com.nju.iot.service.rule_engine.re_humiduty;

import com.nju.iot.entity.Rule;
import com.nju.iot.service.rule_engine.RuleImpl;
import com.nju.iot.service.rule_engine.RuleService;
import com.nju.iot.service.rule_engine.re_temperature.RE_TemperatureService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author: d_xin
 * @date: 2021/4/20
 * @description:
 */
@Service
public class RE_HumidityImpl implements RE_HumidityService {
    @Override
    public boolean checkHumidityThreshold(Double humi_data) {
        Rule current_enabled_humi_rule = null;

        RuleService ruleService = new RuleImpl();
        ArrayList<Rule> humi_rules = ruleService.showHumidityRules();
        for(int i = 0;i < humi_rules.size();i++){
            if(humi_rules.get(i).getIs_enabled()){
                current_enabled_humi_rule = humi_rules.get(i);
                break;
            }
        }

        boolean is_overThre = false;
        // 这里暂时只考虑 当前湿度数据低过规则设置的阈值0.1（？）
        if(current_enabled_humi_rule != null &&
                humi_data - current_enabled_humi_rule.getThreshold_data() <= 0.1){
            is_overThre = true;
        }
        return is_overThre;
    }

    @Override
    public boolean sendHumidityWarningSignal(int signal) {
        return false;
    }
}

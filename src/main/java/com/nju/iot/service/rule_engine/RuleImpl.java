package com.nju.iot.service.rule_engine;

import com.nju.iot.dao.DataRepository;
import com.nju.iot.dao.RuleRepository;
import com.nju.iot.dao.UserRepository;
import com.nju.iot.entity.*;
import com.nju.iot.payloads.RuleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
@Service
public class RuleImpl implements RuleService {
    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataRepository dataRepository;

    @Override
    public Long addRule(RuleType type, Long user_id, Double data, RuleAction rule_state) {
        User user = userRepository.findById(user_id).get();
        Rule rule = new Rule(type, user, data, rule_state);
        ArrayList<Rule> rules = ruleRepository.findDistinctByUserId(user_id);
        for (Rule r : rules){
            if(r.getType() == type && r.getState() == RuleAction.ENABLED){
                disableRule(r.getId());
                break;
            }
        }
        ruleRepository.save(rule);
        return rule.getId();
    }

    @Override
    public void deleteRule(Long id) {
        if(ruleRepository.existsById(id)) {
            Rule rule = ruleRepository.findById(id).get();
            rule.setState(RuleAction.DELETED);
            ruleRepository.save(rule);
        }
    }

    @Override
    public boolean modifyRule(Long id) {
        if(ruleRepository.existsById(id)){
            Rule rule = ruleRepository.findById(id).get();
            if(rule.getState() == RuleAction.ENABLED){
                disableRule(id);
                return true;
            }
            else if(rule.getState() == RuleAction.DISABLED){
                enableRule(id);
                return true;
            }
            // else if (rule.getState() == RuleAction.DELETED)
        }
        return false;
    }

    @Override
    public boolean enableRule(Long id) {
        System.out.println("call backend-enableRule, rule_id=" + id);
        if(ruleRepository.existsById(id)) {
            Rule rule = ruleRepository.findById(id).get();

            Rule pre_enabled_rule = getEnabledXXRule(rule.getUser().getId(), rule.getType().getS());
            if (pre_enabled_rule != null && rule.getId() == pre_enabled_rule.getId()){
                return true;
            }
            if(rule.getState() == RuleAction.DISABLED) {
                rule.setState(RuleAction.ENABLED);
                ruleRepository.save(rule);
                if (pre_enabled_rule != null){
                    disableRule(pre_enabled_rule.getId());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void disableRule(Long id) {
        System.out.println("call backend-disableRule, rule_id=" + id);
        if(ruleRepository.existsById(id)) {
            Rule rule = ruleRepository.findById(id).get();
            rule.setState(RuleAction.DISABLED);
            ruleRepository.save(rule);
        }
    }

    @Override
    public ArrayList<Rule> showAllRulesByUserId(Long user_id) {
        ArrayList<Rule> user_rules = ruleRepository.findDistinctByUserId(user_id);
        return user_rules;
    }

    @Override
    public ArrayList<RuleInfo> showAllRulesInfoByUserId(Long user_id) {
        ArrayList<Rule> temp_rules = showAllRulesByUserId(user_id);
        ArrayList<RuleInfo> temp_info = new ArrayList<>();
        for (Rule temp_r : temp_rules){
            temp_info.add(new RuleInfo(temp_r));
        }
        return temp_info;
    }

    @Override
    public ArrayList<Rule> showTempRules(Long user_id) {
        ArrayList<Rule> temp_rules = new ArrayList<>();

        ArrayList<Rule> all_rules = showAllRulesByUserId(user_id);
        for (int i = 0;i < all_rules.size();i++){
            Rule i_rule = all_rules.get(i);
            if (i_rule.getType() == RuleType.TEMPERATURE_RULE){
                temp_rules.add(i_rule);
            }
        }
        return temp_rules;
    }

    @Override
    public ArrayList<Rule> showHumidityRules(Long user_id) {
        ArrayList<Rule> humi_rules = new ArrayList<>();

        ArrayList<Rule> all_rules = showAllRulesByUserId(user_id);
        for (int i = 0;i < all_rules.size();i++){
            Rule i_rule = all_rules.get(i);
            if (i_rule.getType() == RuleType.HUMIDITY_RULE){
                humi_rules.add(i_rule);
            }
        }
        return humi_rules;
    }

    @Override
    public ArrayList<Data> getLatestDataList() {
        List<Data> data_list = dataRepository.findAll();
        ArrayList<Data> latest_list = new ArrayList<>();
        boolean temp_flag = false;
        boolean humi_flag = false;
        boolean co2_flag = false;
        boolean light_flag = false;
        for (int i = data_list.size() - 1;i >= 0;i--){
            Data tempd = data_list.get(i);
            if (!temp_flag && tempd.getDataType() == DataType.TEMPERATURE){
                latest_list.add(tempd);
                temp_flag = true;
            }
            if (!humi_flag && tempd.getDataType() == DataType.HUMIDITY){
                latest_list.add(tempd);
                humi_flag = true;
            }
            if (!co2_flag && tempd.getDataType() == DataType.CO2){
                latest_list.add(tempd);
                co2_flag = true;
            }
            if (!light_flag && tempd.getDataType() == DataType.LIGHT_INTENSITY){
                latest_list.add(tempd);
                light_flag = true;
            }
            if (temp_flag && humi_flag && co2_flag && light_flag){
                break;
            }
        }
        return latest_list;
    }

    @Override
    public Rule getEnabledXXRule(Long user_id, String rule_type) {
        Rule ret_rule = null;
        ArrayList<Rule> all_rules = showAllRulesByUserId(user_id);
        for (Rule rule : all_rules){
            if (rule.getState() == RuleAction.ENABLED && rule_type == rule.getType().getS()){
                ret_rule = rule;
                break;
            }
        }
        return ret_rule;
    }
}

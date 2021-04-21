package com.nju.iot.service.rule_engine;

import com.nju.iot.dao.RuleRepository;
import com.nju.iot.dao.UserRepository;
import com.nju.iot.entity.*;
import com.nju.iot.payloads.RuleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

//        System.out.println("");
//        Long rule_id = Long.parseLong("0");
//        return rule_id;
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
    public boolean enableRule(Long id) {
        if(ruleRepository.existsById(id)) {
            Rule rule = ruleRepository.findById(id).get();
            if(rule.getState() == RuleAction.DISABLED) {
                rule.setState(RuleAction.ENABLED);
                ruleRepository.save(rule);
                return true;
            }
        }
        return false;
    }

    @Override
    public void disableRule(Long id) {
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
}

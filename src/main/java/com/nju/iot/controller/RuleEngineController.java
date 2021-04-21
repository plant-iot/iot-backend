package com.nju.iot.controller;

import com.nju.iot.entity.*;
import com.nju.iot.payloads.RuleInfo;
import com.nju.iot.service.rule_engine.RuleService;
import com.nju.iot.service.rule_engine.re_temperature.RE_TemperatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
@RestController
@RequestMapping("/rule_engine")
@Api(tags = "规则引擎")
public class RuleEngineController {
    @Autowired
    private RuleService ruleService;

    @Autowired
    private RE_TemperatureService re_TemperatureService;

    @PostMapping("/add_rule")
    @ApiOperation("添加规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "type", value = "规则类型"),
            @ApiImplicitParam(name = "data", value = "规则阈值数据")
    })
    public Long addRule(Long userId, String type, String data) {
//        System.out.println("call controller-addRule");
//        RuleType rt = RuleType.TEMPERATURE_RULE;  // 默认为温度规则
//        if(type == "HUMIDITY_RULE"){
//            rt = RuleType.HUMIDITY_RULE;
//        }
//        else if(type == "CO2_RULE"){
//            rt = RuleType.CO2_RULE;
//        }
//        else if(type == "LIGHT_INTENSITY_RULE"){
//            rt = RuleType.LIGHT_INTENSITY_RULE;
//        }
//
//        Long rule_id = ruleService.addRule(rt, userId, Double.parseDouble(data), RuleAction.ENABLED);
//        System.out.println("call backend: addRule, rule_id=" + rule_id);
        Long rule_id = Long.parseLong("0");
        return rule_id;
    }

    @GetMapping("/delete_rule")
    @ApiOperation("删除规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则ID")
    })
    public void deleteRule(Long id) {
        ruleService.deleteRule(id);
    }

    @GetMapping("/enable_rule")
    @ApiOperation("启用规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则ID")
    })
    public boolean enableRule(Long id) {
        boolean is_enable_success = ruleService.enableRule(id);
        return is_enable_success;
    }

    @GetMapping("/disable_rule")
    @ApiOperation("禁用规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则ID")
    })
    public void disableRule(Long id) {
        ruleService.disableRule(id);
    }

    @GetMapping("/show_all_rules_info")
    @ApiOperation("显示所有规则的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    public ArrayList<RuleInfo> showAllRulesInfo(Long userId) {
        ArrayList<RuleInfo> rules = new ArrayList<>();
        User user = new User();
        user.setId(Long.parseLong("1"));
        user.setPassword("123456");
        Rule r = new Rule(Long.parseLong("1"), RuleType.TEMPERATURE_RULE, user, 30.0, RuleAction.ENABLED);
        rules.add(new RuleInfo(r));
        System.out.println("call backend: showAllRulesInfo, desc=" + rules.get(0).getDescription());
        return rules;
//        System.out.println("call controller-showAllRulesInfo");
//        ArrayList<RuleInfo> rules = ruleService.showAllRulesInfoByUserId(userId);
//        return rules;
    }

    @GetMapping("/check_temp_and_warn")
    @ApiOperation("检测温度阈值和告警")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "温度数据")
    })
    public void checkTempThresholdAndWarn(Long userId, Data data) {
        boolean is_overThre = re_TemperatureService.checkTempThreshold(userId, data.getValue());
        if(is_overThre){
            int signal = 1;
            re_TemperatureService.sendTempWarningSignal(userId, signal);
        }
    }
}

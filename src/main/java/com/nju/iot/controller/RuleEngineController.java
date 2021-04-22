package com.nju.iot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nju.iot.dao.DataRepository;
import com.nju.iot.entity.*;
import com.nju.iot.payloads.RuleInfo;
import com.nju.iot.payloads.WarningInfo;
import com.nju.iot.service.rule_engine.RuleService;
import com.nju.iot.service.rule_engine.re_temperature.RE_TemperatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static java.lang.Long.parseLong;

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
    public Object addRule(@RequestBody Object rule_data){
    //public Long addRule(Long userId, String type, String data) {
        System.out.println("call controller-addRule, json_data=" + rule_data.toString());

        JSONObject jo = (JSONObject) JSONObject.toJSON(rule_data);
//        System.out.println("jo_uid=" + jo.getString("userId"));
//        System.out.println("jo_type=" + jo.getString("type"));
//        System.out.println("jp_data_num=" + jo.get("data_num"));

        String userId = jo.getString("userId");
        String type = jo.getString("type");
        String data = jo.getString("data_num");
        RuleType rt = RuleType.getRuleType(type);

//        System.out.println("user_id=" + userId);
//        System.out.println("type=" + type);
//        System.out.println("data=" + data);

        Long rule_id = ruleService.addRule(rt, Long.parseLong(userId), Double.parseDouble(data), RuleAction.ENABLED);
        System.out.println("call backend: addRule, rule_id=" + rule_id);
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

    @GetMapping("/modify_rule")
    @ApiOperation("启用或禁用规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则ID")
    })
    public boolean modifyRule(Long id) {
//        System.out.println("call controller-modifyRule, json_data=" + id_data.toString());
//        JSONObject jo = (JSONObject) JSONObject.toJSON(id_data);
//        String id = jo.getString("id");
        System.out.println("call backend-modifyRule, rule_id=" + id);
        boolean is_enable_success = ruleService.modifyRule(id);
        return is_enable_success;
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
//        ArrayList<RuleInfo> rules = new ArrayList<>();
//        User user = new User();
//        user.setId(Long.parseLong("1"));
//        user.setPassword("123456");
//        Rule r = new Rule(Long.parseLong("1"), RuleType.TEMPERATURE_RULE, user, 30.0, RuleAction.ENABLED);
//        rules.add(new RuleInfo(r));
//        System.out.println("call backend: showAllRulesInfo, desc=" + rules.get(0).getDescription());
//        return rules;
//        System.out.println("call controller-showAllRulesInfo");
        ArrayList<RuleInfo> rules = ruleService.showAllRulesInfoByUserId(userId);
        return rules;
    }

    @GetMapping("/check_and_warn")
    @ApiOperation("检测温度/湿度/CO2/光照强度的阈值和告警")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id_data", value = "用户id")
    })
    public WarningInfo checkThresholdAndWarn(Long user_id) {
        WarningInfo warningInfo = new WarningInfo();  // 默认均为”暂无告警“

        // TODO
        warningInfo.setTemp_warn("告警：当前温度为30℃，已超过25℃！");
        warningInfo.setHumi_warn("告警：当前湿度：50%，已低于70%！");
        warningInfo.setCo2_warn("告警：当前CO2含量为20%，已低于50%！");
        warningInfo.setLight_warn("告警：当前光照强度为10%，已低于60%！");

//        ArrayList<Data> data_list = ruleService.getLatestDataList();
//        // 如果没有数据
//        if (data_list == null || data_list.size() == 0){
//            return warningInfo;
//        }
//        // 如果有数据，查规则，比较阈值
//        Rule tempr = ruleService.getEnabledXXRule(user_id, "TEMPERATURE_RULE");
//        Rule humir = ruleService.getEnabledXXRule(user_id, "HUMIDITY_RULE");
//        Rule co2r = ruleService.getEnabledXXRule(user_id, "CO2_RULE");
//        Rule lightr = ruleService.getEnabledXXRule(user_id, "LIGHT_INTENSITY_RULE");
//        for (Data tempd : data_list){
//            if (tempr != null && tempd.getDataType() == DataType.TEMPERATURE &&
//                    tempd.getValue() - tempr.getThreshold_data() >= 0.1){  // 设置一个误差范围
//                warningInfo.setTemp_warn("告警：当前温度为" + tempd.getValue() + "℃" + "，已超过" + tempr.getThreshold_data() + "℃！");
//            }
//            else if (humir != null && tempd.getDataType() == DataType.HUMIDITY &&
//                    tempd.getValue() - humir.getThreshold_data() <= -0.1){
//                warningInfo.setHumi_warn("告警：当前湿度为" + tempd.getValue() + "%" + "，已低于" + humir.getThreshold_data() + "%！");
//            }
//            else if (co2r != null && tempd.getDataType() == DataType.CO2 &&
//                    tempd.getValue() - co2r.getThreshold_data() <= -0.1){
//                warningInfo.setCo2_warn("告警：当前CO2含量为" + tempd.getValue() + "%" + "，已低于" + co2r.getThreshold_data() + "%！");
//            }
//            else if (lightr != null && tempd.getDataType() == DataType.LIGHT_INTENSITY &&
//                    tempd.getValue() - lightr.getThreshold_data() <= -0.1){
//                warningInfo.setLight_warn("告警：当前光照强度为" + tempd.getValue() + "%" + "，已低于" + lightr.getThreshold_data() + "%！");
//            }
//        }
        return warningInfo;
    }

    @PostMapping("/check_temp_and_warn")
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

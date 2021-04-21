package com.nju.iot.payloads;

import com.nju.iot.entity.Device;
import com.nju.iot.entity.Rule;
import com.nju.iot.entity.RuleAction;
import com.nju.iot.entity.RuleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.format.DateTimeFormatter;

/**
 * @author: d_xin
 * @date: 2021/4/21
 * @description:
 */
@ApiModel
public class RuleInfo {
    @ApiModelProperty(value = "规则id")
    private long id;
    @ApiModelProperty(value = "规则名称")
    private String name;
    @ApiModelProperty(value = "规则状态")
    private String state;
    @ApiModelProperty(value = "规则类型")
    private String type;
    @ApiModelProperty(value = "阈值")
    private double threshold;
    @ApiModelProperty(value = "规则描述")
    private String description;
    @ApiModelProperty(value = "用户id")
    private long userId;

    public RuleInfo() {
    }

    public RuleInfo(Rule rule) {
        this.id = rule.getId();
        this.name = "" + this.id;
        RuleAction ruleAction = rule.getState();
        if(ruleAction == RuleAction.ENABLED) {
            this.state = "已启用";
        }else if(ruleAction == RuleAction.DISABLED) {
            this.state = "已禁用";
        }else if(ruleAction == RuleAction.DELETED) {
            this.state = "已删除";
        }

        this.threshold = rule.getThreshold_data();

        RuleType ruleType = rule.getType();
        if(ruleType == RuleType.TEMPERATURE_RULE) {
            this.type = "温度规则";
            this.description = "当温度超过" + this.threshold + "℃时告警";
        }else if(ruleType == RuleType.HUMIDITY_RULE) {
            this.type = "湿度规则";
            this.description = "当湿度超过" + this.threshold + "时告警";
        }else if(ruleType == RuleType.CO2_RULE) {
            this.type = "CO2规则";
            this.description = "当CO2超过" + this.threshold + "时告警";
        }else if(ruleType == RuleType.LIGHT_INTENSITY_RULE) {
            this.type = "光照强度规则";
            this.description = "当光照强度超过" + this.threshold + "时告警";
        }else if(ruleType == RuleType.K_CONTENT_RULE) {
            this.type = "K元素规则";
            this.description = "当K含量超过" + this.threshold + "时告警";
        }else if(ruleType == RuleType.N_CONTENT_RULE) {
            this.type = "N元素规则";
            this.description = "当N含量超过" + this.threshold + "时告警";
        }else if(ruleType == RuleType.P_CONTENT_RULE) {
            this.type = "P元素规则";
            this.description = "当P含量超过" + this.threshold + "时告警";
        }else if(ruleType == RuleType.MG_CONTENT_RULE) {
            this.type = "Mg元素规则";
            this.description = "当Mg含量超过" + this.threshold + "时告警";
        }

        this.userId = rule.getUser().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

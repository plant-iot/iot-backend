package com.nju.iot.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
@Entity
public class Rule {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private RuleType type;

    private Double threshold_data = 0.0;

    private boolean is_enabled = false;

    // private int priority = 0;  // 暂时规定为同一时间只能启用一条相关规则

    public Rule(){

    }

    public Rule(String id, RuleType type, Double data, boolean is_enabled){
        this.id = id;
        this.type = type;
        this.threshold_data = data;
        this.is_enabled = is_enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RuleType getType() {
        return type;
    }

    public void setType(RuleType type) {
        this.type = type;
    }

    public Double getThreshold_data() {
        return threshold_data;
    }

    public void setThreshold_data(Double threshold_data) {
        this.threshold_data = threshold_data;
    }

    public boolean getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(boolean is_enabled) {
        this.is_enabled = is_enabled;
    }

}

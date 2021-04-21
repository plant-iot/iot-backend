package com.nju.iot.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
@Entity
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RuleType type;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Double threshold_data = 0.0;

    @Enumerated(EnumType.STRING)
    private RuleAction state = RuleAction.DISABLED;

    // private boolean is_enabled = false;

    // private int priority = 0;  // 暂时规定为同一时间只能启用一条相关规则

    public Rule(){

    }

    public Rule(RuleType type, User user, Double data, RuleAction rule_state){
        this.type = type;
        this.user = user;
        this.threshold_data = data;
        this.state = rule_state;
    }

    public Rule(Long id, RuleType type, User user, Double data, RuleAction rule_state){
        this.id = id;
        this.type = type;
        this.user = user;
        this.threshold_data = data;
        this.state = rule_state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RuleType getType() {
        return type;
    }

    public void setType(RuleType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getThreshold_data() {
        return threshold_data;
    }

    public void setThreshold_data(Double threshold_data) {
        this.threshold_data = threshold_data;
    }

    public RuleAction getState() {
        return state;
    }

    public void setState(RuleAction state) {
        this.state = state;
    }

}

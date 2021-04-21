package com.nju.iot.entity;

/**
 * @author: d_xin
 * @date: 2021/4/20
 * @description: 规则的状态
 */
public enum RuleAction {
    ENABLED("已启用"),
    DISABLED("已禁用"),
    DELETED("已删除");

    private final String s;

    RuleAction(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}

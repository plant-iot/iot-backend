package com.nju.iot.entity;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
public enum RuleType {
    TEMPERATURE_RULE("TEMPERATURE_RULE"),
    HUMIDITY_RULE("HUMIDITY_RULE"),
    CO2_RULE("CO2_RULE"),
    LIGHT_INTENSITY_RULE("LIGHT_INTENSITY_RULE"),
    N_CONTENT_RULE("N_CONTENT_RULE"),
    P_CONTENT_RULE("P_CONTENT_RULE"),
    K_CONTENT_RULE("K_CONTENT_RULE"),
    MG_CONTENT_RULE("MG_CONTENT_RULE");

    private final String s;

    RuleType(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public static RuleType getRuleType(String s) {
        for(RuleType ruleType : RuleType.values()) {
            if(ruleType.getS().equals(s)) {
                return ruleType;
            }
        }
        return null;
    }
}

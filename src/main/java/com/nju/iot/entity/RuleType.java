package com.nju.iot.entity;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
public enum RuleType {
    TEMPERATURE_RULE("temperature rule"),
    HUMIDITY_RULE("humidity rule"),
    CO2_RULE("CO2 rule"),
    LIGHT_INTENSITY_RULE("light intensity rule"),
    N_CONTENT_RULE("N content rule"),
    P_CONTENT_RULE("P content rule"),
    K_CONTENT_RULE("K content rule"),
    MG_CONTENT_RULE("Mg content rule");

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

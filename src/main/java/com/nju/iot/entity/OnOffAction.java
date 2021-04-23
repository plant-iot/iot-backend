package com.nju.iot.entity;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
public enum OnOffAction {
    ON("上线"),OFF("下线");

    private final String s;

    OnOffAction(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}

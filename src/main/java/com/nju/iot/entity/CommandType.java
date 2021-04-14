package com.nju.iot.entity;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/13
 * @description:
 */
public enum CommandType {
    ;

    private final String s;

    CommandType(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public static CommandType getCommandType(String s) {
        for(CommandType type : CommandType.values()) {
            if(type.getS().equals(s)) {
                return type;
            }
        }
        return null;
    }
}

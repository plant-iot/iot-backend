package com.nju.iot.entity;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/11
 * @description:
 */
public enum DataType {
    TEMPERATURE("temperature");

    private final String s;

    DataType(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public static DataType getDataType(String s) {
        for(DataType dataType : DataType.values()) {
            if(dataType.getS().equals(s)) {
                return dataType;
            }
        }
        return null;
    }
}

package com.nju.iot.entity;

/**
 * @author: xiang
 * @date: 2021/4/13
 * @description: 设备类型
 */
public enum DeviceType {
    SENSOR("传感器", false, true),
    EXECUTOR("执行器", true, false);

    private final String s;
    private final boolean canSendCommand;
    private final boolean canGetData;

    DeviceType(String s, boolean canSendCommand, boolean canGetData) {
        this.s = s;
        this.canSendCommand = canSendCommand;
        this.canGetData = canGetData;
    }

    public boolean isCanSendCommand() {
        return canSendCommand;
    }

    public boolean isCanGetData() {
        return canGetData;
    }

    public String getS() {
        return s;
    }

    public static DeviceType getType(String s) {
        for(DeviceType type : DeviceType.values()) {
            if(type.getS().equals(s)) {
                return type;
            }
        }
        return null;
    }
}

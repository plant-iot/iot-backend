package com.nju.iot.entity;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/13
 * @description:
 */
public enum DeviceType {
    DATA_DEVICE(false, true),
    COMMAND_DEVICE(true, false),
    DATA_COMMAND_DEVICE(true, true);

    private final boolean canSendCommand;
    private final boolean canGetData;

    DeviceType(boolean canSendCommand, boolean canGetData) {
        this.canSendCommand = canSendCommand;
        this.canGetData = canGetData;
    }

    public boolean isCanSendCommand() {
        return canSendCommand;
    }

    public boolean isCanGetData() {
        return canGetData;
    }
}

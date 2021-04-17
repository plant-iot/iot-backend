package com.nju.iot.payloads;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
public enum SendCommandResult {
    SUCCESS("成功"),
    DEVICE_NOT_FOUND("未找到对应设备"),
    SENSOR_COMMAND("设备无法接受命令");

    private final String s;

    SendCommandResult(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}

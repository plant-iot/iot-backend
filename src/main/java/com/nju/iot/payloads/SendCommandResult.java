package com.nju.iot.payloads;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
public enum SendCommandResult {
    SUCCESS("成功"),
    NO_COMMAND("命令不存在"),
    DEVICE_NOT_FOUND("未找到对应设备"),
    OFF_LINE("设备已离线"),
    SENSOR_COMMAND("设备无法接受命令"),
    MISMATCHED_COMMAND("命令与设备不匹配");

    private final String s;

    SendCommandResult(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}

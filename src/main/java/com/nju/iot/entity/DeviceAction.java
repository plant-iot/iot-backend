package com.nju.iot.entity;

/**
 * @author: xiang
 * @date: 2021/4/17
 * @description: 设备状态
 */
public enum DeviceAction {
    IN_USE("可使用"),  // 可发送、接受信息
    DISABLED("以禁用"),// 不会发送、接受信息
    DELETED("已删除");

    private final String s;

    DeviceAction(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}

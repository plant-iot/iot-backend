package com.nju.iot.service.device;

import com.nju.iot.entity.DeviceType;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
public interface DeviceService {
    // 新建设备
    // 返回：正：创建成功，返回设备id；负：创建失败
    long addDevice(Long userId, DeviceType type);
}

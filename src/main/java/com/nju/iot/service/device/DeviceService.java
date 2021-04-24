package com.nju.iot.service.device;

import com.nju.iot.entity.DeviceType;
import com.nju.iot.payloads.DeviceInfo;
import com.nju.iot.payloads.DeviceLog;
import com.nju.iot.payloads.ThingModelRequest;

import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
public interface DeviceService {
    // 新建设备
    // 返回：正：创建成功，返回设备id；负：创建失败
    long addDevice(Long userId, DeviceType type, String deviceName, int modelId);

    // 新建设备
    // 返回：正：创建成功，返回设备id；负：创建失败
    long addDevice(Long userId, DeviceType type, ThingModelRequest modelRequest);

    // 删除设备
    void deleteDevice(Long deviceId);

    // 禁用设备
    void disableDevice(Long deviceId);

    // 启用设备
    boolean enableDevice(Long deviceId);

    // 查看设备信息
    DeviceInfo getDeviceInfo(long deviceId);

    // 查看用户的设备信息列表
    List<DeviceInfo> getDeviceInfoList(long userId);

    DeviceLog getDeviceLog(long deviceId);

    List<DeviceLog> getDeviceLogList(long userId);
    
    //执行数据分析python文件
    String dataAnalysis();
}

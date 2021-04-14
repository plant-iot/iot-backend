package com.nju.iot.service.connect;

import com.nju.iot.entity.CommandType;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/13
 * @description:
 */
public interface ConnectService {
    // 下发命令
    void sendCommand(Long deviceId, String command, CommandType type);
}

package com.nju.iot.service.connect;

import com.nju.iot.entity.CommandType;
import com.nju.iot.payloads.SendCommandResult;

import java.util.Map;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/13
 * @description:
 */
public interface ConnectService {
    // 下发命令
    SendCommandResult sendCommand(Long deviceId, String command/*, CommandType type*/);

    // 下发命令
    Map<Long, String> sendCommand(Long[] deviceIdList, String command/*, CommandType type*/);

    Map<Long, String> sendCommand(Long[] deviceIdList, String[] commands, Double[] values);
}

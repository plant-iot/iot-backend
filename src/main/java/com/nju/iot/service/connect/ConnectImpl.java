package com.nju.iot.service.connect;

import com.nju.iot.dao.CommandRepository;
import com.nju.iot.dao.DeviceRepository;
import com.nju.iot.entity.Command;
import com.nju.iot.entity.CommandType;
import com.nju.iot.entity.Device;
import com.nju.iot.payloads.SendCommandResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/13
 * @description:
 */
@Service
public class ConnectImpl implements ConnectService {
    @Autowired
    private MqttPushClient mqttPushClient;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private CommandRepository commandRepository;

    @Override
    public SendCommandResult sendCommand(Long deviceId, String command/*, CommandType type*/) {
        if(!deviceRepository.existsById(deviceId)) {
            return SendCommandResult.DEVICE_NOT_FOUND;
        }
        Device device = deviceRepository.findById(deviceId).get();
        if(!device.getType().isCanSendCommand()) {
            return SendCommandResult.SENSOR_COMMAND;
        }

        String topic = device.getTopic();
        mqttPushClient.publish(0, true, topic, command);

        LocalDateTime time = LocalDateTime.now();
        Command commandEntity = new Command(device, time, null, command);
        commandRepository.save(commandEntity);
        return SendCommandResult.SUCCESS;
    }

    @Override
    public Map<Long, String> sendCommand(Long[] deviceIdList, String command) {
        Map<Long, String> resultMap = new HashMap<>();

        if(deviceIdList == null) {
            return resultMap;
        }

        for(long id : deviceIdList) {
            SendCommandResult result = sendCommand(id, command);
            if(result != SendCommandResult.SUCCESS) {
                resultMap.put(id, result.getS());
            }
        }

        return resultMap;
    }
}

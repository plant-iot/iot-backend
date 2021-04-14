package com.nju.iot.service.connect;

import com.nju.iot.dao.CommandRepository;
import com.nju.iot.dao.DeviceRepository;
import com.nju.iot.entity.Command;
import com.nju.iot.entity.CommandType;
import com.nju.iot.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public void sendCommand(Long deviceId, String command, CommandType type) {
        Device device = deviceRepository.findById(deviceId).get();
        String topic = device.getTopic();
        mqttPushClient.publish(0, true, topic, command);

        LocalDateTime time = LocalDateTime.now();
        Command commandEntity = new Command(device, time, type, command);
        commandRepository.save(commandEntity);
    }
}

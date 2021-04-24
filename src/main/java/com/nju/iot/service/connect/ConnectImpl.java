package com.nju.iot.service.connect;

import com.alibaba.fastjson.JSONObject;
import com.nju.iot.dao.CommandRepository;
import com.nju.iot.dao.DeviceRepository;
import com.nju.iot.dao.ThingModelRecordRepository;
import com.nju.iot.entity.*;
import com.nju.iot.payloads.SendCommandResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Autowired
    private ThingModelRecordRepository thingModelRecordRepository;

//    @Override
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
    public Map<Long, String> sendCommand(Long[] deviceIdList, String[] commands, Double[] values) {
        Map<Long, String> resultMap = new HashMap<>();

        if(deviceIdList == null) {
            return resultMap;
        }

        for(long id : deviceIdList) {
            resultMap.put(id, sendCommand(id, commands, values));
        }

        return resultMap;
    }

    @Override
    public String sendCommand(Long deviceId, String[] commands, Double[] values) {
        System.out.println("in send command");
        System.out.println("device: " + deviceId);
        System.out.println("commands: " + Arrays.toString(commands));
        System.out.println("values: " + Arrays.toString(values));
        if(!deviceRepository.existsById(deviceId)) {
            return SendCommandResult.DEVICE_NOT_FOUND.getS();
        }
        Device device = deviceRepository.findById(deviceId).get();
        if(device.getState() != DeviceAction.IN_USE) {
            return SendCommandResult.OFF_LINE.getS();
        }
        JSONObject jsonObject = new JSONObject();
        SendCommandResult result = SendCommandResult.SUCCESS;
        for(int i = 0 ; i < commands.length ; i++) {
            String command = commands[i];
            ServiceName name = ServiceName.getInstance(command);
            if(null == name) {
                result = SendCommandResult.NO_COMMAND;
            }else {
                ThingModel thingModel = device.getModel();
                if(thingModelRecordRepository.existsByModel(thingModel)) {
                    jsonObject.put(name.toString(), values[i]);
                }else {
                    result = SendCommandResult.MISMATCHED_COMMAND;
                }
            }
        }
        if(result == SendCommandResult.SUCCESS) {
            result = sendCommand(deviceId, jsonObject.toJSONString());
        }
        return result.getS();
    }
}

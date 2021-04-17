package com.nju.iot.service.connect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.nju.iot.dao.DataRepository;
import com.nju.iot.dao.DeviceOnOffRecordRepository;
import com.nju.iot.dao.DeviceRepository;
import com.nju.iot.entity.*;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/13
 * @description: 消费监听类
 */
@Service
public class PushCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(PushCallback.class);

    @Autowired
    private MqttProperties mqttProperties;
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceOnOffRecordRepository deviceOnOffRecordRepository;

    private static MqttClient client;

    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        logger.info("连接断开，可以做重连");
//        if (client == null || !client.isConnected()) {
            mqttProperties.getMqttPushClient();
//        }
        logger.info("已重连");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        logger.info("接收消息主题 : " + topic);
        logger.info("接收消息内容 : " + new String(mqttMessage.getPayload()));

        if(topic.endsWith("/disconnected")) {
            Long deviceId = Long.valueOf(topic.split("/")[4]);
            logger.info("设备 {} 下线", deviceId);
            if(deviceRepository.existsById(deviceId)) {
                Device device = deviceRepository.findById(deviceId).get();
                device.setOnline(false);
                deviceRepository.save(device);
                DeviceOnOffRecord record = new DeviceOnOffRecord(device, OnOffAction.OFF);
                deviceOnOffRecordRepository.save(record);
            }
        }else if(topic.endsWith("/connected")) {
            Long deviceId = Long.valueOf(topic.split("/")[4]);
            logger.info("设备 {} 上线", deviceId);
            if(deviceRepository.existsById(deviceId)) {
                Device device = deviceRepository.findById(deviceId).get();
                device.setOnline(true);
                deviceRepository.save(device);
                DeviceOnOffRecord record = new DeviceOnOffRecord(device, OnOffAction.ON);
                deviceOnOffRecordRepository.save(record);
            }
        }

        String[] topicList = topic.split("/");
        if(topicList.length == 2) {
            Long deviceId = Long.valueOf(topicList[1]);
            logger.info("接收到来自设备{}的消息", deviceId);

            if(!deviceRepository.existsById(deviceId)) {
                return;
            }
            Device device = deviceRepository.findById(deviceId).get();
            LocalDateTime time = LocalDateTime.now();

            String msg = new String(mqttMessage.getPayload());
            try {
                JSONObject jsonObject = JSON.parseObject(msg);
                System.out.println(jsonObject.toJSONString());
                for(DataType dataType : DataType.values()) {
                    if(jsonObject.containsKey(dataType.getS())) {
                        double value = jsonObject.getDoubleValue(dataType.getS());
                        Data data = new Data(time, device, dataType, value);
                        dataRepository.save(data);
                    }
                }

            } catch (JSONException e) {
                logger.error("JSON Format Parsing Exception : {}", msg);
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
}

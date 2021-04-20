package com.nju.iot.service.connect;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/19
 * @description:
 */
@Service
public class SysPushClient{
    private static final Logger logger = LoggerFactory.getLogger(SysPushClient.class);

    @Autowired
    private PushCallback pushCallback;

    private static MqttClient client;

    private static MqttClient getClient() {
        return client;
    }

    private static void setClient(MqttClient client) {
        SysPushClient.client = client;
    }

    public void connect(String host, String clientID, String username, String password, int timeout, int keepAlive) {
        MqttClient client;
        try {
            client = new MqttClient(host, clientID, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(timeout);
            options.setKeepAliveInterval(keepAlive);
            SysPushClient.setClient(client);
            try {
                client.setCallback(pushCallback);
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(int qos, boolean retained, String topic, String pushMessage) {
        logger.info("publish topic: {}, message: {}", topic, pushMessage);
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mqttTopic = SysPushClient.getClient().getTopic(topic);
        if(mqttTopic == null) {
            logger.info("topic not exist");
        }
        client.setCallback(pushCallback);
        MqttDeliveryToken token;
        try {
            token = mqttTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic) {
        logger.info("开始订阅主题" + topic);
        try {
            SysPushClient.getClient().subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

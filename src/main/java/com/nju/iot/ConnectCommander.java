package com.nju.iot;

import com.nju.iot.service.connect.MqttProperties;
import com.nju.iot.service.connect.MqttPushClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/19
 * @description:
 */
@Component
public class ConnectCommander implements CommandLineRunner {
    @Autowired
    private MqttPushClient mqttPushClient;
    @Autowired
    private MqttProperties mqttProperties;

    private static final Logger logger = LoggerFactory.getLogger(ConnectCommander.class);
    @Override
    public void run(String... args) throws Exception {
        logger.info("in ConnectCommander run");
        mqttPushClient.publish(0, true, "test", "{platform: online}");

    }
}

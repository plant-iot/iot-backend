package com.nju.iot.service.connect;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/11
 * @description:
 */
@Data
@Configuration
@ConfigurationProperties("spring.mqtt")
@Setter
@Getter
public class MqttProperties {
    @Autowired
    private MqttPushClient mqttPushClient;

    private String url;
    private String username;
    private String password;
    private String clientId;
    private String defaultTopic;
    private int timeout;
    private int keepalive;
    private String sysTopic;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getClientId() {
        return clientId;
    }

    public String getDefaultTopic() {
        return defaultTopic;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getKeepalive() {
        return keepalive;
    }

    public String getSysTopic() {
        return sysTopic;
    }

    @Bean
    public MqttPushClient getMqttPushClient() {
        mqttPushClient.connect(url, clientId, username, password, timeout, keepalive);
        mqttPushClient.subscribe(defaultTopic);
        return mqttPushClient;
    }

    @Bean
    public MqttPushClient getMqttPushSysClient() {
        mqttPushClient.connect(url, clientId, username, password, timeout, keepalive);
        mqttPushClient.subscribe(sysTopic);
        return mqttPushClient;
    }
}

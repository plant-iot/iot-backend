//package com.nju.iot;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import com.nju.iot.service.connect.MqttProperties;
//
///**
// * @author: xiang
// * TODO
// * @date: 2021/4/14
// * @description:
// */
//@Component
//public class Mqtt implements CommandLineRunner {
//    @Autowired
//    private MqttProperties mqttProperties;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("url: " + mqttProperties.getUrl());
//        System.out.println("username: " + mqttProperties.getUsername());
//        System.out.println("password: " + mqttProperties.getPassword());
//        System.out.println("clientId: " + mqttProperties.getClientId());
//        System.out.println("defaultTopic: " + mqttProperties.getDefaultTopic());
//        System.out.println("timeout: " + mqttProperties.getTimeout());
//        System.out.println("keepalive: " + mqttProperties.getKeepalive());
//    }
//}

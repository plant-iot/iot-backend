package com.nju.iot.controller;

import com.nju.iot.service.connect.MqttPushClient;
import com.nju.iot.dao.DeviceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiang
 * @date: 2021/4/11
 * @description:
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试页面")
public class TestController {
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/test")
    @ApiOperation("测试")
    @ApiImplicitParam(name = "str", value = "字符串")
    public String getDeviceState(String str) {
        return "hello " + str + "   device num: " + deviceRepository.count();
    }

    @Autowired
    private MqttPushClient mqttPushClient;

    @GetMapping("/test_mqtt")
    @ApiOperation("测试MQTT发布主题")
    @ApiImplicitParam(name = "topic", value = "主题")
    public void publicTopic(String topic) {
        mqttPushClient.publish(0,false, topic,"{\n" +
                "  \"msg\": \"测试MQTT发布主题\"\n" +
                "}");
    }
}

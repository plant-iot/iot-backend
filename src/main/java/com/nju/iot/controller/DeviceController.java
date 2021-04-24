package com.nju.iot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nju.iot.entity.CommandType;
import com.nju.iot.entity.DeviceType;
import com.nju.iot.payloads.DeviceInfo;
import com.nju.iot.service.connect.ConnectService;
import com.nju.iot.service.device.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/14
 * @description:
 */
@RestController
@RequestMapping("/device")
@Api(tags = "设备页面")
public class DeviceController {
    @Autowired
    private ConnectService connectService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/sendCommand")
    @ApiOperation("下发命令")
    @ApiImplicitParams({
            /*@ApiImplicitParam(name = "deviceId", value = "设备id"),
            @ApiImplicitParam(name = "commands", value = "命令类型"),
            @ApiImplicitParam(name = "values", value = "命令参数")*/
    })
    public String sendCommand(@RequestBody String requestStr/*long deviceId, String[] commands, Double[] values*/) {
        JSONObject object = JSON.parseObject(requestStr);
        return connectService.sendCommand(object.getLong("deviceId"),
                object.getObject("commands", String[].class),
                object.getObject("values", Double[].class));
    }

    @PostMapping("/sendCommands")
    @ApiOperation("群体下发多条命令")
    @ApiImplicitParams({
            /*@ApiImplicitParam(name = "deviceIdList", value = "设备id列表"),
            @ApiImplicitParam(name = "commands", value = "命令列表"),
            @ApiImplicitParam(name = "values", value = "命令参数")*/
    })
    public Map<Long, String> sendCommands(@RequestBody String requestStr/*Long[] deviceIdList, String[] commands, Double[] values*/) {
        JSONObject object = JSON.parseObject(requestStr);
        return connectService.sendCommand(object.getObject("deviceIdList", Long[].class),
                object.getObject("commands", String[].class),
                object.getObject("values", Double[].class));
    }

    @PostMapping("/addDevice")
    @ApiOperation("添加设备")
    @ApiImplicitParams({
    })
    public long addDevice(@RequestBody String str) {
        JSONObject json = JSON.parseObject(str);
        return deviceService.addDevice(json.getLong("userId"),
                DeviceType.getType(json.getString("type")),
                json.getString("deviceName"),
                json.getIntValue("modelId"));
    }

    @GetMapping("/deleteDevice")
    @ApiOperation("删除设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "设备id")
    })
    public void deleteDevice(Long deviceId) {
        deviceService.deleteDevice(deviceId);
    }

    @GetMapping("/disableDevice")
    @ApiOperation("禁用设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "设备id")
    })
    public void disableDevice(Long deviceId) {
        deviceService.disableDevice(deviceId);
    }

    @GetMapping("/enableDevice")
    @ApiOperation("启用设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "设备id")
    })
    public boolean enableDevice(Long deviceId) {
        return deviceService.enableDevice(deviceId);
    }
}

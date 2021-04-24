package com.nju.iot.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/sendCommand")
    @ApiOperation("下发命令")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "设备id"),
            @ApiImplicitParam(name = "commands", value = "命令类型"),
            @ApiImplicitParam(name = "values", value = "命令参数")
    })
    public String sendCommand(long deviceId, String[] commands, Double[] values) {
        return connectService.sendCommand(deviceId, commands, values);
    }

    @GetMapping("/sendCommands")
    @ApiOperation("群体下发多条命令")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceIdList", value = "设备id列表"),
            @ApiImplicitParam(name = "commands", value = "命令列表"),
            @ApiImplicitParam(name = "values", value = "命令参数")
    })
    public Map<Long, String> sendCommand(Long[] deviceIdList, String[] commands, Double[] values) {
        return connectService.sendCommand(deviceIdList, commands, values);
    }

    @GetMapping("/addDevice")
    @ApiOperation("添加设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "type", value = "设备类型"),
            @ApiImplicitParam(name = "deviceName", value = "设备名"),
            @ApiImplicitParam(name = "modelId", value = "物模型"),
    })
    public DeviceInfo addDevice(Long userId, String type, String deviceName, int modelId) {
        return deviceService.addDevice(userId, DeviceType.getType(type), deviceName, modelId);
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

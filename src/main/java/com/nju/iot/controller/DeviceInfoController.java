package com.nju.iot.controller;

import com.nju.iot.payloads.DeviceInfo;
import com.nju.iot.service.device.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
@RestController
@RequestMapping("/deviceinfo")
@Api(tags = "设备信息页面")
public class DeviceInfoController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/getDeviceInfo")
    @ApiOperation("查看设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "设备id"),
    })
    public DeviceInfo sendCommand(long deviceId) {
        return deviceService.getDeviceInfo(deviceId);
    }
}

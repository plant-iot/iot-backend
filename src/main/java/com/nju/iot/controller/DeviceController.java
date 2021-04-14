package com.nju.iot.controller;

import com.nju.iot.entity.CommandType;
import com.nju.iot.service.connect.ConnectService;
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
 * @date: 2021/4/14
 * @description:
 */
@RestController
@RequestMapping("/device")
@Api(tags = "设备页面")
public class DeviceController {
    @Autowired
    private ConnectService connectService;

    @GetMapping("/sendCommand")
    @ApiOperation("下发命令")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "设备id"),
            @ApiImplicitParam(name = "command", value = "命令（json格式）"),
            @ApiImplicitParam(name = "type", value = "命令类型")
    })
    public boolean sendCommand(long deviceId, String command, String type) {
        CommandType commandType = CommandType.getCommandType(type);
        if(commandType == null) {
            return false;
        }
        return connectService.sendCommand(deviceId, command, commandType);
    }
}

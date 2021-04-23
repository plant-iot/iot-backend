package com.nju.iot.controller;

import com.nju.iot.entity.ThingModel;
import com.nju.iot.payloads.ThingModelInfo;
import com.nju.iot.payloads.ThingModelRequest;
import com.nju.iot.service.thingModel.ThingModelInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/20
 * @description:
 */
@RestController
@RequestMapping("/thingModel")
@Api(tags = "物模型操作")
public class ThingModelController {
    @Autowired
    private ThingModelInterface thingModelInterface;

    @PostMapping(value = "/addThingModel" ,produces = "application/json;charset=UTF-8")
    @ApiOperation("新增物模型")
    @ApiImplicitParams({
             @ApiImplicitParam(name = "request", value = "物模型"),
    })
    public ThingModelInfo addThingModel(@RequestBody ThingModelRequest request) {
        return thingModelInterface.persistThingModel(request.getUserId(), request);
    }

    @GetMapping("/getThingModel")
    @ApiOperation("查看物模型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id")
    })
    public List<ThingModelInfo> getThingModel(Long userId) {
        return thingModelInterface.getThingModel(userId);
    }

    @GetMapping("/getDeviceThingModel")
    @ApiOperation("查看设备物模型服务名称列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "设备id")
    })
    public List<String> getDeviceThingModel(Long deviceId) {
        return thingModelInterface.getDeviceThingModel(deviceId);
    }

}

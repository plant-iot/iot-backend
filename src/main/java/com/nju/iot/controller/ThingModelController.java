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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO test
    @GetMapping("/addThingModel")
    @ApiOperation("新增物模型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "request", value = "物模型"),
    })
    public ThingModelInfo addThingModel(Long userId, @RequestBody ThingModelRequest request) {
        return thingModelInterface.persistThingModel(userId, request);
    }

    @GetMapping("/getThingModel")
    @ApiOperation("查看物模型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id")
    })
    public List<ThingModelInfo> addThingModel(Long userId) {
        return thingModelInterface.getThingModel(userId);
    }
}

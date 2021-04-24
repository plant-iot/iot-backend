package com.nju.iot.controller;

import com.nju.iot.payloads.DataRecord;
import com.nju.iot.service.data.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/21
 * @description:
 */
@RestController
@RequestMapping("/deviceinfo")
@Api(tags = "数据信息页面")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("/getData")
    @ApiOperation("查看数据信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
    })
    public List<DataRecord> getData(long userId) {
        return dataService.getData(userId);
    }

    @GetMapping("/getDataByFilter")
    @ApiOperation("过滤查看数据信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "deviceId", value = "设备id"),
            @ApiImplicitParam(name = "dataType", value = "数据类别"),
            @ApiImplicitParam(name = "start", value = "开始时间(yyyy-MM-dd)"),
            @ApiImplicitParam(name = "end", value = "结束时间(yyyy-MM-dd)"),
    })
    public List<DataRecord> getData(long userId, Long deviceId, String dataType, String start, String end) {
        return dataService.getData(userId, deviceId, dataType, start, end);
    }
}

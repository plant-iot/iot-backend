package com.nju.iot.controller;

import com.nju.iot.entity.DeviceType;
import com.nju.iot.service.group.GroupService;
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
 * @date: 2021/4/18
 * @description:
 */
@RestController
@RequestMapping("/group")
@Api(tags = "设备群组页面")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/addGroup")
    @ApiOperation("添加设备群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "name", value = "设备群组名"),
            @ApiImplicitParam(name = "deviceIdList", value = "设备id列表")
    })
    public boolean addDevice(Long userId, String name, Long[] deviceIdList) {
        return groupService.addGroup(userId, name, deviceIdList);
    }
}
